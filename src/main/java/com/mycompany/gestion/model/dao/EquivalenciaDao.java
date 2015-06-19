/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gestion.model.dao;



import com.mycompany.gestion.entities.Asignatura;
import com.mycompany.gestion.entities.Equivalencia;

import java.util.List;


public interface EquivalenciaDao extends GenericDao<Equivalencia, Integer> {
   public List<Equivalencia> listarEquivalenciasPorContrato(Integer id);
   public List<Equivalencia> equivalenciasPublicas(String universidad);
   public List<Object> listarAsignaturasMovilidad();
   public List<Asignatura> lista2();
}
