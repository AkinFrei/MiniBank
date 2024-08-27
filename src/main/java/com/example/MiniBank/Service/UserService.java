package com.example.MiniBank.Service;

import com.example.MiniBank.Dto.LoginDto;
import com.example.MiniBank.Dto.UserDto;
import com.example.MiniBank.Entity.User;
import com.example.MiniBank.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    ModelMapper modelMapper = new ModelMapper();

    public UserDto registerUser(UserDto payload) {
        User user = modelMapper.map(payload, User.class);
        user.setPassword(passwordEncoder.encode(payload.getPassword()));
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    public UserDto login(LoginDto payload) {
        return modelMapper.map(userRepository.findByEmailAndPassword(payload.getEmail(), payload.getPassword())
                .filter(u -> passwordEncoder.matches(payload.getPassword(), u.getPassword()))
                .orElseThrow(() -> new UsernameNotFoundException("Invalid email or password")), UserDto.class);
    }

}
