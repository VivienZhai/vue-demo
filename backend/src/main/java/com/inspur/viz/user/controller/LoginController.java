package com.inspur.viz.user.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.loushang.framework.base.RestResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@PostMapping("/login")
	public RestResult<String> doLogin(@RequestBody Map<String, String> userMap) {
		String username = userMap.get("username");
		String pwd = userMap.get("password");
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		String sessionId = session.getId().toString();
		System.out.println("sessionId: " + sessionId);

		UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);

		return new RestResult<String>();
	}
}
