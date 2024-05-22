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

    public static Eleve findById(Long id) {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Eleve> query = em.createQuery("SELECT P FROM Eleve P WHERE P.id = :id", Eleve.class);

        query.setParameter("id", id);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
