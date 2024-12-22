package com.featurerich.scheduled;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@Configuration
@EnableScheduling
@EnableJdbcRepositories("com.featurerich.scheduled")
public class ScheduledConfiguration {}
