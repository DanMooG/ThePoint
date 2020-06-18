package Func;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DAO.M_Info_DAO;
import DTO.M_Info_DTO;

public class FirstScreen extends JFrame {
	// 컴포넌트
	private JPanel MainFrame; // Panel 등록해서 객체로 쓰고
	private JLabel label, lbl_Mark, lbl_Name, lbl_Goal, lbl_LKind, lbl_SKind, lbl_Date, lbl_Determin;
	private JTextField txt_Name, txt_LKind, txt_SKind;
	private JButton btn_Start;
	private JComboBox comb_Goal, com_sYear, com_sMonth, com_sDate, com_eYear, com_eMonth, com_eDate;
	private JTextArea txt_Determin;

	// 크기 조절
	private int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private int width = x * 3 / 4, height = y * 4 / 5;
	private int btn_W = width / 6, btn_H = height / 10;

	// 그 외에 변수
	String[] goal = new String[] { "합격", "취득", "통과", "패스", "급제", "장원" };
	private Calendar c = Calendar.getInstance(); // 날짜
	private Calendar c_s = Calendar.getInstance(); // 시작날짜
	private Calendar c_e = Calendar.getInstance(); // 끝날짜
	String[] year = new String[11];
	String[] month = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
	String[] date = new String[] { "31", "29", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31" };
	int thisyear = c.get(Calendar.YEAR);
	int thismonth = c.get(Calendar.MONTH) + 1;
	int thisdate = c.get(Calendar.DATE);

	// 글씨체
	private Font font_L = new Font("맑은 고딕", Font.BOLD, 40 + y / 250);
	private Font font_BM = new Font("맑은 고딕", Font.BOLD, 30 + y / 250);
	private Font font_PS = new Font("맑은 고딕", Font.PLAIN, 20 + y / 250);
	private Font font_PSS = new Font("맑은 고딕", Font.PLAIN, 10 + y / 250);

	// 이미지
	private ImageIcon BarIcon = new ImageIcon("images/icon.png");

	/* 좌측 아이콘+ThePoint */
	private ImageIcon Mark1 = new ImageIcon("images/mark.png");
	private Image imgMark = Mark1.getImage();
	private Image imgMark2 = imgMark.getScaledInstance(width / 3, height * 2 / 3, Image.SCALE_SMOOTH);
	private ImageIcon Mark = new ImageIcon(imgMark2);

	/* 시작 버튼 */
	private ImageIcon btnStart1 = new ImageIcon("images/button/btnstart.png");
	private Image imgbtnStart = btnStart1.getImage();
	private Image imgbtnStart2 = imgbtnStart.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnStart = new ImageIcon(imgbtnStart2);

	/* 눌린 시작 버튼 */
	private ImageIcon btnStart_push1 = new ImageIcon("images/pushedbutton/btnstart_push.png");
	private Image imgbtnStart_push = btnStart_push1.getImage();
	private Image imgbtnStart_push2 = imgbtnStart_push.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnStart_push = new ImageIcon(imgbtnStart_push2);

	/* DB 변수 */
	private static M_Info_DAO mi_DAO = null;
	private static M_Info_DTO mi_DTO = null;

	//////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) { // Main 함수
		EventQueue.invokeLater(new Runnable() { // 이건 뭘까
			public void run() { // 실행 메서드
				try { // 이거 실행해서
					boolean ChkMember = ChkMember();
					if (ChkMember == true) {
						MainScreen ms = new MainScreen();
						ms.setVisible(true);
					} else {
						FirstScreen frame = new FirstScreen(); // 밑에 Member 메서드 실행, 회원 그 MEMBER 아님 주의!
						frame.setVisible(true); // 제일 겉 프레임 보이도록 설정
					}
				} catch (Exception e) { // 오류나면 경고!
					e.printStackTrace();
				}
			}
		});
	}

	public FirstScreen() { // 생성자야 실행하면 여기서 초기설정 해줘
		setTitle("The Point"); // 타이틀바 글씨 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 이거 해놓고 x누르면 아예 프로그램이 종료 돼
		setBounds((x - width) / 2, height / 10, width, height); // 앞에 두 개는 프레임 시작 위치, 뒤에 두 개는 프레임 사이즈!
		MainFrame = new JPanel(); // 위에서 선언한 객체 초기화
		MainFrame.setBorder(new EmptyBorder(5, 5, 5, 5)); // 상하좌우 살짝씩 여유 주는거야
		MainFrame.setLayout(new BorderLayout(0, 0)); // 멀라 Layout 설정해준대
		setResizable(false); // 전체화면 못하게
		setContentPane(MainFrame); // MainFrame을 지금까지 설정해준걸로 세팅해
		setIconImage(BarIcon.getImage()); // 좌측 상단이랑 작업 표시줄 아이콘 설정
		MainFrame.setBackground(new java.awt.Color(167, 186, 242)); // 뒤에 배경 블랙
		setLayout(null); // 판을 다시 짠다
		c_s.set(thisyear, thismonth, thisdate);
		c_e.set(thisyear, thismonth, thisdate);

		// 디자인
		/** 메세지박스 UI **/
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", new java.awt.Color(119, 150, 242));
		UI.put("Panel.background", new java.awt.Color(119, 150, 242));
		UI.put("OptionPane.messageFont", font_L);
		UI.put("OptionPane.messageForeground", Color.WHITE);
		UI.put("OptionPane.buttonFont", font_L);

		/* The Point 아이콘 */
		lbl_Mark = new JLabel(Mark);
		lbl_Mark.setBounds(width / 10, (height - Mark.getIconHeight()) / 2 - 10, Mark.getIconWidth(),
				Mark.getIconHeight());
		lbl_Mark.setVerticalAlignment(SwingConstants.CENTER);
		lbl_Mark.setHorizontalAlignment(SwingConstants.CENTER);

		MainFrame.add(lbl_Mark);

		/* 이름 */
		lbl_Name = new JLabel("이름");
		lbl_Name.setFont(font_L);
		lbl_Name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Name.setBounds(lbl_Mark.getX() + lbl_Mark.getWidth(), lbl_Mark.getY() + height / 100, 100 + width / 30,
				30 + height / 50);
		lbl_Name.setForeground(Color.WHITE);

		add(lbl_Name);

		/* 이름 입력 */
		txt_Name = new JTextField(20); // 텍스트필드 재정의
		txt_Name.setBounds((lbl_Name.getX() + lbl_Name.getWidth()) + 10, lbl_Name.getY(), width / 7,
				lbl_Name.getHeight()); // 위치랑 사이즈
		txt_Name.setFont(font_PS); // 글씨체

		add(txt_Name); // 판넬에 textfield 추가

		/* 목표 */
		lbl_Goal = new JLabel("목표");
		lbl_Goal.setFont(font_L);
		lbl_Goal.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Goal.setBounds(lbl_Name.getX(), (lbl_Name.getY() + lbl_Name.getHeight()) + 20, 100 + width / 30,
				lbl_Name.getHeight());
		lbl_Goal.setForeground(Color.WHITE);

		add(lbl_Goal);

		/* 목표 입력 */
		comb_Goal = new JComboBox<String>(goal); // 콤보박스 초기화인데 String 형식이야
		comb_Goal.setFont(font_PS); // 폰트
		comb_Goal.setBounds((lbl_Goal.getX() + lbl_Goal.getWidth()) + 10, lbl_Goal.getY(), width / 7,
				lbl_Name.getHeight()); // 위치랑 사이즈

		add(comb_Goal); // panel에 추가

		/* 주제 */
		lbl_LKind = new JLabel("주제");
		lbl_LKind.setFont(font_L);
		lbl_LKind.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_LKind.setBounds(lbl_Name.getX(), (lbl_Goal.getY() + lbl_Goal.getHeight()) + 20, 100 + width / 30,
				lbl_Name.getHeight());
		lbl_LKind.setForeground(Color.WHITE);

		add(lbl_LKind);

		/* 주제 입력 */
		txt_LKind = new JTextField(20); // 텍스트필드 재정의
		txt_LKind.setBounds((lbl_LKind.getX() + lbl_LKind.getWidth()) + 10, lbl_LKind.getY(), width / 3,
				lbl_Name.getHeight()); // 위치랑 사이즈
		txt_LKind.setFont(font_PS); // 글씨체

		add(txt_LKind); // 판넬에 textfield 추가

		/* 부제 */
		lbl_SKind = new JLabel("부제");
		lbl_SKind.setFont(font_L);
		lbl_SKind.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_SKind.setBounds(lbl_LKind.getX(), (txt_LKind.getY() + txt_LKind.getHeight()) + 20, 100 + width / 30,
				lbl_Name.getHeight());
		lbl_SKind.setForeground(Color.WHITE);

		add(lbl_SKind);

		/* 부제 입력 */
		txt_SKind = new JTextField(20); // 텍스트필드 재정의
		txt_SKind.setBounds((lbl_SKind.getX() + lbl_SKind.getWidth()) + 10, lbl_SKind.getY(), width / 3,
				lbl_Name.getHeight()); // 위치랑 사이즈
		txt_SKind.setFont(font_PS); // 글씨체

		add(txt_SKind); // 판넬에 textfield 추가

		/* 기간 */
		lbl_Date = new JLabel("기간");
		lbl_Date.setFont(font_L);
		lbl_Date.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Date.setBounds(lbl_Name.getX(), (lbl_SKind.getY() + lbl_SKind.getHeight()) + 20, 100 + width / 30,
				lbl_Name.getHeight());
		lbl_Date.setForeground(Color.WHITE);

		add(lbl_Date);

		// 올해 포함 이전 10년을 Year에 담기
		for (int i = 0, y = thisyear - i; i <= 10; i++, y--) {
			year[i] = String.valueOf(y);
		}

		/* 년 선택 */
		com_sYear = new JComboBox<String>(year); // 콤보박스 초기화인데 String 형식이야
		com_sYear.setFont(font_PS); // 폰트
		com_sYear.setBounds((lbl_Date.getX() + lbl_Date.getWidth()) + 10, lbl_Date.getY(), width / 17,
				lbl_Date.getHeight()); // 위치랑
		com_sYear.setSelectedItem(String.valueOf(thisyear)); // 사이즈

		add(com_sYear);

		/* - */
		label = new JLabel("-");
		label.setFont(font_PS);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds((com_sYear.getX() + com_sYear.getWidth()) - 5, com_sYear.getY(), width / 40,
				lbl_Name.getHeight());
		label.setForeground(Color.WHITE);

		add(label);

		/* 월 선택 */
		com_sMonth = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_sMonth.setFont(font_PS); // 폰트
		com_sMonth.setBounds((label.getX() + label.getWidth()) - 5, label.getY(), width / 25, lbl_Date.getHeight()); // 위치랑
																														// 사이즈
		for (int i = 1; i <= thismonth; i++) {
			com_sMonth.addItem(String.valueOf(i));
		}
		com_sMonth.setSelectedItem(String.valueOf(thismonth));
		com_sMonth.setSelectedIndex(thismonth - 1);

		com_sYear.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();
				// 월을 다 지워주고
				com_sMonth.removeAllItems();

				// 올해면
				if (Integer.parseInt(com_sYear.getSelectedItem().toString()) == thisyear) {
					for (int i = 1; i <= thismonth; i++) {
						com_sMonth.addItem(String.valueOf(i));
					}
				} else { // 올해가 아니면
					for (int i = 0; i < month.length; i++) {
						com_sMonth.addItem(month[i]);
					}
				}
				com_sMonth.setSelectedItem(1);
				com_sMonth.setSelectedIndex(0);
				c_s.set(Integer.parseInt(com_sYear.getSelectedItem().toString()),
						Integer.parseInt(com_sMonth.getSelectedItem().toString()), 1);
			}
		});

		add(com_sMonth);

		/* - */
		label = new JLabel("-");
		label.setFont(font_PS);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds((com_sMonth.getX() + com_sMonth.getWidth()) - 5, com_sMonth.getY(), width / 40,
				lbl_Name.getHeight());
		label.setForeground(Color.WHITE);

		add(label);

		/* 일 선택 */
		com_sDate = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_sDate.setFont(font_PS); // 폰트
		com_sDate.setBounds((label.getX() + label.getWidth()) - 5, label.getY(), width / 25, lbl_Date.getHeight()); // 위치랑
		// 사이즈
		/* 일 수 계산해서 넣기 */
		for (int i = 0; i < thisdate; i++) {
			com_sDate.addItem(String.valueOf(i + 1));
		}

		com_sMonth.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();
				com_sDate.removeAllItems();
				if (com_sMonth.getSelectedItem() != null) {
					c_s.set(Integer.parseInt(com_sYear.getSelectedItem().toString()),
							Integer.parseInt(com_sMonth.getSelectedItem().toString()) - 1, 1);
				}

				/* 일 수 계산해서 넣기 */
				if (com_sMonth.getSelectedItem() != null) {
					if ((Integer.parseInt(com_sYear.getSelectedItem().toString()) == thisyear)
							&& (Integer.parseInt(com_sMonth.getSelectedItem().toString()) == thismonth)) {
						for (int i = 1; i <= thisdate; i++) {
							com_sDate.addItem(i);
						}
					} else {
						for (int i = 0; i < c_s.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
							com_sDate.addItem(i + 1);
						}
					}
				}
			}
		});
		com_sDate.setSelectedItem(String.valueOf(thisdate));

		add(com_sDate);

		/* ~ */
		label = new JLabel("~");
		label.setFont(font_PS);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds((com_sDate.getX() + com_sDate.getWidth()) + 10, com_sDate.getY(), width / 40,
				lbl_Name.getHeight());
		label.setForeground(Color.WHITE);

		add(label);

		// 올해 포함 이후 10년을 Year에 담기
		for (int i = 0, y = thisyear + i; i <= 10; i++, y++) {
			year[i] = String.valueOf(y);
		}

		/* 년 선택 */
		com_eYear = new JComboBox<String>(year); // 콤보박스 초기화인데 String 형식이야
		com_eYear.setFont(font_PS); // 폰트
		com_eYear.setBounds((label.getX() + label.getWidth()) + 10, label.getY(), width / 17, lbl_Date.getHeight()); // 위치랑
		com_eYear.setSelectedItem(String.valueOf(thisyear)); // 사이즈

		add(com_eYear);

		/* - */
		label = new JLabel("-");
		label.setFont(font_PS);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds((com_eYear.getX() + com_eYear.getWidth()) - 5, com_eYear.getY(), width / 40,
				lbl_Name.getHeight());
		label.setForeground(Color.WHITE);

		add(label);

		/* 월 선택 */
		com_eMonth = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_eMonth.setFont(font_PS); // 폰트
		com_eMonth.setBounds((label.getX() + label.getWidth()) - 5, label.getY(), width / 25, lbl_Date.getHeight()); // 위치랑
																														// 사이즈
		for (int i = thismonth; i <= 12; i++) {
			com_eMonth.addItem(String.valueOf(i));
		}

		com_eMonth.setSelectedItem(String.valueOf(thismonth));
		com_eMonth.setSelectedIndex(0);

		com_eYear.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();
				// 월을 다 지워주고
				com_eMonth.removeAllItems();

				// 올해면
				if (Integer.parseInt(com_eYear.getSelectedItem().toString()) == thisyear) {
					for (int i = thismonth; i <= 12; i++) {
						com_eMonth.addItem(String.valueOf(i));
					}
					com_eMonth.setSelectedItem(String.valueOf(thismonth));
				} else { // 올해가 아니면
					for (int i = 0; i < month.length; i++) {
						com_eMonth.addItem(month[i]);
					}
					com_eMonth.setSelectedIndex(0);
				}
				c_e.set(Integer.parseInt(com_eYear.getSelectedItem().toString()),
						Integer.parseInt(com_eMonth.getSelectedItem().toString()), 1);
			}
		});

		add(com_eMonth);

		/* - */
		label = new JLabel("-");
		label.setFont(font_PS);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds((com_eMonth.getX() + com_eMonth.getWidth()) - 5, com_eMonth.getY(), width / 40,
				lbl_Name.getHeight());
		label.setForeground(Color.WHITE);

		add(label);

		/* 일 선택 */
		com_eDate = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_eDate.setFont(font_PS); // 폰트
		com_eDate.setBounds((label.getX() + label.getWidth()) - 5, label.getY(), width / 25, lbl_Date.getHeight()); // 위치랑
		// 사이즈
		/* 일 수 계산해서 넣기 */
		for (int i = thisdate; i <= Integer
				.parseInt(date[Integer.parseInt(com_eMonth.getSelectedItem().toString())]); i++) {
			com_eDate.addItem(i);
		}

		com_eMonth.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();
				com_eDate.removeAllItems();
				if (com_eMonth.getSelectedItem() != null) {
					c_e.set(Integer.parseInt(com_eYear.getSelectedItem().toString()),
							Integer.parseInt(com_eMonth.getSelectedItem().toString()) - 1, 1);
				}

				/* 일 수 계산해서 넣기 */
				if (com_eMonth.getSelectedItem() != null) {
					if ((Integer.parseInt(com_eYear.getSelectedItem().toString()) == thisyear)
							&& (Integer.parseInt(com_eMonth.getSelectedItem().toString()) == thismonth)) {
						for (int i = thisdate; i < Integer
								.parseInt(date[Integer.parseInt(com_eMonth.getSelectedItem().toString())]); i++) {
							com_eDate.addItem(i);
						}
					} else {
						for (int i = 1; i <= c_e.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
							com_eDate.addItem(i);
						}
					}
				}
			}
		});
		com_eDate.setSelectedItem(String.valueOf(thisdate));

		add(com_eDate);

		/* 각오 */
		lbl_Determin = new JLabel("각오");
		lbl_Determin.setFont(font_L);
		lbl_Determin.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Determin.setBounds(lbl_Name.getX(), (lbl_Date.getY() + lbl_Date.getHeight()) + 20, 100 + width / 30,
				lbl_Name.getHeight());
		lbl_Determin.setForeground(Color.WHITE);

		add(lbl_Determin);

		/* 각오 입력 */
		txt_Determin = new JTextArea();
		txt_Determin.setBounds((lbl_Determin.getX() + lbl_Determin.getWidth()) + 10, lbl_Determin.getY(), width / 3,
				height / 8);
		txt_Determin.setFont(font_PS);
		txt_Determin.setLineWrap(true);

		add(txt_Determin);

		/* 시작 버튼 */
		btn_Start = new JButton(btnStart);
		btn_Start.setSelectedIcon(btnStart_push);
		btn_Start.setRolloverIcon(btnStart_push);
		btn_Start.setBorderPainted(false);
		btn_Start.setFocusPainted(false);
		btn_Start.setContentAreaFilled(false);
		btn_Start.setBounds(
				(lbl_Mark.getX() + lbl_Mark.getWidth()) + (width - (lbl_Mark.getX() + lbl_Mark.getWidth())) / 3,
				(lbl_Mark.getY() + lbl_Mark.getHeight()) - btn_H, btn_W, btn_H);
		btn_Start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean ChkNon = ChkNon();
				if (ChkNon == true) {
					// 데이터를 DB에 입력
					if (InsertInfo() == true) {
						// 메인화면으로 이동
						MainScreen ms = new MainScreen();
						ms.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "데이터 입력에 실패하였습니다!", "시작 실패", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		add(btn_Start);
	}

	// 입력된 고객의 정보가 있는지 없는지 확인
	public static boolean ChkMember() {
		boolean result = false;

		// 새롭게 초기화
		mi_DAO = new M_Info_DAO();
		mi_DTO = new M_Info_DTO();

		// dao에서 멤버 정보 가져오기
		mi_DTO = mi_DAO.CheckInfo();

		// 데이터가 있으면 true, 없으면 false
		if (mi_DTO.getM_Name() == null || mi_DTO.getM_Name().equals("")) {
			result = false;
		} else {
			result = true;
		}

		return result;
	}

	// 빈칸이 있는지 확인
	public boolean ChkNon() {
		boolean result = false;

		if (txt_Name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "이름을 입력하세요", "시작 실패", JOptionPane.WARNING_MESSAGE);
			result = false;
		} else if (txt_LKind.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "주제를 입력하세요", "시작 실패", JOptionPane.WARNING_MESSAGE);
			result = false;
		} else if (txt_Determin.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "각오를 입력하세요", "시작 실패", JOptionPane.WARNING_MESSAGE);
			result = false;
		} else {
			result = true;
		}

		return result;
	}

	// 데이터를 DB에 저장
	public boolean InsertInfo() {
		boolean result = false;
		
		// 새롭게 초기화
		mi_DAO = new M_Info_DAO();
		mi_DTO = new M_Info_DTO();
		
		// 각 데이터 DTO 입력
		mi_DTO.setM_Name(txt_Name.getText().toString());
		mi_DTO.setM_Goal(comb_Goal.getSelectedItem().toString());
		mi_DTO.setM_LKind(txt_LKind.getText().toString());
		mi_DTO.setM_SKind(txt_SKind.getText().toString());
		mi_DTO.setM_StartYear(Integer.parseInt(com_sYear.getSelectedItem().toString()));
		mi_DTO.setM_StartMonth(Integer.parseInt(com_sMonth.getSelectedItem().toString()));
		mi_DTO.setM_StartDate(Integer.parseInt(com_sDate.getSelectedItem().toString()));
		mi_DTO.setM_EndYear(Integer.parseInt(com_eYear.getSelectedItem().toString()));
		mi_DTO.setM_EndMonth(Integer.parseInt(com_eMonth.getSelectedItem().toString()));
		mi_DTO.setM_EndDate(Integer.parseInt(com_eDate.getSelectedItem().toString()));
		mi_DTO.setM_Determin(txt_Determin.getText().toString());
		
		result = mi_DAO.InsertInfo(mi_DTO);
		
		return result;
	}
}
