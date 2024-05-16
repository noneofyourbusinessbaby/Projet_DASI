/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.test.tp1.metier.service;

import fr.insalyon.dasi.test.tp1.dao.EtablissementDao;
import fr.insalyon.dasi.test.tp1.dao.JpaUtil;
import fr.insalyon.dasi.test.tp1.metier.model.Etablissement;
import fr.insalyon.dasi.test.tp1.metier.utils.EducNetApi;
import java.util.List;

/**
 *
 * @author nhajjhassa
 */
public class EtablissementService {
    public Etablissement enregistrer(String code) throws Exception {
            
        EducNetApi api = new EducNetApi();

        List<String> result = api.getInformationCollege(code);

        if(result == null) {
            throw new Exception();
        }
        
        String nom = result.get(1);

        // String secteur = result.get(2);
        // String codeCommune = result.get(3);
        // String nomCommune = result.get(4);
        // String codeDepartement = result.get(5);
        // String nomDepartement = result.get(6);
        // String academie = result.get(7);

        String ips = result.get(8);

        Etablissement etablissement = new Etablissement(nom, ips, code, "adresse");
                
        try {
            
            JpaUtil.creerContextePersistance();    // String uai = result.get(0);

            JpaUtil.ouvrirTransaction();

            EtablissementDao etablissementDao = new EtablissementDao();

            etablissementDao.create(etablissement);
                        
            JpaUtil.validerTransaction();
        }
        catch(Exception e) {
            JpaUtil.annulerTransaction();
            
            throw e;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
       
        return etablissement;        
    }
    
    public Etablissement findEtablissementByCode(String code){
        
    }
}