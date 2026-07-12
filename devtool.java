package getworker;
import getworker.backend.auth.*;

public class devtool {

    public static void main(String[] args) {

        LoginRequest request = new LoginRequest("eva.hansen", "cGFzc3dvcmQ=");
        AuthService authService = new AuthService();
        LoginResponse response = authService.login(request);
        System.out.println(response.getMessage());
    }
}