package Func;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import DAO.M_Info_DAO;
import DTO.M_Info_DTO;

public class MainScreen extends JFrame {
	// 컴포넌트
	private JPanel panel; // Panel 등록해서 객체로 쓰고
	private JButton btn_Struct, btn_Point, btn_Calendar, btn_Info, btn_File;
	private JLabel label;

	// 크기 조절
	private int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private int width = x * 3 / 4, height = y * 4 / 5;
	private int btn_W = (width - 45) / 3, btn_H = height - (height / 15 + 60),
			btn_SH = (height - (height / 15 + 80)) / 3;

	// 글씨체
	private Font font_L = new Font("맑은 고딕", Font.BOLD, 40 + y / 250);
	private Font font_BM = new Font("맑은 고딕", Font.BOLD, 30 + y / 250);
	private Font font_PS = new Font("맑은 고딕", Font.PLAIN, 20 + y / 250);
	private Font font_PSS = new Font("맑은 고딕", Font.PLAIN, 10 + y / 250);

	// 이미지
	private ImageIcon BarIcon = new ImageIcon("images/icon.png");

	/* 목차 버튼 */
	private ImageIcon btnStruct1 = new ImageIcon("images/button/btnstruct.png");
	private Image imgbtnStruct = btnStruct1.getImage();
	private Image imgbtnStruct2 = imgbtnStruct.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnStruct = new ImageIcon(imgbtnStruct2);

	private ImageIcon btnStruct_push1 = new ImageIcon("images/pushedbutton/btnstruct_push.png");
	private Image imgbtnStruct_push = btnStruct_push1.getImage();
	private Image imgbtnStruct_push2 = imgbtnStruct_push.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnStruct_push = new ImageIcon(imgbtnStruct_push2);

	/* 핵심 버튼 */
	private ImageIcon btnPoint1 = new ImageIcon("images/button/btnpoint.png");
	private Image imgbtnPoint = btnPoint1.getImage();
	private Image imgbtnPoint2 = imgbtnPoint.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnPoint = new ImageIcon(imgbtnPoint2);

	private ImageIcon btnPoint_push1 = new ImageIcon("images/pushedbutton/btnpoint_push.png");
	private Image imgbtnPoint_push = btnPoint_push1.getImage();
	private Image imgbtnPoint_push2 = imgbtnPoint_push.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnPoint_push = new ImageIcon(imgbtnPoint_push2);

	/* 캘린더 버튼 */
	private ImageIcon btnCalendar1 = new ImageIcon("images/button/btncalendar.png");
	private Image imgbtnCalendar = btnCalendar1.getImage();
	private Image imgbtnCalendar2 = imgbtnCalendar.getScaledInstance(btn_W, btn_SH, Image.SCALE_SMOOTH);
	private ImageIcon btnCalendar = new ImageIcon(imgbtnCalendar2);

	private ImageIcon btnCalendar_push1 = new ImageIcon("images/pushedbutton/btncalendar_push.png");
	private Image imgbtnCalendar_push = btnCalendar_push1.getImage();
	private Image imgbtnCalendar_push2 = imgbtnCalendar_push.getScaledInstance(btn_W, btn_SH, Image.SCALE_SMOOTH);
	private ImageIcon btnCalendar_push = new ImageIcon(imgbtnCalendar_push2);

	/* 정보수정 버튼 */
	private ImageIcon btnInfo1 = new ImageIcon("images/button/btninfo.png");
	private Image imgbtnInfo = btnInfo1.getImage();
	private Image imgbtnInfo2 = imgbtnInfo.getScaledInstance(btn_W, btn_SH, Image.SCALE_SMOOTH);
	private ImageIcon btnInfo = new ImageIcon(imgbtnInfo2);

	private ImageIcon btnInfo_push1 = new ImageIcon("images/pushedbutton/btninfo_push.png");
	private Image imgbtnInfo_push = btnInfo_push1.getImage();
	private Image imgbtnInfo_push2 = imgbtnInfo_push.getScaledInstance(btn_W, btn_SH, Image.SCALE_SMOOTH);
	private ImageIcon btnInfo_push = new ImageIcon(imgbtnInfo_push2);

	/* 파일 버튼 */
	private ImageIcon btnFile1 = new ImageIcon("images/button/btnfile.png");
	private Image imgbtnFile = btnFile1.getImage();
	private Image imgbtnFile2 = imgbtnFile.getScaledInstance(btn_W, btn_SH, Image.SCALE_SMOOTH);
	private ImageIcon btnFile = new ImageIcon(imgbtnFile2);

	private ImageIcon btnFile_push1 = new ImageIcon("images/pushedbutton/btnfile_push.png");
	private Image imgbtnFile_push = btnFile_push1.getImage();
	private Image imgbtnFile_push2 = imgbtnFile_push.getScaledInstance(btn_W, btn_SH, Image.SCALE_SMOOTH);
	private ImageIcon btnFile_push = new ImageIcon(imgbtnFile_push2);

	/* DB 변수 */
	private static M_Info_DAO mi_DAO = null;
	private static M_Info_DTO mi_DTO = null;

	//////////////////////////////////////////////////////////////////////////////////////

	public MainScreen() {
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

		/* 목차 버튼 */
		btn_Struct = new JButton(btnStruct);
		btn_Struct.setSelectedIcon(btnStruct_push);
		btn_Struct.setRolloverIcon(btnStruct_push);
		btn_Struct.setBorderPainted(false);
		btn_Struct.setFocusPainted(false);
		btn_Struct.setContentAreaFilled(false);
		btn_Struct.setBounds(10, label.getHeight() + 10, btn_W, btn_H);
		btn_Struct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Structure st = new Structure();
				st.setVisible(true);
				dispose();
			}
		});

		panel.add(btn_Struct);

		/* 핵심 버튼 */
		btn_Point = new JButton(btnPoint);
		btn_Point.setSelectedIcon(btnPoint_push);
		btn_Point.setRolloverIcon(btnPoint_push);
		btn_Point.setBorderPainted(false);
		btn_Point.setFocusPainted(false);
		btn_Point.setContentAreaFilled(false);
		btn_Point.setBounds((btn_Struct.getX() + btn_Struct.getWidth()) + 10, label.getHeight() + 10, btn_W, btn_H);
		btn_Point.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KeyPoint kp = new KeyPoint();
				kp.setVisible(true);
				dispose();
			}
		});

		panel.add(btn_Point);

		/* 캘린더 버튼 */
		btn_Calendar = new JButton(btnCalendar);
		btn_Calendar.setSelectedIcon(btnCalendar_push);
		btn_Calendar.setRolloverIcon(btnCalendar_push);
		btn_Calendar.setBorderPainted(false);
		btn_Calendar.setFocusPainted(false);
		btn_Calendar.setContentAreaFilled(false);
		btn_Calendar.setBounds((btn_Point.getX() + btn_Point.getWidth()) + 10, label.getHeight() + 10, btn_W, btn_SH);
		btn_Calendar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChkCalendar cc = new ChkCalendar();
				cc.setVisible(true);
				dispose();
			}
		});

		panel.add(btn_Calendar);

		/* 정보수정 버튼 */
		btn_Info = new JButton(btnInfo);
		btn_Info.setSelectedIcon(btnInfo_push);
		btn_Info.setRolloverIcon(btnInfo_push);
		btn_Info.setBorderPainted(false);
		btn_Info.setFocusPainted(false);
		btn_Info.setContentAreaFilled(false);
		btn_Info.setBounds(btn_Calendar.getX(), (btn_Calendar.getY() + btn_Calendar.getHeight()) + 10, btn_W, btn_SH);
		btn_Info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ModInfo mi = new ModInfo();
				mi.setVisible(true);
				dispose();
			}
		});

		panel.add(btn_Info);

		/* 파일 버튼 */
		btn_File = new JButton(btnFile);
		btn_File.setSelectedIcon(btnFile_push);
		btn_File.setRolloverIcon(btnFile_push);
		btn_File.setBorderPainted(false);
		btn_File.setFocusPainted(false);
		btn_File.setContentAreaFilled(false);
		btn_File.setBounds(btn_Info.getX(), (btn_Info.getY() + btn_Info.getHeight()) + 10, btn_W, btn_SH);
		btn_File.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ToFile tf = new ToFile();
				tf.setVisible(true);
				dispose();
			}
		});

		panel.add(btn_File);
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
}
