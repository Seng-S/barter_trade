/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.dao.impl;

import com.supinfo.dao.IUserDao;
import com.supinfo.entity.User;
import com.supinfo.entity.User_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Oudomsieng
 */

@Stateless
public class UserJPADao implements IUserDao {
    @PersistenceContext(unitName = "SupBartering-ejbPU")
    private EntityManager em;
    /**
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(Long id){
        return em.find(User.class, id);
    }
    /**
     *
     * @param user
     * @return
     */
    @Override
    public User addUser(User user) {
        em.persist(user);
        return user;
    }
    
    @Override
    public User updateUser(User user){
        em.merge(user);
        return user;
    }

    @Override
    public User authenticate(String username, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);
        
        Predicate userCond = cb.equal(user.get(User_.username), username);
        Predicate passCond = cb.equal(user.get(User_.password), password);
        
        query.where(cb.and(userCond, passCond));
        
        List<User> userConnected = em.createQuery(query).getResultList();
        if(userConnected.size() == 1){
            return userConnected.get(0);
        }
        return null;
    }

    @Override
    public Long countUsers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        
        cq.select(cb.count(cq.from(User.class)));
        
        return em.createQuery(cq).getSingleResult();
    }
}
