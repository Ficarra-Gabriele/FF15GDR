/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public class Ignis extends PersonaggioBase {
    
    private final int aumentoDifesa = 4;
    
    public Ignis(){
        super();
        this.dif += aumentoDifesa;
        this.difMax += aumentoDifesa;
    }
}