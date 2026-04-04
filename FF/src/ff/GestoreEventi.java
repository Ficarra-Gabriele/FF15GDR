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

        return new Bandana(rand.nextInt(5) + 1);
    }
}
