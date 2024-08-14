package kz.medet.idrish_medet_hw_27.repositories;

import kz.medet.idrish_medet_hw_27.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
