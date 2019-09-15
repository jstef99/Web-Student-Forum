package com.jstef.StudentForum.Controller;

import com.jstef.StudentForum.Entity.AllowedEmail;
import com.jstef.StudentForum.Entity.Token;
import com.jstef.StudentForum.Entity.User;
import com.jstef.StudentForum.Service.AllowedEmailService;
import com.jstef.StudentForum.Service.TokenService;
import com.jstef.StudentForum.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private UserService service;

    @Autowired
    private AllowedEmailService emailService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JavaMailSender mailSender;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/form")
    public String showRegistrationForm(Model model){
        User newUser = new User();
        model.addAttribute("user",newUser);
        return "register-form";
    }

    @PostMapping("/submit")
    public String submitUser(@Valid @ModelAttribute("user")User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "register-form";
        }
        String username = user.getUsername();
        User existing = service.findByUserName(username);
        if (existing != null){
            model.addAttribute("user", new User());
            model.addAttribute("registrationError", "User with this username already exists.");
            return "register-form";
        }
        String email = user.getEmail();
        AllowedEmail allowedEmail = emailService.findByName(email);
        if(allowedEmail==null||allowedEmail.isUsed()==true){
            model.addAttribute("user", new User());
            model.addAttribute("registrationError", "You cannot register with this email");
            return "register-form";
        }
        allowedEmail.setUsed(true);
        emailService.UpdateEmail(allowedEmail);
        service.saveNewUser(user);
        User mergedUser = service.findByUserName(user.getUsername());
        Token token = new Token();
        token.setUser(mergedUser);
        token.setName(UUID.randomUUID().toString());
        //process of sending token to user to verify account
        SimpleMailMessage sampleMail = new SimpleMailMessage();
        sampleMail.setTo(user.getEmail());
        sampleMail.setSubject("Confirm you account");
        sampleMail.setText("Hello " + user.getUsername() + "!\n" + "To activate account please click here:" + "http://localhost:8080" + "/register/submit/"+token.getName());
        mailSender.send(sampleMail);
        tokenService.saveToken(token);

        return "redirect:/login";
    }

    @GetMapping("/submit/{tokenName}")
    public String confirmToken(@PathVariable("tokenName")String tokenName){
        Token token = tokenService.findByName(tokenName);
        if(token==null){
            return "redirect:/error";
        }
        User user = token.getUser();
        user.setEnabled(true);
        service.updateUser(user);
        return "redirect:/login";
    }

}
