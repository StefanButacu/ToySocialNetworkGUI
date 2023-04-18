package com.toysocialnetworkgui.service.export;

import com.toysocialnetworkgui.utils.UserFriendDTO;
import com.toysocialnetworkgui.utils.UserMessageDTO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class PdfExportStrategy implements ExportStrategy {
    @Override
    public void export(List<UserFriendDTO> friends, List<UserMessageDTO> messages, String filePath) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();

        contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
        contentStream.newLineAtOffset(25, 20);
        contentStream.showText("Report made on: " + LocalDate.now());

        contentStream.newLineAtOffset(0, 700);
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 18);

        contentStream.newLineAtOffset(0, -50);
        contentStream.showText("NEW FRIENDS");
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        contentStream.newLineAtOffset(0, -30);
        for (UserFriendDTO friend : friends) {
            contentStream.showText(friend.toString());
            contentStream.newLineAtOffset(0, -15);
        }

        contentStream.newLineAtOffset(0, -30);
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 20);
        contentStream.showText("MESSAGES RECEIVED");
        contentStream.newLineAtOffset(0, -30);
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        for (UserMessageDTO message : messages) {
            contentStream.showText(message.toString());
            contentStream.newLineAtOffset(0, -15);
        }

        contentStream.endText();
        contentStream.close();

        document.save(filePath);
        document.close();


    }
}
