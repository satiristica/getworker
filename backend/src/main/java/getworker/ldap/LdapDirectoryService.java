package getworker.ldap;

import getworker.directory.DirectoryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.List;

@Service
public class LdapDirectoryService {

    private final LdapConnection ldapConnect;
    private final String adminDn;
    private final String adminPassword;

    public LdapDirectoryService(
            LdapConnection ldapConnect,
            @Value("${ldap.admin-dn}") String adminDn,
            @Value("${ldap.admin-password}") String adminPassword
    ) {
        this.ldapConnect = ldapConnect;
        this.adminDn = adminDn;
        this.adminPassword = adminPassword;
    }

    public List<DirectoryResponse> getEmployees() {
        List<DirectoryResponse> employees = new ArrayList<>();

        try {
            DirContext connection =
                    ldapConnect.connect(adminDn, adminPassword);

            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(
                    SearchControls.SUBTREE_SCOPE
            );

            NamingEnumeration<SearchResult> results =
                    connection.search(
                            "ou=Employees,dc=admin,dc=local",
                            "(objectClass=inetOrgPerson)",
                            searchControls
                    );

            while (results.hasMore()) {
                SearchResult result = results.next();
                Attributes attributes = result.getAttributes();

                DirectoryResponse employee =
                        new DirectoryResponse();

                employee.setName(
                        attributes.get("givenName").get().toString()
                );

                employee.setSurname(
                        attributes.get("sn").get().toString()
                );

                employee.setPhoneNumber(
                        attributes.get("telephoneNumber").get().toString()
                );

                employee.setEmail(
                        attributes.get("mail").get().toString()
                );

                employees.add(employee);
            }

            results.close();
            connection.close();

            return employees;

        } catch (NamingException error) {
            throw new IllegalStateException(
                    "Error reading LDAP employees",
                    error
            );
        }
    }
}