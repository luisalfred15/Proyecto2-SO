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
    private int calidadHabs;
    private int calidadVida;
    private int calidadFuerza;
    private int calidadAgilidad;
    
    public Propiedades(int habilidades, int puntosVida, int fuerza, int agilidad, int calidadHabs, int calidadVida, int calidadFuerza, int calidadAgilidad) {
        this.habilidades = habilidades;
        this.puntosVida = puntosVida;
        this.fuerza = fuerza;
        this.habilidades = agilidad;
        this.calidadHabs = calidadHabs;
        this.calidadVida = calidadVida;
        this.calidadFuerza = calidadFuerza;
        this.calidadAgilidad = calidadAgilidad;
        
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

    /**
     * @return the calidadHabs
     */
    public int getCalidadHabs() {
        return calidadHabs;
    }

    /**
     * @param calidadHabs the calidadHabs to set
     */
    public void setCalidadHabs(int calidadHabs) {
        this.calidadHabs = calidadHabs;
    }

    /**
     * @return the calidadVida
     */
    public int getCalidadVida() {
        return calidadVida;
    }

    /**
     * @param calidadVida the calidadVida to set
     */
    public void setCalidadVida(int calidadVida) {
        this.calidadVida = calidadVida;
    }

    /**
     * @return the calidadFuerza
     */
    public int getCalidadFuerza() {
        return calidadFuerza;
    }

    /**
     * @param calidadFuerza the calidadFuerza to set
     */
    public void setCalidadFuerza(int calidadFuerza) {
        this.calidadFuerza = calidadFuerza;
    }

    /**
     * @return the calidadAgilidad
     */
    public int getCalidadAgilidad() {
        return calidadAgilidad;
    }

    /**
     * @param calidadAgilidad the calidadAgilidad to set
     */
    public void setCalidadAgilidad(int calidadAgilidad) {
        this.calidadAgilidad = calidadAgilidad;
    }
}
