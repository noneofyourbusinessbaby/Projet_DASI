/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.dao;

import fr.insalyon.dasi.test.tp1.metier.model.Personne;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


/**
 *
 * @author khoupeurt
 */
public class PersonneDao {
    public static void create(Personne personne) {
        JpaUtil.obtenirContextePersistance().persist(personne);
    }
    
    public static Personne findByEmail(String email) {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Personne> query = em.createQuery("SELECT P FROM Personne P WHERE P.login.email = :email", Personne.class);

        query.setParameter("email", email);

        return query.getSingleResult();
    }
    
}
