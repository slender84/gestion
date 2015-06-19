
package com.mycompany.gestion.model.dao;

import com.mycompany.gestion.entities.Idioma;

import org.springframework.stereotype.Repository;

@Repository("idiomaDao")
public class IdiomaDaoImpl extends GenericDaoHibernate<Idioma, String> implements IdiomaDao {
    
}
