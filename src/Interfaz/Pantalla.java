/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import Administrador.SistemaOperativo;
import Clases.Cola;
import Clases.Personaje;
import Clases.Propiedades;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.TOP;

/**
 *
 * @author luisa
 */
public class Pantalla extends javax.swing.JFrame {

    private static Personaje[] poolZelda = new Personaje[10];
    private static Personaje[] poolStreet = new Personaje[10];

    public static void labelCreation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    int velocidadPelea;
    private static Cola zColaP1;
    private static Cola zColaP2;
    private static Cola zColaP3;
    private static Cola zRefuerzo;
    private static Cola stColaP1;
    private static Cola stColaP2;
    private static Cola stColaP3;
    private static Cola stRefuerzo;

    private int cicloCont;

    public static volatile Semaphore zSemaforo;
    public static volatile Semaphore stSemaforo;

    public static Personaje zFigther;
    public static Personaje stFigther;

    public SistemaOperativo SO = new SistemaOperativo();

    /**
     * Creates new form Pantalla
     */
    public Pantalla() throws IOException, InterruptedException {

        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        initComponents();

        this.setCicloCont(0);

        Propiedades p1 = new Propiedades(4, 8, 7, 2);
        Propiedades p2 = new Propiedades(6, 7, 6, 4);
        Propiedades p3 = new Propiedades(7, 4, 7, 8);
        Propiedades p4 = new Propiedades(7, 3, 4, 8);
        Propiedades p5 = new Propiedades(5, 6, 4, 6);
        Propiedades p6 = new Propiedades(6, 4, 5, 8);
        Propiedades p7 = new Propiedades(7, 3, 6, 9);
        Propiedades p8 = new Propiedades(5, 7, 6, 4);
        Propiedades p9 = new Propiedades(6, 5, 7, 3);
        Propiedades p10 = new Propiedades(2, 6, 4, 7);
        Propiedades p11 = new Propiedades(3, 7, 8, 2);
        Propiedades p12 = new Propiedades(4, 6, 7, 5);
        Propiedades p13 = new Propiedades(8, 5, 5, 9);
        Propiedades p14 = new Propiedades(3, 10, 8, 1);
        Propiedades p15 = new Propiedades(2, 5, 7, 4);
        Propiedades p16 = new Propiedades(3, 4, 6, 10);
        Propiedades p17 = new Propiedades(4, 5, 7, 4);
        Propiedades p18 = new Propiedades(6, 6, 5, 4);
        Propiedades p19 = new Propiedades(4, 4, 7, 4);
        Propiedades p20 = new Propiedades(2, 6, 9, 2);

        poolZelda[0] = new Personaje("z1", "Daruk", p1);
        poolZelda[1] = new Personaje("z2", "Ganondorf", p2);
        poolZelda[2] = new Personaje("z3", "Link", p3);
        poolZelda[3] = new Personaje("z4", "Midna", p4);
        poolZelda[4] = new Personaje("z5", "Mipha", p5);
        poolZelda[5] = new Personaje("z6", "Revali", p6);
        poolZelda[6] = new Personaje("z7", "Sheik", p7);
        poolZelda[7] = new Personaje("z8", "Skull Kid", p8);
        poolZelda[8] = new Personaje("z9", "Urbosa", p9);
        poolZelda[9] = new Personaje("z10", "Zelda", p10);

        poolStreet[0] = new Personaje("s1", "Blanka", p11);
        poolStreet[1] = new Personaje("s2", "Chun-Li", p12);
        poolStreet[2] = new Personaje("s3", "Dhalsim", p13);
        poolStreet[3] = new Personaje("s4", "Edmond Honda", p14);
        poolStreet[4] = new Personaje("s5", "Guile", p15);
        poolStreet[5] = new Personaje("s6", "Juri Han", p16);
        poolStreet[6] = new Personaje("s7", "Ken", p17);
        poolStreet[7] = new Personaje("s8", "Manon", p18);
        poolStreet[8] = new Personaje("s9", "Ryu", p19);
        poolStreet[9] = new Personaje("s10", "Zangief", p20);

        for (int i = 0; i < 10; i++) {
            poolZelda[i].determinarTipo();
            poolStreet[i].determinarTipo();
        }

        for (int i = 0; i < 10; i++) {
            poolZelda[i].imprimirInfo();
        }

        for (int i = 0; i < 10; i++) {
            poolStreet[i].imprimirInfo();
        }

        zColaP1 = new Cola(1);
        zColaP2 = new Cola(2);
        zColaP3 = new Cola(3);
        zRefuerzo = new Cola(4);
        stColaP1 = new Cola(1);
        stColaP2 = new Cola(2);
        stColaP3 = new Cola(3);
        stRefuerzo = new Cola(4);
        zSemaforo = new Semaphore(1);
        stSemaforo = new Semaphore(1);

        velocidadPelea = velocidad.getValue();
        
        this.llenarColas();

        while (true) {
            this.setCicloCont(this.getCicloCont() + 1);
            this.meterEnBatalla();
            
            System.out.println(velocidad.getValue());
            
            if (this.getCicloCont() == 2) {
                Random r = new Random();
                int decision = r.nextInt(101);
                if (decision <= 80) {
                    SO.agregarPersonaje(poolZelda, zColaP1, zColaP2, zColaP3, zPanel1, zPanel2, zPanel3);
                    SO.agregarPersonaje(poolStreet, stColaP1, stColaP2, stColaP3, stPanel1, stPanel2, stPanel3);
                }
                this.setCicloCont(0);
            }
            do {} while (this.velocidadPelea == 0);
            sleep(100000 / this.velocidadPelea);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        stFigtherLabel = new javax.swing.JLabel();
        zFighterLabel = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        stPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        stPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        stPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        stPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        zPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        zPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        zPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        zPanel1 = new javax.swing.JPanel();
        meter = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        revisar = new javax.swing.JButton();
        aumentar = new javax.swing.JButton();
        velocidad = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        stFigtherLabel.setText("stFigther");
        stFigtherLabel.setToolTipText("");
        stFigtherLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        stFigtherLabel.setFocusable(false);
        stFigtherLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stFigtherLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(stFigtherLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 300, 180, 160));

        zFighterLabel.setText("zFigther");
        zFighterLabel.setToolTipText("");
        zFighterLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        zFighterLabel.setFocusable(false);
        zFighterLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        zFighterLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(zFighterLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, 180, 160));

        stPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane8.setViewportView(stPanel4);

        jPanel1.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 540, 290, 110));

        stPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane6.setViewportView(stPanel2);

        jPanel1.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 200, 290, 110));

        stPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane7.setViewportView(stPanel3);

        jPanel1.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 370, 290, 110));

        stPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane5.setViewportView(stPanel1);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 20, 290, 110));

        zPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane4.setViewportView(zPanel4);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 690, 290, 100));

        zPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(zPanel3);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 290, 110));

        zPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(zPanel2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 290, 110));

        zPanel1.setBackground(new java.awt.Color(0, 0, 0));
        zPanel1.setForeground(new java.awt.Color(0, 0, 0));
        zPanel1.setLayout(new java.awt.GridLayout(1, 0, 5, 0));
        jScrollPane1.setViewportView(zPanel1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 290, 100));

        meter.setText("meter en batalla");
        meter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meterActionPerformed(evt);
            }
        });
        jPanel1.add(meter, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 120, -1));

        borrar.setText("borrar");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        jPanel1.add(borrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, -1, -1));

        revisar.setText("revisar colas");
        revisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revisarActionPerformed(evt);
            }
        });
        jPanel1.add(revisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, -1));

        aumentar.setText("aumentar priodidad");
        aumentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aumentarActionPerformed(evt);
            }
        });
        jPanel1.add(aumentar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, -1, -1));

        velocidad.setMajorTickSpacing(50);
        velocidad.setMinorTickSpacing(10);
        velocidad.setPaintTicks(true);
        velocidad.setOpaque(false);
        velocidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                velocidadStateChanged(evt);
            }
        });
        jPanel1.add(velocidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 730, 450, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("1");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 770, 20, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("0");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 770, 40, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("2");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 770, 20, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesInterfaz/pantalla_fondo.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 860));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 860));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void velocidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_velocidadStateChanged
        velocidadPelea = velocidad.getValue();
        System.out.println(velocidadPelea);
    }//GEN-LAST:event_velocidadStateChanged

    //Crea las labels que van a irse agregando a las colas que se muestran en interfaz
    public static void labelCreation(Personaje personaje, JPanel p) {
        String texto = personaje.getId();
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setSize(60, 60);
        etiqueta.setForeground(Color.white);
        etiqueta.setVerticalTextPosition(1);
        etiqueta.setVerticalAlignment(TOP);
        etiqueta.setHorizontalTextPosition(CENTER);
        etiqueta.setIconTextGap(3);
        etiqueta.setVerticalTextPosition(TOP);
        ImageIcon fot = new ImageIcon(personaje.getRutaIcon());
        ImageIcon icon = new ImageIcon(fot.getImage().getScaledInstance(etiqueta.getWidth(), etiqueta.getHeight(), Image.SCALE_DEFAULT));

        etiqueta.setIcon(icon);
        etiqueta.repaint();

        p.add(etiqueta);
        p.updateUI();
    }

    //LLena las colas al inicio de la simulacion
    public void llenarColas() {
        SO.llenarColas(poolZelda, zColaP1, zColaP2, zColaP3, zPanel1, zPanel2, zPanel3);
        SO.llenarColas(poolStreet, stColaP1, stColaP2, stColaP3, stPanel1, stPanel2, stPanel3);
    }

    //Monta los personajes en Las variables peleador 
    public static void pelea(Personaje p, JLabel l) {

        ImageIcon fot = new ImageIcon(p.getRutaImagen());
        ImageIcon icon = new ImageIcon(fot.getImage().getScaledInstance(l.getWidth(), l.getHeight(), Image.SCALE_DEFAULT));

        l.setIcon(icon);
        l.repaint();

    }

    public void meterEnBatalla() {
        try {
            this.zFigther = SO.escogerPersonajes(zColaP1, zColaP2, zColaP3, zSemaforo, zPanel1, zPanel2, zPanel3);
            this.stFigther = SO.escogerPersonajes(stColaP1, stColaP2, stColaP3, stSemaforo, stPanel1, stPanel2, stPanel3);
            this.pelea(zFigther, zFighterLabel);
            this.pelea(stFigther, stFigtherLabel);
        } catch (InterruptedException ex) {
            Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Aquie en adelante son botones para probar como se ven las colas, como se actualizan y esas cosas
    private void meterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meterActionPerformed
        try {
            this.zFigther = SO.escogerPersonajes(zColaP1, zColaP2, zColaP3, zSemaforo, zPanel1, zPanel2, zPanel3);
            this.stFigther = SO.escogerPersonajes(stColaP1, stColaP2, stColaP3, stSemaforo, stPanel1, stPanel2, stPanel3);
            this.pelea(zFigther, zFighterLabel);
            this.pelea(stFigther, stFigtherLabel);
            this.setCicloCont(this.getCicloCont() + 1);
            if (this.getCicloCont() == 2) {
                Random r = new Random();
                int decision = r.nextInt(101);
                if (decision <= 80) {
                    SO.agregarPersonaje(poolZelda, zColaP1, zColaP2, zColaP3, zPanel1, zPanel2, zPanel3);
                    SO.agregarPersonaje(poolStreet, stColaP1, stColaP2, stColaP3, stPanel1, stPanel2, stPanel3);
                }
                this.setCicloCont(0);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_meterActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        // TODO add your handling code here:
        this.llenarColas();
        System.out.println(zColaP1.imprimirCola());
    }//GEN-LAST:event_borrarActionPerformed

    private void aumentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aumentarActionPerformed
        // TODO add your handling code here:
        SO.actualizarColas();
    }//GEN-LAST:event_aumentarActionPerformed

    private void revisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revisarActionPerformed
        // TODO add your handling code here:
        try {
            SO.revisarColas(zColaP1, zColaP2, zColaP3, zColaP3, zSemaforo, zPanel1, zPanel2, zPanel3);
            SO.revisarColas(zColaP1, zColaP2, zColaP3, zColaP2, zSemaforo, zPanel1, zPanel2, zPanel3);
            SO.revisarColas(stColaP1, stColaP2, stColaP3, stColaP2, stSemaforo, stPanel1, stPanel2, stPanel3);
            SO.revisarColas(stColaP1, stColaP2, stColaP3, stColaP3, stSemaforo, stPanel1, stPanel2, stPanel3);
        } catch (InterruptedException ex) {
            Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_revisarActionPerformed
    /**
     * @return the poolZelda
     */
    public static Personaje[] getPoolZelda() {
        return poolZelda;

    }

    /**
     * @param poolZelda the poolZelda to set
     */
    public void setPoolZelda(Personaje[] poolZelda) {
        this.poolZelda = poolZelda;
    }

    /**
     * @return the poolStreet
     */
    public static Personaje[] getPoolStreet() {
        return poolStreet;
    }

    /**
     * @param poolStreet the poolStreet to set
     */
    public void setPoolStreet(Personaje[] poolStreet) {
        this.poolStreet = poolStreet;
    }

    /**
     * @return the zColaP1
     */
    public static Cola getzColaP1() {
        return zColaP1;
    }

    /**
     * @param azColaP1 the zColaP1 to set
     */
    public static void setzColaP1(Cola azColaP1) {
        zColaP1 = azColaP1;
    }

    /**
     * @return the zColaP2
     */
    public static Cola getzColaP2() {
        return zColaP2;
    }

    /**
     * @param azColaP2 the zColaP2 to set
     */
    public static void setzColaP2(Cola azColaP2) {
        zColaP2 = azColaP2;
    }

    /**
     * @return the zColaP3
     */
    public static Cola getzColaP3() {
        return zColaP3;
    }

    /**
     * @param azColaP3 the zColaP3 to set
     */
    public static void setzColaP3(Cola azColaP3) {
        zColaP3 = azColaP3;
    }

    /**
     * @return the zRefuerzo
     */
    public static Cola getzRefuerzo() {
        return zRefuerzo;
    }

    /**
     * @param azRefuerzo the zRefuerzo to set
     */
    public static void setzRefuerzo(Cola azRefuerzo) {
        zRefuerzo = azRefuerzo;
    }

    /**
     * @return the stColaP1
     */
    public static Cola getStColaP1() {
        return stColaP1;
    }

    /**
     * @param aStColaP1 the stColaP1 to set
     */
    public static void setStColaP1(Cola aStColaP1) {
        stColaP1 = aStColaP1;
    }

    /**
     * @return the stColaP2
     */
    public static Cola getStColaP2() {
        return stColaP2;
    }

    /**
     * @param aStColaP2 the stColaP2 to set
     */
    public static void setStColaP2(Cola aStColaP2) {
        stColaP2 = aStColaP2;
    }

    /**
     * @return the stColaP3
     */
    public static Cola getStColaP3() {
        return stColaP3;
    }

    /**
     * @param aStColaP3 the stColaP3 to set
     */
    public static void setStColaP3(Cola aStColaP3) {
        stColaP3 = aStColaP3;
    }

    /**
     * @return the stRefuerzo
     */
    public static Cola getStRefuerzo() {
        return stRefuerzo;
    }

    /**
     * @param aStRefuerzo the stRefuerzo to set
     */
    public static void setStRefuerzo(Cola aStRefuerzo) {
        stRefuerzo = aStRefuerzo;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    try {
                        new Pantalla().setVisible(true);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aumentar;
    private javax.swing.JButton borrar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JButton meter;
    private javax.swing.JButton revisar;
    public static javax.swing.JLabel stFigtherLabel;
    public static javax.swing.JPanel stPanel1;
    public static javax.swing.JPanel stPanel2;
    public static javax.swing.JPanel stPanel3;
    public static javax.swing.JPanel stPanel4;
    private javax.swing.JSlider velocidad;
    public static javax.swing.JLabel zFighterLabel;
    public static javax.swing.JPanel zPanel1;
    public static javax.swing.JPanel zPanel2;
    public static javax.swing.JPanel zPanel3;
    public static javax.swing.JPanel zPanel4;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the cicloCont
     */
    public int getCicloCont() {
        return cicloCont;
    }

    /**
     * @param cicloCont the cicloCont to set
     */
    public void setCicloCont(int cicloCont) {
        this.cicloCont = cicloCont;
    }
}
