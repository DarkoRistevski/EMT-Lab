package mk.ukim.finki.emt.onlineshop.sharedkernel.infra.eventlog;

import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.RemoteEventLog;

public interface RemoteEventLogService {

    String source();

    RemoteEventLog currentLog(long lastProcessedId);

}

