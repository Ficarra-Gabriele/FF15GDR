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
public class GameManager {

    private Noctis giocatore;
    private Nemico nemicoCorrente;
    private GestoreEventi gestoreEventi;
    private String ultimoEventoImg = "strada";
    private String descrizioneEvento = "Il viaggio verso Insomnia ha inizio. L'asfalto della strada brilla sotto il sole di Eos mentre i motori della Regalia rombano. Esplora le terre selvagge per trovare Ardyn e reclamare il trono.";
    private int passiTotali, passiFatti = 0;
    private boolean battagliaAttiva = false;

    public GameManager(Noctis n) {
        this.giocatore = n;
        this.gestoreEventi = new GestoreEventi();
        this.passiTotali = new Random().nextInt(18, 31);
    }

    public void esplora() {
        if (battagliaAttiva || giocatore.getStamina() <= 0) {
            return;
        }
        giocatore.setStamina(giocatore.getStamina() - 1);
        passiFatti++;
        giocatore.incrementaPassi();
        controlloProgressione();
    }

    private void controlloProgressione() {
        if (passiFatti >= passiTotali) {
            nemicoCorrente = new Ardyn();
            battagliaAttiva = true;
            ultimoEventoImg = "Ardyn";
            descrizioneEvento = "L'ARIA SI FA PESANTE: Le tenebre si addensano improvvisamente. Davanti a te, con un sorriso beffardo, appare Ardyn Izunia. 'Il tempo della tua stirpe è finalmente giunto al termine, Noctis. Vediamo se il Re Prescelto è all'altezza della sua leggenda.'";
        } else {
            Object ev = gestoreEventi.generaEvento();
            processaEvento(ev);
        }
    }

    private void processaEvento(Object ev) {
        if (ev instanceof Nemico n) {
            nemicoCorrente = n;
            battagliaAttiva = true;
            ultimoEventoImg = n.getNome();
            descrizioneEvento = "NEMICO IN VISTA: Un feroce " + n.getNome() + " sbarra il tuo cammino! Le creature di Eos sono diventate agitate a causa della piaga delle stelle. Questa bestia non sembra intenzionata a lasciarti passare senza lottare.";

            if (n.getNome().toLowerCase().contains("pollorosa")) {
                giocatore.setStamina(giocatore.getStamina() - 2);
                descrizioneEvento = "Attento! L'attacco a sorpresa del 'Grande Pollo Rosa' ti ha lasciato stordito, facendoti perdere ben 2 punti Stamina. La sua ferocia è pari solo al suo bizzarro piumaggio.";
            }
        } else if (ev instanceof Oggetto o) {
            giocatore.getInventario().add(o);
            descrizioneEvento = "TESORO TROVATO! I tuoi occhi scorgono un riflesso tra le rocce. Hai recuperato " + o.toString() + "!Questo artefatto potrebbe rivelarsi decisivo per la tua sopravvivenza.";
            if (o instanceof Bandana) {
                ultimoEventoImg = o.toString().toLowerCase().replace(" ", "").replace("'", "");
            } else if (o instanceof Pozione p) {
                ultimoEventoImg = "pozione" + p.getTipo().toLowerCase();
            } else {
                ultimoEventoImg = "tesoro";
            }
        } else if (ev instanceof String s && s.equals("chocobo")) {
            passiFatti += 4;
            ultimoEventoImg = "chocobo";
            descrizioneEvento = "KWEH! Un Chocobo selvatico si è avvicinato amichevolmente. Saltando in groppa al nobile pennuto, riesci a percorrere un vasto tratto di strada in pochi minuti";
            if (passiFatti >= passiTotali) {
                passiFatti = passiTotali;
            }
            controlloProgressione();
        }
    }

    public void eseguiTurno(String azione) {
        if (!battagliaAttiva) {
            return;
        }
        switch (azione) {
            case "ATTACCO" -> {
                nemicoCorrente.riceviDanni(giocatore.getDanno());
                descrizioneEvento = "SFERRI UN ATTACCO: La tua arma si materializza tra le mani. Colpisci il nemico con determinazione!";
            }
            case "SPELL" -> {
                giocatore.castSpell(nemicoCorrente);
                descrizioneEvento = "MAGIA ELEMENTALE: Canalizzi il potere del Cristallo per scagliare un incantesimo devastante!";
            }
            case "WARP" -> {
                giocatore.warpStrike(nemicoCorrente);
                descrizioneEvento = "PROIEZIONE OFFENSIVA: Lanci la tua arma e ti smaterializzi per riapparire istantaneamente sul nemico, colpendolo con una forza cinetica incredibile!";
            }
            case "DIFESA" -> {
                giocatore.dif = giocatore.dif + 500;
                if (giocatore.dif > giocatore.difMax) {
                    giocatore.dif = giocatore.difMax;
                }
                descrizioneEvento = "POSIZIONE DIFENSIVA: Evochi lo scudo dei Re Lucis. La tua difesa aumenta temporaneamente, preparandoti a incassare il prossimo colpo.";
            }
        }
        if (nemicoCorrente.isVivo()) {
            nemicoCorrente.eseguiMossa(giocatore);
        } else {
            battagliaAttiva = false;
            giocatore.aggiungiGuil(200);
            descrizioneEvento = "VITTORIA ECLISSANTE! Il nemico si dissolve in particelle di oscurità. La minaccia è stata sventata e la via è di nuovo libera. Hai recuperato 200 Guil dai resti del nemico.";
        }
    }

    public String getUltimoEventoImg() {
        return ultimoEventoImg;
    }

    public String getDescrizioneEvento() {
        return descrizioneEvento;
    }

    public boolean isBattagliaAttiva() {
        return battagliaAttiva;
    }

    public Nemico getNemicoCorrente() {
        return nemicoCorrente;
    }

    public int getPassiRimanenti() {
        int rimanenti = passiTotali - passiFatti;
        if (rimanenti < 0) {
            return 0;
        } else {
            return rimanenti;
        }
    }

    public boolean isGameOver() {
        if (giocatore.isVivo() == false || giocatore.getStamina() < 0) {
            return true;
        }
        return false;
    }

    public boolean isVittoriaFinale() {
        return nemicoCorrente instanceof Ardyn && !nemicoCorrente.isVivo();
    }

}
