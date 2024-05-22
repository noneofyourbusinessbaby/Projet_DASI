package fr.insalyon.dasi.test.tp1.metier.model.personne;

import fr.insalyon.dasi.test.tp1.metier.model.Personne_;
import fr.insalyon.dasi.test.tp1.metier.model.Seance;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-05-22T11:36:50")
@StaticMetamodel(Intervenant.class)
public abstract class Intervenant_ extends Personne_ {

    public static volatile SingularAttribute<Intervenant, Integer> niveauCompetenceMax;
    public static volatile ListAttribute<Intervenant, Seance> seances;
    public static volatile SingularAttribute<Intervenant, Integer> niveauCompetenceMin;

}