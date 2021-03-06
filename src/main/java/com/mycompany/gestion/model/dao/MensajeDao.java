/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gestion.model.dao;



import com.mycompany.gestion.entities.Mensaje;


import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;


public interface MensajeDao extends GenericDao<Mensaje, Integer>{

    public void crearMensaje(Mensaje m);
    public List<Mensaje> mensajesEnviados(String origen,String destino);
    public List<Mensaje> mensajesRecibidos(String origen,String destino);
   
    public List<Mensaje> mensajesEnviadosTotal(String origen);
    public List<Mensaje> mensajesRecibidosTotal(String destino);
    public List<Mensaje> listaLazyMensajeRecibido(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters,String destino);
    public int countMensajeRecibido(Map<String,Object>filters,String destino);
    public List<Mensaje> listaLazyMensajeEnviado(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters,String origen);
    public int countMensajeEnviado(Map<String,Object>filters,String origen);
    
}
