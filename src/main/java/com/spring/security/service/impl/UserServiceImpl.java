package com.spring.security.service.impl;

import com.spring.security.request.UserDTO;
import com.spring.security.entity.Role;
import com.spring.security.entity.User;
import com.spring.security.exception.BaseException;
import com.spring.security.repository.RoleRepository;
import com.spring.security.repository.UserRepository;
import com.spring.security.response.BaseResponse;
import com.spring.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public BaseResponse registerAccount(@RequestBody UserDTO userDTO) {
        BaseResponse response = new BaseResponse();

        //validate data from client
        validateAccount(userDTO);

        User user = insertUser(userDTO);

        try {
            userRepository.save(user);
            response.setCode(String.valueOf(HttpStatus.CREATED.value()));
            response.setMessage("Register account successfully!!");
        }catch (Exception e) {
            response.setCode(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
            response.setMessage("Service Unavailable!");
        }

        return response;
    }


    private User insertUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setPwd(passwordEncoder.encode(userDTO.getPwd()));
        user.setEmail(userDTO.getEmail());
        user.setNickname(userDTO.getNickname());
        user.setPhone(userDTO.getPhone());

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(userDTO.getRole()));
        user.setRoles(new HashSet<>());

        return user;
    }

    private void validateAccount(UserDTO userDTO) {
        if(ObjectUtils.isEmpty(userDTO)) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Request data not found!");
        }

        try {
            if(!ObjectUtils.isEmpty(userDTO.checkProperties())) {
                throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Request data not found!");
            }
        }catch (IllegalAccessException e) {
            throw new BaseException(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()), "Service Unavailable!");
        }


        List<String> roles = roleRepository.findAll().stream().map(Role::getName).toList();

        if(roles.contains(userDTO.getRole())) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Invalid Role!");
        }

        User user = userRepository.findById(userDTO.getId());

        if(!ObjectUtils.isEmpty(user)) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "User had existed!");
        }
    }
}
