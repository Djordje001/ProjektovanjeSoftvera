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
public class Autor extends AbstractDomainObject {

    private Knjiga knjiga;
    private int rb;
    private Pisac pisac;

    public Autor(Knjiga knjiga, int rb, Pisac pisac) {
        this.knjiga = knjiga;
        this.rb = rb;
        this.pisac = pisac;
    }

    public Autor() {
    }

    @Override
    public String nazivTabele() {
        return " Autor ";
    }

    @Override
    public String alijas() {
        return " a ";
    }

    @Override
    public String join() {
        return " JOIN KNJIGA KNJ ON (KNJ.KNJIGAID = AUT.KNJIGAID) "
                + "JOIN PISAC P ON (P.PISACID = AUT.PISACID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Knjiga k = new Knjiga(rs.getLong("KnjigaID"),
                    rs.getString("naziv"), rs.getString("opis"),
                    rs.getDouble("cena"), rs.getInt("izdanje"), null);

            Pisac p = new Pisac(rs.getLong("PisacID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Email"));

            Autor autor = new Autor(k, rs.getInt("rb"), p);

            lista.add(autor);
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
        return " KnjigaID = " + knjiga.getKnjigaID();
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

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Pisac getPisac() {
        return pisac;
    }

    public void setPisac(Pisac pisac) {
        this.pisac = pisac;
    }

}
