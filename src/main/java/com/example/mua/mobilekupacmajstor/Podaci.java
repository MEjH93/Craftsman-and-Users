package com.example.mua.mobilekupacmajstor;

import java.util.*;

public class Podaci {
    public static HashMap<String, Korisnik> podaci = new HashMap<>();

    public static HashMap<String, List<Zahtev>> zahtevi= new HashMap<>(); //po kupcu - njegovi zahtevi

    public static Korisnik trenutniKorisnik = null; //moze biti i majstor

    public static Korisnik logovaniKorisnik = null; //LOGOVANI KORISNIK TJ KUPAC
    static{
        List<String> S1 = new ArrayList<>(); S1.add("Vodoinstalacija"); S1.add("Popravka");
        List<String> K1 = new ArrayList<>(); K1.add("Milan je super i korektan");

        Korisnik.Majstor Milan = new Korisnik.Majstor(2, 13, K1, S1, 5, 200, 1000); //6.5

        List<String> S2 = new ArrayList<>(); S2.add("Vodoinstalacija"); S2.add("Tehnika");
        List<String> K2 = new ArrayList<>(); K2.add("Mihailo Zi je za preporuku svima");

        Korisnik.Majstor Mihailo = new Korisnik.Majstor(3, 29, K2, S2, 2, 500, 700); //9.nesto

        podaci.put("Mitra", new Korisnik("Mitra", "Vesovic", "0601234567", "Djuriceva 1", "Mitra", "lozinka", Korisnik.Vrsta.KUPAC, null));
        podaci.put("Drazen", new Korisnik("Drazen", "Draskovic", "067654321", "Djuke Dinica 2", "Drazen", "lozinka", Korisnik.Vrsta.KUPAC, null));
        podaci.put("Milan", new Korisnik("Milan", "Vukmirovic", "062444555", "Ruzveltova 3", "Milan", "lozinka", Korisnik.Vrsta.MAJSTOR, Milan));
        podaci.put("Mihailo", new Korisnik("Mihailo", "Zi Djordjevic", "063000111", "Toplicka 4", "Mihailo", "lozinka", Korisnik.Vrsta.MAJSTOR, Mihailo));

        long TRENUTNOMINUS15DANA = System.currentTimeMillis() - 15 * 24 * 60 * 60 * 1000; //start prvog izvrsenog zahteva
        long TRENUTNOMINUS13DANA = System.currentTimeMillis() - 13 * 24 * 60 * 60 * 1000; //finish
        long TRENUTNOMINUS10DANA = System.currentTimeMillis() - 10 * 24 * 60 * 60 * 1000; //start drugog izvrsenog zahteva
        long TRENUTNOMINUS5DANA = System.currentTimeMillis() - 5 * 24 * 60 * 60 * 1000; //finish

        long TRENUTNOPLUS5DANA = System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000; //finish neki

        Zahtev.IzvrsenZahtev i1 = new Zahtev.IzvrsenZahtev(new Date(TRENUTNOMINUS15DANA), new Date(TRENUTNOMINUS13DANA));
        Zahtev.IzvrsenZahtev i2 = new Zahtev.IzvrsenZahtev(new Date(TRENUTNOMINUS10DANA), new Date(TRENUTNOMINUS5DANA));

        Zahtev z1 = new Zahtev("Milesevska 1", "Zvezdara", 0, podaci.get("Mitra"));
        z1.izvrsenZahtev = i1;
        z1.majstor = podaci.get("Milan");
        i1.placeno = 1;
        z1.status = 2;
        //placeno, zavrseno, nacin placanja 0

        Zahtev z2 = new Zahtev("Toplicka 2", "Zvezdara", 1, podaci.get("Drazen"));
        z2.izvrsenZahtev = i2;
        z2.majstor = podaci.get("Mihailo");
        i2.placeno = 0;
        z2.status = 2;
        //NEplaceno, zavrseno, nacin placanja 1

        Zahtev z3 = new Zahtev("Ruzveltova 5", "Zvezdara", 1, podaci.get("Mitra"));
        z3.majstor = podaci.get("Mihailo");
        z3.status = 3;
        //nema placeno/neplaceno jer nije prihvacen tj. odbijen je

        Zahtev z4 = new Zahtev("Djuriceva 4", "Zvezdara", 0, podaci.get("Drazen"));
        z4.status = 0;
        z4.majstor = podaci.get("Mihailo");
        //nema placeno/neplaceno



        Zahtev z5 = new Zahtev("Ruzveltova 7", "Vracar", 0, podaci.get("Drazen"));
        Zahtev.IzvrsenZahtev i3 = new Zahtev.IzvrsenZahtev(new Date(TRENUTNOMINUS5DANA), new Date(TRENUTNOPLUS5DANA));
        z5.izvrsenZahtev = i3;
        z5.majstor = podaci.get("Milan");
        i3.placeno = 0;
        z5.status = 1;
        //PRIHVACEN I U TOKU, neplaceno (MILAN TRENUTNO RADI)

        List<Zahtev> drazen = new ArrayList<>();
        drazen.add(z2); drazen.add(z4); drazen.add(z5);

        List<Zahtev> mitra = new ArrayList<>();
        mitra.add(z1); mitra.add(z3);

        zahtevi.put("Mitra", mitra);
        zahtevi.put("Drazen", drazen);
    }
}
