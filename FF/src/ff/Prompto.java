/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public class Prompto extends PersonaggioBase{
    
    private final int aumentoStamina = 5;

    public Prompto() {
        super();
        this.stamina += aumentoStamina;
        this.staminaMax += aumentoStamina;
    }
    
}
