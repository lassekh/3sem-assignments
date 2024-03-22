package org.rest.task3.dao;

import io.javalin.validation.ValidationException;
import org.rest.task3.entities.Role;
import org.rest.task3.entities.User;

public interface ISecurityDAO {
    User getVerifiedUser(String username, String password) throws ValidationException;
    User createUser(String username, String password);
    Role createRole(String role);
    User addUserRole(String username, String role);
}
