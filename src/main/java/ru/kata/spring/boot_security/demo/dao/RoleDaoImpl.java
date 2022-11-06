package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("FROM Role ", Role.class).getResultList();
    }

    @Override
    public Role getByName(String name) {
        Query query = entityManager.createQuery("from Role where name=:name")
                .setParameter("name", name);
        try {
            return (Role) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}

