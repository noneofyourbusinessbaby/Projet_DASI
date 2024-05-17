/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.model.seance;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author nhajjhassa
 */
@Embeddable
public class Comprehension implements Serializable {
    
    @Column(nullable = false)
    private Integer note;

    public Comprehension() {
        this.note = 0;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) throws Exception {
        if (note < 0 || note > 5) {
            throw new Exception();
        }
        
        this.note = note;
    }
}
