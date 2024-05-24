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

    /**
     * Crée une personne
     * 
     * @param personne la personne à créer. Une personne peut être un élève ou un
     *                 intervenant.
     */
    public static void create(Personne personne) throws Exception {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        try {
            em.persist(personne);
        } catch (Exception e) {
            throw new Exception("Impossible de crée la personne");
        }
    }

    /**
     * Recupère une personne par son id
     * 
     * @param personneId l'id de la personne
     * @return la personne correspondante à l'id
     */
    public static Personne findById(Long personneId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Personne> query = em.createQuery("SELECT p FROM Personne p WHERE p.id = :ID", Personne.class);

        query.setParameter("ID", personneId);

        return query.getSingleResult();
    }

    /**
     * Recupère une personne par son email
     * 
     * @param email l'email de la personne
     * @return la personne correspondante à l'email
     */
    public static Personne findByEmail(String email) {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Personne> query = em.createQuery("SELECT P FROM Personne P WHERE P.login.email = :email",
                Personne.class);

        query.setParameter("email", email);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
