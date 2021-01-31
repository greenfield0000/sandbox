package greenfield.group.com.gateway.intercaptors;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Интерсептор для прокидывания токена
 *
 * @author Ivanov Roman
 */
public class RestTemplateClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private final String bearerValue;

    public RestTemplateClientHttpRequestInterceptor(String bearerValue) {
        this.bearerValue = bearerValue;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest
            , byte[] bytes
            , ClientHttpRequestExecution clientHttpRequestExecution
    ) throws IOException {
        httpRequest.getHeaders().add("Authorization", bearerValue);
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
