import javax.swing.*;
import java.awt.*;

public class LifeBar extends  JProgressBar {
    JFrame displayZoneFrame;
    public LifeBar(JFrame displayZoneFrame) {
        // Créer et configurer la barre de vie
        super(0, 100); // Valeur minimale 0, maximale 100
        setValue(100); // Valeur initiale (pleine)
        setStringPainted(true); // Afficher le texte de la valeur
        setForeground(Color.GREEN); // Couleur de la barre pleine
        setBackground(Color.RED); // Couleur d'arrière-plan
        // Définir une taille préférée pour la barre de vie
        setPreferredSize(new Dimension(400, 30)); // Largeur de 400, hauteur de 30
        setStringPainted(true); // Ne pas afficher le texte
        this.displayZoneFrame = displayZoneFrame;
    }

    public void damage() {
        setValue(getValue() - 5);
        displayZoneFrame.repaint();
    }
}
