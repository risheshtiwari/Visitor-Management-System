package io.bootify.minor_project.repos;

import io.bootify.minor_project.domain.Address;
import io.bootify.minor_project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByAddress(Address address);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhoneIgnoreCase(String phone);

}
