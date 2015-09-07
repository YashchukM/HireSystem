/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.league.hire.coremodule.service;

import entity.Role;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import manager.RoleManager;
import manager.UserManager;
//import org.league.hire.coremodule.entity.Role;
//import org.league.hire.coremodule.entity.User;
//import org.league.hire.coremodule.repository.RoleRepository;
//import org.league.hire.coremodule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexander-ilkun
 */
@Service
@Transactional
public class UserService {

//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;

    @Autowired
    private UserManager userManager;
    
    @Autowired
    private RoleManager roleManager;
    
    public List<User> findAll() {
        return userManager.query().findInitialized(0, 1000);
    }

    public User findOne(int id) {
        return userManager.getById(id);
    }

    public void save(User user) {
        user.setEnabled(true);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        List<Role> roles = new ArrayList<Role>();
        roles.add(roleManager.query().hasName("ROLE_USER").find(0, 1).get(0));
        user.setRoles(roles);

        userManager.save(user);
    }

}
