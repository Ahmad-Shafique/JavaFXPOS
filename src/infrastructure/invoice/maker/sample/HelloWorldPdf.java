package infrastructure.invoice.maker.sample;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import core.domain.model.entities.SaleItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileOutputStream;

public class HelloWorldPdf {

    private final ObservableList<SaleItem> items;

    public HelloWorldPdf(){
        this.items = FXCollections.observableArrayList(
                new SaleItem(1, 1, 1, 1, 250),
                new SaleItem(2, 1, 2, 2, 480)
        );
    }

    public static final String RESULT
            = "invoices/";

    public void create() throws Exception{
        String filename = "HelloWorldPdf.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();

        Paragraph paragraph = new Paragraph("Products list");
        document.add(paragraph);
        addEmptyLine(paragraph, 5);
        addEmptyLine(paragraph, 5);
        addEmptyLine(paragraph, 5);
        addEmptyLine(paragraph, 5);
        addEmptyLine(paragraph, 5);


        PdfPTable table = createTable();
        document.add(table);

        // document.add(new Paragraph("Hello World!"));
        // step 5
        document.close();

    }

    private PdfPTable createTable() {

        PdfPTable table = new PdfPTable(4);
        PdfPCell c1 = new PdfPCell(new Phrase("Item"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Price"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantity"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Total"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        for (SaleItem i : items) {
            table.addCell(i.getName());
            table.addCell(String.valueOf(i.getPrice()));
            table.addCell(String.valueOf(i.getQuantity()));
            table.addCell(String.valueOf(i.getTotal()));
        }

        return table;
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
