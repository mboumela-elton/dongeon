import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RenderEngine extends JPanel implements Engine {
    private List<Displayable> renderList;

    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
    }

    public void setRenderList(List<Displayable> renderList) {
        this.renderList = renderList;
    }

    public void addToRenderList(Displayable displayable) {
        renderList.add(displayable);
    }

    public void addToRenderList(ArrayList<Displayable> displayable) {
        if (!renderList.contains(displayable)) {
            renderList.addAll(displayable);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        renderList.forEach(displayable -> displayable.draw(g));
    }


    @Override
    public void update() {
        repaint();
//        System.out.println("fonction update activée");
    }
}
