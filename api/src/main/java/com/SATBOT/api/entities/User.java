package com.SATBOT.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    String email;

    String name;

    String password;

    String role;

    Boolean enabled = true;

    String department;

    String mobile;

    String faculty;

    String type;
}
