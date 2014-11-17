package com.keyit.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.keyit.bean.AdminUser;

@Controller
public class LoginController {

	@RequestMapping(value = "/restaurant/showLoginPage", method = RequestMethod.GET)
	public ModelAndView gotoLoginPage(HttpServletRequest request) {

		if (request.getSession().getAttribute("adminUser") == null) {

			ModelAndView modelAndView = new ModelAndView("login");

			AdminUser adminUser = new AdminUser();

			modelAndView.addObject("adminUser", adminUser);

			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView(
					"redirect:/restaurant/restaurants");
			return modelAndView;
		}
	}

	@RequestMapping(value = "/restaurant/login", method = RequestMethod.POST)
	public ModelAndView LoginProcess(
			@ModelAttribute("adminUser") AdminUser adminUser,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(
				"redirect:/restaurant/restaurants");

		String userName = adminUser.getUserName();
		String password = adminUser.getPassword();

		if (!this.checkUser(userName, password)) {
			ModelAndView loginFail = new ModelAndView(
					"redirect:/restaurant/showLoginPage");
			request.getSession().setAttribute("message", "Login Fail..");
			request.setAttribute("adminUser", null);

			return loginFail;
		}
		this.deleteTempFiles(request);
		request.getSession().setAttribute("adminUser", adminUser);
		return modelAndView;
	}

	@RequestMapping(value = "/restaurant/logout", method = RequestMethod.GET)
	public ModelAndView LogOutProcess(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(
				"redirect:/restaurant/showLoginPage");
		this.deleteTempFiles(request);
		request.getSession().invalidate();
		return modelAndView;
	}

	private void deleteTempFiles(HttpServletRequest request) {
		String tempDirectoryPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/resources/temp/";
		File tempDir = new File(tempDirectoryPath);
		try {
			FileUtils.cleanDirectory(tempDir);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private boolean checkUser(String userName, String password) {
		Properties prop = new Properties();

		InputStream inputStream = LoginController.class.getClassLoader()
				.getResourceAsStream("/resources/admin.properties");

		try {
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String user = prop.getProperty("userName");
		String pwd = prop.getProperty("password");

		if (user.equals(userName) && pwd.equals(password)) {

			return true;
		}

		return false;
	}

}
