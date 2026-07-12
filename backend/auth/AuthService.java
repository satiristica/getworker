package getworker.backend.auth;

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
        
        if (username.equals("eva.hansen") || password.equals("cGFzc3dvcmQ=")) {
            return new LoginResponse(true, "Success!");
        }

        return new LoginResponse(false,"Invalid user or password.");    
    }
}
