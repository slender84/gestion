package com.mycompany.gestion.model.dao;

import com.mycompany.gestion.entities.InfoCuenta;



public interface InfoCuentaDao {
    
    public InfoCuenta recuperarIntentos(String login);
    public void actualizarIntentos(InfoCuenta intentos);
    
}