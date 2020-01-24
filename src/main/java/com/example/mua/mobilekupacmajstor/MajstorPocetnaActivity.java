package com.example.mua.mobilekupacmajstor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class MajstorPocetnaActivity extends AppCompatActivity {

    private Button Odjava, MojiKupci, Podaciii, Zahtevi;
    //sem ovih STRANICA bice i o ZAHTEVU gde moze da ima CONFIRM DELETE ili da ima DONE (ako je CONFIRM) (nakon DONE/DELETE) zahtev nestaje odatle, ali ostaje evidentiran
    //<!--bice i detaljno zahtev gde moze da ga prihvati, odbije ili oznaci zavrsenim, ovde ce biti i SORTIRANJE-->
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_majstor_pocetna);

        Odjava = (Button) findViewById(R.id.Odjava); //i pass hehe
        MojiKupci = (Button) findViewById(R.id.MojiKupci);
        Podaciii = (Button) findViewById(R.id.Podaci);
        Zahtevi = (Button) findViewById(R.id.Zahtevi);

        Odjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nazad();
            }
        });

        Zahtevi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zahtevi();
            }
        });

        IdR = (RadioButton) findViewById(R.id.IdR);

        Podaciii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                podaciii();
            }
        });

        MojiKupci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mojikupci();
            }
        });
    }

    public void podaciii(){
        Intent ii = new Intent(this,MajstorPodaciActivity.class);
        startActivity(ii);
    }

    public static TableLayout KupciTableLayout;

    public void mojikupci(){
        if(Podaci.logovaniKorisnik == null) return; //nece se desiti

        KupciTableLayout = new TableLayout(this);

        final ArrayList<Zahtev>[] l = Podaci.zahtevi.values().toArray(new ArrayList[0]);

        int redovi = 0;

        for (int i = 0; i < l.length; i++) {
            for (int j = 0; j < l[i].size(); j++) {
                if(l[i].get(j).majstor == Podaci.logovaniKorisnik){

                    TableRow tr= new TableRow(this);
                    TextView b = new TextView(this);
                    b.setText(l[i].get(j).kupac.korisnickoime);
                    tr.addView(b, new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
                    KupciTableLayout.addView(tr, redovi++);

                    tr= new TableRow(this);
                    TextView e1 = new TextView(this);
                    e1.setText("");
                    tr.addView(e1, new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
                    KupciTableLayout.addView(tr, redovi++);
                }
            }

        Intent ii = new Intent(this,MajstorKupciActivity.class);
        startActivity(ii);
    }

}

    private RadioButton IdR;
    public static boolean RASTUCE;

    public void nazad(){
        Podaci.trenutniKorisnik = null;
        Podaci.logovaniKorisnik = null;
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void zahtevi(){
        if(IdR.isChecked()){
            RASTUCE = true;
        }
        else RASTUCE = false;

        Intent i = new Intent(this,MajstorZahteviActivity.class);
        startActivity(i);
    }
}
