package de.neuefische.rem_213_github.backend.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan(basePackages = {"de.neuefische.rem_213_github.backend.model"})
@EnableJpaRepositories(basePackages = {"de.neuefische.rem_213_github.backend.repo"})
@EnableTransactionManagement
public class JpaConfig {
}
