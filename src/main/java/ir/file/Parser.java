package ir.file;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class Parser {

    public static String readDocxFile(String fileName) {

        StringBuilder paragraph = new StringBuilder("");

        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

            XWPFDocument document = new XWPFDocument(fis);

            List<XWPFParagraph> paragraphs = document.getParagraphs();

            for (XWPFParagraph para : paragraphs) {
                paragraph.append(para.getText());
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return paragraph.toString();
    }

    public static String[] tokenize(String input, String delimiter) {
        String[] output;
        output = input.split(delimiter);

        return output;
    }


    public static void test() {
        System.out.println(Arrays.toString(tokenize(readDocxFile("src/main/java/ir/resources/Doc1.docx"), " ")));
        System.out.println(Arrays.toString(tokenize(readDocxFile("src/main/java/ir/resources/Doc2.docx"), " ")));

    }
}
