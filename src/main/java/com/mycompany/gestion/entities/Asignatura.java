package com.mycompany.gestion.entities;
// Generated 16-dic-2014 9:35:09 by Hibernate Tools 4.3.1



import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Asignatura generated by hbm2java
 */
@Entity
@Table(name="asignatura"
    ,catalog="gestordb"
)
public class Asignatura  implements java.io.Serializable {


    private AsignaturaId id;
     private Universidad universidad;
     private String nombreAsignatura;
     private Float creditos;
     private String periodo;
     private String webAsignatura;
     private String facultad;
     private String titulacion;
     private String curso;
     private String idioma;
     private Boolean disponible;
     private Set<MiembroGrupoAsignaturaA> miembroGrupoAsignaturaAs = new HashSet<MiembroGrupoAsignaturaA>(0);
     private Set<ComentarioAsignatura> comentarioAsignaturas = new HashSet<ComentarioAsignatura>(0);
     private Set<MiembroGrupoAsignaturaB> miembroGrupoAsignaturaBs = new HashSet<MiembroGrupoAsignaturaB>(0);

    public Asignatura() {
    }

	
    public Asignatura(AsignaturaId id, Universidad universidad) {
        this.id = id;
        this.universidad = universidad;
    }
    public Asignatura(AsignaturaId id, Universidad universidad, String nombreAsignatura, Float creditos, String periodo, String webAsignatura, String facultad, String titulacion, String curso, String idioma, Boolean disponible, Set<MiembroGrupoAsignaturaA> miembroGrupoAsignaturaAs, Set<ComentarioAsignatura> comentarioAsignaturas, Set<MiembroGrupoAsignaturaB> miembroGrupoAsignaturaBs) {
       this.id = id;
       this.universidad = universidad;
       this.nombreAsignatura = nombreAsignatura;
       this.creditos = creditos;
       this.periodo = periodo;
       this.webAsignatura = webAsignatura;
       this.facultad = facultad;
       this.titulacion = titulacion;
       this.curso = curso;
       this.idioma = idioma;
       this.disponible = disponible;
       this.miembroGrupoAsignaturaAs = miembroGrupoAsignaturaAs;
       this.comentarioAsignaturas = comentarioAsignaturas;
       this.miembroGrupoAsignaturaBs = miembroGrupoAsignaturaBs;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="codAsignatura", column=@Column(name="codAsignatura", nullable=false) ), 
        @AttributeOverride(name="nombreUniversidad", column=@Column(name="nombreUniversidad", nullable=false, length=200) ) } )
    public AsignaturaId getId() {
        return this.id;
    }
    
    public void setId(AsignaturaId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="nombreUniversidad", nullable=false, insertable=false, updatable=false)
    public Universidad getUniversidad() {
        return this.universidad;
    }
    
    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    
    @Column(name="nombreAsignatura", length=100)
    public String getNombreAsignatura() {
        return this.nombreAsignatura;
    }
    
    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    
    @Column(name="creditos")
    public Float getCreditos() {
        return this.creditos;
    }
    
    public void setCreditos(Float creditos) {
        this.creditos = creditos;
    }

    
    @Column(name="periodo", length=10)
    public String getPeriodo() {
        return this.periodo;
    }
    
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

   
    @Column(name="webAsignatura", length=500)
    public String getWebAsignatura() {
        return this.webAsignatura;
    }
    
    public void setWebAsignatura(String webAsignatura) {
        this.webAsignatura = webAsignatura;
    }

    
    @Column(name="facultad", length=200)
    public String getFacultad() {
        return this.facultad;
    }
    
    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    
    @Column(name="titulacion", length=200)
    public String getTitulacion() {
        return this.titulacion;
    }
    
    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }
    
    @Column(name="curso", length=15)
    public String getCurso() {
        return this.curso;
    }
    
    public void setCurso(String curso) {
        this.curso = curso;
    }

    
    @Column(name="idioma", length=45)
    public String getIdioma() {
        return this.idioma;
    }
    
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    
    @Column(name="disponible")
    public Boolean getDisponible() {
        return this.disponible;
    }
    
    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
    

@OneToMany(fetch=FetchType.LAZY, mappedBy="asignatura",cascade = CascadeType.REMOVE)
    public Set<MiembroGrupoAsignaturaB> getMiembroGrupoAsignaturaBs() {
        return this.miembroGrupoAsignaturaBs;
    }
    
    public void setMiembroGrupoAsignaturaBs(Set<MiembroGrupoAsignaturaB> miembroGrupoAsignaturaBs) {
        this.miembroGrupoAsignaturaBs = miembroGrupoAsignaturaBs;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="asignatura",cascade = CascadeType.REMOVE)
    public Set<MiembroGrupoAsignaturaA> getMiembroGrupoAsignaturaAs() {
        return this.miembroGrupoAsignaturaAs;
    }
    
    public void setMiembroGrupoAsignaturaAs(Set<MiembroGrupoAsignaturaA> miembroGrupoAsignaturaAs) {
        this.miembroGrupoAsignaturaAs = miembroGrupoAsignaturaAs;
    }
    
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="asignatura")
    public Set<ComentarioAsignatura> getComentarioAsignaturas() {
        return this.comentarioAsignaturas;
    }
    
    public void setComentarioAsignaturas(Set<ComentarioAsignatura> comentarioAsignaturas) {
        this.comentarioAsignaturas = comentarioAsignaturas;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
       
        if (obj == null) {
            return false;
        }
        
        if (!(obj instanceof Asignatura)) {
            return false;
        }
        
        /*System.out.println(getClass().equals(obj.getClass()));
        if ( getClass().equals(obj.getClass())==false) {
            return false;
        }*/
        
        Asignatura otra=(Asignatura)obj;
        
       
   return (Objects.equals(this.getId(),otra.getId() ));
        
       
    
    
    }


}

