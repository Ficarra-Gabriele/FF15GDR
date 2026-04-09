/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

import java.util.ArrayList;

/**
 *
 * @author ironm
 */
public class Negozio {

    private ArrayList<Oggetto> merce = new ArrayList<>();

    public Negozio() {

        merce.add(new Spada("Spada Motrice", 100, 1200));
        merce.add(new Spada("Ultima Blade", 500, 4500));
        merce.add(new Spada("Balmung", 1200, 12000));

        for (int i = 0; i < 5; i++) {
            merce.add(new Pozione("HP"));
            merce.add(new Pozione("MANA"));
            merce.add(new Pozione("STAMINA"));
        }

        merce.add(new Bandana(1));
        merce.add(new Bandana(2));
        merce.add(new Bandana(3));
        merce.add(new Bandana(4));
        merce.add(new Bandana(5));
    }

    public ArrayList<Oggetto> getMerce() {
        return merce;
    }

}
