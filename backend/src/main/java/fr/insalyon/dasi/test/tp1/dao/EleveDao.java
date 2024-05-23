/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.dao;

import fr.insalyon.dasi.test.tp1.metier.model.personne.Eleve;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author khoupeurt
 */
public class EleveDao {

    /**
     * Récupère un élève par son id
     * 
     * @param id l'id de l'élève
     * @return l'élève correspondant à l'id
     */
    public static Eleve findById(Long id) {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Eleve> query = em.createQuery("SELECT P FROM Eleve P WHERE P.id = :ID", Eleve.class);

        query.setParameter("ID", id);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
