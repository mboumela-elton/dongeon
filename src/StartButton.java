import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class StartButton extends JButton {
    private String buttonName;
    Timer timer;
    JFrame displayZoneFrame;

    public StartButton(String buttonName, JFrame displayZoneFrame0) {
        super(buttonName);
        displayZoneFrame = displayZoneFrame0;
        displayZoneFrame.setLayout(new FlowLayout());
        setFont(new Font("Arial", Font.BOLD, 20));

        // Couleurs de base
        setBackground(Color.decode("#4CAF50")); // Vert
        setForeground(Color.WHITE);

        // Ajouter une bordure arrondie
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.decode("#388E3C"), 2));

        // Ajouter du padding
        setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));

        // Animation de texte
        timer = new Timer(30, null);
        AtomicReference<Boolean> test = new AtomicReference<>(true);
        timer.addActionListener(e -> {
            Font font = getFont();
            float newSize = font.getSize2D() + (test.get() ? 1 : -1);
            setFont(font.deriveFont(newSize));

            if (newSize > 50) {
                test.set(false);
            }
            if (newSize < 20) {
                test.set(true);
            }

        });
        timer.start();
    }

        // Effets de survol
    public void displayGame(){
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                setForeground(Color.YELLOW); // Change le texte en gris
            }

            public void mouseExited(MouseEvent evt) {
                setForeground(Color.WHITE); // Réinitialise le texte à blanc
            }

            public void mousePressed(MouseEvent evt) {
                timer.stop();
                displayZoneFrame.getContentPane().removeAll();
                try {
                    displayZoneFrame.setVisible(false);
                    displayZoneFrame.setLayout(new BorderLayout());
                    new GamePage(displayZoneFrame);
                    displayZoneFrame.setVisible(true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        // Adding the button to the frame
        displayZoneFrame.add(this, BorderLayout.CENTER);
    }
}
