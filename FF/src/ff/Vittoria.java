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
public class Vittoria extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Vittoria.class.getName());
    private JButton btnLeggiTutto, btnEsci;
    private int letteraAttuale = 0;
    private Timer timerDigitazione;
    private JLayeredPane layeredPane;
    private JTextArea jTextArea1;
    private JScrollPane jScrollPane1;

    private String storiaVittoria = "Con l'ultimo fendente della Spada del Padre, l'oscurità che avvolgeva Ardyn Izunia si dissolve in un grido soffocato dal peso di secoli di rancore. La battaglia è finita, ma il prezzo dell'alba è scritto nelle stelle.\n\n"
            + "Noctis Lucis Caelum, il centosedicesimo Re di Lucis, osserva per l'ultima volta i suoi compagni. Gladio, Ignis e Prompto sono lì, feriti e stremati, ma vivi. Il loro legame, forgiato tra asfalto e battaglie, è l'unica cosa che il destino non ha potuto spezzare. Noctis sa che il trono lo attende, non per regnare, ma per compiere il sacrificio supremo. Solo il potere del Cristallo, unito alla vita del Re Prescelto, può purificare il mondo dalla Piaga delle Stelle.\n\n"
            + "Salendo i gradini del trono di Insomnia, Noctis sente il peso di ogni re che lo ha preceduto. Le ombre dei suoi antenati lo circondano, armi magiche pronte a trafiggere il suo cuore per liberare la luce. Mentre la lama scende, il pensiero di Noctis vola a Luna, alla promessa di un futuro che non vedranno mai, e ai suoi amici, a cui affida il compito di ricostruire un mondo finalmente libero dalle tenebre.\n\n"
            + "Un'esplosione di luce bianca travolge la sala del trono. Il Re muore, e con lui muore la notte eterna. Oltre le rovine di Insomnia, il sole sorge per la prima volta dopo dieci anni, baciando la terra di Eos. Il sacrificio di Noctis non è stato vano: il ciclo della vendetta è spezzato. Camminando tra i campi bagnati dalla rugiada, i tre compagni rimasti guardano l'orizzonte in silenzio. Il Re è morto, lunga vita al Re della Luce. Noctis ha protetto tutti, diventando la leggenda che illuminerà ogni domani.";

    class JTextAreaFF extends JTextArea {

        Image sfondo;

        public JTextAreaFF() {
            try {
                sfondo = new ImageIcon(getClass().getResource("/ff/immagini/vittoria.png")).getImage();
            } catch (Exception e) {
                System.err.println("Immagine vittoria.png non trovata nel percorso risorse!");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            if (sfondo != null) {
                g2d.drawImage(sfondo, 0, 0, getWidth(), getHeight(), this);
            }
            g2d.setColor(new Color(0, 0, 0, 180));
            g2d.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }
    }

    public Vittoria() {
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
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(getClass().getResource("/ff/Musica/vittoria.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println("File vittoria.wav non trovato");
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
        timerDigitazione = new Timer(30, e -> {
            if (letteraAttuale < storiaVittoria.length()) {
                jTextArea1.append(String.valueOf(storiaVittoria.charAt(letteraAttuale)));
                letteraAttuale++;
            } else {
                ((Timer) e.getSource()).stop();
                btnLeggiTutto.setVisible(false);
                btnEsci.setVisible(true);
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
        jTextArea1.setForeground(new Color(255, 255, 230));
        jTextArea1.setFont(new Font("Palatino Linotype", Font.ITALIC, 28));
        jTextArea1.setMargin(new Insets(80, 120, 80, 120));
        jTextArea1.setOpaque(false);

        jScrollPane1 = new JScrollPane(jTextArea1);
        jScrollPane1.setBounds(0, 0, screenW, screenH);
        jScrollPane1.setBorder(null);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        layeredPane.add(jScrollPane1, JLayeredPane.DEFAULT_LAYER);

        btnLeggiTutto = creaBottoneSpeciale("SALTA");
        btnEsci = creaBottoneSpeciale("CHIUDI IL GIOCO");
        btnEsci.setVisible(false);

        btnLeggiTutto.setBounds(screenW - 250, screenH - 120, 200, 40);
        btnEsci.setBounds(screenW - 250, screenH - 120, 200, 40);

        layeredPane.add(btnLeggiTutto, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(btnEsci, JLayeredPane.PALETTE_LAYER);

        btnLeggiTutto.addActionListener(e -> {
            timerDigitazione.stop();
            jTextArea1.setText(storiaVittoria);
            btnLeggiTutto.setVisible(false);
            btnEsci.setVisible(true);
        });

        btnEsci.addActionListener(e -> {
            fermaMusica();
            System.exit(0);
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
        b.setFont(new Font("Palatino Linotype", Font.BOLD, 14));
        b.setForeground(Color.WHITE);
        b.setContentAreaFilled(false);
        b.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 1));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                suonoBottoni("/ff/Musica/Cursor.wav");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 1));
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
        java.awt.EventQueue.invokeLater(() -> new Vittoria().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
