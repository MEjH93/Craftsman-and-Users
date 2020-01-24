package com.example.mua.mobilekupacmajstor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import java.util.*;

public class KupacPocetnaActivity  extends AppCompatActivity {

    private Button LicniPodaciKS, PretraziKS, MojiZahteviKS, MojiMajstoriKS, OdjaviMeKS;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kupac_pocetna);

        LicniPodaciKS = (Button) findViewById(R.id.LicniPodaciKS); //i pass hehe
        PretraziKS = (Button) findViewById(R.id.PretraziKS);
        MojiZahteviKS = (Button) findViewById(R.id.MojiZahteviKS);
        MojiMajstoriKS = (Button) findViewById(R.id.MojiMajstoriKS);
        OdjaviMeKS = (Button) findViewById(R.id.OdjaviMeKS);

        OdjaviMeKS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nazad();
            }
        });

        PretraziKS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pretrazi();
            }
        });

        LicniPodaciKS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                licnipodaci();
            }
        });

        MojiZahteviKS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mojizahtevi();
            }
        });

        MojiMajstoriKS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mojimajstori();
            }
        });
    }

    public void nazad(){
        Podaci.trenutniKorisnik = null;
        Podaci.logovaniKorisnik = null;
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void pretrazi(){
        Intent i = new Intent(this,KupacSearchActivity.class);
        startActivity(i);
    }

    public void licnipodaci(){
        Intent i = new Intent(this,KupacPodaciActivity.class);
        startActivity(i);
    }

    public static TableLayout tlZahteviMajstori;

    public void mojizahtevi(){
         if(Podaci.logovaniKorisnik == null) return;

        List<Zahtev> zahtevi = Podaci.zahtevi.get(Podaci.logovaniKorisnik.korisnickoime);

        tlZahteviMajstori = new TableLayout(this);

        int redovi = 0;
        for(int i=0; zahtevi!=null && i<zahtevi.size(); i++){
            String majstor = (zahtevi.get(i).majstor == null) ? "Nema" : zahtevi.get(i).majstor.korisnickoime;

            TableRow tr= new TableRow(this);
            TextView b = new TextView(this);
            b.setText("Majstor: " + majstor);
            tr.addView(b, new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            tlZahteviMajstori.addView(tr, redovi++);

            tr= new TableRow(this);
            b = new TextView(this);
            b.setText("Status: " + zahtevi.get(i).statusOpis());
            tr.addView(b, new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            tlZahteviMajstori.addView(tr, redovi++);

            tr= new TableRow(this);
            b = new TextView(this);
            b.setText("Adresa: " + zahtevi.get(i).adresa);
            tr.addView(b, new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            tlZahteviMajstori.addView(tr, redovi++);

            tr= new TableRow(this);
            b = new TextView(this);
            b.setText("Opstina: " + zahtevi.get(i).opstina);
            tr.addView(b, new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            tlZahteviMajstori.addView(tr, redovi++);

            tr= new TableRow(this);
            b = new TextView(this);
            b.setText("Placanje: " + zahtevi.get(i).placanje());
            tr.addView(b, new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            tlZahteviMajstori.addView(tr, redovi++);

            tr= new TableRow(this);
            TextView e1 = new TextView(this);
            e1.setText("");
            tr.addView(e1, new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            tlZahteviMajstori.addView(tr, redovi++);

        }

        Intent i = new Intent(this,KupacMZActivity.class);
        startActivity(i);
    }

    public void mojimajstori(){

        if(Podaci.logovaniKorisnik == null) return; //nece se desiti

        tlZahteviMajstori = new TableLayout(this);

        List<Zahtev> zahtevi = Podaci.zahtevi.get(Podaci.logovaniKorisnik.korisnickoime);

        List<String> listamajstora = new ArrayList<>();
        for(int i=0; zahtevi!=null && i<zahtevi.size(); i++){
            if(zahtevi.get(i).majstor != null){
                listamajstora.add(zahtevi.get(i).majstor.korisnickoime);
            }
        }

        int redovi = 0;
        for(int i=0; i<listamajstora.size(); i++){

                TableRow tr= new TableRow(this);
                TextView b = new TextView(this);
                b.setText(listamajstora.get(i));
                tr.addView(b, new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
                tlZahteviMajstori.addView(tr, redovi++);

                tr= new TableRow(this);
                TextView e1 = new TextView(this);
                e1.setText("");
                tr.addView(e1, new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
                tlZahteviMajstori.addView(tr, redovi++);
        }

        Intent i = new Intent(this,KupacMZActivity.class);
        startActivity(i);
    }

}
