package io.bootify.minor_project.repos;

import io.bootify.minor_project.domain.Address;
import io.bootify.minor_project.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VisitorRepository extends JpaRepository<Visitor, Long> {

    Visitor findFirstByAddress(Address address);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhoneIgnoreCase(String phone);

    boolean existsByIdNumberIgnoreCase(String idNumber);

}
