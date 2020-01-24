package com.example.mua.mobilekupacmajstor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import java.util.*;

public class MajstorZahteviActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_majstor_zahtevi);

        TableLayout t = (TableLayout) findViewById(R.id.Tabelica);

        //hm = new HashMap<>();

        //...
        TableLayout ttt = new TableLayout(this);

        if(Podaci.logovaniKorisnik == null) return;

        final ArrayList<Zahtev>[] l = Podaci.zahtevi.values().toArray(new ArrayList[0]);

        int redovi = 0;

        int currentId;
        int lastId = Zahtev.poslId;
        boolean uslov;

        if(MajstorPocetnaActivity.RASTUCE){
            currentId = 0;
            uslov = (currentId <= lastId);
        }else{
            currentId = lastId;
            uslov = (currentId >= 0);
        }

        while (uslov) {

            for (int i = 0; i < l.length; i++) {
                for (int j = 0; j < l[i].size(); j++) {
                    if (l[i].get(j).majstor.equals(Podaci.logovaniKorisnik) && l[i].get(j).id == currentId &&
                            ((l[i].get(j).status < 2) || (l[i].get(j).status == 2 && l[i].get(j).izvrsenZahtev.placeno == 0))) {

                        //kreirani, zapoceti, zavrseni neplaceni !!!

                        //sa null -> CONFIRM DELETE (DELETE -> nema ga vise ovde, CONFIRM -> DONE)
                        //nista ga ne brise iz Podataka!
                        //sa mnom kao majstorom -> DONE -> nema ga vise ovde

                        //TableRow tr= new TableRow(this);
                        //Button b = new Button (this);
                        //b.setText();
                        //tr.addView(b, new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
                        //ttt.addView(tr, redovi++);

                        final int ii = i;
                        final int jj = j;

                        TableRow tr = new TableRow(this);
                        TextView e1 = new TextView(this);
                        e1.setText("Id: " + l[i].get(j).id);
                        tr.addView(e1, 350, 50);
                        ttt.addView(tr, redovi++);

                        tr = new TableRow(this);
                        e1 = new TextView(this);
                        e1.setText("User: " + l[i].get(j).kupac.korisnickoime);
                        tr.addView(e1, 350, 50);
                        ttt.addView(tr, redovi++);

                        tr = new TableRow(this);
                        e1 = new TextView(this);
                        e1.setText("Broj: " + l[i].get(j).kupac.broj);
                        tr.addView(e1, 350, 50);
                        ttt.addView(tr, redovi++);

                        tr = new TableRow(this);
                        e1 = new TextView(this);
                        e1.setText("Adresa: " + l[i].get(j).adresa);
                        tr.addView(e1, 350, 50);
                        ttt.addView(tr, redovi++);

                        tr = new TableRow(this);
                        e1 = new TextView(this);
                        e1.setText("Opstina: " + l[i].get(j).opstina);
                        tr.addView(e1, 350, 50);
                        ttt.addView(tr, redovi++);

                        tr = new TableRow(this);
                        e1 = new TextView(this);
                        e1.setText("Status: " + l[i].get(j).statusOpis());
                        tr.addView(e1, 350, 50);
                        ttt.addView(tr, redovi++);

                        if (l[i].get(j).status == 0) {
                            //prihvati ili odbij
                            tr = new TableRow(this);
                            Button b = new Button(this);
                            b.setText("Prihvati");
                            b.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //hm.put((Button)view, l[ii].get(jj));

                                    l[ii].get(jj).majstor = Podaci.logovaniKorisnik;
                                    l[ii].get(jj).status = 1;

                                    l[ii].get(jj).izvrsenZahtev = new Zahtev.IzvrsenZahtev(new Date(), null);

                                    nazad_redirect();
                                    Toast.makeText(getApplicationContext(), "Uspesno prihvacen zahtev", Toast.LENGTH_LONG).show();
                                }
                            });
                            tr.addView(b, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                            ttt.addView(tr, redovi++);

                            tr = new TableRow(this);
                            b = new Button(this);
                            b.setText("Odbij");
                            b.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //hm.put((Button)view, l[ii].get(jj));

                                    l[ii].get(jj).majstor = Podaci.logovaniKorisnik;
                                    l[ii].get(jj).status = 3;
                                    nazad_redirect();
                                    Toast.makeText(getApplicationContext(), "Uspesno odbijen zahtev", Toast.LENGTH_LONG).show();
                                }
                            });
                            tr.addView(b, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                            ttt.addView(tr, redovi++);
                        } else if (l[i].get(j).status == 1) {
                            //gotovo
                            tr = new TableRow(this);
                            Button b = new Button(this);
                            b.setText("Gotovo");
                            b.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //hm.put((Button)view, l[ii].get(jj));

                                    //l[ii].get(jj).majstor = Podaci.logovaniKorisnik;
                                    l[ii].get(jj).status = 2;
                                    l[ii].get(jj).izvrsenZahtev.kraj = new Date();


                                    nazad_redirect();
                                    Toast.makeText(getApplicationContext(), "Uspesno zavrsen zahtev", Toast.LENGTH_LONG).show();
                                }
                            });
                            tr.addView(b, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                            ttt.addView(tr, redovi++);
                        } else if (l[i].get(j).status == 2) {
                            //naplaceno
                            tr = new TableRow(this);
                            Button b = new Button(this);
                            b.setText("Naplaceno");
                            b.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    l[ii].get(jj).izvrsenZahtev.placeno = 1;
                                    nazad_redirect();
                                    Toast.makeText(getApplicationContext(), "Uspesno naplacen zahtev", Toast.LENGTH_LONG).show();
                                }
                            });
                            tr.addView(b, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                            ttt.addView(tr, redovi++);
                        }

                        tr = new TableRow(this);
                        Button b = new Button(this);
                        b.setText("Mapa");
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/" + l[ii].get(jj).adresa + ",+Belgrade")));
                            }
                        });
                        tr.addView(b, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                        ttt.addView(tr, redovi++);

                        tr = new TableRow(this);
                        e1 = new TextView(this);
                        e1.setText("");
                        tr.addView(e1, 350, 50);
                        ttt.addView(tr, redovi++);
                    }
                }
            }

            if(MajstorPocetnaActivity.RASTUCE){
                currentId++;
                uslov = (currentId <= lastId);
            }
            else {
                currentId--;
                uslov = (currentId >= 0);
            }
        }

        //...

        t.addView(ttt, new TableLayout.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));

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

    //public static HashMap<Button, Zahtev> hm;

    public void nazad_redirect(){
        Intent i = new Intent(this,MajstorPocetnaActivity.class);
        startActivity(i);
    }
}
