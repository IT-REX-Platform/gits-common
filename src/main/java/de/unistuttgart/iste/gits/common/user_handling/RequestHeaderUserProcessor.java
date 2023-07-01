package de.unistuttgart.iste.gits.common.user_handling;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import reactor.core.publisher.Mono;

import java.util.Collections;

public class RequestHeaderUserProcessor {
    @SneakyThrows
    public static Mono<WebGraphQlResponse> process(WebGraphQlRequest request, WebGraphQlInterceptor.Chain chain) {
        String value = request.getHeaders().getFirst("CurrentUser");

        User currentUser = (new ObjectMapper()).readValue(value, User.class);

        request.configureExecutionInput(
                ((executionInput, builder) -> builder.graphQLContext(Collections.singletonMap(
                        "currentUser", currentUser
                )).build())
        );

        return chain.next(request);
    }
}