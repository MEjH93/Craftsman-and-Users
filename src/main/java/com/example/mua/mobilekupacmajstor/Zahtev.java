package com.example.mua.mobilekupacmajstor;

import java.util.*;

public class Zahtev {

    public static int poslId = 0;
    public int id = ++poslId;

    public static class IzvrsenZahtev{
        public Date pocetak;
        public Date kraj;

        public int placeno = 0; //1- kada kupac plati! xd

        public IzvrsenZahtev (Date s, Date f) { pocetak = s; kraj = f; }
    }

    public IzvrsenZahtev izvrsenZahtev = null;
    public Korisnik majstor;

    public int status=0; //0-napravljen zahtev, 1-prihvacen i u toku, 2-zavrsen uspesno, 3-odbijen(izvrsenZahtev=null)

    public String statusOpis(){
        if(status == 0) return "Kreiran";
        if(status == 1) return "Prihvacen, u toku";
        if(status == 2) return "Prihvacen, zavrsen";
        if(status == 3) return "Odbijen";
        return "Nedefinisan";
    }

    public String placanje(){
        if(nacinplacanja == 0) return "Racun";
        if(nacinplacanja == 1) return "Gotovina";
        return "Nedefinisano";
    }

    public String adresa;
    public String opstina;
    public int nacinplacanja; //0-racun, 1-gotovina
    public Korisnik kupac;

    public Zahtev(String a, String o, int n, Korisnik k){
        adresa = a; opstina = o; nacinplacanja = n; kupac = k;
    }

    //mi ne mozemo da prihvatamo/odbijemo zahtev... NEMAMO MAJSTOR DESKTOP! xd

    public void plati(){
        if(status == 1 || status == 2){
            if(izvrsenZahtev != null){
                izvrsenZahtev.placeno = 1;
            }
        }
    }

    public static int[] DMY (Date date){
        if(date == null) return null;
        int[] niz = new int[3];
        String d_first = date.toString(); // -> 01/12/2015
        String d = d_first.substring(8, 10) + "/";
        if(d_first.substring(4, 7).equals("Jan")) {
            d += "01";
        }
        else if(d_first.substring(4, 7).equals("Feb")) {
            d += "02";
        }
        else if(d_first.substring(4, 7).equals("Mar")) {
            d += "03";
        }
        else if(d_first.substring(4, 7).equals("Apr")) {
            d += "04";
        }
        else if(d_first.substring(4, 7).equals("May")) {
            d += "05";
        }
        else if(d_first.substring(4, 7).equals("Jun")) {
            d += "06";
        }
        else if(d_first.substring(4, 7).equals("Jul")) {
            d += "07";
        }
        else if(d_first.substring(4, 7).equals("Aug")) {
            d += "08";
        }
        else if(d_first.substring(4, 7).equals("Sep")) {
            d += "09";
        }
        else if(d_first.substring(4, 7).equals("Oct")) {
            d += "10";
        }
        else if(d_first.substring(4, 7).equals("Nov")) {
            d += "11";
        }
        else if(d_first.substring(4, 7).equals("Dec")) {
            d += "12";
        }
        d+="/"+d_first.substring(30);

        //01/12/2015

        try{
            niz[0] = Integer.parseInt(d.substring(0,2));
            niz[1] = Integer.parseInt(d.substring(3,5));
            niz[2] = Integer.parseInt(d.substring(6));
            return niz;
        }catch(Exception e){
            return null;
        }
    }

    public int dohvatiStatusZahteva(){
        int[] danas = DMY(new Date());
        if(status == 1 && izvrsenZahtev!=null){
            int[] kraj = DMY(izvrsenZahtev.kraj);
            if(kraj[2] == danas[2]){
                if(kraj[1] == danas[1]){
                    if(kraj[0] <= danas[0]) status = 2;
                }
                else if(kraj[1] < danas[1]) status = 2;
            }
            else if(kraj[2] < danas[2]) status = 2;
        }

        return status;
    }
}
