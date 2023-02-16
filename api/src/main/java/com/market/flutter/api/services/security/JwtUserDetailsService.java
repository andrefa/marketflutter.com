package com.market.flutter.api.services.security;

import java.util.List;

import com.market.flutter.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.market.flutter.api.models.domain.User user = userRepository.findByEmail(username);
        if (user != null) {
            return new User(user.getEmail(), user.getPassword(), List.of());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
