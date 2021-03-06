package com.mycompany.gestion.controllers;

import com.mycompany.gestion.entities.Usuario;
import com.mycompany.gestion.exceptions.InstanceNotFoundException;
import com.mycompany.gestion.model.services.UsuarioService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;



@ManagedBean
@ViewScoped
public class EliminarUsuarioController implements Serializable{

    @ManagedProperty(value="#{sessionController}")
    private SessionController sessionController;
    
    @ManagedProperty(value="#{usuarioService}")  
    private transient UsuarioService usuarioService;
    
    
    private ArrayList<Usuario> selectedUsuarios;
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Usuario> filteredUsuarios;
    
    private LazyDataModel<Usuario> model;
    private List<Usuario> result;
    
    
    
    public EliminarUsuarioController() {
    }
    @PostConstruct
    public void init(){
        
        //setListaUsuarios((ArrayList < Usuario >)usuarioService.listarUsuarios());
        
         model=new LazyDataModel<Usuario>(){
          
            @Override
            public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    
             result=usuarioService.listaLazyUsuario(first,pageSize,sortField,sortOrder,filters);
            
                //setRowCount(10);
                setRowCount(usuarioService.countUsuario(filters));
              return result;
              
              
            }
            
            
            @Override
            public Object getRowKey(Usuario usuario){
                  
                  return usuario.getLogin();
                  
              }
            
            @Override
            public Usuario getRowData(String rowKey){
                
                for(Usuario u:result){
                    
                    if(u.getLogin().equals(rowKey))
                        return u;
                    
                }
                return null;
            }
            
        
    };        
        
        
        
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public ArrayList<Usuario> getSelectedUsuarios() {
        return selectedUsuarios;
    }

    public void setSelectedUsuarios(ArrayList<Usuario> selectedUsuarios) {
        this.selectedUsuarios = selectedUsuarios;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ArrayList<Usuario> getFilteredUsuarios() {
        return filteredUsuarios;
    }

    public void setFilteredUsuarios(ArrayList<Usuario> filteredUsuarios) {
        this.filteredUsuarios = filteredUsuarios;
    }

    public LazyDataModel<Usuario> getModel() {
        return model;
    }

    public void setModel(LazyDataModel<Usuario> model) {
        this.model = model;
    }

    public List<Usuario> getResult() {
        return result;
    }

    public void setResult(List<Usuario> result) {
        this.result = result;
    }
    
    
    
    public String eliminaUsuariosLista(){
        
        if(selectedUsuarios.isEmpty()){
            return null;
        }
        
        for(Usuario u:selectedUsuarios){
            
            if(u.getTipoUsuario()==2){
                try{
                usuarioService.eliminarUsuario(u);
                result.remove(u);
                }catch(RuntimeException ex){
                    return null;
                }
            }else{
                
                
        
            
            
            u.setBorrado(true);
            try{
        usuarioService.editarUsuario(u);
            }catch(InstanceNotFoundException ex){
                
                
            }
        listaUsuarios.remove(u);
        }
        
        }
         sessionController.creaMensaje("usuarios borrados ", FacesMessage.SEVERITY_INFO);
        //setListaUsuarios((ArrayList < Usuario >)usuarioService.listar());
        return null;
          
    }
    
    public String borrarUsuario(){
        
        
        
         if(selectedUsuarios.isEmpty()){
            return null;
        }
         
         for(Usuario u:selectedUsuarios){
             
             try{
                 
                 usuarioService.eliminarUsuario(u);
                  
             }catch(RuntimeException ex){
                 
                 sessionController.creaMensaje("se ha producido un error", FacesMessage.SEVERITY_ERROR);
                 //listaUsuarios=(ArrayList < Usuario >)usuarioService.listarUsuarios();
                 return null;
             }
             
             result.remove(u);
             
             
         }
         sessionController.creaMensaje("usuarios eliminados correctamente", FacesMessage.SEVERITY_INFO);
                 
         
         return null;
        
    }
    
    
    
    
    
    
    
}
