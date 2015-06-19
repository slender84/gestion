
package com.mycompany.gestion.model.dao;

import com.mycompany.gestion.entities.Cronica;
import com.mycompany.gestion.entities.Usuario;

import java.util.List;


public interface CronicaDao extends GenericDao<Cronica,Integer> {
    
    
    public List<Cronica> listarCronicasPorUniversidad(String universidad);
    public List<Cronica> listarMisCronicas(Usuario u);
    public List<Cronica> listarCronicasPublicas(String universidad);
    
}
