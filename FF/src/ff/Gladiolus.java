/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public class Gladiolus extends PersonaggioBase {

    private boolean scudoReale; // ogni partita ha la possibilità di non farsi colpire una volta a partita (ogni volta che incontra un nemico si resetta) 
    
    public Gladiolus() {
        this.hp = 9000;
        this.hpMax = 9000;
        this.danno = 600; // se il giocatore usa bandane, pozioni e quant'altro riesce a tirare giù Ardin
        this.dif = 700; // così riceve 40 danni dal personaggio base
        this.difMax = 700;
        this.stamina = 5; // può fare max 5 passi prima di dover ristabilire la stamina
        this.staminaMax = 5;
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
