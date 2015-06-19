package com.mycompany.gestion.entities;
// Generated 15-ene-2015 23:57:01 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Cursoacademico generated by hbm2java
 */
@Entity
@Table(name="cursoacademico"
    ,catalog="gestordb"
)
public class Cursoacademico  implements java.io.Serializable {


     private String cursoAcademico;
     private Set<Movilidad> movilidads = new HashSet<Movilidad>(0);

    public Cursoacademico() {
    }

	
    public Cursoacademico(String cursoAcademico) {
        this.cursoAcademico = cursoAcademico;
    }
    public Cursoacademico(String cursoAcademico, Set<Movilidad> movilidads) {
       this.cursoAcademico = cursoAcademico;
       this.movilidads = movilidads;
    }
   
     @Id 

    
    @Column(name="cursoAcademico", unique=true, nullable=false, length=10)
    public String getCursoAcademico() {
        return this.cursoAcademico;
    }
    
    public void setCursoAcademico(String cursoAcademico) {
        this.cursoAcademico = cursoAcademico;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="cursoacademico")
    public Set<Movilidad> getMovilidads() {
        return this.movilidads;
    }
    
    public void setMovilidads(Set<Movilidad> movilidads) {
        this.movilidads = movilidads;
    }




}


