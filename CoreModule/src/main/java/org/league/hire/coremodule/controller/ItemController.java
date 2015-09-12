/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.league.hire.coremodule.controller;

//import org.league.hire.coremodule.entity.User;
import entity.Item;
import entity.ItemReview;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.league.hire.coremodule.service.ItemService;
import org.league.hire.coremodule.service.ReviewAndRatingService;
import org.league.hire.coremodule.service.UserService;
import search.dao.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author alexander-ilkun
 */
@Controller
public class ItemController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private SolrService solrService;
    
    @Autowired
    private ReviewAndRatingService reviewAndRatingService;
    
    @ModelAttribute("itemReview")
    public ItemReview constructItemReview() {
        return new ItemReview();
    }
    
    @RequestMapping("/items")
    public String items(Model model) {
        model.addAttribute("items", itemService.findAll());
        model.addAttribute("solr", solrService.readAll(0, 3));
        return "items";
    }

    @RequestMapping("/items/{id}")
    public String detail(Model model, @PathVariable int id) {
        Item item = itemService.findOne(id);
        List<ItemReview> itemReviews = reviewAndRatingService.findAllByItem(item);
        List<Integer> itemReviewsRatings = reviewAndRatingService.findRatingsByItemReviews(itemReviews);
        int itemRating = reviewAndRatingService.findItemRating(item);
        model.addAttribute("item", item);
        model.addAttribute("itemReviews", itemReviews);
        model.addAttribute("itemReviewsRatings", itemReviewsRatings);
        model.addAttribute("itemRating", itemRating);
        return "item-detail";
    }

//    @RequestMapping("/register")
//    public String showRegister() {
//        return "user-register";
//    }
//
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public String doRegister(@ModelAttribute("user") User user) {
//        itemService.save(user);
//        return "redirect:/register.html?success=true";
//    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.POST)
    public String doItemReview(@ModelAttribute("itemReview") ItemReview itemReview, @PathVariable int id, Principal principal) {
        itemReview.getReview().setAuthor(userService.findByLogin(principal.getName()));
        itemReview.getReview().setDate(new Date());
        itemReview.setItem(itemService.findOne(id));
        reviewAndRatingService.saveItemReview(itemReview);
        return "redirect:/items/" + id + ".html?success=true";
    }

    @RequestMapping(value = "/items/{id}/voteReview/{reviewId}")
    public String doItemReviewRating(@PathVariable int id, @PathVariable int reviewId, Principal principal) {
        reviewAndRatingService.voteItemReview(reviewId, userService.findByLogin(principal.getName()));
        return "redirect:/items/" + id + ".html?success=true";
    }

    @RequestMapping(value = "/items/{id}/voteItem")
    public String doItemRating(@PathVariable int id, Principal principal) {
        reviewAndRatingService.voteItem(itemService.findOne(id), userService.findByLogin(principal.getName()));
        return "redirect:/items/" + id + ".html?success=true";
    }
}
