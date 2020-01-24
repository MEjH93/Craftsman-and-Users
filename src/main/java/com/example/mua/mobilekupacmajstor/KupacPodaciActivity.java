package com.example.mua.mobilekupacmajstor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

public class KupacPodaciActivity extends AppCompatActivity {

    //private Button SacuvajChange, NazadChange;
    //private EditText BrojChange, ImeChange, LozinkaChange, PrezimeChange, AdresaChange;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kupac_podaci);

        if(Podaci.logovaniKorisnik != null){
            ((EditText)findViewById(R.id.ImeChange)).setText(Podaci.logovaniKorisnik.ime);
            ((EditText)findViewById(R.id.PrezimeChange)).setText(Podaci.logovaniKorisnik.prezime);
            ((EditText)findViewById(R.id.BrojChange)).setText(Podaci.logovaniKorisnik.broj);
            ((EditText)findViewById(R.id.AdresaChange)).setText(Podaci.logovaniKorisnik.adresa);
            ((EditText)findViewById(R.id.LozinkaChange)).setText(Podaci.logovaniKorisnik.lozinka);
        }

        ((Button)findViewById(R.id.SacuvajChange)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sacuvaj();
            }
        });

        ((Button)findViewById(R.id.NazadChange)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nazad();
            }
        });
    }

    public void nazad(){
        Intent i = new Intent(this,KupacPocetnaActivity.class);
        startActivity(i);
    }

    public void sacuvaj(){
        if(Podaci.logovaniKorisnik==null
                || ((EditText)findViewById(R.id.ImeChange)).getText().toString().equals("")
                || ((EditText)findViewById(R.id.PrezimeChange)).getText().toString().equals("")
                || ((EditText)findViewById(R.id.BrojChange)).getText().toString().equals("")
                || ((EditText)findViewById(R.id.AdresaChange)).getText().toString().equals("")
                || ((EditText)findViewById(R.id.LozinkaChange)).getText().toString().equals("")
                || !((EditText)findViewById(R.id.BrojChange)).getText().toString().matches("[0-9]+")
                || !((EditText)findViewById(R.id.ImeChange)).getText().toString().matches("[a-zA-Z]+")
                || !((EditText)findViewById(R.id.PrezimeChange)).getText().toString().matches("[a-zA-Z]+")){
            Toast.makeText(getApplicationContext(), "Neadekvatni podaci", Toast.LENGTH_LONG).show();
        }
        else{
            Podaci.logovaniKorisnik.ime = ((EditText)findViewById(R.id.ImeChange)).getText().toString();
            Podaci.logovaniKorisnik.prezime = ((EditText)findViewById(R.id.PrezimeChange)).getText().toString();
            Podaci.logovaniKorisnik.broj = ((EditText)findViewById(R.id.BrojChange)).getText().toString();
            Podaci.logovaniKorisnik.adresa = ((EditText)findViewById(R.id.AdresaChange)).getText().toString();
            Podaci.logovaniKorisnik.lozinka = ((EditText)findViewById(R.id.LozinkaChange)).getText().toString();

            Toast.makeText(getApplicationContext(), "Podaci su promenjeni", Toast.LENGTH_LONG).show();
        }
    }
}