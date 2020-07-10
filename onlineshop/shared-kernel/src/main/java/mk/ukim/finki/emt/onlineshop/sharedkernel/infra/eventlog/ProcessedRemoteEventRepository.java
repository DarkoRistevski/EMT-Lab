package mk.ukim.finki.emt.onlineshop.sharedkernel.infra.eventlog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedRemoteEventRepository extends JpaRepository<ProcessedRemoteEvent,String> {
}

