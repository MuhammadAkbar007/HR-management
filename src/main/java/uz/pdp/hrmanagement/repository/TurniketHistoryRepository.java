package uz.pdp.hrmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hrmanagement.entity.Turniket;
import uz.pdp.hrmanagement.entity.TurniketHistory;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface TurniketHistoryRepository extends JpaRepository<TurniketHistory, UUID> {

    List<TurniketHistory> findAllByTurniketAndTimeIsBetween(Turniket turniket, Timestamp time, Timestamp time2);
    List<TurniketHistory> findAllByTurniket(Turniket turniket);
}
