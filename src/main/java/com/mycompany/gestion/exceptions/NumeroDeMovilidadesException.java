
package com.mycompany.gestion.exceptions;


public class NumeroDeMovilidadesException extends Exception{

    String motivo;
    
    public NumeroDeMovilidadesException(String motivo) {
        super(motivo);
        
    }
    
    
    
}