package com.SATBOT.api.repos;

import com.SATBOT.api.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvRepo extends JpaRepository<Conversation, Long> {
}

