
package com.mycompany.gestion.controllers;

import com.mycompany.gestion.entities.Contrato;
import com.mycompany.gestion.exceptions.InstanceNotFoundException;
import com.mycompany.gestion.model.services.EquivalenciaService;
import com.mycompany.gestion.model.utils.email;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;




@ManagedBean
@RequestScoped
public class FileController implements Serializable{

    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;
    
    @ManagedProperty(value = "#{equivalenciaService}")
    private EquivalenciaService equivalenciaService;
    
    private Contrato selectedContrato;
    private UploadedFile file=null;

    public Contrato getSelectedContrato() {
        return selectedContrato;
    }

    public void setSelectedContrato(Contrato selectedContrato) {
        this.selectedContrato = selectedContrato;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public EquivalenciaService getEquivalenciaService() {
        return equivalenciaService;
    }

    public void setEquivalenciaService(EquivalenciaService equivalenciaService) {
        this.equivalenciaService = equivalenciaService;
    }
    
    
    
    
    
    
    
    public FileController() {
    }
    
    @PostConstruct
    public void init(){
        if(sessionController.getUser().getTipoUsuario()==1){
            
            
            if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("contrato")){
            
            selectedContrato=(Contrato)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("contrato");
            if(selectedContrato.getArchivo()!=null)
            descarga=descargarArchivo();
            
            
            
        }else
            
            try{
                
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/usuario/verMisMovilidades.xhtml");
                
            }catch(IOException ex){
                
                
            }

        
    }else{
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("contrato")){
            
            selectedContrato=(Contrato)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("contrato");
           
            if(selectedContrato.getArchivo()!=null)
            descarga=descargarArchivo();
            
            
            
        }else
            
            try{
                
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/admin/verMovilidades.xhtml");
                
            }catch(IOException ex){
                
                
            }
}
    }
    
    
    public String subirArchivo()throws IOException{
         
        
        
         if(file.getFileName().equals("")){
             sessionController.creaMensaje("Hay que seleccionar un archivo", FacesMessage.SEVERITY_ERROR);
             return null;
         }
         
         String fileName=file.getFileName();
         Long fileSize=file.getSize();
         byte[] aux=new byte[fileSize.intValue()];
         try{
         
         InputStream fin=file.getInputstream();
         
         
         fin.read(aux);
         }catch(Exception ex){
             //ex.printStackTrace();
         }
         
         selectedContrato.setArchivo(aux);
         try{
             
             equivalenciaService.modificarContrato(selectedContrato);
             
         }catch(InstanceNotFoundException ex){
             
             sessionController.creaMensaje("se ha producido un error", FacesMessage.SEVERITY_ERROR);
             file=null;
             return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/admin/gestionarContrato.xhtml";
             
             
         }
         
         String mensaje="La administración ha subido un documento para la movilidad "+selectedContrato.getMovilidad().getUniversidad().getNombre();
         
         try{
             
             email.enviarEmailAdmin(selectedContrato.getMovilidad().getUsuario().getLogin(), sessionController.getCorreoConf(), "Documento subido", mensaje);
             
             
         }catch(EmailException ex){
             
             sessionController.creaMensaje("no se pudo enviar el correo", FacesMessage.SEVERITY_ERROR);
             
         }
         
         
         sessionController.creaMensaje("Archivo subido correctamente", FacesMessage.SEVERITY_INFO);
         file=null;
         return null;
         
     }
     
     private StreamedContent descarga;
     public StreamedContent getDescarga(){
         return descarga;
     }
     
     public StreamedContent descargarArchivo(){
         
         InputStream bis=new ByteArrayInputStream(selectedContrato.getArchivo());
         
         descarga=new DefaultStreamedContent(bis, "application/pdf", "documento_erasmus_"+selectedContrato.getMovilidad().getUsuario().getLogin()+".pdf");
         return descarga;
        
         
     }
     
     public String eliminarArchivo(){
         
         selectedContrato.setArchivo(null);
         
         try{
             
             equivalenciaService.modificarContrato(selectedContrato);
             
             
         }catch(InstanceNotFoundException ex){
             
             sessionController.creaMensaje("se ha producido un error", FacesMessage.SEVERITY_ERROR);
             return null;
         }
         
         sessionController.creaMensaje("Archivo eliminado", FacesMessage.SEVERITY_INFO);
         return null;
         
     }
    
    
    
    
}
