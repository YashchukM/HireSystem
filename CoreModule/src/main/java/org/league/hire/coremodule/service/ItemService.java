/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.league.hire.coremodule.service;

import entity.Item;

import java.util.List;
import javax.transaction.Transactional;
import manager.ItemManager;
import manager.RoleManager;
//import org.league.hire.coremodule.entity.Role;
//import org.league.hire.coremodule.entity.User;
//import org.league.hire.coremodule.repository.RoleRepository;
//import org.league.hire.coremodule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexander-ilkun
 */
@Service
@Transactional
public class ItemService {

//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;

    @Autowired
    private ItemManager itemManager;
    
    @Autowired
    private RoleManager roleManager;
    
    public List<Item> findAll() {
        return itemManager.query().findInitialized(0, 1000);
    }

    public Item findOne(int id) {
        return itemManager.getById(id);
    }

    public Item findByName(String name) {
        return itemManager.query().hasNameLike(name).findInitialized(0, 1).get(0);
    }
    
//    public void save(User user) {
//        user.setEnabled(true);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        user.setPassword(encoder.encode(user.getPassword()));
//
//        List<Role> roles = new ArrayList<Role>();
//        roles.add(roleManager.query().hasName("ROLE_USER").find(0, 1).get(0));
//        user.setRoles(roles);
//
//        userManager.save(user);
//    }

}
