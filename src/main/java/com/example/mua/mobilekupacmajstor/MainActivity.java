package com.example.mua.mobilekupacmajstor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private Button PrijaviSe, RegistrujSe, Pretrazuj;
    private EditText KorisnickoIme, Lozinka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PrijaviSe = (Button) findViewById(R.id.PrijaviSe);
        RegistrujSe = (Button) findViewById(R.id.RegistrujSe);
        Pretrazuj = (Button) findViewById(R.id.Pretrazuj);

        KorisnickoIme = (EditText) findViewById(R.id.KorisnickoIme);
        Lozinka = (EditText) findViewById(R.id.Lozinka);

        PrijaviSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proverilogin();
            }
        });
        RegistrujSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        Pretrazuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pretrazuj();
            }
        });

    }

    public void pretrazuj(){
        startActivity(new Intent(this, GuestSearchActivity.class));
        Toast.makeText(getApplicationContext(), "Pretrazuj majstore - Gost", Toast.LENGTH_SHORT).show();
    }

    public void register(){
        startActivity(new Intent(this, RegistrationActivity.class));
        Toast.makeText(getApplicationContext(), "Registracija", Toast.LENGTH_SHORT).show();
    }

    public void proverilogin(){

        String korisnickoime = KorisnickoIme.getText().toString();
        String lozinka = Lozinka.getText().toString();
        String poruka = "";

        if(korisnickoime == null || korisnickoime.equals("")) {
            poruka = "Unesite korisnicko ime";
        }
        Podaci.trenutniKorisnik = Podaci.podaci.get(korisnickoime);
        if(Podaci.trenutniKorisnik != null){
            if(lozinka!=null && lozinka.equals(Podaci.trenutniKorisnik.lozinka)){
                Podaci.logovaniKorisnik = Podaci.podaci.get(korisnickoime);
                if(Podaci.trenutniKorisnik.vrsta == Korisnik.Vrsta.KUPAC){
                    poruka = "Dobro dosao kupce";
                }
                else { poruka = "Dobro dosao majstore"; }
            }
            else{
                poruka = "Losa lozinka";
            }
        }
        else{
            poruka = "Los username";
        }

        Toast.makeText(getApplicationContext(), poruka, Toast.LENGTH_LONG).show();
        if(poruka.equals("Dobro dosao kupce")){
            Intent i = new Intent(this,KupacPocetnaActivity.class);
            startActivity(i);
        }else if(poruka.equals("Dobro dosao majstore")){
            Intent i = new Intent(this,MajstorPocetnaActivity.class);
            startActivity(i);
        }
    }
}
