package pl.pawellewarski.Kwiaty.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {

    @Getter
    private final String email;


    public CustomUserDetails(String userName, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String email) {
        super(userName, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.email = email;
    }

    @Override
    public String toString() {
        return "CustomUserDetails{" +
                "email='" + email + '\'' +
                "} " + super.toString();
    }
}
