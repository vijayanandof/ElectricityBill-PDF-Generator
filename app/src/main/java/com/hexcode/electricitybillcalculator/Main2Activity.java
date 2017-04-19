package com.hexcode.electricitybillcalculator;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Main2Activity extends AppCompatActivity {

   //This string contains stuff that are to be written to the pdf
    private static final String TAG = "PdfActivity";
    private File pdfFile;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;
    Button button;
    ImageButton imageButton;
    ImageButton imageButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        button = (Button)findViewById(R.id.button3);
        imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton2 = (ImageButton)findViewById(R.id.imageButton2);

        TextView textView = (TextView) findViewById(R.id.textView49);
        textView.setText("Rs."+MainActivity.NET_TOTAL);

        TextView textView1 = (TextView) findViewById(R.id.textView47);
        textView1.setText("Rs."+MainActivity.TOTAL_DEMAND);

        TextView textView2 = (TextView)findViewById(R.id.textView50);
        textView2.setText("Rs."+MainActivity.three[0]);

        TextView textView3 = (TextView)findViewById(R.id.textView52);
        textView3.setText("Rs."+MainActivity.three[1]);

        TextView textView4 = (TextView)findViewById(R.id.textView55);
        textView4.setText("Rs."+MainActivity.three[2]);

        //assigning function for create pdf button
        //create pdf button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createPdfWrapper();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });

        //share button
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previewPdf();
            }
        });

        //cloud upload button
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void createPdfWrapper() throws FileNotFoundException,DocumentException {

        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }


                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        }else {
            createPdf();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    try {
                        createPdfWrapper();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Permission Denied
                    Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void createPdf() throws FileNotFoundException, DocumentException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Created a new directory for PDF");
        }

        pdfFile = new File(docsFolder.getAbsolutePath(),"Final Bill.pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();
        Paragraph preface = new Paragraph("TamilNadu Generation and Distribution Corporation Ltd.\r\n _____________ Circle Office\r\nHigh Tension Bill (Provisional) for the Month of ___________");
        preface.setAlignment(Element.ALIGN_CENTER);
        document.add(preface);
        Paragraph prefac = new Paragraph("Address of the consumer");
        prefac.setAlignment(Element.ALIGN_LEFT);
        document.add(prefac);
        Paragraph pref = new Paragraph("Service No : _________\r\n Bill No : _________\r\nDate of Bill: _________\r\nDue Date          : _________\r\nTariff App./Bld   : _________");
        pref.setAlignment(Element.ALIGN_RIGHT);
        document.add(pref);
        Paragraph pre = new Paragraph("Premitted MD : ____________KVA     Transformer Loss : 0/0 Tr.CAP. 0 KVA");
        pre.setAlignment(Element.ALIGN_CENTER);
        document.add(pre);
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------"));
        Paragraph pr = new Paragraph("Details"+"                                                                 "+"Amount(Rs)");
        pr.setAlignment(Element.ALIGN_CENTER);
        document.add(pr);
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------"));
        document.add(new Paragraph("\r\n1. Industrial Consumption Units:"+"                                                                 "+MainActivity.thr+"\n"));
        document.add(new Paragraph("2.Peak Hr. Consumption Units:"+"                                                                     "+MainActivity.three[1]+"\n"));
        document.add(new Paragraph("3.Night Hr. consumption 5% rebate:"+"                                                              "+MainActivity.three[0]+"\n"));
        document.add(new Paragraph("4.Quaters Consumption:"+"                                                                              "+MainActivity.qua+"\n"));
        document.add(new Paragraph("5.Commercial Consumption:"+"                                                                          "+MainActivity.comm+"\n"));
        document.add(new Paragraph("6.Total Energy chargers:"+"                                                                             "+MainActivity.comm+MainActivity.thr+MainActivity.three[1]+MainActivity.three[0]+MainActivity.qua+"\n"));
        document.add(new Paragraph("7. Demand Charges:"+"                                                                                   "+MainActivity.demand+"\n"));
        document.add(new Paragraph("8.Total Demand Charges and Energy Charges:"+"                                               "+MainActivity.comm+MainActivity.thr+MainActivity.three[1]+MainActivity.three[0]+MainActivity.qua+MainActivity.demand+"\n"));
        document.add(new Paragraph("9.For Non-Availing the supply at the Required Voltage 11kV \n" +
                "\tat .100 Rs. per unit:"+"                                                                                           "+MainActivity.non*0.1+"\n"));
        document.add(new Paragraph("10. Add meter rent:"+"                                                                                     "+MainActivity.rent+"\n"));
        document.add(new Paragraph("11. Add Belated Payment:"+"                                                                           "+MainActivity.pay+"\n"));
        document.add(new Paragraph("12. Add Extra Levy for exceeding limits:"+"\n"));
        document.add(new Paragraph("    a) Quota Consumption Units at Rs 12.70 per unit:"+"                                           "+MainActivity.exd*12.70+"\n"));
        document.add(new Paragraph("    b) Quota Demand at Rs 700.00 per kVA:"+"                                                        "+MainActivity.quo*700+"\n"));
        document.add(new Paragraph("    c) Contracted Max. Dmd at Rs. 700 per kVA:                                               "+MainActivity.con*700+"\n"));
        document.add(new Paragraph("    d) Compensation Charges for Low PF:                                                         "+MainActivity.conpf+"\n"));
        document.add(new Paragraph("    e) Comp. Charges for WM PF RKVAHR:                                                       "+MainActivity.rkva+"\n"));
        document.add(new Paragraph("    f) Evening Peak Energy and Demand:                                                          "+MainActivity.epdf+"\n"));
        document.add(new Paragraph("13. Wind Mill Service Charge:"+"                                                                       "+MainActivity.wsch+"\n"));
        document.add(new Paragraph("14. Add/Less Adjustment Charge:                                                                     "+MainActivity.ash+"\n"));
        document.add(new Paragraph("15. Harmonics Compensation Charge:                                                               "+MainActivity.hcc+"\n"));
        document.add(new Paragraph("16. Cross Subsidy Surcharge:                                                                        "+MainActivity.css+"\n"));
        document.add(new Paragraph("17. Reliability Charge:                                                                                 "+MainActivity.rcs+"\n"));
        document.add(new Paragraph("18. Electricity Tax (Incl rel. chg):                                                                  "+(MainActivity.comm+MainActivity.thr+MainActivity.three[1]+MainActivity.three[0]+MainActivity.qua+MainActivity.demand)*0.5+"\n"));
        document.add(new Paragraph("19. Rounding Off:                                                                                       "+Math.round((MainActivity.comm+MainActivity.thr+MainActivity.three[1]+MainActivity.three[0]+MainActivity.qua+MainActivity.demand)*0.5)+"\n"));
        document.add(new Paragraph("20. Assessment Amount:                                                                                "+MainActivity.assm+"\n"));
        document.add(new Paragraph("21. Other Adjustment:                                                                                   "+MainActivity.otsm+"\n"));
        document.add(new Paragraph("22. Self Generation Tax:                                                                               "+MainActivity.sgt+"\n"));
        document.add(new Paragraph("23. Nett Total:                                                                                           "+(Math.round((MainActivity.comm+MainActivity.thr+MainActivity.three[1]+MainActivity.three[0]+MainActivity.qua+MainActivity.demand+MainActivity.assm+MainActivity.otsm+MainActivity.sgt)))+"\n"));
        document.close();
        Toast.makeText(this, "Pdf Created!", Toast.LENGTH_SHORT)
                .show();

    }
    private void previewPdf() {

        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        java.util.List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdfFile);
            intent.setDataAndType(uri, "application/pdf");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(intent);

        }else{
            Toast.makeText(this,"Download a PDF Viewer to see the generated PDF",Toast.LENGTH_SHORT).show();
        }
    }



}
