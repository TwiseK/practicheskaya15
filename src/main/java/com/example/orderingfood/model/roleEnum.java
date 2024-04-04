package com.example.orderingfood.model;
import org.springframework.security.core.GrantedAuthority;

public enum roleEnum implements GrantedAuthority {
    MANAGER, ADMIN, USER, DELIVER;

    @Override
    public String getAuthority()
    {
        return name();
    }
}
