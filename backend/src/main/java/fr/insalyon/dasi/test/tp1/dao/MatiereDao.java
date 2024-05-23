/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.dao;

import fr.insalyon.dasi.test.tp1.metier.model.Matiere;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author nhajjhassa
 */
public class MatiereDao {

    /**
     * Crée une matière
     * 
     * @param matiere la matière à créer
     */
    public static void create(Matiere matiere) {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        em.persist(matiere);
    }

    /**
     * Recupère une matière par son id
     * 
     * @param id      l'id de la matière
     * @param matiere la matière correspondante à l'id
     */
    public static Matiere findById(Long id) {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Matiere> query = em.createQuery("SELECT M FROM Matiere M WHERE M.id = :ID", Matiere.class);

        query.setParameter("ID", id);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
