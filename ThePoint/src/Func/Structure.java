package Func;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.table.TableModel;

import DAO.M_Info_DAO;
import DAO.P_KeyPoint_DAO;
import DTO.M_Info_DTO;
import DTO.P_KeyPoint_DTO;

public class Structure extends JFrame {
	// 컴포넌트
	private JPanel panel; // Panel 등록해서 객체로 쓰고
	private JButton btn_background, btn_Home, btn_Plus, btn_Minus, btn_Input;
	private JLabel label, lbl_1, lbl_2, lbl_3, lbl_4, lbl_5;
	private JTextArea tArea_Info;
	private JTable table_Structure;
	private JScrollPane scroll;
	private JTextField txt_1, txt_2, txt_3, txt_4, txt_5;
	private Vector<String> col = new Vector<String>(); // 테이블 컬럼
	private Vector row = new Vector(); // 테이블 내용
	private DefaultTableModel model;

	// 크기 조절
	private int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private int width = x * 3 / 4, height = y * 4 / 5;
	private int btn_W = (((width - 40) / 3) - 10) / 6 - 2, btn_H = height / 6 + 15;
	private int num_W = (((width - 40) / 3) - 10) / 8;

	// 글씨체
	private Font font_L = new Font("맑은 고딕", Font.BOLD, 40 + y / 250);
	private Font font_BM = new Font("맑은 고딕", Font.BOLD, 30 + y / 250);
	private Font font_PS = new Font("맑은 고딕", Font.PLAIN, 20 + y / 250);
	private Font font_PSS = new Font("맑은 고딕", Font.PLAIN, 10 + y / 250);

	// 이미지
	private ImageIcon BarIcon = new ImageIcon("images/icon.png");

	/* Home 버튼 */
	private ImageIcon btnHome1 = new ImageIcon("images/button/btnhome.png");
	private Image imgbtnHome = btnHome1.getImage();
	private Image imgbtnHome2 = imgbtnHome.getScaledInstance(height / 15 - 5, height / 15 - 5, Image.SCALE_SMOOTH);
	private ImageIcon btnHome = new ImageIcon(imgbtnHome2);

	private ImageIcon btnStruct_push1 = new ImageIcon("images/pushedbutton/btnstruct_push.png");
	private Image imgbtnStruct_push = btnStruct_push1.getImage();
	private Image imgbtnStruct_push2 = imgbtnStruct_push.getScaledInstance(height / 15 - 5, height / 15 - 5,
			Image.SCALE_SMOOTH);
	private ImageIcon btnStruct_push = new ImageIcon(imgbtnStruct_push2);

	/* 1 */
	private ImageIcon btn11 = new ImageIcon("images/button/btn1.png");
	private Image imgbtn1 = btn11.getImage();
	private Image imgbtn12 = imgbtn1.getScaledInstance(num_W, num_W, Image.SCALE_SMOOTH);
	private ImageIcon btn1 = new ImageIcon(imgbtn12);

	/* 2 */
	private ImageIcon btn21 = new ImageIcon("images/button/btn2.png");
	private Image imgbtn2 = btn21.getImage();
	private Image imgbtn22 = imgbtn2.getScaledInstance(num_W, num_W, Image.SCALE_SMOOTH);
	private ImageIcon btn2 = new ImageIcon(imgbtn22);

	/* 3 */
	private ImageIcon btn31 = new ImageIcon("images/button/btn3.png");
	private Image imgbtn3 = btn31.getImage();
	private Image imgbtn32 = imgbtn3.getScaledInstance(num_W, num_W, Image.SCALE_SMOOTH);
	private ImageIcon btn3 = new ImageIcon(imgbtn32);

	/* 4 */
	private ImageIcon btn41 = new ImageIcon("images/button/btn4.png");
	private Image imgbtn4 = btn41.getImage();
	private Image imgbtn42 = imgbtn4.getScaledInstance(num_W, num_W, Image.SCALE_SMOOTH);
	private ImageIcon btn4 = new ImageIcon(imgbtn42);

	/* 5 */
	private ImageIcon btn51 = new ImageIcon("images/button/btn5.png");
	private Image imgbtn5 = btn51.getImage();
	private Image imgbtn52 = imgbtn5.getScaledInstance(num_W, num_W, Image.SCALE_SMOOTH);
	private ImageIcon btn5 = new ImageIcon(imgbtn52);

	/* Minus 버튼 */
	private ImageIcon btnMinus1 = new ImageIcon("images/button/btnMinus.png");
	private Image imgbtnMinus = btnMinus1.getImage();
	private Image imgbtnMinus2 = imgbtnMinus.getScaledInstance(btn_W * 2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnMinus = new ImageIcon(imgbtnMinus2);

	private ImageIcon btnMinus_push1 = new ImageIcon("images/pushedbutton/btnMinus_push.png");
	private Image imgbtnMinus_push = btnMinus_push1.getImage();
	private Image imgbtnMinus_push2 = imgbtnMinus_push.getScaledInstance(btn_W * 2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnMinus_push = new ImageIcon(imgbtnMinus_push2);

	/* Plus 버튼 */
	private ImageIcon btnPlus1 = new ImageIcon("images/button/btnPlus.png");
	private Image imgbtnPlus = btnPlus1.getImage();
	private Image imgbtnPlus2 = imgbtnPlus.getScaledInstance(btn_W * 2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnPlus = new ImageIcon(imgbtnPlus2);

	private ImageIcon btnPlus_push1 = new ImageIcon("images/pushedbutton/btnPlus_push.png");
	private Image imgbtnPlus_push = btnPlus_push1.getImage();
	private Image imgbtnPlus_push2 = imgbtnPlus_push.getScaledInstance(btn_W * 2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnPlus_push = new ImageIcon(imgbtnPlus_push2);

	/* 입력 버튼 */
	private ImageIcon btnInput1 = new ImageIcon("images/button/btnInput.png");
	private Image imgbtnInput = btnInput1.getImage();
	private Image imgbtnInput2 = imgbtnInput.getScaledInstance(btn_W * 2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnInput = new ImageIcon(imgbtnInput2);

	private ImageIcon btnInput_push1 = new ImageIcon("images/pushedbutton/btnInput_push.png");
	private Image imgbtnInput_push = btnInput_push1.getImage();
	private Image imgbtnInput_push2 = imgbtnInput_push.getScaledInstance(btn_W * 2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnInput_push = new ImageIcon(imgbtnInput_push2);

	/* DB 변수 */
	private static M_Info_DAO mi_DAO = null;
	private static M_Info_DTO mi_DTO = null;
	private static P_KeyPoint_DAO pk_DAO = null;
	private static P_KeyPoint_DTO pk_DTO = null;
	private static P_KeyPoint_DTO sel_DTO = null;

	//////////////////////////////////////////////////////////////////////////////////////

	public Structure() {
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

		/* 선택된 정보 */
		tArea_Info = new JTextArea();
		tArea_Info.setFont(font_BM);
		tArea_Info.setForeground(Color.WHITE);
		tArea_Info.setOpaque(true);
		tArea_Info.setBackground(new java.awt.Color(119, 150, 242)); // 뒤에 배경
		tArea_Info.setLineWrap(true);

		panel.add(tArea_Info);

		/* 1 */
		lbl_1 = new JLabel(btn1);
		lbl_1.setVerticalAlignment(SwingConstants.CENTER);
		lbl_1.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_1);

		/* 1 입력 */
		txt_1 = new JTextField(20); // 텍스트필드 재정의
		txt_1.setFont(font_PS); // 글씨체

		add(txt_1);

		/* 2 */
		lbl_2 = new JLabel(btn2);
		lbl_2.setVerticalAlignment(SwingConstants.CENTER);
		lbl_2.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_2);

		/* 2 입력 */
		txt_2 = new JTextField(20); // 텍스트필드 재정의
		txt_2.setFont(font_PS); // 글씨체

		add(txt_2);

		/* 3 */
		lbl_3 = new JLabel(btn3);
		lbl_3.setVerticalAlignment(SwingConstants.CENTER);
		lbl_3.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_3);

		/* 3 입력 */
		txt_3 = new JTextField(20); // 텍스트필드 재정의
		txt_3.setFont(font_PS); // 글씨체

		add(txt_3);

		/* 4 */
		lbl_4 = new JLabel(btn4);
		lbl_4.setVerticalAlignment(SwingConstants.CENTER);
		lbl_4.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_4);

		/* 4 입력 */
		txt_4 = new JTextField(20); // 텍스트필드 재정의
		txt_4.setFont(font_PS); // 글씨체

		add(txt_4);

		/* 5 */
		lbl_5 = new JLabel(btn5);
		lbl_5.setVerticalAlignment(SwingConstants.CENTER);
		lbl_5.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_5);

		/* 5 입력 */
		txt_5 = new JTextField(20); // 텍스트필드 재정의
		txt_5.setFont(font_PS); // 글씨체

		add(txt_5);

		/* 입력 버튼 */
		btn_Plus = new JButton(btnPlus);
		btn_Plus.setSelectedIcon(btnPlus_push);
		btn_Plus.setRolloverIcon(btnPlus_push);
		btn_Plus.setBorderPainted(false);
		btn_Plus.setFocusPainted(false);
		btn_Plus.setContentAreaFilled(false);
		btn_Plus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sel_DTO == null) {
					int chk_first = JOptionPane.showConfirmDialog(null, "처음 목차 입력 시, 전체가 초기화 됩니다.\n첫 목차 입력입니까?",
							"입력여부 확인", JOptionPane.YES_NO_OPTION);
					if (chk_first == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(null, "오호", "성공", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "추가할 위치의 목차를 선택해주세요", "오류", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					pk_DAO = new P_KeyPoint_DAO();
					// 입력 하고 결과 확인
					boolean result1 = false;
					if (!txt_1.getText().toString().equals("")) {
						result1 = pk_DAO.addNew(sel_DTO.getP_Kind1() + 1, 0, 0, 0, 0, txt_1.getText().toString());
						if (result1 == true) {
							JOptionPane.showMessageDialog(null, "입력 되었습니다", "입력 완료", JOptionPane.WARNING_MESSAGE);
							Reset();
						} else {
							JOptionPane.showMessageDialog(null, "오류가 발생했습니다", "오류", JOptionPane.WARNING_MESSAGE);
						}
					} else if (!txt_2.getText().toString().equals("")) {
						result1 = pk_DAO.addNew(sel_DTO.getP_Kind1(), sel_DTO.getP_Kind2() + 1, 0, 0, 0,
								txt_2.getText().toString());
						if (result1 == true) {
							JOptionPane.showMessageDialog(null, "입력 되었습니다", "입력 완료", JOptionPane.WARNING_MESSAGE);
							Reset();
						} else {
							JOptionPane.showMessageDialog(null, "오류가 발생했습니다", "오류", JOptionPane.WARNING_MESSAGE);
						}
					} else if (!txt_3.getText().toString().equals("")) {
						result1 = pk_DAO.addNew(sel_DTO.getP_Kind1(), sel_DTO.getP_Kind2(), sel_DTO.getP_Kind3() + 1, 0,
								0, txt_3.getText().toString());
						if (result1 == true) {
							JOptionPane.showMessageDialog(null, "입력 되었습니다", "입력 완료", JOptionPane.WARNING_MESSAGE);
							Reset();
						} else {
							JOptionPane.showMessageDialog(null, "오류가 발생했습니다", "오류", JOptionPane.WARNING_MESSAGE);
						}
					} else if (!txt_4.getText().toString().equals("")) {
						result1 = pk_DAO.addNew(sel_DTO.getP_Kind1(), sel_DTO.getP_Kind2(), sel_DTO.getP_Kind3(),
								sel_DTO.getP_Kind4() + 1, 0, txt_4.getText().toString());
						if (result1 == true) {
							JOptionPane.showMessageDialog(null, "입력 되었습니다", "입력 완료", JOptionPane.WARNING_MESSAGE);
							Reset();
						} else {
							JOptionPane.showMessageDialog(null, "오류가 발생했습니다", "오류", JOptionPane.WARNING_MESSAGE);
						}
					} else if (!txt_5.getText().toString().equals("")) {
						result1 = pk_DAO.addNew(sel_DTO.getP_Kind1(), sel_DTO.getP_Kind2(), sel_DTO.getP_Kind3(),
								sel_DTO.getP_Kind4(), sel_DTO.getP_Kind4() + 1, txt_5.getText().toString());
						if (result1 == true) {
							JOptionPane.showMessageDialog(null, "입력 되었습니다", "입력 완료", JOptionPane.WARNING_MESSAGE);
							Reset();
						} else {
							JOptionPane.showMessageDialog(null, "오류가 발생했습니다", "오류", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "내용을 입력해주세요", "오류", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		panel.add(btn_Plus);

		/* 수정 버튼 */
		btn_Input = new JButton(btnInput);
		btn_Input.setSelectedIcon(btnInput_push);
		btn_Input.setRolloverIcon(btnInput_push);
		btn_Input.setBorderPainted(false);
		btn_Input.setFocusPainted(false);
		btn_Input.setContentAreaFilled(false);
		btn_Input.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sel_DTO == null) {
					JOptionPane.showMessageDialog(null, "수정할 목차를 선택해주세요", "오류", JOptionPane.WARNING_MESSAGE);
				} else {
					int result1 = JOptionPane.showConfirmDialog(null, "'" + tArea_Info.getText() + "'을(를) 수정하시겠습니까?",
							"수정여부 확인", JOptionPane.YES_NO_OPTION);
					if (result1 == JOptionPane.YES_OPTION) { // Yes
						// 수정 하고 결과 확인
						String info = null;
						
						if (!txt_1.getText().toString().equals("")) {
							info = txt_1.getText().toString();
						} else if (!txt_2.getText().toString().equals("")) {
							info = txt_2.getText().toString();
						} else if (!txt_3.getText().toString().equals("")) {
							info = txt_3.getText().toString();
						} else if (!txt_4.getText().toString().equals("")) {
							info = txt_4.getText().toString();
						} else if (!txt_5.getText().toString().equals("")) {
							info = txt_5.getText().toString();
						} else {
							info = null;
						}
						
						if(info == null) {
							JOptionPane.showMessageDialog(null, "내용을 입력해주세요", "오류", JOptionPane.WARNING_MESSAGE);
						} else {
							boolean result2 = false;
							pk_DAO = new P_KeyPoint_DAO();
							result2 = pk_DAO.ResetRow(sel_DTO.getP_Kind1(), sel_DTO.getP_Kind2(), sel_DTO.getP_Kind3(),
								sel_DTO.getP_Kind4(), sel_DTO.getP_Kind5(), info);
							if (result2 == true) {
								JOptionPane.showMessageDialog(null, "수정 되었습니다", "수정 완료", JOptionPane.WARNING_MESSAGE);
								Reset();
							} else {
								JOptionPane.showMessageDialog(null, "오류가 발생했습니다", "오류", JOptionPane.WARNING_MESSAGE);
							}
						}
						
					} else { // No 또는 창을 그냥 닫은 경우
						JOptionPane.showMessageDialog(null, "취소되었습니다", "취소", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		panel.add(btn_Input);

		/* Minus 버튼 */
		btn_Minus = new JButton(btnMinus);
		btn_Minus.setSelectedIcon(btnMinus_push);
		btn_Minus.setRolloverIcon(btnMinus_push);
		btn_Minus.setBorderPainted(false);
		btn_Minus.setFocusPainted(false);
		btn_Minus.setContentAreaFilled(false);
		btn_Minus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sel_DTO == null) {
					JOptionPane.showMessageDialog(null, "삭제할 목차를 선택해주세요", "오류", JOptionPane.WARNING_MESSAGE);
				} else {
					int result1 = JOptionPane.showConfirmDialog(null, "'" + tArea_Info.getText() + "'을(를) 삭제하시겠습니까?",
							"삭제여부 확인", JOptionPane.YES_NO_OPTION);
					if (result1 == JOptionPane.YES_OPTION) { // Yes
						pk_DAO = new P_KeyPoint_DAO();

						// 삭제 시키고 결과 확인
						boolean result2 = false;
						result2 = pk_DAO.RemoveRow(sel_DTO.getP_Kind1(), sel_DTO.getP_Kind2(), sel_DTO.getP_Kind3(),
								sel_DTO.getP_Kind4(), sel_DTO.getP_Kind5());

						if (result2 == true) {
							JOptionPane.showMessageDialog(null, "삭제되었습니다", "삭제", JOptionPane.WARNING_MESSAGE);
							Reset();
						} else {
							JOptionPane.showMessageDialog(null, "오류가 발생했습니다", "오류", JOptionPane.WARNING_MESSAGE);
						}
					} else { // No 또는 창을 그냥 닫은 경우
						JOptionPane.showMessageDialog(null, "취소되었습니다", "취소", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		panel.add(btn_Minus);

		/* Table - 목차 띄워주는 테이블 */
		pk_DAO = new P_KeyPoint_DAO();
		pk_DTO = new P_KeyPoint_DTO();
		row = pk_DAO.getTable();
		setCell();
		model = new DefaultTableModel(row, col) {
			// 셀 수정 못하게 하는 부분
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_Structure = new JTable(model);
		table_Structure.setFont(font_PS); // 글씨체
		table_Structure.setRowHeight(50);
		table_Structure.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table_Structure.getSelectedRow();
				TableModel md = table_Structure.getModel();
				txt_1.setText("");
				txt_2.setText("");
				txt_3.setText("");
				txt_4.setText("");
				txt_5.setText("");

				String str = "";
				if (!md.getValueAt(row, 0).toString().equals("")) {
					str = md.getValueAt(row, 0).toString();
				} else if (!md.getValueAt(row, 1).toString().equals("")) {
					str = md.getValueAt(row, 1).toString();
				} else if (!md.getValueAt(row, 2).toString().equals("")) {
					str = md.getValueAt(row, 2).toString();
				} else if (!md.getValueAt(row, 3).toString().equals("")) {
					str = md.getValueAt(row, 3).toString();
				} else if (!md.getValueAt(row, 4).toString().equals("")) {
					str = md.getValueAt(row, 4).toString();
				} else {
					str = "";
				}

				sel_DTO = pk_DAO.SearchData(str);
				tArea_Info.setText(sel_DTO.getP_Kind_Info());
			}
		});

		scroll = new JScrollPane(table_Structure);
		scroll.setViewportView(table_Structure);
		scroll.setBounds(10, (label.getY() + label.getHeight()) + 10, (width - 30) * 2 / 3,
				height - (label.getHeight() + 60));

		panel.add(scroll); // 스크롤 추가

		// 각 컴포넌트 위치
		tArea_Info.setBounds((scroll.getX() + scroll.getWidth()) + 10, scroll.getY(), (width - 40) / 3, height / 4);
		lbl_1.setBounds(tArea_Info.getX(), (tArea_Info.getY() + tArea_Info.getHeight()) + 15, num_W, num_W);
		txt_1.setBounds((lbl_1.getX() + lbl_1.getWidth()) + 10, lbl_1.getY(), tArea_Info.getWidth() - (num_W + 10),
				lbl_1.getHeight()); // 위치랑 사이즈
		lbl_2.setBounds(lbl_1.getX(), (lbl_1.getY() + lbl_1.getHeight()) + 15, num_W, num_W);
		txt_2.setBounds((lbl_2.getX() + lbl_2.getWidth()) + 10, lbl_2.getY(), tArea_Info.getWidth() - (num_W + 10),
				lbl_2.getHeight()); // 위치랑 사이즈
		lbl_3.setBounds(lbl_2.getX(), (lbl_2.getY() + lbl_2.getHeight()) + 15, num_W, num_W);
		txt_3.setBounds((lbl_3.getX() + lbl_3.getWidth()) + 10, lbl_3.getY(), tArea_Info.getWidth() - (num_W + 10),
				lbl_3.getHeight()); // 위치랑 사이즈
		lbl_4.setBounds(lbl_3.getX(), (lbl_3.getY() + lbl_3.getHeight()) + 15, num_W, num_W);
		txt_4.setBounds((lbl_4.getX() + lbl_4.getWidth()) + 10, lbl_4.getY(), tArea_Info.getWidth() - (num_W + 10),
				lbl_4.getHeight()); // 위치랑 사이즈
		lbl_5.setBounds(lbl_4.getX(), (lbl_4.getY() + lbl_4.getHeight()) + 15, num_W, num_W);
		txt_5.setBounds((lbl_5.getX() + lbl_5.getWidth()) + 10, lbl_5.getY(), tArea_Info.getWidth() - (num_W + 10),
				lbl_5.getHeight()); // 위치랑 사이즈
		btn_Plus.setBounds(tArea_Info.getX(), (lbl_5.getY() + lbl_5.getHeight()) + 10, btn_W * 2, btn_H);
		btn_Input.setBounds((btn_Plus.getX() + btn_Plus.getWidth()) + 10, btn_Plus.getY(), btn_W * 2, btn_H);
		btn_Minus.setBounds((btn_Input.getX() + btn_Input.getWidth()) + 10, btn_Input.getY(), btn_W * 2, btn_H);
	}

	// 테이블 셀
	public void setCell() {
		col.addElement("1");
		col.addElement("2");
		col.addElement("3");
		col.addElement("4");
		col.addElement("5");
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

	// 리셋
	public void Reset() {
		sel_DTO = null;
		tArea_Info.setText("");
		txt_1.setText("");
		txt_2.setText("");
		txt_3.setText("");
		txt_4.setText("");
		txt_5.setText("");

		table_Structure.removeAll();
		pk_DAO = new P_KeyPoint_DAO();
		pk_DTO = new P_KeyPoint_DTO();
		row = pk_DAO.getTable();
		model = new DefaultTableModel(row, col) {
			// 셀 수정 못하게 하는 부분
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_Structure.setModel(model);
	}
}
