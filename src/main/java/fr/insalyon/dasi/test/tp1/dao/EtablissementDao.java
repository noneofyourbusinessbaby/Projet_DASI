/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.dao;

import fr.insalyon.dasi.test.tp1.metier.model.Etablissement;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author nhajjhassa
 */
public class EtablissementDao {
    public static void create(Etablissement etablissement) {
        JpaUtil.obtenirContextePersistance().persist(etablissement);
    }
    
    public static Etablissement findByCode(String code){
        EntityManager em = JpaUtil.obtenirContextePersistance();

        TypedQuery<Etablissement> query = em.createQuery("SELECT E FROM Etablissement E WHERE E.code = :code", Etablissement.class);

        query.setParameter("code", code);

        return query.getSingleResult();
    }
}
