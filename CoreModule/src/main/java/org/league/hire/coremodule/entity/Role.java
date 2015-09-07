///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package org.league.hire.coremodule.entity;
//
//import java.util.List;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
///**
// *
// * @author alexander-ilkun
// */
//@Entity
//@Table(name = "role")
//public class Role {
//
//    @Id
//    @GeneratedValue
//    private int id;
//
//    private String name;
//
//    @ManyToMany(mappedBy = "roles")
//    private List<User> users;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
//
//}
