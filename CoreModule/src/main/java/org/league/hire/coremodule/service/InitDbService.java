/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.league.hire.coremodule.service;

import entity.Item;
import entity.ItemDetails;
import entity.Review;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import entity.Role;
import entity.User;
import entity.UserReview;
import manager.ItemDetailsManager;
import manager.ItemManager;
import manager.RoleManager;
import manager.UserManager;
import manager.UserReviewManager;
//import org.league.hire.coremodule.repository.RoleRepository;
//import org.league.hire.coremodule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexander-ilkun
 */
@Transactional
@Service
public class InitDbService {
    @Autowired
    private RoleManager roleManager;
    
    @Autowired
    private UserManager userManager;
    
    @Autowired
    private UserReviewManager userReviewManager;
    
    @Autowired
    private ItemDetailsManager itemDetailsManager;
    
    @Autowired
    private ItemManager itemManager;
    
    @PostConstruct
    public void init() {
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleManager.save(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleManager.save(roleAdmin);

        User userAdmin = new User();
        userAdmin.setEnabled(true);
        userAdmin.setLogin("admin");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userAdmin.setPassword(encoder.encode("admin"));
        List<Role> adminRoles = new ArrayList<Role>();
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);
        userAdmin.setRoles(adminRoles);
        userManager.save(userAdmin);
    
        User user = new User();
        user.setEnabled(true);
        user.setLogin("user");
        user.setPassword(encoder.encode("user"));
        List<Role> userRoles = new ArrayList<Role>();
        userRoles.add(roleUser);
        user.setRoles(userRoles);
        userManager.save(user);
    
        Review adminReview = new Review();
        adminReview.setAuthor(userAdmin);
        adminReview.setText("He is a good guy.");
        adminReview.setDate(new Date());
        userReviewManager.save(new UserReview(adminReview, user));
    
//        ItemDetails adminItemDetails = new ItemDetails();
//        Item adminItem = new Item();
//        adminItem.setName("adminItem");
//        adminItemDetails.setItem(adminItem);
//        adminItemDetails.setOwner(userAdmin);
//        itemDetailsManager.save(adminItemDetails);
    }

}
