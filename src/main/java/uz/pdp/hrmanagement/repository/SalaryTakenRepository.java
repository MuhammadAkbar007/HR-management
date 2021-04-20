package uz.pdp.hrmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hrmanagement.entity.SalaryTaken;
import uz.pdp.hrmanagement.entity.User;
import uz.pdp.hrmanagement.enums.Months;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SalaryTakenRepository extends JpaRepository<SalaryTaken, UUID> {

    Optional<SalaryTaken> findByOwnerAndPeriod(User owner, Months period);

    List<SalaryTaken> findAllByOwner(User user);

    List<SalaryTaken> findAllByPeriod(Months period);
}
