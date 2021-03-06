package mk.ukim.finki.emt.onlineshop.sharedkernel.infra.eventlog;

import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.DomainEvent;

import java.util.Optional;

public interface RemoteEventTranslator {

    boolean supports(StoredDomainEvent storedDomainEvent);

    Optional<DomainEvent> translate(StoredDomainEvent remoteEvent);
}
