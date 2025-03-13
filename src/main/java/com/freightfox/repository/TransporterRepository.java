package com.freightfox.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freightfox.model.Transporter;

@Repository
public interface TransporterRepository extends JpaRepository<Transporter, Long> {

  Optional<Transporter> findByName(String name);
}
