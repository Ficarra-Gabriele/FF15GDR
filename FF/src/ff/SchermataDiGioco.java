/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author ficarra.gabriele
 */
public class SchermataDiGioco extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SchermataDiGioco.class.getName());
private Noctis noctis;
    private JPanel pnlStatsEroe, pnlComandi, pnlStatsNemico;
    private JLabel lblHp, lblMp, lblStm, lblAtkDef, lblSteps, lblSpec;
    private JButton btnAtk, btnPot, btnSpl, btnExp, btnUp, btnSav, btnShp, btnInv;
    private final int H_PANEL = 220;

    class PannelloSfondo extends JPanel {
        Image img;
        PannelloSfondo() {
            try { img = new ImageIcon(getClass().getResource("/ff/immagini/Gioco.png")).getImage(); } 
            catch (Exception e) { System.err.println("Sfondo non trovato"); }
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (img != null) g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public SchermataDiGioco(Noctis n) {
        this.noctis = n;
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        PannelloSfondo sfondo = new PannelloSfondo();
        sfondo.setLayout(null);
        this.setContentPane(sfondo);
        creaUI();
        aggiornaDati();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) { posizionaUI(); }
        });
    }

    private void creaUI() {
        Color bluFF = new Color(5, 10, 40, 220);
        Font fTitle = new Font("Monospaced", Font.BOLD, 14);
        Font fStats = new Font("Monospaced", Font.PLAIN, 14);

        // 1. STATS NOCTIS
        pnlStatsEroe = new JPanel(new GridLayout(6, 1));
        pnlStatsEroe.setBackground(bluFF);
        pnlStatsEroe.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), " NOCTIS ", 0, 0, fTitle, Color.CYAN));
        lblHp = creaLabel("", fStats, Color.WHITE);
        lblMp = creaLabel("", fStats, Color.WHITE);
        lblStm = creaLabel("", fStats, Color.WHITE);
        lblAtkDef = creaLabel("", fStats, Color.WHITE);
        lblSteps = creaLabel("", fStats, Color.WHITE);
        lblSpec = creaLabel("SPECIAL: Warp-Strike", fStats, Color.ORANGE);
        pnlStatsEroe.add(lblHp); pnlStatsEroe.add(lblMp); pnlStatsEroe.add(lblStm);
        pnlStatsEroe.add(lblAtkDef); pnlStatsEroe.add(lblSteps); pnlStatsEroe.add(lblSpec);

        // 2. COMANDI
        pnlComandi = new JPanel(new GridLayout(4, 2, 5, 5));
        pnlComandi.setBackground(bluFF);
        pnlComandi.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), " COMMANDS ", 0, 0, fTitle, Color.WHITE));
        btnAtk = creaBtn("ATTACK"); btnPot = creaBtn("POTION");
        btnSpl = creaBtn("SPELL"); btnExp = creaBtn("EXPLORE");
        btnUp = creaBtn("UPLOAD"); btnSav = creaBtn("SAVE");
        btnShp = creaBtn("SHOP"); btnInv = creaBtn("INVENTORY");
        pnlComandi.add(btnAtk); pnlComandi.add(btnPot); pnlComandi.add(btnSpl); pnlComandi.add(btnExp);
        pnlComandi.add(btnUp); pnlComandi.add(btnSav); pnlComandi.add(btnShp); pnlComandi.add(btnInv);

        // 3. STATS NEMICO
        pnlStatsNemico = new JPanel(new GridLayout(4, 1));
        pnlStatsNemico.setBackground(bluFF);
        pnlStatsNemico.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), " ENEMY ", 0, 0, fTitle, Color.RED));
        pnlStatsNemico.add(creaLabel("ARDYN", fStats, Color.RED));
        pnlStatsNemico.add(creaLabel("HP: ???/???", fStats, Color.WHITE));
        pnlStatsNemico.add(creaLabel("ATK: ???", fStats, Color.WHITE));
        pnlStatsNemico.add(creaLabel("SPECIAL: ???", fStats, Color.MAGENTA));

        getContentPane().add(pnlStatsEroe);
        getContentPane().add(pnlComandi);
        getContentPane().add(pnlStatsNemico);
        posizionaUI();
    }

    private void aggiornaDati() {
        lblHp.setText("HP: " + noctis.getHp() + "/" + noctis.getHpMax());
        lblMp.setText("MP: " + noctis.getMana() + "/" + noctis.getManaMax());
        lblStm.setText("STM: " + noctis.getStamina());
        lblAtkDef.setText("ATK: " + noctis.getDanno() + " | DEF: " + noctis.getDif());
        lblSteps.setText("STEPS: " + noctis.getPassi());
    }

    private void posizionaUI() {
        int w = getWidth(), h = getHeight(), m = 20;
        int pW = (w - (m * 4)) / 3;
        pnlStatsEroe.setBounds(m, h - H_PANEL - 60, pW, H_PANEL);
        pnlComandi.setBounds(m * 2 + pW, h - H_PANEL - 60, pW, H_PANEL);
        pnlStatsNemico.setBounds(m * 3 + (pW * 2), h - H_PANEL - 60, pW, H_PANEL);
    }

    private JLabel creaLabel(String t, Font f, Color c) {
        JLabel l = new JLabel(t); l.setFont(f); l.setForeground(c); return l;
    }

    private void suonoBottoni(String filePath) {
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(getClass().getResource(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (Exception e) {
            System.err.println("file non trovato");
        }
    }
    private JButton creaBtn(String t) {
        JButton b = new JButton(t); b.setForeground(Color.WHITE); b.setContentAreaFilled(false);
        b.setFocusPainted(false); b.setBorderPainted(false); b.setFont(new Font("SansSerif", Font.PLAIN, 14));
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { b.setForeground(new Color(173, 216, 230)); b.setFont(b.getFont().deriveFont(Font.BOLD)); suonoBottoni("/ff/Musica/battleMenu.wav"); }
            public void mouseExited(MouseEvent e) { b.setForeground(Color.WHITE); b.setFont(b.getFont().deriveFont(Font.PLAIN)); }
            public void mousePressed(MouseEvent e) {suonoBottoni("/ff/Musica/CursorPress.wav");}
            
        });
        return b;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlGioco = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlGioco.setMinimumSize(new java.awt.Dimension(0, 0));
        pnlGioco.setPreferredSize(new java.awt.Dimension(1400, 1000));

        javax.swing.GroupLayout pnlGiocoLayout = new javax.swing.GroupLayout(pnlGioco);
        pnlGioco.setLayout(pnlGiocoLayout);
        pnlGiocoLayout.setHorizontalGroup(
            pnlGiocoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1400, Short.MAX_VALUE)
        );
        pnlGiocoLayout.setVerticalGroup(
            pnlGiocoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlGioco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlGioco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlGioco;
    // End of variables declaration//GEN-END:variables
}
