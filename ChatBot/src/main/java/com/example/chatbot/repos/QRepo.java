package com.example.chatbot.repos;


import com.example.chatbot.entities.QNA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QRepo extends JpaRepository<QNA, Long> {
    List<QNA> findAllByQuestion(String message);
}
