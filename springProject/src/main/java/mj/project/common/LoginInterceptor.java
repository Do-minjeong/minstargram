package mj.project.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import mj.project.domain.MemberVO;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle Interceptor");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		if(member != null) {
			System.out.println("prehandle true");
			return true;
		}

		System.out.println("prehandle false");
		System.out.println(request.getContextPath());
		request.getRequestDispatcher("/home").forward(request, response);
		return false;
	}

}
