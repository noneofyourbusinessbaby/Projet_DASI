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
    public static void create(Matiere matiere) {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        em.persist(matiere);
    }

    public static Matiere findById(Long id) {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Matiere> query = em.createQuery("SELECT M FROM Matiere M WHERE M.id = :id", Matiere.class);

        query.setParameter("id", id);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
