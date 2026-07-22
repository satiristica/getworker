package getworker.backend.ldap;

public class ldapAuthService {
    public static boolean authenticate(String username, String password) {
        String userDn = "uid=" + username + ",ou=people,dc=admin,dc=local";
        String command = "docker exec directoryLdap " + "ldapwhoami " + "-x " + "-H ldap://localhost:389 " + 
        "-D \"" + userDn + "\" " + "-w \"" + password + "\"";
        System.out.println(command);
        return true;
    }
}