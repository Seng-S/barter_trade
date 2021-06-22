/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.beans;

import com.supinfo.entity.User;
import javax.ejb.Remote;

/**
 *
 * @author Oudomsieng
 */

@Remote
public interface UserService {
    User getUserById(Long id);
    User authenticate(String username, String password);
    User addUser(User user);
    void updateUser(User user);
    Long countUsers();
}
