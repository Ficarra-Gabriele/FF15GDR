/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public class Ardin extends Nemico{

    private boolean specialeDisponibile = true;
    private int aumentaDanno;
    
    public void evocaIfrit(IEntitaDanneggiabile e ){
        e.riceviDanni(2500);
    } 
    
    private int moltiplicatoreDanno = 1000;
    
    public void aumentoDanno(Nemico n){
       int danno = n.getDanno();
       danno += moltiplicatoreDanno;
    }
    
    @Override
    public int getHp() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void riceviDanni(int quantità) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isVivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
