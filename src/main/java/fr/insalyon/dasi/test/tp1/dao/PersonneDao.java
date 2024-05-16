/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.dao;

import fr.insalyon.dasi.test.tp1.metier.model.Personne;


/**
 *
 * @author khoupeurt
 */
public class PersonneDao {
    public void create(Personne personne) {
        JpaUtil.obtenirContextePersistance().persist(personne);
    }
}
