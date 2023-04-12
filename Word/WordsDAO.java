package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

public class WordsDAO {
	String url;
	String user;
	String pass;

	// DB연결
	public WordsDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@localhost:XE";
			user = "javase";
			pass = "1234";
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	// 단어 입력
	public void WordInsert(Words words) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "INSERT INTO word VALUES (word_seq.nextval,?,?,)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, words.getEnglish());
			pstmt.setString(2, words.getKorea());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 전체보기
	public ArrayList<Words> WordView() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Words> wArr = new ArrayList<Words>();
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT * FROM word ORDER BY num desc";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Words words = new Words();
				words.setNum(rs.getInt("num"));
				words.setEnglish(rs.getString("eng"));
				words.setKorea(rs.getString("kor"));
				wArr.add(words);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wArr;
	}

	// 단어 수정
	public void WordUpdate(Words words) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE word SET eng=?, kor=? WHERE num=?";
		try {
			con = DriverManager.getConnection(url, user, pass);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, words.getEnglish());
			pstmt.setString(2, words.getKorea());
			pstmt.setInt(3, words.getNum());
			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 단어 삭제 하기
	public void WordDelete(int num) {
		Connection con = null;
		Statement st = null;
		try {
			String sql = "DELETE FROM word WHERE num=" + num;
			con = DriverManager.getConnection(url, user, pass);
			st = con.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 단어 검색하기
	public ArrayList<Words> WordSearch(String key, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Words> wArr = new ArrayList<Words>();
		try {
			con = DriverManager.getConnection(url, user, pass);
			st = con.createStatement();
			String sql = "SELECT * FROM WHERE " + key + " LIKE '%" + word + "%'";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Words words = new Words();
				words.setNum(rs.getInt("num"));
				words.setEnglish(rs.getString("eng"));
				words.setKorea(rs.getString("kor"));
				wArr.add(words);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wArr;
	}

	// 단어 추가시 dB중복 여부 조회
	public int SearchWord(String enword) {
		String str = "영어";
		Connection con = null;
		PreparedStatement pstmt = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT eng FROM word WHERE eng=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, enword);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				str = rs.getString("eng");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (str.equals(enword) == false) {
			return 1;
		} else {

			return 0;
		}
	}
	//닫기 종료
	

}
