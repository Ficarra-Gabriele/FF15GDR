/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public class Noctis extends PersonaggioBase {

    private int mana;
    private int manaMax;
    
    
    public Noctis() {
        this.hp = 8000;
        this.hpMax = 8000;
        this.danno = 570; // se il giocatore usa magie, pozioni e quant'altro riesce a tirare giù Ardin
        this.dif = 664; // così riceve 40 danni dal personaggio base
        this.difMax = 664;
        this.stamina = 10; // può fare max 10 passi prima di dover ristabilire la stamina
        this.staminaMax = 10;
        this.passicompiuti = 0;
        this.mana = 3000;
        this.manaMax = 3000; // ogni spell costa 300, ne posso lanciare 10 prima di dover ristabilire il mana
    }
    
    @Override
    public int getHp() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void riceviDanni(int quantità) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isVivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
