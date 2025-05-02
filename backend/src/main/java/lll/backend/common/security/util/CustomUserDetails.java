package lll.backend.common.security.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public record CustomUserDetails(
        Long userId
) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<String> roles = new ArrayList<>();
        roles.add("USER");

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return userId.toString();
    }

    @Override
    public String getPassword() {
        return "";
    }
}
