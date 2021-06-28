package com.datapp.chambre.utils.export;

import lombok.Getter;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by kmluns on 30.07.2020
 */
public class WordParser {

    @Getter
    private ExportType exportType;

    @Getter
    private XWPFDocument document;

    public WordParser(String templateFilePath) {
        this.exportType = ExportType.WORD;

        InputStream templateFile = null;
        try {
            templateFile = new ClassPathResource(templateFilePath).getInputStream();
            this.document = new XWPFDocument(templateFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editTable(int tableIndex, Method editMethod) throws InvocationTargetException, IllegalAccessException {
        int tableCount = this.document.getTables().size();

        if (tableCount > tableIndex) {
            XWPFTable table = this.document.getTableArray(tableIndex);
            editMethod.invoke(this, table);
        }
    }


    public void replaceAll(String searchText, String replaceText) {

        for (XWPFParagraph p : this.document.getParagraphs()) {
            replaceAllOnRuns(p.getRuns(), searchText, replaceText);
        }
        for (XWPFTable tbl : this.document.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        replaceAllOnRuns(p.getRuns(), searchText, replaceText);
                    }
                }
            }
        }

    }

    private static void replaceAllOnRuns(List<XWPFRun> runList, String searchText, String replaceText) {
        if (runList == null) {
            return;
        }
        for (XWPFRun r : runList) {
            String text = r.getText(0);
            if (text != null && text.contains(searchText)) {
                text = text.replace(searchText, replaceText);
                r.setText(text, 0);
            }
        }
    }

    public void saveDocxFile(String filePath, String fileName) throws IOException {
        FileOutputStream out = new FileOutputStream(filePath + fileName + ".docx");
        this.document.write(out);
        out.close();
    }

    public ByteArrayOutputStream getByteArray() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        this.document.write(out);
        out.close();
        return out;
    }

    public void closeDocument() throws IOException {
        this.document.close();
    }


}
