package uz.pdp.hrmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hrmanagement.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
