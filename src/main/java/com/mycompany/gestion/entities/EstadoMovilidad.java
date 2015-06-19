package com.mycompany.gestion.entities;
// Generated 15-ene-2015 23:57:01 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * EstadoMovilidad generated by hbm2java
 */
@Entity
@Table(name="estado_movilidad"
    ,catalog="gestordb"
)
public class EstadoMovilidad  implements java.io.Serializable {


     private String estadoMovilidad;

    public EstadoMovilidad() {
    }

    public EstadoMovilidad(String estadoMovilidad) {
       this.estadoMovilidad = estadoMovilidad;
    }
   
     @Id 

    
    @Column(name="estadoMovilidad", unique=true, nullable=false, length=50)
    public String getEstadoMovilidad() {
        return this.estadoMovilidad;
    }
    
    public void setEstadoMovilidad(String estadoMovilidad) {
        this.estadoMovilidad = estadoMovilidad;
    }




}


