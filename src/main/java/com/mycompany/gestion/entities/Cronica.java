package com.mycompany.gestion.entities;
// Generated 15-ene-2015 23:57:01 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Cronica generated by hbm2java
 */
@Entity
@Table(name="cronica"
    ,catalog="gestordb"
)
public class Cronica  implements java.io.Serializable {


     private Integer idcronica;
     private Universidad universidad;
     private Usuario usuario;
     private Date fecha;
     private String estado;
     private String alias;
     private String texto;

    public Cronica() {
    }

	
    public Cronica(Universidad universidad, Date fecha, String estado, String alias,String texto) {
        this.universidad = universidad;
        this.fecha = fecha;
        this.estado = estado;
        this.alias = alias;
        this.texto=texto;
    }
    public Cronica(Universidad universidad, Usuario usuario, Date fecha, String estado, String alias,String texto) {
       this.universidad = universidad;
       this.usuario = usuario;
       this.fecha = fecha;
       this.estado = estado;
       this.alias = alias;
       this.texto=texto;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idcronica", unique=true, nullable=false)
    public Integer getIdcronica() {
        return this.idcronica;
    }
    
    public void setIdcronica(Integer idcronica) {
        this.idcronica = idcronica;
    }

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="universidad", nullable=false)
    public Universidad getUniversidad() {
        return this.universidad;
    }
    
    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="login")
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha", nullable=false, length=19)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    @Column(name="estado", nullable=false, length=10)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    @Column(name="alias", nullable=false, length=45)
    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    @Column(name="texto", nullable=false)
    public String getTexto() {
        return this.texto;
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.idcronica);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cronica other = (Cronica) obj;
        if (!Objects.equals(this.idcronica, other.idcronica)) {
            return false;
        }
        return true;
    }




}


