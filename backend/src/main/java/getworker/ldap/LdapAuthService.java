package getworker.ldap; 

import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

@Service 
public class LdapAuthService {
    private final LdapConnection ldapConnect;

    public LdapAuthService(LdapConnection ldapConnect) {
        this.ldapConnect = ldapConnect;
    }   
    
    public boolean authenticate(String username, String password) {
        String userDn = "uid=" + username + ",ou=Employees,dc=admin,dc=local"; 

        try {
            DirContext connection = ldapConnect.connect(userDn, password);
            
            connection.close();
            return true;
        }
        catch (NamingException error) {
            return false;
        }
    }
}