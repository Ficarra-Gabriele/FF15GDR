/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public class GrandeRospoAzzurro extends Nemico {

    public GrandeRospoAzzurro() {
        this.nome = "granderospoazzurro";
        this.hp = 1800;
        this.danno = 380;
        this.hpMax = hp;
    }

    @Override
    public void eseguiMossa(IEntitaDanneggiabile bersaglio) {
        bersaglio.riceviDanni(this.danno);
        if (!specialeUsata) {
            bersaglio.applicaEffetto("RALLENTA", 2);
            specialeUsata = true;
        }
    }
}
