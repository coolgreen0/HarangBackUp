package bamboo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.BambooDTO;
import dto.DlikeDTO;
import dto.LikeDTO;
import dto.MemberDTO;
import harang.dbcp.DBConnectionMgr;

public class U_Bb_Content_Command implements CommandInterface {

	private Connection con;
	private PreparedStatement pstmt;
	private DBConnectionMgr pool;
	private ResultSet rs;

	public U_Bb_Content_Command() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception err) {
			System.out.println("DBCP 연결실패 : " + err);
		}
	}

	public String processCommand(HttpServletRequest req, HttpServletResponse resp) {

		//HttpSession session = req.getSession();
		//MemberDTO mdto = (MemberDTO) session.getAttribute("member");
		//String m_id = mdto.getM_id();
		
		BambooDTO bbdto = null;
		
		String bb_num = req.getParameter("bb_num");
		//System.out.println("U_Bb_Content 에서 테스트 bb_num : " + bb_num);

		String sql = null;
		
		
		
		try {
			con = pool.getConnection();

			sql = "select * from tbl_bamboo where bb_num =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bb_num);
			rs = pstmt.executeQuery();
			
			// 본문 불러오기
			if (rs.next()) {
				bbdto = new BambooDTO(); 
				bbdto.setBb_num(rs.getString("bb_num"));
				bbdto.setM_id(rs.getString("m_id"));
				bbdto.setBb_notice(rs.getString("bb_notice"));
				bbdto.setBb_title(rs.getString("bb_title"));
				bbdto.setBb_content(rs.getString("bb_content"));
				bbdto.setBb_regdate(rs.getDate("bb_regdate"));
				bbdto.setBb_count(rs.getInt("bb_count"));
				bbdto.setBb_nickname(rs.getString("bb_nickname"));

				// System.out.println(rs.getString("b_content"));

			}
			
			sql = "select * from tbl_bbreply where bb_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bb_num);
			rs = pstmt.executeQuery();
			
			ArrayList bbrlist = new ArrayList();
			
			
			
			
			
		} catch (Exception err) {
			System.out.println("U_Bb_Content 에서 에러 ");
			err.printStackTrace();

		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		req.setAttribute("bbcon", bbdto);
		
		Bb_List_Command bb = new Bb_List_Command();
		req.setAttribute("bblist", bb.bblist(req));
		
		U_Br_List_Command br = new U_Br_List_Command();
		req.setAttribute("brlist", br.brlist(req));
		
		req.setAttribute("bblcnt", Bb_Like_Cnt(req));
		req.setAttribute("bbdlcnt", Bb_Dlike_Cnt(req));
		
		return "/WEB-INF/bamboo/u_bb_content.jsp";
	}
	
	public ArrayList Bb_Like_Cnt(HttpServletRequest req){
		
		HttpSession session = req.getSession();

		MemberDTO mdto = (MemberDTO) session.getAttribute("member");
		String m_id = mdto.getM_id();

		// System.out.println("Bb_List_Command에서 Test : " + m_id);

		ArrayList list = new ArrayList();
		try {
			con = pool.getConnection();

			String sql;
			String bb_num = req.getParameter("bb_num");

			sql = "SELECT * FROM harang.tbl_like where bb_num ='" +bb_num+"'";
			pstmt = con.prepareStatement(sql);
			//System.out.println("U_Br_List_Command에서 테스트. sql : " + sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				LikeDTO bldto = new LikeDTO();
				bldto.setM_id(rs.getString("m_id"));
				bldto.setBb_num(rs.getString("bb_num"));

				list.add(bldto);
				
				if(m_id.equals(rs.getString("m_id"))){
					
					req.setAttribute("islike", "y");
				}

			}

		} catch (

		Exception err) {
			System.out.println("Bb_Like_Cnt에서 오류");
			err.printStackTrace();

		} finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return list;
		
		
		
		
	}
	
	public ArrayList Bb_Dlike_Cnt(HttpServletRequest req){
		
		HttpSession session = req.getSession();
		
		MemberDTO mdto = (MemberDTO) session.getAttribute("member");
		String m_id = mdto.getM_id();
		
		// System.out.println("Bb_List_Command에서 Test : " + m_id);
		
		ArrayList list = new ArrayList();
		try {
			con = pool.getConnection();
			
			String sql;
			String bb_num = req.getParameter("bb_num");
			
			sql = "SELECT * FROM harang.tbl_dlike where bb_num ='" +bb_num+"'";
			pstmt = con.prepareStatement(sql);
			//System.out.println("U_Br_List_Command에서 테스트. sql : " + sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				DlikeDTO bdldto = new DlikeDTO();
				bdldto.setM_id(rs.getString("m_id"));
				bdldto.setBb_num(rs.getString("bb_num"));
				
				list.add(bdldto);
				
				if(m_id.equals(rs.getString("m_id"))){
					
					req.setAttribute("isdlike", "y");
				}
				
			}
			
		} catch (
				
				Exception err) {
			System.out.println("Bb_Dlike_Cnt에서 오류");
			err.printStackTrace();
			
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return list;
		
		
		
		
	}
	
	
	
}
