/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.dao;

import com.supinfo.entity.User;
import javax.ejb.Local;

/**
 *
 * @author Oudomsieng
 */
@Local
public interface IUserDao {
    User getUserById(Long id);
    User authenticate(String username, String password);
    User addUser(User user);
    User updateUser(User user);
    Long countUsers();
}
