package com.jstef.StudentForum.Controller;

import com.jstef.StudentForum.Entity.ForumThread;
import com.jstef.StudentForum.Entity.Subthread;
import com.jstef.StudentForum.Entity.Topic;
import com.jstef.StudentForum.Entity.User;
import com.jstef.StudentForum.Service.ForumThreadService;
import com.jstef.StudentForum.Service.SubthreadService;
import com.jstef.StudentForum.Service.TopicService;
import com.jstef.StudentForum.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class ThreadController {
    @Autowired
    UserService userService;

    @Autowired
    ForumThreadService forumThreadService;

    @Autowired
    SubthreadService subthreadService;

    @Autowired
    TopicService topicService;

    //listing all subthreads in a given thread
    @RequestMapping("/threads/{threadNumber}")
    public String threadInterior(@PathVariable(value="threadNumber") int threadNumber, Model model){
        ForumThread thread = forumThreadService.findById(threadNumber);
        List<Subthread>subthreads= thread.getSubthreads();
        model.addAttribute("subthreads",subthreads);
        model.addAttribute("thread_number",threadNumber);
        return "list-subthreads";
    }
    //listing all topics in given subtread and thread
    @RequestMapping("/threads/{threadNumber}/{subthreadId}")
    public String subthreadInterior(@PathVariable("threadNumber")int threadNumber, @PathVariable("subthreadId")int subthreadId,Model model){
        Subthread subthread = subthreadService.findById(subthreadId);
        List<Topic>topics = subthread.getTopics();
        model.addAttribute("topics",topics);
        model.addAttribute("threadId",threadNumber);
        model.addAttribute("subthreadId",subthreadId);
        return "list-topics";
    }
    //adding topic in certain subthread in thread
    @GetMapping("/threads/{threadId}/{subthreadId}/add_topic")
    public String showNewTopicForm(@PathVariable("threadId") int threadId,@PathVariable("subthreadId") int subthreadId, Model model){
        Topic topic = new Topic();
        model.addAttribute("topic",topic);
        model.addAttribute("threadId",threadId);
        model.addAttribute("subthreadId",subthreadId);
        return "new-topic-form";
    }
    @PostMapping("/threads/{threadId}/{subthreadId}/submit_topic")
    public String submitTopic(@ModelAttribute("topic")Topic topic,@PathVariable("threadId")int threadId, @PathVariable("subthreadId")int subthreadId){
        Subthread subthread = subthreadService.findById(subthreadId);
        topic.setSubthread(subthread);
        topic.setThreadId(threadId);
        topicService.saveTopic(topic);
        return "redirect:/threads/{threadId}";
    }
    //signing up - user will be considered while finalizing sign ups
    @RequestMapping("/threads/{threadId}/{subthreadId}/topics/{topicId}/sign_up")
    public String topicSignUp(Principal principal, @PathVariable("threadId")int threadId, @PathVariable("subthreadId")int subthreadId,@PathVariable("topicId")int topicId){
        User user = userService.findByUserName(principal.getName());
        Topic topic = topicService.findById(topicId);
        user.signUpForTopic(topic);
        userService.updateUser(user);
        return "redirect:/threads/{threadId}/{subthreadId}";
    }
}
