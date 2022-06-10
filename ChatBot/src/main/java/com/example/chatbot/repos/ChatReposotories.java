package com.example.chatbot.repos;


import com.example.chatbot.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatReposotories extends JpaRepository<Message, Long> {

    List<Message> findAllByConversation(Long id);
}

