package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import java.io.IOException;

public class Controller {
    @FXML
    TextField korisnickoime;
    @FXML
    TextField lozinka;
    @FXML
    Label poruka;

    static Stage stage;
    public static void save(Stage stageee){
        stage=stageee;
    }

    public void load(String resourceName, String title) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(resourceName));
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void login(ActionEvent actionEvent) throws IOException {
        if(korisnickoime.getText() == null || korisnickoime.getText().equals("")) {
            poruka.setText("Unesite korisnicko ime");
            return;
        }
        Podaci.trenutniKorisnik = Podaci.podaci.get(korisnickoime.getText());
        if(Podaci.trenutniKorisnik != null){
            if(lozinka.getText()!=null && lozinka.getText().equals(Podaci.trenutniKorisnik.lozinka)){

                if(Podaci.trenutniKorisnik.vrsta == Korisnik.Vrsta.KUPAC){
                    poruka.setText("");

                    Podaci.logovaniKorisnik = Podaci.podaci.get(korisnickoime.getText());

                    load("kupacPocetna.fxml", "Pocetna stranica kupca");

                }
                else poruka.setText ("Vi ste majstor, a ne kupac");
            }
            else{
                poruka.setText("Losa lozinka");
            }
        }
        else{
            poruka.setText("Los username");
        }
    }

    public void register(ActionEvent actionEvent) throws IOException{
            load("registration.fxml", "Registracija kupca");
    }

    public void searchguest(ActionEvent actionEvent) throws IOException{
            load("guestPretragaMajstora.fxml", "Pretraga majstora - gost");
    }


    @FXML
    TextField korisnickoimeR, lozinkaR, ime, prezime, broj, adresa;
    @FXML
    Label porukaR;
    public void registracija(ActionEvent actionEvent){
        if(korisnickoimeR.getText() == null || korisnickoimeR.getText().equals("")) { porukaR.setText("Unesite korisnicko ime"); }
        else if (Podaci.podaci.containsKey(korisnickoimeR.getText())) { porukaR.setText("Korisnicko ime je zauzeto"); }
        else if(lozinkaR.getText() == null || lozinkaR.getText().equals("")) { porukaR.setText("Unesite lozinku"); }
        else if(ime.getText() == null || ime.getText().equals("") || !ime.getText().matches("[a-zA-Z]+")) { porukaR.setText("Unesite ime pomocu samo slova"); }
        else if(prezime.getText() == null || prezime.getText().equals("") || !prezime.getText().matches("[a-zA-Z]+")) { porukaR.setText("Unesite prezime pomocu samo slova"); }
        else if(broj.getText() == null || broj.getText().equals("") || !broj.getText().matches("[0-9]+")) { porukaR.setText("Unesite broj pomocu samo cifara"); }
        else if(adresa.getText() == null || adresa.getText().equals("")) { porukaR.setText("Unesite adresu"); }
        else{
            porukaR.setText("Uspesna registracija");
            Podaci.podaci.put(korisnickoimeR.getText(), new Korisnik(ime.getText(), prezime.getText(), broj.getText(), adresa.getText(), korisnickoimeR.getText(), lozinkaR.getText(), Korisnik.Vrsta.KUPAC, null));
        }

    }

    public void nazad(ActionEvent actionEvent) throws IOException{
        Podaci.trenutniKorisnik = null;
        Podaci.logovaniKorisnik = null;
        load("sample.fxml", "Desktop kupac");
    }

    private int IsStringANumber(String s){
        if(s == null || s.length() == 0) { return -1; }
        try { int thenumber = Integer.valueOf(s);

            if(thenumber < 0 ) { return -2; }

            return thenumber; }
        catch (Exception e) { return -3; }
    }


    @FXML
    Button Item1, Item2;

    public void nazaddd(ActionEvent e ) throws IOException {
        load("guestPretragaMajstora.fxml", "Pretraga majstora - gost");
    }

    @FXML
    Label imeMG, prezimeMG, adresaMG, brojMG, korisnickoimeMG;

    public void ucitaj1(ActionEvent e) throws IOException{
        Podaci.trenutniKorisnik = Podaci.podaci.get("Milan");
        load("guestMajstor.fxml", "Informacije o majstoru - Gost");
    }

    public void ucitaj2(ActionEvent e) throws IOException{
        Podaci.trenutniKorisnik = Podaci.podaci.get("Mihailo");
        load("guestMajstor.fxml", "Informacije o majstoru - Gost");
    }

    @FXML
    public void initialize(){
        if(Item1!=null) Item1.setText("Milan");
        if(Item2!=null) Item2.setText("Mihailo");

        if(Podaci.trenutniKorisnik!=null) {
            if (imeMG != null) imeMG.setText("Ime: " + Podaci.trenutniKorisnik.ime);
            if (prezimeMG != null) prezimeMG.setText("Prezime: " + Podaci.trenutniKorisnik.prezime);
            if (brojMG != null) brojMG.setText("Broj: " + Podaci.trenutniKorisnik.broj);
            if (adresaMG != null) adresaMG.setText("Adresa: " + Podaci.trenutniKorisnik.adresa);
            if (korisnickoimeMG != null)
                korisnickoimeMG.setText("Korisnicko ime: " + Podaci.trenutniKorisnik.korisnickoime);
        }

        if(Podaci.logovaniKorisnik !=null) {
            if (imeM != null) imeM.setText(Podaci.logovaniKorisnik.ime);
            if (prezimeM != null) prezimeM.setText(Podaci.logovaniKorisnik.prezime);
            if (brojM != null) brojM.setText(Podaci.logovaniKorisnik.broj);
            if (adresaM != null) adresaM.setText(Podaci.logovaniKorisnik.adresa);
            if (lozinkaM != null) lozinkaM.setText(Podaci.logovaniKorisnik.lozinka);
        }

    }

    @FXML
    TextField cenaOd, cenaDo, posao, specijalneTehnike;
    @FXML
    Spinner<Integer> ocenaOd, ocenaDo, iskustvoOd, iskustvoDo;
    @FXML
    DatePicker datumOd, datumDo;
    @FXML
    CheckBox hitnost;
    @FXML
    Label porukaP;


    private boolean imapreseka(int min1, int max1, int min2, int max2){
        if(max2 < min1) return false; //min2,max2,min1,max1
        if(max1 < min2) return false; //min1,max1,min2,max2
        return true;
    }

    public void guest_pretraga(ActionEvent actionEvent) throws IOException{
        porukaP.setText("");

        boolean [] ok = new boolean[2]; ok[0] = true; ok[1] = true;
        List<Korisnik> listica = new ArrayList<>();
        listica.add(Podaci.podaci.get("Milan"));listica.add(Podaci.podaci.get("Mihailo"));

        int cenaod = IsStringANumber(cenaOd.getText());
        int cenado = IsStringANumber(cenaDo.getText());
        if(cenaod < -1 || cenado < -1){
            porukaP.setText("Cena mora sadrzati samo pozitivne brojeve");
        }
        else{
            for(int i=0; i<2; i++) {
                Korisnik m = listica.get(i);

                //cenaod, cenado check
                if (cenaOd.getText() != null && cenaDo.getText() != null && !cenaOd.getText().equals("") && !cenaDo.getText().equals("")) {
                    if (!imapreseka(cenaod, cenado, m.majstor.mincena, m.majstor.maxcena)) ok[i] = false;
                } else if (cenaOd.getText() != null && !cenaOd.getText().equals("")) {
                    if (!imapreseka(cenaod, Integer.MAX_VALUE, m.majstor.mincena, m.majstor.maxcena)) ok[i] = false;
                } else if (cenaDo.getText() != null && !cenaDo.getText().equals("")) {
                    if (!imapreseka(Integer.MIN_VALUE, cenado, m.majstor.mincena, m.majstor.maxcena)) ok[i] = false;
                }

                //ocenaod, ocenado check
                if (ocenaDo.getValue().intValue() != 0) {
                    //obe postoje!
                    if (m.majstor.prosecnaOcenaVrednost() != -1) {
                        if (m.majstor.prosecnaOcenaVrednost() < ocenaOd.getValue().intValue() || m.majstor.prosecnaOcenaVrednost() > ocenaDo.getValue().intValue()) {
                            ok[i] = false;
                        }
                    }
                } else {
                    if (m.majstor.prosecnaOcenaVrednost() != -1) {
                        if (m.majstor.prosecnaOcenaVrednost() < ocenaOd.getValue().intValue()) {
                            ok[i] = false;
                        }
                    }
                }

                //iskustvood, iskustvodo check
                if (iskustvoDo.getValue().intValue() != 0) {
                    //obe postoje!
                    if (m.majstor.iskustvo < iskustvoOd.getValue().intValue() || m.majstor.iskustvo > iskustvoDo.getValue().intValue()) {
                        ok[i] = false;
                    }
                } else {
                    if (m.majstor.iskustvo < iskustvoOd.getValue().intValue()) {
                        ok[i] = false;
                    }
                }

                //posao check
                if(posao.getText() != null && !posao.getText().equals("")){
                    boolean found = false;
                    for(int j=0; m.majstor.tehnike!=null && j<m.majstor.tehnike.size(); j++){
                        if(posao.getText().equals(m.majstor.tehnike.get(j))){
                            found = true;
                        }
                    }
                    if(found == false) ok[i] = false;
                }

                //spec. tehnike check
                if(specijalneTehnike.getText() != null && !specijalneTehnike.getText().equals("")){
                    boolean found = false;
                    for(int j=0; m.majstor.tehnike!=null && j<m.majstor.tehnike.size(); j++){
                        if(specijalneTehnike.getText().equals(m.majstor.tehnike.get(j))){
                            found = true;
                        }
                    }
                    if(found == false) ok[i] = false;
                }

                //hitnost check: DA JE SLOBODAN u prvom mogucem datumu xD ++ datumod, datumdo
                Date datumod = null, datumdo = null;
                if(datumOd.getValue() != null){
                    datumod = Date.from(Instant.from(datumOd.getValue().atStartOfDay(ZoneId.systemDefault())));
                }
                if(datumDo.getValue() != null){
                    datumdo = Date.from(Instant.from(datumDo.getValue().atStartOfDay(ZoneId.systemDefault())));
                }
                Date prvi_trazeni_datum = null; //ukoliko je hitno -> majstor treba da bude slobodan PRVOG dana kojeg je TRAZEN

                //Mihailo slobodan, Milan zauzet narednih 5 dana

                long TRENUTNOPLUS5DANA = System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000; //finish neki

                if(datumod!=null && datumdo!=null && datumod.getTime() > datumdo.getTime()){
                    porukaP.setText("Datum od mora biti pre datuma do");
                    return;
                }
                //i=0 nije slobodan narednih 5 dana
                //i=1 jeste slobodan

                if(datumod!=null && i == 0){
                    if(datumod.getTime() < TRENUTNOPLUS5DANA) {
                        //ok[0] = false;
                    }
                    if(day_before_today(datumod)){
                        porukaP.setText("Oba datuma moraju biti danas ili kasnije");
                    }
                }
                if(datumdo!=null && i == 0){
                    if(datumdo.getTime() < TRENUTNOPLUS5DANA) {
                        ok[0] = false;
                    }
                    if(day_before_today(datumdo)){
                        porukaP.setText("Oba datuma moraju biti danas ili kasnije");
                    }
                }


                if(hitnost.isSelected() == true){
                    //HITNO ZNACI DANAS, ne zanimaju nas datumi, Milan nije u mogucnosti
                    ok[0] = false;
                }
            }

            if(ok[0] == false && ok[1] == false){
                Item1.setText(""); Item2.setText("");
            }
            else if (ok[0] == false) {
                Item1.setText("Mihailo"); Item2.setText("");
            }
            else if(ok[1] == false){
                Item1.setText("Milan"); Item2.setText("");
            }
            else {
                Item1.setText("Milan"); Item2.setText("Mihailo");
            }

        }
    }


    private boolean day_before_today (Date d){
        int[] danas = Zahtev.DMY(new Date());
        int[] kraj = Zahtev.DMY(d);;
        if(kraj[2] == danas[2]){
            if(kraj[1] == danas[1]){
                if(kraj[0] < danas[0]) return true; //LOSE
            }
            else if(kraj[1] < danas[1]) return true;
        }
        else if(kraj[2] < danas[2]) return true;

        return false; //DOBRO
    }

    public void ucitaj11(ActionEvent e) throws IOException{
        Podaci.trenutniKorisnik = Podaci.podaci.get("Milan");
        load("Majstor.fxml", "Informacije o majstoru");
    }

    public void ucitaj22(ActionEvent e) throws IOException{
        Podaci.trenutniKorisnik = Podaci.podaci.get("Mihailo");
        load("Majstor.fxml", "Informacije o majstoru");
    }

    public void nazadddddd(ActionEvent e) throws IOException{
        load("kupacPocetna.fxml", "Pocetna stranica kupca");
    }

    public void komentariiocena(ActionEvent e) throws IOException{

        List<String> komentari = new ArrayList<>();
        if(Podaci.trenutniKorisnik != null) {  komentari.addAll(Podaci.trenutniKorisnik.majstor.komentari); }
        else return;

        final Label labela = new Label("Komentari");
        ObservableList<String> lista = FXCollections.<String>observableArrayList(komentari);
        ListView<String> listaview = new ListView<>(lista);
        listaview.setOrientation(Orientation.VERTICAL);
        listaview.setPrefSize(300, 100);
        listaview.setEditable(false);


        VBox v = new VBox();
        v.setSpacing(10);
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(labela,listaview);

        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(5);
        pane.setAlignment(Pos.CENTER);
        pane.addColumn(0, v);
        /*pane.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");*/

        final Label l = new Label("Prosecna ocena je: " + Podaci.trenutniKorisnik.majstor.prosecnaOcena());
        pane.addColumn(1,l);

        Button b = new Button("Nazad");
        b.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try{
                    load("Majstor.fxml", "Informacije o majstoru");
                }
                catch(Exception e){}
            }
        });

        pane.addColumn(2, b);

        Parent root = FXMLLoader.load(getClass().getResource("komentariiocena.fxml"));
        Scene scene = new Scene(pane, 1000, 700);
        stage.setScene(scene);
        stage.setTitle("Komentari i ocena");
        stage.show();
    }

    public void kreirajzahtev(ActionEvent e) {
        if(adresaZ.getText()==null || adresaZ.getText().equals("") || opstinaZ.getText()==null || opstinaZ.getText().equals("")){
            porukaZ.setText("Popunite sve podatke!");
        }
        else{
            boolean gotovina = radio1Z.isSelected();
            int i = 0;
            if(gotovina) i = 1;
            Zahtev z = new Zahtev(adresaZ.getText(), opstinaZ.getText(), i, Podaci.logovaniKorisnik); //kupac
            z.majstor = Podaci.trenutniKorisnik; //majstor

            List<Zahtev> l = Podaci.zahtevi.remove(Podaci.logovaniKorisnik.korisnickoime);
            if(l==null){
                l = new ArrayList<>();
            }
            l.add(z);
            Podaci.zahtevi.put(Podaci.logovaniKorisnik.korisnickoime, l);

            porukaZ.setText("Uspesno napravljen zahtev!");
        }
    }

    public void podelif(ActionEvent e){
        porukaZ.setText("Podeljeno na facebook!");
    }

    public void podelit(ActionEvent e){
        porukaZ.setText("Podeljeno na twitter!");
    }

    @FXML
    Spinner<Integer> ocenaZ;
    @FXML
    TextField komentarZ, adresaZ, opstinaZ;
    @FXML
    RadioButton radio1Z;
    @FXML
    Label porukaZ;
    public void posaljikomentar(ActionEvent e){
        if(Podaci.trenutniKorisnik != null && komentarZ.getText()!=null && !komentarZ.getText().equals("")) {
            Podaci.trenutniKorisnik.majstor.komentari.add(komentarZ.getText());
            porukaZ.setText("Uspesno dodat komentar");
        }
        else porukaZ.setText("Neuspesno dodat komentar - nema texta");
    }

    public void posaljiocenu(ActionEvent e){
        if(Podaci.trenutniKorisnik != null && ocenaZ.getValue().intValue() !=0) {
            Podaci.trenutniKorisnik.majstor.brojOcena++;
            Podaci.trenutniKorisnik.majstor.zbirOcena+=ocenaZ.getValue().intValue();
            porukaZ.setText("Uspesno dodata ocena");
        }
        else porukaZ.setText("Neuspesno dodata ocena - mora biti > 0");
    }

    @FXML
    TextField imeM, prezimeM, brojM, adresaM, lozinkaM;
    @FXML
    Label porukaM;

    public void licnipodaci(ActionEvent e) throws IOException{
        load("licnipodaci.fxml", "Licni podaci");
    }

    public void sacuvaj(ActionEvent e){
        if(Podaci.logovaniKorisnik==null || imeM.getText() == null || imeM.getText().equals("") || prezimeM.getText() == null || prezimeM.getText().equals("")
                || brojM.getText() == null || brojM.getText().equals("") || adresaM.getText() == null || adresaM.getText().equals("")
                || !brojM.getText().matches("[0-9]+") || !imeM.getText().matches("[a-zA-Z]+") || !prezimeM.getText().matches("[a-zA-Z]+")
                || lozinkaM.getText() == null || lozinkaM.getText().equals("")
        ){
            porukaM.setText("Neadekvatni podaci!");
        }
        else{
            Podaci.logovaniKorisnik.ime = imeM.getText();
            Podaci.logovaniKorisnik.prezime = prezimeM.getText();
            Podaci.logovaniKorisnik.broj = brojM.getText();
            Podaci.logovaniKorisnik.adresa = adresaM.getText();
            Podaci.logovaniKorisnik.lozinka = lozinkaM.getText();

            porukaM.setText("Podaci su promenjeni");
        }
    }

    public void mojimajstori(ActionEvent e) throws IOException{
        if(Podaci.logovaniKorisnik!=null){
            List<Zahtev> zahtevi = Podaci.zahtevi.get(Podaci.logovaniKorisnik.korisnickoime);

            List<String> listamajstora = new ArrayList<>();
            for(int i=0; zahtevi!=null && i<zahtevi.size(); i++){
                if(zahtevi.get(i).majstor != null){
                    listamajstora.add(zahtevi.get(i).majstor.korisnickoime);
                }
            }

            final Label labela = new Label("Korisnicka imena mojih majstora po zahtevima");
            ObservableList<String> lista = FXCollections.<String>observableArrayList(listamajstora);
            ListView<String> listaview = new ListView<>(lista);
            listaview.setOrientation(Orientation.VERTICAL);
            listaview.setPrefSize(300, 100);
            listaview.setEditable(false);


            VBox v = new VBox();
            v.setSpacing(10);
            v.setAlignment(Pos.CENTER);
            v.getChildren().addAll(labela,listaview);

            GridPane pane = new GridPane();
            pane.setHgap(10);
            pane.setVgap(5);
            pane.setAlignment(Pos.CENTER);
            pane.addColumn(0, v);


            Button b = new Button("Nazad");
            b.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    try{
                        load("kupacPocetna.fxml", "Pocetna stranica kupca");
                    }
                    catch(Exception e){}
                }
            });

            pane.addColumn(2, b);

            Parent root = FXMLLoader.load(getClass().getResource("komentariiocena.fxml"));
            Scene scene = new Scene(pane, 1000, 700);
            stage.setScene(scene);
            stage.setTitle("Moji majstori");
            stage.show();
        }
    }

    public void mojizahtevi(ActionEvent e) throws IOException{
        if(Podaci.logovaniKorisnik!=null){
            List<Zahtev> zahtevi = Podaci.zahtevi.get(Podaci.logovaniKorisnik.korisnickoime);

            List<String> listaaa = new ArrayList<>();
            for(int i=0; zahtevi!=null && i<zahtevi.size(); i++){
                String majstor = (zahtevi.get(i).majstor == null) ? "Nema" : zahtevi.get(i).majstor.korisnickoime;
                listaaa.add("Id: " + (i+1) + ", Majstor: " + majstor + ", Status: " + zahtevi.get(i).statusOpis()
                + ", Adresa: " + zahtevi.get(i).adresa + ", Opstina: " + zahtevi.get(i).opstina + ", Placenje: " + zahtevi.get(i).placanje());
            }

            final Label labela = new Label("Korisnicka imena mojih majstora po zahtevima");
            ObservableList<String> lista = FXCollections.<String>observableArrayList(listaaa);
            ListView<String> listaview = new ListView<>(lista);
            listaview.setOrientation(Orientation.VERTICAL);
            listaview.setPrefSize(800, 100);
            listaview.setEditable(false);


            VBox v = new VBox();
            v.setSpacing(10);
            v.setAlignment(Pos.CENTER);
            v.getChildren().addAll(labela,listaview);

            GridPane pane = new GridPane();
            pane.setHgap(10);
            pane.setVgap(5);
            pane.setAlignment(Pos.CENTER);
            pane.addColumn(0, v);


            Button b = new Button("Nazad");
            b.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    try{
                        load("kupacPocetna.fxml", "Pocetna stranica kupca");
                    }
                    catch(Exception e){}
                }
            });

            pane.addColumn(2, b);

            Parent root = FXMLLoader.load(getClass().getResource("komentariiocena.fxml"));
            Scene scene = new Scene(pane, 1000, 700);
            stage.setScene(scene);
            stage.setTitle("Moji zahtevi");
            stage.show();
        }
    }
}
