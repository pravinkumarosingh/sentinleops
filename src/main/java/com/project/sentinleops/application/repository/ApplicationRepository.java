package com.project.sentinleops.application.repository;

import com.project.sentinleops.application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicationRepository  extends JpaRepository<Application, UUID>{
    boolean existsByNameAndEnvironment(
            String name,
            String environment
    );
}
