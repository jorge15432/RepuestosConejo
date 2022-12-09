package com.example.repuestosconejo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.repuestosconejo.viewmodel.LocaleHelper;

import org.w3c.dom.Text;

public class Cambio extends AppCompatActivity{

    TextView language_dialog;
    boolean lang_selected=true;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cambio);

        language_dialog=(TextView) findViewById(R.id.dialog_language);

        language_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] language={"Español","English"};

                int checkedItem;

                if(lang_selected)
                {
                    checkedItem=0;
                }else{
                    checkedItem=1;
                }
                final AlertDialog.Builder builder=new AlertDialog.Builder(Cambio.this);
                builder.setTitle("Escoge un idioma")
                        .setSingleChoiceItems(language, checkedItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            language_dialog.setText(language[which]);
                            if(language[which].equals("Español"))
                            {
context= LocaleHelper.setLocale(Cambio.this,"es");
resources=context.getResources();
                            }
                            if(language[which].equals("English")){
                                context= LocaleHelper.setLocale(Cambio.this,"en");
                                resources=context.getResources();
                            }
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                builder.create().show();
            }
        });
    }
}
