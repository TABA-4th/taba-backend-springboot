package com.taba.nimonaemo.global.auth.jwt;

import com.taba.nimonaemo.global.auth.role.MemberRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
public class JwtAuthentication implements AppAuthentication {

    private Long userId;
    private MemberRole memberRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String authority : memberRole.getName().split(",")) {
            authorities.add(() -> authority);
        }
        return authorities;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public MemberRole getUserRole() {
        return memberRole;
    }

    @Override
    public boolean isAdmin() {
        return memberRole.isAdmin();
    }

    @Override
    public Object getCredentials() {
        return userId;
    }

    @Override
    public Object getDetails() {
        return userId;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
