package getworker.auth;

import getworker.ldap.LdapAuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public LoginResponse login(LoginRequest request) {

        if (request == null) {
            return new LoginResponse(false, "Empty login");
        }

        String username = request.getUsername();
        String password = request.getPassword();

        if (username == null || username.isBlank()) {
            return new LoginResponse(false, "Username is required");
        }

        if (password == null || password.isBlank()) {
            return new LoginResponse(false, "Password is required");
        }

        boolean authenticated =
                LdapAuthService.authenticate(username, password);

        if (!authenticated) {
            return new LoginResponse(false, "Invalid credentials");
        }

        return new LoginResponse(true, "Success!");
    }
}