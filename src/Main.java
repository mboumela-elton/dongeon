import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame;
    GamePage gamePage;

    public Main() throws Exception {
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400, 600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Créer un bouton de démarrage
        StartButton button = new StartButton("Start", displayZoneFrame);

        // fonction permettant d'afficher le jeu
        button.displayGame();

        displayZoneFrame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        // write your code here
        Main main = new Main();
    }
}