package bamboo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.MemberDTO;
import harang.dbcp.DBConnectionMgr;

public class U_Bb_Reply_Command implements CommandInterface {

	private Connection con;
	private PreparedStatement pstmt;
	private DBConnectionMgr pool;

	public U_Bb_Reply_Command() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception err) {
			System.out.println("DBCP 연결실패 : " + err);
		}
	}

	public String processCommand(HttpServletRequest req, HttpServletResponse resp) {


		


		try {
			HttpSession session = req.getSession();
			MemberDTO mdto = (MemberDTO) session.getAttribute("member");
			con = pool.getConnection();

			//System.out.println(m_id);

			String sql = "insert into tbl_bbreply (br_coment, br_nickname, bb_num, m_id) values (?,?,?,?)";

			//System.out.println(sql);

			String br_coment = (String) req.getParameter("br_coment");
			String br_nickname = (String) req.getParameter("br_nickname");
			String bb_num = req.getParameter("bb_num");
			String m_id = mdto.getM_id();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, br_coment);
			pstmt.setString(2, br_nickname);
			pstmt.setString(3, bb_num);
			pstmt.setString(4, m_id);

			pstmt.executeUpdate();

		} catch (Exception err) {
			System.out.println("U_Bb_Post_Complete에서 에러");
			err.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);

		}

		
		U_Bb_Content_Command goback = new U_Bb_Content_Command();
		String url = goback.processCommand(req, resp);

		return url;
	}
}
