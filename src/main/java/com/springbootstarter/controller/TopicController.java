package com.springbootstarter.controller;

import com.springbootstarter.bean.Topic;
import com.springbootstarter.bean.Users;
import com.springbootstarter.service.LoginService;
import com.springbootstarter.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

//@CrossOrigin(origins = "http://localhost/8082", maxAge = 3600)
@RestController
public class TopicController implements ErrorController {
	
	@Autowired
	private TopicService topicService;

    @Autowired
    LoginService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(Model model, String error, String logout) {
        System.out.println("GET ");
		if (error != null)
			model.addAttribute("errorMsg", "Your username and password are invalid.");

		if (logout != null)
			model.addAttribute("msg", "You have been logged out successfully.");
        model.addAttribute("users", new Users());
		return new ModelAndView( "login");
	}
   /* @RequestMapping(value="/login", method = RequestMethod.POST)
    public ModelAndView showWelcomePageP(@ModelAttribute Users users){
        System.out.println("post ");
        Users u = service.validateUser(users.getUsername());
        System.out.println("post ");
        if (u !=null && u.getPassword()==users.getPassword()) {
            // model.put("name", name);
            System.out.println("valid user "+users.getUsername());
            if(u.getRole()=="ADMIN")
                return new ModelAndView("AdminPage");
            else
                return new ModelAndView("UserPage");
        }else{
            System.out.println(" user not found"+users.getUsername()+users.getPassword());
          //  model.put("errorMessage", "Invalid Credentials");
            return new ModelAndView("login");
        }

    }*/

	@RequestMapping("/logout")
	ModelAndView logout(HttpServletRequest r, Model model, HttpSession session) throws Exception {
		if (session != null) {
			session.invalidate();
		}

		return new ModelAndView( "login");
	}
   @GetMapping("/switchtouser")
    public String switchToUserFromAdmin(){
	   /*//HttpSecurity httpSecurity = new HttpSecurity();
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	   List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
	   System.out.println("current authority : "+updatedAuthorities.get(0));
	   updatedAuthorities.add(new SimpleGrantedAuthority("USER")); //add your role here [e.g., new SimpleGrantedAuthority("ROLE_NEW_ROLE")]

	   Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
	   System.out.println("no of auth : "+updatedAuthorities.size()+" new added role "+updatedAuthorities.get(1));
	   SecurityContextHolder.getContext().setAuthentication(newAuth);*/
	   return "Successfully updated authority";
   }

    @CrossOrigin(origins = "http://localhost:8082")
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
    public String welcomeUser(@ModelAttribute Users users, HttpServletRequest req, HttpServletResponse respons){
		HttpSession session = req.getSession();
		System.out.println(req.getSession().getId());
		return "Welcome !!!!!!!!!!";
    }

    /*@RequestMapping(value="/addTopic", method = RequestMethod.GET)
    public ModelAndView showAddtopic(Model model){
        return new ModelAndView( "addTopic");
    }*/
    @CrossOrigin(origins = "http://localhost:8082")
	@GetMapping("/topics")
	public List<Topic> getAllTopics(HttpServletRequest req,HttpServletResponse respons) {
		HttpSession session = req.getSession();
		System.out.println("Current session id "+req.getSession().getId());
    	return topicService.getAllTopics();
	}
	
	@GetMapping("/topics/{id}")
	public Topic getTopic(HttpServletRequest req,@PathVariable String id) {
		HttpSession session = req.getSession();
		System.out.println("Current session id in search topic "+req.getSession().getId());
		return topicService.getTopic(id);	
	}

  //  @CrossOrigin(origins = "http://localhost:8082")
	@PostMapping("/topics")
	public String addTopic(@RequestBody Topic topic) {
		topicService.addTopic(topic);
		return ("Thanks For adding "+ topic.getId());
	}
	
	@PutMapping("/topics/{id}")
	public void updateTopic(@RequestBody Topic topic, @PathVariable String id) {
		topicService.updateTopic(topic, id);
	}
	
	@DeleteMapping("/topics/{id}")
	public String deleteTopic(@PathVariable String id) {
		topicService.deleteTopic(id);
		return  "Topic "+id+" has been deleted successfully";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				return "Not found";
			}
			else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "Internal server error occurred";
			}else if(statusCode ==403){
                return "You don't have access for this";
            }
		}
		return "Something went wrong";
	}

}
