package fr.insalyon.dasi.test.tp1.metier.model.personne;

import fr.insalyon.dasi.test.tp1.metier.model.Etablissement;
import fr.insalyon.dasi.test.tp1.metier.model.Personne_;
import fr.insalyon.dasi.test.tp1.metier.model.Seance;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-05-22T11:36:50")
@StaticMetamodel(Eleve.class)
public class Eleve_ extends Personne_ {

    public static volatile SingularAttribute<Eleve, Integer> classe;
    public static volatile ListAttribute<Eleve, Seance> seances;
    public static volatile SingularAttribute<Eleve, Etablissement> etablissement;

}