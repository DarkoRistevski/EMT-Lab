package mk.ukim.finki.emt.onlineshop.sharedkernel.infra.eventlog;

import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.IdentifiableDomainObject;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "processed_remote_events")
class ProcessedRemoteEvent implements IdentifiableDomainObject<String> {

    @Id
    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "last_processed_event_d", nullable = false)
    private long lastProcessedEventId;

    @SuppressWarnings("unused") // Used by JPA only
    private ProcessedRemoteEvent() {
    }

    ProcessedRemoteEvent(String source, long lastProcessedEventId) {
        this.source = Objects.requireNonNull(source);
        this.lastProcessedEventId = lastProcessedEventId;
    }

    @NonNull
    public String source() {
        return source;
    }

    @NonNull
    public long lastProcessedEventId() {
        return lastProcessedEventId;
    }

    @Override
    public String id() {
        return source();
    }
}
