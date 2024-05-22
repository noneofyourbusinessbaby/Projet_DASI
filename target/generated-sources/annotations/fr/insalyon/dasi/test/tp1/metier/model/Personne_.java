package fr.insalyon.dasi.test.tp1.metier.model;

import fr.insalyon.dasi.test.tp1.metier.model.Contact;
import fr.insalyon.dasi.test.tp1.metier.model.Login;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-05-22T11:36:50")
@StaticMetamodel(Personne.class)
public abstract class Personne_ { 

    public static volatile SingularAttribute<Personne, Contact> contact;
    public static volatile SingularAttribute<Personne, Long> id;
    public static volatile SingularAttribute<Personne, Login> login;
    public static volatile SingularAttribute<Personne, String> nom;
    public static volatile SingularAttribute<Personne, String> prenom;

}