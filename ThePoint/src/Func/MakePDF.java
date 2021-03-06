package Func;

import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import DTO.P_KeyPoint_DTO;

public class MakePDF {

	private static PDPageContentStream contentStream = null;
	private static PDPage page = null;
	private static PDDocument document = null;
	private static InputStream fontStream = null;
	private static PDType0Font fontNanum = null;

	private static Vector<String> contents_v = null;

	private static int x = 25, y = 760;

	// 글씨체
	private Font font_L = new Font("맑은 고딕", Font.BOLD, 40 + y / 250);
	private Font font_BM = new Font("맑은 고딕", Font.BOLD, 30 + y / 250);
	private Font font_PS = new Font("맑은 고딕", Font.PLAIN, 20 + y / 250);
	private Font font_PSS = new Font("맑은 고딕", Font.PLAIN, 10 + y / 250);

	public MakePDF(List<P_KeyPoint_DTO> val) throws Exception {
		try {
			// Create a document and add a page to it
			document = new PDDocument();
			page = new PDPage();
			document.addPage(page);

			// Create a new font object selecting one of the PDF base fonts
			fontStream = new FileInputStream("./fonts/malgun.ttf");
			fontNanum = PDType0Font.load(document, fontStream);

			// Start a new content steam which will "hold" the to be created content
			contentStream = new PDPageContentStream(document, page);

			// Define a text content stream using the selected font, moving the cursor and
			// drawing the text "Hello World"
			contentStream.beginText();
			contentStream.setFont(fontNanum, 12);

			contents_v = new Vector<String>();
			for (int i = 0; i < val.size(); i++) {
				String contents = new String();

				// 목차
				int[] kind = new int[5];
				kind[0] = val.get(i).getP_Kind1();
				kind[1] = val.get(i).getP_Kind2();
				kind[2] = val.get(i).getP_Kind3();
				kind[3] = val.get(i).getP_Kind4();
				kind[4] = val.get(i).getP_Kind5();

				// 앞 여백
				contents = makeSpace(kind);
				contents = contents.substring(0, contents.length() - 2);

				// 목차 그리기
				contents += val.get(i).getP_Kind_Info();
				contents_v.add(contents);

				// 앞 여백
				contents = makeSpace(kind);

				// 내용
				if (val.get(i).getP_Point_Info() != null) {
					String[] point = val.get(i).getP_Point_Info().split("\n");
					for (int j = 0; j < point.length; j++) {
						if (point[j].length() > 54) {
							int num = point[j].length() / 54;
							for (int k = 0; k < num; k++) {
								// 앞 여백
								contents = makeSpace(kind);
								contents += point[k].substring(55 * j, (55 * j) + 54);
								contents_v.add(contents);
							}

							// 앞 여백
							contents = makeSpace(kind);
							contents += point[j].substring(55 * num, point[j].length());
							contents_v.add(contents);
						} else {
							// 앞 여백
							contents = makeSpace(kind);
							contents += point[j];
							contents_v.add(contents);
						}
					}
					contents_v.add(" ");
				} else {
					contents_v.add(" ");
				}
			}

			contentStream.newLineAtOffset(x, y);
			contentStream.setLeading(15f);
			for (int i = 0; i < contents_v.size(); i++) {
				if ((i != 0) && (i % 50) == 0) {
					contentStream.endText();
					contentStream.close();

					page = new PDPage();
					document.addPage(page);
					contentStream = new PDPageContentStream(document, page);
					contentStream.beginText();
					contentStream.setFont(fontNanum, 12);

					contentStream.newLineAtOffset(x, y);
					contentStream.setLeading(15f);
				} else {
					contentStream.drawString(contents_v.get(i));
					contentStream.newLine();
				}
			}

			contentStream.endText();

			// Make sure that the content stream is closed.
			contentStream.close();

			// Save the results and ensure that the document is properly closed.
			document.save("C:\\Users\\tytyj\\Documents\\ThePoint.pdf");
			document.close();
			

			JOptionPane.showMessageDialog(null, "사용자 파일 밑 Documents에 출력되었습니다", "완료", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			/** 메세지박스 UI **/
			UIManager UI = new UIManager();
			UI.put("OptionPane.background", new java.awt.Color(119, 150, 242));
			UI.put("Panel.background", new java.awt.Color(119, 150, 242));
			UI.put("OptionPane.messageFont", font_BM);
			UI.put("OptionPane.messageForeground", Color.WHITE);
			UI.put("OptionPane.buttonFont", font_L);

			JOptionPane.showMessageDialog(null, "포함할 수 없는 특수문자가 있습니다!!", "오류", JOptionPane.WARNING_MESSAGE);
		}
	}

	public String makeSpace(int[] kind) {
		String result = "";

		if (kind[0] != 0 && kind[1] == 0 && kind[2] == 0 && kind[3] == 0 && kind[4] == 0) { // 1분류
			result = "  ";
		} else if (kind[0] != 0 && kind[1] != 0 && kind[2] == 0 && kind[3] == 0 && kind[4] == 0) { // 2분류
			result = "    ";
		} else if (kind[0] != 0 && kind[1] != 0 && kind[2] != 0 && kind[3] == 0 && kind[4] == 0) { // 3분류
			result = "      ";
		} else if (kind[0] != 0 && kind[1] != 0 && kind[2] != 0 && kind[3] != 0 && kind[4] == 0) { // 4분류
			result = "        ";
		} else if (kind[0] != 0 && kind[1] != 0 && kind[2] != 0 && kind[3] != 0 && kind[4] != 0) { // 5분류
			result = "          ";
		}

		return result;
	}

}
