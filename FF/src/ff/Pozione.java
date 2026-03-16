/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
class Pozione extends Oggetto {
    private String tipoStat;
    private int potenza;
    private boolean raccoglibile;
    
    public String getTipoStat(){
        return tipoStat;
    }
    
    public int getPotenza(){
        return potenza;
    }
}