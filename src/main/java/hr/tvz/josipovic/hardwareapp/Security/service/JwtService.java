package hr.tvz.josipovic.hardwareapp.Security.service;

import hr.tvz.josipovic.hardwareapp.Security.domain.User;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}
