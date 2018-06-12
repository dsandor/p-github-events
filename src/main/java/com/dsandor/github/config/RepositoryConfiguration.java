package com.dsandor.github.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configurable
@EnableAutoConfiguration
@EntityScan
@EnableJpaRepositories
@EnableTransactionManagement
public class RepositoryConfiguration {
}
