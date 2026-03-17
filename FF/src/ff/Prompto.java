/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public class Prompto extends PersonaggioBase{
    

    public Prompto() {
        this.hp = 7000;
        this.hpMax = 7000;
        this.danno = 540; // se il giocatore usa bandane, pozioni e quant'altro riesce a tirare giù Ardin
        this.dif = 650; // così riceve 40 danni dal personaggio base
        this.difMax = 650;
        this.stamina = 15; // può fare max 5 passi prima di dover ristabilire la stamina
        this.staminaMax = 15;
        this.passicompiuti = 0;
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
