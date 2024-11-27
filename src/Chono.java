import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chono extends  JLabel{
    private JFrame frame;
    private Timer timer;
    private int secondsElapsed;

    public  Chono(JFrame frame) {
        // Initialiser le JLabel pour afficher le temps
        super("Temps écoulé : 0 secondes");
        setFont(new Font("Arial", Font.PLAIN, 24));

        // Initialiser le timer
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsElapsed++; // Incrémenter le compteur
                setText("Temps écoulé : " + secondsElapsed + " secondes"); // Mettre à jour l'affichage
            }
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
}
