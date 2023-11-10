

package Administrador;

import Clases.Cola;
import Clases.Personaje;
import java.io.IOException;
import java.util.concurrent.Semaphore;

/**
 *
 * @author raco1
 */
public class SistemaOperativo extends Thread {

    private Personaje fighterZ;
    private Personaje figtherST;
//    private Cola zColaP1;
//    private Cola zColaP2;
//    private Cola zColaP3;
//    private Cola zRefuerzo;
//    private Cola stColaP1;
//    private Cola stColaP2;
//    private Cola stColaP3;
//    private Cola stRefuerzo;
    int cicloCont;
    Semaphore zSemaforo;
    Semaphore stSemaforo;
    boolean turno;
//    public SistemaOperativo(Cola zCola1, Cola zCola2, Cola zCola3, Cola zRefuerzo, Cola stCola1, Cola stCola2, Cola stCola3, Cola stRefuerzo){
//        this.zColaP1=zCola1;
//        this.zColaP2=zCola2;
//        this.zColaP3= zCola3;
//        this.zRefuerzo=zRefuerzo;
//    }
    public SistemaOperativo(){
        this.cicloCont=0;
        this.turno=false;
    }
    
    public void actualizarColas(){
        //Aqui vamos a generar en tiempo real las colas en la interfaz
    }
    //Recibe la cola desde donde va a recibir el personaje 
    public void revisarColas(Cola p1, Cola p2, Cola p3, Cola revisada, Semaphore semaforo) throws InterruptedException{
        try{
            
        while(true){
            Personaje aux=revisada.getNodoCabeza().getDatos();
           
                if( revisada.getTipo()==3){
                    if(aux.getTipo()==2){
                        semaforo.acquire(1);
                        revisada.desencolar();
                        p2.encolar(aux);
                        semaforo.release();
                    }else{
                        break;
                    }
                }else if(revisada.getTipo()==2){
                    if(aux.getTipo()==1){
                        semaforo.acquire(1);
                        revisada.desencolar();
                        p1.encolar(aux);
                        semaforo.release();
                    }else{
                        break;
                    }
                }
            }
        } catch(Exception err){
            System.out.println("error");
        }
    }
    //Saca un personaje de su cola de prioridad, si no hay elementos en una cola pasa a la siguiente. Si no hay elementos en ninguna cola pasa al protocolo de emergencia
    public Personaje escogerPersonajes(Cola P1, Cola P2, Cola P3, Semaphore Semaforo) throws InterruptedException{
        try{
            
        Personaje aux= P1.getNodoCabeza().getDatos();
        if(!P1.esVacia()){
            aux=P1.getNodoCabeza().getDatos();
            Semaforo.acquire(1);
            P1.desencolar();
            Semaforo.release();
        }else if(!P2.esVacia()){
            aux=P2.getNodoCabeza().getDatos();
            Semaforo.acquire(1);
            P2.desencolar();
            Semaforo.release();
        }else if(!P3.esVacia()){
            aux=P3.getNodoCabeza().getDatos();
            Semaforo.acquire(1);
            P3.desencolar();
            Semaforo.release();
        }else{
            System.out.println("No hay personajes disponibles, insertar 10 mas");
        }
        return aux;
        }catch(Exception err){
            return null;
    }
   }  
    //Agregar personajes cada dos ciclos de revision
    public void agregarPersonaje(){
        
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
    
    
}
