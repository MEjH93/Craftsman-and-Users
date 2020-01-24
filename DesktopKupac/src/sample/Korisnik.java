package sample;

import java.util.*;

public class Korisnik {
    public String ime="";
    public String prezime="";
    public String broj="";
    public String adresa="";
    public String korisnickoime="";
    public String lozinka="";
    public enum Vrsta {KUPAC, MAJSTOR}
    Vrsta vrsta = null;
    Majstor majstor = null;

    public Korisnik(String ime, String prezime, String broj, String adresa, String korisnickoime, String lozinka, Vrsta vrsta, Majstor m){
        this.ime = ime;
        this.prezime = prezime;
        this.broj = broj;
        this.adresa = adresa;
        this.korisnickoime = korisnickoime;
        this.lozinka = lozinka;
        this.vrsta = vrsta;

        if(vrsta == Vrsta.MAJSTOR){
            majstor = m;
        }
    }

    public static class Majstor {
        public int brojOcena = 0;
        public int zbirOcena = 0;
        public List<String> komentari = new ArrayList<>();
        public List<String> tehnike = new ArrayList<>();
        public int iskustvo = 0;

        public int mincena = 100;
        public int maxcena = 500;

        public Majstor(int bO, int zO, List<String> k, List<String> t, int i, int min, int max){
            brojOcena = bO;
            zbirOcena = zO;
            komentari = k;
            tehnike = t;
            iskustvo = i;
            mincena = min;
            maxcena = max;
        }

        public String prosecnaOcena() {
            if(brojOcena != 0) {
                double z = zbirOcena*1.0;
                z = z / (brojOcena * 1.0);
                String s = String.valueOf(z);
                s = s.substring(0, (s.length() > 4 ? 4 : s.length()));
                return s;
            }
            else return "Nema ocene";
        }

        public double prosecnaOcenaVrednost(){
            if(brojOcena == 0) return -1;

            double k = zbirOcena * 1.0;
            return k / (brojOcena*1.0);
        }

        public String dodajOcenu (int ocena){
            if(ocena < 0 || ocena > 10) return "Nevalidna ocena";

            brojOcena++;
            zbirOcena+=ocena;
            return "Ocena je dodata";
        }

        public String dodajKomentar (String comm){
            komentari.add(comm);
            return "Komentar je dodat";
        }
    }
}
