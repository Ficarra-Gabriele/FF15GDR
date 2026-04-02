/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author ficarra.gabriele
 */
public class SchermataStoria extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SchermataStoria.class.getName());
    private JButton btnLeggiTutto, btnAvanti;

    class JTextAreaFF extends JTextArea {

        Image sfondo;

        public JTextAreaFF() {
            try {
                sfondo = new ImageIcon(getClass().getResource("/ff/immagini/Storia.jpg")).getImage();
            } catch (Exception e) {
                System.err.println("Immagine non trovata!");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            if (sfondo != null) {
                g2d.drawImage(sfondo, 0, 0, getWidth(), getHeight(), this);
            }
            g2d.setColor(new Color(0, 5, 15, 160));
            g2d.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }
    }
    private String storiaCompleta = "C’era un tempo in cui il mondo di Eos respirava sotto un cielo che non conosceva fine, protetto dalla luce di un Cristallo che gli Dei stessi avevano affidato ai mortali. Per secoli, quel frammento di divinità è stato il cuore pulsante del Regno di Lucis, un baluardo di magia antica capace di tenere a bada l'avanzata delle tenebre. Ma la pace, in questo mondo, è un velo sottile che il vento del destino sta per strappare.\n"
            + "\n"
            + "Mentre la tecnologia dell'Impero di Niflheim forja eserciti di metallo e macchine senza anima, un male più profondo, la Piaga delle Stelle, scivola tra le ombre. È un morbo che non mangia la carne, ma consuma la luce stessa del sole, allungando le notti e trasformando gli uomini in Daemon, creature perdute nel vuoto. La profezia parla di un Re Prescelto che riporterà l'alba, ma la leggenda omette di dire che nessun re può regnare tra le macerie senza una fratellanza che ne sostenga il peso.\n"
            + "\n"
            + "Il viaggio che sta per compiere la Regalia non è la marcia trionfale di un army, ma l’ultima odissea di quattro anime legate da un giuramento silenzioso. C’è Noctis, l’erede di una stirpe condannata al sacrificio; Ignis, la mente che scruta l'orizzonte e trasforma la strategia in sopravvivenza; Gladio, lo scudo vivente che non indietreggia neanche di fronte ai passi colossali degli Dei; e Prompto, il cuore umano che scatta fotografie alla polvere per ricordarsi che, prima di essere soldati, erano solo amici.\n"
            + "\n"
            + "Insieme, dovranno attraversare terre selvagge dove gli Astrali — Titani di pietra e Dei di ghiaccio — dormono in attesa di essere risvegliati da una Sciamana che sacrifica la propria vita per unire la terra al cielo. Dovranno cercare le tombe dei re antichi, dove le armi del passato attendono mani degne di impugnarle. Ogni chilometro di asfalto, ogni pasto consumato attorno a un fuoco, ogni battaglia contro i Magitek dell'Impero è un passo verso una verità terribile: l'alba non è un diritto, è un dono che va pagato col sangue.\n"
            + "\n"
            + "Niflheim ha già marciato su Insomnia. Il trono è vuoto, il Cristallo è perduto e l'oscuro Ardyn Izunia osserva il mondo sgretolarsi con il sorriso di chi ha atteso secoli per questo momento. La storia non chiederà a questi quattro compagni di essere eroi, ma di essere fratelli fino all'ultimo respiro. Perché quando la notte diventerà eterna, non saranno le corone a illuminare la via, ma il legame indissolubile di chi ha deciso di sfidare il destino, insieme.";
    
    private int letteraAttuale = 0;
    private Timer timerDigitazione;
    private JLayeredPane layeredPane;

    public SchermataStoria() {
        initComponents();
        configuraStruttura();
        iniziaDigitazione();
    }

    private void iniziaDigitazione() {
        jTextArea1.setText("");
        timerDigitazione = new Timer(15, e -> {
            if (letteraAttuale < storiaCompleta.length()) {
                jTextArea1.append(String.valueOf(storiaCompleta.charAt(letteraAttuale)));
                letteraAttuale++;
            } else {
                ((Timer) e.getSource()).stop();
                btnLeggiTutto.setVisible(false);
                btnAvanti.setVisible(true);
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
        jTextArea1.setForeground(new Color(245, 245, 240));
        jTextArea1.setFont(new Font("Palatino Linotype", Font.ITALIC, 32));
        jTextArea1.setMargin(new Insets(100, 100, 150, 100));
        jTextArea1.setOpaque(false);
        jScrollPane1 = new JScrollPane(jTextArea1);
        jScrollPane1.setBounds(0, 0, screenW, screenH);
        jScrollPane1.setBorder(null);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setWheelScrollingEnabled(true);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        layeredPane.add(jScrollPane1, JLayeredPane.DEFAULT_LAYER);
        btnLeggiTutto = creaBottoneSpeciale("LEGGI TUTTO");
        btnAvanti = creaBottoneSpeciale("CONTINUA");
        btnAvanti.setVisible(false);
        btnLeggiTutto.setBounds(screenW - 250, screenH - 100, 200, 40);
        btnAvanti.setBounds(screenW - 250, screenH - 100, 200, 40);
        layeredPane.add(btnLeggiTutto, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(btnAvanti, JLayeredPane.PALETTE_LAYER);
        btnLeggiTutto.addActionListener(e -> {
            timerDigitazione.stop();
            jTextArea1.setText(storiaCompleta);
            btnLeggiTutto.setVisible(false);
            btnAvanti.setVisible(true);
        });
        btnAvanti.addActionListener(e -> {
            if (SchermataIniziale.clip != null) {
                SchermataIniziale.clip.stop();
            }
            this.dispose();
            new SchermataDiGioco(new Noctis()).setVisible(true);
        });
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

    private JButton creaBottoneSpeciale(String testo) {
        JButton b = new JButton(testo);
        b.setFont(new Font("Palatino Linotype", Font.BOLD, 14));
        b.setForeground(Color.WHITE);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
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

        PannelloSfondo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout PannelloSfondoLayout = new javax.swing.GroupLayout(PannelloSfondo);
        PannelloSfondo.setLayout(PannelloSfondoLayout);
        PannelloSfondoLayout.setHorizontalGroup(
            PannelloSfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        PannelloSfondoLayout.setVerticalGroup(
            PannelloSfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PannelloSfondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PannelloSfondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        java.awt.EventQueue.invokeLater(() -> new SchermataStoria().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PannelloSfondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
