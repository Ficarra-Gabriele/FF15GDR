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
        return cartella.list((dir, name) -> name.endsWith(".ser") || name.endsWith(".csv"));
    }

    public void salvaPartita(Noctis n, String nomeFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeFile + ".csv"))) {
            bw.write("HP,HP_MAX,MANA,MANA_MAX,STAMINA,GUIL");
            bw.newLine();
            bw.write(n.getHp() + "," + n.getHpMax() + "," + n.getMana() + "," + n.getManaMax() + "," + n.getStamina() + "," + n.getGuil());
        } catch (IOException e) {
            System.err.println("Errore scrittura CSV");
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeFile + ".ser"))) {
            oos.writeObject(n);
            JOptionPane.showMessageDialog(null, "Salvataggio effettuato (CSV + SER): " + nomeFile);
        } catch (IOException e) {
            System.err.println("Errore serializzazione: " + e.getMessage());
        }
    }

    public Noctis selezionaECarica(Component parent) {
        String[] saves = elencoSalvataggi();

        if (saves == null || saves.length == 0) {
            JOptionPane.showMessageDialog(parent, "Nessun salvataggio trovato.");
            return null;
        }

        JList<String> listaSaves = new JList<>(saves);
        int scelta = JOptionPane.showConfirmDialog(parent, new JScrollPane(listaSaves), "Seleziona file da caricare", JOptionPane.OK_CANCEL_OPTION);

        if (scelta == JOptionPane.OK_OPTION && listaSaves.getSelectedValue() != null) {
            String fileScelto = listaSaves.getSelectedValue();

            if (fileScelto.endsWith(".ser")) {
                return caricaPartitaSerializzata(fileScelto);
            } else if (fileScelto.endsWith(".csv")) {
                return caricaPartitaCSV(fileScelto);
            }
        }
        return null;
    }

    public Noctis caricaPartitaSerializzata(String nomeFile) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeFile))) {
            return (Noctis) ois.readObject();
        } catch (Exception e) {
            System.err.println("Errore caricamento SER");
            return null;
        }
    }

    public Noctis caricaPartitaCSV(String nomeFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
            br.readLine();
            String riga = br.readLine();

            if (riga != null) {
                String[] dati = riga.split(",");
                Noctis n = new Noctis();
                n.setHp(Integer.parseInt(dati[0]));
                n.setHpMax(Integer.parseInt(dati[1]));
                int manaCorrente = Integer.parseInt(dati[2]);
                n.setManaMax(Integer.parseInt(dati[3]));
                n.setStamina(Integer.parseInt(dati[4]));
                n.aggiungiGuil(Integer.parseInt(dati[5]) - n.getGuil());

                n.rigeneraMana(manaCorrente - n.getMana());

                return n;
            }
        } catch (Exception e) {
            System.err.println("Errore caricamento CSV");
        }
        return null;
    }
}
