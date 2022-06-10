package com.example.chatbot.utils;

import com.example.chatbot.entities.Conversation;
import com.example.chatbot.entities.Message;
import com.example.chatbot.entities.QNA;
import com.example.chatbot.entities.User;
import com.example.chatbot.repos.ChatReposotories;
import com.example.chatbot.repos.ConversationReposotries;
import com.example.chatbot.repos.QRepo;
import com.example.chatbot.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommonService {

    @Autowired
    ChatReposotories chatReposotories;

    @Autowired
    ConversationReposotries conversationReposotries;

    @Autowired
    UserRepo userRepo;

    @Autowired
    QRepo qRepo;


    public Conversation createConversation(String anonymous) {
        Conversation  conversation = new Conversation();
        conversation.setUser("Anonymous");
        conversation.setStarttime( new Date());
        conversationReposotries.save( conversation );

        return  conversation;
    }

    public void saveChat(Long index, String user, String msg , String owner) {
        Message message = new Message();
        message.setMessage(msg);
        message.setUser(user);
        message.setOwner(owner);
        message.setConversation(index);
        message.setTime( new Date());
        chatReposotories.save(message);
    }

    public String isInQnA(String message) {

        List<QNA> qnAList =  qRepo.findAllByQuestion(message);

        if(qnAList.isEmpty()){
            return "Sorry! Couldnt help this moment. Try something else....";
        } else {
            return qnAList.get(0).getAnswer();
        }

    }

    public void saveUser(User user) {
        userRepo.save(user);

    }

    public User getUser(String username) {

        return userRepo.findAllByEmail(username);

    }

    public List<Conversation> getConversations() {
        return conversationReposotries.findAll();
    }

    public List<Message> getMessages(Long id) {
        return chatReposotories.findAllByConversation(id);
    }
}
