package Func;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import DAO.Calendar_DAO;
import DAO.M_Info_DAO;
import DTO.Calendar_DTO;
import DTO.M_Info_DTO;

public class ChkCalendar extends JFrame {
	// 컴포넌트
	private JPanel panel; // Panel 등록해서 객체로 쓰고
	private JButton btn_Home;
	private JLabel label, lbl_mon, lbl_tue, lbl_wed, lbl_thu, lbl_fri, lbl_sat, lbl_sun, lbl_back;
	private JButton days[];

	// 크기 조절
	private int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private int width = x * 3 / 4, height = y * 4 / 5;
	private int day_W = (width - 80) / 7, back_H = ((height - (80 + height / 15)) / 6)*5/6, day_H = back_H*6/5 - 50;

	// 그 외에
	private String state[] = new String[31];

	// 글씨체
	private Font font_L = new Font("맑은 고딕", Font.BOLD, 40 + y / 250);
	private Font font_BM = new Font("맑은 고딕", Font.BOLD, 30 + y / 250);
	private Font font_PS = new Font("맑은 고딕", Font.PLAIN, 20 + y / 250);
	private Font font_PSS = new Font("맑은 고딕", Font.PLAIN, 10 + y / 250);

	// 이미지
	private ImageIcon BarIcon = new ImageIcon("./images/icon.png");

	/* Home 버튼 */
	private ImageIcon btnHome1 = new ImageIcon("./images/button/btnhome.png");
	private Image imgbtnHome = btnHome1.getImage();
	private Image imgbtnHome2 = imgbtnHome.getScaledInstance(height / 15 - 5, height / 15 - 5, Image.SCALE_SMOOTH);
	private ImageIcon btnHome = new ImageIcon(imgbtnHome2);

	private ImageIcon btnStruct_push1 = new ImageIcon("./images/pushedbutton/btnstruct_push.png");
	private Image imgbtnStruct_push = btnStruct_push1.getImage();
	private Image imgbtnStruct_push2 = imgbtnStruct_push.getScaledInstance(height / 15 - 5, height / 15 - 5,
			Image.SCALE_SMOOTH);
	private ImageIcon btnStruct_push = new ImageIcon(imgbtnStruct_push2);

	/* 월 */
	private ImageIcon mon1 = new ImageIcon("./images/mon.png");
	private Image imgmon = mon1.getImage();
	private Image imgmon2 = imgmon.getScaledInstance(day_W, day_H, Image.SCALE_SMOOTH);
	private ImageIcon mon = new ImageIcon(imgmon2);

	/* 화 */
	private ImageIcon tue1 = new ImageIcon("./images/tue.png");
	private Image imgtue = tue1.getImage();
	private Image imgtue2 = imgtue.getScaledInstance(day_W, day_H, Image.SCALE_SMOOTH);
	private ImageIcon tue = new ImageIcon(imgtue2);

	/* 수 */
	private ImageIcon wed1 = new ImageIcon("./images/wed.png");
	private Image imgwed = wed1.getImage();
	private Image imgwed2 = imgwed.getScaledInstance(day_W, day_H, Image.SCALE_SMOOTH);
	private ImageIcon wed = new ImageIcon(imgwed2);

	/* 목 */
	private ImageIcon thu1 = new ImageIcon("./images/thu.png");
	private Image imgthu = thu1.getImage();
	private Image imgthu2 = imgthu.getScaledInstance(day_W, day_H, Image.SCALE_SMOOTH);
	private ImageIcon thu = new ImageIcon(imgthu2);

	/* 금 */
	private ImageIcon fri1 = new ImageIcon("./images/fri.png");
	private Image imgfri = fri1.getImage();
	private Image imgfri2 = imgfri.getScaledInstance(day_W, day_H, Image.SCALE_SMOOTH);
	private ImageIcon fri = new ImageIcon(imgfri2);

	/* 토 */
	private ImageIcon sat1 = new ImageIcon("./images/sat.png");
	private Image imgsat = sat1.getImage();
	private Image imgsat2 = imgsat.getScaledInstance(day_W, day_H, Image.SCALE_SMOOTH);
	private ImageIcon sat = new ImageIcon(imgsat2);

	/* 일 */
	private ImageIcon sun1 = new ImageIcon("./images/sun.png");
	private Image imgsun = sun1.getImage();
	private Image imgsun2 = imgsun.getScaledInstance(day_W, day_H, Image.SCALE_SMOOTH);
	private ImageIcon sun = new ImageIcon(imgsun2);

	/* 일 배경 */
	private ImageIcon back1 = new ImageIcon("./images/back.png");
	private Image imgback = back1.getImage();
	private Image imgback2 = imgback.getScaledInstance(day_W, back_H, Image.SCALE_SMOOTH);
	private ImageIcon back = new ImageIcon(imgback2);

	/* NONE */
	private ImageIcon day_none1 = new ImageIcon("./images/day/none.png");
	private Image imgday_none1 = day_none1.getImage();
	private Image imgday_none2 = imgday_none1.getScaledInstance(day_W, back_H, Image.SCALE_SMOOTH);
	private ImageIcon day_none = new ImageIcon(imgday_none2);

	/* 일 O */
	private ImageIcon day_O1 = new ImageIcon("./images/day/day_O.png");
	private Image imgday_O = day_O1.getImage();
	private Image imgday_O2 = imgday_O.getScaledInstance(day_W, back_H, Image.SCALE_SMOOTH);
	private ImageIcon day_O = new ImageIcon(imgday_O2);

	/* 일 X */
	private ImageIcon day_X1 = new ImageIcon("./images/day/day_X.png");
	private Image imgday_X = day_X1.getImage();
	private Image imgday_X2 = imgday_X.getScaledInstance(day_W, back_H, Image.SCALE_SMOOTH);
	private ImageIcon day_X = new ImageIcon(imgday_X2);

	private ImageIcon days_icon1[];
	private Image imgdays[];
	private Image imgdays2[];
	private ImageIcon days_icon[];

	/* DB 변수 */
	private static M_Info_DAO mi_DAO = null;
	private static M_Info_DTO mi_DTO = null;
	private static Calendar_DAO c_DAO = null;
	private static Calendar_DTO[] c_DTO = null;

	/* 날짜 */
	private static int[] MAX_DAYS = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static int[] LEAP_MAX_DAYS = new int[] { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static Date today = new Date();
	int thisyear = today.getYear() + 1900;
	int thismonth = 7 + 1;

	//////////////////////////////////////////////////////////////////////////////////////

	public ChkCalendar() {
		setTitle("The Point"); // 타이틀바 텍스트 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 이거 해놓고 x누르면 아예 프로그램이 종료 돼
		setBounds((x - width) / 2, height / 10, width, height); // 앞에 두 개는 프레임 시작 위치, 뒤에 두 개는 프레임 사이즈!
		panel = new JPanel(); // 위에서 선언한 객체 초기화
		panel.setBorder(new EmptyBorder(5, 5, 5, 5)); // 상하좌우 살짝씩 여유 주는거야
		panel.setLayout(new BorderLayout(0, 0)); // 멀라 Layout 설정해준대
		setResizable(false); // 전체화면 못하게
		setContentPane(panel); // MainFrame을 지금까지 설정해준걸로 세팅해
		setIconImage(BarIcon.getImage()); // 좌측 상단이랑 작업 표시줄 아이콘 설정
		panel.setBackground(new java.awt.Color(240, 240, 242)); // 뒤에 배경 블랙
		setLayout(null); // 판을 다시 짠다

		/* Home 버튼 */
		btn_Home = new JButton(btnHome);
		btn_Home.setBorderPainted(false);
		btn_Home.setFocusPainted(false);
		btn_Home.setContentAreaFilled(false);
		btn_Home.setBounds(width - (height / 15 - 5 + 10), 3, height / 15 - 5, height / 15 - 5);
		btn_Home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainScreen ms = new MainScreen();
				ms.setVisible(true);
				dispose();
			}
		});

		panel.add(btn_Home);

		/* 맨 위 글귀 */
		label = new JLabel(MakeTop());
		label.setFont(font_BM);
		label.setForeground(Color.WHITE);
		label.setBounds(0, 0, width - 5, height / 15);
		label.setOpaque(true);
		label.setBackground(new java.awt.Color(182, 121, 242)); // 뒤에 배경
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(label);

		/* 일 */
		lbl_sun = new JLabel(sun);
		lbl_sun.setBounds(8, (label.getY() + label.getHeight()) + 18, day_W, day_H);
		lbl_sun.setVerticalAlignment(SwingConstants.CENTER);
		lbl_sun.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_sun);

		/* 월 */
		lbl_mon = new JLabel(mon);
		lbl_mon.setBounds((lbl_sun.getX() + lbl_sun.getWidth()) + 10, lbl_sun.getY(), day_W, day_H);
		lbl_mon.setVerticalAlignment(SwingConstants.CENTER);
		lbl_mon.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_mon);

		/* 화 */
		lbl_tue = new JLabel(tue);
		lbl_tue.setBounds((lbl_mon.getX() + lbl_mon.getWidth()) + 10, lbl_mon.getY(), day_W, day_H);
		lbl_tue.setVerticalAlignment(SwingConstants.CENTER);
		lbl_tue.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_tue);

		/* 수 */
		lbl_wed = new JLabel(wed);
		lbl_wed.setBounds((lbl_tue.getX() + lbl_tue.getWidth()) + 10, lbl_tue.getY(), day_W, day_H);
		lbl_wed.setVerticalAlignment(SwingConstants.CENTER);
		lbl_wed.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_wed);

		/* 목 */
		lbl_thu = new JLabel(thu);
		lbl_thu.setBounds((lbl_wed.getX() + lbl_wed.getWidth()) + 10, lbl_wed.getY(), day_W, day_H);
		lbl_thu.setVerticalAlignment(SwingConstants.CENTER);
		lbl_thu.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_thu);

		/* 금 */
		lbl_fri = new JLabel(fri);
		lbl_fri.setBounds((lbl_thu.getX() + lbl_thu.getWidth()) + 10, lbl_thu.getY(), day_W, day_H);
		lbl_fri.setVerticalAlignment(SwingConstants.CENTER);
		lbl_fri.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_fri);

		/* 토 */
		lbl_sat = new JLabel(sat);
		lbl_sat.setBounds((lbl_fri.getX() + lbl_fri.getWidth()) + 10, lbl_fri.getY(), day_W, day_H);
		lbl_sat.setVerticalAlignment(SwingConstants.CENTER);
		lbl_sat.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_sat);

		printCalendar(thisyear, thismonth);
	}

	// 상단 문구 DB 연결 및 출력
	public String MakeTop() {
		String result = "";

		// 새롭게 초기화
		mi_DAO = new M_Info_DAO();
		mi_DTO = new M_Info_DTO();

		// dao에서 멤버 정보 가져오기
		mi_DTO = mi_DAO.CheckInfo();

		result = mi_DTO.getM_Name() + "님! " + mi_DTO.getM_SKind() + " " + mi_DTO.getM_LKind() + " " + mi_DTO.getM_Goal()
				+ " 화이팅!";

		return result;
	}

	// 윤년 계산
	public boolean isLeapYear(int year) {
		if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
			return true;
		}
		return false;
	}

	public int getMaxDayOfMonth(int year, int month) {
		if (isLeapYear(year)) {
			return LEAP_MAX_DAYS[month - 1];
		} else {
			return MAX_DAYS[month - 1];
		}
	}

	public void printCalendar(int year, int month) {
		// get weekday automatically
		int weekday = getWeekDay(year, month);
		int maxDay = getMaxDayOfMonth(year, month);
		c_DAO = new Calendar_DAO();

		days = new JButton[42];
		days_icon1 = new ImageIcon[maxDay];
		imgdays = new Image[maxDay];
		imgdays2 = new Image[maxDay];
		days_icon = new ImageIcon[maxDay];
		int n = 0;
		for (int i = 0; i < maxDay; i++) {
			n = i + 1;
			days_icon1[i] = new ImageIcon("images/day/d" + n + ".png");
			imgdays[i] = days_icon1[i].getImage();
			imgdays2[i] = imgdays[i].getScaledInstance(day_W, back_H, Image.SCALE_SMOOTH);
			days_icon[i] = new ImageIcon(imgdays2[i]);
		}

		// 요일 공백 출력
		for (int i = 0; i < weekday; i++) {
			days[i] = new JButton(day_none);
			days[i].setBorderPainted(false);
			days[i].setFocusPainted(false);
			days[i].setContentAreaFilled(false);
			if(i == 0) {
				days[i].setBounds(lbl_sun.getX(), (lbl_sun.getY() + lbl_sun.getHeight()) + 10, day_W, back_H);
			} else {
				days[i].setBounds((days[i - 1].getX() + days[i - 1].getWidth()) + 10, days[i - 1].getY(), day_W, back_H);
			}
			add(days[i]);
		}

		// 첫 줄 출력
		int icon_num = 0;
		int count = 7 - weekday;
		state = c_DAO.GetState();
		for (int i = weekday; i < 7; i++, icon_num++) {
			final int j = i-1;
			final int icn = icon_num;
			if(state[icon_num].equals("N")) {
				days[i] = new JButton(days_icon[icon_num]);
			} else if(state[icon_num].equals("O")) {
				days[i] = new JButton(day_O);
			} else {
				days[i] = new JButton(day_X);
			}
			days[i].setBorderPainted(false);
			days[i].setFocusPainted(false);
			days[i].setContentAreaFilled(false);
			days[i].setBounds((days[i - 1].getX() + days[i - 1].getWidth()) + 10, days[i - 1].getY(), day_W, back_H);
			days[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Reset();
					
					state = c_DAO.GetState();
					if (state[icn].equals("N")) {
						c_DAO.ChangeState((icn+1), "O");
						days[(j+1)].setIcon(day_O);
					} else if (state[icn].equals("O")) {
						c_DAO.ChangeState((icn+1), "X");
						days[(j+1)].setIcon(day_X);
					} else {
						c_DAO.ChangeState((icn+1), "N");
						days[(j+1)].setIcon(days_icon[icn]);
					}
				}
			});
			add(days[i]);
		}
		count++;

		final int cnt = count;
		// 둘째줄부터 출력
		for (int i = 7; i <= maxDay+(weekday-1); i++, icon_num++) {
			final int j = i-1;
			final int icn = icon_num;
			int k = 1;
			if(state[icon_num].equals("N")) {
				days[i] = new JButton(days_icon[icon_num]);
			} else if(state[icon_num].equals("O")) {
				days[i] = new JButton(day_O);
			} else {
				days[i] = new JButton(day_X);
			}
			days[i].setBorderPainted(false);
			days[i].setFocusPainted(false);
			days[i].setContentAreaFilled(false);
			days[i].setBounds(days[i-7].getX(), days[i-7].getY()+(back_H+10)*k, day_W, back_H);
			days[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Reset();
					
					state = c_DAO.GetState();
					if (state[icn].equals("N")) {
						c_DAO.ChangeState((icn+1), "O");
						days[(j+1)].setIcon(day_O);
					} else if (state[icn].equals("O")) {
						c_DAO.ChangeState((icn+1), "X");
						days[(j+1)].setIcon(day_X);
					} else {
						c_DAO.ChangeState((icn+1), "N");
						days[(j+1)].setIcon(days_icon[icn]);
					}
				}
			});
			add(days[i]);
			if (i % 7 == 6) {
				++k;
			}
		}
		
		// 요일 공백 출력
		for (int i = maxDay+weekday; i < 42; i++) {
			days[i] = new JButton(day_none);
			days[i].setBorderPainted(false);
			days[i].setFocusPainted(false);
			days[i].setContentAreaFilled(false);
			days[i].setBounds(days[i-7].getX(), days[i-7].getY()+(back_H+10), day_W, back_H);
			add(days[i]);
		}
	}

	private int getWeekDay(int year, int month) {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, year); // 년도 셋팅
		date.set(Calendar.MONTH, month - 1); // 월 셋팅
		date.set(Calendar.DATE, 1); // 시작 1이롤 셋팅
		int weekday = date.get(Calendar.DAY_OF_WEEK) - 1; // 리턴값이 sun:1 ~ sat:7 로리턴 되므로 -1을 해줌
		return weekday;
	}
	
	public void Reset() {
		for(int i = 0; i < days.length; i++)
			remove(days[i]);
		printCalendar(thisyear, thismonth);
	}
}
