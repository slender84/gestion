/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gestion.model.dao;

 
import com.mycompany.gestion.entities.Asignatura;
import com.mycompany.gestion.entities.AsignaturaId;
import com.mycompany.gestion.entities.ComentarioAsignatura;
import com.mycompany.gestion.entities.Movilidad;
import com.mycompany.gestion.entities.Usuario;
import com.mycompany.gestion.exceptions.InstanceNotFoundException;



import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;


public interface AsignaturaDao extends GenericDao<Asignatura, AsignaturaId>{
    
    
    public List<Asignatura> listarAsignaturasPorUniversidad(boolean disponible,String codUniversidad);
    public void insertarComentario(ComentarioAsignatura c);
    public void eliminarComentario(ComentarioAsignatura c);
    public void editarComentario(ComentarioAsignatura c);
    public List<Asignatura> listarAsignaturasPorUniversidadYCurso (boolean disponible,String nombreUniversidad, String curso);
    public List<ComentarioAsignatura> listarComentariosAsignatura(Usuario usuario);
    public List<Asignatura> asignaturasMovilidad(Movilidad m);
    public List<ComentarioAsignatura> listarComentariosPorAsignatura(AsignaturaId id);
    public ComentarioAsignatura buscarComentarioAsignatura(Integer id) throws InstanceNotFoundException;
    public List<ComentarioAsignatura> listarComentariosAsignaturaPendientes();
    public List<ComentarioAsignatura> listaLazyComentarioAsignatura(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);
    public int countComentarioAsignatura(Map<String,Object>filters);
}
