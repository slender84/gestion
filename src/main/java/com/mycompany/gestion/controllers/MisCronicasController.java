
package com.mycompany.gestion.controllers;

import com.mycompany.gestion.entities.Cronica;
import com.mycompany.gestion.entities.Mensaje;
import com.mycompany.gestion.entities.Movilidad;
import com.mycompany.gestion.entities.Usuario;
import com.mycompany.gestion.exceptions.InstanceNotFoundException;
import com.mycompany.gestion.model.services.MensajeService;
import com.mycompany.gestion.model.services.MovilidadService;
import com.mycompany.gestion.model.services.UniversidadService;
import com.mycompany.gestion.model.services.UsuarioService;
import com.mycompany.gestion.model.utils.email;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.EmailException;







@ManagedBean
@ViewScoped
public class MisCronicasController implements Serializable{

    @ManagedProperty(value="#{universidadService}")
    private UniversidadService universidadService;
    
    @ManagedProperty(value ="#{mensajeService}")
    private MensajeService mensajeService;
    
    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;
    
    @ManagedProperty(value="#{sessionController}")
    private SessionController sessionController;
    
    @ManagedProperty(value = "#{movilidadService}")
    private MovilidadService movilidadService;
    
    
    @PostConstruct
    public void init(){
        
        
        usuario=sessionController.getUser();
        
        HttpServletRequest request=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if(request.getRequestURI().equals(request.getContextPath()+"/usuario/verMisCronicas.xhtml")){
            
            
            listaMisCronicas=(ArrayList<Cronica>)universidadService.listarMisCronicas(usuario);
            
        } else{
        
        
        
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("movilidad")){
        selectedMovilidad=(Movilidad)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("movilidad");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("movilidad");
        try{
               selectedMovilidad=movilidadService.buscarMovilidad(selectedMovilidad.getCodMovilidad());
           }catch(InstanceNotFoundException ex){
               try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/usuario/verMisMovilidades.xhtml");
            }catch(IOException ex2){
                    
                    }
           }
    }else{
             try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/usuario/verMisMovilidades.xhtml");
            }catch(IOException ex){
                    
                    }
    }
    }
    }
    

    public UniversidadService getUniversidadService() {
        return universidadService;
    }

    public void setUniversidadService(UniversidadService universidadService) {
        this.universidadService = universidadService;
    }

    public MensajeService getMensajeService() {
        return mensajeService;
    }

    public void setMensajeService(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    

    public MovilidadService getMovilidadService() {
        return movilidadService;
    }

    public void setMovilidadService(MovilidadService movilidadService) {
        this.movilidadService = movilidadService;
    }
    
    
    
    
    private Movilidad selectedMovilidad;
    
    private ArrayList<Cronica> listaMisCronicas;
    
    
    private Cronica selectedCronica;
    
    
    private Usuario usuario;
    private String texto;
    private String alias;

    private boolean btnActivados=true;
    private boolean panelEdicion;
    
    public Movilidad getSelectedMovilidad() {
        return selectedMovilidad;
    }

    public void setSelectedMovilidad(Movilidad selectedMovilidad) {
        this.selectedMovilidad = selectedMovilidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean isBtnActivados() {
        return btnActivados;
    }

    public boolean isPanelEdicion() {
        return panelEdicion;
    }

    public void setPanelEdicion(boolean panelEdicion) {
        this.panelEdicion = panelEdicion;
    }
    
    

    public void setBtnActivados(boolean btnActivados) {
        this.btnActivados = btnActivados;
    }

    public ArrayList<Cronica> getListaMisCronicas() {
        return listaMisCronicas;
    }

    public void setListaMisCronicas(ArrayList<Cronica> listaMisCronicas) {
        this.listaMisCronicas = listaMisCronicas;
    }

    

    public Cronica getSelectedCronica() {
        return selectedCronica;
    }

    public void setSelectedCronica(Cronica selectedCronica) {
        this.selectedCronica = selectedCronica;
    }
    
    
    
    
    
    public MisCronicasController() {
    }
    
    
    
    
    
    
    public String enviarCronica(){
        
        
        
        
        
        if (alias.equals("")){
            alias=usuario.getLogin();
        }
        
        Cronica c=new Cronica(selectedMovilidad.getUniversidad(), usuario, Calendar.getInstance().getTime(),"pendiente", alias,texto);
        try{
            
            universidadService.enviarCronica(c);
            
        }catch(RuntimeException ex){
               try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/admin/error.xhtml");
            }catch(IOException ex2){
                    
                    }
           }
        Usuario admin=null;
        try{
            
            admin=usuarioService.buscarUsuario("admin");
            
        }catch(InstanceNotFoundException ex){
            
        }
        
        Mensaje m=new Mensaje(admin, usuario, Calendar.getInstance().getTime(), "Crónica creada ", "el usuario "+usuario.getLogin()+" ha escrito una crónica",false,true,false);
        mensajeService.enviarMensaje(m);
        sessionController.creaMensaje("crónica enviada correctamente, a la espera de moderación", FacesMessage.SEVERITY_INFO);
        
        try{
            String textoMensaje="el usuario "+usuario.getLogin()+" ha escrito una crónica sobre su movilidad en "+selectedMovilidad.getUniversidad().getNombre();
            email.enviarEmailUsuario(sessionController.getCorreoConf(), "crónica creada",textoMensaje , 1);
            
        }catch(EmailException ex){
            
            sessionController.creaMensaje("Se ha producido un error enviando el correo", FacesMessage.SEVERITY_ERROR);
        }
        
        btnActivados=false;
        texto="";
        alias="";
       
        return null;
        
    }
    
    public void verCronica(){
        
        
        panelEdicion=true;
        
    }
    
    
    public String editarCronica(){
    
        selectedCronica.setFecha(Calendar.getInstance().getTime());
    try{
        universidadService.editarCronica(selectedCronica);
        
    }catch(InstanceNotFoundException ex){
        
        sessionController.creaMensaje("No existe ese comentario",FacesMessage.SEVERITY_ERROR );
        listaMisCronicas=(ArrayList<Cronica>)universidadService.listarMisCronicas(usuario);
        panelEdicion=false;
        return null;
    }
    Usuario admin=null;
    try{
            
            admin=usuarioService.buscarUsuario("admin");
            
        }catch(InstanceNotFoundException ex){
            
        }
        
        Mensaje m=new Mensaje(admin, usuario, Calendar.getInstance().getTime(), "Comentario editado ", "el usuario "+usuario.getLogin()+" ha editado un comentario",false,true,false);
        mensajeService.enviarMensaje(m);
    sessionController.creaMensaje("Cronica actualizada, pendiente de moderación", FacesMessage.SEVERITY_INFO);
    
    String textoMensaje= "el usuario "+usuario.getLogin()+" ha editado un comentario sobre su movilidad en "+selectedCronica.getUniversidad().getNombre();
    
    try{
        
        email.enviarEmailUsuario(sessionController.getCorreoConf(), "Crónica editada", textoMensaje, 1);
        
    }catch(EmailException ex){
        
        sessionController.creaMensaje("se ha producido un error enviando el correo", FacesMessage.SEVERITY_ERROR);
    }
    
    return null;
    
}
    public void cerrarEdicion(){
        
        panelEdicion=false;
        
        
    }
    
    
}
