

package com.mycompany.gestion.model.services;

import com.mycompany.gestion.entities.Asignatura;
import com.mycompany.gestion.entities.Contrato;
import com.mycompany.gestion.entities.Equivalencia;
import com.mycompany.gestion.entities.Movilidad;
import com.mycompany.gestion.exceptions.InstanceNotFoundException;
import com.mycompany.gestion.model.utils.EquivalenciaRevisada;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;




public interface EquivalenciaService {
    
    
    public void crearEquivalencia(Equivalencia e);
    public void eliminarEquivalencia(Equivalencia e);
    public void actualizarEquivalencia(Equivalencia e);
    public List<Equivalencia> listarEquivalencias();
    public List<Equivalencia> listarEquivalenciasPorContrato(Integer id);
    public void crearContrato(Contrato c);
    public void modificarContrato(Contrato c)throws InstanceNotFoundException;
    public List<Contrato> listarContratos(Movilidad m);
    public void eliminarContrato(Contrato c);
    public Contrato buscarContrato(Integer id) throws InstanceNotFoundException;
   public List<Equivalencia> equivalenciasPublicas(String universidad);
   public Float[] totalCreditos(ArrayList<Equivalencia> lista);
   public void confirmarContrato(ArrayList<Equivalencia> lista,Contrato c);
   public ArrayList<Equivalencia> editarContrato(ArrayList<Equivalencia>listaAuxEquivalencias,Contrato c)throws InstanceNotFoundException;
   public void crearContratoDesdeAceptado(ArrayList<Equivalencia>listaAuxEquivalencias,Contrato c, Contrato cNuevo)throws InstanceNotFoundException;
   public ArrayList<EquivalenciaRevisada> compararEquivalencias(ArrayList<Equivalencia> listaAuxEquivalencias,ArrayList<Equivalencia> listaAuxEquivalenciasComparado);
    public Contrato verContratoPorEquivalencia(Equivalencia e)throws InstanceNotFoundException;
    public Movilidad buscarMovilidadPorContrato(Contrato c)throws InstanceNotFoundException;
    public boolean equivalenciaRepetida(Equivalencia e, Collection<Equivalencia> listaEquivalencias);
    public List<Object> listarAsignaturasMovilidad();
    public List<Asignatura> lista2();
    public Equivalencia buscaEquivalenciaRepetida(Equivalencia e, Collection<Equivalencia> listaEquivalencias);
    
    
}


