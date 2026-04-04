/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public class TurnManager {

private int turnoCorrente;
    
    public void prossimoTurno(){
        turnoCorrente++;
    }
    
    public void IniziaBattaglia(){
        turnoCorrente = 1;
    }
}
