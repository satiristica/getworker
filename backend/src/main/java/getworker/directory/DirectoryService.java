package getworker.directory;

import org.springframework.stereotype.Service;

import java.util.List;

import getworker.ldap.LdapDirectoryService;


@Service
public class DirectoryService {

    private final LdapDirectoryService ldapDirectoryService;

    public DirectoryService(LdapDirectoryService ldapDirectoryService) {
        this.ldapDirectoryService = ldapDirectoryService;
    }

    public List<DirectoryResponse> getEmployees(String search) {
        return ldapDirectoryService.getEmployees(search);
    }
    
}
