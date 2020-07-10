package mk.ukim.finki.emt.onlineshop.sharedkernel.port.client;

import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.RemoteEventLog;
import mk.ukim.finki.emt.onlineshop.sharedkernel.infra.eventlog.RemoteEventLogService;
import mk.ukim.finki.emt.onlineshop.sharedkernel.infra.eventlog.StoredDomainEvent;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RemoteEventLogServiceClient implements RemoteEventLogService {

    private final String source;
    private final String serverUrl;
    private final RestTemplate restTemplate;

    public RemoteEventLogServiceClient(@NonNull String serverUrl, int connectTimeout, int readTimeout){
        this.source = Objects.requireNonNull(serverUrl, "serverUrl must not be null");
        this.serverUrl = serverUrl;
        restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        restTemplate.setRequestFactory(requestFactory);
    }

    @Override
    public String source() {
        return source;
    }

    @Override
    public RemoteEventLog currentLog(long lastProcessedId) {
        URI currentLogUri = UriComponentsBuilder.fromUriString(serverUrl)
                .path(String.format("/api/event-log/%d", lastProcessedId)).build().toUri();
        return retrieveLog(currentLogUri);
    }

    @NonNull
    private RemoteEventLog retrieveLog(@NonNull URI uri){
        ResponseEntity<List<StoredDomainEvent>> response = restTemplate
                .exchange(uri, HttpMethod.GET, null, new
                        ParameterizedTypeReference<List<StoredDomainEvent>>() {
                        });
        if(response.getStatusCode() != HttpStatus.OK){
            throw new IllegalArgumentException("Could not retrieve URI " + uri);
        }
        return new RemoteEventLogImpl(response);
    }

    private class RemoteEventLogImpl implements RemoteEventLog {

        private final List<StoredDomainEvent> events;

        private RemoteEventLogImpl(@NonNull ResponseEntity<List<StoredDomainEvent>> responseEntity) {
            events = List.copyOf(Objects.requireNonNull(responseEntity.getBody()));
        }

        @Override
        public List<StoredDomainEvent> events() {
            return events;
        }

    }

}
