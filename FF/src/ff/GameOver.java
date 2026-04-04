/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ff;

import static ff.SchermataIniziale.clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author ironm
 */
public class GameOver extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GameOver.class.getName());

    private JButton btnLeggiTutto, btnRiprova;
    private int letteraAttuale = 0;
    private Timer timerDigitazione;
    private JLayeredPane layeredPane;
    private JTextArea jTextArea1;
    private JScrollPane jScrollPane1;

    private String storiaGameOver = "Le fiamme si spengono e il fragore dell'acciaio lascia il posto a un silenzio innaturale. Noctis giace a terra, la sua forza prosciugata, mentre la luce del Cristallo svanisce definitivamente dai suoi occhi. Ardyn Izunia osserva il corpo del Prescelto con un sorriso amaro: la sua vendetta, attesa per duemila anni, è finalmente compiuta.\n\n"
            + "Senza un Re a contrastarla, la Piaga delle Stelle accelera la sua morsa fatale su Eos. Il sole scompare dietro un orizzonte di nubi nere che non si apriranno mai più. Gladio, Ignis e Prompto combattono fino all'ultimo respiro tra le rovine di Insomnia, ma senza la magia dell'Anello e la luce del loro leader, vengono sopraffatti dalle orde dei Daemon. Uno dopo l'altro, i legami della fratellanza vengono recisi dall'oscurità.\n\n"
            + "Il mondo sprofonda in una notte eterna. Le città cadono, i ricordi sbiadiscono e l'umanità diventa solo una leggenda dimenticata tra i sussurri dei mostri che ora dominano le terre selvagge. Ardyn siede sul trono di macerie, prigioniero della sua stessa vittoria in un mondo senza vita, dove non esiste più né luce né speranza. Il ciclo si è chiuso nel modo più tragico: il destino ha vinto, e il sacrificio del Re è stato vanificato dal freddo abbraccio della sconfitta.";

    class JTextAreaFF extends JTextArea {

        Image sfondo;

        public JTextAreaFF() {
            try {
                sfondo = new ImageIcon(getClass().getResource("/ff/immagini/gameover.png")).getImage();
            } catch (Exception e) {
                System.err.println("Immagine gameover.png non trovata!");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            if (sfondo != null) {
                g2d.drawImage(sfondo, 0, 0, getWidth(), getHeight(), this);
            }
            g2d.setColor(new Color(20, 0, 0, 190));
            g2d.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }
    }

    public GameOver() {
        initComponents();
        configuraStruttura();
        musica();
        iniziaDigitazione();
    }

    private void musica() {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
                clip.close();
            }
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(getClass().getResource("/ff/Musica/gameover.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println("File gameover.wav non trovato");
        }
    }

    private void fermaMusica() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }

    private void iniziaDigitazione() {
        jTextArea1.setText("");
        timerDigitazione = new Timer(40, e -> {
            if (letteraAttuale < storiaGameOver.length()) {
                jTextArea1.append(String.valueOf(storiaGameOver.charAt(letteraAttuale)));
                letteraAttuale++;
            } else {
                ((Timer) e.getSource()).stop();
                btnLeggiTutto.setVisible(false);
                btnRiprova.setVisible(true);
            }
        });
        timerDigitazione.start();
    }

    private void configuraStruttura() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        int screenW = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenH = Toolkit.getDefaultToolkit().getScreenSize().height;

        layeredPane = new JLayeredPane();
        this.setContentPane(layeredPane);

        jTextArea1 = new JTextAreaFF();
        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setForeground(new Color(255, 200, 200));
        jTextArea1.setFont(new Font("Palatino Linotype", Font.ITALIC, 28));
        jTextArea1.setMargin(new Insets(100, 150, 100, 150));
        jTextArea1.setOpaque(false);

        jScrollPane1 = new JScrollPane(jTextArea1);
        jScrollPane1.setBounds(0, 0, screenW, screenH);
        jScrollPane1.setBorder(null);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        layeredPane.add(jScrollPane1, JLayeredPane.DEFAULT_LAYER);

        btnLeggiTutto = creaBottoneSpeciale("LEGGI TUTTO");
        btnRiprova = creaBottoneSpeciale("TORNA AL MENU");
        btnRiprova.setVisible(false);

        btnLeggiTutto.setBounds(screenW / 2 - 100, screenH - 150, 200, 45);
        btnRiprova.setBounds(screenW / 2 - 100, screenH - 150, 200, 45);

        layeredPane.add(btnLeggiTutto, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(btnRiprova, JLayeredPane.PALETTE_LAYER);

        btnLeggiTutto.addActionListener(e -> {
            timerDigitazione.stop();
            jTextArea1.setText(storiaGameOver);
            btnLeggiTutto.setVisible(false);
            btnRiprova.setVisible(true);
        });

        btnRiprova.addActionListener(e -> {
            fermaMusica();
            this.dispose();
            new SchermataIniziale().setVisible(true);
        });
    }

    private void suonoBottoni(String filePath) {
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(getClass().getResource(filePath));
            Clip c = AudioSystem.getClip();
            c.open(audioInput);
            c.start();
        } catch (Exception e) {
        }
    }

    private JButton creaBottoneSpeciale(String testo) {
        JButton b = new JButton(testo);
        b.setFont(new Font("Palatino Linotype", Font.BOLD, 16));
        b.setForeground(new Color(255, 150, 150));
        b.setContentAreaFilled(false);
        b.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0, 100), 1));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                b.setForeground(Color.RED);
                suonoBottoni("/ff/Musica/Cursor.wav");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0, 100), 1));
                b.setForeground(new Color(255, 150, 150));
            }

            public void mousePressed(MouseEvent e) {
                suonoBottoni("/ff/Musica/CursorPress.wav");
            }
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
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
        java.awt.EventQueue.invokeLater(() -> new GameOver().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
