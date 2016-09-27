using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;

using AgileVendorPool.MessageHandlers;
using Newtonsoft.Json.Serialization;
using System.Net.Http.Headers;
using Newtonsoft.Json;

namespace AgileVendorPool
{
    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            // Web API configuration and services

            // When adding handlers, the order is IMPORTANT
            config.MessageHandlers.Add(new MessageHandler1());
            // For future use
            //config.MessageHandlers.Add(new ErrorHandler());


            // Web API routes
            config.MapHttpAttributeRoutes();

            config.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: "api/{controller}/{id}",
                defaults: new { id = RouteParameter.Optional }
            );


            // SY
            //Force web.api to return JSON by default.
            config.Formatters.JsonFormatter.SupportedMediaTypes.Add(new MediaTypeHeaderValue("text/plain"));
            config.Formatters.JsonFormatter.SerializerSettings.ReferenceLoopHandling = Newtonsoft.Json.ReferenceLoopHandling.Ignore;
            config.Formatters.Remove(config.Formatters.FormUrlEncodedFormatter);
            config.Formatters.Remove(config.Formatters.XmlFormatter);

            // SY
            //Force camelCase all JSON objects by default...
            var formatters = GlobalConfiguration.Configuration.Formatters;
            var jsonFormatter = formatters.JsonFormatter;
            var settings = jsonFormatter.SerializerSettings;
            settings.Formatting = Formatting.Indented;
            settings.ContractResolver = new CamelCasePropertyNamesContractResolver();

        }
    }
}
