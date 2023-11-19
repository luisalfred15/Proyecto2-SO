/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Clases;


import Administrador.SistemaOperativo;
import InteligenciaArtificial.Procesador;
import java.io.File;

import Interfaz.Pantalla;

import java.io.IOException;
import java.util.concurrent.Semaphore;

/**
 *
 * @author luisa
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws IOException, InterruptedException {
        Propiedades p = new Propiedades(1,1,1,1);
        Propiedades p2 = new Propiedades(1,1,1,1);
        Propiedades p3 = new Propiedades(1,1,1,1);
        Personaje link = new Personaje("z1", "Link", p);
        Personaje zelda = new Personaje("z2", "Zelda", p2);
        Personaje ganon = new Personaje("z3", "Ganondorf", p3);
        
        
        link.setContador(8);
        zelda.setContador(8);
        ganon.setContador(8);
        
//        link.setTipo(3);
//        zelda.setTipo(3);
//        ganon.setTipo(2);
        
//        link.aumentarPrioridad();
//        zelda.aumentarPrioridad();
//        ganon.aumentarPrioridad();
        link.determinarTipo();
        zelda.determinarTipo();
        ganon.determinarTipo();
        link.imprimirInfo();
        zelda.imprimirInfo();
        ganon.imprimirInfo();
        System.out.println(link.getTipo());
        System.out.println(zelda.getTipo());
        System.out.println(ganon.getTipo());
//        Semaphore semaforo= new Semaphore(1);
//        SistemaOperativo OS = new SistemaOperativo();
//        Cola p1= new Cola(1);
//        Cola p2 = new Cola(2);
//        Cola p3 = new Cola(3);
//        
//        p2.encolar(ganon);
//        p3.encolar(link);
//        p3.encolar(zelda);
//        
//        System.out.println(p1.imprimirCola());
//        System.out.println(p2.imprimirCola());
//        System.out.println(p3.imprimirCola());
//        
//        OS.revisarColas(p1, p2, p3, p3, semaforo);
//        
//
//
//
//        System.out.println(p1.imprimirCola());
//        System.out.println(p2.imprimirCola());
//        System.out.println(p3.imprimirCola());

//        Pantalla pantalla = new Pantalla();
//        OS.llenarColas();
            

        Procesador IA= new Procesador();
        IA.setStFigther(ganon);
        IA.setzFigther(link);
        IA.eleccion();
        
    }  
    
}
