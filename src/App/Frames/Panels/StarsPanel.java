package App.Frames.Panels;

import javax.swing.*;
import java.awt.*;

public class StarsPanel extends JPanel
{
    public StarsPanel(float f)
    {
        super(new GridLayout(1, 5));
        setOpaque(false);
        JLabel[] starY = new JLabel[5];
        ImageIcon starIcon = new ImageIcon("src/App/Images/yellowstar.png");
        starIcon.setImage(starIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        for (int j = 0; j < 5; j++) {
            starY[j] = new JLabel();
            starY[j].setIcon(starIcon);
        }

        for (int j = 0; j < f; j++)
        {
            add(starY[j]);
        }
    }
}
