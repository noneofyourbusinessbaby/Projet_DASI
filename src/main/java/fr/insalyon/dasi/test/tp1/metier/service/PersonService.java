/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.service;

import com.google.maps.model.LatLng;
import fr.insalyon.dasi.test.tp1.dao.PersonDao;
import fr.insalyon.dasi.test.tp1.dao.JpaUtil;
import fr.insalyon.dasi.test.tp1.metier.model.Person;
import fr.insalyon.dasi.test.tp1.metier.utils.GeoNetApi;
import fr.insalyon.dasi.test.tp1.metier.utils.Messaging;

/**
 *
 * @author khoupeurt
 */
public class PersonService {
    public void inscrire(String nom, String prenom, String password, String email, String address) {
        
        Person person = new Person(nom, prenom, password, email, address);
                
        LatLng coordinates = GeoNetApi.getLatLng(person.getAddress());
        
        person.setLongitudeLatitude(coordinates.lat, coordinates.lng);
                
        try {
            JpaUtil.creerContextePersistance();
            
            PersonDao personDao = new PersonDao();
            
            personDao.create(person);
            
            Messaging.envoyerMail("kilian.houpeurt@insa-lyon.fr", person.getMail(), "obj", "corps");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            JpaUtil.fermerContextePersistance();
        
        }
    }
}
