import java.util.ArrayList;
import java.util.List;

public class PhysicEngine implements Engine {
    private ArrayList<DynamicSprite> movingSpriteList;
    private ArrayList<Sprite> environment;

    public PhysicEngine() {
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
    }

    public void addToMovingSpriteList(DynamicSprite dynamicSprite) {
        movingSpriteList.add(dynamicSprite);
    }

    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }


    @Override
    public void update() {
        movingSpriteList.forEach(dynamicSprite -> dynamicSprite.moveIfPossible(environment));
    }
}
