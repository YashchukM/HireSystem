/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.league.hire.coremodule.controller;

//import org.league.hire.coremodule.entity.User;
import entity.User;
import entity.UserReview;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import org.league.hire.coremodule.service.ReviewAndRatingService;
import org.league.hire.coremodule.service.UserService;
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
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewAndRatingService reviewAndRatingService;
    
    @ModelAttribute("user")
    public User construct() {
        return new User();
    }

    @ModelAttribute("userReview")
    public UserReview constructUserReview() {
        return new UserReview();
    }
    
    @RequestMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @RequestMapping("/users/{id}")
    public String detail(Model model, @PathVariable int id) {
        User user = userService.findOne(id);
        List<UserReview> userReviews = reviewAndRatingService.findAllByUser(user);
        List<Integer> userReviewsRatings = reviewAndRatingService.findRatingsByUserReviews(userReviews);
        int userRating = reviewAndRatingService.findUserRating(user);
        model.addAttribute("user", user);
        model.addAttribute("userReviews", userReviews);
        model.addAttribute("userReviewsRatings", userReviewsRatings);
        model.addAttribute("userRating", userRating);
        return "user-detail";
    }

    @RequestMapping("/register")
    public String showRegister() {
        return "user-register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/register.html?success=true";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public String doUserReview(@ModelAttribute("userReview") UserReview userReview, @PathVariable int id, Principal principal) {
        userReview.getReview().setAuthor(userService.findByLogin(principal.getName()));
        userReview.getReview().setDate(new Date());
        userReview.setUser(userService.findOne(id));
        reviewAndRatingService.saveUserReview(userReview);
        return "redirect:/users/" + id + ".html?success=true";
    }

    @RequestMapping(value = "/users/{id}/voteReview/{reviewId}")
    public String doUserReviewRating(@PathVariable int id, @PathVariable int reviewId, Principal principal) {
        reviewAndRatingService.voteUserReview(reviewId, userService.findByLogin(principal.getName()));
        return "redirect:/users/" + id + ".html?success=true";
    }

    @RequestMapping(value = "/users/{id}/voteUser")
    public String doUserRating(@PathVariable int id, Principal principal) {
        reviewAndRatingService.voteUser(userService.findOne(id), userService.findByLogin(principal.getName()));
        return "redirect:/users/" + id + ".html?success=true";
    }
}
