import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class GamePage {
    private RenderEngine renderEngine;
    private GameEngine gameEngine;
    private PhysicEngine physicEngine;

    private LifeBar lifeBar;

    public GamePage(JFrame displayZoneFrame) throws IOException {
        // Ajouter la barre de vie à la fenêtre
        lifeBar = new LifeBar(displayZoneFrame);
        displayZoneFrame.add(lifeBar, BorderLayout.NORTH); // Positionner en haut

        Chono chono = new Chono(displayZoneFrame);
        chono.start();
        displayZoneFrame.add(chono, BorderLayout.SOUTH);

        DynamicSprite hero = new DynamicSprite(ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 200, 300, 48, 50, lifeBar, chono);

        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);

        Timer renderTimer = new Timer(50, (time) -> {
            renderEngine.update();
        });
        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        Timer physicTimer = new Timer(50, (time) -> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.getContentPane().add(renderEngine);

        Playground level = new Playground("./data/level1.txt");

        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);

    }
}
