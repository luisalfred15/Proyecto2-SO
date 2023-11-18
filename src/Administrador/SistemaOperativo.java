package Administrador;

import Clases.Cola;
import Clases.Personaje;
import Interfaz.Pantalla;
import static Interfaz.Pantalla.stPanel1;
import static Interfaz.Pantalla.stPanel2;
import static Interfaz.Pantalla.stPanel3;
import static Interfaz.Pantalla.zPanel2;
import static Interfaz.Pantalla.zPanel3;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static Interfaz.Pantalla.zPanel1;

/**
 *
 * @author raco1
 */
public class SistemaOperativo {

    private Personaje fighterZ;
    private Personaje figtherST;
    
    private boolean turno;

    public SistemaOperativo() {
        this.turno = false;
    }

    public void actualizarContadores() {
        //metodo de prueba para aumentar la prioridad de los personajes 
        Pantalla.getzColaP2().actualizarPersonajesCont();
        Pantalla.getzColaP3().actualizarPersonajesCont();
        Pantalla.getStColaP2().actualizarPersonajesCont();
        Pantalla.getStColaP3().actualizarPersonajesCont();
    }

    //Revisa las colas y las mete en otra lista si su prioridad cambio 
    public void actualizarColas(Cola p1, Cola p2, Cola p3, Cola revisada, JPanel zP1, JPanel zP2, JPanel zP3) throws InterruptedException {
        try {

            while (true) {
                Personaje aux = revisada.getNodoCabeza().getDatos();
                if (revisada.getTipo() == 3) {
                    if (aux.getTipo() == 2) {
                        revisada.desencolar();
                        p2.encolar(aux);
                        zP2.add(zP3.getComponent(0));
//                        zP3.remove(zP3.getComponent(0));
                        zP3.updateUI();
                        zP2.updateUI();
                    } else {
                        break;
                    }
                } else if (revisada.getTipo() == 2) {
                    if (aux.getTipo() == 1) {
                        revisada.desencolar();
                        p1.encolar(aux);
                        zP1.add(zP2.getComponent(0));
//                        zP2.remove(zP2.getComponent(0));
                        zP1.updateUI();
                        zP2.updateUI();
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception err) {
            System.out.println("error");
        }
    }

    //Saca un personaje de su cola de prioridad, si no hay elementos en una cola pasa a la siguiente. Si no hay elementos en ninguna cola pasa al protocolo de emergencia(no ha sido definido)
    public Personaje escogerPersonajes(Cola P1, Cola P2, Cola P3, JPanel P1UI, JPanel P2UI, JPanel P3UI) throws InterruptedException {
        try {
            Personaje aux = null;
            if (!P1.esVacia()) {
                aux = P1.getNodoCabeza().getDatos();
                P1.desencolar();
                P1UI.remove(P1UI.getComponent(0));
                P1UI.updateUI();
            } else if (!P2.esVacia()) {
                aux = P2.getNodoCabeza().getDatos();
                P2.desencolar();
                P2UI.remove(P2UI.getComponent(0));
                P2UI.updateUI();
            } else if (!P3.esVacia()) {
                aux = P3.getNodoCabeza().getDatos();
                P3.desencolar();
                P3UI.remove(P3UI.getComponent(0));
                P3UI.updateUI();
            } else {
                System.out.println("No hay personajes disponibles, insertar 10 mas");
            }
            return aux;
        } catch (Exception err) {
            return null;
        }
    }

    //Agregar personajes cada dos ciclos de revision
    public void agregarPersonaje(Personaje[] pool, Cola P1, Cola P2, Cola P3, JPanel P1UI, JPanel P2UI, JPanel P3UI) throws InterruptedException {
        Random r = new Random();

        int posicion = r.nextInt(10);

        Personaje elegido = pool[posicion];
        elegido.determinarTipo();

        if (elegido.getTipo() == 3) {
            P3.encolar(elegido);
            Pantalla.labelCreation(elegido, P3UI);
        } else if (elegido.getTipo() == 2) {
            P2.encolar(elegido);
            Pantalla.labelCreation(elegido, P2UI);
        } else {
            P1.encolar(elegido);
            Pantalla.labelCreation(elegido, P1UI);
        }

    }
    //metodo a ser ejecuto cuando inicie la simulacion. Metodo que iba a ser y termino no siendo  jeje      

    public void llenarColas(Personaje[] pool, Cola P1, Cola P2, Cola P3, JPanel P1UI, JPanel P2UI, JPanel P3UI) {
        for (Personaje p : pool) {
            if (p.getTipo() == 1) {
                P1.encolar(p);
                Pantalla.labelCreation(p, P1UI);
            } else if (p.getTipo() == 2) {
                P2.encolar(p);
                Pantalla.labelCreation(p, P2UI);
            } else if (p.getTipo() == 3) {
                P3.encolar(p);
                Pantalla.labelCreation(p, P3UI);
            }
        }
    }

    /**
     * @return the fighterZ
     */
    public Personaje getFighterZ() {
        return fighterZ;
    }

    /**
     * @param fighterZ the fighterZ to set
     */
    public void setFighterZ(Personaje fighterZ) {
        this.fighterZ = fighterZ;
    }

    /**
     * @return the figtherST
     */
    public Personaje getFigtherST() {
        return figtherST;
    }

    /**
     * @param figtherST the figtherST to set
     */
    public void setFigtherST(Personaje figtherST) {
        this.figtherST = figtherST;
    }

//    /**
//     * @return the zColaP1
//     */
//    public Cola getzColaP1() {
//        return zColaP1;
//    }
//
//    /**
//     * @param zColaP1 the zColaP1 to set
//     */
//    public void setzColaP1(Cola zColaP1) {
//        this.zColaP1 = zColaP1;
//    }
//
//    /**
//     * @return the zColaP2
//     */
//    public Cola getzColaP2() {
//        return zColaP2;
//    }
//
//    /**
//     * @param zColaP2 the zColaP2 to set
//     */
//    public void setzColaP2(Cola zColaP2) {
//        this.zColaP2 = zColaP2;
//    }
//
//    /**
//     * @return the zColaP3
//     */
//    public Cola getzColaP3() {
//        return zColaP3;
//    }
//
//    /**
//     * @param zColaP3 the zColaP3 to set
//     */
//    public void setzColaP3(Cola zColaP3) {
//        this.zColaP3 = zColaP3;
//    }
//
//    /**
//     * @return the zRefuerzo
//     */
//    public Cola getzRefuerzo() {
//        return zRefuerzo;
//    }
//
//    /**
//     * @param zRefuerzo the zRefuerzo to set
//     */
//    public void setzRefuerzo(Cola zRefuerzo) {
//        this.zRefuerzo = zRefuerzo;
//    }
//
//    /**
//     * @return the stColaP1
//     */
//    public Cola getStColaP1() {
//        return stColaP1;
//    }
//
//    /**
//     * @param stColaP1 the stColaP1 to set
//     */
//    public void setStColaP1(Cola stColaP1) {
//        this.stColaP1 = stColaP1;
//    }
//
//    /**
//     * @return the stColaP2
//     */
//    public Cola getStColaP2() {
//        return stColaP2;
//    }
//
//    /**
//     * @param stColaP2 the stColaP2 to set
//     */
//    public void setStColaP2(Cola stColaP2) {
//        this.stColaP2 = stColaP2;
//    }
//
//    /**
//     * @return the stColaP3
//     */
//    public Cola getStColaP3() {
//        return stColaP3;
//    }
//
//    /**
//     * @param stColaP3 the stColaP3 to set
//     */
//    public void setStColaP3(Cola stColaP3) {
//        this.stColaP3 = stColaP3;
//    }
//
//    /**
//     * @return the stRefuerzo
//     */
//    public Cola getStRefuerzo() {
//        return stRefuerzo;
//    }
//
//    /**
//     * @param stRefuerzo the stRefuerzo to set
//     */
//    public void setStRefuerzo(Cola stRefuerzo) {
//        this.stRefuerzo = stRefuerzo;
//    }
//    


    /**
     * @return the turno
     */
    public boolean isTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(boolean turno) {
        this.turno = turno;
    }
}
