package DTO;

import java.sql.Blob;

public class P_KeyPoint_DTO {
	private int p_Kind1 = 0;
	private int p_Kind2 = 0;
	private int p_Kind3 = 0;
	private int p_Kind4 = 0;
	private int p_Kind5 = 0;
	private String p_Kind_Info = "";
	private String p_Point_Info = null;
	
	public P_KeyPoint_DTO() {}
	
	public P_KeyPoint_DTO(int p_Kind1, int p_Kind2, int p_Kind3, int p_Kind4, int p_Kind5, String p_Kind_Info, String p_Point_Info) {
		super();
		this.p_Kind1 = p_Kind1;
		this.p_Kind2 = p_Kind2;
		this.p_Kind3 = p_Kind3;
		this.p_Kind4 = p_Kind4;
		this.p_Kind5 = p_Kind5;
		this.p_Kind_Info = p_Kind_Info;
		this.p_Point_Info = p_Point_Info;
	}

	public int getP_Kind1() {
		return p_Kind1;
	}

	public void setP_Kind1(int p_Kind1) {
		this.p_Kind1 = p_Kind1;
	}

	public int getP_Kind2() {
		return p_Kind2;
	}

	public void setP_Kind2(int p_Kind2) {
		this.p_Kind2 = p_Kind2;
	}

	public int getP_Kind3() {
		return p_Kind3;
	}

	public void setP_Kind3(int p_Kind3) {
		this.p_Kind3 = p_Kind3;
	}

	public int getP_Kind4() {
		return p_Kind4;
	}

	public void setP_Kind4(int p_Kind4) {
		this.p_Kind4 = p_Kind4;
	}

	public int getP_Kind5() {
		return p_Kind5;
	}

	public void setP_Kind5(int p_Kind5) {
		this.p_Kind5 = p_Kind5;
	}
	
	public String getP_Kind_Info() {
		return p_Kind_Info;
	}

	public void setP_Kind_Info(String p_Kind_Info) {
		this.p_Kind_Info = p_Kind_Info;
	}
	
	public String getP_Point_Info() {
		return p_Point_Info;
	}

	public void setP_Point_Info(String p_Point_Info) {
		if(p_Point_Info == null) {
			
		} else {
			this.p_Point_Info = p_Point_Info;
		}
	}
	
	
}
