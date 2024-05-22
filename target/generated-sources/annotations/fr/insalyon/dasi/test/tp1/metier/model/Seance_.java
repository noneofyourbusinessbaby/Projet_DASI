package fr.insalyon.dasi.test.tp1.metier.model;

import fr.insalyon.dasi.test.tp1.metier.model.Matiere;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Eleve;
import fr.insalyon.dasi.test.tp1.metier.model.personne.Intervenant;
import fr.insalyon.dasi.test.tp1.metier.model.seance.Bilan;
import fr.insalyon.dasi.test.tp1.metier.model.seance.Comprehension;
import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-05-22T11:36:50")
@StaticMetamodel(Seance.class)
public class Seance_ { 

    public static volatile SingularAttribute<Seance, Date> debut;
    public static volatile SingularAttribute<Seance, String> lien;
    public static volatile SingularAttribute<Seance, Bilan> bilan;
    public static volatile SingularAttribute<Seance, String> description;
    public static volatile SingularAttribute<Seance, Date> fin;
    public static volatile SingularAttribute<Seance, Long> id;
    public static volatile SingularAttribute<Seance, Eleve> eleve;
    public static volatile SingularAttribute<Seance, Comprehension> comprehension;
    public static volatile SingularAttribute<Seance, Intervenant> intervenant;
    public static volatile SingularAttribute<Seance, Matiere> matiere;

}