/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.frontend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nhajjhassa
 */
public abstract class Serialisation {
    
    public abstract void appliquer(HttpServletRequest request, HttpServletResponse response);
}
