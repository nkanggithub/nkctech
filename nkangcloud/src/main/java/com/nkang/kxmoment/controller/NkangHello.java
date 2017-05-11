package com.nkang.kxmoment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/NkangHello")
public class NkangHello {
	
	public String str = "";
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	@RequestMapping("/helloWorld")
	public ModelAndView helloWorld() {
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
		return new ModelAndView("helloWorld", "message", message);
	}
	
	@RequestMapping("/helloKitty")
	public String helloKitty() {
		str = "anotherone";
		return "Jsp/welcome";
	}
}
