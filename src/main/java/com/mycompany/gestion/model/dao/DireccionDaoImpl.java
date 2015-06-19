
package com.mycompany.gestion.model.dao;

import com.mycompany.gestion.entities.Direccion;

import org.springframework.stereotype.Repository;

@Repository("direccionDao")
public class DireccionDaoImpl extends GenericDaoHibernate<Direccion, String> implements DireccionDao{
    
}
