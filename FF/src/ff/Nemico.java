/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

import java.io.Serializable;

/**
 *
 * @author ficarra.gabriele
 */
public abstract class Nemico implements IEntitaDanneggiabile, Serializable {

    protected String nome;
    protected int hp, hpMax, danno;
    protected boolean specialeUsata = false;

    public abstract void eseguiMossa(IEntitaDanneggiabile bersaglio);

    public int getHp() {
        return hp;
    }

    public int getHpMax() {
        return hpMax;
    }

    public String getNome() {
        return nome;
    }

    public boolean isVivo() {
        return hp > 0;
    }

    public void riceviDanni(int q) {
        this.hp = Math.max(0, this.hp - q);
    }
    @Override
    public void applicaEffetto(String tipoEffetto, int valore) {
        
    }

}
