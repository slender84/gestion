/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gestion.model.services;

import com.mycompany.gestion.entities.Asignatura;
import com.mycompany.gestion.entities.AsignaturaId;
import com.mycompany.gestion.entities.ComentarioAsignatura;
import com.mycompany.gestion.entities.Idioma;
import com.mycompany.gestion.entities.Movilidad;
import com.mycompany.gestion.entities.Usuario;
import com.mycompany.gestion.exceptions.InstanceNotFoundException;

import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author abc
 */
public interface AsignaturaService {
    
    public void crearAsignatura(Asignatura a);
    public List<Asignatura> listarAsignaturas();
    public List<Asignatura> listarAsignaturasPorUniversidad(boolean disponible,String codUniversidad);
    public List<Asignatura> listarAsignaturasPorUniversidadYCurso(boolean disponible,String nombreUniversidad, String curso);
    public void eliminarAsignatura(Asignatura a)throws InstanceNotFoundException;
    public void actualizarAsignatura(Asignatura a)throws InstanceNotFoundException;
    public void crearIdioma(Idioma i);
    public void eliminarIdioma(Idioma i);
    public List<Idioma>listarIdiomas();
    public Asignatura buscarAsignatura(AsignaturaId id)throws InstanceNotFoundException;
    public void insertarComentario(ComentarioAsignatura c);
    public void eliminarComentario(ComentarioAsignatura c);
    public List<ComentarioAsignatura> listarComentariosAsignatura(Usuario usuario);
    public void editarComentario(ComentarioAsignatura c) ;
    public List<Asignatura> asignaturasMovilidad(Movilidad m);
    public List<ComentarioAsignatura> listarComentariosPorAsignatura(AsignaturaId id);
    public ComentarioAsignatura buscarComentarioAsignatura(Integer id) throws InstanceNotFoundException;
    public List<ComentarioAsignatura> listarComentariosAsignaturaPendientes();
    public List<ComentarioAsignatura> listaLazyComentarioAsignatura(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);
    public int countComentarioAsignatura(Map<String,Object>filters);
}
