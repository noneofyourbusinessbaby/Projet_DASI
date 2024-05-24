/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.dao;

import fr.insalyon.dasi.test.tp1.metier.model.Etablissement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author nhajjhassa
 */
public class EtablissementDao {

    /**
     * Crée un établissement
     * 
     * @param etablissement l'établissement à créer
     */
    public static void create(Etablissement etablissement) {
        JpaUtil.obtenirContextePersistance().persist(etablissement);
    }

    /**
     * Récupère un établissement par son code
     * 
     * @param code le code de l'établissement
     * @return l'établissement correspondant au code
     */
    public static Etablissement findByCode(String code) {
        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Etablissement> query = em.createQuery("SELECT E FROM Etablissement E WHERE E.code = :code",
                Etablissement.class);

        query.setParameter("code", code);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static List<Etablissement> findAll(){
        
        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Etablissement> query = em.createQuery("SELECT E FROM Etablissement E",
                Etablissement.class);

        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public static void update(Etablissement etablissement) {
        JpaUtil.obtenirContextePersistance().merge(etablissement);
    }
}
