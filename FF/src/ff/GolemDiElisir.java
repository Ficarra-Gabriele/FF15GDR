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
        this.hp = 1000;
        this.danno = 352;
        this.hpMax = hp;
    }

    public void eseguiMossa(IEntitaDanneggiabile b) {
        b.riceviDanni(this.danno);
        if (!specialeUsata && b instanceof Noctis n) {
            n.riceviDanni(n.getHpMax() * 10 / 100);
            specialeUsata = true;
        }
    }
}
