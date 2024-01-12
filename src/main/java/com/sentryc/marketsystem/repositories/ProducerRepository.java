package com.sentryc.marketsystem.repositories;

import com.sentryc.marketsystem.entities.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, UUID> {
    @Override
    Optional<Producer> findById(UUID uuid);
}
