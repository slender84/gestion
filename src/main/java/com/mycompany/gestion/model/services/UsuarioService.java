/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gestion.model.services;

import com.mycompany.gestion.entities.Configuracion;
import com.mycompany.gestion.entities.Direccion;
import com.mycompany.gestion.entities.InfoCuenta;
import com.mycompany.gestion.entities.Usuario;
import com.mycompany.gestion.exceptions.InstanceNotFoundException;
import com.mycompany.gestion.exceptions.PasswordIncorrectoException;

import java.util.List;
import java.util.Map;
import org.apache.commons.mail.EmailException;
import org.primefaces.model.SortOrder;



public interface UsuarioService {
    
    public Usuario buscarUsuario(String nombre)throws InstanceNotFoundException;
    public void eliminarUsuario(Usuario u);
    public List<Usuario> listarUsuarios();
     public void insertarUsuario(Usuario u);
    public void actualizarUsuario(Usuario u);
    public void autenticarUsuario(String password,Usuario u) throws PasswordIncorrectoException;
    public String generarPassword();
     public void enviarEmail(String login,String password,Configuracion correoConf) throws EmailException;
     public InfoCuenta recuperarIntentos(String login);
     public void actualizarIntentos(InfoCuenta intentos);
     public void editarUsuario(Usuario u)throws InstanceNotFoundException;
     public void eliminarDireccion(Direccion d);
     public void crearDireccion(Direccion d);
     public List<Direccion> listaDirecciones();
     public List<Usuario> listaLazyUsuario(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);
     public int countUsuario(Map<String,Object>filters);
}
