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
    private PersonaggioBase giocatore;
    private TurnManager turnManager;
    private GestoreEventi gestoreEventi;
    private boolean giocoAttivo;
    private int passiTotali;
    private Random rdn = new Random();
    
    public int numeroPassi(){ // mi dice il numero di passi da fare
        int nPassi = rdn.nextInt(12, 25);
        return nPassi; 
    }
    public void avviaGioco(){
        
    } 
    public void iniziaEsplorazione(){
        
    }
    public void gestisciEvento(){
        
    }
    public void iniziaBattaglia(Nemico n){
        
    }
    public void termniaBattaglia(){
        
    }
    public void aggiungiOggetto(Oggetto o){
        
    }
    public void salvaGioco(){ //quando clicco sulla form "salva gioco" richiamo questo metodo che richiama "salvaCSV" nel FileManager
        
    }
    public void caricaGioco(){
        
    }
    public boolean controllaGameOver(){
       return giocoAttivo; 
    }
    
}
