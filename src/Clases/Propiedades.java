/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author luisa
 */
public class Propiedades {
    private int habilidades;
    private int puntosVida;
    private int fuerza;
    private int agilidad;
    // 1 si es de calidad, 0 si no es de calidad
    
    public Propiedades(int habilidades, int puntosVida, int fuerza, int agilidad) {
        this.habilidades = habilidades;
        this.puntosVida = puntosVida;
        this.fuerza = fuerza;
        this.agilidad = agilidad;
        
        // las cantidades de cada atributo podrian ser enteras, de forma que si esta es negativa, no es de calidad, y si es positiva, es de calidad
    }

    /**
     * @return the habilidades
     */
    public int getHabilidades() {
        return habilidades;
    }

    /**
     * @param habilidades the habilidades to set
     */
    public void setHabilidades(int habilidades) {
        this.habilidades = habilidades;
    }

    /**
     * @return the puntosVida
     */
    public int getPuntosVida() {
        return puntosVida;
    }

    /**
     * @param puntosVida the puntosVida to set
     */
    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    /**
     * @return the fuerza
     */
    public int getFuerza() {
        return fuerza;
    }

    /**
     * @param fuerza the fuerza to set
     */
    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    /**
     * @return the agilidad
     */
    public int getAgilidad() {
        return agilidad;
    }

    /**
     * @param agilidad the agilidad to set
     */
    public void setAgilidad(int agilidad) {
        this.agilidad = agilidad;
    }
}
