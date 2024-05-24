/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.frontend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nhajjhassa
 */
public class ProfilUtilisateurSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) {
        
        TestUtilisateur user = (TestUtilisateur)request.getAttribute("user");
        
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id" , user.getId());
        jsonObject.addProperty("prenom" , user.getPrenom());
        jsonObject.addProperty("nom" , user.getNom());
        jsonObject.addProperty("mail" , user.getMail());
        
        try (PrintWriter out = response.getWriter()) {
            out.println(gson.toJson(jsonObject));

            out.close()  ;
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
