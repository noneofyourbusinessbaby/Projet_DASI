/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.dao;

import fr.insalyon.dasi.test.tp1.metier.model.Person;

/**
 *
 * @author khoupeurt
 */
public class PersonDao {
    public void create(Person person) {
        JpaUtil.obtenirContextePersistance().persist(person);
    }
}
