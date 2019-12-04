package com.es.phoneshop.security;

import javax.servlet.http.HttpServletRequest;

public interface DosProtectionService {
    boolean allowed(HttpServletRequest request);
}
