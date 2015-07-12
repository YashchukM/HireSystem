/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.league.hire.coremodule.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.league.hire.coremodule.entity.Role;
import org.league.hire.coremodule.entity.User;
import org.league.hire.coremodule.repository.RoleRepository;
import org.league.hire.coremodule.repository.UserRepository;
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
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleRepository.save(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleRepository.save(roleAdmin);

        User userAdmin = new User();
        userAdmin.setEnabled(true);
        userAdmin.setName("admin");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userAdmin.setPassword(encoder.encode("admin"));
        List<Role> roles = new ArrayList<Role>();
        roles.add(roleAdmin);
        roles.add(roleUser);
        userAdmin.setRoles(roles);
        userRepository.save(userAdmin);
    }

}
