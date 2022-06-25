package App.Frames;

import App.Accounts.Account;
import App.Accounts.User;
import App.FilesHandler.GamesFile;
import App.FilesHandler.UserGamesFile;
import App.FilesHandler.UsersFile;
import App.Products.Product;
import App.Review;
import App.Steam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReviewFrame extends JFrame
{
    public ReviewFrame(User user, Product product, JFrame toClose)
    {
        super();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, (int) (screenSize.height * .8f));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Review");
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(36, 44, 68));

        JLabel titleLabel = new JLabel("Write a review for " + product.getName());
        titleLabel.setForeground(Color.white);
        titleLabel.setBounds(10, 10, getWidth() - 20, 50);
        titleLabel.setFont(new java.awt.Font("Arial", Font.BOLD, 20));

        JPanel starsPanel = new JPanel(new GridLayout(1, 5));
        starsPanel.setOpaque(false);
        starsPanel.setBounds(25, 100, getWidth() / 2, 50);

        JLabel[] starLabel = new JLabel[5];

        ImageIcon starIcon = new ImageIcon("src/App/Images/star.png");
        starIcon.setImage(starIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

        ImageIcon yellowStarIcon = new ImageIcon("src/App/Images/yellowStar.png");
        yellowStarIcon.setImage(yellowStarIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

        final int[] starCount = {0};
        for(int i = 0; i < 5; i++)
        {
            starLabel[i] = new JLabel(starIcon);
            int finalI = i;
            int finalI1 = i;
            starLabel[i].addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    starCount[0] = finalI1 + 1;
                    for (int j = 0; j < 5; j++) {
                        if(j<= finalI)
                        {
                            starLabel[j].setIcon(yellowStarIcon);
                        }
                        else
                        {
                            starLabel[j].setIcon(starIcon);
                        }
                    }
                }
            });
            starsPanel.add(starLabel[i]);
        }

        JTextArea reviewTextArea = new JTextArea();
        reviewTextArea.setBounds(10, 200, (int) (getWidth() * .75f), 200);
        reviewTextArea.setLineWrap(true);
        reviewTextArea.setWrapStyleWord(true);
        reviewTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        reviewTextArea.setBackground(Color.WHITE);
        reviewTextArea.setForeground(Color.BLACK);
        reviewTextArea.setFont(new java.awt.Font("Arial", Font.ITALIC, 20));

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(10, 400, (int) (getWidth() * .75f), 50);
        submitButton.setBackground(new Color(36, 44, 68));
        submitButton.setForeground(Color.white);
        submitButton.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
        submitButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(reviewTextArea.getText().length() > 0)
                {
                    Review newReview = new Review(user.getName(), reviewTextArea.getText(), starCount[0]);
                    product.addReview(newReview);
                    GamesFile.addReview(product.getId(), newReview);
                    JOptionPane.showMessageDialog(null, "Review submitted!");
                    toClose.dispose();
                    dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please write a review!");
                }
            }
        });

        mainPanel.add(titleLabel);
        mainPanel.add(starsPanel);
        mainPanel.add(reviewTextArea);
        mainPanel.add(submitButton);

        add(mainPanel);

        setVisible(true);
    }
}
