package demo.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Abz {

    @GetMapping("/download-pdf")
    public ResponseEntity<byte[]> downloadPDF() throws IOException {
        byte[] pdfContent = generatePDFContent();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "sample.pdf");

        return ResponseEntity.ok().headers(headers).body(pdfContent);
    }

    private byte[] generatePDFContent() throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

        float margin = 50;
        float yStart = page.getMediaBox().getHeight() - margin;
        float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
        float yPosition = yStart;
        int cols = 3;
        float rowHeight = 20;

        // Draw table headers
        drawTableCell(contentStream, margin, yPosition, tableWidth / cols, "SlNo");
        drawTableCell(contentStream, margin + tableWidth / cols, yPosition, tableWidth / cols, "Team");
        drawTableCell(contentStream, margin + 2 * tableWidth / cols, yPosition, tableWidth / cols, "Ranking");

        yPosition -= rowHeight;

        // Draw table content
        drawTableCell(contentStream, margin, yPosition, tableWidth / cols, "1");
        drawTableCell(contentStream, margin + tableWidth / cols, yPosition, tableWidth / cols, "Argentina");
        drawTableCell(contentStream, margin + 2 * tableWidth / cols, yPosition, tableWidth / cols, "1");
        yPosition -= rowHeight;

        drawTableCell(contentStream, margin, yPosition, tableWidth / cols, "2");
        drawTableCell(contentStream, margin + tableWidth / cols, yPosition, tableWidth / cols, "Brazil");
        drawTableCell(contentStream, margin + 2 * tableWidth / cols, yPosition, tableWidth / cols, "2");
        yPosition -= rowHeight;

        drawTableCell(contentStream, margin, yPosition, tableWidth / cols, "3");
        drawTableCell(contentStream, margin + tableWidth / cols, yPosition, tableWidth / cols, "France");
        drawTableCell(contentStream, margin + 2 * tableWidth / cols, yPosition, tableWidth / cols, "3");
        yPosition -= rowHeight;

        drawTableCell(contentStream, margin, yPosition, tableWidth / cols, "4");
        drawTableCell(contentStream, margin + tableWidth / cols, yPosition, tableWidth / cols, "Belgium");
        drawTableCell(contentStream, margin + 2 * tableWidth / cols, yPosition, tableWidth / cols, "4");
        yPosition -= rowHeight;

        drawTableCell(contentStream, margin, yPosition, tableWidth / cols, "5");
        drawTableCell(contentStream, margin + tableWidth / cols, yPosition, tableWidth / cols, "India");
        drawTableCell(contentStream, margin + 2 * tableWidth / cols, yPosition, tableWidth / cols, "5");

        contentStream.close();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }

    private void drawTableCell(PDPageContentStream contentStream, float x, float y, float width, String content)
            throws IOException {
        contentStream.setNonStrokingColor(0.8f, 0.8f, 0.8f); // Cell background color

        contentStream.setNonStrokingColor(0f, 0f, 0f); // Cell text color
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset(x + 2, y + 15); // Padding from cell borders
        contentStream.showText(content);
        contentStream.endText();
    }
}
