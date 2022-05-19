package hr.tvz.josipovic.hardwareapp.Security.dto;

public class LoginDTO {
    private final String jwt;

    public LoginDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "jwt='" + jwt + '\'' +
                '}';
    }
}
