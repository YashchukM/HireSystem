package org.league.hire.coremodule.controller;

import entity.*;
import manager.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.league.hire.search.service.SolrService;

@Controller
public class AjaxJSONController {

    @Autowired
    UserManager userManager;
    
    @Autowired
    UserDetailsManager userDetManager;
    
    @Autowired
    AddressManager addressManager;
    
    @Autowired
    ItemManager itemManager;
    
    @Autowired
    ItemDetailsManager itemDetManager;
    
    @Autowired
    CategoryManager catgManager;

    @Autowired
    SolrService solrService;

//	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
//	public String home(Model model) {
//		return "home";
//	}
    @PostConstruct
    public void init() {
        System.out.println("AJAXJSON");
    }
    
    @RequestMapping(value = "account", method = RequestMethod.GET)
    public String account(Model model) {
        User user = userManager.query().hasLogin("jack.sparrow").findInitialized(0, 1).get(0);
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(user.getUserDetails().getBirthDate());
        StringBuilder birthDate = new StringBuilder();
        birthDate.append(calendar.get(Calendar.YEAR) + "-");
        if (calendar.get(Calendar.MONTH) < 10) {
            birthDate.append("0" + calendar.get(Calendar.MONTH) + "-");
        } else {
            birthDate.append(calendar.get(Calendar.MONTH) + "-");
        }
        if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
            birthDate.append("0" + calendar.get(Calendar.DAY_OF_MONTH));
        } else {
            birthDate.append(calendar.get(Calendar.DAY_OF_MONTH));
        }
        
        model.addAttribute("user", user);
        model.addAttribute("birthDate", birthDate);
        return "account";
    }

//	@RequestMapping(value = "/home/{name}", method = RequestMethod.GET)
//	public String home2(Model model, @PathVariable Map<String, String> pathVars) {
//		String name = pathVars.get("name");
//		return "home";
//	}
    @RequestMapping("/getLogginedUserImage")
    public void getLogginedUserImage(HttpServletResponse response, Principal principal, Model model) {
        response.setContentType("image/jpeg");
        try {
            User user = (User) userManager.query().hasLogin(principal.getName());
            if (user != null) {
                byte[] image = user.getUserDetails().getImage();
                response.getOutputStream().write(image);
                response.getOutputStream().flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("/getUserImage/{userId}")
    public void getUsersItemImage(HttpServletResponse response, @PathVariable("userId") final String userId) {
        response.setContentType("image/jpeg");
        try {
            User user = userManager.getById(userId);
            
            if (user != null) {
                byte[] image = user.getUserDetails().getImage();
                response.getOutputStream().write(image);
                response.getOutputStream().flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("/getUsersItemImage/{userId}/{itemId}")
    public void getUsersItemImage(HttpServletResponse response, @PathVariable("userId") final String userId,
            @PathVariable("itemId") final String itemId) {
        response.setContentType("image/jpeg");
        try {
            User user = userManager.getById(userId);
            Item item = itemManager.getById(itemId);
            
            if (user != null && item != null && item.getItemDetails().getOwner().getId() == Integer.parseInt(userId)) {
                byte[] image = item.getMainImage();
                response.getOutputStream().write(image);
                response.getOutputStream().flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("/uploadUserImage")
    public @ResponseBody
    String uploadUserImage(MultipartHttpServletRequest request, Principal principal) {
        String result = "";
        
        try {
			// User user = (User)
            // userManager.query().hasLogin(principal.getName());
            User user = userManager.query().hasLogin("jack.sparrow").findInitialized(0, 1).get(0);
            
            if (user != null) {
                UserDetails userDet = user.getUserDetails();
                Iterator<String> itr = request.getFileNames();
                MultipartFile file = request.getFile(itr.next());
                try {
                    userDet.setImage(file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @RequestMapping("/uploadItemImage")
    public @ResponseBody
    String uploadItemImage(MultipartHttpServletRequest request, Principal principal) {
        String result = "";
        
        try {
			// User user = (User)
            // userManager.query().hasLogin(principal.getName());
            User user = userManager.query().hasLogin("jack.sparrow").findInitialized(0, 1).get(0);
            Item item = itemManager.getById(request.getParameter("itemId"));
            ItemDetails itemDet = null;
            
            if (user != null && item != null) {
                itemDet = item.getItemDetails();
                if (itemDet.getOwner().getLogin().equals(principal.getName())) {
                    Iterator<String> itr = request.getFileNames();
                    MultipartFile file = request.getFile(itr.next());
                    try {
                        item.setMainImage(file.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @RequestMapping(value = "/changeUsersInfoRequest", method = RequestMethod.POST)
    public @ResponseBody
    String changeUsersInfoRequest(HttpServletRequest request, Principal principal) {
        String result = "";

        // User user = (User) userManager.query().hasLogin(principal.getName());
        User user = userManager.query().hasLogin("jack.sparrow").findInitialized(0, 1).get(0);
        UserDetails userDet = null;
        Address address = null;
        
        if (user != null) {
            userDet = user.getUserDetails();
            
            userDet.setName(request.getParameter("name"));
            userDet.setSurname(request.getParameter("surname"));
            
            String dates = request.getParameter("bdate");
            String[] datess = dates.split("-", 3);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(datess[0]), Integer.parseInt(datess[1]), Integer.parseInt(datess[2]));
            Date birthDate = calendar.getTime();
            userDet.setBirthDate(birthDate);
            
            userDet.setEmail(request.getParameter("email"));
            userDet.setPhone(request.getParameter("phone"));

            /*			address = userDet.getAddress();
             address.setCountry(request.getParameter("country"));
             address.setRegion(request.getParameter("region"));
             address.setCity(request.getParameter("city"));
             address.setStreet(request.getParameter("street"));
             address.setBuilding(request.getParameter("building"));
             address.setApartment(request.getParameter("apartment"));*/
            try {
                //addressManager.update(address);
                userDetManager.update(userDet);
                userManager.update(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            
        }
        
        return result;
    }
    
    @RequestMapping(value = "/addItemRequest", headers = "content-type=multipart/*", method = RequestMethod.POST)
    public String addItemRequest(MultipartHttpServletRequest request, Principal principal) {
        String result = "";
        
        User user = userManager.query().hasLogin(principal.getName()).findInitialized(0, 1).get(0);
        
        if (user != null) {
            Item item = new Item();
            ItemDetails itemDet = new ItemDetails();
            itemDet.setItem(item);
            
            item.setName(request.getParameter("name"));
            item.setPrice(Long.parseLong(request.getParameter("price")));
            
            String category = request.getParameter("category");
            List<Category> catg = catgManager.query().hasNameLike(category).findInitialized(0, 1);
            if (catg.size() != 0) {
                item.setCategory(catg.get(0));
            }
            
            itemDet.setDescription(request.getParameter("description"));
            itemDet.setMinHireTime(Float.parseFloat(request.getParameter("minHireTime")));
            itemDet.setMaxHireTime(Float.parseFloat(request.getParameter("maxHireTime")));
            itemDet.setOwner(user);
            
            Iterator<String> itr = request.getFileNames();
            MultipartFile file = request.getFile(itr.next());
            
            try {
                item.setMainImage(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try {
                itemDetManager.save(itemDet);
                solrService.put(itemDet.getItem().getId(), item.getName(), principal.getName(), "category");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            
        }
        return result;
    }
    
    @RequestMapping(value = "/changeItemsInfoRequest", method = RequestMethod.POST)
    public @ResponseBody
    String changeItemsInfoRequest(HttpServletRequest request, Principal principal) {
        String result = "";
        
        User user = userManager.query().hasLogin("jack.sparrow").findInitialized(0, 1).get(0);
        Item item = itemManager.getById(request.getParameter("itemId"));
        ItemDetails itemDet = null;
        
        if (user != null && item != null) {
            itemDet = item.getItemDetails();
            if (itemDet.getOwner().getLogin().equals(principal.getName())) {
                item.setName(request.getParameter("name"));
                item.setPrice(Long.parseLong(request.getParameter("price")));
                
                String category = request.getParameter("category");
                Category catg = catgManager.query().hasNameLike(category).findInitialized(0, 1).get(0);
                if (catg != null) {
                    item.setCategory(catg);
                }
                
                itemDet = item.getItemDetails();
                itemDet.setDescription(request.getParameter("description"));
                itemDet.setMinHireTime(Float.parseFloat(request.getParameter("minHireTime")));
                itemDet.setMaxHireTime(Float.parseFloat(request.getParameter("maxHireTime")));
                
                try {
                    itemManager.update(item);
                    itemDetManager.update(itemDet);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            
        }
        return result;
    }
    
    @RequestMapping(value = "/deleteItemRequest", method = RequestMethod.POST)
    public @ResponseBody
    String deleteItemRequest(HttpServletRequest request, Principal principal) {
        String result = "";
        
        User user = userManager.query().hasLogin("jack.sparrow").findInitialized(0, 1).get(0);
        Item item = itemManager.getById(request.getParameter("itemId"));
        
        if (user != null && item != null) {
            if (item.getItemDetails().getOwner().getLogin().equals(principal.getName())) {
                try {
                    itemManager.delete(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            
        }
        return result;
    }
    
    @RequestMapping(value = "/confirmItemReturningRequest", method = RequestMethod.POST)
    public @ResponseBody
    String confirmItemReturningRequest(HttpServletRequest request, Principal principal) {
        String result = "";
        
        User user = userManager.query().hasLogin("jack.sparrow").findInitialized(0, 1).get(0);
        Item item = itemManager.getById(request.getParameter("itemId"));
        
        if (user != null && item != null) {
            if (item.getItemDetails().getOwner().getLogin().equals(principal.getName())) {
                item.setHired(false);
                try {
                    itemManager.update(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            
        }
        return result;
    }
    
    @RequestMapping(value = "/confirmItemLendingRequest", method = RequestMethod.POST)
    public @ResponseBody
    String confirmItemLendingRequest(HttpServletRequest request, Principal principal) {
        String result = "";
        
        User user = userManager.query().hasLogin("jack.sparrow").findInitialized(0, 1).get(0);
        Item item = itemManager.getById(request.getParameter("itemId"));
        
        if (user != null && item != null) {
            if (item.getItemDetails().getOwner().getLogin().equals(principal.getName())) {
                
                item.setHired(true);
                /* set notification to "seen" */
                
                try {
                    itemManager.update(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            
        }
        return result;
    }
    
}
