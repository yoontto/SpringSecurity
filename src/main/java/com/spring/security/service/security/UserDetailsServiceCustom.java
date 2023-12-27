package com.spring.security.service.security;

import com.spring.security.entity.User;
import com.spring.security.exception.BaseException;
import com.spring.security.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.stream.Collectors;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserDetailCustom userDetailCustom = getUserDetailCustom(id);

        if(ObjectUtils.isEmpty(userDetailCustom)) {
            throw new UsernameNotFoundException("User not found");
        }
        return userDetailCustom;
    }

    private UserDetailCustom getUserDetailCustom(String id) {
        User user = userRepository.findById(id);

        if(ObjectUtils.isEmpty(user)) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST), "User not found");
        }

        return new UserDetailCustom(
                user.getId(),
                user.getPwd(),
                user.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.getName()))
                        .collect(Collectors.toList()),
                true,
                true,
                true,
                true
        );
    }
}
