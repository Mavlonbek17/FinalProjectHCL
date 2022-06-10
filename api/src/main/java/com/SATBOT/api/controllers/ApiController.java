package com.SATBOT.api.controllers;


import com.SATBOT.api.entities.Conversation;
import com.SATBOT.api.entities.Message;
import com.SATBOT.api.entities.User;
import com.SATBOT.api.utilities.ChatInput;
import com.SATBOT.api.utilities.CommonService;
import com.SATBOT.api.utilities.MessageBody;
import com.SATBOT.api.utilities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    CommonService commonService;


    @ResponseBody
    @RequestMapping(value = "/web/api/admin/conversations" , method = RequestMethod.POST)
    public ResponseEntity<List<Conversation>> conversations(){
        List<Conversation> response = commonService.getConversations();
        return new ResponseEntity<>( response , HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/web/api/admin/messages" , method = RequestMethod.POST)
    public ResponseEntity<List<Message>> messages(@RequestBody ChatInput chatInput){
        List<Message> response = commonService.getMessages( chatInput.getId() );
        return new ResponseEntity<>( response , HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/web/api/user/conversations" , method = RequestMethod.POST)
    public ResponseEntity<List<Conversation>> conversationsuser(){
        List<Conversation> response = commonService.getConversations();
        return new ResponseEntity<>( response , HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/web/api/user/messages" , method = RequestMethod.POST)
    public ResponseEntity<List<Message>> messagesuser(@RequestBody ChatInput chatInput){
        List<Message> response = commonService.getMessages( chatInput.getId() );
        return new ResponseEntity<>( response , HttpStatus.OK);
    }

}
