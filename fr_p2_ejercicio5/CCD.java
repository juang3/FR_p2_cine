/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr_p2_ejercicio5;

/**
 *
 * @author juang
 */
public class CCD {
    // Atributos
    private int code;
    private String body;
    
    public CCD(String cadena){
        String[] peticion =cadena.split("@");
        this.code = Integer.parseInt(peticion[0]);
        this.body = peticion[1];
    }
    
    public int getCode(){       return this.code;}
    public String getBody(){    return this.body;}
}
