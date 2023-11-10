/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author luisa
 */
public class Nodo {
    
    private Nodo nodoSiguiente;
    private Personaje datos;
    
    public Nodo(Personaje datos) {
        this.nodoSiguiente = null;
        this.datos = datos;
    }

    /**
     * @return the nodoSiguiente
     */
    public Nodo getNodoSiguiente() {
        return nodoSiguiente;
    }

    /**
     * @param nodoSiguiente the nodoSiguiente to set
     */
    public void setNodoSiguiente(Nodo nodoSiguiente) {
        this.nodoSiguiente = nodoSiguiente;
    }

    /**
     * @return the datos
     */
    public Personaje getDatos() {
        return datos;
    }

    /**
     * @param datos the datos to set
     */
    public void setDatos(Personaje datos) {
        this.datos = datos;
    }
    
    
    
}
