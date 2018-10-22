package com.springbootstarter.controller;

import com.springbootstarter.bean.Topic;
import com.springbootstarter.bean.Users;
import com.springbootstarter.service.LoginService;
import com.springbootstarter.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class TopicController {
	
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
    @RequestMapping(value="/login", method = RequestMethod.POST)
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

    }
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
    public String welcomeUser(@ModelAttribute Users users){
        return "Welcome";
    }

    @RequestMapping(value="/addTopic", method = RequestMethod.GET)
    public ModelAndView showAddtopic(Model model){
        return new ModelAndView( "addTopic");
    }

	@RequestMapping("/topics")
	public List<Topic> getAllTopics() {
		return topicService.getAllTopics();
	}
	
	@RequestMapping("/topics/{id}")
	public Topic getTopic(@PathVariable String id) {
		return topicService.getTopic(id);	
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topics")
	public void addTopic(@RequestBody Topic topic) {
		topicService.addTopic(topic);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
	public void updateTopic(@RequestBody Topic topic, @PathVariable String id) {
		topicService.updateTopic(topic, id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
	public void deleteTopic(@PathVariable String id) {
		topicService.deleteTopic(id);
	}
}
