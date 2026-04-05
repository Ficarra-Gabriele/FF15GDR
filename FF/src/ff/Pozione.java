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

    private String tipo;

    public Pozione(String t) {
        this.tipo = t;
    }

    public String getTipo() {
        return tipo;
    }

    public void applicaEffetto(Noctis n) {
    switch (tipo) {
        case "HP" -> 
            n.setHp(n.getHp() + (n.getHpMax() * 20 / 100));
        case "MANA" -> 
            n.rigeneraMana(n.getManaMax() * 20 / 100);
        case "STAMINA" -> 
            n.applicaEffetto("CURA_STAMINA", 3); 
    }
}

    @Override
    public String toString() {
        return "Pozione di " + tipo; 
    }}
