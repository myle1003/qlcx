package com.qlcx.web.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qlcx.common.core.controller.BaseController;
import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.common.utils.ServletUtils;
import com.qlcx.common.utils.StringUtils;

/**
 * Login authentication
 */
@Controller
public class SysLoginController extends BaseController {
	@GetMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		// If it is an Ajax request, return the Json string.
		if (ServletUtils.isAjaxRequest(request)) {
			return ServletUtils.renderString(response,
					"{\"code\":\"1\",\"msg\":\"Chưa đăng nhập hoặc hết thời gian đăng nhập. Xin vui lòng đăng nhập lại.\"}");
		}
		return "login";
	}

	@PostMapping("/login")
	@ResponseBody
	public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return success();
		} catch (AuthenticationException e) {
			String msg = "Tên đăng nhập hoặc mật khẩu không đúng!";
			if (StringUtils.isNotEmpty(e.getMessage())) {
				msg = e.getMessage();
			}
			return error(msg);
		}
	}

	@GetMapping("/unauth")
	public String unauth() {
		return "error/unauth";
	}
}
