package org.rest.task3.dao;

import io.javalin.validation.ValidationException;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import org.rest.task3.entities.Role;
import org.rest.task3.entities.User;

public class UserDAO implements ISecurityDAO{
    private static EntityManagerFactory emf;
    private static UserDAO instance;
    public static UserDAO getInstance(EntityManagerFactory _emf)
    {
        if(instance == null)
        {
            emf = _emf;
            instance = new UserDAO();
        }
        return instance;
    }
    public User getVerifiedUser(String username, String password) throws ValidationException
    {
        User user = new User(username, password);
        try(var em = emf.createEntityManager())
        {
            User result = em.find(User.class, user);
            if(result == null) throw new EntityNotFoundException("No user found with credentials");
            if(!result.verifyPassword(password)) throw new EntityNotFoundException("Wrong password");
            return result;
        }
    }
    public User createUser(String username, String password)
    {
        try(var em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            User user = new User(username, password);
            Role userRole = em.find(Role.class, "user");
            if(userRole == null)
            {
                userRole = new Role("user");
                em.persist(userRole);
            }
            user.addRole(userRole);
            em.persist(user);
            em.getTransaction().commit();
            return user;
        }
    }
    public Role createRole(String role)
    {
        try(var em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            Role newRole = new Role(role);
            em.persist(newRole);
            em.getTransaction().commit();
            return newRole;
        }
    }
    public User addUserRole(String username, String role)
    {
        return null;
    }
}
