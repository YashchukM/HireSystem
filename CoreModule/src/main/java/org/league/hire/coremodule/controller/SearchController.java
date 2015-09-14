package org.league.hire.coremodule.controller;

import entity.Item;
import entity.ItemReview;
import org.league.hire.coremodule.service.ItemService;
import org.league.hire.coremodule.service.ReviewAndRatingService;
import org.league.hire.coremodule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import org.league.hire.search.service.SolrService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 *
 * @author alexander-ilkun
 */
@Controller
public class SearchController {

    @Autowired
    private SolrService solrService;
    
    @RequestMapping(value = "/search", method = RequestMethod.GET, params={"search"})
    public String addItemRequest(@RequestParam(value = "search") String search, Model model, Principal principal) {
        model.addAttribute("items", solrService.findByOwner(search, 0, 10));
        System.out.println("ebat'");
        return "items";
    }
}
