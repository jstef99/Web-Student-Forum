package com.jstef.StudentForum.Controller;

import com.jstef.StudentForum.Entity.*;
import com.jstef.StudentForum.Service.AllowedEmailService;
import com.jstef.StudentForum.Service.ForumThreadService;
import com.jstef.StudentForum.Service.TopicService;
import com.jstef.StudentForum.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
public class AuthenticatedController {
    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ForumThreadService forumThreadService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AllowedEmailService emailService;

    @RequestMapping("/")
    public String goHome(){
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String homePage(Model model){
        List<ForumThread>threads= forumThreadService.findAll();
        model.addAttribute("threads",threads);
        return "home-page";
    }

    //in this section is possible: logging out, changing password, email and viewing owned topics
    //and topics for which student had signed up
    @RequestMapping("/user/my_profile")
    public String myProfile(Principal principal, Model model){
        String userName=principal.getName();
        User user = userService.findByUserName(userName);
        List<Topic> topics = user.getTopics();
        model.addAttribute("email",user.getEmail());
        model.addAttribute("topics",topics);
        return "my-profile";
    }
    @GetMapping("/user/change_password")
    public String changePassword(Principal principal, HttpServletRequest request,Model model){
        String name = principal.getName();
        User user = userService.findByUserName(name);
        request.getSession().setAttribute("roles",user.getRoles());
        request.getSession().setAttribute("topics",user.getTopics());
        model.addAttribute("user",user);
        return "password-update-form";
    }
    @PostMapping("/user/change_password")
    public String changePasswordSubmit(@Valid @ModelAttribute("user")User user,BindingResult result,HttpServletRequest request){
        if(result.hasErrors()){
            return "password-update-form";
        }
        Collection<Role> roles = (Collection<Role>)request.getSession().getAttribute("roles");
        List<Topic> topics = (List<Topic>)request.getSession().getAttribute("topics");
        user.setRoles(roles);
        user.setTopics(topics);
        user.setPassword(encoder.encode(user.getPassword()));
        userService.updateUser(user);
        return "redirect:/home";
    }

    @GetMapping("/user/change_email")
    public String updateUser(Principal principal, Model model, HttpServletRequest request){
        String name = principal.getName();
        User user = userService.findByUserName(name);
        request.getSession().setAttribute("roles",user.getRoles());
        request.getSession().setAttribute("topics",user.getTopics());
        model.addAttribute("user",user);
        request.getSession().setAttribute("previousEmail",user.getEmail());
        return "email-update-form";
    }

    @PostMapping("/user/change_email")
    public String updateUserSubmit(@Valid @ModelAttribute("user")User user, BindingResult result, HttpServletRequest request,
                                   Model model){
        if(result.hasErrors()){
            return "email-update-form";
        }
        Collection<Role> roles = (Collection<Role>)request.getSession().getAttribute("roles");
        List<Topic> topics = (List<Topic>)request.getSession().getAttribute("topics");
        String previousEmail = (String)request.getSession().getAttribute("previousEmail");
        user.setRoles(roles);
        user.setTopics(topics);

        AllowedEmail email = emailService.findByName(user.getEmail());
        if(email==null||email.isUsed()==true){
            System.out.println("Email " + email);
            model.addAttribute("user", new User());
            model.addAttribute("registrationError", "You cannot change email to this");
            return "email-update-form";
        }
        email.setUsed(true);
        AllowedEmail previousAllowedEmail = emailService.findByName(previousEmail);
        System.out.println(previousEmail);
        System.out.println(previousAllowedEmail);
        previousAllowedEmail.setUsed(false);
        emailService.UpdateEmail(email);
        emailService.UpdateEmail(previousAllowedEmail);
        userService.updateUser(user);
        return "redirect:/home";
    }
    //list of topics, for which user had signed up
    @GetMapping("/user/signups")
    public String showSignups(Model model){
        List<ForumThread>threads=forumThreadService.findAll();
        model.addAttribute("threads",threads);
        return "threads-signups";
    }
    @GetMapping("/user/signups/{threadId}")
    public String showSignupsFromThread(Principal principal,Model model,@PathVariable("threadId")int threadId){
        String name = principal.getName();
        User user = userService.findByUserName(name);
        model.addAttribute("topics",user.getTopicsFromThread(threadId));
        return "list-thread-signups";
    }
    //list of topics owned by user
    @GetMapping("/user/topics")
    public String showThreadsForUserTopics(Model model){
        List<ForumThread>threads=forumThreadService.findAll();
        model.addAttribute("threads",threads);
        return "threads-topics";
    }
    @GetMapping("/user/topics/{threadId}")
    public String showUserOwnedTopics(@PathVariable("threadId")int threadId, Principal principal,Model model){
        String name = principal.getName();
        User user = userService.findByUserName(name);
        model.addAttribute("topics",topicService.findByUserIdAndThreadId(user.getId(),threadId));
        return "list-thread-topics";
    }
}
