package com.cocoon.service;

import org.springframework.security.core.Authentication;

// todo I ile başlayan interface ifadeleri .net ailesine aittir
public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
