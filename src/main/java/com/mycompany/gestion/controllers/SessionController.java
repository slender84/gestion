
package com.mycompany.gestion.controllers;


import com.mycompany.gestion.entities.Configuracion;
import com.mycompany.gestion.entities.Estado;
import com.mycompany.gestion.entities.EstadoMovilidad;
import com.mycompany.gestion.entities.Usuario;
import com.mycompany.gestion.model.utils.Seguridad;
import com.mycompany.gestion.model.utils.UtilidadService;


import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;



@ManagedBean
@SessionScoped
public class SessionController implements Serializable {

     private String filtroEstado="todos";
    private String filtroCursoAcademico="todos";
    private String filtroPais="todos";
    private String filtroUniversidad="todos";
    
    private String filtroContratoEstado="todos";
    private String filtroContratoCursoAcademico="todos";
    private String filtroContratoPais="todos";
    private String filtroContratoUniversidad="todos";
    
    Configuracion correoConf;
    
    
    
    public SessionController() {
       
    }
    
    
    @ManagedProperty(value="#{utilidadService}")
    private UtilidadService utilidadService;

    public UtilidadService getUtilidadService() {
        return utilidadService;
    }
    
    
    
    

    public void setUtilidadService(UtilidadService utilidadService) {
        this.utilidadService = utilidadService;
    }
    
    private ArrayList<Estado> listaEstados;
    
    
    private ArrayList<EstadoMovilidad> listaEstadosMovilidad;
    
    
    
    
     private Usuario user;
     
    
    private String zonaHoraria;
    private boolean mostrar=true;

    @PostConstruct
    public void init(){
        
        
         setListaEstados((ArrayList < Estado >)utilidadService.listaEstados());
         setListaEstadosMovilidad((ArrayList < EstadoMovilidad >)utilidadService.listaEstadosMovilidad());
         correoConf=utilidadService.getCorreoConf();
         
    }

    public ArrayList<Estado> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(ArrayList<Estado> listaEstados) {
        this.listaEstados = listaEstados;
    }

    

    public ArrayList<EstadoMovilidad> getListaEstadosMovilidad() {
        return listaEstadosMovilidad;
    }

    public void setListaEstadosMovilidad(ArrayList<EstadoMovilidad> listaEstadosMovilidad) {
        this.listaEstadosMovilidad = listaEstadosMovilidad;
    }

    
    public String getFiltroEstado() {
        return filtroEstado;
    }

    public void setFiltroEstado(String filtroEstado) {
        this.filtroEstado = filtroEstado;
    }

    public String getFiltroCursoAcademico() {
        return filtroCursoAcademico;
    }

    public void setFiltroCursoAcademico(String filtroCursoAcademico) {
        this.filtroCursoAcademico = filtroCursoAcademico;
    }

    public String getFiltroPais() {
        return filtroPais;
    }

    public void setFiltroPais(String filtroPais) {
        this.filtroPais = filtroPais;
    }

    public String getFiltroUniversidad() {
        return filtroUniversidad;
    }

    public void setFiltroUniversidad(String filtroUniversidad) {
        this.filtroUniversidad = filtroUniversidad;
    }

    public String getFiltroContratoEstado() {
        return filtroContratoEstado;
    }

    public void setFiltroContratoEstado(String filtroContratoEstado) {
        this.filtroContratoEstado = filtroContratoEstado;
    }

    public String getFiltroContratoCursoAcademico() {
        return filtroContratoCursoAcademico;
    }

    public void setFiltroContratoCursoAcademico(String filtroContratoCursoAcademico) {
        this.filtroContratoCursoAcademico = filtroContratoCursoAcademico;
    }

    public String getFiltroContratoPais() {
        return filtroContratoPais;
    }

    public void setFiltroContratoPais(String filtroContratoPais) {
        this.filtroContratoPais = filtroContratoPais;
    }

    public String getFiltroContratoUniversidad() {
        return filtroContratoUniversidad;
    }

    public void setFiltroContratoUniversidad(String filtroContratoUniversidad) {
        this.filtroContratoUniversidad = filtroContratoUniversidad;
    }
    
    
    
    
    
    
    public String getZonaHoraria() {
        return zonaHoraria;
    }

    public void setZonaHoraria(String zonaHoraria) {
        this.zonaHoraria = zonaHoraria;
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }
    
    
    public void timezoneChange(){
     
        mostrar=false;
        
           
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    
   
    
    public void limpiarFiltros(){
        
        filtroCursoAcademico="todos";
        filtroEstado="todos";
        filtroPais="todos";
        filtroUniversidad="todos";
        
        
    }
    
    
    public void limpiarFiltrosContrato(){
        
        filtroContratoCursoAcademico="todos";
        filtroContratoEstado="todos";
        filtroContratoPais="todos";
        filtroContratoUniversidad="todos";
        
    }
    
    
         public Configuracion getCorreoConf(){
        try{
        correoConf.setPassword(Seguridad.decrypt(correoConf.getPassword()));
        }catch(Exception ex){
            
        }
        return correoConf;
    }
    
    public void setCorreoConf(Configuracion correoConf){
       
            
            try{
            correoConf.setPassword(Seguridad.encrypt(correoConf.getPassword()));
            utilidadService.setCorreoConf(correoConf);
            }catch(Exception ex){
                
            } 
        
            
            
            
        
        
        
    }
        
    
    
     public void creaMensaje(String texto,FacesMessage.Severity s){
            
            FacesContext context=FacesContext.getCurrentInstance();
            FacesMessage message=new FacesMessage(texto);
            message.setSeverity(s);
            context.addMessage(null, message);
        }
    
     
     public void request(){
         
         HttpServletRequest request=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
         creaMensaje(request.getRequestURI(), FacesMessage.SEVERITY_INFO);
     }
     
     
     
      
     
    public String salir(){
            
            
            
            HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();
            return("/principal.xhtml?faces-redirect=true");
            
            
        }
    
    public void version(){
        
        String version = FacesContext.class.getPackage().getImplementationVersion();
        System.out.println(version);
        
        
    }
    
    
}
