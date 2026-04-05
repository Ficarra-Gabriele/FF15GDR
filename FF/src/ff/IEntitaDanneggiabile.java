/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public interface IEntitaDanneggiabile {

    int getHp();

    void riceviDanni(int quantita);

    boolean isVivo();

    void applicaEffetto(String tipoEffetto, int valore);

}
