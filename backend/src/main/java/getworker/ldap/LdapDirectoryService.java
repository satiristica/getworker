package getworker.ldap;

import getworker.directory.DirectoryResponse;
import java.util.List;
import java.util.ArrayList;

public class LdapDirectoryService {

    public static List<DirectoryResponse> getEmployees() {

        List<DirectoryResponse> employees = new ArrayList<>();

        DirectoryResponse john = new DirectoryResponse();

        john.setName("test");
        john.setSurname("test");
        john.setPhoneNumber("test");
        john.setEmail("test.test@test.local");

        employees.add(john);

        return employees;
    }
}