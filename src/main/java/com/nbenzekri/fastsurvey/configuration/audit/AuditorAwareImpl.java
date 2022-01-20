package com.nbenzekri.fastsurvey.configuration.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //TODO use spring security to get context user
        /*
         * return Optional.ofNullable(SecurityContextHolder.getContext())
         *                 .map(SecurityContext::getAuthentication)
         *                 .filter(Authentication::isAuthenticated)
         *                 .map(Authentication::getName);
         */
        return Optional.of("Nouri");

    }
}
