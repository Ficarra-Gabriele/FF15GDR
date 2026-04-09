/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public class GrandeTacchinoImperatore extends Nemico {

    public GrandeTacchinoImperatore() {
        this.nome = "grandetacchinoimperatore";
        this.hp = 1500;
        this.danno = 520;
        this.hpMax = hp;
    }

    @Override
    public void eseguiMossa(IEntitaDanneggiabile b) {
        if (!specialeUsata) {
            b.riceviDanni(this.danno * 2);
            specialeUsata = true;
        } else {
            b.riceviDanni(this.danno);
        }
    }
}
