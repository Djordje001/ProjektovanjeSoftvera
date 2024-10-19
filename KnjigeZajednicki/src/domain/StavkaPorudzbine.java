/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class StavkaPorudzbine extends AbstractDomainObject {

    private Porudzbina porudzbina;
    private int rb;
    private int kolicina;
    private double cena;
    private Knjiga knjiga;

    public StavkaPorudzbine(Porudzbina porudzbina, int rb, int kolicina, double cena, Knjiga knjiga) {
        this.porudzbina = porudzbina;
        this.rb = rb;
        this.kolicina = kolicina;
        this.cena = cena;
        this.knjiga = knjiga;
    }

    public StavkaPorudzbine() {
    }

    @Override
    public String nazivTabele() {
        return " StavkaPorudzbine ";
    }

    @Override
    public String alijas() {
        return " sp ";
    }

    @Override
    public String join() {
        return " JOIN KNJIGA KNJ ON (KNJ.KNJIGAID = SP.KNJIGAID) "
                + "JOIN PORUDZBINA P ON (P.PORUDZBINAID = SP.PORUDZBINAID) "
                + "JOIN KUPAC K ON (K.KUPACID = P.KUPACID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = P.ADMINISTRATORID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("a.Ime"), rs.getString("a.Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Kupac k = new Kupac(rs.getLong("KupacID"),
                    rs.getString("k.Ime"), rs.getString("k.Prezime"),
                    rs.getString("email"), rs.getString("tipKupca"));

            Porudzbina p = new Porudzbina(rs.getLong("porudzbinaID"),
                    rs.getTimestamp("datumVreme"), rs.getDate("datumIsporuke"),
                    rs.getString("grad"), rs.getString("adresa"),
                    rs.getDouble("cena"), rs.getDouble("popust"),
                    rs.getDouble("konacnaCena"), k, a, null);
            
            Knjiga knj = new Knjiga(rs.getLong("KnjigaID"),
                    rs.getString("naziv"), rs.getString("opis"),
                    rs.getDouble("cena"), rs.getInt("izdanje"), null);

            StavkaPorudzbine sp = new StavkaPorudzbine(p, rs.getInt("rb"), 
                    rs.getInt("kolicina"), rs.getDouble("cena"), knj);
            
            lista.add(sp);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (porudzbinaID, rb, kolicina, cena, knjigaID) ";
    }

    @Override
    public String uslov() {
        return " porudzbinaID = " + porudzbina.getPorudzbinaID();
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + porudzbina.getPorudzbinaID() + ", " + rb + ", "
                + " " + kolicina + ", " + cena + ", " + knjiga.getKnjigaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return " WHERE P.PORUDZBINAID = " + porudzbina.getPorudzbinaID();
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

}
