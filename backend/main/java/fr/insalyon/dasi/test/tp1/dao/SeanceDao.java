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
    public static void create(Seance seance) {
        JpaUtil.obtenirContextePersistance().persist(seance);
    }

    public static void update(Seance seance) {
        JpaUtil.obtenirContextePersistance().merge(seance);
    }

    public static Seance findById(Long id) {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Seance> query = em.createQuery("SELECT P FROM Seance P WHERE P.id = :id", Seance.class);

        query.setParameter("id", id);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Seance> findHistoriqueParPersonne(Long personneId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Seance> query = em.createQuery(
                "SELECT S FROM Seance S WHERE (S.intervenant.id = :id OR S.eleve.id = :id) AND S.fin IS NOT NULL",
                Seance.class);

        query.setParameter("id", personneId);

        List<Seance> seances = query.getResultList();

        return seances;
    }

    public static Seance findSeanceEnCours(Long personneId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Seance> query = em.createQuery(
                "SELECT S FROM Seance S WHERE (S.intervenant.id = :id OR S.eleve.id = :id) AND S.fin IS NULL AND S.debut IS NOT NULL",
                Seance.class);
        // On ne peut pas avoir plus qu'une séance en cours

        query.setParameter("id", personneId);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
