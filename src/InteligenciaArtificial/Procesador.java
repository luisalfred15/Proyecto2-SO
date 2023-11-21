
package InteligenciaArtificial;

import Clases.Personaje;
import Interfaz.Pantalla;
import static java.lang.Math.random;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    Personaje winner;
    private String ganador;
    int  aviso=0;
    int aviso2=0;
    String logBattle="";
    public Procesador(){
        this.estado="Esperando";
        this.turno=false;
        
    }
    
    @Override
    public void run(){
        while(true){
            if(turno==true){
                try {
                    logBattle+="---------------------------------------------\n";
                    this.setEstado("Decidiendo");
                    Pantalla.estadoIA.setText(estado);
                    this.insertarPeleadores();
                    this.eleccion();
                    sleep(Pantalla.velocidad.getValue());
                    this.setEstado("Decidienco resultado");
                    
                    if(aviso==0){
                        
                    }else{
                        Pantalla.labelCreation(winner, Pantalla.ganadores);
                    }
                    if(aviso2!=0){
                        Pantalla.getzRefuerzo().encolar(this.zFigther);            
                        Pantalla.labelCreation(zFigther, Pantalla.zPanel4);
                        Pantalla.getStRefuerzo().encolar(this.stFigther);            
                        Pantalla.labelCreation(stFigther, Pantalla.stPanel4);
                        this.aviso2=0;
                    }
                    Pantalla.contVictoriasZ.setText(Integer.toString(Pantalla.zWins));
                    Pantalla.contVictoriasSt.setText(Integer.toString(Pantalla.stWins));
                    Pantalla.logBatalla.setText(logBattle);
                    Pantalla.estadoIA.setText(estado);
                    Pantalla.resultado.setText(resultado);
                    Pantalla.SO.setTurno(true);
                    this.turno=false;
                    
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Procesador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                this.setEstado("Esperando");
                Pantalla.estadoIA.setText(estado);
            }
        }
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
            this.setResultado("Empate");
            aviso=0;
        }else if (posibilidad<=3.3){
            //No combate
            System.out.println("Hola");
            this.noCombate();
//            this.setResultado("No Combate");
            aviso=0;
        }else {
            //Combate
            winner=this.combate();
            this.setResultado(ganador);
            aviso=1;
        }
        Pantalla.contador+=1;
        Pantalla.cont1.setText(Integer.toString(Pantalla.contador));
    }
    public Personaje combate(){
        this.zFigther.imprimirInfo();
        this.stFigther.imprimirInfo();
        
        try {
            boolean par=true;
            logBattle+="Inicia la batalla\n";
            while (par==true){
                
                this.imprimirVida();
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<,,>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                System.out.println("Turno: "+zFigther.getNombre());
                logBattle+="Turno: "+zFigther.getNombre()+"\n";
                this.defensa(zFigther, stFigther);
                this.actualizarBarra();
                if(stFigther.getPropiedades().getPuntosVida()<=0){
                    System.out.println( "Perdedor: "+stFigther.getNombre());
                    System.out.println( "Ganador: " +zFigther.getNombre());
                    logBattle+="Perdedor: "+stFigther.getNombre()+"\n"+"Ganador: " +zFigther.getNombre()+"\n";
                    Pantalla.zWins+=1;
                    this.setGanador(this.zFigther.getNombre());
                    this.imprimirVida();
//                    Pantalla.labelCreation(zFigther, Pantalla.ganadores);
                    return zFigther;
//                    break;
                } else if(zFigther.getPropiedades().getPuntosVida()<=0){
                    System.out.println( "Perdedor: "+zFigther.getNombre());
                    System.out.println( "Ganador: " +stFigther.getNombre());
                    Pantalla.stWins+=1;
                    logBattle+="Perdedor: "+zFigther.getNombre()+"\n"+"Ganador: " +stFigther.getNombre()+"\n";
                    this.setGanador(this.stFigther.getNombre());
                    this.imprimirVida();
//                    Pantalla.labelCreation(stFigther, Pantalla.ganadores);
                    return stFigther;
//                    break;
                }
                
                
                this.imprimirVida();
                System.out.println("Turno: "+stFigther.getNombre());
                
                this.defensa(stFigther, zFigther);
                this.actualizarBarra();
                if(zFigther.getPropiedades().getPuntosVida()<=0){
                    System.out.println( "Perdedor: "+zFigther.getNombre());
                    System.out.println( "Ganador: " +stFigther.getNombre());
                    logBattle+="Perdedor: "+zFigther.getNombre()+"\n"+"Ganador: " +stFigther.getNombre()+"\n";
                    Pantalla.stWins+=1;
                    this.setGanador(this.stFigther.getNombre());
                    this.imprimirVida();
//                    Pantalla.labelCreation(stFigther, Pantalla.ganadores);
                    return stFigther;
//                    break;
                } else if(stFigther.getPropiedades().getPuntosVida()<=0){
                    System.out.println( "Perdedor: "+stFigther.getNombre());
                    System.out.println( "Ganador: " +zFigther.getNombre());
                    Pantalla.zWins+=1;
                    logBattle+="Perdedor: "+stFigther.getNombre()+"\n"+"Ganador: " +zFigther.getNombre()+"\n";
                    this.setGanador(this.zFigther.getNombre());
//                    Pantalla.labelCreation(zFigther, Pantalla.ganadores);
                   this.imprimirVida();
                    return zFigther;
//                    break;
                }
                 this.imprimirVida();
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<,,>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }
        } catch(Exception err){
            
        }
        return null;
    }
    public void imprimirVida(){
        System.out.println("Vida: " + zFigther.getNombre()+" " +zFigther.getPropiedades().getPuntosVida());
        System.out.println("Vida: " + stFigther.getNombre()+" " +stFigther.getPropiedades().getPuntosVida());
    }
    public void actualizarBarra(){
        Pantalla.bar1.setValue(this.zFigther.getPropiedades().getPuntosVida());
        Pantalla.bar2.setValue(this.stFigther.getPropiedades().getPuntosVida());
    }
    public void defensa(Personaje atacante,  Personaje defensor){
        
        
        Random rand= new Random();
        int r= rand.nextInt(3);
        
        int zHab=atacante.getPropiedades().getHabilidades();
        int zHP=atacante.getPropiedades().getPuntosVida();
        int zAgi=atacante.getPropiedades().getAgilidad();
        int zFuerza=atacante.getPropiedades().getFuerza();
        
        int stHab=defensor.getPropiedades().getHabilidades();
        int stHP=defensor.getPropiedades().getPuntosVida();
        int stAgi=defensor.getPropiedades().getAgilidad();
        int stFuerza=defensor.getPropiedades().getFuerza();
        
      
        
        try{
            
                //Ataque 1 De Zfighter a stFighter
                if(r==0){
                    System.out.println( atacante.getNombre()+" dio un " +" golpe directo");
                    logBattle+= atacante.getNombre()+" dio un " +" golpe directo"+"\n";
                    defensor.getPropiedades().setPuntosVida(defensor.getPropiedades().getPuntosVida()-zFuerza);
                    
                }else if(r==1){
                    //Agilidad. Compara el valor de agilidad con un random, si el random es mayor que el valor de agilidad recibe un golpe directo
                    int randAg= rand.nextInt(101);
                    if(randAg>stAgi){
                        //Golpe directo
                        System.out.println(atacante.getNombre()+" dio un " +" golpe directo");
                        logBattle+= atacante.getNombre()+" dio un " +" golpe directo"+"\n";
                        defensor.getPropiedades().setPuntosVida(defensor.getPropiedades().getPuntosVida()-zFuerza);
                    }else if(randAg==stAgi){
                        //Bloquea el golpe 
                        System.out.println(defensor.getNombre()+" bloqueo el ataque" );
                        logBattle+=defensor.getNombre()+" bloqueo el ataque"+"\n";
                        stHP= stHP-5;
                        zHP= zHP-10;
                    }else{
                        //Esquivo con exito
                        System.out.println( defensor.getNombre() + " Esquivo el ataque de " +atacante.getNombre());
                        logBattle+=defensor.getNombre() + " Esquivo el ataque de " +atacante.getNombre()+"\n";
                    }
                }else{
                    //Habilidad. Compara el valor de habilidad con un random, si el random es mayor que el valor de habilidad recibe un golpe directo
                    int randHab= rand.nextInt(101);
                    if (randHab>stHab ){
                        System.out.println(atacante.getNombre()+" dio un " +" golpe directo");
                        logBattle+=atacante.getNombre()+" dio un " +" golpe directo\n";
                        defensor.getPropiedades().setPuntosVida(defensor.getPropiedades().getPuntosVida()-zFuerza);
                    }else if(randHab<=stHab){
                        System.out.println(defensor.getNombre() + " Devolvio el ataque de " + atacante.getNombre());
                        logBattle+=defensor.getNombre() + " Devolvio el ataque de " + atacante.getNombre()+ "\n";
                        atacante.getPropiedades().setPuntosVida( atacante.getPropiedades().getPuntosVida()-zFuerza);
                    }
                }
            
        }catch(Exception err){
            
        }
    }
    public void empate(){
        System.out.println("Empate");
         try{
             logBattle+="Empate\n";
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
             logBattle+="No combate\n";
             this.aviso2=1;

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

    /**
     * @return the ganador
     */
    public String getGanador() {
        return ganador;
    }

    /**
     * @param ganador the ganador to set
     */
    public void setGanador(String ganador) {
        this.ganador = ganador;
    }
}
