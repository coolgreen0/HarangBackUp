package parttime.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DaetaReadCommand implements CommandInterface {
	public String processCommand(HttpServletRequest req, HttpServletResponse resp) {
		// Session에 저장된 로그인 정보를 받아온다.
		//String m_id = (String) req.getSession().getAttribute("m_id");
		//String d_num = (String)req.getParameter("d_num");
		/*
		if (m_id.equals("a")) {
			// 관리자면 a_daeta_read.jsp로 페이지 이동
			return "WEB-INF/parttime/a_daeta_read.jsp";
		} else {
			// 회원이면 daeta_read.jsp 로 페이지 이동
			// 글번호로 글쓴이를 조회하여 (sql문) 글쓴이와 로그인정보가 일치하면 daeta_read_my.jsp로 페이지 이동
			return "/WEB-INF/parttime/daeta_read.jsp";
		}
		*/
		return "/WEB-INF/parttime/daeta_read.jsp";
	}
}
