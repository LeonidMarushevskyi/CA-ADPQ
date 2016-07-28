package com.tabordasolutions.cws.parentportal;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import java.util.EnumSet;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.client.Client;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tabordasolutions.cws.parentportal.api.Conversation;
import com.tabordasolutions.cws.parentportal.api.ConversationDAO;
import com.tabordasolutions.cws.parentportal.api.Message;
import com.tabordasolutions.cws.parentportal.api.MessageDAO;
import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.api.UserDAO;
import com.tabordasolutions.cws.parentportal.api.response.RuntimeExceptionMapper;
import com.tabordasolutions.cws.parentportal.auth.Cryptography;
import com.tabordasolutions.cws.parentportal.filters.ModifyResponseFilter;
import com.tabordasolutions.cws.parentportal.resources.AgencyResource;
import com.tabordasolutions.cws.parentportal.resources.ChhsOpenDataAgencyResource;
import com.tabordasolutions.cws.parentportal.resources.ConversationResource;
import com.tabordasolutions.cws.parentportal.resources.MessageResource;
import com.tabordasolutions.cws.parentportal.resources.ParentPortalResource;
import com.tabordasolutions.cws.parentportal.resources.SessionResource;
import com.tabordasolutions.cws.parentportal.resources.UserResource;
import com.tabordasolutions.cws.parentportal.services.ConversationService;
import com.tabordasolutions.cws.parentportal.services.MessageService;
import com.tabordasolutions.cws.parentportal.services.SessionService;
import com.tabordasolutions.cws.parentportal.services.UserService;

public class ParentPortalApplication extends Application<ParentPortalConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParentPortalApplication.class);

    private final HibernateBundle<ParentPortalConfiguration> hibernateBundle = new HibernateBundle<ParentPortalConfiguration>(User.class, Message.class, Conversation.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(ParentPortalConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    private final FlywayBundle<ParentPortalConfiguration> flywayBundle = new FlywayBundle<ParentPortalConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(ParentPortalConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }

        @Override
        public FlywayFactory getFlywayFactory(ParentPortalConfiguration configuration) {
            return configuration.getFlywayFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new ParentPortalApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ParentPortalConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor()
                )
        );
//        bootstrap.addBundle(new SwaggerBundle<ParentPortalConfiguration>() {
//            @Override
//            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(ParentPortalConfiguration configuration) {
//                return configuration.swaggerBundleConfiguration;
//            }
//        });

        bootstrap.addBundle(flywayBundle);
        bootstrap.addBundle(hibernateBundle);
    }
    
    @Override
    public void run(final ParentPortalConfiguration configuration, final Environment environment) throws Exception {
        LOGGER.info("Application name: {}", configuration.getApplicationName());

        LOGGER.info("Registering Application Resources");
        registerResources(configuration, environment, hibernateBundle.getSessionFactory());

        LOGGER.info("Registering Servlet Filters");
        registerFilters(configuration, environment);
        
        LOGGER.info("Configuring Flyway DB migration");
        migrateDatabase(configuration);

        LOGGER.info("Configuring CORS: Cross-Origin Resource Sharing");
        configureCors(environment);
        
        LOGGER.info("Registering Exception Mappers");
        registerExceptionMappers(environment);
        
    }
    
    private void registerExceptionMappers(Environment environment) {
    	environment.jersey().register(new RuntimeExceptionMapper());
    }

    private void registerResources(final ParentPortalConfiguration configuration, final Environment environment, final SessionFactory sessionFactory) {
        final ParentPortalResource applicationResource = new ParentPortalResource(configuration.getApplicationName());
        environment.jersey().register(applicationResource);

        UserService userService = new UserService(new UserDAO(sessionFactory));
        Cryptography cryptography = new Cryptography(configuration.getEncryptSalt());
        SessionService sessionService = new SessionService(userService, cryptography);
        final SessionResource sessionResource = new SessionResource(sessionService);
        environment.jersey().register(sessionResource);


        ConversationDAO conversationDAO = new ConversationDAO(sessionFactory);
        MessageDAO messageDAO = new MessageDAO(sessionFactory);
        ConversationService conversationService = new ConversationService(conversationDAO, messageDAO);
        final ConversationResource conversationResource = new ConversationResource(conversationService, sessionService, userService);
        environment.jersey().register(conversationResource);

        final MessageResource messageResource = new MessageResource(new MessageService(messageDAO,conversationDAO),conversationService, sessionService, userService);
        environment.jersey().register(messageResource);

        final UserResource userResource = new UserResource(userService, sessionService);
        environment.jersey().register(userResource);

        final AgencyResource agencyResource = agencyResource(configuration, environment);
        environment.jersey().register(agencyResource);
    }
    
    private void registerFilters(final ParentPortalConfiguration configuration, final Environment environment) {
    	environment.servlets().addFilter("ModfifyResponseFilter", new ModifyResponseFilter()).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
    }
    

	private AgencyResource agencyResource(ParentPortalConfiguration configuration, Environment environment) {
		final Client client = new JerseyClientBuilder(environment).using(configuration.getJerseyClient())
                   .build(getName());
		return new ChhsOpenDataAgencyResource(client, configuration.getApiChhsUrl(), configuration.getApiChhsKey(), configuration.getApiChhsQueryParam());
	}

    private void migrateDatabase(ParentPortalConfiguration configuration) {
        List<String> locations = configuration.getFlywayFactory().getLocations();
        String[] locationsAsStringArray = new String[locations.size()];
        locations.toArray(locationsAsStringArray);
        Flyway flyway = new Flyway();
        flyway.setDataSource(configuration.getDataSourceFactory().getUrl(),
                configuration.getDataSourceFactory().getUser(),
                configuration.getDataSourceFactory().getPassword());
        flyway.setSqlMigrationPrefix(configuration.getFlywayFactory().getSqlMigrationPrefix());
        flyway.setLocations(locationsAsStringArray);
        flyway.migrate();
    }

    private void configureCors(Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,X-Auth-Token");
        filter.setInitParameter("allowCredentials", "true");
    }
}
