package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge ;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.clients.CompteARebours;

import java.util.Random;


/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        //testDuTimeService();
        testCompteARebours();


    }

    private static void testDuTimeService() {
        TimerService t1 = new DummyTimeServiceImpl();

        Horloge horloge1 = new Horloge("Num 1", t1);
        Horloge horloge2 = new Horloge("Num 2", t1);
        Horloge horloge3 = new Horloge("Num 3", t1);
    }
    private static void testCompteARebours() {
        TimerService t1 = new DummyTimeServiceImpl();

        // Test avec une valeur de 5
        CompteARebours compte1 = new CompteARebours("Compte-1", 5, t1);

        // Test avec 10 comptes à rebours avec valeurs aléatoires entre 10 et 20
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int valeur = 10 + random.nextInt(11); // 10 à 20
            new CompteARebours("Compte-" + (i + 2), valeur, t1);
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
