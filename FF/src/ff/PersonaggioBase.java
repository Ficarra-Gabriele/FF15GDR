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

    protected int hp;
    protected int hpMax;
    protected int dif;
    protected int difMax;
    protected int stamina;
    protected int staminaMax;
    protected int passicompiuti;
    protected int danno;
    protected boolean parry;
    protected boolean haIlChocobo;
    protected ArrayList<Oggetto> inventario = new ArrayList<>();
    
    public void riceviDanno(int quantita){
        
    }
    public void usaPozione(Pozione p){
        
    }
    public void equipaggiaBandana(Bandana b){
        
    }
}