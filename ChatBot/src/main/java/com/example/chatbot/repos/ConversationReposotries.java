package com.example.chatbot.repos;

import com.example.chatbot.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationReposotries extends JpaRepository<Conversation,Long> {
}
