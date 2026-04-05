/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ff;

/**
 *
 * @author ficarra.gabriele
 */
public class Noctis extends PersonaggioBase {

    private int turniVeleno = 0;
    private int passiMalus = 0;
    private int mana = 3000;
    private int manaMax = 3000;
    private int guil = 500;
    private int passi = 0;
    private Bandana bandanaEquipaggiata = null;
    private Spada spadaEquipaggiata = null;

    public Noctis() {
        this.hp = 8000;
        this.hpMax = 8000;
        this.danno = 570;
        this.dif = 3000;
        this.difMax = 3000;
        this.stamina = 20;
        this.staminaMax = 20;
    }

    public void castSpell(IEntitaDanneggiabile b) {
        if (this.mana >= 100) {
            this.mana -= 100;
            b.riceviDanni(500);
        }
    }

    public void warpStrike(IEntitaDanneggiabile b) {
        if (this.mana >= 1000) {
            this.mana -= 1000;
            b.riceviDanni(1100);
        }
    }

    @Override
    public void riceviDanni(int q) {
        if (this.dif > 0) {
            int assorbito = Math.min(q, this.dif);
            this.dif -= assorbito;
            int rimanente = q - assorbito;
            this.hp -= rimanente;
        } else {
            this.hp -= q;
        }
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    @Override
    public void applicaEffetto(String tipo, int valore) {
        switch (tipo) {
            case "VELENO" ->
                this.turniVeleno = valore;
            case "RALLENTA" ->
                this.passiMalus += valore;
            case "STAMINA_DOWN" ->
                this.setStamina(this.getStamina() - valore);
            case "CURA_STAMINA" -> {
                this.setStamina(this.getStamina() + valore);
                System.out.println("Stamina rigenerata di " + valore);
            }
        }
    }

    public void aggiornaStati() {
        if (turniVeleno > 0) {
            riceviDanni(150);
            turniVeleno--;
        }
    }

    public int getPassiMalus() {
        return passiMalus;
    }

    public void equipaggiaBandana(Bandana b) {
        if (bandanaEquipaggiata != null) {
            bandanaEquipaggiata.rimuoviEffetto(this);
        }
        this.bandanaEquipaggiata = b;
        b.applicaEffetto(this);
    }

    public void equipaggiaSpada(Spada s) {
        if (spadaEquipaggiata != null) {
            spadaEquipaggiata.rimuoviEffetto(this);
        }
        this.spadaEquipaggiata = s;
        s.applicaEffetto(this);
    }

    public Bandana getBandanaEquipaggiata() {
        return bandanaEquipaggiata;
    }

    public void rigeneraMana(int q) {
        this.mana = Math.min(this.mana + q, this.manaMax);
    }

    public int getGuil() {
        return guil;
    }

    public void aggiungiGuil(int g) {
        this.guil += g;
    }

    public void spendiGuil(int c) {
        this.guil -= c;
    }

    public void setHpMax(int n) {
        this.hpMax = n;
    }

    public void setManaMax(int n) {
        this.manaMax = n;
    }

    public void setHp(int n) {
        this.hp = Math.max(0, Math.min(n, hpMax));
    }

    public void setStamina(int n) {
        this.stamina = Math.max(0, Math.min(n, staminaMax));
    }

    public int getMana() {
        return mana;
    }

    public int getManaMax() {
        return manaMax;
    }

    public int getPassi() {
        return passi;
    }

    public void incrementaPassi() {
        this.passi++;
    }
}
