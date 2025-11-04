package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class CompteARebours implements TimerChangeListener {

    private String name;
    private TimerService timerService;
    private int compteur;

    public CompteARebours(String name, int valeurInitiale, TimerService timerService) {
        this.name = name;
        this.compteur = valeurInitiale;
        this.timerService = timerService;

        // S'inscrire comme observer
        this.timerService.addTimeChangeListener(this);

        System.out.println("CompteARebours " + name + " initialisé avec " + compteur + " secondes");
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        // Réagir uniquement aux changements de seconde
        if (TimerChangeListener.SECONDE_PROP.equals(prop)) {
            if (compteur > 0) {
                compteur--;
                System.out.println(name + " : " + compteur);

                // Se désinscrire quand le compte à rebours atteint 0
                if (compteur == 0) {
                    System.out.println(name + " : TERMINÉ ! Se désinscrit...");
                    timerService.removeTimeChangeListener(this);
                }
            }
        }
    }

    public int getCompteur() {
        return compteur;
    }
}