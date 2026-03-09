/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public class Gladiolus extends PersonaggioBase {

    private final int aumentoVita = 5;
    
    public Gladiolus() {
        super();
        this.hp += aumentoVita;
        this.hpMax += aumentoVita;
    }
    
}
