package jp.co.pew.webflux.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Router {

	private final BasicService service;

	public Router(BasicService service) {
		this.service = service;

	}

	@Bean
	RouterFunction<ServerResponse> routes() {
		return route()
				.GET("/react-list",
						serverRequest -> ServerResponse.ok().contentType(MediaType.TEXT_HTML)
								.body(service.findReactorList(), String.class))
				.GET("/normal-list", ServerRequest -> ServerResponse.ok().contentType(MediaType.TEXT_HTML)
						.body(service.findNormalList(), String.class))
				.GET("/load", ServerRequest -> {
					service.loadData();
					return ServerResponse.ok().body(BodyInserters.fromObject("completed"));
				}).build();
	}
}
