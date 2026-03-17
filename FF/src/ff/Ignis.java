/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public class Ignis extends PersonaggioBase {
        
    public Ignis(){
        this.hp = 7500;
        this.hpMax = 7500;
        this.danno = 575; // se il giocatore usa bandane, pozioni e quant'altro riesce a tirare giù Ardin
        this.dif = 580; // così riceve 40 danni dal personaggio base
        this.difMax = 580;
        this.stamina = 11; // può fare max 5 passi prima di dover ristabilire la stamina
        this.staminaMax = 11;
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