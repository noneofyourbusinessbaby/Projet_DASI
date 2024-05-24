/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.frontend;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author nhajjhassa
 */
public abstract class Action {
    
    String service;
    
    public Action(String service){
        this.service = service;
    }
    
    public abstract void execute(HttpServletRequest request);    
}
