package com.hexcode.electricitybillcalculator;

import android.os.Environment;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;


public class PdfCreator {

    String outPath;

    Calendar c;

    PdfCreator(String content){
        c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        outPath = Environment.getExternalStorageDirectory()+"/"+year+"_"+month+"_"+date+".pdf";
        Document doc = new Document();
        try{
            PdfWriter.getInstance(doc,new FileOutputStream(outPath));
            doc.open();
            doc.add(new Paragraph(content));
            doc.close();
        }catch (DocumentException e){
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

}
