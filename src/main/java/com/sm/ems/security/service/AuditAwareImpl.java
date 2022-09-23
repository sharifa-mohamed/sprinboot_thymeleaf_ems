package com.sm.ems.security.service;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        //UserDetails userObj = (UserDetails) authentication.getPrincipal();
        return Optional.ofNullable(username).filter(s -> !s.isEmpty());

    }
}
