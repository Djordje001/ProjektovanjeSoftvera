/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import java.util.ArrayList;
import so.administrator.SOAddAdministrator;
import so.administrator.SODeleteAdministrator;
import so.administrator.SOUpdateAdministrator;
import so.administrator.SOGetAllAdministrator;
import so.login.SOLogin;

/**
 *
 * @author PC
 */
public class ServerController {

    private static ServerController instance;

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }
    
    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getUlogovani();
    }

    public void addAdministrator(Administrator administrator) throws Exception {
        (new SOAddAdministrator()).templateExecute(administrator);
    }

    public void deleteAdministrator(Administrator administrator) throws Exception {
        (new SODeleteAdministrator()).templateExecute(administrator);
    }

    public void updateAdministrator(Administrator administrator) throws Exception {
        (new SOUpdateAdministrator()).templateExecute(administrator);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        SOGetAllAdministrator so = new SOGetAllAdministrator();
        so.templateExecute(new Administrator());
        return so.getLista();
    }

}
