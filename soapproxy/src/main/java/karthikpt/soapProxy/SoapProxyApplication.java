package karthikpt.soapProxy;

import io.netty.handler.logging.LogLevel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.unit.DataSize;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.SslProvider;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.security.KeyStore;
import java.time.Duration;

@Slf4j
@SpringBootApplication
public class SoapProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapProxyApplication.class, args);

	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder, WebClient webClient) {
//build route
		return builder.routes()
				.route("soapProxy", r -> r.path("/soapProxy/**")
								.filters(f -> f.rewritePath("/soapProxy/(?<segment>.*)", "/${segment}")
										.localResponseCache(Duration.ofMinutes(2), DataSize.ofMegabytes(10))
										.circuitBreaker(config -> config.setName("soapProxyCircuitBreaker")
												.setFallbackUri("forward:/gatewayError"))
										.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
												.setKeyResolver(clientKeyResolver()))
								)
								.metadata("webClient", webClient)
								.uri("https://www.crcind.com/")
						//.uri(uri -> uri.scheme("https").host("www.crcind.com").port(443).build())

				)
				.build();
	}

	//ratelimiter config
	@Bean
	public RedisRateLimiter redisRateLimiter(){
		return new RedisRateLimiter(30,60,60);
	}

	@Bean
	KeyResolver clientKeyResolver(){
		return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("client"))
				.defaultIfEmpty("anonymous");
	}


	//ssl client config
	@Bean
	public WebClient webClient() throws Exception {
		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		log.info("testing logging with lombok");
		try (InputStream trustStoreStream = getClass().getResourceAsStream("/truststore.jks")) {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(trustStoreStream, "secret".toCharArray()); // Load your truststore here
			trustManagerFactory.init(trustStore);
		}

		SslContext sslContext = SslContextBuilder.forClient()
				.trustManager(trustManagerFactory)
				.build();

		HttpClient httpClient = HttpClient.create()
				.wiretap(this.getClass().getCanonicalName(), LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL)
				.secure(sslSpec -> sslSpec.sslContext(sslContext));

		//add logging



		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(httpClient))

				.build();

	/*	WebClient.Builder webClientBuilder = WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient));
		// Apply logging filter
		webClientBuilder.filters(filters -> filters.add(logRequestResponse));
		return webClientBuilder.build();
		*/

	}

}
