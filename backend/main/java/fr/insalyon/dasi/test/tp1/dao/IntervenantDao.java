/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.dao;

import fr.insalyon.dasi.test.tp1.metier.model.Personne;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Eleve;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Intervenant;
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
     */
    public static Intervenant findByNiveauEtDisponible(Eleve eleve) throws Exception {

        EntityManager em = JpaUtil.obtenirContextePersistance();

        String query_text = "SELECT I FROM Intervenant I"
                + " WHERE I.niveauCompetenceMin =< :niveau"
                + " AND I.niveauCompetenceMax =< :niveau"
                + " AND I.id NOT IN"
                + " (SELECT S.intervenant.id FROM Seance S, Intevenant I WHERE S.intervenant.id = I.id AND S.debut IS NOT NULL AND S.fin IS NULL)";

        TypedQuery<Intervenant> query = em.createQuery(query_text, Intervenant.class);

        query.setParameter("niveau", eleve.getClasse());

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
