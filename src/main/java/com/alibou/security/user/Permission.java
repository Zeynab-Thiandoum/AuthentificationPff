package com.alibou.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    admin_READ("admin:read"),
    admin_UPDATE("admin:update"),
    admin_CREATE("admin:create"),
    admin_DELETE("admin:delete"),
    client_READ("client:read"),
    client_UPDATE("client:update"),
    client_CREATE("client:create"),
    client_DELETE("client:delete"),

    paysan_READ("paysan:read"),
    paysan_UPDATE("paysan:update"),
    paysan_CREATE("paysan:create"),
    paysan_DELETE("paysan:delete")
    ;

    @Getter
    private final String permission;
}
