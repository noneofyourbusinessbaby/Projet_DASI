/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.frontend;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author nhajjhassa
 */
public class AuthentifierUtilisateurAction extends Action{

    public AuthentifierUtilisateurAction(String service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        
        String mail = request.getParameter("mail");
        
        TestUtilisateur user = new TestUtilisateur("Ada", "Lovelace", "hello@gmail.com");
        
        String password = request.getParameter("password");
        
        if (mail.equals(user.getMail()) && password.equals(user.getPassword()))
        {
            request.setAttribute("user", user);
        }
    }
}
