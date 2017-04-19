package com.hexcode.electricitybillcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    int[][] val = new int[5][5];
    public static int[] res = new int[5];
    public static double[] three = new double[4];
    double val1 = 0,val2 = 0;
    public static Integer qua;
    public static Integer comm;
    public static Integer non;
    public static double thr,demand,rent,pay,exd,quo,con,conpf,rkva,epdf,wsch,ash,hcc,css,rcs,assm,otsm,sgt;
    public static double TOTAL_DEMAND;
    public static double NET_TOTAL;
    private EditText editText;

    private Spinner spinner;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),"PLEASE FILL ALL THE BLANKS",Toast.LENGTH_LONG).show();

        spinner = (Spinner)findViewById(R.id.planets_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int pos , long id){
                try {
                     val2 = Integer.parseInt(((EditText) findViewById(R.id.editText2)).getText().toString());
                }catch (Exception e){
                    val2 = 0;
                }

                Toast.makeText(getBaseContext(),"value is loaded",Toast.LENGTH_LONG).show();
                switch (pos){
                    case 0:{
                        Toast.makeText(getBaseContext(),"PLEASE SELECT A TARIFF",Toast.LENGTH_LONG).show();
                    }
                    break;
                    case 1:{
                        TOTAL_DEMAND = (val1*6.35)+(val2*350);
                        thr=6.35*(res[0]+res[1]+res[2]+res[3]+res[4]);
                        demand=(val2*350);
                    }
                    break;
                    case 2:{
                        TOTAL_DEMAND = (val1*6.35)+(val2*300);
                        thr=6.35*(res[0]+res[1]+res[2]+res[3]+res[4]);
                        demand=(val2*300);
                    }
                    break;
                    case 3:{
                        TOTAL_DEMAND = (val1*6.35)+(val2*350);
                        thr=6.35*(res[0]+res[1]+res[2]+res[3]+res[4]);
                        demand=(val2*350);
                    }
                    break;
                    case 4:{
                        TOTAL_DEMAND = (val1*6.35)+(val2*350);
                        thr=6.35*(res[0]+res[1]+res[2]+res[3]+res[4]);
                        demand=(val2*350);
                    }
                    break;
                    case 5:{
                        TOTAL_DEMAND = (val1*8)+(val2*350);
                        thr=8*(res[0]+res[1]+res[2]+res[3]+res[4]);
                        demand=(val2*350);
                    }
                    break;
                    case 6:{
                        TOTAL_DEMAND = (val1*0.0)+(val2*0);
                        demand=0;
                        thr=0;
                    }
                    break;
                    case 7:{
                        TOTAL_DEMAND = (val1*11)+(val2*350);
                        thr=11*(res[0]+res[1]+res[2]+res[3]+res[4]);
                        demand=(val2*350);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });


        button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data();

                val[0][0] = Integer.parseInt(((EditText)findViewById(R.id.editText)).getText().toString());
                val[0][1] = Integer.parseInt(((EditText)findViewById(R.id.editText101)).getText().toString());
                val[0][2] = Integer.parseInt(((EditText)findViewById(R.id.editText102)).getText().toString());
                val[0][3] = Integer.parseInt(((EditText)findViewById(R.id.editText103)).getText().toString());
                val[0][4] = Integer.parseInt(((EditText)findViewById(R.id.editText104)).getText().toString());
                val[1][0] = Integer.parseInt(((EditText)findViewById(R.id.editText105)).getText().toString());
                val[1][1] = Integer.parseInt(((EditText)findViewById(R.id.editText106)).getText().toString());
                val[1][2] = Integer.parseInt(((EditText)findViewById(R.id.editText107)).getText().toString());
                val[1][3] = Integer.parseInt(((EditText)findViewById(R.id.editText108)).getText().toString());
                val[1][4] = Integer.parseInt(((EditText)findViewById(R.id.editText109)).getText().toString());
                val[2][0] = Integer.parseInt(((EditText)findViewById(R.id.editText110)).getText().toString());
                val[2][1] = Integer.parseInt(((EditText)findViewById(R.id.editText111)).getText().toString());
                val[2][2] = Integer.parseInt(((EditText)findViewById(R.id.editText112)).getText().toString());
                val[2][3] = Integer.parseInt(((EditText)findViewById(R.id.editText113)).getText().toString());
                val[2][4] = Integer.parseInt(((EditText)findViewById(R.id.editText114)).getText().toString());
                val[3][0] = Integer.parseInt(((EditText)findViewById(R.id.editText115)).getText().toString());
                val[3][1] = Integer.parseInt(((EditText)findViewById(R.id.editText116)).getText().toString());
                val[3][2] = Integer.parseInt(((EditText)findViewById(R.id.editText117)).getText().toString());
                val[3][3] = Integer.parseInt(((EditText)findViewById(R.id.editText118)).getText().toString());
                val[3][4] = Integer.parseInt(((EditText)findViewById(R.id.editText119)).getText().toString());
                val[4][0] = Integer.parseInt(((EditText)findViewById(R.id.editText120)).getText().toString());
                val[4][1] = Integer.parseInt(((EditText)findViewById(R.id.editText121)).getText().toString());
                val[4][2] = Integer.parseInt(((EditText)findViewById(R.id.editText122)).getText().toString());
                val[4][3] = Integer.parseInt(((EditText)findViewById(R.id.editText123)).getText().toString());
                val[4][4] = Integer.parseInt(((EditText)findViewById(R.id.editText124)).getText().toString());
                qua = Integer.parseInt(((EditText)findViewById(R.id.editText27)).getText().toString());
                comm = Integer.parseInt(((EditText)findViewById(R.id.editText28)).getText().toString());
                non= Integer.parseInt(((EditText)findViewById(R.id.editText29)).getText().toString());
                rent= Integer.parseInt(((EditText)findViewById(R.id.editText30)).getText().toString());
                pay= Integer.parseInt(((EditText)findViewById(R.id.editText31)).getText().toString());
                exd= Integer.parseInt(((EditText)findViewById(R.id.editText32)).getText().toString());
                quo= Integer.parseInt(((EditText)findViewById(R.id.editText33)).getText().toString());
                con= Integer.parseInt(((EditText)findViewById(R.id.editText34)).getText().toString());
                conpf= Integer.parseInt(((EditText)findViewById(R.id.editText35)).getText().toString());
                rkva= Integer.parseInt(((EditText)findViewById(R.id.editText36)).getText().toString());
                epdf= Integer.parseInt(((EditText)findViewById(R.id.editText37)).getText().toString());
                wsch= Integer.parseInt(((EditText)findViewById(R.id.editText38)).getText().toString());
                ash= Integer.parseInt(((EditText)findViewById(R.id.editText39)).getText().toString());
                hcc= Integer.parseInt(((EditText)findViewById(R.id.editText40)).getText().toString());
                css= Integer.parseInt(((EditText)findViewById(R.id.editText41)).getText().toString());
                rcs= Integer.parseInt(((EditText)findViewById(R.id.editText42)).getText().toString());
                assm=Integer.parseInt(((EditText)findViewById(R.id.editText3)).getText().toString());
                otsm=Integer.parseInt(((EditText)findViewById(R.id.editText43)).getText().toString());
                sgt=Integer.parseInt(((EditText)findViewById(R.id.editText44)).getText().toString());

                res[0] = val[0][0]-val[1][0]-val[2][0]-val[3][0]-val[4][0];
                res[1] = val[0][1]-val[1][1]-val[2][1]-val[3][1]-val[4][1];
                res[2] = val[0][2]-val[1][2]-val[2][2]-val[3][2]-val[4][2];
                res[3] = val[0][3]-val[1][3]-val[2][3]-val[3][3]-val[4][3];
                res[4] = val[0][4]-val[1][4]-val[2][4]-val[3][4]-val[4][4];

                three[0] = (res[4]) * 0.95;
                three[1] = (res[0] + res[1]) * 1.2;
                three[2] = res[2]+res[3];

                val1 = three[0] + three[1] + three[2];

                Intent intent = new Intent("com.sibivarmanl.electricitybillcalculator.Main2Activity");
                startActivity(intent);
                for(int i = 0; i < 5 ; i++){
                    for(int j = 0 ; j < 5 ; j++){
                        System.out.println(val[i][j]);
                    }
                }
            }
        });
    }
    public void data(){
        LinkedList linkedList = new LinkedList();

        for(int i = R.id.editText25; i < R.id.editText44+1 ; i+=2){
            editText = (EditText)findViewById(i);
            try {
                linkedList.add(Integer.parseInt(editText.getText().toString()));
            }catch (Exception e){
                linkedList.add(0);
            }
        }
        NET_TOTAL =
                linkedList.sum(linkedList.node) + TOTAL_DEMAND;
    }
}
