package io.bootify.minor_project.repos;

import io.bootify.minor_project.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {
}
