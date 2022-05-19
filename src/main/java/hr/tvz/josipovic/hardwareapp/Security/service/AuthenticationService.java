package hr.tvz.josipovic.hardwareapp.Security.service;

import hr.tvz.josipovic.hardwareapp.Security.command.LoginCommand;
import hr.tvz.josipovic.hardwareapp.Security.dto.LoginDTO;


import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}
