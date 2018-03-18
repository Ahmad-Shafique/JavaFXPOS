package infrastructure.invoice.maker;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import core.domain.model.entities.SaleItem;
import core.domain.model.entities._utilities.console;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CreateInvoicePdf {

    private final ObservableList<SaleItem> items;
    private final String barcode;

    public CreateInvoicePdf(ObservableList<SaleItem> items) {
        this.items = FXCollections.observableArrayList(items);
//        this.barcode = barcode;
        this.barcode = "154212";
    }

    public CreateInvoicePdf(){
        this.items = FXCollections.observableArrayList(
                new SaleItem(1, 1, 1, 1, 250),
                new SaleItem(2, 1, 2, 2, 480)
        );
        this.barcode = "154212";
    }

    public void generateReport() {

        try {
            Document document = new Document();
            FileOutputStream fs = new FileOutputStream("Invoice.pdf");
            PdfWriter writer = PdfWriter.getInstance(document, fs);
            document.open();

            Paragraph paragraph = new Paragraph("Product ID");
            document.add(paragraph);
            addEmptyLine(paragraph, 5);

            PdfContentByte cb = writer.getDirectContent();
            BarcodeEAN codeEAN = new BarcodeEAN();
            codeEAN.setCodeType(codeEAN.EAN13);
            codeEAN.setCode(barcode);

            console.log("Reached stage 1");

            document.add(codeEAN.createImageWithBarcode(cb, BaseColor.BLACK, BaseColor.DARK_GRAY));
            addEmptyLine(paragraph, 5);

            PdfPTable table = createTable();
            document.add(table);

            document.close();
        } catch (DocumentException | FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
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
