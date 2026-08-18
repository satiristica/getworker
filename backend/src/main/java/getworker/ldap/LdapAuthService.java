package getworker.ldap;

import java.io.IOException;

public class LdapAuthService {

    public static boolean authenticate(String username, String password) {
        String userDn = "uid=" + username + ",ou=Employees,dc=admin,dc=local";
