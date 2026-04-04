/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public class GrandePolloRosa extends Nemico {

    public GrandePolloRosa() {
        this.nome = "grandepollorosa";
        this.hp = 1;
        this.danno = 0;
        this.hpMax = hp;
    }

    @Override
    public void eseguiMossa(IEntitaDanneggiabile b) {
        if (!specialeUsata && b instanceof Noctis n) {
            n.setStamina(n.getStamina() - 3);
            specialeUsata = true;
        }
    }

    @Override
    public void riceviDanni(int q) {
        this.hp = 0;
    }
}
