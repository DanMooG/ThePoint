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
	// ������Ʈ
	private JPanel MainFrame; // Panel ����ؼ� ��ü�� ����
	private JLabel label, lbl_Mark, lbl_Name, lbl_Goal, lbl_LKind, lbl_SKind, lbl_Date, lbl_Determin;
	private JTextField txt_Name, txt_LKind, txt_SKind;
	private JButton btn_Start;
	private JComboBox comb_Goal, com_sYear, com_sMonth, com_sDate, com_eYear, com_eMonth, com_eDate;
	private JTextArea txt_Determin;

	// ũ�� ����
	private int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private int width = x * 3 / 4, height = y * 4 / 5;
	private int btn_W = width / 6, btn_H = height / 10;

	// �� �ܿ� ����
	String[] goal = new String[] { "�հ�", "���", "���", "�н�", "����", "���" };
	private Calendar c = Calendar.getInstance(); // ��¥
	private Calendar c_s = Calendar.getInstance(); // ���۳�¥
	private Calendar c_e = Calendar.getInstance(); // ����¥
	String[] year = new String[11];
	String[] month = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
	String[] date = new String[] { "31", "29", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31" };
	int thisyear = c.get(Calendar.YEAR);
	int thismonth = c.get(Calendar.MONTH) + 1;
	int thisdate = c.get(Calendar.DATE);

	// �۾�ü
	private Font font_L = new Font("���� ���", Font.BOLD, 40 + y / 250);
	private Font font_BM = new Font("���� ���", Font.BOLD, 30 + y / 250);
	private Font font_PS = new Font("���� ���", Font.PLAIN, 20 + y / 250);
	private Font font_PSS = new Font("���� ���", Font.PLAIN, 10 + y / 250);

	// �̹���
	private ImageIcon BarIcon = new ImageIcon("images/icon.png");

	/* ���� ������+ThePoint */
	private ImageIcon Mark1 = new ImageIcon("images/mark.png");
	private Image imgMark = Mark1.getImage();
	private Image imgMark2 = imgMark.getScaledInstance(width / 3, height * 2 / 3, Image.SCALE_SMOOTH);
	private ImageIcon Mark = new ImageIcon(imgMark2);

	/* ���� ��ư */
	private ImageIcon btnStart1 = new ImageIcon("images/button/btnstart.png");
	private Image imgbtnStart = btnStart1.getImage();
	private Image imgbtnStart2 = imgbtnStart.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnStart = new ImageIcon(imgbtnStart2);

	/* ���� ���� ��ư */
	private ImageIcon btnStart_push1 = new ImageIcon("images/pushedbutton/btnstart_push.png");
	private Image imgbtnStart_push = btnStart_push1.getImage();
	private Image imgbtnStart_push2 = imgbtnStart_push.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnStart_push = new ImageIcon(imgbtnStart_push2);

	/* DB ���� */
	private static M_Info_DAO mi_DAO = null;
	private static M_Info_DTO mi_DTO = null;

	//////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) { // Main �Լ�
		EventQueue.invokeLater(new Runnable() { // �̰� ����
			public void run() { // ���� �޼���
				try { // �̰� �����ؼ�
					boolean ChkMember = ChkMember();
					if (ChkMember == true) {
						MainScreen ms = new MainScreen();
						ms.setVisible(true);
					} else {
						FirstScreen frame = new FirstScreen(); // �ؿ� Member �޼��� ����, ȸ�� �� MEMBER �ƴ� ����!
						frame.setVisible(true); // ���� �� ������ ���̵��� ����
					}
				} catch (Exception e) { // �������� ���!
					e.printStackTrace();
				}
			}
		});
	}

	public FirstScreen() { // �����ھ� �����ϸ� ���⼭ �ʱ⼳�� ����
		setTitle("The Point"); // Ÿ��Ʋ�� �۾� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �̰� �س��� x������ �ƿ� ���α׷��� ���� ��
		setBounds((x - width) / 2, height / 10, width, height); // �տ� �� ���� ������ ���� ��ġ, �ڿ� �� ���� ������ ������!
		MainFrame = new JPanel(); // ������ ������ ��ü �ʱ�ȭ
		MainFrame.setBorder(new EmptyBorder(5, 5, 5, 5)); // �����¿� ��¦�� ���� �ִ°ž�
		MainFrame.setLayout(new BorderLayout(0, 0)); // �ֶ� Layout �������ش�
		setResizable(false); // ��üȭ�� ���ϰ�
		setContentPane(MainFrame); // MainFrame�� ���ݱ��� �������ذɷ� ������
		setIconImage(BarIcon.getImage()); // ���� ����̶� �۾� ǥ���� ������ ����
		MainFrame.setBackground(new java.awt.Color(167, 186, 242)); // �ڿ� ��� ��
		setLayout(null); // ���� �ٽ� §��
		c_s.set(thisyear, thismonth, thisdate);
		c_e.set(thisyear, thismonth, thisdate);

		// ������
		/** �޼����ڽ� UI **/
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", new java.awt.Color(119, 150, 242));
		UI.put("Panel.background", new java.awt.Color(119, 150, 242));
		UI.put("OptionPane.messageFont", font_L);
		UI.put("OptionPane.messageForeground", Color.WHITE);
		UI.put("OptionPane.buttonFont", font_L);

		/* The Point ������ */
		lbl_Mark = new JLabel(Mark);
		lbl_Mark.setBounds(width / 10, (height - Mark.getIconHeight()) / 2 - 10, Mark.getIconWidth(),
				Mark.getIconHeight());
		lbl_Mark.setVerticalAlignment(SwingConstants.CENTER);
		lbl_Mark.setHorizontalAlignment(SwingConstants.CENTER);

		MainFrame.add(lbl_Mark);

		/* �̸� */
		lbl_Name = new JLabel("�̸�");
		lbl_Name.setFont(font_L);
		lbl_Name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Name.setBounds(lbl_Mark.getX() + lbl_Mark.getWidth(), lbl_Mark.getY() + height / 100, 100 + width / 30,
				30 + height / 50);
		lbl_Name.setForeground(Color.WHITE);

		add(lbl_Name);

		/* �̸� �Է� */
		txt_Name = new JTextField(20); // �ؽ�Ʈ�ʵ� ������
		txt_Name.setBounds((lbl_Name.getX() + lbl_Name.getWidth()) + 10, lbl_Name.getY(), width / 7,
				lbl_Name.getHeight()); // ��ġ�� ������
		txt_Name.setFont(font_PS); // �۾�ü

		add(txt_Name); // �ǳڿ� textfield �߰�

		/* ��ǥ */
		lbl_Goal = new JLabel("��ǥ");
		lbl_Goal.setFont(font_L);
		lbl_Goal.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Goal.setBounds(lbl_Name.getX(), (lbl_Name.getY() + lbl_Name.getHeight()) + 20, 100 + width / 30,
				lbl_Name.getHeight());
		lbl_Goal.setForeground(Color.WHITE);

		add(lbl_Goal);

		/* ��ǥ �Է� */
		comb_Goal = new JComboBox<String>(goal); // �޺��ڽ� �ʱ�ȭ�ε� String �����̾�
		comb_Goal.setFont(font_PS); // ��Ʈ
		comb_Goal.setBounds((lbl_Goal.getX() + lbl_Goal.getWidth()) + 10, lbl_Goal.getY(), width / 7,
				lbl_Name.getHeight()); // ��ġ�� ������

		add(comb_Goal); // panel�� �߰�

		/* ���� */
		lbl_LKind = new JLabel("����");
		lbl_LKind.setFont(font_L);
		lbl_LKind.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_LKind.setBounds(lbl_Name.getX(), (lbl_Goal.getY() + lbl_Goal.getHeight()) + 20, 100 + width / 30,
				lbl_Name.getHeight());
		lbl_LKind.setForeground(Color.WHITE);

		add(lbl_LKind);

		/* ���� �Է� */
		txt_LKind = new JTextField(20); // �ؽ�Ʈ�ʵ� ������
		txt_LKind.setBounds((lbl_LKind.getX() + lbl_LKind.getWidth()) + 10, lbl_LKind.getY(), width / 3,
				lbl_Name.getHeight()); // ��ġ�� ������
		txt_LKind.setFont(font_PS); // �۾�ü

		add(txt_LKind); // �ǳڿ� textfield �߰�

		/* ���� */
		lbl_SKind = new JLabel("����");
		lbl_SKind.setFont(font_L);
		lbl_SKind.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_SKind.setBounds(lbl_LKind.getX(), (txt_LKind.getY() + txt_LKind.getHeight()) + 20, 100 + width / 30,
				lbl_Name.getHeight());
		lbl_SKind.setForeground(Color.WHITE);

		add(lbl_SKind);

		/* ���� �Է� */
		txt_SKind = new JTextField(20); // �ؽ�Ʈ�ʵ� ������
		txt_SKind.setBounds((lbl_SKind.getX() + lbl_SKind.getWidth()) + 10, lbl_SKind.getY(), width / 3,
				lbl_Name.getHeight()); // ��ġ�� ������
		txt_SKind.setFont(font_PS); // �۾�ü

		add(txt_SKind); // �ǳڿ� textfield �߰�

		/* �Ⱓ */
		lbl_Date = new JLabel("�Ⱓ");
		lbl_Date.setFont(font_L);
		lbl_Date.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Date.setBounds(lbl_Name.getX(), (lbl_SKind.getY() + lbl_SKind.getHeight()) + 20, 100 + width / 30,
				lbl_Name.getHeight());
		lbl_Date.setForeground(Color.WHITE);

		add(lbl_Date);

		// ���� ���� ���� 10���� Year�� ���
		for (int i = 0, y = thisyear - i; i <= 10; i++, y--) {
			year[i] = String.valueOf(y);
		}

		/* �� ���� */
		com_sYear = new JComboBox<String>(year); // �޺��ڽ� �ʱ�ȭ�ε� String �����̾�
		com_sYear.setFont(font_PS); // ��Ʈ
		com_sYear.setBounds((lbl_Date.getX() + lbl_Date.getWidth()) + 10, lbl_Date.getY(), width / 17,
				lbl_Date.getHeight()); // ��ġ��
		com_sYear.setSelectedItem(String.valueOf(thisyear)); // ������

		add(com_sYear);

		/* - */
		label = new JLabel("-");
		label.setFont(font_PS);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds((com_sYear.getX() + com_sYear.getWidth()) - 5, com_sYear.getY(), width / 40,
				lbl_Name.getHeight());
		label.setForeground(Color.WHITE);

		add(label);

		/* �� ���� */
		com_sMonth = new JComboBox<String>(); // �޺��ڽ� �ʱ�ȭ�ε� String �����̾�
		com_sMonth.setFont(font_PS); // ��Ʈ
		com_sMonth.setBounds((label.getX() + label.getWidth()) - 5, label.getY(), width / 25, lbl_Date.getHeight()); // ��ġ��
																														// ������
		for (int i = 1; i <= thismonth; i++) {
			com_sMonth.addItem(String.valueOf(i));
		}
		com_sMonth.setSelectedItem(String.valueOf(thismonth));
		com_sMonth.setSelectedIndex(thismonth - 1);

		com_sYear.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();
				// ���� �� �����ְ�
				com_sMonth.removeAllItems();

				// ���ظ�
				if (Integer.parseInt(com_sYear.getSelectedItem().toString()) == thisyear) {
					for (int i = 1; i <= thismonth; i++) {
						com_sMonth.addItem(String.valueOf(i));
					}
				} else { // ���ذ� �ƴϸ�
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

		/* �� ���� */
		com_sDate = new JComboBox<String>(); // �޺��ڽ� �ʱ�ȭ�ε� String �����̾�
		com_sDate.setFont(font_PS); // ��Ʈ
		com_sDate.setBounds((label.getX() + label.getWidth()) - 5, label.getY(), width / 25, lbl_Date.getHeight()); // ��ġ��
		// ������
		/* �� �� ����ؼ� �ֱ� */
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

				/* �� �� ����ؼ� �ֱ� */
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

		// ���� ���� ���� 10���� Year�� ���
		for (int i = 0, y = thisyear + i; i <= 10; i++, y++) {
			year[i] = String.valueOf(y);
		}

		/* �� ���� */
		com_eYear = new JComboBox<String>(year); // �޺��ڽ� �ʱ�ȭ�ε� String �����̾�
		com_eYear.setFont(font_PS); // ��Ʈ
		com_eYear.setBounds((label.getX() + label.getWidth()) + 10, label.getY(), width / 17, lbl_Date.getHeight()); // ��ġ��
		com_eYear.setSelectedItem(String.valueOf(thisyear)); // ������

		add(com_eYear);

		/* - */
		label = new JLabel("-");
		label.setFont(font_PS);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds((com_eYear.getX() + com_eYear.getWidth()) - 5, com_eYear.getY(), width / 40,
				lbl_Name.getHeight());
		label.setForeground(Color.WHITE);

		add(label);

		/* �� ���� */
		com_eMonth = new JComboBox<String>(); // �޺��ڽ� �ʱ�ȭ�ε� String �����̾�
		com_eMonth.setFont(font_PS); // ��Ʈ
		com_eMonth.setBounds((label.getX() + label.getWidth()) - 5, label.getY(), width / 25, lbl_Date.getHeight()); // ��ġ��
																														// ������
		for (int i = thismonth; i <= 12; i++) {
			com_eMonth.addItem(String.valueOf(i));
		}

		com_eMonth.setSelectedItem(String.valueOf(thismonth));
		com_eMonth.setSelectedIndex(0);

		com_eYear.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();
				// ���� �� �����ְ�
				com_eMonth.removeAllItems();

				// ���ظ�
				if (Integer.parseInt(com_eYear.getSelectedItem().toString()) == thisyear) {
					for (int i = thismonth; i <= 12; i++) {
						com_eMonth.addItem(String.valueOf(i));
					}
					com_eMonth.setSelectedItem(String.valueOf(thismonth));
				} else { // ���ذ� �ƴϸ�
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

		/* �� ���� */
		com_eDate = new JComboBox<String>(); // �޺��ڽ� �ʱ�ȭ�ε� String �����̾�
		com_eDate.setFont(font_PS); // ��Ʈ
		com_eDate.setBounds((label.getX() + label.getWidth()) - 5, label.getY(), width / 25, lbl_Date.getHeight()); // ��ġ��
		// ������
		/* �� �� ����ؼ� �ֱ� */
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

				/* �� �� ����ؼ� �ֱ� */
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

		/* ���� */
		lbl_Determin = new JLabel("����");
		lbl_Determin.setFont(font_L);
		lbl_Determin.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Determin.setBounds(lbl_Name.getX(), (lbl_Date.getY() + lbl_Date.getHeight()) + 20, 100 + width / 30,
				lbl_Name.getHeight());
		lbl_Determin.setForeground(Color.WHITE);

		add(lbl_Determin);

		/* ���� �Է� */
		txt_Determin = new JTextArea();
		txt_Determin.setBounds((lbl_Determin.getX() + lbl_Determin.getWidth()) + 10, lbl_Determin.getY(), width / 3,
				height / 8);
		txt_Determin.setFont(font_PS);
		txt_Determin.setLineWrap(true);

		add(txt_Determin);

		/* ���� ��ư */
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
					// �����͸� DB�� �Է�
					if (InsertInfo() == true) {
						// ����ȭ������ �̵�
						MainScreen ms = new MainScreen();
						ms.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "������ �Է¿� �����Ͽ����ϴ�!", "���� ����", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		add(btn_Start);
	}

	// �Էµ� ���� ������ �ִ��� ������ Ȯ��
	public static boolean ChkMember() {
		boolean result = false;

		// ���Ӱ� �ʱ�ȭ
		mi_DAO = new M_Info_DAO();
		mi_DTO = new M_Info_DTO();

		// dao���� ��� ���� ��������
		mi_DTO = mi_DAO.CheckInfo();

		// �����Ͱ� ������ true, ������ false
		if (mi_DTO.getM_Name() == null || mi_DTO.getM_Name().equals("")) {
			result = false;
		} else {
			result = true;
		}

		return result;
	}

	// ��ĭ�� �ִ��� Ȯ��
	public boolean ChkNon() {
		boolean result = false;

		if (txt_Name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "�̸��� �Է��ϼ���", "���� ����", JOptionPane.WARNING_MESSAGE);
			result = false;
		} else if (txt_LKind.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "������ �Է��ϼ���", "���� ����", JOptionPane.WARNING_MESSAGE);
			result = false;
		} else if (txt_Determin.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "������ �Է��ϼ���", "���� ����", JOptionPane.WARNING_MESSAGE);
			result = false;
		} else {
			result = true;
		}

		return result;
	}

	// �����͸� DB�� ����
	public boolean InsertInfo() {
		boolean result = false;
		
		// ���Ӱ� �ʱ�ȭ
		mi_DAO = new M_Info_DAO();
		mi_DTO = new M_Info_DTO();
		
		// �� ������ DTO �Է�
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
