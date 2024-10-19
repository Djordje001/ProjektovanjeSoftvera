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
public class Knjiga extends AbstractDomainObject {

    private Long knjigaID;
    private String naziv;
    private String opis;
    private double cena;
    private int izdanje;
    private ArrayList<Pisac> autori;

    @Override
    public String toString() {
        return naziv + " (Cena: " + cena + ", Izdanje: " + izdanje + ")";
    }

    public Knjiga(Long knjigaID, String naziv, String opis, double cena, int izdanje, ArrayList<Pisac> autori) {
        this.knjigaID = knjigaID;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.izdanje = izdanje;
        this.autori = autori;
    }

    public Knjiga() {
    }

    @Override
    public String nazivTabele() {
        return " Knjiga ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Knjiga k = new Knjiga(rs.getLong("KnjigaID"),
                    rs.getString("naziv"), rs.getString("opis"),
                    rs.getDouble("cena"), rs.getInt("izdanje"), null);

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String uslov() {
        return " KnjigaID = " + knjigaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getKnjigaID() {
        return knjigaID;
    }

    public void setKnjigaID(Long knjigaID) {
        this.knjigaID = knjigaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getIzdanje() {
        return izdanje;
    }

    public void setIzdanje(int izdanje) {
        this.izdanje = izdanje;
    }

    public ArrayList<Pisac> getAutori() {
        return autori;
    }

    public void setAutori(ArrayList<Pisac> autori) {
        this.autori = autori;
    }

}
