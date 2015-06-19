
package com.mycompany.gestion.model.dao;

import com.mycompany.gestion.entities.Pais;

import org.springframework.stereotype.Repository;

@Repository("paisDao")
public class PaisDaoImpl extends GenericDaoHibernate<Pais, String> implements PaisDao {
    
}
