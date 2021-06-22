/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.beans;

import com.supinfo.dao.IUserDao;
import com.supinfo.entity.User;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Oudomsieng
 */

@Stateful
public class UserServiceBean implements UserService {

    @EJB
    private IUserDao userDao;
    
    @Override
    public User getUserById(Long id){
        return userDao.getUserById(id);
    }
    
    /**
     *
     * @param user
     */
    @Override
    public User addUser(User user){
        return userDao.addUser(user);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public void updateUser(User user){
        userDao.updateUser(user);
    }

    @Override
    public User authenticate(String username, String password) {
        return userDao.authenticate(username, password);
    }

    @Override
    public Long countUsers() {
        return userDao.countUsers();
    }
}
