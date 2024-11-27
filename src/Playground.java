import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

// La classe Playground représente un environnement de jeu composé de divers sprites.
public class Playground {
    static long outX;
    static long outY;
    // ArrayList pour contenir différents sprites dans l'environnement
    private ArrayList<Sprite> environment = new ArrayList<>();

    // Constructeur qui initialise le Playground avec des sprites à partir d'un fichier spécifié
    public Playground(String pathName) {
        try {
            // Chargement des images pour différents types de sprites
            final Image imageTree = ImageIO.read(new File("./img/tree.png"));
            final Image imageGrass = ImageIO.read(new File("./img/grass.png"));
            final Image imageRock = ImageIO.read(new File("./img/rock.png"));
            final Image imageTrap = ImageIO.read(new File("./img/trap.png")); // Note : imageTrap est chargée mais non utilisée

            // Obtention des dimensions de l'image de l'arbre
            final int imageTreeWidth = imageTree.getWidth(null);
            final int imageTreeHeight = imageTree.getHeight(null);

            // Obtention des dimensions de l'image de l'herbe
            final int imageGrassWidth = imageGrass.getWidth(null);
            final int imageGrassHeight = imageGrass.getHeight(null);

            // Obtention des dimensions de l'image de la roche
            final int imageRockWidth = imageRock.getWidth(null);
            final int imageRockHeight = imageRock.getHeight(null);

            // Lecture de la disposition du terrain à partir d'un fichier spécifié par pathName
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line = bufferedReader.readLine(); // Lecture de la première ligne du fichier
            int lineNumber = 0; // Pour suivre la ligne actuelle dans le fichier
            int columnNumber = 0; // Pour suivre la colonne actuelle dans la ligne

            // Boucle à travers chaque ligne du fichier jusqu'à ce qu'il n'y ait plus de lignes
            while (line != null) {
                // Pour chaque octet (caractère) dans la ligne
                for (byte element : line.getBytes(StandardCharsets.UTF_8)) {
                    // Instruction switch pour déterminer quel sprite créer en fonction du caractère
                    switch (element) {
                        case 'T': // Si le caractère est 'T', créer un sprite d'arbre
                            environment.add(new SolidSprite(imageTree, columnNumber * imageTreeWidth,
                                    lineNumber * imageTreeHeight, imageTreeWidth, imageTreeHeight));
                            break;
                        case ' ': // Si le caractère est un espace, créer un sprite d'herbe
                            environment.add(new Sprite(imageGrass, columnNumber * imageGrassWidth,
                                    lineNumber * imageGrassHeight, imageGrassWidth, imageGrassHeight));
                            break;
                        case 'R': // Si le caractère est 'R', créer un sprite de roche
                            environment.add(new SolidSprite(imageRock, columnNumber * imageRockWidth,
                                    lineNumber * imageRockHeight, imageRockWidth, imageRockHeight));
                            break;
                        case '.':
                            outX = columnNumber * imageTreeWidth;
                            outY = lineNumber * imageTreeHeight;
                            break;
                    }
                    columnNumber++; // Passer à la colonne suivante
                }
                columnNumber = 0; // Réinitialiser le numéro de colonne pour la prochaine ligne
                lineNumber++; // Passer à la ligne suivante
                line = bufferedReader.readLine(); // Lire la ligne suivante
            }
        } catch (Exception e) {
            e.printStackTrace(); // Afficher la trace de la pile en cas d'exception
        }
    }

    // Méthode pour obtenir une liste de tous les sprites solides dans l'environnement
    public ArrayList<Sprite> getSolidSpriteList() {
        ArrayList<Sprite> solidSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite) // Vérifier si le sprite est un SolidSprite
                solidSpriteArrayList.add(sprite); // Ajouter à la liste si c'est le cas
        }
        return solidSpriteArrayList; // Retourner la liste des sprites solides
    }

    // Méthode pour obtenir une liste de tous les sprites affichables dans l'environnement
    public ArrayList<Displayable> getSpriteList() {
        ArrayList<Displayable> displayableArrayList = new ArrayList<>();
        for (Sprite sprite : environment) {
            displayableArrayList.add((Displayable) sprite); // Convertir chaque sprite en Displayable et l'ajouter à la liste
        }
        return displayableArrayList; // Retourner la liste des sprites affichables
    }
}