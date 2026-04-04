/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
class Bandana extends Oggetto {

    private int tipo, prezzo;

    public Bandana(int t) {
        this.tipo = t;
        switch (t) {
            case 1 ->
                prezzo = 200;
            case 2 ->
                prezzo = 500;
            case 3 ->
                prezzo = 700;
            case 4 ->
                prezzo = 1000;
            case 5 ->
                prezzo = 50000;
        }
    }

    public int getPrezzoNegozio() {
        return prezzo;
    }

    public void applicaEffetto(Noctis n) {
        switch (tipo) {
            case 1 -> {
                n.setHpMax(n.getHpMax() + 500);
                n.difMax += 30;
            }
            case 2 -> {
                n.setHpMax(n.getHpMax() + 700);
                n.difMax += 70;
                n.staminaMax += 1;
            }
            case 3 -> {
                n.setHpMax(n.getHpMax() + 1000);
                n.difMax += 150;
                n.staminaMax += 2;
            }
            case 4 -> {
                n.setHpMax(n.getHpMax() + 1100);
                n.difMax += 300;
                n.staminaMax += 3;
            }
            case 5 -> {
                n.setHpMax(n.getHpMax() + 99999);
                n.difMax += 999;
                n.staminaMax += 10;
                n.setManaMax(n.getManaMax() + 10000);
            }
        }
    }

    public void rimuoviEffetto(Noctis n) {
        switch (tipo) {
            case 1 -> {
                n.setHpMax(n.getHpMax() - 500);
                n.difMax -= 30;
            }
            case 2 -> {
                n.setHpMax(n.getHpMax() - 700);
                n.difMax -= 70;
                n.staminaMax -= 1;
            }
            case 3 -> {
                n.setHpMax(n.getHpMax() - 1000);
                n.difMax -= 150;
                n.staminaMax -= 2;
            }
            case 4 -> {
                n.setHpMax(n.getHpMax() - 1100);
                n.difMax -= 300;
                n.staminaMax -= 3;
            }
            case 5 -> {
                n.setHpMax(n.getHpMax() - 99999);
                n.difMax -= 999;
                n.staminaMax -= 10;
                n.setManaMax(n.getManaMax() - 10000);
            }
        }
    }

    @Override
    public String toString() {
        return switch (tipo) {
            case 1 ->
                "Bandana di Bronzo";
            case 3 ->
                "Bandana di Argento";
            case 4 ->
                "Bandana d'Oro";
            case 5 ->
                "Bandana Capitan Harlock";
            default ->
                "Bandana di Bronzo";
        };
    }
}
