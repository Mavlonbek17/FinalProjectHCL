package com.SATBOT.api.repos;

import com.SATBOT.api.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepo extends JpaRepository<Message, Long> {

    List<Message> findAllByConversation(Long id);
}

