package com.upm.bo0414.sp_recoversavetextcontentdb;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText txtName;
    TextView txtMSNInfo, txtContent ;
    Button butonSave, butonRecover, butonEmtyFile;
    FileOutputStream fos;
    private String MIW_TAG = "MIW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accion_content);

        txtName = (EditText) findViewById(R.id.editTextName);
        txtMSNInfo = (TextView) findViewById(R.id.textMSN);
        txtContent= (TextView) findViewById(R.id.textViewContent);

        butonSave = (Button) findViewById(R.id.buttonSave);
        butonRecover = (Button) findViewById(R.id.buttonRecover);
        butonEmtyFile= (Button) findViewById(R.id.buttonEmtyFile);



        //ESCUCHAN LA ACCION CLICK
        butonRecover.setOnClickListener(this);
        butonSave.setOnClickListener(this);
        butonEmtyFile.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        String msn = "No sea realizado ninguna accion";
        switch (v.getId()) {
            case (R.id.buttonSave):

                try {
                    OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("contenidoDB.txt", Context.MODE_APPEND));
                    msn = "Contenido guardado";
                    fout.write(String.valueOf(txtContent));
                    fout.close();
                    Log.i(MIW_TAG, msn);
                    txtMSNInfo.setText(msn);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(MIW_TAG, e.getMessage());
                }
            case (R.id.buttonRecover):
                try {
                    BufferedReader contentReader = new BufferedReader(new InputStreamReader(openFileInput("contenidoDB.txt")));
                    msn = "Archivo Leido";
                    Log.i(MIW_TAG, msn);
                    txtContent.setText(contentReader.readLine());
                    txtMSNInfo.setText(msn);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(MIW_TAG, e.getMessage());
                }


                break;
            case (R.id.buttonEmtyFile):

                try {
                    //TODO EMTY FILE

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(MIW_TAG, e.getMessage());
                }

                break;
            default:
                break;
        }
    }
}
