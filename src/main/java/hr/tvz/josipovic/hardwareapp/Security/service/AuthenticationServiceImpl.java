package hr.tvz.josipovic.hardwareapp.Security.service;

import hr.tvz.josipovic.hardwareapp.Security.command.LoginCommand;
import hr.tvz.josipovic.hardwareapp.Security.domain.User;
import hr.tvz.josipovic.hardwareapp.Security.dto.LoginDTO;
import hr.tvz.josipovic.hardwareapp.Security.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<LoginDTO> login(LoginCommand command) {
        Optional<User> user = userRepository.findByUsername(command.getUsername());

        if (user.isEmpty() || !isMatchingPassword(command.getPassword(), user.get().getPassword())) {
            return Optional.empty();
        }

        return Optional.of(
                new LoginDTO(jwtService.createJwt(user.get()))
        );
    }

    private boolean isMatchingPassword(String rawPassword, String encryptedPassword) {
        System.out.println("ssssssssssssssssssssssssssssssssssssssssssssss");
        if(BCrypt.checkpw(rawPassword, encryptedPassword)) {
            System.out.println("It matches");
            return true;
        }
        else{
            System.out.println("It does not match");
            return false;
        }
        //throw new UnsupportedOperationException();
    }
}
