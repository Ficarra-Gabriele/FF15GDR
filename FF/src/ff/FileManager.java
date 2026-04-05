/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

import java.awt.Component;
import java.io.*;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author ficarra.gabriele
 */
public class FileManager {

    public String[] elencoSalvataggi() {
        File cartella = new File(".");
        String[] tuttiIFile = cartella.list();
        return tuttiIFile;
    }

    public void salvaPartita(Noctis n, String nomeFile) {
        if (nomeFile.endsWith(".csv") == false) {
            nomeFile = nomeFile + ".csv";
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeFile))) {
            bw.write(n.getHp() + "," + n.getHpMax() + "," + n.getMana() + "," + n.getManaMax() + "," + n.getStamina() + "," + n.getGuil());
            JOptionPane.showMessageDialog(null, "Salvataggio effettuato in: " + nomeFile);
            bw.close();
        } catch (IOException e) {
            System.err.println("Errore scrittura file");
        }
    }

    public Noctis selezionaECarica(Component parent) {
        String[] saves = elencoSalvataggi();

        JList<String> listaSaves = new JList<>(saves);
        int scelta = JOptionPane.showConfirmDialog(parent, new JScrollPane(listaSaves), "Seleziona File", JOptionPane.OK_CANCEL_OPTION);

        if (scelta == JOptionPane.OK_OPTION && listaSaves.getSelectedValue() != null) {
            return caricaPartita(listaSaves.getSelectedValue());
        }
        return null;
    }

    public Noctis caricaPartita(String nomeFile) {
        try {
            FileReader fr = new FileReader(nomeFile);
            BufferedReader br = new BufferedReader(fr);

            String riga = br.readLine();
            br.close();

            if (riga != null) {
                String[] dati = riga.split(",");
                Noctis n = new Noctis();
                int hp = Integer.parseInt(dati[0]);
                int hpMax = Integer.parseInt(dati[1]);
                int mana = Integer.parseInt(dati[2]);
                int manaMax = Integer.parseInt(dati[3]);
                n.setHp(hp);
                n.setHpMax(hpMax);
                n.setManaMax(manaMax);
                n.rigeneraMana(mana - n.getMana());
                return n;
            }
        } catch (Exception e) {
            System.out.println("Errore nel caricamento");
        }
        return null;
    }
}
