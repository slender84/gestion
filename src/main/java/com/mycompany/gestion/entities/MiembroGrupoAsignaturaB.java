package com.mycompany.gestion.entities;
// Generated 15-ene-2015 23:57:01 by Hibernate Tools 4.3.1


import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * MiembroGrupoAsignaturaB generated by hbm2java
 */
@Entity
@Table(name="miembro_grupo_asignatura_b"
    ,catalog="gestordb"
)
public class MiembroGrupoAsignaturaB  implements java.io.Serializable {


     private Integer idmiembroGrupoAsignaturaB;
     private Asignatura asignatura;
     private Equivalencia equivalencia;

    public MiembroGrupoAsignaturaB() {
    }

    public MiembroGrupoAsignaturaB(Asignatura asignatura, Equivalencia equivalencia) {
       this.asignatura = asignatura;
       this.equivalencia = equivalencia;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idmiembro_grupo_asignatura_b", unique=true, nullable=false)
    public Integer getIdmiembroGrupoAsignaturaB() {
        return this.idmiembroGrupoAsignaturaB;
    }
    
    public void setIdmiembroGrupoAsignaturaB(Integer idmiembroGrupoAsignaturaB) {
        this.idmiembroGrupoAsignaturaB = idmiembroGrupoAsignaturaB;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( { 
        @JoinColumn(name="codAsignatura", referencedColumnName="codAsignatura"), 
        @JoinColumn(name="nombreUniversidad", referencedColumnName="nombreUniversidad") } )
    public Asignatura getAsignatura() {
        return this.asignatura;
    }
    
    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idEquivalencia")
    public Equivalencia getEquivalencia() {
        return this.equivalencia;
    }
    
    public void setEquivalencia(Equivalencia equivalencia) {
        this.equivalencia = equivalencia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.asignatura);
        return hash;
    }

     @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof MiembroGrupoAsignaturaB)) {
            return false;
        }
        MiembroGrupoAsignaturaB other = (MiembroGrupoAsignaturaB) obj;
      return(Objects.equals(this.getAsignatura(), other.getAsignatura()));




}

}