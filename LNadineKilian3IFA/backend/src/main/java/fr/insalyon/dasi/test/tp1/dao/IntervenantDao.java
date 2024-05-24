/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.dao;

import fr.insalyon.dasi.test.tp1.metier.model.Personne;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Eleve;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Intervenant;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author khoupeurt
 */
public class IntervenantDao {

    /**
     * Recupère un intervenant disponible pour un niveau donné
     * 
     * @param intervenant l'intervenant qui est disponible
     * @return l'intervenant disponible
     */
    public static List<Intervenant> findByNiveauEtDisponible(Eleve eleve) {

        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Intervenant> query = em.createQuery(
                "SELECT I FROM Intervenant I WHERE I.niveauCompetenceMin <= :niveau AND I.niveauCompetenceMax >= :niveau AND I.id NOT IN (SELECT S.intervenant.id FROM Seance S WHERE S.debut IS NOT NULL AND S.bilan IS NULL)",
                Intervenant.class);

        query.setParameter("niveau", eleve.getClasse());

        return query.getResultList();

    }

    public static void update(Intervenant intervenant) {
        JpaUtil.obtenirContextePersistance().merge(intervenant);
    }
}
