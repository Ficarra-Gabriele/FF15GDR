/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ff;

import static ff.SchermataIniziale.clip;
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

    private void musica() {
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(getClass().getResource("/ff/Musica/combattimento.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println("file non trovato");
        }
    }

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SchermataDiGioco.class.getName());
    private Noctis noctis;
    private GameManager gameManager;
    private Negozio negozio = new Negozio();

    private JPanel pnlStatsEroe, pnlComandi, pnlStatsNemico, pnlDescrizione;
    private JLabel lblHp, lblMp, lblStm, lblAtkDef, lblSteps, lblSpec, lblImmagineIncontro;
    private JLabel lblNomeNemico, lblHpNemico;
    private JTextArea txtDescrizione;

    private JButton btnAtk, btnLoad, btnSpl, btnWarp, btnExp, btnSav, btnShp, btnInv;
    private JButton btnUse, btnSell, btnBuy;

    private final int H_PANEL = 220;

    private DefaultListModel<String> modelLista;
    private JList<String> listaGrafica;
    private JScrollPane scrollInventario;

    private DefaultListModel<String> modelShop;
    private JList<String> listaShop;
    private JScrollPane scrollShop;

    class PannelloSfondo extends JPanel {

        Image img;

        PannelloSfondo() {
            try {
                img = new ImageIcon(getClass().getResource("/ff/immagini/Gioco.png")).getImage();
            } catch (Exception e) {
                System.err.println("Sfondo non trovato");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (img != null) {
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public SchermataDiGioco(Noctis n) {
        musica();
        this.noctis = n;
        this.gameManager = new GameManager(n);
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        PannelloSfondo sfondo = new PannelloSfondo();
        sfondo.setLayout(null);
        this.setContentPane(sfondo);
        creaUI();
        configuraBottoni();
        aggiornaDati();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                posizionaUI();
            }
        });
    }

    private void creaUI() {
        Color bluFF = new Color(5, 10, 40, 220);
        Font fTitle = new Font("Monospaced", Font.BOLD, 14);
        Font fStats = new Font("Monospaced", Font.PLAIN, 14);

        lblImmagineIncontro = new JLabel();
        lblImmagineIncontro.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblImmagineIncontro);

        pnlStatsEroe = new JPanel(new GridLayout(6, 1));
        pnlStatsEroe.setBackground(bluFF);
        pnlStatsEroe.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), " NOCTIS ", 0, 0, fTitle, Color.CYAN));
        lblHp = creaLabel("", fStats, Color.WHITE);
        lblMp = creaLabel("", fStats, Color.WHITE);
        lblStm = creaLabel("", fStats, Color.WHITE);
        lblAtkDef = creaLabel("", fStats, Color.WHITE);
        lblSteps = creaLabel("", fStats, Color.YELLOW);
        lblSpec = creaLabel("SPECIAL: Warp-Strike", fStats, Color.ORANGE);
        pnlStatsEroe.add(lblHp);
        pnlStatsEroe.add(lblMp);
        pnlStatsEroe.add(lblStm);
        pnlStatsEroe.add(lblAtkDef);
        pnlStatsEroe.add(lblSteps);
        pnlStatsEroe.add(lblSpec);

        pnlComandi = new JPanel(new GridLayout(4, 2, 5, 5));
        pnlComandi.setBackground(bluFF);
        pnlComandi.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), " COMMANDS ", 0, 0, fTitle, Color.WHITE));
        btnAtk = creaBtn("ATTACK");
        btnLoad = creaBtn("LOAD");
        btnSpl = creaBtn("SPELL");
        btnWarp = creaBtn("WARP-STRIKE");
        btnExp = creaBtn("EXPLORE");
        btnInv = creaBtn("INVENTORY");
        btnShp = creaBtn("SHOP");
        btnSav = creaBtn("SAVE");
        pnlComandi.add(btnAtk);
        pnlComandi.add(btnLoad);
        pnlComandi.add(btnSpl);
        pnlComandi.add(btnWarp);
        pnlComandi.add(btnExp);
        pnlComandi.add(btnInv);
        pnlComandi.add(btnShp);
        pnlComandi.add(btnSav);

        pnlStatsNemico = new JPanel(new GridLayout(2, 1));
        pnlStatsNemico.setBackground(bluFF);
        pnlStatsNemico.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), " ENEMY ", 0, 0, fTitle, Color.RED));
        lblNomeNemico = creaLabel("NONE", fStats, Color.RED);
        lblHpNemico = creaLabel("HP: 0/0", fStats, Color.WHITE);
        pnlStatsNemico.add(lblNomeNemico);
        pnlStatsNemico.add(lblHpNemico);
        pnlStatsNemico.setVisible(false);

        pnlDescrizione = new JPanel(new BorderLayout());
        pnlDescrizione.setBackground(bluFF);
        pnlDescrizione.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), " INFO ", 0, 0, fTitle, Color.WHITE));
        txtDescrizione = new JTextArea();
        txtDescrizione.setEditable(false);
        txtDescrizione.setLineWrap(true);
        txtDescrizione.setWrapStyleWord(true);
        txtDescrizione.setBackground(bluFF);
        txtDescrizione.setForeground(Color.LIGHT_GRAY);
        txtDescrizione.setFont(new Font("Monospaced", Font.ITALIC, 13));
        pnlDescrizione.add(txtDescrizione, BorderLayout.CENTER);

        modelLista = new DefaultListModel<>();
        listaGrafica = new JList<>(modelLista);
        listaGrafica.setBackground(new Color(10, 20, 60));
        listaGrafica.setForeground(Color.WHITE);
        scrollInventario = new JScrollPane(listaGrafica);
        scrollInventario.setVisible(false);

        modelShop = new DefaultListModel<>();
        listaShop = new JList<>(modelShop);
        listaShop.setBackground(new Color(10, 20, 60));
        listaShop.setForeground(Color.GREEN);
        scrollShop = new JScrollPane(listaShop);
        scrollShop.setVisible(false);

        btnUse = creaBtn("USE");
        btnSell = creaBtn("SELL");
        btnBuy = creaBtn("BUY");
        btnUse.setVisible(false);
        btnSell.setVisible(false);
        btnBuy.setVisible(false);

        getContentPane().add(pnlStatsEroe);
        getContentPane().add(pnlComandi);
        getContentPane().add(pnlStatsNemico);
        getContentPane().add(pnlDescrizione);
        getContentPane().add(scrollInventario);
        getContentPane().add(scrollShop);
        getContentPane().add(btnUse);
        getContentPane().add(btnSell);
        getContentPane().add(btnBuy);
    }

    private void configuraBottoni() {
        btnExp.addActionListener(e -> {
            gameManager.esplora();
            aggiornaDati();
            controllaStatoGioco();
        });
        btnAtk.addActionListener(e -> {
            gameManager.eseguiTurno("ATTACCO");
            aggiornaDati();
            controllaStatoGioco();
        });
        btnSpl.addActionListener(e -> {
            gameManager.eseguiTurno("SPELL");
            aggiornaDati();
            controllaStatoGioco();
        });
        btnWarp.addActionListener(e -> {
            gameManager.eseguiTurno("WARP");
            aggiornaDati();
            controllaStatoGioco();
        });

        btnInv.addActionListener(e -> {
            boolean mostrareInv = !scrollInventario.isVisible();
            scrollInventario.setVisible(mostrareInv);
            btnUse.setVisible(mostrareInv);
            btnSell.setVisible(mostrareInv);
            scrollShop.setVisible(false);
            btnBuy.setVisible(false);

            if (mostrareInv) {
                aggiornaInventario();
            }
        });

        btnShp.addActionListener(e -> {
            boolean mostrareShop = !scrollShop.isVisible();
            scrollShop.setVisible(mostrareShop);
            btnBuy.setVisible(mostrareShop);
            scrollInventario.setVisible(false);
            btnUse.setVisible(false);
            btnSell.setVisible(false);

            if (mostrareShop) {
                aggiornaShop();
            }
        });

        btnUse.addActionListener(e -> {
            int i = listaGrafica.getSelectedIndex();
            if (i != -1) {
                Oggetto obj = noctis.getInventario().get(i);
                if (obj instanceof Bandana b) {
                    Bandana vecchia = noctis.getBandanaEquipaggiata();
                    if (vecchia != null) {
                        noctis.getInventario().add(vecchia);
                    }
                    noctis.equipaggiaBandana(b);
                    noctis.getInventario().remove(i);
                } else if (obj instanceof Spada s) {
                    noctis.equipaggiaSpada(s);
                } else {
                    obj.applicaEffetto(noctis);
                    if (obj instanceof Pozione) {
                        noctis.getInventario().remove(i);
                    }
                }
                aggiornaDati();
            }
        });

        btnSell.addActionListener(e -> {
            int i = listaGrafica.getSelectedIndex();
            if (i != -1) {
                Oggetto obj = noctis.getInventario().get(i);
                int ricavo = (obj instanceof Spada s) ? s.getPrezzo() / 2 : 150;
                noctis.aggiungiGuil(ricavo);
                noctis.getInventario().remove(i);
                aggiornaDati();
            }
        });

        btnBuy.addActionListener(e -> {
            int i = listaShop.getSelectedIndex();
            if (i != -1) {
                Oggetto obj = negozio.getMerce().get(i);
                int costo = (obj instanceof Spada s) ? s.getPrezzo() : (obj instanceof Bandana b ? b.getPrezzoNegozio() : 300);
                if (noctis.getGuil() >= costo) {
                    noctis.spendiGuil(costo);
                    noctis.getInventario().add(obj);
                    negozio.getMerce().remove(i);
                    aggiornaShop();
                    aggiornaDati();
                }
            }
        });

        btnSav.addActionListener(e -> {
            String nomeFile = JOptionPane.showInputDialog(this, "Nome del salvataggio:");
            if (nomeFile != null && !nomeFile.trim().isEmpty()) {
                new FileManager().salvaPartita(noctis, nomeFile);
            }
        });

        btnLoad.addActionListener(e -> {
            Noctis caricato = new FileManager().selezionaECarica(this);
            if (caricato != null) {
                this.noctis = caricato;
                this.gameManager = new GameManager(noctis);
                aggiornaDati();
            }
        });
    }

    private void aggiornaDati() {
        lblHp.setText("HP: " + noctis.getHp() + "/" + noctis.getHpMax());
        lblMp.setText("MP: " + noctis.getMana() + "/" + noctis.getManaMax());
        lblStm.setText("STM: " + noctis.getStamina());
        lblAtkDef.setText("ATK: " + noctis.getDanno() + " | DEF: " + noctis.getDif());
        lblSteps.setText("ARDYN IN: " + gameManager.getPassiRimanenti() + " | GUIL: " + noctis.getGuil());
        txtDescrizione.setText(gameManager.getDescrizioneEvento());

        boolean inB = gameManager.isBattagliaAttiva();
        btnAtk.setEnabled(inB);
        btnSpl.setEnabled(inB && noctis.getMana() >= 100);
        btnWarp.setEnabled(inB && noctis.getMana() >= 1000);
        btnLoad.setEnabled(true);
        btnExp.setEnabled(!inB);
        btnShp.setEnabled(true);
        btnInv.setEnabled(true);
        btnSav.setEnabled(true);

        pnlStatsNemico.setVisible(inB);

        if (inB) {
            Nemico n = gameManager.getNemicoCorrente();
            lblNomeNemico.setText(n.getNome().toUpperCase());
            lblHpNemico.setText("HP: " + n.getHp() + "/" + n.getHpMax());
            setImmagine("/ff/immagini/" + n.getNome().toLowerCase() + ".png");
        } else {
            String imgTag = gameManager.getUltimoEventoImg().toLowerCase();
            String path = switch (imgTag) {
                case "bandanadibronzo" ->
                    "/ff/immagini/bandanadibronzo.png";
                case "bandanadiargento" ->
                    "/ff/immagini/bndanadiargento.png";
                case "bandanadoro" ->
                    "/ff/immagini/bandanadoro.png";
                case "bandanacapitanharlock" ->
                    "/ff/immagini/bandanacaptainharlock.png";
                case "pozionehp" ->
                    "/ff/immagini/cura.png";
                case "pozionemana" ->
                    "/ff/immagini/mana.png";
                case "pozionestamina" ->
                    "/ff/immagini/stamina.png";
                case "chocobo" ->
                    "/ff/immagini/chocobo.png";
                default ->
                    "/ff/immagini/strada.png";
            };
            setImmagine(path);
        }
        aggiornaInventario();
        repaint();
    }

    private void fermaMusica() {
        clip.close();
    }

    private void controllaStatoGioco() {
        if (noctis.getHp() <= 0 || noctis.getStamina() <= 0) {
            fermaMusica();
            new GameOver().setVisible(true);
            this.dispose();
        } else if (gameManager.isVittoriaFinale()) {
            fermaMusica();
            new Vittoria().setVisible(true);
            this.dispose();
        }
    }

    private void setImmagine(String path) {
        try {
            java.net.URL imgURL = getClass().getResource(path);
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                Image scaled = icon.getImage().getScaledInstance(lblImmagineIncontro.getWidth(), lblImmagineIncontro.getHeight(), Image.SCALE_SMOOTH);
                lblImmagineIncontro.setIcon(new ImageIcon(scaled));
            } else {
                lblImmagineIncontro.setIcon(null);
            }
        } catch (Exception e) {
        }
    }

    private void posizionaUI() {
        int w = getWidth(), h = getHeight(), m = 20;
        int pW = (w - (m * 4)) / 3;
        pnlStatsEroe.setBounds(m, h - H_PANEL - 80, pW, H_PANEL);
        pnlComandi.setBounds(m * 2 + pW, h - H_PANEL - 80, pW, H_PANEL);
        pnlStatsNemico.setBounds(m * 3 + (pW * 2), h - H_PANEL - 80, pW, H_PANEL);
        lblImmagineIncontro.setBounds(w / 2 - 200, h / 2 - 250, 400, 300);
        int larghezzaColonna = 270;
        int coordinataX = w - larghezzaColonna - m;
        int zonaY = 10;
        scrollInventario.setBounds(coordinataX, zonaY, larghezzaColonna, 200);
        btnUse.setBounds(coordinataX, zonaY + 205, 130, 30);
        btnSell.setBounds(coordinataX + 140, zonaY + 205, 130, 30);
        scrollShop.setBounds(coordinataX, zonaY, larghezzaColonna, 200);
        btnBuy.setBounds(coordinataX, zonaY + 205, larghezzaColonna, 30);
        int infoY = zonaY + 205 + 40;
        pnlDescrizione.setBounds(coordinataX, infoY, larghezzaColonna, 200);
    }

    private JLabel creaLabel(String t, Font f, Color c) {
        JLabel l = new JLabel(t);
        l.setFont(f);
        l.setForeground(c);
        return l;
    }

    private JButton creaBtn(String t) {
        JButton b = new JButton(t);
        b.setForeground(Color.WHITE);
        b.setContentAreaFilled(false);
        b.setFont(new Font("SansSerif", Font.PLAIN, 14));
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b.setForeground(Color.CYAN);
            }

            public void mouseExited(MouseEvent e) {
                b.setForeground(Color.WHITE);
            }
        });
        return b;
    }

    private void aggiornaInventario() {
        modelLista.clear();
        for (Oggetto o : noctis.getInventario()) {
            modelLista.addElement(o.toString());
        }
    }

    private void aggiornaShop() {
        modelShop.clear();
        for (Oggetto o : negozio.getMerce()) {
            int costo = (o instanceof Spada s) ? s.getPrezzo()
                    : (o instanceof Bandana b ? b.getPrezzoNegozio() : 300);

            modelShop.addElement(o.toString() + " - " + costo + "G");
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
