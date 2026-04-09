/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public class Ardyn extends Nemico {

    public Ardyn() {
        this.nome = "Ardyn";
        this.hp = 7000;
        this.hpMax = 7000;
        this.danno = 1200;
    }

    public void eseguiMossa(IEntitaDanneggiabile b) {
        if (!specialeUsata) {
            b.riceviDanni(2000);
            specialeUsata = true;
        } else {
            b.riceviDanni(this.danno);
        }
    }
}
