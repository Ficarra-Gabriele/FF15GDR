/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public abstract class Nemico implements IEntitaDanneggiabile {
    
    protected String nome;
    protected int hp;
    protected int danno;

    public Nemico() {
        this.nome = nome;
        this.hp = hp;
        this.danno = 352;
    }
         
    public int getDanno(){
        return danno;
    }
    public void eseguiMossa(IEntitaDanneggiabile bersaglio){
        
    }
    
}