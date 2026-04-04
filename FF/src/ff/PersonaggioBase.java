/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

import java.util.ArrayList;

/**
 *
 * @author ficarra.gabriele
 */
public abstract class PersonaggioBase implements IEntitaDanneggiabile {

    protected int hp, hpMax, dif, difMax, stamina, staminaMax, danno;
    protected ArrayList<Oggetto> inventario = new ArrayList<>();

    public int getHp() {
        return hp;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getDif() {
        return dif;
    }

    public int getDanno() {
        return danno;
    }

    public int getStamina() {
        return stamina;
    }

    public boolean isVivo() {
        return hp > 0;
    }

    public ArrayList<Oggetto> getInventario() {
        return inventario;
    }
}
