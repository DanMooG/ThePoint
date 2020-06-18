package DTO;

public class M_Info_DTO {
	private String m_Name = "";
	private String m_Goal = "";
	private String m_LKind = "";
	private String m_SKind = "";
	private int m_StartYear = 0;
	private int m_StartMonth = 0;
	private int m_StartDate = 0;
	private int m_EndYear = 0;
	private int m_EndMonth = 0;
	private int m_EndDate = 0;
	private String m_Determin = "";
	
	public M_Info_DTO() {}
	
	public M_Info_DTO(String m_Name, String m_Goal, String m_LKind, String m_SKind, int m_StartYear, int m_StartMonth, int m_StartDate, int m_EndYear, int m_EndMonth, int m_EndDate, String m_Determin) {
		this.m_Name = m_Name;
		this.m_Goal = m_Goal;
		this.m_LKind = m_LKind;
		this.m_SKind = m_SKind;
		this.m_StartYear = m_StartYear;
		this.m_StartMonth = m_StartMonth;
		this.m_StartDate = m_StartDate;
		this.m_EndYear = m_EndYear;
		this.m_EndMonth = m_EndMonth;
		this.m_EndDate = m_EndDate;
		this.m_Determin = m_Determin;
	}
	
	public String getM_Name() {
		return m_Name;
	}
	public void setM_Name(String m_Name) {
		this.m_Name = m_Name;
	}
	public String getM_Goal() {
		return m_Goal;
	}
	public void setM_Goal(String m_Goal) {
		this.m_Goal = m_Goal;
	}
	public String getM_LKind() {
		return m_LKind;
	}
	public void setM_LKind(String m_LKind) {
		this.m_LKind = m_LKind;
	}
	public String getM_SKind() {
		return m_SKind;
	}
	public void setM_SKind(String m_SKind) {
		this.m_SKind = m_SKind;
	}
	public int getM_StartYear() {
		return m_StartYear;
	}
	public void setM_StartYear(int m_StartYear) {
		this.m_StartYear = m_StartYear;
	}
	public int getM_StartMonth() {
		return m_StartMonth;
	}
	public void setM_StartMonth(int m_StartMonth) {
		this.m_StartMonth = m_StartMonth;
	}
	public int getM_StartDate() {
		return m_StartDate;
	}
	public void setM_StartDate(int m_StartDate) {
		this.m_StartDate = m_StartDate;
	}
	public int getM_EndYear() {
		return m_EndYear;
	}
	public void setM_EndYear(int m_EndYear) {
		this.m_EndYear = m_EndYear;
	}
	public int getM_EndMonth() {
		return m_EndMonth;
	}
	public void setM_EndMonth(int m_EndMonth) {
		this.m_EndMonth = m_EndMonth;
	}
	public int getM_EndDate() {
		return m_EndDate;
	}
	public void setM_EndDate(int m_EndDate) {
		this.m_EndDate = m_EndDate;
	}
	public String getM_Determin() {
		return m_Determin;
	}
	public void setM_Determin(String m_Determin) {
		this.m_Determin = m_Determin;
	}
	
	
}
