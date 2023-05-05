package com.backend.FUN4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class Fun4Application {

	@RequestMapping("/")
	@ResponseBody
	String home(){
		return "Fun4 - Backend";
	}

	public static void main(String[] args) {
		SpringApplication.run(Fun4Application.class, args);
	}

}
