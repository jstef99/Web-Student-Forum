package com.jstef.StudentForum.Computation;

import com.jstef.StudentForum.Entity.User;
import com.jstef.StudentForum.Service.TopicService;

import java.util.ArrayList;
import java.util.List;

public class Computation {

    public List<User> findCandidates(List<User>signedUpUsers, int [] occurences, TopicService topicService, int threadId){
        int mxSize = occurences.length;
        List<Integer>signedUpOccurences = new ArrayList<>(mxSize);
        List<User>minOccUsers=new ArrayList<>(mxSize);
        List<User>candidates=new ArrayList<>(mxSize);
        int minOcc = occurences[signedUpUsers.get(0).getId()];
        for(User user : signedUpUsers){
            signedUpOccurences.add(occurences[user.getId()]);
        }
        for(int currOcc : signedUpOccurences){
            System.out.print(currOcc);
            minOcc = Math.min(minOcc,currOcc);
        }
        System.out.println("FOUND MINOCC="+minOcc);
        for(int i = 0; i < signedUpUsers.size(); ++i){
            if(signedUpOccurences.get(i)==minOcc){
                minOccUsers.add(signedUpUsers.get(i));
            }
        }
        int minTopics = topicService.findByUserIdAndThreadId(threadId,minOccUsers.get(0).getId()).size();
        for(User user : minOccUsers){
            minTopics = Math.min(minTopics,topicService.findByUserIdAndThreadId(threadId,user.getId()).size());
        }
        for(User user : minOccUsers){
            if(topicService.findByUserIdAndThreadId(threadId,user.getId()).size()==minTopics){
                candidates.add(user);
            }
        }
        return candidates;
    }
}
