/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Clases;

import java.io.IOException;

/**
 *
 * @author luisa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Propiedades p1 = new Propiedades(1, 1, 1, 1);
        Propiedades p2 = new Propiedades(2, 2, 2, 2);
        Propiedades p3 = new Propiedades(3, 3, 3, 3);
        
        
        Personaje link = new Personaje("z1", "Link", p1);
        Personaje zelda = new Personaje("z2", "Zelda", p2);
        Personaje ganon = new Personaje("z3", "Ganondorf", p3);
        
        link.determinarTipo();
        zelda.determinarTipo();
        ganon.determinarTipo();
        
        link.imprimirInfo();
        zelda.imprimirInfo();
        ganon.imprimirInfo();
    }
    
}
