/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.dao;

import fr.insalyon.dasi.test.tp1.metier.model.Matiere;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author nhajjhassa
 */
public class MatiereDao {
    public static void create(Matiere matiere) {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        em.persist(matiere);
    }
    
    public static Matiere findByName(String nom){
        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Matiere> query = em.createQuery("SELECT M FROM Matiere M WHERE M.nom = :nom", Matiere.class);

        query.setParameter("nom", nom);

        return query.getSingleResult();
    }
}
