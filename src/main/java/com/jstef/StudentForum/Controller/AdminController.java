package com.jstef.StudentForum.Controller;

import com.jstef.StudentForum.Computation.Computation;
import com.jstef.StudentForum.Entity.*;
import com.jstef.StudentForum.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    ForumThreadService threadService;
    @Autowired
    SubthreadService subthreadService;
    @Autowired
    TopicService topicService;
    @Autowired
    AllowedEmailService emailService;


    //students management list from admin pov
    @RequestMapping("/students")
    public String studentsManagement(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "students-admin-list";
    }
    //threads management list from admin pov
    @RequestMapping("/threads")
    public String threadsManagement(Model model){
        List<ForumThread>threads = threadService.findAll();
        model.addAttribute("threads",threads);
        return "thread-admin-list";
    }
    //subthreads management list from admin pov
    @RequestMapping("/subthreads")
    public String subthreadsManagement(Model model){
        List<Subthread>subthreads = subthreadService.findAll();
        model.addAttribute("subthreads",subthreads);
        return "subthread-admin-list";
    }
    //topics management list from admin pov
    @RequestMapping("/topics")
    public String topicsManagement(Model model){
        List<Topic>topics = topicService.findAll();
        model.addAttribute("topics",topics);
        return "topic-admin-list";
    }

    @GetMapping("/update_thread/{Id}")
    public String updateThread(@PathVariable("Id")int Id, Model model,HttpServletRequest request){
        ForumThread thread = threadService.findById(Id);
        model.addAttribute("thread",thread);
        return "thread-update-form";
    }

    @PostMapping("/update_thread_submit")
    public String updateThreadSubmit(@ModelAttribute("thread")ForumThread thread,HttpServletRequest request){
        threadService.saveNewThread(thread);
        return "redirect:/admin/threads";
    }

    @GetMapping("/update_subthread/{Id}")
    public String updateSubthread(@PathVariable("Id")int Id, Model model,HttpServletRequest request){
        Subthread subthread = subthreadService.findById(Id);
        model.addAttribute("subthread",subthread);
        return "subthread-update-form";
    }

    @PostMapping("/update_subthread_submit")
    public String updateSubthreadSubmit(@ModelAttribute("subthread")Subthread subthread,HttpServletRequest request){
        subthreadService.saveNewSubthread(subthread);
        return "redirect:/admin/subthreads";
    }

    @GetMapping("/new_thread")
    public String addNewThread(Model model){
        ForumThread thread = new ForumThread();
        model.addAttribute("thread",thread);
        return "new-thread-form";
    }

    @PostMapping("/new_thread")
    public String submitNewThread(@ModelAttribute("thread")ForumThread thread){
        threadService.saveNewThread(thread);
        return "redirect:/home";
    }

    @GetMapping("/delete_thread/{Id}")
    public String deleteThread(@PathVariable("Id")int id){
        threadService.deleteById(id);
        return "redirect:/admin/threads";
    }

    @GetMapping("/delete_subthread/{Id}")
    public String deleteSubthread(@PathVariable("Id")int id){
        subthreadService.deleteById(id);
        return "redirect:/admin/subthreads";
    }

    @GetMapping("/update_topic/{Id}")
    public String updateTopic(@PathVariable("Id")int Id, Model model,HttpServletRequest request){
        Topic topic = topicService.findById(Id);
        Subthread subthread = topic.getSubthread();
        request.getSession().setAttribute("subthread",subthread);
        model.addAttribute("topic",topic);
        return "topic-update-form";
    }

    @PostMapping("/update_topic_submit")
    public String updateTopicSubmit(@ModelAttribute("topic")Topic topic,HttpServletRequest request){
        Subthread subthread = (Subthread) request.getSession().getAttribute("subthread");
        topic.setSubthread(subthread);
        topicService.saveTopic(topic);
        return "redirect:/admin/topics";
    }

    @GetMapping("/delete_topic/{Id}")
    public String deleteTopic(@PathVariable("Id")int Id, Model model,HttpServletRequest request){
        Topic topic = topicService.findById(Id);
        topicService.deleteTopic(topic);
        return "redirect:/admin/topics";
    }

    @GetMapping("/add_user")
    public String addUser(Model model){
        model.addAttribute("user",new User());
        return "new-user";
    }

    @PostMapping("/add_user")
    public String submitUser(@ModelAttribute("user") User user){
        userService.saveNewUser(user);
        return "redirect:/admin/students";
    }

    @RequestMapping("/delete_user/{Id}")
    public String deleteUser(@PathVariable("Id")int Id){
        User user = userService.findById(Id);
        String email = user.getEmail();
        userService.deleteById(Id);
        AllowedEmail aEmail = emailService.findByName(email);
        aEmail.setUsed(false);
        emailService.UpdateEmail(aEmail);
        return "redirect:/admin/students";
    }

    @GetMapping("/update_user/{Id}")
    public String updateUser(@PathVariable("Id")int Id, Model model,HttpServletRequest request){
        User user = userService.findById(Id);
        request.getSession().setAttribute("roles",user.getRoles());
        request.getSession().setAttribute("topics",user.getTopics());
        model.addAttribute("user",user);
        return "student-update-form";
    }

    @PostMapping("/update_user_submit")
    public String updateUserSubmit(@ModelAttribute("user")User user,HttpServletRequest request){
        Collection<Role> roles = (Collection<Role>)request.getSession().getAttribute("roles");
        List<Topic> topics = (List<Topic>)request.getSession().getAttribute("topics");
        user.setRoles(roles);
        user.setTopics(topics);
        userService.updateUser(user);
        return "redirect:/admin/students";
    }

    //creating new subthread in a given thread
    @GetMapping("/{threadNumber}/new_subthread")
    public String showNewSubThreadForm(Model model,@PathVariable("threadNumber")int threadNumber){
        model.addAttribute("subthread", new Subthread());
        model.addAttribute("threadNumber",threadNumber);
        return "new-subthread-form";
    }
    //..and submitting it
    @PostMapping("/{threadNumber}/new_subthread")
    public String submitThread(@ModelAttribute("thread")Subthread subthread, @PathVariable("threadNumber")int threadNumber) {
        ForumThread thread = threadService.findById(threadNumber);
        subthread.setThread(thread);
        subthread.setActive(true);
        subthreadService.saveNewSubthread(subthread);
        return "redirect:/threads/{threadNumber}";
    }
    //all the users who signed up for a topic in a given subthread are considered.
    //the biggest priority have users with lowest occurance (occurence in terms of this
    //certain subthread), then users who are they owners of the smallest ammount of topics
    //in this thread. If there are users with same priority, they are chosen randomly. For each
    //topic in a given subthread is chosen a new user who had signed.
    @RequestMapping("/{threadNumber}/{subthreadId}/finalizeSubmits")
    public String finalizeSubmits(@PathVariable("threadNumber")int threadId, @PathVariable("subthreadId")int subthreadId){
        Subthread subthread = subthreadService.findById(subthreadId);
        if(subthread.isActive()==false) return "redirect:/threads/{threadNumber}";
        Computation computeClass = new Computation();
        int maxId = userService.findBiggestId();
        int [] occurences = new int [maxId+1];
        List<Topic>topics=topicService.findBySubthreadId(subthreadId);
        for(Topic topic : topics){
            List<User>signedUpUsers=userService.findBySignedUp(topic);
            if(signedUpUsers.size()==0){
                continue;
            }
            //helper method to choose users with highest priority for a given topic
            List<User>candidates = computeClass.findCandidates(signedUpUsers,occurences,topicService,threadId);
            int randomCandidate = (int)(Math.random()*candidates.size());
            topic.setOwnerId(candidates.get(randomCandidate).getId());
            topicService.updateTopic(topic);
            occurences[candidates.get(randomCandidate).getId()]++;
        }
        subthread.setActive(false);
        subthreadService.saveNewSubthread(subthread);
        return "redirect:/threads/{threadNumber}";
    }
    //add new email, with which users can register
    @GetMapping("/add_allowed_email")
    public String newAllowedEmailForm(Model model){
        model.addAttribute("email", new AllowedEmail());
        return "new-allowed-email-form";
    }
    @PostMapping("/add_allowed_email")
    public String newAllowedEmailFormSubmit(@ModelAttribute("email")AllowedEmail email){
        emailService.UpdateEmail(email);
        return "redirect:/home";
    }
}
