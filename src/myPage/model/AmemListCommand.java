package myPage.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AmemListCommand implements CommandInterface {

	@Override
	public Object processCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return "/WEB-INF/myPage/a_memList.jsp";
	}

}