/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.Random;

/**
 *
 * @author luisa
 */
public class Personaje { // se pueden tener personajes repetidos pero de diferente Tipo? O un personaje debe tener un arreglo de Tipoes?
    
    private String id;
    private String nombre;
    private int imagen;
    private int tipo; // tipo puede ser excepcional (1), promedio (2) o deficiente (3)
    private Propiedades propiedades;
    private int contador;
    //static int contadorId = 0;
    //int contadorCompania = 0;
    
    public Personaje(String nombre, int imagen, Propiedades propiedades, int contador) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.propiedades = propiedades;
        this.contador = 0;
        
        /*
        contadorId++;
        this.generarId();
        this.determinarTipo();
        */
    }
    /*
    public void generarId() {
        if (this.contadorId < 10) {
            this.id = "Z" + this.contadorCompania;
            contadorCompania++;
        } else {
            this.id = "S" + this.contadorCompania;
        }
    }
    */
    
    public void aumentarPrioridad() {
        if (this.getContador() == 8) {
            if (this.getTipo() != 1) { // tipo es lo mismo que prioridad
                this.setContador(0);
                this.setTipo(this.getTipo() + 1);
            }
        }
    }
    
    public void determinarTipo() {
        int contadorCalidad = 0;
        
        Random random = new Random();
        
        int randHabilidades = random.nextInt(100);
        int randVida = random.nextInt(100);
        int randFuerza = random.nextInt(100);
        int randAgilidad = random.nextInt(100);
        
        if (randHabilidades < 60) {
            this.getPropiedades().setCalidadHabs(1);
            contadorCalidad++;
        }
        
        if (randVida < 70) {
            this.getPropiedades().setCalidadVida(1);
            contadorCalidad++;
        }
        
        if (randFuerza < 50) {
            this.getPropiedades().setCalidadFuerza(1);
            contadorCalidad++;
        }
        
        if (randAgilidad < 40) {
            this.getPropiedades().setCalidadAgilidad(1);
            contadorCalidad++;
        }
        
        if (contadorCalidad >= 3) {
            this.setTipo(1);
        } else if (contadorCalidad == 2){
            this.setTipo(2);
        } else {
            this.setTipo(3);
        }
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the imagen
     */
    public int getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the propiedades
     */
    public Propiedades getPropiedades() {
        return propiedades;
    }

    /**
     * @param propiedades the propiedades to set
     */
    public void setPropiedades(Propiedades propiedades) {
        this.propiedades = propiedades;
    }

    /**
     * @return the contador
     */
    public int getContador() {
        return contador;
    }

    /**
     * @param contador the contador to set
     */
    public void setContador(int contador) {
        this.contador = contador;
    }

    
   
}
