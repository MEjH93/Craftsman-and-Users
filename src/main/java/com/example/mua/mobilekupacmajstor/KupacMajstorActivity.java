package com.example.mua.mobilekupacmajstor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import org.w3c.dom.Text;

import java.util.*;

public class KupacMajstorActivity extends AppCompatActivity {

    private Korisnik majstor = null;
    private TextView Ime, Prezime, Adresa, Telefon, POcena, Komentar1, Komentar2, Komentar3;
    private Button KomentarB, OcenaB, Facebook, Twitter, Zahtev, Nazad;
    private EditText Komentar, Ocena, AdresaA, OpstinaA;
    private RadioButton Gotovina, Racun;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kupac_majstor);

        majstor = Podaci.trenutniKorisnik;

        //ovde hocu i poslednje comm i ocenu ovog majstora :) (mozda i samo posl. comm)
        //ocena 1-10!!!
        //neki ispis za FB/TW ili sta god

        Ime = (TextView) findViewById(R.id.Ime);
        Prezime = (TextView) findViewById(R.id.Prezime);
        Adresa = (TextView) findViewById(R.id.Adresa);
        Telefon = (TextView) findViewById(R.id.Telefon);
        POcena = (TextView) findViewById(R.id.POcena);
        Komentar1 = (TextView) findViewById(R.id.Komentar1);
        Komentar2 = (TextView) findViewById(R.id.Komentar2);
        Komentar3 = (TextView) findViewById(R.id.Komentar3);

        if(Podaci.trenutniKorisnik != null && Podaci.trenutniKorisnik.majstor != null){
            Ime.setText("Ime : "+Podaci.trenutniKorisnik.ime);
            Prezime.setText("Prezime : "+Podaci.trenutniKorisnik.prezime);
            Adresa.setText("Adresa : "+Podaci.trenutniKorisnik.adresa);
            Telefon.setText("Broj : "+Podaci.trenutniKorisnik.broj);
            POcena.setText("Ocena : "+Podaci.trenutniKorisnik.majstor.prosecnaOcena());

            List<String> l = Podaci.trenutniKorisnik.majstor.komentari;
            if(l!=null && l.size()!=0) {
                if(l.size()>=1) Komentar1.setText("Komentar#1 : " + Podaci.trenutniKorisnik.majstor.komentari.get(0));
                if(l.size()>=2) Komentar2.setText("Komentar#2 : " + Podaci.trenutniKorisnik.majstor.komentari.get(1));
                if(l.size()>=3) Komentar3.setText("Komentar#3 : " + Podaci.trenutniKorisnik.majstor.komentari.get(2));
            }
        }

        Gotovina = (RadioButton) findViewById(R.id.Gotovina);
        Racun = (RadioButton) findViewById(R.id.Racun);

        Komentar = (EditText) findViewById(R.id.Komentar);
        Ocena = (EditText) findViewById(R.id.Ocena);
        AdresaA = (EditText) findViewById(R.id.AdresaA);
        OpstinaA = (EditText) findViewById(R.id.OpstinaA);

        KomentarB = (Button) findViewById(R.id.KomentarB);
        OcenaB = (Button) findViewById(R.id.OcenaB);
        Facebook = (Button) findViewById(R.id.Facebook);
        Twitter = (Button) findViewById(R.id.Twitter);
        Zahtev = (Button) findViewById(R.id.Zahtev);
        Nazad = (Button) findViewById(R.id.Nazad);

        KomentarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Podaci.trenutniKorisnik!=null && Podaci.trenutniKorisnik.majstor!=null && !Komentar.getText().toString().equals("")) {
                    Podaci.trenutniKorisnik.majstor.komentari.add(Komentar.getText().toString());
                    Toast.makeText(getApplicationContext(), "Dodat komentar", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(getApplicationContext(), "Komentar mora imati tekst", Toast.LENGTH_LONG).show();
            }
        });

        OcenaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int o = IsStringANumberBetween1and10(Ocena.getText().toString());

                if(Podaci.trenutniKorisnik!=null && Podaci.trenutniKorisnik.majstor!=null && o>0) {
                    Podaci.trenutniKorisnik.majstor.brojOcena++;
                    Podaci.trenutniKorisnik.majstor.zbirOcena+=o;
                    Toast.makeText(getApplicationContext(), "Dodata ocena", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(getApplicationContext(), "Ocena je broj izmedju 1 i 10", Toast.LENGTH_LONG).show();
            }
        });

        Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Share-ovano na Facebook", Toast.LENGTH_LONG).show();
            }
        });

        Twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Share-ovano na Twitter", Toast.LENGTH_LONG).show();
            }
        });

        Nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nazad();
            }
        });

        Zahtev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zahtev();
            }
        });
    }

    public void nazad(){
        Intent i = new Intent(this,KupacPocetnaActivity.class); //vracamo se na pocetnu xD
        startActivity(i);
    }

    private int IsStringANumberBetween1and10(String s){
        if(s == null || s.length() == 0) { return -1; }
        try { int thenumber = Integer.valueOf(s);

            if(thenumber <= 0 ) { return -2; }

            if(thenumber > 10) { return -4; }

            return thenumber;
        }
        catch (Exception e) { return -3; }
    }

    public void zahtev(){
        //AdresaA, OpstinaA, Gotovina

        if(AdresaA.getText().toString().equals("") || OpstinaA.getText().toString().equals("")){
             Toast.makeText(getApplicationContext(), "Popunite sve podatke", Toast.LENGTH_LONG).show();
        }
        else{
            boolean gotovina = Gotovina.isChecked();
            int i = 0;
            if(gotovina) i = 1;
            Zahtev z = new Zahtev(AdresaA.getText().toString(), OpstinaA.getText().toString(), i, Podaci.logovaniKorisnik); //kupac
            z.majstor = Podaci.trenutniKorisnik; //majstor

            List<Zahtev> l = Podaci.zahtevi.remove(Podaci.logovaniKorisnik.korisnickoime);
            if(l==null){
                l = new ArrayList<>();
            }
            l.add(z);
            Podaci.zahtevi.put(Podaci.logovaniKorisnik.korisnickoime, l);

            Toast.makeText(getApplicationContext(), "Dodat zahtev", Toast.LENGTH_LONG).show();
        }
    }
}
