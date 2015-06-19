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

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;



public interface MovilidadService {
    
    public List<Movilidad> listarTodasMovilidades();
  
    public Date fechaMin();
    public Date fechaMax();
    public void crearMovilidad(Movilidad m);
    public List<Movilidad> listarMisMovilidades(String user);
    public void eliminarMovilidad(Movilidad m);
    public Movilidad buscarMovilidad(Integer id) throws InstanceNotFoundException;
    public void crearMovilidad(Date fechaInicio,Date fechaFin,Usuario user,Universidad u,Cursoacademico ca) throws DuracionException,NumeroDeMovilidadesException;
    public void editarMovilidad(Movilidad m);
    public List<Movilidad> listarMovilidadPorFiltro(Map<String,String> listaFiltros);
    public List<Contrato> listarContratosPendientes();
    public List<Contrato> listarTodosContratos();
    public List<Contrato> listarContratosPorFiltro(Map<String,String> listaFiltros);
    public List<Movilidad> listaLazy(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);
    public int count(Map<String,Object>filters);
    public List<Contrato> listaLazyContrato(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);
    public int countContrato(Map<String,Object> filters);
    
      
      
  
}
