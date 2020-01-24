package com.example.mua.mobilekupacmajstor;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.*;

public class KupacSearchActivity extends AppCompatActivity {

    private EditText CenaOdKS, CenaDoKS, PosaoKS, SpecijalneTehnikeKS, OcenaDoKS, OcenaOdKS, IskustvoOdKS, IskustvoDoKS;
    private Button PretraziKS, NazadKS;
    private EditText DatumOdKS, DatumDoKS;
    private CheckBox HitnostKS;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kupac_search);

        CenaOdKS = (EditText) findViewById(R.id.CenaOdKS);
        CenaDoKS = (EditText) findViewById(R.id.CenaDoKS);
        OcenaOdKS = (EditText) findViewById(R.id.OcenaOdKS);
        OcenaDoKS = (EditText) findViewById(R.id.OcenaDoKS);
        IskustvoOdKS = (EditText) findViewById(R.id.IskustvoOdKS);
        IskustvoDoKS = (EditText) findViewById(R.id.IskustvoDoKS);
        PosaoKS = (EditText) findViewById(R.id.PosaoKS);
        SpecijalneTehnikeKS = (EditText) findViewById(R.id.SpecijalneTehnikeKS);

        PretraziKS = (Button) findViewById(R.id.PretraziKS);
        NazadKS = (Button) findViewById(R.id.NazadKS);

        DatumOdKS = (EditText) findViewById(R.id.DatumOdKS);
        DatumDoKS = (EditText) findViewById(R.id.DatumDoKS);

        HitnostKS = (CheckBox) findViewById(R.id.HitnostKS);

        NazadKS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nazad_redirect();
            }
        });
        PretraziKS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pretrazi_redirect();
            }
        });

    }

    public int[] okdatum(String s){
        if(s==null || s.equals("") || s.length()==0) return new int[] {-1, -1};

        //21/05/2017 ili 21:05:2017 ili tako nestooo

        if(s.length() != 10) return new int[] {-2};

        int[] i = {0,0,0};

        try{
            i[0] = Integer.valueOf(s.substring(0,2));
            i[1] = Integer.valueOf(s.substring(3,5));
            i[2] = Integer.valueOf(s.substring(6));
        }catch(Exception e){
            return new int[] {-3};
        }

        if(i[2] <= 0 || i[1] <= 0 || i[0] <= 0) return new int[] {-4};

        if(i[1] > 12) return new int[] {-5};

        if(i[0] > 31) return new int[] {-6};

        if(i[1] == 2 && i[0] > 28)  return new int[] {-7};

        if((i[1] == 4 || i[1] == 6 || i[1] == 9 || i[1] == 11) && i[0] == 31) return new int[] {-8};

        if(i[2] < 2015 || i[2] > 2025) return new int[] {-9};

        return i;
    }

    public void nazad_redirect(){
        Intent i = new Intent(this,KupacPocetnaActivity.class);
        startActivity(i);
    }

    private int IsStringANumber(String s){
        if(s == null || s.length() == 0) { return -1; }
        try { int thenumber = Integer.valueOf(s);

            if(thenumber < 0 ) { return -2; }

            return thenumber; }
        catch (Exception e) { return -3; }
    }

    private boolean imapreseka(int min1, int max1, int min2, int max2){
        if(max2 < min1) return false; //min2,max2,min1,max1
        if(max1 < min2) return false; //min1,max1,min2,max2
        return true;
    }

    public void pretrazi_redirect(){
        String porukaP = "";

        String cenaOd = CenaOdKS.getText().toString();
        String cenaDo = CenaDoKS.getText().toString();
        String ocenaOd = OcenaOdKS.getText().toString();
        String ocenaDo = OcenaDoKS.getText().toString();
        String iskustvoOd = IskustvoOdKS.getText().toString();
        String iskustvoDo = IskustvoDoKS.getText().toString();

        //List<String> listicica = new ArrayList<>();
        //listicica.add(cenaOd); listicica.add(cenaDo); listicica.add(ocenaOd);
        //listicica.add(ocenaDo); listicica.add(iskustvoOd); listicica.add(iskustvoDo);
        //porukaP = proveri(listicica);

        String posao = PosaoKS.getText().toString();
        String specijalneTehnike = SpecijalneTehnikeKS.getText().toString();

        //int[] datumod = {0, 0, 0};
        //int[] datumdo = {0, 0, 0};

        /*if (DatumOdGS.getYear() != 2000){
            datumod[0] = DatumOdGS.getDayOfMonth();
            datumod[1] = DatumOdGS.getMonth();
            datumod[2] = DatumOdGS.getYear();
        }
        if(DatumDoGS.getYear() != 2000){
            datumdo[0] = DatumDoGS.getDayOfMonth();
            datumdo[1] = DatumDoGS.getMonth();
            datumdo[2] = DatumDoGS.getYear();
        }*/

        HashMap<Korisnik, Boolean> mapica = new HashMap<>();
        Korisnik[] korisnici = Podaci.podaci.values().toArray(new Korisnik[0]);
        for(int i=0; i<korisnici.length; i++){
            if(korisnici[i].majstor != null && korisnici[i].vrsta == Korisnik.Vrsta.MAJSTOR){
                mapica.put(korisnici[i], true);
            }
        }

        int cenaod = IsStringANumber(cenaOd);
        int cenado = IsStringANumber(cenaDo);
        int ocenaod = IsStringANumber(ocenaOd);
        int ocenado = IsStringANumber(ocenaDo);
        int iskustvood = IsStringANumber(iskustvoOd);
        int iskustvodo = IsStringANumber(iskustvoDo);
        if(cenaod < -1 || cenado < -1 || ocenaod < -1 || ocenado < -1 || iskustvood < -1 || iskustvodo < -1){
            porukaP = "(o)cena/iskustvo su brojevi > 0";
        }
        else{

            Korisnik[] majstori = mapica.keySet().toArray(new Korisnik[0]);

            for(int i=0; i<majstori.length; i++) {
                Korisnik m = majstori[i];

                //cenaod, cenado check
                if (cenaod >= 0 && cenado >=0) {
                    if (!imapreseka(cenaod, cenado, m.majstor.mincena, m.majstor.maxcena)) mapica.put(m, false);
                } else if (cenaod >= 0) {
                    if (!imapreseka(cenaod, Integer.MAX_VALUE, m.majstor.mincena, m.majstor.maxcena)) mapica.put(m, false);
                } else if (cenado >= 0) {
                    if (!imapreseka(Integer.MIN_VALUE, cenado, m.majstor.mincena, m.majstor.maxcena)) mapica.put(m, false);
                }

                //ocenaod, ocenado check
                if (ocenaod >= 0 && ocenado >=0) {
                    if (!(ocenaod <= m.majstor.prosecnaOcenaVrednost() && ocenado >= m.majstor.prosecnaOcenaVrednost())) mapica.put(m, false);
                } else if (ocenaod >= 0) {
                    if (ocenaod > m.majstor.prosecnaOcenaVrednost()) mapica.put(m, false);
                } else if (ocenado >= 0) {
                    if (ocenado < m.majstor.prosecnaOcenaVrednost()) mapica.put(m, false);
                }

                //iskustvood, iskustvodo check
                if (iskustvood >= 0 && iskustvodo >=0) {
                    if (!(iskustvood <= m.majstor.iskustvo && iskustvodo >= m.majstor.iskustvo)) mapica.put(m, false);
                } else if (iskustvood >= 0) {
                    if (iskustvood > m.majstor.iskustvo) mapica.put(m, false);
                } else if (iskustvodo >= 0) {
                    if (iskustvodo < m.majstor.iskustvo) mapica.put(m, false);
                }

                //posao check
                if(!posao.equals("")){
                    boolean found = false;
                    for(int j=0; m.majstor.tehnike!=null && j<m.majstor.tehnike.size(); j++){
                        if(posao.equals(m.majstor.tehnike.get(j))){
                            found = true;
                        }
                    }
                    if(!found) mapica.put(m, false);
                }

                //spec. tehnike check
                if(!specijalneTehnike.equals("")){
                    boolean found = false;
                    for(int j=0; m.majstor.tehnike!=null && j<m.majstor.tehnike.size(); j++){
                        if(specijalneTehnike.equals(m.majstor.tehnike.get(j))){
                            found = true;
                        }
                    }
                    if(!found) mapica.put(m, false);
                }

                //hitnost check: DA JE SLOBODAN u prvom mogucem datumu xD ++ datumod, datumdo
                //Date prvi_trazeni_datum = null; //ukoliko je hitno -> majstor treba da bude slobodan PRVOG dana kojeg je TRAZEN
                //Mihailo slobodan, Milan zauzet narednih 5 dana
                //long TRENUTNOPLUS5DANA = System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000; //finish neki
                int[] datumod = okdatum(DatumOdKS.getText().toString());
                int[] datumdo = okdatum(DatumDoKS.getText().toString());
                if(datumod.length<2 || datumod.length<2){
                    porukaP = "Datumi moraju biti oblika DD/MM/YYYY i ispravni";
                }
                else if(datumod.length==3 && datumdo.length==3){
                    if(toMilli(datumod) > toMilli(datumdo)) porukaP = "Datum od mora biti pre datuma do";
                    else {
                        if(da_li_je_majstor_slobodan(m, datumod, datumdo) < 0) mapica.put(m, false);
                    }
                }
                else if(datumod.length==3){
                    if(day_before_today(datumod)){
                        porukaP = "Oba datuma moraju biti danas ili kasnije";
                    }
                    else {
                        if(da_li_je_majstor_slobodan(m, datumod, new int[] {1, 1, 2020}) < 0) mapica.put(m, false);
                    }
                }
                else if(datumdo.length==3){
                    if(day_before_today(datumdo)){
                        porukaP = "Oba datuma moraju biti danas ili kasnije";
                    }
                    else {
                        if(da_li_je_majstor_slobodan(m, Zahtev.DMY(new Date()), datumdo) < 0) mapica.put(m, false);
                    }
                }


                if(HitnostKS.isChecked() == true){
                    //HITNO ZNACI DANAS, ne zanimaju nas datumi, Milan nije u mogucnosti
                    if(da_li_je_majstor_slobodan(m, Zahtev.DMY(new Date()), Zahtev.DMY(new Date())) < 0) mapica.put(m, false);
                }
            }
        }

        if(porukaP.equals("")) {
            tl = new TableLayout(this);

            korisnici = mapica.keySet().toArray(new Korisnik[0]);
            int redovi = 0;
            for(int i=0; i<korisnici.length; i++){
                if(mapica.get(korisnici[i]).booleanValue() == true){
                    TableRow tr= new TableRow(this);
                    Button b = new Button(this);
                    b.setText(korisnici[i].korisnickoime);
                    tr.addView(b, new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
                    tl.addView(tr, redovi++);

                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            skaci(view);
                        }
                    });

                    tr= new TableRow(this);
                    TextView e1 = new TextView(this);
                    e1.setText("");
                    tr.addView(e1, new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
                    tl.addView(tr, redovi++);
                }
            }

            majstor_view_guest();
        }
        else {
            Toast.makeText(getApplicationContext(), porukaP, Toast.LENGTH_LONG).show();
        }


    }

    public void skaci(View view){

        Button b = (Button) view; //VALDA

        Podaci.trenutniKorisnik = Podaci.podaci.get(b.getText().toString());

        Intent i = new Intent(this,KupacMajstorActivity.class);
        startActivity(i);
    }

    public static TableLayout tl = null;

    public void majstor_view_guest(){
        Intent i = new Intent(this,KupacTabelaActivity.class);
        startActivity(i);
    }

    private boolean day_before_today (int[] kraj){
        if(kraj==null || kraj.length!=3) return true;//BAGBAGBAG

        int[] danas = Zahtev.DMY(new Date());
        if(kraj[2] == danas[2]){
            if(kraj[1] == danas[1]){
                if(kraj[0] < danas[0]) return true; //LOSE
            }
            else if(kraj[1] < danas[1]) return true;
        }
        else if(kraj[2] < danas[2]) return true;

        return false; //DOBRO
    }

    private long toMilli(int[] i){ ;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(i[2], i[1], i[0], 0, 0, 0);
        return cal.getTime().getTime();
    }

    private int da_li_je_majstor_slobodan(Korisnik k, int[] datumod, int[] datumdo){
        if(k==null || k.majstor == null || k.vrsta!=Korisnik.Vrsta.MAJSTOR || datumod==null || datumod.length!=3 || datumdo==null || datumdo.length!=3) return -2;

        ArrayList<Zahtev>[] l = Podaci.zahtevi.values().toArray(new ArrayList[0]); //niz arraylista po KUPCU uredjenih

        long pocetak = toMilli(datumod);
        long kraj = toMilli(datumdo);

        while (pocetak <= kraj) {

            boolean imbusythatday = false;

            for (int i = 0; i < l.length; i++) {
                for (int j = 0; j < l[i].size(); j++) {
                    if (l[i].get(j).izvrsenZahtev != null && l[i].get(j).majstor.equals(k)) {

                        int[] start = Zahtev.DMY(l[i].get(j).izvrsenZahtev.pocetak);
                        int[] end = Zahtev.DMY(l[i].get(j).izvrsenZahtev.kraj);

                        long s = toMilli(start);
                        long e = toMilli(end);

                        if(s <= pocetak && pocetak <= e){
                            imbusythatday = true; break;
                        }

                    }
                }
                if(imbusythatday) { break; }
            }

            if(!imbusythatday) return 1; //slobodan sam!

            pocetak += 1000 * 60 * 60 * 24;//1 dan, iteriramo kroz dane
        }

        return -1;
    }
}
