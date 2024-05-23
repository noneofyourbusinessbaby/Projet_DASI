/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.dao;

import fr.insalyon.dasi.test.tp1.metier.model.Seance;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author nhajjhassa
 */
public class SeanceDao {

    /**
     * Crée une séance
     * 
     * @param seance la séance à créer
     */
    public static void create(Seance seance) {
        JpaUtil.obtenirContextePersistance().persist(seance);
    }

    /**
     * Met à jour une séance
     * 
     * @param seance la séance à mettre à jour
     */
    public static void update(Seance seance) {
        JpaUtil.obtenirContextePersistance().merge(seance);
    }

    /**
     * Retourne la liste des séances en attente pour une matière donnée
     * 
     * @param id id de la seance
     * @return la seance correspondante à l'id
     */
    public static Seance findById(Long id) {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Seance> query = em.createQuery("SELECT P FROM Seance P WHERE P.id = :ID", Seance.class);

        query.setParameter("ID", id);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Retourne l'historique des séances pour une personne donnée
     * 
     * @param personneId id de la personne
     * @return la liste des séances terminées pour la personne donnée. Une séance
     *         est terminée si le bilan et la compréhension sont renseignés
     */
    public static List<Seance> findHistoriqueParPersonne(Long personneId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Seance> query = em.createQuery(
                "SELECT S FROM Seance S" +
                        " WHERE (S.intervenant.id = :ID OR S.eleve.id = :ID)" +
                        " AND S.fin IS NOT NULL AND S.bilan IS NOT NULL AND S.comprehension IS NOT NULL",
                Seance.class);

        query.setParameter("ID", personneId);

        List<Seance> seances = query.getResultList();

        return seances;
    }

    /**
     * Retourne la séance en cours pour une personne donnée
     * 
     * @param personneId id de la personne
     * @return la séance en cours ou null si aucune séance n'est en cours. Une
     *         séance est en cours si elle n'est pas terminée et que le bilan et la
     *         compréhension ne sont pas renseignés
     */
    public static Seance findSeanceEnCours(Long personneId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Seance> query = em.createQuery(
                "SELECT S FROM Seance S" +
                        " WHERE (S.intervenant.id = :id OR S.eleve.id = :id)" +
                        " AND S.fin IS NULL AND S.debut IS NOT NULL" +
                        " AND S.bilan IS NULL OR S.comprehension IS NULL",
                Seance.class);

        query.setParameter("id", personneId);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
