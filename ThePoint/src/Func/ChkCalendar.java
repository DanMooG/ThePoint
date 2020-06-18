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
	// ������Ʈ
	private JPanel panel; // Panel ����ؼ� ��ü�� ����
	private JButton btn_Home;
	private JLabel label, lbl_mon, lbl_tue, lbl_wed, lbl_thu, lbl_fri, lbl_sat, lbl_sun, lbl_back;
	private JButton days[];

	// ũ�� ����
	private int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private int width = x * 3 / 4, height = y * 4 / 5;
	private int day_W = (width - 80) / 7, back_H = (height - (80 + height / 15)) / 6, day_H = back_H - 50;

	// �� �ܿ�
	private String state[] = new String[31];

	// �۾�ü
	private Font font_L = new Font("���� ����", Font.BOLD, 40 + y / 250);
	private Font font_BM = new Font("���� ����", Font.BOLD, 30 + y / 250);
	private Font font_PS = new Font("���� ����", Font.PLAIN, 20 + y / 250);
	private Font font_PSS = new Font("���� ����", Font.PLAIN, 10 + y / 250);

	// �̹���
	private ImageIcon BarIcon = new ImageIcon("images/icon.png");

	/* Home ��ư */
	private ImageIcon btnHome1 = new ImageIcon("images/button/btnhome.png");
	private Image imgbtnHome = btnHome1.getImage();
	private Image imgbtnHome2 = imgbtnHome.getScaledInstance(height / 15 - 5, height / 15 - 5, Image.SCALE_SMOOTH);
	private ImageIcon btnHome = new ImageIcon(imgbtnHome2);

	private ImageIcon btnStruct_push1 = new ImageIcon("images/pushedbutton/btnstruct_push.png");
	private Image imgbtnStruct_push = btnStruct_push1.getImage();
	private Image imgbtnStruct_push2 = imgbtnStruct_push.getScaledInstance(height / 15 - 5, height / 15 - 5,
			Image.SCALE_SMOOTH);
	private ImageIcon btnStruct_push = new ImageIcon(imgbtnStruct_push2);

	/* �� */
	private ImageIcon mon1 = new ImageIcon("images/mon.png");
	private Image imgmon = mon1.getImage();
	private Image imgmon2 = imgmon.getScaledInstance(day_W, day_H, Image.SCALE_SMOOTH);
	private ImageIcon mon = new ImageIcon(imgmon2);

	/* ȭ */
	private ImageIcon tue1 = new ImageIcon("images/tue.png");
	private Image imgtue = tue1.getImage();
	private Image imgtue2 = imgtue.getScaledInstance(day_W, day_H, Image.SCALE_SMOOTH);
	private ImageIcon tue = new ImageIcon(imgtue2);

	/* �� */
	private ImageIcon wed1 = new ImageIcon("images/wed.png");
	private Image imgwed = wed1.getImage();
	private Image imgwed2 = imgwed.getScaledInstance(day_W, day_H, Image.SCALE_SMOOTH);
	private ImageIcon wed = new ImageIcon(imgwed2);

	/* �� */
	private ImageIcon thu1 = new ImageIcon("images/thu.png");
	private Image imgthu = thu1.getImage();
	private Image imgthu2 = imgthu.getScaledInstance(day_W, day_H, Image.SCALE_SMOOTH);
	private ImageIcon thu = new ImageIcon(imgthu2);

	/* �� */
	private ImageIcon fri1 = new ImageIcon("images/fri.png");
	private Image imgfri = fri1.getImage();
	private Image imgfri2 = imgfri.getScaledInstance(day_W, day_H, Image.SCALE_SMOOTH);
	private ImageIcon fri = new ImageIcon(imgfri2);

	/* �� */
	private ImageIcon sat1 = new ImageIcon("images/sat.png");
	private Image imgsat = sat1.getImage();
	private Image imgsat2 = imgsat.getScaledInstance(day_W, day_H, Image.SCALE_SMOOTH);
	private ImageIcon sat = new ImageIcon(imgsat2);

	/* �� */
	private ImageIcon sun1 = new ImageIcon("images/sun.png");
	private Image imgsun = sun1.getImage();
	private Image imgsun2 = imgsun.getScaledInstance(day_W, day_H, Image.SCALE_SMOOTH);
	private ImageIcon sun = new ImageIcon(imgsun2);

	/* �� ��� */
	private ImageIcon back1 = new ImageIcon("images/back.png");
	private Image imgback = back1.getImage();
	private Image imgback2 = imgback.getScaledInstance(day_W, back_H, Image.SCALE_SMOOTH);
	private ImageIcon back = new ImageIcon(imgback2);

	/* NONE */
	private ImageIcon day_none1 = new ImageIcon("images/day/none.png");
	private Image imgday_none1 = day_none1.getImage();
	private Image imgday_none2 = imgday_none1.getScaledInstance(day_W, back_H, Image.SCALE_SMOOTH);
	private ImageIcon day_none = new ImageIcon(imgday_none2);

	/* �� O */
	private ImageIcon day_O1 = new ImageIcon("images/day/day_O.png");
	private Image imgday_O = day_O1.getImage();
	private Image imgday_O2 = imgday_O.getScaledInstance(day_W, back_H, Image.SCALE_SMOOTH);
	private ImageIcon day_O = new ImageIcon(imgday_O2);

	/* �� X */
	private ImageIcon day_X1 = new ImageIcon("images/day/day_X.png");
	private Image imgday_X = day_X1.getImage();
	private Image imgday_X2 = imgday_X.getScaledInstance(day_W, back_H, Image.SCALE_SMOOTH);
	private ImageIcon day_X = new ImageIcon(imgday_X2);

	private ImageIcon days_icon1[];
	private Image imgdays[];
	private Image imgdays2[];
	private ImageIcon days_icon[];

	/* DB ���� */
	private static M_Info_DAO mi_DAO = null;
	private static M_Info_DTO mi_DTO = null;
	private static Calendar_DAO c_DAO = null;
	private static Calendar_DTO[] c_DTO = null;

	/* ��¥ */
	private static int[] MAX_DAYS = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static int[] LEAP_MAX_DAYS = new int[] { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static Date today = new Date();
	int thisyear = today.getYear() + 1900;
	int thismonth = today.getMonth() + 1;

	//////////////////////////////////////////////////////////////////////////////////////

	public ChkCalendar() {
		setTitle("The Point"); // Ÿ��Ʋ�� �ؽ�Ʈ ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �̰� �س��� x������ �ƿ� ���α׷��� ���� ��
		setBounds((x - width) / 2, height / 10, width, height); // �տ� �� ���� ������ ���� ��ġ, �ڿ� �� ���� ������ ������!
		panel = new JPanel(); // ������ ������ ��ü �ʱ�ȭ
		panel.setBorder(new EmptyBorder(5, 5, 5, 5)); // �����¿� ��¦�� ���� �ִ°ž�
		panel.setLayout(new BorderLayout(0, 0)); // �ֶ� Layout �������ش�
		setResizable(false); // ��üȭ�� ���ϰ�
		setContentPane(panel); // MainFrame�� ���ݱ��� �������ذɷ� ������
		setIconImage(BarIcon.getImage()); // ���� ����̶� �۾� ǥ���� ������ ����
		panel.setBackground(new java.awt.Color(240, 240, 242)); // �ڿ� ��� ����
		setLayout(null); // ���� �ٽ� §��

		/* Home ��ư */
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

		/* �� �� �۱� */
		label = new JLabel(MakeTop());
		label.setFont(font_BM);
		label.setForeground(Color.WHITE);
		label.setBounds(0, 0, width - 5, height / 15);
		label.setOpaque(true);
		label.setBackground(new java.awt.Color(182, 121, 242)); // �ڿ� ���
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(label);

		/* �� */
		lbl_sun = new JLabel(sun);
		lbl_sun.setBounds(8, (label.getY() + label.getHeight()) + 18, day_W, day_H);
		lbl_sun.setVerticalAlignment(SwingConstants.CENTER);
		lbl_sun.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_sun);

		/* �� */
		lbl_mon = new JLabel(mon);
		lbl_mon.setBounds((lbl_sun.getX() + lbl_sun.getWidth()) + 10, lbl_sun.getY(), day_W, day_H);
		lbl_mon.setVerticalAlignment(SwingConstants.CENTER);
		lbl_mon.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_mon);

		/* ȭ */
		lbl_tue = new JLabel(tue);
		lbl_tue.setBounds((lbl_mon.getX() + lbl_mon.getWidth()) + 10, lbl_mon.getY(), day_W, day_H);
		lbl_tue.setVerticalAlignment(SwingConstants.CENTER);
		lbl_tue.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_tue);

		/* �� */
		lbl_wed = new JLabel(wed);
		lbl_wed.setBounds((lbl_tue.getX() + lbl_tue.getWidth()) + 10, lbl_tue.getY(), day_W, day_H);
		lbl_wed.setVerticalAlignment(SwingConstants.CENTER);
		lbl_wed.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_wed);

		/* �� */
		lbl_thu = new JLabel(thu);
		lbl_thu.setBounds((lbl_wed.getX() + lbl_wed.getWidth()) + 10, lbl_wed.getY(), day_W, day_H);
		lbl_thu.setVerticalAlignment(SwingConstants.CENTER);
		lbl_thu.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_thu);

		/* �� */
		lbl_fri = new JLabel(fri);
		lbl_fri.setBounds((lbl_thu.getX() + lbl_thu.getWidth()) + 10, lbl_thu.getY(), day_W, day_H);
		lbl_fri.setVerticalAlignment(SwingConstants.CENTER);
		lbl_fri.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_fri);

		/* �� */
		lbl_sat = new JLabel(sat);
		lbl_sat.setBounds((lbl_fri.getX() + lbl_fri.getWidth()) + 10, lbl_fri.getY(), day_W, day_H);
		lbl_sat.setVerticalAlignment(SwingConstants.CENTER);
		lbl_sat.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_sat);

		printCalendar(thisyear, thismonth);
	}

	// ��� ���� DB ���� �� ���
	public String MakeTop() {
		String result = "";

		// ���Ӱ� �ʱ�ȭ
		mi_DAO = new M_Info_DAO();
		mi_DTO = new M_Info_DTO();

		// dao���� ��� ���� ��������
		mi_DTO = mi_DAO.CheckInfo();

		result = mi_DTO.getM_Name() + "��! " + mi_DTO.getM_SKind() + " " + mi_DTO.getM_LKind() + " " + mi_DTO.getM_Goal()
				+ " ȭ����!";

		return result;
	}

	// ���� ���
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

		days = new JButton[35];
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

		// ���� ���� ���
		for (int i = 0; i < weekday; i++) {
			days[i] = new JButton(day_none);
			days[i].setBorderPainted(false);
			days[i].setFocusPainted(false);
			days[i].setContentAreaFilled(false);
			days[i].setBounds(lbl_sun.getX(), (lbl_sun.getY() + lbl_sun.getHeight()) + 10, day_W, back_H);
			add(days[i]);
		}

		// ù �� ���
		int count = 7 - weekday;
		int delim = count < 7 ? count : 0;
		state = c_DAO.GetState();
		for (int i = 1; i <= count; i++) {
			final int j = i-1;
			if(state[i-1].equals("N")) {
				days[i] = new JButton(days_icon[i - 1]);
			} else if(state[i-1].equals("O")) {
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
					state = c_DAO.GetState();
					
					if (state[j].equals("N")) {
						c_DAO.ChangeState(j+1, "O");
						days[j+1].setIcon(day_O);
					} else if (state[j].equals("O")) {
						c_DAO.ChangeState(j+1, "X");
						days[j+1].setIcon(day_X);
					} else {
						c_DAO.ChangeState(j+1, "N");
						days[j+1].setIcon(days_icon[j]);
					}
				}
			});
			add(days[i]);
		}
		count++;

		// ��°�ٺ��� ���
		for (int i = count; i < maxDay; i++) {
			final int j = i-1;
			int k = 1;
			if(state[i-1].equals("N")) {
				days[i] = new JButton(days_icon[i - 1]);
			} else if(state[i-1].equals("O")) {
				days[i] = new JButton(day_O);
			} else {
				days[i] = new JButton(day_X);
			}
			days[i].setBorderPainted(false);
			days[i].setFocusPainted(false);
			days[i].setContentAreaFilled(false);
			days[i].setBounds(days[i-7*k].getX(), days[i-7*k].getY()+(back_H+10)*k, day_W, back_H);
			days[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					state = c_DAO.GetState();
					
					if (state[j].equals("N")) {
						c_DAO.ChangeState(j+1, "O");
						days[j+1].setIcon(day_O);
					} else if (state[j].equals("O")) {
						c_DAO.ChangeState(j+1, "X");
						days[j+1].setIcon(day_X);
					} else {
						c_DAO.ChangeState(j+1, "N");
						days[j+1].setIcon(days_icon[j]);
					}
				}
			});
			add(days[i]);
			if (i % 7 == delim) {
				++k;
			}
		}
		// ���� ���� ���
		for (int i = maxDay; i < 35; i++) {
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
		date.set(Calendar.YEAR, year); // �⵵ ����
		date.set(Calendar.MONTH, month - 1); // �� ����
		date.set(Calendar.DATE, 1); // ���� 1�̷� ����
		int weekday = date.get(Calendar.DAY_OF_WEEK) - 1; // ���ϰ��� sun:1 ~ sat:7 �θ��� �ǹǷ� -1�� ����
		return weekday;
	}
	
	public void Reset() {
		printCalendar(thisyear, thismonth);
	}
}