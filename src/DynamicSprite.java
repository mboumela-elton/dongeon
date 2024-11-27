import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {
    private boolean isWorking = true;
    private double speed = 5.0;
    private double spriteSheetNumberOfColumn = 10;
    private double timeBetweenFrame = 200;
    private Direction direction = Direction.EAST;
    LifeBar lifeBar;
    private static long timer = 0;
    private Chono chono;

    public DynamicSprite(Image image, double x, double y, double width, double height, LifeBar lifeBar, Chono chono) {
        super(image, x, y, width, height);
        this.lifeBar = lifeBar;
        this.chono = chono;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void draw(Graphics g) {
        int index = (int) (System.currentTimeMillis() / timeBetweenFrame % spriteSheetNumberOfColumn);

        g.drawImage(image, (int) x, (int) y, (int) (x + width), (int) (y + height),
                (int) (index * this.width), (int) (direction.getFrameLineNumber() * height),
                (int) ((index + 1) * this.width), (int) ((direction.getFrameLineNumber() + 1) * this.height), null);
    }

    private void move() {
        switch (direction) {
            case NORTH -> {
                this.y -= speed;
            }
            case SOUTH -> {
                this.y += speed;
            }
            case WEST -> {
                this.x -= speed;
            }
            case EAST -> {
                this.x += speed;
            }
        }
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        Rectangle2D.Double moved = new Rectangle2D.Double();
        switch (direction) {
            case EAST:
                moved.setRect(super.getHitBox().getX() + speed, super.getHitBox().getY(),
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case WEST:
                moved.setRect(super.getHitBox().getX() - speed, super.getHitBox().getY(),
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case NORTH:
                moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() - speed,
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case SOUTH:
                moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() + speed,
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
        }

        for (Sprite s : environment) {
            if ((s instanceof SolidSprite) && (s != this)) {
                if (((SolidSprite) s).intersect(moved)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void moveIfPossible(ArrayList<Sprite> environment) {
        if(Math.abs(Playground.outX - x) < 20 && Math.abs(Playground.outY - y) < 20){
            chono.stop();
        }
        if (isMovingPossible(environment)) {
            move();
        } else if (timer == 0) {
            lifeBar.damage();
            timer += 50;
        }
        if (timer <= 2000 && timer >= 50) {
            timer += 50;
        } else {
            timer = 0;
        }
    }
}
