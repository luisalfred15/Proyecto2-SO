/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author luisa
 */
public class Cola {
    
    private Nodo nodoCabeza;
    private Nodo nodoCola;
    private int longitud;
    private int tipo; //prioridad
    
    public Cola(int tipo) {
        this.nodoCabeza = null;
        this.nodoCola = null;
        this.longitud = 0;
        this.tipo=tipo;
    }
    
    public boolean esVacia() {
        return this.getNodoCola() == null;
    }
    
    public void vaciar() {
        this.setNodoCabeza(null);
        this.setNodoCola(null);
        this.setLongitud(0);
    }
    
    public void encolar(Personaje dato) {
        Nodo nodoNuevo = new Nodo(dato);
        if (this.esVacia()) {
            this.nodoCabeza = nodoNuevo;
            
        } else {
            this.nodoCola.setNodoSiguiente(nodoNuevo);
        }
        this.nodoCola = nodoNuevo;
        this.longitud++;
    }
    
    public void desencolar() {
        if (this.esVacia()) {
            System.out.println("La cola esta vacia.");
        } else if (this.longitud == 1) {
            this.vaciar();
//            this.longitud--;
        }else {
            this.nodoCabeza = this.nodoCabeza.getNodoSiguiente();
            this.longitud--;
        }
        
    }
    public Nodo desencolarValor() {
        Nodo aux= this.nodoCabeza;
        if (this.esVacia()) {
            System.out.println("La cola esta vacia.");
        } else if (this.longitud == 1) {
            this.vaciar();
            aux=null;
            this.longitud--;
        } else {
            aux=this.nodoCabeza;
            this.nodoCabeza = this.nodoCabeza.getNodoSiguiente();
            this.longitud--;
        }
        return aux;
    }
    
    public String imprimirCola() {
        if (!this.esVacia()) {
            String impresion = "";
            for (int i = 0; i < this.longitud; i++) {
                Nodo nodoActual = this.nodoCabeza;
                desencolar();
                impresion += nodoActual.getDatos().getNombre() + ", ";
                encolar(nodoActual.getDatos());
            }
            return impresion;
        }
        return "Vacia";
    }
    
    public String imprimirRecur(String impresion) {
        if (!this.esVacia()) {
            Nodo nodoAux = this.nodoCabeza;
            desencolar();
            impresion = imprimirRecur(impresion); 
            encolar(nodoAux.getDatos());
        }
        return impresion;
    }

    /**
     * @return the nodoCabeza
     */
    public Nodo getNodoCabeza() {
        return nodoCabeza;
    }

    /**
     * @param nodoCabeza the nodoCabeza to set
     */
    public void setNodoCabeza(Nodo nodoCabeza) {
        this.nodoCabeza = nodoCabeza;
    }

    /**
     * @return the nodoCola
     */
    public Nodo getNodoCola() {
        return nodoCola;
    }

    /**
     * @param nodoCola the nodoCola to set
     */
    public void setNodoCola(Nodo nodoCola) {
        this.nodoCola = nodoCola;
    }

    /**
     * @return the longitud
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(int longitud) {
        this.longitud = longitud;
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
    
}
