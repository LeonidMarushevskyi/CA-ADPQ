package com.portlandwebworks.chhs.authentication.hystrix;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * A bit convulated, but works around issue of FeignClients getting executed
 * in a separate thread by Hystrix. Spring's SecurityContext is Thread bound
 * per request, and since Hystrix runs the client request in a new background
 * thread we need to set the correct context up in order to still be able to 
 * access it.
 * 
 * Gist is that this is setting Hystrix parent thread context to the main request
 * thread so context variables can be shared.
 * 
 * @see HystrixRequestContextEnablerFilter
 * @see HystrixSecurityContextSetterFilter
 * 
 * @author nick
 */
@Component
public class CustomHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

	private static final Logger log = LoggerFactory.getLogger(CustomHystrixConcurrencyStrategy.class);
	public CustomHystrixConcurrencyStrategy() {
		HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
	}

	@Override
	public <T> Callable<T> wrapCallable(Callable<T> callable) {
		return new HystrixContextWrapper<T>(callable);
	}

	public static class HystrixContextWrapper<V> implements Callable<V> {

		private HystrixRequestContext hystrixRequestContext;
		private Callable<V> delegate;

		public HystrixContextWrapper(Callable<V> delegate) {
			this.hystrixRequestContext = HystrixRequestContext.getContextForCurrentThread();
			this.delegate = delegate;
		}

		@Override
		public V call() throws Exception {
			HystrixRequestContext existingState = HystrixRequestContext.getContextForCurrentThread();
			try {
				HystrixRequestContext.setContextOnCurrentThread(this.hystrixRequestContext);
				return this.delegate.call();
			} finally {
				HystrixRequestContext.setContextOnCurrentThread(existingState);
			}
		}
	}
}
