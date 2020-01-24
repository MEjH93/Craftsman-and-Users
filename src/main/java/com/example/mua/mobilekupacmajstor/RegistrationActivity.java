package com.example.mua.mobilekupacmajstor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity{

    private Button RegistrujSe, Nazad;
    private RadioButton RBK, RBM;
    private EditText ImeR, PrezimeR, KorisnickoImeR, LozinkaR, AdresaR, BrojR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        RegistrujSe = (Button) findViewById(R.id.RegistrujSeR);
        Nazad = (Button) findViewById(R.id.NazadR);

        RBK = (RadioButton) findViewById(R.id.RBK);
        RBM = (RadioButton) findViewById(R.id.RBM);

        ImeR = (EditText) findViewById(R.id.ImeR);
        PrezimeR = (EditText) findViewById(R.id.PrezimeR);
        KorisnickoImeR = (EditText) findViewById(R.id.KorisnickoImeR);
        LozinkaR = (EditText) findViewById(R.id.LozinkaR);
        AdresaR = (EditText) findViewById(R.id.AdresaR);
        BrojR = (EditText) findViewById(R.id.BrojR);

        Nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nazad();
            }
        });
        RegistrujSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registracijacheck();
            }
        });

    }

    public void nazad(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void registracijacheck(){
        String korisnickoimeR = KorisnickoImeR.getText().toString();
        String ime = ImeR.getText().toString();
        String prezime = PrezimeR.getText().toString();
        String broj = BrojR.getText().toString();
        String adresa = AdresaR.getText().toString();
        String lozinkaR = LozinkaR.getText().toString();
        String porukaR = "";

        if(korisnickoimeR == null || korisnickoimeR.equals("")) { porukaR = "Unesite korisnicko ime"; }
        else if (Podaci.podaci.containsKey(korisnickoimeR)) { porukaR = "Korisnicko ime je zauzeto"; }
        else if(lozinkaR == null || lozinkaR.equals("")) { porukaR = "Unesite lozinku"; }
        else if(ime == null || ime.equals("") || !ime.matches("[a-zA-Z]+")) { porukaR = "Unesite ime pomocu samo slova"; }
        else if(prezime == null || prezime.equals("") || !prezime.matches("[a-zA-Z]+")) { porukaR = "Unesite prezime pomocu samo slova"; }
        else if(broj == null || broj.equals("") || !broj.matches("[0-9]+")) { porukaR = "Unesite broj pomocu samo cifara"; }
        else if(adresa == null || adresa.equals("")) { porukaR = "Unesite adresu"; }
        else{
            porukaR = "Uspesna registracija" ;

            boolean kk = RBK.isChecked();
            if(kk == true)
                Podaci.podaci.put(korisnickoimeR, new Korisnik(ime, prezime, broj, adresa, korisnickoimeR, lozinkaR, Korisnik.Vrsta.KUPAC, null));
            else {
                List<String> l = new ArrayList<>(); List<String> k = new ArrayList<>();
                Korisnik.Majstor km = new Korisnik.Majstor(1, 10, l, k, 1, 100, 500); //npr. default parametri
                Podaci.podaci.put(korisnickoimeR, new Korisnik(ime, prezime, broj, adresa, korisnickoimeR, lozinkaR, Korisnik.Vrsta.MAJSTOR, km));
            }
        }

        Toast.makeText(getApplicationContext(), porukaR, Toast.LENGTH_LONG).show();
    }
}
