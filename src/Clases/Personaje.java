/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author luisa
 */
public class Personaje {
    
    private String id;
    private String nombre;
    private BufferedImage imagen;
    private int tipo; // tipo puede ser excepcional (1), promedio (2) o deficiente (3)
    private Propiedades propiedades;
    private int contador;
    
    public Personaje(String id, String nombre, Propiedades propiedades) throws IOException {
        this.id = id;
        this.nombre = nombre;
        this.imagen = ImageIO.read(new File("src/ImagenesPersonajes/"+nombre + ".png"));
        this.propiedades = propiedades;
        this.contador = 0;
    }

    public void aumentarPrioridad() {
        if (this.getContador() == 8) {
            if (this.getTipo() != 1) { // tipo es lo mismo que prioridad
                this.setContador(0);
                this.setTipo(this.getTipo() - 1);
            }
        }
    }
    
    public void determinarTipo() {
        int contadorCalidad = 0;
        
        Random random = new Random();
        
        int randHabilidades = random.nextInt(101);
        int randVida = random.nextInt(101);
        int randFuerza = random.nextInt(101);
        int randAgilidad = random.nextInt(101);
        
        Propiedades props = this.getPropiedades();
        
        if (randHabilidades < 60) {
            // Si se selecciona que es de calidad, aumenta el contador y no se modifica la cantidad de la propiedad
            contadorCalidad++;
        } else {
            // Si no, se multiplica por -1
            props.setHabilidades(props.getHabilidades() * -1);
        }
        
        if (randVida < 70) {
            contadorCalidad++;
        } else {
            props.setPuntosVida(props.getPuntosVida() * -1);
        }
        
        if (randFuerza < 50) {
            contadorCalidad++;
        } else {
            props.setFuerza(props.getFuerza() * -1);
        }
        
        if (randAgilidad < 40) {
            contadorCalidad++;
        } else {
            props.setAgilidad(props.getAgilidad() * -1);
        }
        
        if (contadorCalidad >= 3) {
            this.setTipo(1);
        } else if (contadorCalidad == 2){
            this.setTipo(2);
        } else {
            this.setTipo(3);
        }
    }
    
    public void imprimirInfo() {
        System.out.println("Propiedades de " + this.nombre);
        
        Propiedades p = this.getPropiedades();
        
        if (p.getHabilidades() > 0) {
            System.out.println("Habilidades es de calidad");
        } else {
            System.out.println("Habilidades no es de calidad");
        }
        
        if (p.getPuntosVida() > 0) {
            System.out.println("Puntos de vida es de calidad");
        } else {
            System.out.println("Puntos de vida no es de calidad");
        }
        
        if (p.getFuerza() > 0) {
            System.out.println("Fuerza es de calidad");
        } else {
            System.out.println("Fuerza no es de calidad");
        }
        
        if (p.getAgilidad() > 0) {
            System.out.println("Agilidad es de calidad");
        } else {
            System.out.println("Agilidad no es de calidad");
        }
        
        System.out.println("Prioridad de " + this.getNombre() + " es " + this.getTipo());
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
    public BufferedImage getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(BufferedImage imagen) {
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
