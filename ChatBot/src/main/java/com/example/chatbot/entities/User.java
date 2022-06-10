package com.example.chatbot.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    String email;
    String name;

    String password;

    String role;

    Boolean enabled = true;

    String department;

    String mobile;

    String faculity;

    String type;







}
