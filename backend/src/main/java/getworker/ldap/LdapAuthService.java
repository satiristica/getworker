package getworker.ldap;

import java.io.IOException;

public class LdapAuthService {

    public static boolean authenticate(String username, String password) {
        String userDn = "uid=" + username + ",ou=Employees,dc=admin,dc=local";

        ProcessBuilder processBuilder = new ProcessBuilder(
                "docker",
                "exec",
                "directoryLdap",
                "ldapwhoami",
                "-x",
                "-H",
                "ldap://localhost:389",
                "-D",
                userDn,
                "-w",
                password
        );

        try {
            Process process = processBuilder.start();

            int exitCode = process.waitFor();

            return exitCode == 0;

        } catch (IOException | InterruptedException error) {
            return false;
        }
    }
}