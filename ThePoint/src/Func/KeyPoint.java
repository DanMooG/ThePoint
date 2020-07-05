package Func;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Blob;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import DAO.M_Info_DAO;
import DAO.P_KeyPoint_DAO;
import DTO.M_Info_DTO;
import DTO.P_KeyPoint_DTO;

public class KeyPoint extends JFrame {
	// 컴포넌트
	private JPanel panel; // Panel 등록해서 객체로 쓰고
	private JButton btn_Home, btn_Search, btn_Input, btn_Reset;
	private JLabel label, lbl_back1, lbl_1, lbl_2, lbl_3, lbl_4, lbl_5;
	private JComboBox com_1, com_2, com_3, com_4, com_5;
	private JTextArea txt_Input;
	private JScrollPane scroll;

	// 크기 조절
	private int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private int width = x * 3 / 4, height = y * 4 / 5;
	private int btn_W = width / 7, btn_H = height / 10;
	private int num_W = ((((width - 40) / 3) - 10) / 2) / 6;
	String[] list1, list2, list3, list4, list5;
	String str = "";

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

	/* 검색 버튼 */
	private ImageIcon btnSearch1 = new ImageIcon("./images/button/btnSearch.png");
	private Image imgbtnSearch = btnSearch1.getImage();
	private Image imgbtnSearch2 = imgbtnSearch.getScaledInstance((height / 11 - 10) * 18 / 10, height / 11 - 10,
			Image.SCALE_SMOOTH);
	private ImageIcon btnSearch = new ImageIcon(imgbtnSearch2);

	private ImageIcon btnSearch_push1 = new ImageIcon("./images/pushedbutton/btnSearch_push.png");
	private Image imgbtnSearch_push = btnSearch_push1.getImage();
	private Image imgbtnSearch_push2 = imgbtnSearch_push.getScaledInstance((height / 11 - 10) * 18 / 10,
			height / 11 - 10, Image.SCALE_SMOOTH);
	private ImageIcon btnSearch_push = new ImageIcon(imgbtnSearch_push2);

	/* 입력 버튼 */
	private ImageIcon btnInput1 = new ImageIcon("./images/button/btn_point_input.png");
	private Image imgbtnInput = btnInput1.getImage();
	private Image imgbtnInput2 = imgbtnInput.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnInput = new ImageIcon(imgbtnInput2);

	private ImageIcon btnInput_push1 = new ImageIcon("./images/pushedbutton/btn_point_input_push.png");
	private Image imgbtnInput_push = btnInput_push1.getImage();
	private Image imgbtnInput_push2 = imgbtnInput_push.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnInput_push = new ImageIcon(imgbtnInput_push2);

	/* 초기화 버튼 */
	private ImageIcon btnReset1 = new ImageIcon("./images/button/btn_point_reset.png");
	private Image imgbtnReset = btnReset1.getImage();
	private Image imgbtnReset2 = imgbtnReset.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnReset = new ImageIcon(imgbtnReset2);

	private ImageIcon btnReset_push1 = new ImageIcon("./images/pushedbutton/btn_point_reset_push.png");
	private Image imgbtnReset_push = btnReset_push1.getImage();
	private Image imgbtnReset_push2 = imgbtnReset_push.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnReset_push = new ImageIcon(imgbtnReset_push2);

	/* 1 */
	private ImageIcon btn11 = new ImageIcon("./images/button/btn1.png");
	private Image imgbtn1 = btn11.getImage();
	private Image imgbtn12 = imgbtn1.getScaledInstance(num_W, num_W, Image.SCALE_SMOOTH);
	private ImageIcon btn1 = new ImageIcon(imgbtn12);

	/* 2 */
	private ImageIcon btn21 = new ImageIcon("./images/button/btn2.png");
	private Image imgbtn2 = btn21.getImage();
	private Image imgbtn22 = imgbtn2.getScaledInstance(num_W, num_W, Image.SCALE_SMOOTH);
	private ImageIcon btn2 = new ImageIcon(imgbtn22);

	/* 3 */
	private ImageIcon btn31 = new ImageIcon("./images/button/btn3.png");
	private Image imgbtn3 = btn31.getImage();
	private Image imgbtn32 = imgbtn3.getScaledInstance(num_W, num_W, Image.SCALE_SMOOTH);
	private ImageIcon btn3 = new ImageIcon(imgbtn32);

	/* 4 */
	private ImageIcon btn41 = new ImageIcon("./images/button/btn4.png");
	private Image imgbtn4 = btn41.getImage();
	private Image imgbtn42 = imgbtn4.getScaledInstance(num_W, num_W, Image.SCALE_SMOOTH);
	private ImageIcon btn4 = new ImageIcon(imgbtn42);

	/* 5 */
	private ImageIcon btn51 = new ImageIcon("./images/button/btn5.png");
	private Image imgbtn5 = btn51.getImage();
	private Image imgbtn52 = imgbtn5.getScaledInstance(num_W, num_W, Image.SCALE_SMOOTH);
	private ImageIcon btn5 = new ImageIcon(imgbtn52);

	/* DB 변수 */
	private static M_Info_DAO mi_DAO = new M_Info_DAO();
	private static M_Info_DTO mi_DTO = new M_Info_DTO();
	private static P_KeyPoint_DAO pk_DAO = new P_KeyPoint_DAO();
	private static P_KeyPoint_DTO pk_DTO = new P_KeyPoint_DTO();

	//////////////////////////////////////////////////////////////////////////////////////

	public KeyPoint() {
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

		/** 메세지박스 UI **/
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", new java.awt.Color(119, 150, 242));
		UI.put("Panel.background", new java.awt.Color(119, 150, 242));
		UI.put("OptionPane.messageFont", font_BM);
		UI.put("OptionPane.messageForeground", Color.WHITE);
		UI.put("OptionPane.buttonFont", font_L);

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

		/* 1 */
		lbl_1 = new JLabel(btn1);
		lbl_1.setBounds(label.getX() + 15, (label.getY() + label.getHeight()) + 25, num_W, num_W);
		lbl_1.setVerticalAlignment(SwingConstants.CENTER);
		lbl_1.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_1);

		/* 1 선택 */
		com_1 = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_1.setFont(font_PS); // 폰트
		com_1.setBounds((lbl_1.getX() + lbl_1.getWidth()) + 5, lbl_1.getY(), width / 7, lbl_1.getHeight()); // 위치랑 사이즈
		str = "p_Kind1 NOT LIKE 0 AND p_Kind2 LIKE 0 AND p_Kind3 LIKE 0 AND p_Kind4 LIKE 0 AND p_Kind5 LIKE 0";
		list1 = new String[pk_DAO.GetCntStructure(str)];
		list1 = getSelectedStructure(str);
		for (int i = 0; i < list1.length; i++) {
			com_1.addItem(list1[i]);
		}

		add(com_1); // panel에 추가

		/* 2 */
		lbl_2 = new JLabel(btn2);
		lbl_2.setBounds((com_1.getX() + com_1.getWidth()) + 10, com_1.getY(), num_W, num_W);
		lbl_2.setVerticalAlignment(SwingConstants.CENTER);
		lbl_2.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_2);

		/* 2 선택 */
		com_2 = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_2.setFont(font_PS); // 폰트
		com_2.setBounds((lbl_2.getX() + lbl_2.getWidth()) + 5, lbl_2.getY(), width / 7, lbl_2.getHeight()); // 위치랑 사이즈
		str = "p_Kind1 LIKE " + (com_1.getSelectedIndex() + 1)
				+ " AND p_Kind2 NOT LIKE 0 AND p_Kind3 LIKE 0 AND p_Kind4 LIKE 0 AND p_Kind5 LIKE 0";
		list2 = new String[pk_DAO.GetCntStructure(str)];
		list2 = getSelectedStructure(str);
		for (int i = 0; i < list2.length; i++) {
			com_2.addItem(list2[i]);
		}
		com_1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				str = "p_Kind1 LIKE " + (com_1.getSelectedIndex() + 1)
						+ " AND p_Kind2 NOT LIKE 0 AND p_Kind3 LIKE 0 AND p_Kind4 LIKE 0 AND p_Kind5 LIKE 0";
				com_2.removeAllItems();
				list2 = new String[pk_DAO.GetCntStructure(str)];
				list2 = getSelectedStructure(str);
				for (int i = 0; i < list2.length; i++) {
					com_2.addItem(list2[i]);
				}
				com_2.setSelectedIndex(-1);
			}
		});
		com_2.setSelectedIndex(-1);
		add(com_2); // panel에 추가

		/* 3 */
		lbl_3 = new JLabel(btn3);
		lbl_3.setBounds((com_2.getX() + com_2.getWidth()) + 10, com_2.getY(), num_W, num_W);
		lbl_3.setVerticalAlignment(SwingConstants.CENTER);
		lbl_3.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_3);

		/* 3 선택 */
		com_3 = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_3.setFont(font_PS); // 폰트
		com_3.setBounds((lbl_3.getX() + lbl_3.getWidth()) + 5, lbl_3.getY(), width / 7, lbl_3.getHeight()); // 위치랑 사이즈
		str = "p_Kind1 LIKE " + (com_1.getSelectedIndex() + 1) + " AND p_Kind2 LIKE " + (com_2.getSelectedIndex() + 1)
				+ " AND p_Kind3 NOT LIKE 0 AND p_Kind4 LIKE 0 AND p_Kind5 LIKE 0";
		list3 = new String[pk_DAO.GetCntStructure(str)];
		list3 = getSelectedStructure(str);
		for (int i = 0; i < list3.length; i++) {
			com_3.addItem(list3[i]);
		}
		com_2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				str = "p_Kind1 LIKE " + (com_1.getSelectedIndex() + 1) + " AND p_Kind2 LIKE "
						+ (com_2.getSelectedIndex() + 1)
						+ " AND p_Kind3 NOT LIKE 0 AND p_Kind4 LIKE 0 AND p_Kind5 LIKE 0";
				com_3.removeAllItems();
				list3 = new String[pk_DAO.GetCntStructure(str)];
				list3 = getSelectedStructure(str);
				for (int i = 0; i < list3.length; i++) {
					com_3.addItem(list3[i]);
				}
				com_3.setSelectedIndex(-1);
			}
		});
		com_3.setSelectedIndex(-1);
		add(com_3); // panel에 추가

		/* 4 */
		lbl_4 = new JLabel(btn4);
		lbl_4.setBounds((com_3.getX() + com_3.getWidth()) + 10, com_3.getY(), num_W, num_W);
		lbl_4.setVerticalAlignment(SwingConstants.CENTER);
		lbl_4.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_4);

		/* 4 선택 */
		com_4 = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_4.setFont(font_PS); // 폰트
		com_4.setBounds((lbl_4.getX() + lbl_4.getWidth()) + 5, lbl_4.getY(), width / 7, lbl_4.getHeight()); // 위치랑 사이즈
		str = "p_Kind1 LIKE " + (com_1.getSelectedIndex() + 1) + " AND p_Kind2 LIKE " + (com_2.getSelectedIndex() + 1)
				+ " AND p_Kind3 LIKE " + (com_3.getSelectedIndex() + 1) + " AND p_Kind4 NOT LIKE 0 AND p_Kind5 LIKE 0";
		list4 = new String[pk_DAO.GetCntStructure(str)];
		list4 = getSelectedStructure(str);
		for (int i = 0; i < list4.length; i++) {
			com_4.addItem(list4[i]);
		}
		com_3.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				str = "p_Kind1 LIKE " + (com_1.getSelectedIndex() + 1) + " AND p_Kind2 LIKE "
						+ (com_2.getSelectedIndex() + 1) + " AND p_Kind3 LIKE " + (com_3.getSelectedIndex() + 1)
						+ " AND p_Kind4 NOT LIKE 0 AND p_Kind5 LIKE 0";
				com_4.removeAllItems();
				list4 = new String[pk_DAO.GetCntStructure(str)];
				list4 = getSelectedStructure(str);
				for (int i = 0; i < list4.length; i++) {
					com_4.addItem(list4[i]);
				}
				com_4.setSelectedIndex(-1);
			}
		});
		com_4.setSelectedIndex(-1);
		add(com_4); // panel에 추가

		/* 5 */
		lbl_5 = new JLabel(btn5);
		lbl_5.setBounds((com_4.getX() + com_4.getWidth()) + 10, com_4.getY(), num_W, num_W);
		lbl_5.setVerticalAlignment(SwingConstants.CENTER);
		lbl_5.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_5);

		/* 5 선택 */
		com_5 = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_5.setFont(font_PS); // 폰트
		com_5.setBounds((lbl_5.getX() + lbl_5.getWidth()) + 5, lbl_5.getY(), width / 7, lbl_5.getHeight()); // 위치랑 사이즈
		str = "p_Kind1 LIKE " + (com_1.getSelectedIndex() + 1) + " AND p_Kind2 LIKE " + (com_2.getSelectedIndex() + 1)
				+ " AND p_Kind3 LIKE " + (com_3.getSelectedIndex() + 1) + " AND p_Kind4 LIKE "
				+ (com_4.getSelectedIndex() + 1) + " AND p_Kind5 NOT LIKE 0";
		list5 = new String[pk_DAO.GetCntStructure(str)];
		list5 = getSelectedStructure(str);
		for (int i = 0; i < list3.length; i++) {
			com_3.addItem(list3[i]);
		}
		com_4.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				str = "p_Kind1 LIKE " + (com_1.getSelectedIndex() + 1) + " AND p_Kind2 LIKE "
						+ (com_2.getSelectedIndex() + 1) + " AND p_Kind3 LIKE " + (com_3.getSelectedIndex() + 1)
						+ " AND p_Kind4 LIKE " + (com_4.getSelectedIndex() + 1) + " AND p_Kind5 NOT LIKE 0";
				com_5.removeAllItems();
				list5 = new String[pk_DAO.GetCntStructure(str)];
				list5 = getSelectedStructure(str);
				for (int i = 0; i < list5.length; i++) {
					com_5.addItem(list4[i]);
				}
				com_5.setSelectedIndex(-1);
			}
		});
		com_5.setSelectedIndex(-1);
		add(com_5); // panel에 추가

		/* 검색 버튼 */
		btn_Search = new JButton(btnSearch);
		btn_Search.setSelectedIcon(btnSearch_push);
		btn_Search.setRolloverIcon(btnSearch_push);
		btn_Search.setBorderPainted(false);
		btn_Search.setFocusPainted(false);
		btn_Search.setContentAreaFilled(false);
		btn_Search.setBounds((com_5.getX() + com_5.getWidth()) + 10, (label.getY() + label.getHeight()) + 10,
				(height / 11 - 10) * 18 / 10, height / 11 - 10);
		btn_Search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pk_DTO.setP_Kind1(com_1.getSelectedIndex() + 1);
				pk_DTO.setP_Kind2(com_2.getSelectedIndex() + 1);
				pk_DTO.setP_Kind3(com_3.getSelectedIndex() + 1);
				pk_DTO.setP_Kind4(com_4.getSelectedIndex() + 1);
				pk_DTO.setP_Kind5(com_5.getSelectedIndex() + 1);
				pk_DTO.setP_Kind_Info("");
				pk_DTO.setP_Point_Info("");
				pk_DTO = pk_DAO.SearchPoint(pk_DTO);
				txt_Input.setText(pk_DTO.getP_Point_Info().toString());
			}
		});

		panel.add(btn_Search);

		/* 선택창 배경 */
		lbl_back1 = new JLabel();
		lbl_back1.setBounds(5, (label.getY() + label.getHeight()) + 5, width - 15, height / 11);
		lbl_back1.setOpaque(true);
		lbl_back1.setBackground(new java.awt.Color(119, 150, 242)); // 뒤에 배경
		lbl_back1.setVerticalAlignment(SwingConstants.CENTER);
		lbl_back1.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_back1);

		/* 내용 입력 */
		txt_Input = new JTextArea();
		txt_Input.setFont(font_PS);
		txt_Input.setLineWrap(true);
		txt_Input.setBorder(new LineBorder(new java.awt.Color(167, 186, 242), 5));

		scroll = new JScrollPane(txt_Input);
		scroll.setViewportView(txt_Input);
		scroll.setBounds(lbl_back1.getX(), (lbl_back1.getY() + lbl_back1.getHeight()) + 10, lbl_back1.getWidth(),
				height - ((lbl_back1.getHeight() + label.getHeight()) * 2 + 20));

		panel.add(scroll); // 스크롤 추가

		/* 입력 버튼 */
		btn_Input = new JButton(btnInput);
		btn_Input.setSelectedIcon(btnInput_push);
		btn_Input.setRolloverIcon(btnInput_push);
		btn_Input.setBorderPainted(false);
		btn_Input.setFocusPainted(false);
		btn_Input.setContentAreaFilled(false);
		btn_Input.setBounds(scroll.getX(), (scroll.getY() + scroll.getHeight() + 7), btn_W, btn_H);
		btn_Input.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pk_DTO.getP_Kind1() == 0) {
					JOptionPane.showMessageDialog(null, "먼저 검색을 해주세요!", "오류", JOptionPane.WARNING_MESSAGE);
				} else if (txt_Input.getText().equals("") || txt_Input.getText() == null) {
					int result1 = JOptionPane.showConfirmDialog(null, "입력된 내용이 없습니다!\n입력하시겠습니까?", "입력여부 확인",
							JOptionPane.YES_NO_OPTION);
					if (result1 == JOptionPane.YES_OPTION) { // Yes
						String inputText = StringReplace(txt_Input.getText());
						boolean result = pk_DAO.InputPoint(pk_DTO, inputText);
						if (result == true) {
							JOptionPane.showMessageDialog(null, "수정 되었습니다", "수정 완료", JOptionPane.WARNING_MESSAGE);
							Reset();
						} else {
							JOptionPane.showMessageDialog(null, "오류가 발생했습니다", "오류", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "취소되었습니다", "취소", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					String inputText = StringReplace(txt_Input.getText());
					boolean result = pk_DAO.InputPoint(pk_DTO, inputText);
					if (result == true) {
						JOptionPane.showMessageDialog(null, "수정 되었습니다", "수정 완료", JOptionPane.WARNING_MESSAGE);
						Reset();
					} else {
						JOptionPane.showMessageDialog(null, "오류가 발생했습니다", "오류", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		panel.add(btn_Input);

		/* 내용 입력 시 유의사항 */
		label = new JLabel("※!!특수문자는 키보드에 있는 문자나※");
		label.setFont(font_PS);
		label.setForeground(Color.BLACK);
		label.setBounds(width / 2 - (width - (btn_W * 2 + 50)) / 2, (scroll.getY() + scroll.getHeight()) + 5,
				width - (btn_W * 2 + 50), btn_W / 5);
		label.setOpaque(true);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(label);
		
		/* 내용 입력 시 유의사항 */
		label = new JLabel("※!!\'ㄱ ~ ㅎ, ㄲ, ㄸ, ㅃ\'+한자에 있는 특수문자만 가능합니다!!※");
		label.setFont(font_PS);
		label.setForeground(Color.BLACK);
		label.setBounds(width / 2 - (width - (btn_W * 2 + 50)) / 2, (scroll.getY() + scroll.getHeight()) + btn_W/5+5,
				width - (btn_W * 2 + 50), btn_W / 5);
		label.setOpaque(true);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(label);

		/* 초기화 버튼 */
		btn_Reset = new JButton(btnReset);
		btn_Reset.setSelectedIcon(btnReset_push);
		btn_Reset.setRolloverIcon(btnReset_push);
		btn_Reset.setBorderPainted(false);
		btn_Reset.setFocusPainted(false);
		btn_Reset.setContentAreaFilled(false);
		btn_Reset.setBounds(width - (scroll.getX() + btn_W + 5), (scroll.getY() + scroll.getHeight() + 7),
				btn_W, btn_H);
		btn_Reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txt_Input.setText("");
			}
		});

		panel.add(btn_Reset);

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

	// 앞에 꺼가 어떤 건지 다 알고 있어야 혀 예를 들어 목차1-2-1을 검색하려면 (1, 2, 1, 0, 0)을 보내서 검색하는거지
	public String[] getSelectedStructure(String str) {
		// 새롭게 초기화
		pk_DAO = new P_KeyPoint_DAO();
		pk_DTO = new P_KeyPoint_DTO();

		String[] result = new String[pk_DAO.GetCntStructure(str)];

		// dao에서 멤버 정보 가져오기
		result = pk_DAO.GetAllStructure(str);

		return result;
	}

	// 초기화
	public void Reset() {
		MainScreen ms = new MainScreen();
		ms.setVisible(true);
		dispose();
	}

	// 특수문자 체크
	public static String StringReplace(String str) {
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		match += "！ ＇ ， ． ／ ： ； ？ ＾ ＿ ｀ ｜ ￣ 、 。 · ‥ … ¨ 〃­ ― ∥ ＼ ∼ ´ ～ ˇ ˘ ˝ ˚ ˙ ¸ ˛ ¡ ¿ ː "
				+"Æ Ð Ħ Ĳ Ŀ Ł Ø Œ Þ Ŧ Ŋ æ đ ð Ł Ø ĳ ĸ ŀ ł ø œ ß þ ŧ ŋ ŉ"
				+"＄ ％ ￦ Ｆ ′ ″ ℃ Å ￠ ￡ ￥ ¤ ℉ ‰ ?? ㎕ ㎖ ㎗ ℓ ㎘ ㏄ ㎣ ㎤ ㎥ ㎦ ㎙ ㎚ ㎛ ㎜ ㎝ ㎞ ㎟ ㎠ ㎡ ㎙㏊"
				+"㎍ ㎎ ㎏ ㏏ ㎈ ㎉ ㏈ ㎧ ㎨ ㎰ ㎱ ㎲ ㎳ ㎴ ㎵ ㎶ ㎷ ㎸ ㎹ ㎀ ㎁ ㎂ ㎃ ㎄ ㎺ ㎻ ㎼ ㎽ ㎾ ㎿ ㎐ ㎑ ㎒ ㎓ ㎔ Ω"
				+"㏀ ㏁ ㎊ ㎋ ㎌ ㏖ ㏅ ㎭ ㎮ ㎯ ㏛ ㎩ ㎪ ㎫ ㎬ ㏝ ㏐ ㏓ ㏃ ㏉ ㏜ ㏆" 
				+"＃ ＆ ＊ ＠ § ※ ☆ ★ ○ ● ◎ ◇ ◆ □ ■ △ ▲ ▽ ▼ → ← ↑ ↓ ↔ 〓 ◁ ◀ ▷ ▶ ♤ ♠ ♡ ♥ ♧ ♣ ⊙ ◈ ▣ ◐ ◑ ▒ ▤ ▥ ▨ ▧ ▦ ▩"
				+" ♨ ☏ ☎ ☜ ☞ ¶ † ‡ ↕ ↗ ↙ ↖ ↘ ♭ ♩ ♪ ♬ ㉿ ㈜ № ㏇ ™ ㏂ ㏘ ℡ ?? ª º" 
				+"─ │ ┌ ┐ ┘ └ ├ ┬ ┤ ┴ ┼ ━ ┃ ┏ ┓ ┛ ┗ ┣ ┳ ┫ ┻ ╋ ┠ ┯ ┨ ┷ ┿ ┝ ┰ ┥ ┸ ╂ ┒ ┑ ┚ ┙ ┖ ┕"
				+"┎ ┍ ┞ ┟ ┡ ┢ ┦ ┧ ┩ ┪ ┭ ┮ ┱ ┲ ┵ ┶ ┹ ┺ ┽ ┾ ╀ ╁ ╃ ╄ ╅ ╆ ╇ ╈ ╉ ╊" 
				+"ァ ア ィ イ ゥ ウ ェ エ ォ オ カ ガ キ ギ ク グ ケ ゲ コ ゴ サ ザ シ ジ ス ズ セ ゼ ソ ゾ タ ダ チ ヂ ッ ツヅ テ デ ト ド ナ ニ ヌ ネ ノ ハ バ"
				+"パ ヒ ビ ピ フ ブ プ ヘ ベ ペ ホ ボ ポ マ ミ ム メ モ ャ ヤ ュ ユ ョ ヨラ リ ル レ ロ ヮ ワ ヰ ヱ ヲ ン ヴ ヵ ヶ" 
				+"㉠ ㉡ ㉢ ㉣ ㉭ ㉥ ㉦ ㉧ ㉨ ㉩ ㉪ ㉫ ㉬ ㉭ ㉮ ㉯ ㉰ ㉱ ㉲ ㉳ ㉴ ㉵ ㉶ ㉷ ㉸ ㉹ ㉺ ㉻"
				+"㈀ ㈁ ㈂ ㈃ ㈄ ㈅ ㈆ ㈇ ㈈ ㈉ ㈊ ㈋ ㈌ ㈍ ㈎ ㈏ ㈐ ㈑ ㈒ ㈓ ㈔ ㈕ ㈖ ㈗ ㈘ ㈙ ㈚ ㈛"
				+"А Б В Г Д Е Ё Ж З И Й К Л М Н О П Р С Т У Ф Х Ц Ч Ш Щ Ъ Ы Ь Э Ю Я а б"
				+"в г д е ё ж з и й к л м н о п р с т ф х ц ч ш щ ъ ы ы ь э ю я" 
				+"ⓐ ⓑ ⓒ ⓓ ⓔ ⓕ ⓖ ⓗ ⓘ ⓙ ⓚ ⓛ ⓜ ⓝ ⓞ ⓖ ⓠ ⓡ ⓢ ⓣ ⓤ ⓥ ⓦ ⓧ ⓨ ⓩ ① ② ③ ④ ⑤ ⑥ ⑦ ⑧ ⑨ ⑩ ⑪ ⑫ ⑬ ⑭ ⑮"
				+" ⒜ ⒝ ⒞ ⒟ ⒠ ⒡ ⒢ ⒣ ⒤ ⒥ ⒦ ⒧ ⒨ ⒩ ⒪ ⒫ ⒬ ⒭ ⒮ ⒯ ⒰ ⒱ ⒲ ⒳ ⒴ ⒵ ⑴ ⑵ ⑶ ⑷ ⑸ ⑹ ⑺ ⑻ ⑼ ⑽ ⑾ ⑿ ⒀ ⒁ ⒂" 
				+"０ １ ２ ３ ４ ５ ６ ７ ８ ９ ⅰ ⅱ ⅲ ⅳ ⅴ ⅵ ⅶ ⅷ ⅸ ⅹ Ⅰ Ⅱ Ⅲ Ⅳ Ⅴ Ⅵ Ⅶ Ⅷ Ⅸ Ⅹ\r\n" 
				+"½ ⅓ ⅔ ¼ ¾ ⅛ ⅜ ⅝ ⅞ ¹ ² ³ ⁴ ⁿ ₁ ₂ ₃ ₄"
				+"Α Β Γ Δ Ε Ζ Η Θ Ι Κ Λ Μ Ν Ξ Ο Π Ρ Σ Τ Υ Φ Χ Ψ Ω α β γ δ ε ζ η θ ι κ λ μ ν ξ ο π ρ σ τ υ φ χ ψ ω";
		str = str.replaceAll(match, "");
		return str;
	}
}
