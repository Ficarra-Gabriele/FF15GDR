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
        this.nome = "Daemon"; 
        this.hp = 4500;
        this.hpMax = 4500;
        this.danno = 650;
        this.potenziamentoAttivo = false;
    }

    @Override
    public void eseguiMossa(IEntitaDanneggiabile bersaglio) {
        if (potenziamentoAttivo) {
            bersaglio.riceviDanni(this.danno * 2);
            potenziamentoAttivo = false;
        } else {
            bersaglio.riceviDanni(this.danno);
            potenziamentoAttivo = true;
        }
    }
}
