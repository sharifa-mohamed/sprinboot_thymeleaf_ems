package com.sm.ems.report;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sm.ems.util.MethodOrder;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PdfExporter {

    private Document document;
    private PdfPTable table;
    private String title;
    private List recordsList;
    private Class reportClass;


    private List<String> fieldsList;
    private Method[] getterMethodsOrdered;


    public PdfExporter(Class reportClass, List recordsList, String title) {
        this.reportClass = reportClass;
        this.recordsList = recordsList;
        this.title = title;
    }


    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.yellow);
        cell.setPadding(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.black);
        font.setSize(12);

        for (String col : fieldsList) {
            cell.setPhrase(new Phrase(col.toUpperCase(), font));
            table.addCell(cell);
        }

    }


    private void writeTableData(PdfPTable table) throws InvocationTargetException, IllegalAccessException {

        Object res;
        Class<?> returnType;
        PdfPCell cell;
        for (Object obj : recordsList) {
            for (Method method : getterMethodsOrdered) {
                
                res = method.invoke(obj);
                cell = new PdfPCell();
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPhrase(new Phrase(res == null ? "" : res.toString()));
                table.addCell(cell);
            }
        }
        document.add(table);
        document.close();


    }

    private void prepareFieldsAndGettersList() {
        List<Method> allDeclaredMethods = Arrays.stream(reportClass.getDeclaredMethods()).toList();

        ReflectionUtils.MethodFilter mf = method -> !method.getName().toLowerCase().contains("password") && method.getName().startsWith("get") && method.getAnnotations().length > 0;
        ArrayList<Method> gettersListUnOrdered = new ArrayList<>();
        fieldsList = new ArrayList<>();

        for (Method m : allDeclaredMethods) {
            if (mf.matches(m)) {
                gettersListUnOrdered.add(m);
            }
        }
        getterMethodsOrdered = MethodOrder.getMethodsOrdered(gettersListUnOrdered.toArray(new Method[gettersListUnOrdered.size()]));
        fieldsList = Arrays.stream(getterMethodsOrdered).map(m -> m.getName().replace("get", "")).toList();
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException, InvocationTargetException, IllegalAccessException {
        createDocument(response);
        addTitle();

        prepareFieldsAndGettersList();
        createTable();
        writeTableHeader(table);
        writeTableData(table);


    }

    private void createDocument(HttpServletResponse response) throws IOException {
        document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
    }

    private void addTitle() {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(16);
        font.setColor(Color.black);

        Paragraph p = new Paragraph(title, font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
    }

    private void createTable() {
        table = new PdfPTable(fieldsList.size());
        table.setWidthPercentage(100f);
        // table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
    }
}