package getworker.ldap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

@Component
public class LdapConnection {

    private final String url;
    private final Map<String, Object> settings = new HashMap<>();

    public LdapConnection(@Value("${ldap.url}") String url) {
        this.url = url;

        settings.put(Context.PROVIDER_URL, url);
        settings.put(
            Context.INITIAL_CONTEXT_FACTORY,
            "com.sun.jndi.ldap.LdapCtxFactory"
        );
        settings.put(Context.SECURITY_AUTHENTICATION, "simple");
    }
    
    public DirContext connect(String userDn, String password) throws NamingException {
        Map<String, Object> userSettings = createUserSettings(userDn, password);
        return new InitialDirContext(new Hashtable<>(userSettings));
    }

    private Map<String, Object> createUserSettings(String userDn, String password) {
        Map<String, Object> userSettings = new HashMap<>(settings);

        userSettings.put(Context.SECURITY_PRINCIPAL, userDn);
        userSettings.put(Context.SECURITY_CREDENTIALS, password);

        return userSettings;
    }
}