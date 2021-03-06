

package com.mycompany.gestion.controllers;

import com.mycompany.gestion.entities.Contrato;
import com.mycompany.gestion.entities.Equivalencia;
import com.mycompany.gestion.entities.Movilidad;
import com.mycompany.gestion.entities.Usuario;
import com.mycompany.gestion.exceptions.InstanceNotFoundException;
import com.mycompany.gestion.model.services.EquivalenciaService;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;




@ManagedBean
@ViewScoped
public class VerMisContratosController implements Serializable{

    @ManagedProperty(value="#{sessionController}")
    private SessionController sessionController;
    
    @ManagedProperty(value="#{equivalenciaService}")
    private EquivalenciaService equivalenciaService;

    
    
   private Usuario user;
    
    
    
    
    private boolean nuevo;
    private boolean visibleContratos;
    private boolean verAceptado;
    
    
    
    private ArrayList<Contrato> listaContratos;
    private ArrayList<Contrato> filteredContratos;
    private Contrato selectedContrato;
    
    private Movilidad selectedMovilidad;
  
    
    public VerMisContratosController() {
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

    
     
    public EquivalenciaService getEquivalenciaService() {
        return equivalenciaService;
    }

    public void setEquivalenciaService(EquivalenciaService equivalenciaService) {
        this.equivalenciaService = equivalenciaService;
    }
    
    
   
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
   

    public boolean isNuevo() {
        return nuevo;
    }

   
    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public boolean isVisibleContratos() {
        return visibleContratos;
    }

    public void setVisibleContratos(boolean visibleContratos) {
        this.visibleContratos = visibleContratos;
    }

    public boolean isVerAceptado() {
        return verAceptado;
    }

    public void setVerAceptado(boolean verAceptado) {
        this.verAceptado = verAceptado;
    }
    
    

    public ArrayList<Contrato> getListaContratos() {
        return listaContratos;
    }

    public void setListaContratos(ArrayList<Contrato> listaContratos) {
        this.listaContratos = listaContratos;
    }

    public ArrayList<Contrato> getFilteredContratos() {
        return filteredContratos;
    }

    public void setFilteredContratos(ArrayList<Contrato> filteredContratos) {
        this.filteredContratos = filteredContratos;
    }

    public Contrato getSelectedContrato() {
        return selectedContrato;
    }

    public void setSelectedContrato(Contrato selectedContrato) {
        this.selectedContrato = selectedContrato;
    }

    
    public Movilidad getSelectedMovilidad() {
        return selectedMovilidad;
    }

    public void setSelectedMovilidad(Movilidad selectedMovilidad) {
        this.selectedMovilidad = selectedMovilidad;
    }

    
    
    
    public String eliminarContrato(){
        
        try{
        selectedContrato=equivalenciaService.buscarContrato(selectedContrato.getIdContrato());
        }catch(InstanceNotFoundException ex){
            sessionController.creaMensaje("contrato no encontrado", FacesMessage.SEVERITY_ERROR);
            verContratos();
            return null;
            
        }
        ArrayList<Equivalencia> listaCopia=new ArrayList<>(selectedContrato.getEquivalencias());
        
           
           selectedContrato.setEquivalencias(null);
           equivalenciaService.eliminarContrato(selectedContrato);
            
            for(Equivalencia e:listaCopia){
              
                try{
                equivalenciaService.eliminarEquivalencia(e);
                }catch(org.springframework.dao.DataIntegrityViolationException ex){
                    
                }
            }
            
          
        sessionController.creaMensaje("contrato eliminado correctamente", FacesMessage.SEVERITY_INFO);
        verContratos();
        
        selectedContrato=null;
        
        return null;
    }
    
    
    public void verContratos(){
        verAceptado=true;
        visibleContratos=true;
        
        
        listaContratos=(ArrayList<Contrato>)equivalenciaService.listarContratos(selectedMovilidad);
        
        if(listaContratos.isEmpty()){
        nuevo=true;
    }else
            for (Contrato c: listaContratos){
                
        if(c.getEstado().equalsIgnoreCase("pendiente")||c.getEstado().equalsIgnoreCase("rechazado")||c.getEstado().equalsIgnoreCase("aceptado")){
            nuevo=false;
            break;
        }
    }
        
    }
    
     public void cerrarContratos(){
        
        visibleContratos=false;
    }
    
     
     public String revisarContrato(){
         
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("movilidad", selectedMovilidad);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("contrato", selectedContrato);
       
        
        
        
        for(Contrato c:listaContratos){
            
            if(selectedContrato.getFecha().compareTo(c.getFecha())==-1||selectedContrato.getEstado().equals("pendiente")){
                
             return ("verContrato.xhtml?faces-redirect=true");
            }
            
            
        }
        
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ultimo", "ultimo");
          return ("verContrato.xhtml?faces-redirect=true");
     }
     
    
    public String crearContrato(){
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("movilidad", selectedMovilidad);
        return ("elaborarContrato.xhtml?faces-redirect=true");
        
        
    }
    
    public String editarContrato(){
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("movilidad", selectedMovilidad);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("contrato", selectedContrato);
       
        
        return ("elaborarContratoEditado.xhtml?faces-redirect=true");
        
    }
    
    
    
    
    
    
    
    
    
   
    
    
    
  
    
    
}
