/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gestion.model.services;


import com.mycompany.gestion.entities.Configuracion;
import com.mycompany.gestion.entities.Contrato;
import com.mycompany.gestion.entities.Cursoacademico;
import com.mycompany.gestion.entities.Movilidad;
import com.mycompany.gestion.entities.Universidad;
import com.mycompany.gestion.entities.Usuario;
import com.mycompany.gestion.exceptions.DuracionException;
import com.mycompany.gestion.exceptions.InstanceNotFoundException;
import com.mycompany.gestion.exceptions.NumeroDeMovilidadesException;
import com.mycompany.gestion.model.dao.ContratoDao;
import com.mycompany.gestion.model.dao.MovilidadDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("movilidadService")
@Transactional
public class MovilidadServiceImpl implements MovilidadService{
    
    
    @Autowired
    private MovilidadDao movilidadDao;
    
    @Autowired
    private ContratoDao contratoDao;
    
    SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");

    public MovilidadDao getMovilidadDao() {
        return movilidadDao;
    }

    public void setMovilidadDao(MovilidadDao movilidadDao) {
        this.movilidadDao = movilidadDao;
    }

    public ContratoDao getContratoDao() {
        return contratoDao;
    }

    public void setContratoDao(ContratoDao contratoDao) {
        this.contratoDao = contratoDao;
    }
    
    
    
    @Override
    @Transactional
    public List<Movilidad> listarTodasMovilidades(){
        
       List<Movilidad> aux=movilidadDao.listar();
        for(Movilidad m:aux){
            //Hibernate.initialize(m.getUniversidad());
            if(m.getFechaFin().compareTo(new Date())==-1){
                m.setEstado("terminada");
                editarMovilidad(m);
                
            }
        }
        
        
        return aux;
        
    }
    
    
    
    @Override
    public Date fechaMin(){
        
        Calendar calendario=Calendar.getInstance();
        Date d=calendario.getTime();
        return d;
    }
    
    @Override
    public Date fechaMax(){
        Calendar calendario=Calendar.getInstance();
        calendario.add(1, 1);// máximo tiempo para empezar la movilidad
        Date d=calendario.getTime();
        return d;
        
    }
    
    @Override
    public void crearMovilidad(Movilidad m){
        
        movilidadDao.insertar(m);
    }
    
    @Override
    @Transactional
    public List<Movilidad> listarMisMovilidades(String user){
        
        List<Movilidad> aux= movilidadDao.listarMisMovilidades(user);
        for(Movilidad m:aux){
            
            
            if(m.getFechaFin().compareTo(new Date())==-1){
                m.setEstado("terminada");
                editarMovilidad(m);
                
            }
            
            
        }
        return aux;
    }
    
    @Override
    public void eliminarMovilidad(Movilidad m){
        
        movilidadDao.eliminar(m);
    }
    
    
    
    @Override
    @Transactional(readOnly = true)
    public Movilidad buscarMovilidad(Integer id)throws InstanceNotFoundException{
        
        Movilidad m=movilidadDao.buscar(id);
        //Hibernate.initialize(m.getUniversidad());
        return m;
    }
    
    
    @Override
    public void crearMovilidad(Date fechaInicio,Date fechaFin,Usuario user,Universidad u,Cursoacademico ca)throws DuracionException,NumeroDeMovilidadesException{
        
        Calendar cal1=Calendar.getInstance();
        Calendar cal2=Calendar.getInstance();
                cal1.setTime(fechaInicio);
                cal2.setTime(fechaFin);
                if (cal2.compareTo(cal1)<1){
                    throw new DuracionException("la fecha de inicio es igual o posterior a la fecha de fin");
                }
                
                Calendar calAux=Calendar.getInstance();
                calAux.setTime(fechaInicio);
                calAux.add(2, 3);
                if(cal2.compareTo(calAux)<0){
                    
                    throw new DuracionException("la duración mínima de una movilidad son 3 meses");
                }
                
                calAux.setTime(fechaInicio);
                calAux.add(2, 12);
                if(cal2.compareTo(calAux)>0){
                    throw new DuracionException("la duración máxima es de un año");
                }
                
                 
                
                   ArrayList<Movilidad> aux;
                   aux=(ArrayList < Movilidad >)listarMisMovilidades(user.getLogin());
                      
                    
                    int i=0;
                    Movilidad enCurso=null;
                    
                    if(aux.size()>0){
                    for(Movilidad mov:aux){
                        
                        if(mov.getEstado().equalsIgnoreCase("pendiente")){
                           throw new NumeroDeMovilidadesException("hay una movilidad pendiente que debe ser aceptada por el coordinador o eliminada");
                        }
                        
                       if(mov.getEstado().equalsIgnoreCase("aceptada")||mov.getEstado().equalsIgnoreCase("terminada")){
                           i=i+1;
                           enCurso=mov;
                           if(i>1){
                               
                               throw new NumeroDeMovilidadesException("Como máximo se pueden tener dos movilidades");
                           }
                           
                       }
                  
                    }     
                    
                       if(i==1){
                           
                            if( (fechaInicio.compareTo(enCurso.getFechaInicio())>-1 && fechaInicio.compareTo(enCurso.getFechaFin())<1)||(fechaFin.compareTo(enCurso.getFechaInicio())>-1  && fechaFin.compareTo(enCurso.getFechaFin())<1)||(fechaInicio.compareTo(enCurso.getFechaInicio())<1  && fechaFin.compareTo(enCurso.getFechaFin())>-1)){
                                //creaMensaje(Boolean.toString((fechaInicio.compareTo(enCurso.getFechaInicio())<1  && fechaFin.compareTo(enCurso.getFechaFin())<1)), FacesMessage.SEVERITY_INFO);
                                throw new NumeroDeMovilidadesException("las fechas se solapan con otra movilidad");
                       }
                       }
                    }
                    
              
              String estado="pendiente";
              
              
             
              Movilidad m=new Movilidad(ca,u, user, fechaInicio, fechaFin, estado,null);
              
              crearMovilidad(m);
              
    }
    
    @Override
    public void editarMovilidad(Movilidad m){
        
        movilidadDao.editar(m);
        
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public List<Movilidad> listarMovilidadPorFiltro(Map<String,String> listaFiltros){
        
        
        return movilidadDao.listarMovilidadPorFiltro(listaFiltros);
        
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Contrato> listarContratosPorFiltro(Map<String,String> listaFiltros){
        
        return contratoDao.listarContratosPorFiltro(listaFiltros);
        
    }
    
    
    
    
    
     @Override
     @Transactional(readOnly = true)
     public List<Contrato> listarContratosPendientes(){
         
         
         return contratoDao.listarContratosPendientes();
         
     }
    
     @Override
     @Transactional(readOnly = true)
     public List<Contrato> listarTodosContratos(){
         
         return contratoDao.listarTodosContratos();
         
         
     }
     
     
     @Override
     public List<Movilidad> listaLazy(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
         
         return movilidadDao.listaLazy(first,pageSize,sortField,sortOrder,filters);
         
     }
     
     @Override
      public int count(Map<String,Object>filters){
      
      return movilidadDao.count(filters);
      
  }
     @Override
     public List<Contrato> listaLazyContrato(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
         
         
         return contratoDao.listaLazyContrato(first, pageSize, sortField, sortOrder,filters);
         
         
         
     }
     
     @Override
    public int countContrato(Map<String,Object> filters){
        
        
        return contratoDao.countContrato(filters);
        
        
    }
    
    
}
