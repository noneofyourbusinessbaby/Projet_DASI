package fr.insalyon.dasi.test.tp1.metier.model;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-05-17T10:41:55")
@StaticMetamodel(Seance.class)
public abstract class Seance_ { 

    public static volatile SingularAttribute<Seance, Date> debut;
    public static volatile SingularAttribute<Seance, String> lien;
    public static volatile SingularAttribute<Seance, String> description;
    public static volatile SingularAttribute<Seance, Date> fin;
    public static volatile SingularAttribute<Seance, Long> id;

}