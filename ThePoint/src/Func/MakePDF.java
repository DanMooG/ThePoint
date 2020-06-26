package Func;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import DTO.P_KeyPoint_DTO;

public class MakePDF {
	
	public static PDPageContentStream contentStream = null;

	public MakePDF(List<P_KeyPoint_DTO> val) throws Exception {
		// Create a document and add a page to it
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);

		// Create a new font object selecting one of the PDF base fonts
		InputStream fontStream = new FileInputStream("fonts/NanumGothic.ttf");
		PDType0Font fontNanum = PDType0Font.load(document, fontStream);

		// Start a new content steam which will "hold" the to be created content
		contentStream = new PDPageContentStream(document, page);

		// Define a text content stream using the selected font, moving the cursor and
		// drawing the text "Hello World"
		contentStream.beginText();
		contentStream.setFont(fontNanum, 12);

		int x = 25, y = 760;
		contentStream.newLineAtOffset(x, y);
		for (int i = 0; i < val.size(); i++) {
			String contents = new String();
			contentStream.setLeading(15f);

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
			contentStream.drawString(contents);
			contentStream.newLine();

			//앞 여백
			contents = makeSpace(kind);
			
			// 내용
			if (val.get(i).getP_Point_Info() != null) {
				String[] point = val.get(i).getP_Point_Info().split("\n");
				for (int j = 0; j < point.length; j++) {
					if (point[j].length() > 54) {
						int num = point[j].length() / 54;
						for(int k = 0; k < num; k++) {
							//앞 여백
							contents = makeSpace(kind);
							contents += point[k].substring(55*j, (55*j)+54);
							contentStream.drawString(contents);
							contentStream.setLeading(15f);
							contentStream.newLine();
						}
						
						//앞 여백
						contents = makeSpace(kind);
						contents += point[j].substring(55*num, point[j].length());
						contentStream.drawString(contents);
						contentStream.newLine();
						contentStream.setLeading(10f);
						contentStream.newLine();
					} else {
						//앞 여백
						contents = makeSpace(kind);
						contents += point[j];
						contentStream.drawString(contents);
						contentStream.newLine();
						contentStream.setLeading(10f);
						contentStream.newLine();
					}
				}
			} else {
				contentStream.setLeading(10f);
				contentStream.newLine();
			}
		}

		contentStream.endText();

		// Make sure that the content stream is closed.
		contentStream.close();

		// Save the results and ensure that the document is properly closed.
		document.save("C:\\Users\\tytyj\\Documents\\ThePoint.pdf");
		document.close();
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
