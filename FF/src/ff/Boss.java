/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public class Boss extends Nemico {

    private boolean potenziamentoAttivo;

    public Boss() {
        super();
        this.potenziamentoAttivo = false;
        this.hpMax = hp;
    }

    @Override
    public void eseguiMossa(IEntitaDanneggiabile bersaglio) {
        if (potenziamentoAttivo == true) {
            bersaglio.riceviDanni(this.danno * 2);
            potenziamentoAttivo = false;
        } else {
            bersaglio.riceviDanni(this.danno);
            potenziamentoAttivo = true;
        }
    }
}
