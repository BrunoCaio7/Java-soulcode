package com.soulcode.servicos.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class AuthUserDetail implements UserDetails {
    // criar atributos

    private String login;
    private String password;

    // criar construtor
    public AuthUserDetail(String login, String password){
        this.login = login;
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() { // a conta não expirou
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // a conta não bloqueou
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // as credencias não expiraram
        return true;
    }

    @Override
    public boolean isEnabled() { // o usuário está habilitado
        return true;
    }
}

/*
 * O Spring Security não se comunica diretamente com o nosso model User =(
 * Então devemos criar uma classe que ele conheça para fazer essa comunicação,
 * UserDetails = Guarda informações do contexto de autenticação do usuário (autorizações, habilitado, etc)
 * */
