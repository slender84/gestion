

package com.mycompany.gestion.model.services;


import com.mycompany.gestion.entities.Asignatura;
import com.mycompany.gestion.entities.AsignaturaId;
import com.mycompany.gestion.entities.ComentarioAsignatura;
import com.mycompany.gestion.entities.Idioma;
import com.mycompany.gestion.entities.Movilidad;
import com.mycompany.gestion.entities.Usuario;
import com.mycompany.gestion.exceptions.InstanceNotFoundException;
import com.mycompany.gestion.model.dao.AsignaturaDao;
import com.mycompany.gestion.model.dao.IdiomaDao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

 @Transactional
 @Service("asignaturaService")
public class AsignaturaServiceImpl implements AsignaturaService,Serializable{
    
   
    @Autowired
    private AsignaturaDao asignaturaDao;
    @Autowired
    private IdiomaDao idiomaDao;

    public AsignaturaDao getAsignaturaDao() {
        return asignaturaDao;
    }
    public void setAsignaturaDao(AsignaturaDao asignaturaDao) {
        this.asignaturaDao = asignaturaDao;
    }

    public IdiomaDao getIdiomaDao() {
        return idiomaDao;
    }

    public void setIdiomaDao(IdiomaDao idiomaDao) {
        this.idiomaDao = idiomaDao;
    }
    
    
    
    
    @Override
    public void crearAsignatura(Asignatura a){
        
        asignaturaDao.insertar(a);
    }
    
    @Override
    public List<Asignatura> listarAsignaturas(){
        return asignaturaDao.listar();
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public List<Asignatura> listarAsignaturasPorUniversidad(boolean disponible,String codUniversidad){
        
        return asignaturaDao.listarAsignaturasPorUniversidad(disponible,codUniversidad);
                
                
    }
    @Override
    public void eliminarAsignatura(Asignatura a) throws InstanceNotFoundException{
        if(asignaturaDao.existe(a.getId())==false)
                throw new InstanceNotFoundException();
        
        asignaturaDao.eliminar(a);
        
    }
    
    
    @Override
    public void actualizarAsignatura(Asignatura a)throws InstanceNotFoundException{
        if(asignaturaDao.existe(a.getId())==false)
                throw new InstanceNotFoundException();
        asignaturaDao.editar(a);
    }
    
    @Override
   public void crearIdioma(Idioma i){
       
       idiomaDao.insertar(i);
       
       
   }
   @Override
    public void eliminarIdioma(Idioma i){
        
        idiomaDao.eliminar(i);
        
    }
    
    @Override
    public List<Idioma>listarIdiomas(){
        
    return idiomaDao.listar();
        
        
    }
    @Override
    public Asignatura buscarAsignatura(AsignaturaId id)throws InstanceNotFoundException{
        
        if(asignaturaDao.existe(id)==false)
            throw new InstanceNotFoundException();
        Asignatura a= asignaturaDao.buscar(id);
        Hibernate.initialize(a.getComentarioAsignaturas());
        return a;
    }
    
    @Override
    public void insertarComentario(ComentarioAsignatura c){
        
        asignaturaDao.insertarComentario(c);
        
    }
    
    @Override
    public void eliminarComentario(ComentarioAsignatura c){
        
        
        asignaturaDao.eliminarComentario(c);
        
    }
    
    @Override
    public List<Asignatura> listarAsignaturasPorUniversidadYCurso(boolean disponible,String nombreUniversidad, String curso){
        
        return asignaturaDao.listarAsignaturasPorUniversidadYCurso (disponible,nombreUniversidad, curso);
        
        
    }
    
    @Override
    public List<ComentarioAsignatura> listarComentariosAsignatura(Usuario usuario){
        
        return asignaturaDao.listarComentariosAsignatura(usuario);
    }
    
    
    
    @Override
    public void editarComentario(ComentarioAsignatura c) {
        
        asignaturaDao.editarComentario(c);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Asignatura> asignaturasMovilidad(Movilidad m){
        
        
        return asignaturaDao.asignaturasMovilidad(m);
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public List<ComentarioAsignatura> listarComentariosPorAsignatura(AsignaturaId id){
        
        return asignaturaDao.listarComentariosPorAsignatura(id);
    }
    
    
    @Override
    public ComentarioAsignatura buscarComentarioAsignatura(Integer id) throws InstanceNotFoundException{
        
        
        return asignaturaDao.buscarComentarioAsignatura(id);
        
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ComentarioAsignatura> listarComentariosAsignaturaPendientes(){
        
        return asignaturaDao.listarComentariosAsignaturaPendientes();
        
        
    }
    
    @Override
    public List<ComentarioAsignatura> listaLazyComentarioAsignatura(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
        
        return asignaturaDao.listaLazyComentarioAsignatura(first, pageSize, sortField, sortOrder, filters);
        
        
        
    }
    
    @Override
    public int countComentarioAsignatura(Map<String,Object>filters){
        
        
        return asignaturaDao.countComentarioAsignatura(filters);
        
    }
    
    
    
    
    
}
