/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ironm
 */
public class Spada extends Oggetto {

    private String nome;
    private int bonus, prezzo;

    public Spada(String n, int b, int p) {
        this.nome = n;
        this.bonus = b;
        this.prezzo = p;
    }

    public String getNome() {
        return nome;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void applicaEffetto(Noctis n) {
        n.danno += bonus;
    }

    public void rimuoviEffetto(Noctis n) {
        n.danno -= bonus;
    }

    @Override
    public String toString() {
        return nome + " (ATK +" + bonus + ")";
    }
}
