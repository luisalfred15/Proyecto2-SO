package Clases;

import Clases.Personaje;
import Interfaz.Pantalla;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Personaje winner;
    private String ganador;
    private int aviso = 0;
    private int aviso2 = 0;
    private String logBattle = "";

    public Procesador() {
        this.estado = "Esperando";
        this.turno = false;

    }

    @Override
    public void run() {
        while (true) {
            if (isTurno() == true) {
                try {
                    setLogBattle(getLogBattle() + "---------------------------------------------\n");
                    this.setEstado("Decidiendo");
                    Pantalla.estadoIA.setText(getEstado());
                    this.insertarPeleadores();
                    this.eleccion();
                    while (Pantalla.velocidad.getValue() == 0) {
                    }
                    sleep(100000 / Pantalla.velocidad.getValue());
                    this.setEstado("Decidiendo resultado");

                    if (getAviso() == 0) {

                    } else {
                        Pantalla.labelCreation(getWinner(), Pantalla.ganadores);
                    }

                    if (getAviso2() != 0) {
                        Pantalla.getzRefuerzo().encolar(this.getzFigther());
                        Pantalla.labelCreation(getzFigther(), Pantalla.zPanel4);
                        Pantalla.getStRefuerzo().encolar(this.getStFigther());
                        Pantalla.labelCreation(getStFigther(), Pantalla.stPanel4);
                        this.setAviso2(0);
                    }

                    Pantalla.contVictoriasZ.setText(Integer.toString(Pantalla.zWins));
                    Pantalla.contVictoriasSt.setText(Integer.toString(Pantalla.stWins));
                    Pantalla.logBatalla.setText(getLogBattle());
                    Pantalla.estadoIA.setText(getEstado());
                    Pantalla.resultado.setText(getResultado());
                    Pantalla.SO.setTurno(true);
                    this.setTurno(false);

                } catch (InterruptedException ex) {
                    Logger.getLogger(Procesador.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                this.setEstado("Esperando");
                Pantalla.estadoIA.setText(getEstado());
            }
        }
    }

    public void insertarPeleadores() {
        try {
            this.setzFigther(Pantalla.zFigther);
            this.setStFigther(Pantalla.stFigther);
        } catch (Exception err) {
        }
    }

    public void eleccion() {
        Random random = new Random();
        float posibilidad = random.nextInt(10) + random.nextFloat();
        if (posibilidad <= 2.7) {
            //Empate
            this.empate();
            setAviso(0);
        } else if (posibilidad <= 3.3) {
            //No combate
            this.noCombate();
            setAviso(0);
        } else {
            //Combate
            setWinner(this.combate());
            this.setResultado(getGanador() + " ganó");
            setAviso(1);
        }
    }

    public Personaje combate() {

        try {
            boolean par = true;
            setLogBattle(getLogBattle() + "Inicia la batalla\n");
            while (par == true) {
                setLogBattle(getLogBattle() + "Turno: " + getzFigther().getNombre() + "\n");
                this.defensa(getzFigther(), getStFigther());
                this.actualizarBarra();
                if (getStFigther().getPropiedades().getPuntosVida() <= 0) {
                    setLogBattle(getLogBattle() + "Perdedor: " + getStFigther().getNombre() + "\n" + "Ganador: " + getzFigther().getNombre() + "\n");
                    Pantalla.zWins += 1;
                    this.setGanador(this.getzFigther().getNombre());
                    return getzFigther();
                } else if (getzFigther().getPropiedades().getPuntosVida() <= 0) {
                    Pantalla.stWins += 1;
                    setLogBattle(getLogBattle() + "Perdedor: " + getzFigther().getNombre() + "\n" + "Ganador: " + getStFigther().getNombre() + "\n");
                    this.setGanador(this.getStFigther().getNombre());
                    return getStFigther();
                }

                this.defensa(getStFigther(), getzFigther());
                this.actualizarBarra();
                if (getzFigther().getPropiedades().getPuntosVida() <= 0) {
                    setLogBattle(getLogBattle() + "Perdedor: " + getzFigther().getNombre() + "\n" + "Ganador: " + getStFigther().getNombre() + "\n");
                    Pantalla.stWins += 1;
                    this.setGanador(this.getStFigther().getNombre());
                    return getStFigther();
                } else if (getStFigther().getPropiedades().getPuntosVida() <= 0) {
                    Pantalla.zWins += 1;
                    setLogBattle(getLogBattle() + "Perdedor: " + getStFigther().getNombre() + "\n" + "Ganador: " + getzFigther().getNombre() + "\n");
                    this.setGanador(this.getzFigther().getNombre());
                    return getzFigther();
                }
            }
        } catch (Exception err) {

        }
        return null;
    }

    public void actualizarBarra() {
        Pantalla.bar1.setValue(this.getzFigther().getPropiedades().getPuntosVida());
        Pantalla.bar2.setValue(this.getStFigther().getPropiedades().getPuntosVida());
    }

    public void defensa(Personaje atacante, Personaje defensor) {

        Random rand = new Random();
        int r = rand.nextInt(3);

        int zHab = atacante.getPropiedades().getHabilidades();
        int zHP = atacante.getPropiedades().getPuntosVida();
        int zAgi = atacante.getPropiedades().getAgilidad();
        int zFuerza = atacante.getPropiedades().getFuerza();

        int stHab = defensor.getPropiedades().getHabilidades();
        int stHP = defensor.getPropiedades().getPuntosVida();
        int stAgi = defensor.getPropiedades().getAgilidad();
        int stFuerza = defensor.getPropiedades().getFuerza();

        try {

            //Ataque 1 De Zfighter a stFighter
            if (r == 0) {
                setLogBattle(getLogBattle() + atacante.getNombre() + " dio un " + " golpe directo" + "\n");
                defensor.getPropiedades().setPuntosVida(defensor.getPropiedades().getPuntosVida() - zFuerza);

            } else if (r == 1) {
                //Agilidad. Compara el valor de agilidad con un random, si el random es mayor que el valor de agilidad recibe un golpe directo
                int randAg = rand.nextInt(101);
                if (randAg > stAgi) {
                    //Golpe directo
                    setLogBattle(getLogBattle() + atacante.getNombre() + " dio un " + " golpe directo" + "\n");
                    defensor.getPropiedades().setPuntosVida(defensor.getPropiedades().getPuntosVida() - zFuerza);
                } else if (randAg == stAgi) {
                    //Bloquea el golpe 
                    setLogBattle(getLogBattle() + defensor.getNombre() + " bloqueo el ataque" + "\n");
                    stHP = stHP - 5;
                    zHP = zHP - 10;
                } else {
                    //Esquivo con exito
                    setLogBattle(getLogBattle() + defensor.getNombre() + " esquivo el ataque de " + atacante.getNombre() + "\n");
                }
            } else {
                //Habilidad. Compara el valor de habilidad con un random, si el random es mayor que el valor de habilidad recibe un golpe directo
                int randHab = rand.nextInt(101);
                if (randHab > stHab) {
                    setLogBattle(getLogBattle() + atacante.getNombre() + " dio un " + " golpe directo\n");
                    defensor.getPropiedades().setPuntosVida(defensor.getPropiedades().getPuntosVida() - zFuerza);
                } else if (randHab <= stHab) {
                    setLogBattle(getLogBattle() + defensor.getNombre() + " devolvio el ataque de " + atacante.getNombre() + "\n");
                    atacante.getPropiedades().setPuntosVida(atacante.getPropiedades().getPuntosVida() - zFuerza);
                }
            }

        } catch (Exception err) {

        }
    }

    public void empate() {
        try {
            setLogBattle(getLogBattle() + "Empate\n");
            Pantalla.getzColaP1().encolar(this.getzFigther());
            Pantalla.labelCreation(getzFigther(), Pantalla.zPanelP1);
            Pantalla.getStColaP1().encolar(this.getStFigther());
            Pantalla.labelCreation(getStFigther(), Pantalla.stPanel1);
            this.setResultado("Empate");
        } catch (Exception err) {

        }
    }

    public void noCombate() {
        try {
            setLogBattle(getLogBattle() + "No combate\n");
            this.setAviso2(1);

            this.setResultado("Peleadores débiles");
        } catch (Exception err) {

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

    /**
     * @return the winner
     */
    public Personaje getWinner() {
        return winner;
    }

    /**
     * @param winner the winner to set
     */
    public void setWinner(Personaje winner) {
        this.winner = winner;
    }

    /**
     * @return the aviso
     */
    public int getAviso() {
        return aviso;
    }

    /**
     * @param aviso the aviso to set
     */
    public void setAviso(int aviso) {
        this.aviso = aviso;
    }

    /**
     * @return the aviso2
     */
    public int getAviso2() {
        return aviso2;
    }

    /**
     * @param aviso2 the aviso2 to set
     */
    public void setAviso2(int aviso2) {
        this.aviso2 = aviso2;
    }

    /**
     * @return the logBattle
     */
    public String getLogBattle() {
        return logBattle;
    }

    /**
     * @param logBattle the logBattle to set
     */
    public void setLogBattle(String logBattle) {
        this.logBattle = logBattle;
    }
}
