package DTO;

public class Calendar_DTO {
	private int c_Day = 0;
	private String c_State = "";
	
	public Calendar_DTO() { }
	
	public Calendar_DTO(int c_Day, String c_State) {
		this.c_Day = c_Day;
		this.c_State = c_State;
	}

	public int getC_Day() {
		return c_Day;
	}

	public void setC_Day(int c_Day) {
		this.c_Day = c_Day;
	}

	public String getC_State() {
		return c_State;
	}

	public void setC_State(String c_State) {
		this.c_State = c_State;
	}
	
	
}
