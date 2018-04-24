package com.king.system.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.king.common.context.ValidationCodeServlet;

/**
 * 用户登录
 * @author wanghuaying
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private ValidationCodeServlet validationCodeServlet;
	
	@RequestMapping("/login")
	public String showLoginForm(HttpServletRequest req, Model model) {
		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
			model.addAttribute("error", error);
		}else if("randomCodeError".equals(exceptionClassName)){
			error = "验证码不对!";
			model.addAttribute("error", error);
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
			model.addAttribute("error", error);
		} else if (exceptionClassName != null) {
			error = "其他错误：" + exceptionClassName;
			model.addAttribute("error", error);
		}else{
			error="成功";
			model.addAttribute("error", error);
		}
		return "login";
	}
	
	@RequestMapping("/check/captcha")
    @ResponseBody
    public Object captcha(HttpServletRequest request) {
        return validationCodeServlet.isCaptcha(request)+"";
    }
	
}
