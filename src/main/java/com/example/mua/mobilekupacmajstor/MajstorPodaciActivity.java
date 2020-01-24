package com.example.mua.mobilekupacmajstor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

public class MajstorPodaciActivity extends AppCompatActivity {

    //private Button SacuvajChange, NazadChange;
    //private EditText BrojChange, ImeChange, LozinkaChange, PrezimeChange, AdresaChange;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_majstor_podaci);

        if(Podaci.logovaniKorisnik != null){
            ((EditText)findViewById(R.id.ImeChangee)).setText(Podaci.logovaniKorisnik.ime);
            ((EditText)findViewById(R.id.PrezimeChangee)).setText(Podaci.logovaniKorisnik.prezime);
            ((EditText)findViewById(R.id.BrojChangee)).setText(Podaci.logovaniKorisnik.broj);
            ((EditText)findViewById(R.id.AdresaChangee)).setText(Podaci.logovaniKorisnik.adresa);
            ((EditText)findViewById(R.id.LozinkaChangee)).setText(Podaci.logovaniKorisnik.lozinka);
        }

        ((Button)findViewById(R.id.SacuvajChangee)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sacuvaj();
            }
        });

        ((Button)findViewById(R.id.NazadChangee)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nazad();
            }
        });
    }

    public void nazad(){
        Intent i = new Intent(this,MajstorPocetnaActivity.class);
        startActivity(i);
    }

    public void sacuvaj(){
        if(Podaci.logovaniKorisnik==null
                || ((EditText)findViewById(R.id.ImeChangee)).getText().toString().equals("")
                || ((EditText)findViewById(R.id.PrezimeChangee)).getText().toString().equals("")
                || ((EditText)findViewById(R.id.BrojChangee)).getText().toString().equals("")
                || ((EditText)findViewById(R.id.AdresaChangee)).getText().toString().equals("")
                || ((EditText)findViewById(R.id.LozinkaChangee)).getText().toString().equals("")
                || !((EditText)findViewById(R.id.BrojChangee)).getText().toString().matches("[0-9]+")
                || !((EditText)findViewById(R.id.ImeChangee)).getText().toString().matches("[a-zA-Z]+")
                || !((EditText)findViewById(R.id.PrezimeChangee)).getText().toString().matches("[a-zA-Z]+")){
            Toast.makeText(getApplicationContext(), "Neadekvatni podaci", Toast.LENGTH_LONG).show();
        }
        else{
            Podaci.logovaniKorisnik.ime = ((EditText)findViewById(R.id.ImeChangee)).getText().toString();
            Podaci.logovaniKorisnik.prezime = ((EditText)findViewById(R.id.PrezimeChangee)).getText().toString();
            Podaci.logovaniKorisnik.broj = ((EditText)findViewById(R.id.BrojChangee)).getText().toString();
            Podaci.logovaniKorisnik.adresa = ((EditText)findViewById(R.id.AdresaChangee)).getText().toString();
            Podaci.logovaniKorisnik.lozinka = ((EditText)findViewById(R.id.LozinkaChangee)).getText().toString();

            Toast.makeText(getApplicationContext(), "Podaci su promenjeni", Toast.LENGTH_LONG).show();
        }
    }
}