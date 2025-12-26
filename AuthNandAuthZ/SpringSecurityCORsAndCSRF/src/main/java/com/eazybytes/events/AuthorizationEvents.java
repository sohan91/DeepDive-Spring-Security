package com.eazybytes.events;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.security.authorization.event.AuthorizationEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorizationEvents {

    @EventListener
    public void onSuccess(AuthorizationDeniedEvent authenticationEvent)
    {
      log.error("Authorization failure {} due to {} ",authenticationEvent.getAuthentication().get().getName(),authenticationEvent.getAuthorizationDecision().toString());
    }
}
