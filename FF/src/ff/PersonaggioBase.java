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
    protected boolean parry;
    protected boolean haIlChocobo;
    protected ArrayList<Oggetto> inventario = new ArrayList<>();

    public PersonaggioBase() {
        this.hp = 15;
        this.hpMax = 15;
        this.dif = 5;
        this.difMax = 5;
        this.stamina = 10;
        this.staminaMax = 10;
        this.passicompiuti = 0;
    }
    
    public void riceviDanno(int quantita){
        
    }
    public void usaPozione(Pozione p){
        
    }
    public void equipaggiaBandana(Bandana b){
        
    }
}