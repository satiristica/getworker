package getworker.backend.auth;
import getworker.backend.ldap.ldapAuthService;

public class AuthService {
    
    public LoginResponse login(LoginRequest request) {
    
        if (request == null) {
            return new LoginResponse(false, "Empty error: empty login");
        }
        
        String username = request.getUsername(); 
        String password = request.getPassword(); 

        if (username == null || username.isBlank()) {
            return new LoginResponse(false, "User error: username is required");
        }
        
        if (password == null || password.isBlank()) {
            return new LoginResponse(false, "Password error: password is required");
        }
        
        boolean authenticated = ldapAuthService.authenticate(username, password);
        if (authenticated) {
            return new LoginResponse(true, "Success!");
        }
        else {
            return new LoginResponse(false, "Invalid credentials");
        }    
    }
}
