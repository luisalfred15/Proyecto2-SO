/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Clases;

import java.io.File;
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
        Propiedades p = new Propiedades(1,1,1,1);
        Personaje link = new Personaje("z1", "Link", p);
        Personaje zelda = new Personaje("z2", "Zelda", p);
        Personaje ganon = new Personaje("z3", "Ganondorf", p);
        
        link.determinarTipo();
        zelda.determinarTipo();
        ganon.determinarTipo();
        link.imprimirInfo();
        zelda.imprimirInfo();
        ganon.imprimirInfo();
    }
    
}
