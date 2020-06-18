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
	// ������Ʈ
	private JPanel panel; // Panel ����ؼ� ��ü�� ����
	private JButton btn_background, btn_Home, btn_Plus, btn_Minus, btn_Input;
	private JLabel label, lbl_1, lbl_2, lbl_3, lbl_4, lbl_5;
	private JTextArea tArea_Info;
	private JTable table_Structure;
	private JScrollPane scroll;
	private JTextField txt_1, txt_2, txt_3, txt_4, txt_5;
	private Vector<String> col = new Vector<String>(); // ���̺� �÷�
	private Vector row = new Vector(); // ���̺� ����
	private DefaultTableModel model;

	// ũ�� ����
	private int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private int width = x * 3 / 4, height = y * 4 / 5;
	private int btn_W = (((width - 40) / 3) - 10) / 6 - 2, btn_H = height / 6 + 15;
	private int num_W = (((width - 40) / 3) - 10) / 8;

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

	/* Minus ��ư */
	private ImageIcon btnMinus1 = new ImageIcon("images/button/btnMinus.png");
	private Image imgbtnMinus = btnMinus1.getImage();
	private Image imgbtnMinus2 = imgbtnMinus.getScaledInstance(btn_W * 2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnMinus = new ImageIcon(imgbtnMinus2);

	private ImageIcon btnMinus_push1 = new ImageIcon("images/pushedbutton/btnMinus_push.png");
	private Image imgbtnMinus_push = btnMinus_push1.getImage();
	private Image imgbtnMinus_push2 = imgbtnMinus_push.getScaledInstance(btn_W * 2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnMinus_push = new ImageIcon(imgbtnMinus_push2);

	/* Plus ��ư */
	private ImageIcon btnPlus1 = new ImageIcon("images/button/btnPlus.png");
	private Image imgbtnPlus = btnPlus1.getImage();
	private Image imgbtnPlus2 = imgbtnPlus.getScaledInstance(btn_W * 2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnPlus = new ImageIcon(imgbtnPlus2);

	private ImageIcon btnPlus_push1 = new ImageIcon("images/pushedbutton/btnPlus_push.png");
	private Image imgbtnPlus_push = btnPlus_push1.getImage();
	private Image imgbtnPlus_push2 = imgbtnPlus_push.getScaledInstance(btn_W * 2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnPlus_push = new ImageIcon(imgbtnPlus_push2);

	/* �Է� ��ư */
	private ImageIcon btnInput1 = new ImageIcon("images/button/btnInput.png");
	private Image imgbtnInput = btnInput1.getImage();
	private Image imgbtnInput2 = imgbtnInput.getScaledInstance(btn_W * 2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnInput = new ImageIcon(imgbtnInput2);

	private ImageIcon btnInput_push1 = new ImageIcon("images/pushedbutton/btnInput_push.png");
	private Image imgbtnInput_push = btnInput_push1.getImage();
	private Image imgbtnInput_push2 = imgbtnInput_push.getScaledInstance(btn_W * 2, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon btnInput_push = new ImageIcon(imgbtnInput_push2);

	/* DB ���� */
	private static M_Info_DAO mi_DAO = null;
	private static M_Info_DTO mi_DTO = null;
	private static P_KeyPoint_DAO pk_DAO = null;
	private static P_KeyPoint_DTO pk_DTO = null;
	private static P_KeyPoint_DTO sel_DTO = null;

	//////////////////////////////////////////////////////////////////////////////////////

	public Structure() {
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

		/** �޼����ڽ� UI **/
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", new java.awt.Color(119, 150, 242));
		UI.put("Panel.background", new java.awt.Color(119, 150, 242));
		UI.put("OptionPane.messageFont", font_BM);
		UI.put("OptionPane.messageForeground", Color.WHITE);
		UI.put("OptionPane.buttonFont", font_L);

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

		/* ���õ� ���� */
		tArea_Info = new JTextArea();
		tArea_Info.setFont(font_BM);
		tArea_Info.setForeground(Color.WHITE);
		tArea_Info.setOpaque(true);
		tArea_Info.setBackground(new java.awt.Color(119, 150, 242)); // �ڿ� ���
		tArea_Info.setLineWrap(true);

		panel.add(tArea_Info);

		/* 1 */
		lbl_1 = new JLabel(btn1);
		lbl_1.setVerticalAlignment(SwingConstants.CENTER);
		lbl_1.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_1);

		/* 1 �Է� */
		txt_1 = new JTextField(20); // �ؽ�Ʈ�ʵ� ������
		txt_1.setFont(font_PS); // �۾�ü

		add(txt_1);

		/* 2 */
		lbl_2 = new JLabel(btn2);
		lbl_2.setVerticalAlignment(SwingConstants.CENTER);
		lbl_2.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_2);

		/* 2 �Է� */
		txt_2 = new JTextField(20); // �ؽ�Ʈ�ʵ� ������
		txt_2.setFont(font_PS); // �۾�ü

		add(txt_2);

		/* 3 */
		lbl_3 = new JLabel(btn3);
		lbl_3.setVerticalAlignment(SwingConstants.CENTER);
		lbl_3.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_3);

		/* 3 �Է� */
		txt_3 = new JTextField(20); // �ؽ�Ʈ�ʵ� ������
		txt_3.setFont(font_PS); // �۾�ü

		add(txt_3);

		/* 4 */
		lbl_4 = new JLabel(btn4);
		lbl_4.setVerticalAlignment(SwingConstants.CENTER);
		lbl_4.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_4);

		/* 4 �Է� */
		txt_4 = new JTextField(20); // �ؽ�Ʈ�ʵ� ������
		txt_4.setFont(font_PS); // �۾�ü

		add(txt_4);

		/* 5 */
		lbl_5 = new JLabel(btn5);
		lbl_5.setVerticalAlignment(SwingConstants.CENTER);
		lbl_5.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lbl_5);

		/* 5 �Է� */
		txt_5 = new JTextField(20); // �ؽ�Ʈ�ʵ� ������
		txt_5.setFont(font_PS); // �۾�ü

		add(txt_5);

		/* �Է� ��ư */
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
					int chk_first = JOptionPane.showConfirmDialog(null, "ó�� ���� �Է� ��, ��ü�� �ʱ�ȭ �˴ϴ�.\nù ���� �Է��Դϱ�?",
							"�Է¿��� Ȯ��", JOptionPane.YES_NO_OPTION);
					if (chk_first == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(null, "��ȣ", "����", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "�߰��� ��ġ�� ������ �������ּ���", "����", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					pk_DAO = new P_KeyPoint_DAO();
					// �Է� �ϰ� ��� Ȯ��
					boolean result1 = false;
					if (!txt_1.getText().toString().equals("")) {
						result1 = pk_DAO.addNew(sel_DTO.getP_Kind1() + 1, 0, 0, 0, 0, txt_1.getText().toString());
						if (result1 == true) {
							JOptionPane.showMessageDialog(null, "�Է� �Ǿ����ϴ�", "�Է� �Ϸ�", JOptionPane.WARNING_MESSAGE);
							Reset();
						} else {
							JOptionPane.showMessageDialog(null, "������ �߻��߽��ϴ�", "����", JOptionPane.WARNING_MESSAGE);
						}
					} else if (!txt_2.getText().toString().equals("")) {
						result1 = pk_DAO.addNew(sel_DTO.getP_Kind1(), sel_DTO.getP_Kind2() + 1, 0, 0, 0,
								txt_2.getText().toString());
						if (result1 == true) {
							JOptionPane.showMessageDialog(null, "�Է� �Ǿ����ϴ�", "�Է� �Ϸ�", JOptionPane.WARNING_MESSAGE);
							Reset();
						} else {
							JOptionPane.showMessageDialog(null, "������ �߻��߽��ϴ�", "����", JOptionPane.WARNING_MESSAGE);
						}
					} else if (!txt_3.getText().toString().equals("")) {
						result1 = pk_DAO.addNew(sel_DTO.getP_Kind1(), sel_DTO.getP_Kind2(), sel_DTO.getP_Kind3() + 1, 0,
								0, txt_3.getText().toString());
						if (result1 == true) {
							JOptionPane.showMessageDialog(null, "�Է� �Ǿ����ϴ�", "�Է� �Ϸ�", JOptionPane.WARNING_MESSAGE);
							Reset();
						} else {
							JOptionPane.showMessageDialog(null, "������ �߻��߽��ϴ�", "����", JOptionPane.WARNING_MESSAGE);
						}
					} else if (!txt_4.getText().toString().equals("")) {
						result1 = pk_DAO.addNew(sel_DTO.getP_Kind1(), sel_DTO.getP_Kind2(), sel_DTO.getP_Kind3(),
								sel_DTO.getP_Kind4() + 1, 0, txt_4.getText().toString());
						if (result1 == true) {
							JOptionPane.showMessageDialog(null, "�Է� �Ǿ����ϴ�", "�Է� �Ϸ�", JOptionPane.WARNING_MESSAGE);
							Reset();
						} else {
							JOptionPane.showMessageDialog(null, "������ �߻��߽��ϴ�", "����", JOptionPane.WARNING_MESSAGE);
						}
					} else if (!txt_5.getText().toString().equals("")) {
						result1 = pk_DAO.addNew(sel_DTO.getP_Kind1(), sel_DTO.getP_Kind2(), sel_DTO.getP_Kind3(),
								sel_DTO.getP_Kind4(), sel_DTO.getP_Kind4() + 1, txt_5.getText().toString());
						if (result1 == true) {
							JOptionPane.showMessageDialog(null, "�Է� �Ǿ����ϴ�", "�Է� �Ϸ�", JOptionPane.WARNING_MESSAGE);
							Reset();
						} else {
							JOptionPane.showMessageDialog(null, "������ �߻��߽��ϴ�", "����", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "������ �Է����ּ���", "����", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		panel.add(btn_Plus);

		/* ���� ��ư */
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
					JOptionPane.showMessageDialog(null, "������ ������ �������ּ���", "����", JOptionPane.WARNING_MESSAGE);
				} else {
					int result1 = JOptionPane.showConfirmDialog(null, "'" + tArea_Info.getText() + "'��(��) �����Ͻðڽ��ϱ�?",
							"�������� Ȯ��", JOptionPane.YES_NO_OPTION);
					if (result1 == JOptionPane.YES_OPTION) { // Yes
						// ���� �ϰ� ��� Ȯ��
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
							JOptionPane.showMessageDialog(null, "������ �Է����ּ���", "����", JOptionPane.WARNING_MESSAGE);
						} else {
							boolean result2 = false;
							pk_DAO = new P_KeyPoint_DAO();
							result2 = pk_DAO.ResetRow(sel_DTO.getP_Kind1(), sel_DTO.getP_Kind2(), sel_DTO.getP_Kind3(),
								sel_DTO.getP_Kind4(), sel_DTO.getP_Kind5(), info);
							if (result2 == true) {
								JOptionPane.showMessageDialog(null, "���� �Ǿ����ϴ�", "���� �Ϸ�", JOptionPane.WARNING_MESSAGE);
								Reset();
							} else {
								JOptionPane.showMessageDialog(null, "������ �߻��߽��ϴ�", "����", JOptionPane.WARNING_MESSAGE);
							}
						}
						
					} else { // No �Ǵ� â�� �׳� ���� ���
						JOptionPane.showMessageDialog(null, "��ҵǾ����ϴ�", "���", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		panel.add(btn_Input);

		/* Minus ��ư */
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
					JOptionPane.showMessageDialog(null, "������ ������ �������ּ���", "����", JOptionPane.WARNING_MESSAGE);
				} else {
					int result1 = JOptionPane.showConfirmDialog(null, "'" + tArea_Info.getText() + "'��(��) �����Ͻðڽ��ϱ�?",
							"�������� Ȯ��", JOptionPane.YES_NO_OPTION);
					if (result1 == JOptionPane.YES_OPTION) { // Yes
						pk_DAO = new P_KeyPoint_DAO();

						// ���� ��Ű�� ��� Ȯ��
						boolean result2 = false;
						result2 = pk_DAO.RemoveRow(sel_DTO.getP_Kind1(), sel_DTO.getP_Kind2(), sel_DTO.getP_Kind3(),
								sel_DTO.getP_Kind4(), sel_DTO.getP_Kind5());

						if (result2 == true) {
							JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�", "����", JOptionPane.WARNING_MESSAGE);
							Reset();
						} else {
							JOptionPane.showMessageDialog(null, "������ �߻��߽��ϴ�", "����", JOptionPane.WARNING_MESSAGE);
						}
					} else { // No �Ǵ� â�� �׳� ���� ���
						JOptionPane.showMessageDialog(null, "��ҵǾ����ϴ�", "���", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		panel.add(btn_Minus);

		/* Table - ���� ����ִ� ���̺� */
		pk_DAO = new P_KeyPoint_DAO();
		pk_DTO = new P_KeyPoint_DTO();
		row = pk_DAO.getTable();
		setCell();
		model = new DefaultTableModel(row, col) {
			// �� ���� ���ϰ� �ϴ� �κ�
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_Structure = new JTable(model);
		table_Structure.setFont(font_PS); // �۾�ü
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

		panel.add(scroll); // ��ũ�� �߰�

		// �� ������Ʈ ��ġ
		tArea_Info.setBounds((scroll.getX() + scroll.getWidth()) + 10, scroll.getY(), (width - 40) / 3, height / 4);
		lbl_1.setBounds(tArea_Info.getX(), (tArea_Info.getY() + tArea_Info.getHeight()) + 15, num_W, num_W);
		txt_1.setBounds((lbl_1.getX() + lbl_1.getWidth()) + 10, lbl_1.getY(), tArea_Info.getWidth() - (num_W + 10),
				lbl_1.getHeight()); // ��ġ�� ������
		lbl_2.setBounds(lbl_1.getX(), (lbl_1.getY() + lbl_1.getHeight()) + 15, num_W, num_W);
		txt_2.setBounds((lbl_2.getX() + lbl_2.getWidth()) + 10, lbl_2.getY(), tArea_Info.getWidth() - (num_W + 10),
				lbl_2.getHeight()); // ��ġ�� ������
		lbl_3.setBounds(lbl_2.getX(), (lbl_2.getY() + lbl_2.getHeight()) + 15, num_W, num_W);
		txt_3.setBounds((lbl_3.getX() + lbl_3.getWidth()) + 10, lbl_3.getY(), tArea_Info.getWidth() - (num_W + 10),
				lbl_3.getHeight()); // ��ġ�� ������
		lbl_4.setBounds(lbl_3.getX(), (lbl_3.getY() + lbl_3.getHeight()) + 15, num_W, num_W);
		txt_4.setBounds((lbl_4.getX() + lbl_4.getWidth()) + 10, lbl_4.getY(), tArea_Info.getWidth() - (num_W + 10),
				lbl_4.getHeight()); // ��ġ�� ������
		lbl_5.setBounds(lbl_4.getX(), (lbl_4.getY() + lbl_4.getHeight()) + 15, num_W, num_W);
		txt_5.setBounds((lbl_5.getX() + lbl_5.getWidth()) + 10, lbl_5.getY(), tArea_Info.getWidth() - (num_W + 10),
				lbl_5.getHeight()); // ��ġ�� ������
		btn_Plus.setBounds(tArea_Info.getX(), (lbl_5.getY() + lbl_5.getHeight()) + 10, btn_W * 2, btn_H);
		btn_Input.setBounds((btn_Plus.getX() + btn_Plus.getWidth()) + 10, btn_Plus.getY(), btn_W * 2, btn_H);
		btn_Minus.setBounds((btn_Input.getX() + btn_Input.getWidth()) + 10, btn_Input.getY(), btn_W * 2, btn_H);
	}

	// ���̺� ��
	public void setCell() {
		col.addElement("1");
		col.addElement("2");
		col.addElement("3");
		col.addElement("4");
		col.addElement("5");
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

	// ����
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
			// �� ���� ���ϰ� �ϴ� �κ�
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_Structure.setModel(model);
	}
}