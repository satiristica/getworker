package getworker.backend.auth;

public class loginresponse {
    private boolean success;
    private String message;

    public loginresponse() {
    }

    public loginresponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}