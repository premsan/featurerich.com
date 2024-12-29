package com.featurerich.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@Profile("!local")
@EnableWebSecurity
@EnableMethodSecurity
public class EnableSecurityConfiguration {}