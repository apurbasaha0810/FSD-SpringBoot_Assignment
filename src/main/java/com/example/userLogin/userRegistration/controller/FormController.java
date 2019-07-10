package com.example.userLogin.userRegistration.controller;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.userLogin.userRegistration.config.HibernateUtil;
import com.example.userLogin.userRegistration.dao.userDao;
import com.example.userLogin.userRegistration.model.UserModel;


@Controller
public class FormController {

	@GetMapping("/home")
	public String homePage() { return "home";
	}

	@GetMapping("/login")
	public String loginPage(@ModelAttribute UserModel user) {
		return "login";
	}

	@GetMapping("/register")
	public String displayLogin(Model model) {
		model.addAttribute("userModel", new UserModel());
		return "register";
	}

	@GetMapping("/logout")
	public String logOut() {
		return "logout";
	}

	@GetMapping("/update")
	public String update(@ModelAttribute UserModel user) {
		return "accountUpdate";
	}
	
	@PostMapping("/registration")
	public String registerForm(@ModelAttribute UserModel user, HttpSession session1,HttpServletRequest request,HttpServletResponse response)
	{
		String result = "";
		try {
			PrintWriter out = response.getWriter();

			boolean condition1 = user.getName() != null
					&& user.getPassword() != null && user.getEmail() != null;
			boolean condition2 = user.getUserId() != 0 && user.getName() != "" && user.getPassword() != "" && user.getEmail() != "";

			String captcha = (String) session1.getAttribute("captcha");
			String code = (String) request.getParameter("code");

			boolean checkCaptcha = false;
			if (captcha != null && code != null) {
				if (captcha.equals(code)) {
					checkCaptcha = true;
				} else {
					out.print("<p style='color:red'>Incorrect Captcha! Please Try Again.</p>");
				}
			}
			
			if(!checkCaptcha) {
				result = "register";
			} else if (condition1 && condition2) {
				out.print("<p style='color:green'>Successfully Registered. Please login with your credential.</p>");
				result = "login";
				// insert into H2 DB
				SessionFactory sf = HibernateUtil.getSessionFactory();
				Session session = sf.openSession();
				userDao userDaoObj = new userDao(session);
				userDaoObj.insertUser(user);
			} else {
				out.print("<p style='color:red'>Please correct your details.</p>");
				result = "register";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@PostMapping("/admin")
	public String handleAfterLogging(@ModelAttribute UserModel user)
	{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session=sf.openSession();
		// get the values

		userDao userDao = new userDao(session);

		userDao.getUserById(user.getUserId());
		return "accountUpdate";
	}

	@PostMapping("/update")
	public String updatePassword(@ModelAttribute UserModel user)
	{
		//update password
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session=sf.openSession();

		userDao userDao = new userDao(session);

		// userDao.getUserById(user.getUserId());
		userDao.updatePassword(user);

		return "Updated";
	}

}
