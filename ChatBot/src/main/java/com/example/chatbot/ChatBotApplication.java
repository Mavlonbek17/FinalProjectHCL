package com.example.chatbot;

import com.example.chatbot.entities.QNA;
import com.example.chatbot.repos.QRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatBotApplication {

	@Autowired
	QRepo qRepo;

	public static void main(String[] args) {
		SpringApplication.run(ChatBotApplication.class, args);
	}

	@Bean
	public void addData(){
		qRepo.save(new QNA( null, "size" , "1200acres" , "Campus" , "CAMPUS"));
		qRepo.save(new QNA( null, "Color" , "Green" , "Campus" , "CAMPUS" ));
		qRepo.save(new QNA( null, "Department" , "10" , "Department" , "DEPARTMENTS" ));
		qRepo.save(new QNA( null, "Gender" , "50:50" , "Ratio" , "FUN" ));
		qRepo.save(new QNA( null, "hello" , "Hello! Nice to see you Again" , "Greeting" ,"GREETING" ));
		qRepo.save(new QNA( null, "rank" , "Vistula University ranking #1 in Poland )" , "ranking" , "CAMPUS"));
		qRepo.save(new QNA( null, "students" , "We have student from more than 100 countries Including USA" , "students" , "CAMPUS"));
		qRepo.save(new QNA( null, "fee" , "One year Tuition fee is 2500 Euro" , "ranking" , "CAMPUS"));
		qRepo.save(new QNA( null, "scholarship" , "Vistula University has Rectors students in the amount of 10000 pln" , "ranking" , "CAMPUS"));
		qRepo.save(new QNA( null, "bye" , "I hope you liked Vistula University . Hopefully we will see you back soon" , "ranking" , "CAMPUS"));
		qRepo.save(new QNA( null, "how to apply" , "You can apply here https://apply.vistula.edu.pl/" , "ranking" , "CAMPUS"));



	}

}
