
package InteligenciaArtificial;

import Clases.Personaje;
import Interfaz.Pantalla;
import static java.lang.Math.random;

import java.util.Random;

/**
 *
 * @author raco1
 */
public class Procesador extends Thread {
    
    private String estado; //Esperando, deciciendo, anunciando resultado
    private Personaje zFigther;
    private Personaje stFigther;
    private boolean turno;
    private int velocidad;
    private String resultado;
    public Procesador(){
        this.estado="Esperando";
        this.turno=false;
        
    }
    
    @Override
    public void run(){
        
    }
    
    
    public void insertarPeleadores(){
        try{
            this.setzFigther(Pantalla.zFigther);
            this.setStFigther(Pantalla.stFigther);
        }catch(Exception err){
            System.out.println("Error insertar peleadores");
        }
    }
    
    
    public void eleccion(){
        Random random= new Random();
        float posibilidad=random.nextInt(10)+ random.nextFloat();
        System.out.println(posibilidad);
        if(posibilidad<=2.7){
            //Empate
            this.empate();
            
        }else if (posibilidad<=3.3){
            //No combate
            this.noCombate();
            
        }else {
            //Combate
            this.combate();
        }
    }
    
    public void combate(){
        System.out.println("Combate");
        
        try{
            
        }catch(Exception err){
            
        }
    }
    public void empate(){
        System.out.println("Empate");
         try{
            Pantalla.getzColaP1().encolar(this.zFigther);            
            Pantalla.labelCreation(zFigther, Pantalla.zPanelP1);
            Pantalla.getStColaP1().encolar(this.stFigther);            
            Pantalla.labelCreation(stFigther, Pantalla.stPanel1);
            this.setResultado("Empate");
        }catch(Exception err){
            
        }
    }
    
    public void noCombate(){
        System.out.println("No combate");
         try{
            Pantalla.getzRefuerzo().encolar(this.zFigther);            
            Pantalla.labelCreation(zFigther, Pantalla.zPanel4);
            Pantalla.getStRefuerzo().encolar(this.stFigther);            
            Pantalla.labelCreation(stFigther, Pantalla.stPanel4);
            this.setResultado("Peleadores muy debiles para pelear");
        }catch(Exception err){
            
        }
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the zFigther
     */
    public Personaje getzFigther() {
        return zFigther;
    }

    /**
     * @param zFigther the zFigther to set
     */
    public void setzFigther(Personaje zFigther) {
        this.zFigther = zFigther;
    }

    /**
     * @return the stFigther
     */
    public Personaje getStFigther() {
        return stFigther;
    }

    /**
     * @param stFigther the stFigther to set
     */
    public void setStFigther(Personaje stFigther) {
        this.stFigther = stFigther;
    }

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

    /**
     * @return the velocidad
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * @param velocidad the velocidad to set
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * @return the resultado
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
