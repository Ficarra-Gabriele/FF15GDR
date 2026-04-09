/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

import java.util.Random;

/**
 *
 * @author ficarra.gabriele
 */
public class GestoreEventi {

    private Random rand = new Random();

    public Object generaEvento() {
        int r = rand.nextInt(100);

        if (r < 10) {
            return "chocobo";
        }
        if (r < 20) {
            return new GolemDiElisir();
        }
        if (r < 30) {
            return new GrandePolloRosa();
        }
        if (r < 40) {
            return new GrandeRospoAzzurro();
        }
        if (r < 50) {
            return new GrandeTacchinoImperatore();
        }
        if (r < 60) {
            return new Pozione("HP");
        }
        if (r < 70) {
            return new Pozione("MANA");
        }
        if (r < 80) {
            return new Pozione("STAMINA");
        }
        if (r < 90) {
            return new Pozione("DIFESA");
        }
        if (r <= 100) {
            return new Boss();
        }

        return generaBandanaRara();
    }

    private Bandana generaBandanaRara() {
        int estrazione = rand.nextInt(1000) + 1;

        if (estrazione == 1000) {
            return new Bandana(5);
        }
        if (estrazione > 980) {
            return new Bandana(4);
        }
        if (estrazione > 947) {
            return new Bandana(3);
        }
        if (estrazione > 897) {
            return new Bandana(2);
        }
        if (estrazione > 797) {
            return new Bandana(1);
        }
        return new Bandana(1);
    }
}
