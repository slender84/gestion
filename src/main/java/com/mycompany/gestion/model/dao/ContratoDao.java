
package com.mycompany.gestion.model.dao;


import com.mycompany.gestion.entities.Configuracion;
import com.mycompany.gestion.entities.Contrato;
import com.mycompany.gestion.entities.Movilidad;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;


public interface ContratoDao extends GenericDao<Contrato, Integer>{
    
    public List<Contrato> listarContratos(Movilidad m);
    public List<Contrato> listarContratosPendientes();
    public List<Contrato> listarTodosContratos();
    public List<Contrato> listarContratosPorFiltro(Map<String,String> listaFiltros);
    public List<Contrato> listaLazyContrato(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);
    public int countContrato(Map<String,Object> filters);
    
}
