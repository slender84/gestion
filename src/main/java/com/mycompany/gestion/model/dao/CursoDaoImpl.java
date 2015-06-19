
package com.mycompany.gestion.model.dao;

import com.mycompany.gestion.entities.Curso;

import org.springframework.stereotype.Repository;


@Repository("cursoDao")
public class CursoDaoImpl extends GenericDaoHibernate<Curso, String> implements CursoDao{
    
}
