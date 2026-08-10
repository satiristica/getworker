package getworker.directory;

import org.springframework.stereotype.Service;

import java.util.List;

import getworker.ldap.LdapDirectoryService;


@Service 
public class DirectoryService {
    public List<DirectoryResponse> getEmployees() {
        List<DirectoryResponse> employees = LdapDirectoryService.getEmployees();
        return employees;
    }
}