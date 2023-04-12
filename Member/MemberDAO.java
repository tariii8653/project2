package project;

import java.awt.image.DataBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicStampedReference;

public class MemberDAO {
	String url;
	String user;
	String pass;

	// DB연결
	public MemberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:orcle:thin:@localhost:1521:XE";
			user = "javase";
			pass = "1234";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void memberInsert(Member member) { // 회원가입-점수는 0으로 설정을 한다.
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "INSERT INTO member VALUES (member_seq.nextval,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getId());
			pstmt.setString(3, member.getPw());
			pstmt.setString(4, member.getEmail());
			pstmt.setInt(5, 0);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 아이디 중복확인
	public boolean IdCheck(String id) {
		boolean result = true;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT * FROM member WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id.trim());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = false; // 레코드가 존재하면 false이다.
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	// 로그인 하면 로그인 id와 pass가 맞는지 체크 확인을 해야한다.
	public int LoginCheck(String id, String pss) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DriverManager.getConnection(url, user, pass);
			st = con.createStatement();
			String sql = "Select * from member where id='" + id + "'";
			rs = st.executeQuery(sql);
			if (rs.next() == false || (id.isEmpty() == true)) { // id거 존재하지 않는다.
				result = 1;
			} else { // id가 존재한다.
				sql = "select * from (select * from member where id='" + id + "')";
				rs = st.executeQuery(sql);
				while (rs.next() == true) { // 다음 값이다.
					if (rs.getString(4).equals(pass)) { // member DB의 4번째 pass와 같은지 비교한다
						result = 0; // 같으면 로그인 성공
					} else {
						result = 1; // 아이디는 같고 pass가 다른경우
					}

				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	// 회원 수정
	public void MemberUPdate(Member member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE member SET name=?, id=?, pass=?, score=?  WHERE num=?";
		try {
			con = DriverManager.getConnection(url, user, pass);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPw());
			pstmt.setInt(4, member.getNum());
		
			pstmt.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 회원 검색
	public ArrayList<Member> MemberSearch(String key, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Member> mArr = new ArrayList<Member>();
		String sql;
		try {
			con = DriverManager.getConnection(url, user, pass);
			st = con.createStatement();
			sql = "SELECT * FROM member WHERE" + key + "LIKE '%" + word + "%'";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Member member = new Member();
				member.setNum(rs.getInt("num"));
				member.setName(rs.getString("name"));
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pass"));
		
				mArr.add(member);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mArr;
	}

	// 회원 삭제
	public void MemberDelete(int num) {
		Connection con = null;
		Statement st = null;
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "DELETE FROM member WHERE num=" + num;
			st = con.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 닫기 종료 (학원에서 하기)
	// public void rele
}
