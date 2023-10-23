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
    private Object datos;
    
    public Nodo(Object datos) {
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
    public Object getDatos() {
        return datos;
    }

    /**
     * @param datos the datos to set
     */
    public void setDatos(Object datos) {
        this.datos = datos;
    }
    
    
    
}
