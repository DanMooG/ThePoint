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

import DAO.M_Info_DAO;
import DTO.M_Info_DTO;

public class ToFile extends JFrame {
	// 컴포넌트
	private JPanel panel; // Panel 등록해서 객체로 쓰고
	private JButton btn_Home, btn_Word, btn_PDF;
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

	/* Home 버튼 */
	private ImageIcon btnHome1 = new ImageIcon("images/button/btnhome.png");
	private Image imgbtnHome = btnHome1.getImage();
	private Image imgbtnHome2 = imgbtnHome.getScaledInstance(height / 15 - 5, height / 15 - 5, Image.SCALE_SMOOTH);
	private ImageIcon btnHome = new ImageIcon(imgbtnHome2);

	/* 워드 버튼 */
	private ImageIcon btnWord1 = new ImageIcon("images/button/word.png");
	private Image imgbtnWord = btnWord1.getImage();
	private Image imgbtnWord2 = imgbtnWord.getScaledInstance(btn_W*3/2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnWord = new ImageIcon(imgbtnWord2);

	private ImageIcon btnWord_push1 = new ImageIcon("images/pushedbutton/word_push.png");
	private Image imgbtnWord_push = btnWord_push1.getImage();
	private Image imgbtnWord_push2 = imgbtnWord_push.getScaledInstance(btn_W*3/2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnWord_push = new ImageIcon(imgbtnWord_push2);
	
	/* PDF 버튼 */
	private ImageIcon btnPDF1 = new ImageIcon("images/button/PDF.png");
	private Image imgbtnPDF = btnPDF1.getImage();
	private Image imgbtnPDF2 = imgbtnPDF.getScaledInstance(btn_W*3/2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnPDF = new ImageIcon(imgbtnPDF2);

	private ImageIcon btnPDF_push1 = new ImageIcon("images/pushedbutton/PDF_push.png");
	private Image imgbtnPDF_push = btnPDF_push1.getImage();
	private Image imgbtnPDF_push2 = imgbtnPDF_push.getScaledInstance(btn_W*3/2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnPDF_push = new ImageIcon(imgbtnPDF_push2);
	
	/* DB 변수 */
	private static M_Info_DAO mi_DAO = null;
	private static M_Info_DTO mi_DTO = null;
	
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
		
		/* 워드 버튼 */
		btn_Word = new JButton(btnWord);
		btn_Word.setSelectedIcon(btnWord_push);
		btn_Word.setRolloverIcon(btnWord_push);
		btn_Word.setBorderPainted(false);
		btn_Word.setFocusPainted(false);
		btn_Word.setContentAreaFilled(false);
		btn_Word.setBounds(10, label.getHeight() + 10, btn_W*3/2, btn_H);
		btn_Word.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("WORD");
			}
		});

		panel.add(btn_Word);
		
		/* PDF 버튼 */
		btn_PDF = new JButton(btnPDF);
		btn_PDF.setSelectedIcon(btnPDF_push);
		btn_PDF.setRolloverIcon(btnPDF_push);
		btn_PDF.setBorderPainted(false);
		btn_PDF.setFocusPainted(false);
		btn_PDF.setContentAreaFilled(false);
		btn_PDF.setBounds((btn_Word.getX()+btn_Word.getWidth())+18
				, btn_Word.getY(), btn_W*3/2, btn_H);
		btn_PDF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("PDF");
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
}
