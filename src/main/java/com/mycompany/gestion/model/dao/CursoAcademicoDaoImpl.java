/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion.model.dao;

import com.mycompany.gestion.entities.Cursoacademico;

import org.springframework.stereotype.Repository;

@Repository("cursoacademicoDao")
public class CursoAcademicoDaoImpl extends GenericDaoHibernate<Cursoacademico, String> implements CursoAcademicoDao{
    
}
