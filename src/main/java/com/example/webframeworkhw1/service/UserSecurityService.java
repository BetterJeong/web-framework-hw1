package com.example.webframeworkhw1.service;

import com.example.webframeworkhw1.model.entity.Authority;
import com.example.webframeworkhw1.model.entity.UserEntity;
import com.example.webframeworkhw1.repository.AuthorityRepository;
import com.example.webframeworkhw1.repository.UserRepository;
import com.example.webframeworkhw1.util.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepository.findByEmail(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        UserEntity userEntity = userOptional.get();
        Authority authority = Authority.builder()
                .username(userEntity.getUsername())
                .authority(UserRole.USER.getValue())
                .build();

        Optional<Authority> optionalAuthority = authorityRepository.findByUsername(userEntity.getUsername());
        if (optionalAuthority.isPresent()) {
            authority = optionalAuthority.get();
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}
