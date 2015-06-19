package com.mycompany.gestion.model.utils;

import com.mycompany.gestion.entities.Configuracion;
import com.mycompany.gestion.entities.Estado;
import com.mycompany.gestion.entities.EstadoMovilidad;
import java.util.List;


public interface UtilidadService {
    
    public void crearEstado(Estado e);
    public List<Estado> listaEstados();
    public void eliminaEstado(Estado e);
    
    public void crearEstadoMovilidad(EstadoMovilidad e);
    public List<EstadoMovilidad> listaEstadosMovilidad();
    public void eliminaEstadoMovilidad(EstadoMovilidad e);
    
    
    public Configuracion getCorreoConf();
    public void setCorreoConf(Configuracion correoConf);
    
            
}