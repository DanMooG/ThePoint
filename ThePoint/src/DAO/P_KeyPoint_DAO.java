package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.TableModel;

import DTO.P_KeyPoint_DTO;

public class P_KeyPoint_DAO {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/ThePoint";
	private static final String USER = "root";
	private static final String PASS = "hyun5292";

	// DB���� �޼���
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

	// �ش� ���� ���� ��������
	public int GetCntStructure(String str) {
		int result = 0;
		String sql = "";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "SELECT COUNT(p_Kind_Info) as cnt FROM p_keypoint WHERE " + str;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				result = rs.getInt("cnt");
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

	// ��� ���� ��������
	public String[] GetAllStructure(String str) {
		String[] result = null;
		String sql = "";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "SELECT p_Kind_Info FROM p_keypoint WHERE " + str;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			int i = 0;
			result = new String[GetCntStructure(str)];
			while (rs.next()) {
				result[i] = rs.getString("p_Kind_Info");
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

	// ������ ������ ��������
	public Vector getTable() {
		Vector result = new Vector();
		String sql = "";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "SELECT P_Kind1, P_Kind2, P_Kind3, P_Kind4, P_Kind5, P_Kind_Info FROM p_keypoint ORDER BY p_Kind1, p_Kind2, p_Kind3, p_Kind4, p_Kind5;";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			int i = 0;
			while (rs.next()) {
				Vector row = new Vector();
				if (rs.getInt("p_Kind1") != 0) {
					if (rs.getInt("p_Kind2") != 0) {
						if (rs.getInt("p_Kind3") != 0) {
							if (rs.getInt("p_Kind4") != 0) {
								if (rs.getInt("p_Kind5") != 0) {
									row.add("");
									row.add("");
									row.add("");
									row.add("");
									row.add(rs.getString("p_Kind_Info"));
								} else {
									row.add("");
									row.add("");
									row.add("");
									row.add(rs.getString("p_Kind_Info"));
									row.add("");
								}
							} else {
								row.add("");
								row.add("");
								row.add(rs.getString("p_Kind_Info"));
								row.add("");
								row.add("");
							}
						} else {
							row.add("");
							row.add(rs.getString("p_Kind_Info"));
							row.add("");
							row.add("");
							row.add("");
						}
					} else {
						row.add(rs.getString("p_Kind_Info"));
						row.add("");
						row.add("");
						row.add("");
						row.add("");
					}
				} else {
					break;
				}

				result.add(row);
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

	// �ش� ������ �˻�
	public P_KeyPoint_DTO SearchData(String str) {
		P_KeyPoint_DTO result = new P_KeyPoint_DTO();
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "SELECT P_Kind1, P_Kind2, P_Kind3, P_Kind4, P_Kind5, P_Kind_Info FROM p_keypoint where p_Kind_Info LIKE ? ;";

			pst = conn.prepareStatement(sql);
			pst.setString(1, str);
			rs = pst.executeQuery();

			int i = 1;
			if (rs.next()) {
				result.setP_Kind1(rs.getInt("p_Kind1"));
				result.setP_Kind2(rs.getInt("p_Kind2"));
				result.setP_Kind3(rs.getInt("p_Kind3"));
				result.setP_Kind4(rs.getInt("p_Kind4"));
				result.setP_Kind5(rs.getInt("p_Kind5"));
				result.setP_Kind_Info(rs.getString("p_Kind_Info"));
			} else {
				result.setP_Kind1(1);
				result.setP_Kind2(0);
				result.setP_Kind3(0);
				result.setP_Kind4(0);
				result.setP_Kind5(0);
				result.setP_Kind_Info(null);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (!conn.isClosed())
					conn.close();
				if (!pst.isClosed())
					pst.close();
				if (!rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		return result;
	}

	// ���ο� ������ �Է�
	public boolean addNew(int k1, int k2, int k3, int k4, int k5, String kind_point) {
		boolean result = false;
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;

		try {
			conn = getConn();
			sql = "insert into P_KeyPoint VALUES(?, ?, ?, ?, ?, ?, ?)";

			pst = conn.prepareStatement(sql);
			pst.setInt(1, k1);
			pst.setInt(2, k2);
			pst.setInt(3, k3);
			pst.setInt(4, k4);
			pst.setInt(5, k5);
			pst.setString(6, kind_point);
			pst.setString(7, null);
			rs = pst.executeUpdate();

			if (rs > 0) {
				result = true;
			} else {
				result = false;
			}
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

		return result;
	}

	// �ش� ���� ����
	public boolean ResetRow(int k1, int k2, int k3, int k4, int k5, String kind_point) {
		boolean result = false;
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;

		try {
			conn = getConn();
			sql = "UPDATE P_KeyPoint SET p_kind_info = ? WHERE p_kind1 = ? and p_kind2 = ? and p_kind3 = ? and p_kind4 = ? and p_kind5 = ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, kind_point);
			pst.setInt(2, k1);
			pst.setInt(3, k2);
			pst.setInt(4, k3);
			pst.setInt(5, k4);
			pst.setInt(6, k5);
			rs = pst.executeUpdate();

			if (rs > 0) {
				result = true;
			} else {
				result = false;
			}
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

		return result;
	}

	// �ش� ���� ����
	public boolean RemoveRow(int k1, int k2, int k3, int k4, int k5) {
		boolean result = false;
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;

		try {
			conn = getConn();
			sql = "delete from p_KeyPoint where p_kind1 like ? and p_kind2 like ? and p_kind3 like ? and p_kind4 like ? and p_kind5 like ?";

			pst = conn.prepareStatement(sql);
			pst.setInt(1, k1);
			pst.setInt(2, k2);
			pst.setInt(3, k3);
			pst.setInt(4, k4);
			pst.setInt(5, k5);
			rs = pst.executeUpdate();

			if (rs > 0) {
				result = true;
			} else {
				result = false;
			}
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

		return result;
	}

	// ���� ���� ��������
	// �ش� ������ �˻�
	public P_KeyPoint_DTO SearchPoint(P_KeyPoint_DTO dto) {
		P_KeyPoint_DTO result = dto;
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConn();
			sql = "SELECT p_Kind_Info, p_Point_Info FROM p_keypoint where p_Kind1 LIKE ? and p_Kind2 LIKE ? and p_Kind3 LIKE ? and p_Kind4 LIKE ? and p_Kind5 LIKE ?";

			pst = conn.prepareStatement(sql);
			pst.setInt(1, dto.getP_Kind1());
			pst.setInt(2, dto.getP_Kind2());
			pst.setInt(3, dto.getP_Kind3());
			pst.setInt(4, dto.getP_Kind4());
			pst.setInt(5, dto.getP_Kind5());
			rs = pst.executeQuery();

			int i = 1;
			if (rs.next()) {
				result.setP_Kind_Info(rs.getString("P_Kind_Info"));
				result.setP_Point_Info(rs.getString("P_Point_Info"));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (!conn.isClosed())
					conn.close();
				if (!pst.isClosed())
					pst.close();
				if (!rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		return result;
	}

	// ������ �ش� ���� �Է�
	public boolean InputPoint(P_KeyPoint_DTO dto, String input) {
		boolean result = false;
		String sql = "";
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;

		try {
			conn = getConn();
			sql = "UPDATE P_KeyPoint SET p_Point_info = ? WHERE p_kind1 = ? and p_kind2 = ? and p_kind3 = ? and p_kind4 = ? and p_kind5 = ?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, input);
			pst.setInt(2, dto.getP_Kind1());
			pst.setInt(3, dto.getP_Kind2());
			pst.setInt(4, dto.getP_Kind3());
			pst.setInt(5, dto.getP_Kind4());
			pst.setInt(6, dto.getP_Kind5());
			rs = pst.executeUpdate();

			if (rs > 0) {
				result = true;
			} else {
				result = false;
			}
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

		return result;
	}
}