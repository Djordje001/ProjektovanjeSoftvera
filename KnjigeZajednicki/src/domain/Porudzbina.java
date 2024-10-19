/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class Porudzbina extends AbstractDomainObject {

    private Long porudzbinaID;
    private Date datumVreme;
    private Date datumIsporuke;
    private String grad;
    private String adresa;
    private double cena;
    private double popust;
    private double konacnaCena;
    private Kupac kupac;
    private Administrator administrator;
    private ArrayList<StavkaPorudzbine> stavkePorudzbine;

    public Porudzbina(Long porudzbinaID, Date datumVreme, Date datumIsporuke, String grad, String adresa, double cena, double popust, double konacnaCena, Kupac kupac, Administrator administrator, ArrayList<StavkaPorudzbine> stavkePorudzbine) {
        this.porudzbinaID = porudzbinaID;
        this.datumVreme = datumVreme;
        this.datumIsporuke = datumIsporuke;
        this.grad = grad;
        this.adresa = adresa;
        this.cena = cena;
        this.popust = popust;
        this.konacnaCena = konacnaCena;
        this.kupac = kupac;
        this.administrator = administrator;
        this.stavkePorudzbine = stavkePorudzbine;
    }

    public Porudzbina() {
    }

    @Override
    public String nazivTabele() {
        return " Porudzbina ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return " JOIN KUPAC K ON (K.KUPACID = P.KUPACID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = P.ADMINISTRATORID) ";
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

            lista.add(p);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumVreme, datumIsporuke, grad, adresa, cena, popust, konacnaCena, "
                + "kupacID, administratorID) ";
    }

    @Override
    public String uslov() {
        return " porudzbinaID = " + porudzbinaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Timestamp(datumVreme.getTime()) + "', "
                + "'" + new java.sql.Date(datumIsporuke.getTime()) + "', "
                + "'" + grad + "', '" + adresa + "', " + cena + ", " + popust + ", "
                + konacnaCena + ", " + kupac.getKupacID() + ", " + administrator.getAdministratorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumIsporuke = '" + new java.sql.Date(datumIsporuke.getTime()) + "', "
                + "grad = '" + grad + "', "
                + "adresa = '" + adresa + "', cena = " + cena + ", "
                + "konacnaCena = " + konacnaCena;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getPorudzbinaID() {
        return porudzbinaID;
    }

    public void setPorudzbinaID(Long porudzbinaID) {
        this.porudzbinaID = porudzbinaID;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public Date getDatumIsporuke() {
        return datumIsporuke;
    }

    public void setDatumIsporuke(Date datumIsporuke) {
        this.datumIsporuke = datumIsporuke;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public double getKonacnaCena() {
        return konacnaCena;
    }

    public void setKonacnaCena(double konacnaCena) {
        this.konacnaCena = konacnaCena;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public ArrayList<StavkaPorudzbine> getStavkePorudzbine() {
        return stavkePorudzbine;
    }

    public void setStavkePorudzbine(ArrayList<StavkaPorudzbine> stavkePorudzbine) {
        this.stavkePorudzbine = stavkePorudzbine;
    }

}
