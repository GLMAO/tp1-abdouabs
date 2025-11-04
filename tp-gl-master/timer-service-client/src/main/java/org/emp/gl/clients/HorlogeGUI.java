package org.emp.gl.clients;
import org.emp.gl.time.service.impl.*;
import javax.swing.*;
import java.awt.*;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 * Interface graphique affichant l'heure en temps réel
 */
public class HorlogeGUI extends JFrame implements TimerChangeListener {

    private TimerService timerService;
    private JLabel timeLabel;
    private JLabel dateLabel;

    public HorlogeGUI(TimerService timerService) {
        this.timerService = timerService;

        // Configuration de la fenêtre
        setTitle("Horloge Digitale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Créer l'interface
        createUI();

        // S'inscrire comme observer
        this.timerService.addTimeChangeListener(this);

        // Affichage initial
        updateTime();
    }

    private void createUI() {
        // Panel principal avec fond sombre
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(30, 30, 30));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Label pour l'heure
        timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Digital-7", Font.BOLD, 72));
        timeLabel.setForeground(new Color(0, 255, 0));

        // Label pour la date
        dateLabel = new JLabel("", SwingConstants.CENTER);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        dateLabel.setForeground(new Color(200, 200, 200));

        // Ajouter les composants
        mainPanel.add(timeLabel, BorderLayout.CENTER);
        mainPanel.add(dateLabel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void updateTime() {
        // Formater l'heure avec des zéros devant si nécessaire
        String heures = String.format("%02d", timerService.getHeures());
        String minutes = String.format("%02d", timerService.getMinutes());
        String secondes = String.format("%02d", timerService.getSecondes());

        // Mettre à jour le label (sur le thread EDT)
        SwingUtilities.invokeLater(() -> {
            timeLabel.setText(heures + ":" + minutes + ":" + secondes);

            // Mettre à jour la date
            java.time.LocalDate today = java.time.LocalDate.now();
            java.time.format.DateTimeFormatter formatter =
                    java.time.format.DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy");
            dateLabel.setText(today.format(formatter));
        });
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        // Mettre à jour l'affichage à chaque changement de seconde
        if (TimerChangeListener.SECONDE_PROP.equals(prop)) {
            updateTime();
        }
    }

    // Méthode principale pour lancer l'application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Créer le service timer
            TimerService timerService = new org.emp.gl.time.service.impl.DummyTimeServiceImpl();

            // Créer et afficher l'horloge graphique
            HorlogeGUI horloge = new HorlogeGUI(timerService);
            horloge.setVisible(true);
        });
    }
}