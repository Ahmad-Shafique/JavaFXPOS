package infrastructure.invoice.printer;

import core.domain.model.entities._utilities.console;

import javax.print.Doc;
import javax.print.DocPrintJob;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import java.io.FileInputStream;

public class PdfPrinter {
    String filepath;

    public PdfPrinter(String filepath){
        this.filepath = filepath;
    }

    public boolean print(){
        try{
            FileInputStream fis = new FileInputStream(filepath);
            Doc pdfDoc = new SimpleDoc(fis, null, null);
            /*DocPrintJob printJob = printService.createPrintJob();
            printJob.print(pdfDoc, new HashPrintRequestAttributeSet());*/
            fis.close();
            return true;
        }catch(Exception e){
            console.log("Error encountered while printing");
            return false;
        }
    }
}
