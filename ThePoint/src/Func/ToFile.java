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
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.mariadb.jdbc.internal.failover.Listener;

import DAO.M_Info_DAO;
import DAO.P_KeyPoint_DAO;
import DTO.M_Info_DTO;
import DTO.P_KeyPoint_DTO;

public class ToFile extends JFrame {
	// 컴포넌트
	private JPanel panel; // Panel 등록해서 객체로 쓰고
	private JButton btn_Home, btn_Word, btn_PDF;
	private JLabel label, lbl_back1, lbl_1, lbl_2, lbl_3, lbl_4, lbl_5;
	private JComboBox com_1_start, com_2_start, com_3_start, com_4_start, com_5_start;
	private JComboBox com_1_end, com_2_end, com_3_end, com_4_end, com_5_end;

	// 크기 조절
	private int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private int width = x * 3 / 4, height = y * 4 / 5;
	private int btn_W = (width - 45) / 3, btn_H = height - (height / 15 + 60),
			btn_SH = (height - (height / 15 + 80)) / 3;
	private int num_W = ((((width - 40) / 3) - 10) / 2) / 5;
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

	/* 워드 버튼 */
	private ImageIcon btnWord1 = new ImageIcon("./images/button/word.png");
	private Image imgbtnWord = btnWord1.getImage();
	private Image imgbtnWord2 = imgbtnWord.getScaledInstance(btn_W * 3 / 2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnWord = new ImageIcon(imgbtnWord2);

	private ImageIcon btnWord_push1 = new ImageIcon("./images/pushedbutton/word_push.png");
	private Image imgbtnWord_push = btnWord_push1.getImage();
	private Image imgbtnWord_push2 = imgbtnWord_push.getScaledInstance(btn_W * 3 / 2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnWord_push = new ImageIcon(imgbtnWord_push2);

	/* PDF 버튼 */
	private ImageIcon btnPDF1 = new ImageIcon("./images/button/PDF.png");
	private Image imgbtnPDF = btnPDF1.getImage();
	private Image imgbtnPDF2 = imgbtnPDF.getScaledInstance(btn_W * 3 / 2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnPDF = new ImageIcon(imgbtnPDF2);

	private ImageIcon btnPDF_push1 = new ImageIcon("./images/pushedbutton/PDF_push.png");
	private Image imgbtnPDF_push = btnPDF_push1.getImage();
	private Image imgbtnPDF_push2 = imgbtnPDF_push.getScaledInstance(btn_W * 3 / 2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnPDF_push = new ImageIcon(imgbtnPDF_push2);

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
	private static M_Info_DAO mi_DAO = null;
	private static M_Info_DTO mi_DTO = null;
	private static P_KeyPoint_DAO pk_DAO = new P_KeyPoint_DAO();
	private static P_KeyPoint_DTO pk_DTO = new P_KeyPoint_DTO();

	public ToFile() {
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

		/* 1 */
		lbl_1 = new JLabel(btn1);
		lbl_1.setBounds(label.getX() + 35, (label.getY() + label.getHeight()) + 50, num_W, num_W);
		lbl_1.setVerticalAlignment(SwingConstants.CENTER);
		lbl_1.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_1);

		/* 1 선택 */
		com_1_start = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_1_start.setFont(font_BM); // 폰트
		com_1_start.setBounds((lbl_1.getX() + lbl_1.getWidth()) + 20, lbl_1.getY(), btn_W, lbl_1.getHeight()); // 위치랑 사이즈
		str = "p_Kind1 NOT LIKE 0 AND p_Kind2 LIKE 0 AND p_Kind3 LIKE 0 AND p_Kind4 LIKE 0 AND p_Kind5 LIKE 0";
		list1 = new String[pk_DAO.GetCntStructure(str)];
		list1 = getSelectedStructure(str);
		for (int i = 0; i < list1.length; i++) {
			com_1_start.addItem(list1[i]);
		}

		add(com_1_start); // panel에 추가
		
		/* 2 */
		lbl_2 = new JLabel(btn2);
		lbl_2.setBounds(lbl_1.getX(), (lbl_1.getY()+lbl_1.getHeight())+20, num_W, num_W);
		lbl_2.setVerticalAlignment(SwingConstants.CENTER);
		lbl_2.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_2);

		/* 2 선택 */
		com_2_start = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_2_start.setFont(font_BM); // 폰트
		com_2_start.setBounds((lbl_2.getX() + lbl_2.getWidth()) + 20, lbl_2.getY(), btn_W, lbl_2.getHeight()); // 위치랑 사이즈
		str = "p_Kind1 LIKE " + (com_1_start.getSelectedIndex() + 1)
				+ " AND p_Kind2 NOT LIKE 0 AND p_Kind3 LIKE 0 AND p_Kind4 LIKE 0 AND p_Kind5 LIKE 0";
		list2 = new String[pk_DAO.GetCntStructure(str)];
		list2 = getSelectedStructure(str);
		for (int i = 0; i < list2.length; i++) {
			com_2_start.addItem(list2[i]);
		}
		com_1_start.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				str = "p_Kind1 LIKE " + (com_1_start.getSelectedIndex() + 1)
						+ " AND p_Kind2 NOT LIKE 0 AND p_Kind3 LIKE 0 AND p_Kind4 LIKE 0 AND p_Kind5 LIKE 0";
				com_2_start.removeAllItems();
				list2 = new String[pk_DAO.GetCntStructure(str)];
				list2 = getSelectedStructure(str);
				for (int i = 0; i < list2.length; i++) {
					com_2_start.addItem(list2[i]);
				}
				com_2_start.setSelectedIndex(-1);
			}
		});
		com_2_start.setSelectedIndex(-1);
		add(com_2_start); // panel에 추가
		
		/* 3 */
		lbl_3 = new JLabel(btn3);
		lbl_3.setBounds(lbl_2.getX(), (lbl_2.getY()+lbl_2.getHeight())+20, num_W, num_W);
		lbl_3.setVerticalAlignment(SwingConstants.CENTER);
		lbl_3.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_3);

		/* 3 선택 */
		com_3_start = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_3_start.setFont(font_BM); // 폰트
		com_3_start.setBounds((lbl_3.getX() + lbl_3.getWidth()) + 20, lbl_3.getY(), btn_W, lbl_3.getHeight()); // 위치랑 사이즈
		str = "p_Kind1 LIKE " + (com_1_start.getSelectedIndex() + 1) + " AND p_Kind2 LIKE " + (com_2_start.getSelectedIndex() + 1)
				+ " AND p_Kind3 NOT LIKE 0 AND p_Kind4 LIKE 0 AND p_Kind5 LIKE 0";
		list3 = new String[pk_DAO.GetCntStructure(str)];
		list3 = getSelectedStructure(str);
		for (int i = 0; i < list3.length; i++) {
			com_3_start.addItem(list3[i]);
		}
		com_2_start.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				str = "p_Kind1 LIKE " + (com_1_start.getSelectedIndex() + 1) + " AND p_Kind2 LIKE "
						+ (com_2_start.getSelectedIndex() + 1)
						+ " AND p_Kind3 NOT LIKE 0 AND p_Kind4 LIKE 0 AND p_Kind5 LIKE 0";
				com_3_start.removeAllItems();
				list3 = new String[pk_DAO.GetCntStructure(str)];
				list3 = getSelectedStructure(str);
				for (int i = 0; i < list3.length; i++) {
					com_3_start.addItem(list3[i]);
				}
				com_3_start.setSelectedIndex(-1);
			}
		});
		com_3_start.setSelectedIndex(-1);
		add(com_3_start); // panel에 추가
		
		/* 4 */
		lbl_4 = new JLabel(btn4);
		lbl_4.setBounds(lbl_3.getX(), (lbl_3.getY()+lbl_3.getHeight())+20, num_W, num_W);
		lbl_4.setVerticalAlignment(SwingConstants.CENTER);
		lbl_4.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_4);

		/* 4 선택 */
		com_4_start = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_4_start.setFont(font_BM); // 폰트
		com_4_start.setBounds((lbl_4.getX() + lbl_4.getWidth()) + 20, lbl_4.getY(), btn_W, lbl_4.getHeight()); // 위치랑 사이즈
		str = "p_Kind1 LIKE " + (com_1_start.getSelectedIndex() + 1) + " AND p_Kind2 LIKE " + (com_2_start.getSelectedIndex() + 1)
				+ " AND p_Kind3 LIKE " + (com_3_start.getSelectedIndex() + 1) + " AND p_Kind4 NOT LIKE 0 AND p_Kind5 LIKE 0";
		list4 = new String[pk_DAO.GetCntStructure(str)];
		list4 = getSelectedStructure(str);
		for (int i = 0; i < list4.length; i++) {
			com_4_start.addItem(list4[i]);
		}
		com_3_start.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				str = "p_Kind1 LIKE " + (com_1_start.getSelectedIndex() + 1) + " AND p_Kind2 LIKE "
						+ (com_2_start.getSelectedIndex() + 1) + " AND p_Kind3 LIKE " + (com_3_start.getSelectedIndex() + 1)
						+ " AND p_Kind4 NOT LIKE 0 AND p_Kind5 LIKE 0";
				com_4_start.removeAllItems();
				list4 = new String[pk_DAO.GetCntStructure(str)];
				list4 = getSelectedStructure(str);
				for (int i = 0; i < list4.length; i++) {
					com_4_start.addItem(list4[i]);
				}
				com_4_start.setSelectedIndex(-1);
			}
		});
		com_4_start.setSelectedIndex(-1);
		add(com_4_start); // panel에 추가
		
		/* 5 */
		lbl_5 = new JLabel(btn5);
		lbl_5.setBounds(lbl_4.getX(), (lbl_4.getY()+lbl_4.getHeight())+20, num_W, num_W);
		lbl_5.setVerticalAlignment(SwingConstants.CENTER);
		lbl_5.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_5);

		/* 5 선택 */
		com_5_start = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_5_start.setFont(font_BM); // 폰트
		com_5_start.setBounds((lbl_5.getX() + lbl_5.getWidth()) + 20, lbl_5.getY(), btn_W, lbl_5.getHeight()); // 위치랑 사이즈
		str = "p_Kind1 LIKE " + (com_1_start.getSelectedIndex() + 1) + " AND p_Kind2 LIKE " + (com_2_start.getSelectedIndex() + 1)
				+ " AND p_Kind3 LIKE " + (com_3_start.getSelectedIndex() + 1) + " AND p_Kind4 LIKE "
				+ (com_4_start.getSelectedIndex() + 1) + " AND p_Kind5 NOT LIKE 0";
		list5 = new String[pk_DAO.GetCntStructure(str)];
		list5 = getSelectedStructure(str);
		for (int i = 0; i < list3.length; i++) {
			com_3_start.addItem(list3[i]);
		}
		com_4_start.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				str = "p_Kind1 LIKE " + (com_1_start.getSelectedIndex() + 1) + " AND p_Kind2 LIKE "
						+ (com_2_start.getSelectedIndex() + 1) + " AND p_Kind3 LIKE " + (com_3_start.getSelectedIndex() + 1)
						+ " AND p_Kind4 LIKE " + (com_4_start.getSelectedIndex() + 1) + " AND p_Kind5 NOT LIKE 0";
				com_5_start.removeAllItems();
				list5 = new String[pk_DAO.GetCntStructure(str)];
				list5 = getSelectedStructure(str);
				for (int i = 0; i < list5.length; i++) {
					com_5_start.addItem(list4[i]);
				}
				com_5_start.setSelectedIndex(-1);
			}
		});
		com_5_start.setSelectedIndex(-1);
		add(com_5_start); // panel에 추가
		
		/* 에서 */
		label = new JLabel("에서");
		label.setFont(font_L);
		label.setForeground(Color.BLACK);
		label.setBounds((com_5_start.getX()+com_5_start.getWidth())+20, com_5_start.getY(), com_5_start.getWidth()/2, com_5_start.getHeight());

		panel.add(label);
		
		/* 1 */
		lbl_1 = new JLabel(btn1);
		lbl_1.setBounds(lbl_5.getX(), (lbl_5.getY()+lbl_5.getHeight())+50, num_W, num_W);
		lbl_1.setVerticalAlignment(SwingConstants.CENTER);
		lbl_1.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_1);

		/* 1 선택 */
		com_1_end = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_1_end.setFont(font_BM); // 폰트
		com_1_end.setBounds((lbl_1.getX() + lbl_1.getWidth()) + 20, lbl_1.getY(), btn_W, lbl_1.getHeight()); // 위치랑 사이즈
		str = "p_Kind1 NOT LIKE 0 AND p_Kind2 LIKE 0 AND p_Kind3 LIKE 0 AND p_Kind4 LIKE 0 AND p_Kind5 LIKE 0";
		list1 = new String[pk_DAO.GetCntStructure(str)];
		list1 = getSelectedStructure(str);
		for (int i = 0; i < list1.length; i++) {
			com_1_end.addItem(list1[i]);
		}

		add(com_1_end); // panel에 추가
		
		/* 2 */
		lbl_2 = new JLabel(btn2);
		lbl_2.setBounds(lbl_1.getX(), (lbl_1.getY()+lbl_1.getHeight())+20, num_W, num_W);
		lbl_2.setVerticalAlignment(SwingConstants.CENTER);
		lbl_2.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_2);

		/* 2 선택 */
		com_2_end = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_2_end.setFont(font_BM); // 폰트
		com_2_end.setBounds((lbl_2.getX() + lbl_2.getWidth()) + 20, lbl_2.getY(), btn_W, lbl_2.getHeight()); // 위치랑 사이즈
		str = "p_Kind1 LIKE " + (com_1_end.getSelectedIndex() + 1)
				+ " AND p_Kind2 NOT LIKE 0 AND p_Kind3 LIKE 0 AND p_Kind4 LIKE 0 AND p_Kind5 LIKE 0";
		list2 = new String[pk_DAO.GetCntStructure(str)];
		list2 = getSelectedStructure(str);
		for (int i = 0; i < list2.length; i++) {
			com_2_end.addItem(list2[i]);
		}
		com_1_end.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				str = "p_Kind1 LIKE " + (com_1_end.getSelectedIndex() + 1)
						+ " AND p_Kind2 NOT LIKE 0 AND p_Kind3 LIKE 0 AND p_Kind4 LIKE 0 AND p_Kind5 LIKE 0";
				com_2_end.removeAllItems();
				list2 = new String[pk_DAO.GetCntStructure(str)];
				list2 = getSelectedStructure(str);
				for (int i = 0; i < list2.length; i++) {
					com_2_end.addItem(list2[i]);
				}
				com_2_end.setSelectedIndex(-1);
			}
		});
		com_2_end.setSelectedIndex(-1);
		add(com_2_end); // panel에 추가
		
		/* 3 */
		lbl_3 = new JLabel(btn3);
		lbl_3.setBounds(lbl_2.getX(), (lbl_2.getY()+lbl_2.getHeight())+20, num_W, num_W);
		lbl_3.setVerticalAlignment(SwingConstants.CENTER);
		lbl_3.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_3);

		/* 3 선택 */
		com_3_end = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_3_end.setFont(font_BM); // 폰트
		com_3_end.setBounds((lbl_3.getX() + lbl_3.getWidth()) + 20, lbl_3.getY(), btn_W, lbl_3.getHeight()); // 위치랑 사이즈
		str = "p_Kind1 LIKE " + (com_1_end.getSelectedIndex() + 1) + " AND p_Kind2 LIKE " + (com_2_end.getSelectedIndex() + 1)
				+ " AND p_Kind3 NOT LIKE 0 AND p_Kind4 LIKE 0 AND p_Kind5 LIKE 0";
		list3 = new String[pk_DAO.GetCntStructure(str)];
		list3 = getSelectedStructure(str);
		for (int i = 0; i < list3.length; i++) {
			com_3_end.addItem(list3[i]);
		}
		com_2_end.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				str = "p_Kind1 LIKE " + (com_1_end.getSelectedIndex() + 1) + " AND p_Kind2 LIKE "
						+ (com_2_end.getSelectedIndex() + 1)
						+ " AND p_Kind3 NOT LIKE 0 AND p_Kind4 LIKE 0 AND p_Kind5 LIKE 0";
				com_3_end.removeAllItems();
				list3 = new String[pk_DAO.GetCntStructure(str)];
				list3 = getSelectedStructure(str);
				for (int i = 0; i < list3.length; i++) {
					com_3_end.addItem(list3[i]);
				}
				com_3_end.setSelectedIndex(-1);
			}
		});
		com_3_end.setSelectedIndex(-1);
		add(com_3_end); // panel에 추가
		
		/* 4 */
		lbl_4 = new JLabel(btn4);
		lbl_4.setBounds(lbl_3.getX(), (lbl_3.getY()+lbl_3.getHeight())+20, num_W, num_W);
		lbl_4.setVerticalAlignment(SwingConstants.CENTER);
		lbl_4.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_4);

		/* 4 선택 */
		com_4_end = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_4_end.setFont(font_BM); // 폰트
		com_4_end.setBounds((lbl_4.getX() + lbl_4.getWidth()) + 20, lbl_4.getY(), btn_W, lbl_4.getHeight()); // 위치랑 사이즈
		str = "p_Kind1 LIKE " + (com_1_end.getSelectedIndex() + 1) + " AND p_Kind2 LIKE " + (com_2_end.getSelectedIndex() + 1)
				+ " AND p_Kind3 LIKE " + (com_3_end.getSelectedIndex() + 1) + " AND p_Kind4 NOT LIKE 0 AND p_Kind5 LIKE 0";
		list4 = new String[pk_DAO.GetCntStructure(str)];
		list4 = getSelectedStructure(str);
		for (int i = 0; i < list4.length; i++) {
			com_4_end.addItem(list4[i]);
		}
		com_3_end.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				str = "p_Kind1 LIKE " + (com_1_end.getSelectedIndex() + 1) + " AND p_Kind2 LIKE "
						+ (com_2_end.getSelectedIndex() + 1) + " AND p_Kind3 LIKE " + (com_3_end.getSelectedIndex() + 1)
						+ " AND p_Kind4 NOT LIKE 0 AND p_Kind5 LIKE 0";
				com_4_end.removeAllItems();
				list4 = new String[pk_DAO.GetCntStructure(str)];
				list4 = getSelectedStructure(str);
				for (int i = 0; i < list4.length; i++) {
					com_4_end.addItem(list4[i]);
				}
				com_4_end.setSelectedIndex(-1);
			}
		});
		com_4_end.setSelectedIndex(-1);
		add(com_4_end); // panel에 추가
		
		/* 5 */
		lbl_5 = new JLabel(btn5);
		lbl_5.setBounds(lbl_4.getX(), (lbl_4.getY()+lbl_4.getHeight())+20, num_W, num_W);
		lbl_5.setVerticalAlignment(SwingConstants.CENTER);
		lbl_5.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_5);

		/* 5 선택 */
		com_5_end = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_5_end.setFont(font_BM); // 폰트
		com_5_end.setBounds((lbl_5.getX() + lbl_5.getWidth()) + 20, lbl_5.getY(), btn_W, lbl_5.getHeight()); // 위치랑 사이즈
		str = "p_Kind1 LIKE " + (com_1_end.getSelectedIndex() + 1) + " AND p_Kind2 LIKE " + (com_2_end.getSelectedIndex() + 1)
				+ " AND p_Kind3 LIKE " + (com_3_end.getSelectedIndex() + 1) + " AND p_Kind4 LIKE "
				+ (com_4_end.getSelectedIndex() + 1) + " AND p_Kind5 NOT LIKE 0";
		list5 = new String[pk_DAO.GetCntStructure(str)];
		list5 = getSelectedStructure(str);
		for (int i = 0; i < list3.length; i++) {
			com_3_end.addItem(list3[i]);
		}
		com_4_end.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				str = "p_Kind1 LIKE " + (com_1_end.getSelectedIndex() + 1) + " AND p_Kind2 LIKE "
						+ (com_2_end.getSelectedIndex() + 1) + " AND p_Kind3 LIKE " + (com_3_end.getSelectedIndex() + 1)
						+ " AND p_Kind4 LIKE " + (com_4_end.getSelectedIndex() + 1) + " AND p_Kind5 NOT LIKE 0";
				com_5_end.removeAllItems();
				list5 = new String[pk_DAO.GetCntStructure(str)];
				list5 = getSelectedStructure(str);
				for (int i = 0; i < list5.length; i++) {
					com_5_end.addItem(list4[i]);
				}
				com_5_end.setSelectedIndex(-1);
			}
		});
		com_5_end.setSelectedIndex(-1);
		add(com_5_end); // panel에 추가
		
		/* 까지 */
		label = new JLabel("까지");
		label.setFont(font_L);
		label.setForeground(Color.BLACK);
		label.setBounds((com_5_end.getX()+com_5_end.getWidth())+20, com_5_end.getY(), com_5_end.getWidth()/2+10, com_5_end.getHeight());

		panel.add(label);
		
		/* 선택창 배경 */
		lbl_back1 = new JLabel();
		lbl_back1.setBounds(17, (btn_Home.getY()+btn_Home.getHeight())+30, width/2-50, height-(height/15+95));
		lbl_back1.setOpaque(true);
		lbl_back1.setBackground(Color.WHITE); // 뒤에 배경
		lbl_back1.setVerticalAlignment(SwingConstants.CENTER);
		lbl_back1.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_back1);

		/* PDF 버튼 */
		btn_PDF = new JButton(btnPDF);
		btn_PDF.setSelectedIcon(btnPDF_push);
		btn_PDF.setRolloverIcon(btnPDF_push);
		btn_PDF.setBorderPainted(false);
		btn_PDF.setFocusPainted(false);
		btn_PDF.setContentAreaFilled(false);
		btn_PDF.setBounds((10 + btn_W * 3 / 2) + 18, (btn_Home.getY()+btn_Home.getHeight())+12, btn_W * 3 / 2, btn_H);
		btn_PDF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<P_KeyPoint_DTO> val = GetPDFInfo();
				
				try {
					MakePDF mPDF = new MakePDF(val);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		panel.add(btn_PDF);

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
	
	//DB에서 범위 값 가져오기
	public List<P_KeyPoint_DTO> GetPDFInfo(){
		List<P_KeyPoint_DTO> result = null;
		int[] startK = new int[5];
		int[] endK = new int[5];
		
		startK[0] = com_1_start.getSelectedIndex()+1;
		startK[1] = com_2_start.getSelectedIndex()+1;
		startK[2] = com_3_start.getSelectedIndex()+1;
		startK[3] = com_4_start.getSelectedIndex()+1;
		startK[4] = com_5_start.getSelectedIndex()+1;
		
		endK[0] = com_1_end.getSelectedIndex()+1;
		endK[1] = com_2_end.getSelectedIndex()+1;
		endK[2] = com_3_end.getSelectedIndex()+1;
		endK[3] = com_4_end.getSelectedIndex()+1;
		endK[4] = com_5_end.getSelectedIndex()+1;
				
		pk_DAO = new P_KeyPoint_DAO();
		result = pk_DAO.SearchData(startK, endK);
		
		return result;
	}
}
