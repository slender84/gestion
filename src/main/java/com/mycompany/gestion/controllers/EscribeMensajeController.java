package com.mycompany.gestion.controllers;

import com.mycompany.gestion.entities.Mensaje;
import com.mycompany.gestion.entities.Usuario;
import com.mycompany.gestion.exceptions.InstanceNotFoundException;
import com.mycompany.gestion.model.services.MensajeService;
import com.mycompany.gestion.model.services.UsuarioService;
import com.mycompany.gestion.model.utils.email;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.mail.EmailException;



@ManagedBean
@ViewScoped
public class EscribeMensajeController implements Serializable{

    @ManagedProperty(value="#{sessionController}")
    private SessionController sessionController;
    
    @ManagedProperty(value="#{mensajeService}")
    private MensajeService mensajeService;
    @ManagedProperty(value="#{usuarioService}")
    private UsuarioService usuarioService;
    
    private Usuario user;
    
    private String tema;
    private String texto;
    
    private boolean activaTexto;
    
    
    
    private ArrayList<Usuario> selectedUsuarios;
    
    
    public EscribeMensajeController() {
    }
    
    @PostConstruct
    public void init(){
        
        user=sessionController.getUser();
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
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
    
    

    public boolean isActivaTexto() {
        return activaTexto;
    }

    public void setActivaTexto(boolean activaTexto) {
        this.activaTexto = activaTexto;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    
    
    
    
    

    public ArrayList<Usuario> getSelectedUsuarios() {
        return selectedUsuarios;
    }

    public void setSelectedUsuarios(ArrayList<Usuario> selectedUsuarios) {
        this.selectedUsuarios = selectedUsuarios;
    }
    
    
    
    
    
    public String activarTexto(){
        
        if(selectedUsuarios==null)
            return null;
        
        if(selectedUsuarios.isEmpty()==false){
        activaTexto=true;
        return null;
    }else{
            sessionController.creaMensaje("hay que seleccionar al menos un usuario", FacesMessage.SEVERITY_ERROR);
            return null;
                    
        }
    }
    
   
    
     public String enviarMensajesVarios(){
         if(selectedUsuarios==null)
             return null;
         
    if(selectedUsuarios.isEmpty()){
        return null;
    }
    Usuario aux=null;
         try{
         
         aux=usuarioService.buscarUsuario("admin");
         }catch(InstanceNotFoundException ex){
             
         }
         
         Usuario[] logins=new Usuario[selectedUsuarios.size()];
         int i=0;
    for(Usuario u:selectedUsuarios){
        logins[i]=u;
        Mensaje mensaje=new Mensaje(u, aux, Calendar.getInstance().getTime(), tema, texto,false,false,false);
        i++;
        try{
        mensajeService.enviarMensaje(mensaje);
        }catch(Exception ex){
            
            sessionController.creaMensaje("error al enviar mensajes", FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }
    
    String loginPrincipal=logins[0].getLogin();
    
     try{
        
        if(logins.length>1){
        email.enviarEmailAdmin(loginPrincipal, sessionController.getCorreoConf(), tema, texto, logins);
        
        }else{
            email.enviarEmailAdmin(loginPrincipal, sessionController.getCorreoConf(), tema, texto);
        }
        
        
    }catch(EmailException ex){
        
        sessionController.creaMensaje("Error enviando el correo", FacesMessage.SEVERITY_ERROR);
        
    }
    
    
         
        sessionController.creaMensaje("mensajes enviados correctamente", FacesMessage.SEVERITY_INFO);
        activaTexto=false;
        //selectedUsuarios=null;
        tema="";
        texto="";
    return null;
}
    
     
      public void cancelarEnvioCoordinador(){
        
        activaTexto=false;
    }
    
       
}
