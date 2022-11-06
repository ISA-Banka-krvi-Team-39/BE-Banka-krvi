package app.user.model;

import javax.persistence.*;

public class User {
    @Id
    @SequenceGenerator(name = "personSeqGen", sequenceName = "personSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSeqGen")
    @Column(name="id", unique=true, nullable=false)
    String id;
    @Column(name="owner", unique=true, nullable=false)
    String email;
    @Column(name="owner", unique=false, nullable=false)
    String password;
}
