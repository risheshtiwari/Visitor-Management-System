package io.bootify.minor_project.repos;

import io.bootify.minor_project.domain.Flat;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FlatRepository extends JpaRepository<Flat, Long> {
    Flat findByNumber(String number);
}
