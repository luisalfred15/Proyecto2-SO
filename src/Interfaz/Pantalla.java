/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import Administrador.SistemaOperativo;
import Clases.Cola;
import Clases.Personaje;
import Clases.Propiedades;
import InteligenciaArtificial.Procesador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    
    public static volatile Semaphore zSemaforo;
    public static volatile Semaphore stSemaforo;
    
    public static Personaje zFigther;
    public static Personaje stFigther;
    
    public static SistemaOperativo SO= new SistemaOperativo();
    public static Procesador IA= new Procesador();
    
    public static int zWins=0;
    public static int stWins=0;
    
    public static int contador=0;
    
    
    
    /**
     * Creates new form Pantalla
     */
    public Pantalla() throws IOException {
      
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        initComponents();

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
        
        zColaP1= new Cola(1);
        zColaP2=new Cola(2);
        zColaP3=new Cola(3);
        zRefuerzo=new Cola(4);
        stColaP1=new Cola(1);
        stColaP2=new Cola(2);
        stColaP3= new Cola(3);
        stRefuerzo=new Cola(4);
        zSemaforo= new Semaphore(1);
        stSemaforo= new Semaphore(1);
        
        this.llenarColas();
        this.SO.setTurno(true);
        SO.start();
        IA.start();
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
        jScrollPane9 = new javax.swing.JScrollPane();
        ganadores = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        zPanelP1 = new javax.swing.JPanel();
        label1 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        label5 = new javax.swing.JLabel();
        label6 = new javax.swing.JLabel();
        label7 = new javax.swing.JLabel();
        label8 = new javax.swing.JLabel();
        bar1 = new javax.swing.JProgressBar();
        bar2 = new javax.swing.JProgressBar();
        contVictoriasSt = new javax.swing.JLabel();
        contVictoriasZ = new javax.swing.JLabel();
        velocidad = new javax.swing.JSlider();
        resultado = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        logBatalla = new javax.swing.JTextArea();
        estadoIA = new javax.swing.JLabel();
        cont1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        zInfo2 = new javax.swing.JLabel();
        zInfo3 = new javax.swing.JLabel();
        zInfo4 = new javax.swing.JLabel();
        stInfo1 = new javax.swing.JLabel();
        stInfo2 = new javax.swing.JLabel();
        stInfo3 = new javax.swing.JLabel();
        stInfo4 = new javax.swing.JLabel();
        zInfo1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        stFigtherLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stFigtherLabel.setText("stFigther");
        stFigtherLabel.setToolTipText("");
        stFigtherLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        stFigtherLabel.setFocusable(false);
        stFigtherLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stFigtherLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(stFigtherLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 290, 180, 160));

        zFighterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        zFighterLabel.setText("zFigther");
        zFighterLabel.setToolTipText("");
        zFighterLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        zFighterLabel.setFocusable(false);
        zFighterLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        zFighterLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(zFighterLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 180, 160));

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

        ganadores.setBackground(new java.awt.Color(0, 0, 0));
        ganadores.setForeground(new java.awt.Color(255, 255, 255));
        ganadores.setLayout(new java.awt.GridLayout(1, 0, 5, 0));
        jScrollPane9.setViewportView(ganadores);

        jPanel1.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 500, 100));

        zPanelP1.setBackground(new java.awt.Color(0, 0, 0));
        zPanelP1.setForeground(new java.awt.Color(0, 0, 0));
        zPanelP1.setLayout(new java.awt.GridLayout(1, 0, 5, 0));
        jScrollPane1.setViewportView(zPanelP1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 290, 100));

        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label1.setText(":Fuerza");
        label1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 500, 50, 30));

        label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label2.setText(":Habilidad");
        label2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 530, 60, 30));

        label3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label3.setText(":Agilidad");
        label3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 560, 80, 30));

        label4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label4.setText(": Vida");
        label4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 470, 40, 30));

        label5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label5.setText("Vida:");
        label5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(398, 470, 80, 30));

        label6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label6.setText("Fuerza:");
        label6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(label6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 500, 80, 30));

        label7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label7.setText("Habilidad: ");
        label7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(label7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 530, 80, 30));

        label8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label8.setText("Agilidad: ");
        label8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(label8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 560, 80, 30));

        bar1.setValue(50);
        jPanel1.add(bar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, -1, 30));

        bar2.setValue(50);
        jPanel1.add(bar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 240, -1, 30));

        contVictoriasSt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        contVictoriasSt.setForeground(new java.awt.Color(0, 0, 0));
        contVictoriasSt.setText("0");
        jPanel1.add(contVictoriasSt, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 660, 100, 40));

        contVictoriasZ.setBackground(new java.awt.Color(255, 255, 255));
        contVictoriasZ.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        contVictoriasZ.setForeground(new java.awt.Color(255, 255, 255));
        contVictoriasZ.setText("0");
        jPanel1.add(contVictoriasZ, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 660, 100, 40));

        velocidad.setMajorTickSpacing(1000);
        velocidad.setMaximum(10000);
        velocidad.setMinimum(1000);
        velocidad.setMinorTickSpacing(100);
        velocidad.setPaintTicks(true);
        velocidad.setValue(10000);
        velocidad.setOpaque(false);
        velocidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                velocidadStateChanged(evt);
            }
        });
        jPanel1.add(velocidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 730, 450, 50));

        resultado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(resultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 470, 180, 170));

        logBatalla.setColumns(20);
        logBatalla.setRows(5);
        jScrollPane10.setViewportView(logBatalla);

        jPanel1.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 240, 110));

        estadoIA.setText("Estado");
        jPanel1.add(estadoIA, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 80, 40));

        cont1.setText("jLabel1");
        jPanel1.add(cont1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 60, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("10");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 770, 30, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("1");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 770, 40, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("5");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 770, 40, -1));

        zInfo2.setText("0");
        jPanel1.add(zInfo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 500, 60, 30));

        zInfo3.setText("0");
        jPanel1.add(zInfo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 530, 60, 30));

        zInfo4.setText("0");
        jPanel1.add(zInfo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 560, 60, 30));

        stInfo1.setText("0");
        jPanel1.add(stInfo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 470, 30, 30));

        stInfo2.setText("0");
        jPanel1.add(stInfo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 500, 20, 30));

        stInfo3.setText("0");
        jPanel1.add(stInfo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 530, 20, 30));

        stInfo4.setText("0");
        jPanel1.add(stInfo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 560, 20, 30));

        zInfo1.setText("0");
        jPanel1.add(zInfo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 476, 50, 20));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesInterfaz/pantalla_fondo.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 860));

        jButton4.setText("aumentar priodidad");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 710, -1, -1));

        jButton1.setText("meter en batalla");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 710, 120, -1));

        jButton2.setText("borrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 710, -1, -1));

        jButton3.setText("revisar colas");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 710, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 860));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void velocidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_velocidadStateChanged
        velocidadPelea = velocidad.getValue();
//        this.tiempo.setText(Integer.toString(velocidadPelea));
        System.out.println(velocidadPelea);
    }//GEN-LAST:event_velocidadStateChanged
    //LLena las colas al inicio de la simulacion
    public void llenarColas(){
        
         for (Personaje x: poolZelda) {
             if(x.getTipo()==1){
                zColaP1.encolar(x);
                this.labelCreation(x, zPanelP1);
             }else if(x.getTipo()==2){
                 zColaP2.encolar(x);
                 this.labelCreation(x, zPanel2);
             }else if(x.getTipo()==3){
                 zColaP3.encolar(x);
                 this.labelCreation(x, zPanel3);
             }
         }
         for (Personaje x: poolStreet) {
             if(x.getTipo()==1){
                stColaP1.encolar(x);
                this.labelCreation(x, stPanel1);
             }else if(x.getTipo()==2){
                 stColaP2.encolar(x);
                 this.labelCreation(x, stPanel2);
             }else if(x.getTipo()==3){
                 stColaP3.encolar(x);
                 this.labelCreation(x, stPanel3);
             }
         }
    }
    
    //Crea las labels que van a irse agregando a las colas que se muestran en interfaz
    public static void  labelCreation(Personaje personaje, JPanel p){
        String texto= personaje.getId() ;
        JLabel etiqueta= new JLabel(texto);
        etiqueta.setSize(60, 60);
        etiqueta.setForeground(Color.white);
        etiqueta.setVerticalTextPosition(1);
        etiqueta.setVerticalAlignment(TOP);
        etiqueta.setHorizontalTextPosition(CENTER);
        etiqueta.setIconTextGap(3);
        etiqueta.setVerticalTextPosition(TOP);
        ImageIcon fot= new ImageIcon(personaje.getRutaIcon());
        ImageIcon icon=new ImageIcon(fot.getImage().getScaledInstance(etiqueta.getWidth(), etiqueta.getHeight(), Image.SCALE_DEFAULT));
   
        etiqueta.setIcon(icon);
        etiqueta.repaint();
        
        p.add(etiqueta);
        p.updateUI();
    }
    
    //Monta los personajes en Las variables peleador 
    public static void pelea(Personaje p, JLabel l, JLabel info1, JLabel info2, JLabel info3, JLabel info4){
        
        ImageIcon fot= new ImageIcon(p.getRutaImagen());
        ImageIcon icon=new ImageIcon(fot.getImage().getScaledInstance(l.getWidth(), l.getHeight(), Image.SCALE_DEFAULT));
        l.setText(p.getNombre());
        l.setIcon(icon);
        l.repaint();
        info1.setText(Integer.toString(p.getPropiedades().getPuntosVida()));
        info2.setText(Integer.toString(p.getPropiedades().getFuerza()));
        info3.setText(Integer.toString(p.getPropiedades().getHabilidades()));
        info4.setText(Integer.toString(p.getPropiedades().getAgilidad()));
    }
    
    //Aquie en adelante son botones para probar como se ven las colas, como se actualizan y esas cosas
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

//        try {
//            this.zFigther= SO.escogerPersonajes(zColaP1, zColaP2, zColaP3, zSemaforo, zPanelP1, zPanel2, zPanel3);
//            this.stFigther= SO.escogerPersonajes(stColaP1, stColaP2, stColaP3, stSemaforo, stPanel1, stPanel2, stPanel3);
//            this.bar1.setValue(zFigther.getPropiedades().getPuntosVida());
//            this.bar2.setValue(stFigther.getPropiedades().getPuntosVida());
//            this.pelea(zFigther, zFighterLabel, zInfo1, zInfo2, zInfo3, zInfo4);
//            this.pelea(stFigther, stFigtherLabel, stInfo1, stInfo2, stInfo3, stInfo4);
////            this.label1.setText("Vida: " + this.zFigther.getPropiedades().getPuntosVida() + "\n" + " Fuerza: " + this.zFigther.getPropiedades().getFuerza() + "\n" +  "Habilidad: " + this.zFigther.getPropiedades().getHabilidades()+ "\n"  +  "Agilidad " + this.zFigther.getPropiedades().getAgilidad()+ "\n"   );
////            System.out.println("Vida: " + this.zFigther.getPropiedades().getPuntosVida() + "\n" + " Fuerza: " + this.zFigther.getPropiedades().getFuerza() + "\n" +  "Habilidad: " + this.zFigther.getPropiedades().getHabilidades()+ "\n"  +  "Agilidad " + this.zFigther.getPropiedades().getAgilidad()+ "\n");
//
////            this.infoLabelSt.setText("Vida: " + this.stFigther.getPropiedades().getPuntosVida() + "\n" + "Fuerza: " + this.stFigther.getPropiedades().getFuerza() + "\n" +  "Habilidad: " + this.stFigther.getPropiedades().getHabilidades()+ "\n"  +  "Agilidad " + this.stFigther.getPropiedades().getAgilidad()+ "\n"   );
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
        
        
//        zPanelP1.remove(this.zPanelP1.getComponent(0));
//        zPanelP1.updateUI();
//        stPanel1.remove(this.stPanel1.getComponent(0));
//        stPanel1.updateUI();
//        zPanel2.remove(this.zPanel2.getComponent(0));
//        zPanel2.updateUI();
//        stPanel2.remove(this.stPanel2.getComponent(0));
//        stPanel2.updateUI();
//        zPanel3.remove(this.zPanel3.getComponent(0));
//        zPanel3.updateUI();
//        stPanel3.remove(this.stPanel3.getComponent(0));
//        stPanel3.updateUI();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
//        velocidadPelea = velocidad.getValue();
//            System.out.println(velocidadPelea);
//        this.llenarColas();
//        System.out.println(zColaP1.imprimirCola());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.llenarColas();
        this.SO.setTurno(true);
        SO.start();
        IA.start();
  
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
//        try {
//            SO.revisarColas(zColaP1, zColaP2, zColaP3, zColaP3, zSemaforo, zPanelP1, zPanel2, zPanel3);
//            SO.revisarColas(zColaP1, zColaP2, zColaP3, zColaP2, zSemaforo, zPanelP1, zPanel2, zPanel3);
//            SO.revisarColas(stColaP1, stColaP2, stColaP3, stColaP2, stSemaforo, stPanel1, stPanel2, stPanel3);
//            SO.revisarColas(stColaP1, stColaP2, stColaP3, stColaP3, stSemaforo, stPanel1, stPanel2, stPanel3);
//            
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jButton3ActionPerformed
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
                    new Pantalla().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JProgressBar bar1;
    public static javax.swing.JProgressBar bar2;
    public static javax.swing.JLabel cont1;
    public static javax.swing.JLabel contVictoriasSt;
    public static javax.swing.JLabel contVictoriasZ;
    public static javax.swing.JLabel estadoIA;
    public static javax.swing.JPanel ganadores;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel label8;
    public static javax.swing.JTextArea logBatalla;
    public static javax.swing.JLabel resultado;
    public static javax.swing.JLabel stFigtherLabel;
    public static javax.swing.JLabel stInfo1;
    public static javax.swing.JLabel stInfo2;
    public static javax.swing.JLabel stInfo3;
    public static javax.swing.JLabel stInfo4;
    public static javax.swing.JPanel stPanel1;
    public static javax.swing.JPanel stPanel2;
    public static javax.swing.JPanel stPanel3;
    public static javax.swing.JPanel stPanel4;
    public static javax.swing.JSlider velocidad;
    public static javax.swing.JLabel zFighterLabel;
    public static javax.swing.JLabel zInfo1;
    public static javax.swing.JLabel zInfo2;
    public static javax.swing.JLabel zInfo3;
    public static javax.swing.JLabel zInfo4;
    public static javax.swing.JPanel zPanel2;
    public static javax.swing.JPanel zPanel3;
    public static javax.swing.JPanel zPanel4;
    public static javax.swing.JPanel zPanelP1;
    // End of variables declaration//GEN-END:variables
}
