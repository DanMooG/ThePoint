package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import DTO.Calendar_DTO;

public class Calendar_DAO {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/ThePoint";
	private static final String USER = "root";
	private static final String PASS = "hyun5292";

	// DB연결 메서드
	public Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// DB에 저장되어 있는 현재 상태 불러오기
	public String[] GetState() {
		String[] result = new String[31];
		String sql = "";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "SELECT c_State FROM calendar;";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			int i = 0;
			while (rs.next()) {
				result[i] = rs.getString("c_State");
				++i;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (!conn.isClosed())
					conn.close();
				if (!stmt.isClosed())
					stmt.close();
				if (!rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		return result;
	}

	// 해당 날짜 상태 변경
	public void ChangeState(int c_day, String state) {
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;

		try {
			conn = getConn();
			sql = "UPDATE calendar SET c_State = ? WHERE c_Day = ?;";
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, state);
			pst.setInt(2, c_day);
			rs = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (!conn.isClosed())
					conn.close();
				if (!pst.isClosed())
					pst.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
