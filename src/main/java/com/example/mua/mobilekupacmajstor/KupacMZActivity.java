package com.example.mua.mobilekupacmajstor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class KupacMZActivity extends AppCompatActivity {

    public TableLayout t;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kupac_mz);

        t= (TableLayout) findViewById(R.id.TabelaMZKupac);

        t.addView(KupacPocetnaActivity.tlZahteviMajstori, new TableLayout.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));

        Button b = new Button(this);
        b.setText("Nazad");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nazad_redirect();
            }
        });

        t.addView(b, new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
    }

    public void nazad_redirect(){
        Intent i = new Intent(this,KupacPocetnaActivity.class);
        startActivity(i);
    }
}
