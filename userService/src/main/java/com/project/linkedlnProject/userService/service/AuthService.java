package com.project.linkedlnProject.userService.service;

import com.project.linkedlnProject.userService.dto.LoginRequestDto;
import com.project.linkedlnProject.userService.dto.SignupRequestDto;
import com.project.linkedlnProject.userService.dto.UserDto;
import com.project.linkedlnProject.userService.entity.User;
import com.project.linkedlnProject.userService.exception.BadRequestException;
import com.project.linkedlnProject.userService.exception.ResourceNotFoundException;
import com.project.linkedlnProject.userService.repository.UserRepository;
import com.project.linkedlnProject.userService.utils.BCrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UserDto signUp(SignupRequestDto signupRequestDto) {
        log.info("signUp a user with email: {}", signupRequestDto.getEmail());

        boolean exists = userRepository.existsByEmail(signupRequestDto.getEmail());
        if (exists) {
            throw new BadRequestException("User with email already exists");
        }

        User user = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(BCrypt.hash(signupRequestDto.getPassword()));

        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);

    }

    public String login(LoginRequestDto loginRequestDto) {
        log.info("Login request for user with email: {}", loginRequestDto.getEmail());

        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() ->
                        new BadRequestException("Incorrect email or password"));

        boolean isPasswordMatch = BCrypt.match(loginRequestDto.getPassword(), user.getPassword());

        if(!isPasswordMatch) {
            throw new BadRequestException("Incorrect email or password");
        }

        return jwtService.generateAccessToken(user);
    }
}
