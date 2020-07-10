package mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base;

import mk.ukim.finki.emt.onlineshop.sharedkernel.infra.eventlog.StoredDomainEvent;

import java.util.List;

public interface RemoteEventLog {

    List<StoredDomainEvent> events();

}

