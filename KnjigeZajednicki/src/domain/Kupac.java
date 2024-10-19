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
public class Kupac extends AbstractDomainObject {
    
    private Long kupacID;
    private String ime;
    private String prezime;
    private String email;
    private String tipKupca;

    @Override
    public String toString() {
        return ime + " " + prezime + " (Tip: " + tipKupca + ")";
    }

    public Kupac(Long kupacID, String ime, String prezime, String email, String tipKupca) {
        this.kupacID = kupacID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.tipKupca = tipKupca;
    }

    public Kupac() {
    }
    
    @Override
    public String nazivTabele() {
        return " Kupac ";
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
            Kupac k = new Kupac(rs.getLong("KupacID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("email"), rs.getString("tipKupca"));

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, email, tipKupca) ";
    }

    @Override
    public String uslov() {
        return " KupacID = " + kupacID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + email + "', '" + tipKupca + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', "
                + "email = '" + email + "', tipKupca = '" + tipKupca + "' ";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getKupacID() {
        return kupacID;
    }

    public void setKupacID(Long kupacID) {
        this.kupacID = kupacID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipKupca() {
        return tipKupca;
    }

    public void setTipKupca(String tipKupca) {
        this.tipKupca = tipKupca;
    }
    
}
