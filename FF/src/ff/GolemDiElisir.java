/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public class GolemDiElisir extends Nemico {

    public GolemDiElisir() {
        this.nome = "golemdielisir";
        this.hp = 2200;
        this.danno = 450;
        this.hpMax = hp;
    }

    public void eseguiMossa(IEntitaDanneggiabile bersaglio) {
        bersaglio.riceviDanni(this.danno);
        if (!specialeUsata) {
            bersaglio.applicaEffetto("VELENO", 2);
            specialeUsata = true;
        }
    }

}
