package App.Frames;

import App.Accounts.User;
import App.Exceptions.AlreadyInListException;
import App.FilesHandler.UserGamesFile;
import App.Frames.Panels.StarsPanel;
import App.Products.Game;
import App.Products.Product;
import App.Review;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

public class ProductFrame extends JFrame
{
    public ProductFrame(User user, Product product, JLabel cart)
    {
        super(product.getName());
        setTitle(product.getName());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, (int) (screenSize.height * 0.8f));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBounds(0, 0, getWidth(), getHeight());
        mainPanel.setPreferredSize(new Dimension(getWidth() - 50, getHeight()));

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(null);
        gamePanel.setBackground(new Color(36, 44, 68));
        gamePanel.setBounds(100, 10, mainPanel.getWidth() - 200, 1200);

        JLabel gameName = new JLabel(product.getName());
        gameName.setForeground(Color.WHITE);
        gameName.setFont(new Font("Arial", Font.BOLD, 30));
        gameName.setHorizontalAlignment(SwingConstants.CENTER);
        gameName.setVerticalAlignment(SwingConstants.CENTER);
        gameName.setBounds(0, 25, gamePanel.getWidth(), 50);

        StarsPanel starsPanel = new StarsPanel(product.getRating());
        starsPanel.setBounds(gamePanel.getWidth() / 4, 150, gamePanel.getWidth() / 3, 50);

        gamePanel.add(gameName);
        gamePanel.add(starsPanel);

        if(product instanceof Game)
        {
            JPanel lenguagePanel = new JPanel();
            lenguagePanel.setLayout(null);
            lenguagePanel.setBackground(new Color(36, 44, 68));
            lenguagePanel.setBounds((int) (gamePanel.getWidth() * .75f), 450, (int) (gamePanel.getWidth() * .15f), ((Game) product).getLanguage().size() * 20 + 25);
            lenguagePanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
            JLabel language = new JLabel("Languages: ");
            language.setForeground(Color.WHITE);
            language.setFont(new Font("Arial", Font.BOLD, 15));
            language.setBounds(0, 0, lenguagePanel.getWidth(), 25);
            language.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
            lenguagePanel.add(language);

            ArrayList<String> strings = ((Game) product).getLanguage();
            for (int i = 0; i < strings.size(); i++) {
                String s = strings.get(i);
                JLabel languageLabel = new JLabel(s);
                languageLabel.setForeground(Color.WHITE);
                languageLabel.setFont(new Font("Arial", Font.BOLD, 15));
                languageLabel.setBounds(2, ((i+1) * 20) + 2, lenguagePanel.getWidth(), 20);
                lenguagePanel.add(languageLabel);
            }
            gamePanel.add(lenguagePanel);

            JPanel platformPanel = new JPanel();
            platformPanel.setLayout(null);
            platformPanel.setBackground(new Color(36, 44, 68));
            platformPanel.setBounds((int) (gamePanel.getWidth() * .75f), 470 + ((Game) product).getLanguage().size() * 20 + 25, (int) (gamePanel.getWidth() * .15f), ((Game) product).getPlatafomrs().size() * 20 + 25);
            platformPanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
            JLabel platform = new JLabel("Platforms: ");
            platform.setForeground(Color.WHITE);
            platform.setFont(new Font("Arial", Font.BOLD, 15));
            platform.setBounds(0, 0, platformPanel.getWidth(), 25);
            platform.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
            platformPanel.add(platform);

            strings = ((Game) product).getPlatafomrs();
            for (int i = 0; i < strings.size(); i++) {
                String s = strings.get(i);
                JLabel platformLabel = new JLabel(s);
                platformLabel.setForeground(Color.WHITE);
                platformLabel.setFont(new Font("Arial", Font.BOLD, 15));
                platformLabel.setBounds(2, ((i+1) * 20) + 2, platformPanel.getWidth(), 20);
                platformPanel.add(platformLabel);
            }
            gamePanel.add(platformPanel);

            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(null);
            labelPanel.setBackground(new Color(36, 44, 68));
            labelPanel.setBounds((int) (gamePanel.getWidth() * .75f), 490 + ((Game) product).getLanguage().size() * 20 + 25 + ((Game) product).getPlatafomrs().size() * 20 + 25, (int) (gamePanel.getWidth() * .15f), ((Game) product).getLabels().size() * 20 + 25);
            labelPanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
            JLabel label = new JLabel("Labels: ");
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 15));
            label.setBounds(0, 0, labelPanel.getWidth(), 25);
            label.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
            labelPanel.add(label);

            strings = ((Game) product).getLabels();
            for (int i = 0; i < strings.size(); i++) {
                String s = strings.get(i);
                JLabel labelLabel = new JLabel(s);
                labelLabel.setForeground(Color.WHITE);
                labelLabel.setFont(new Font("Arial", Font.BOLD, 15));
                labelLabel.setBounds(2, ((i+1) * 20) + 2, labelPanel.getWidth(), 20);
                labelPanel.add(labelLabel);
            }
            gamePanel.add(labelPanel);

            JPanel genrePanel = new JPanel();
            genrePanel.setLayout(null);
            genrePanel.setBackground(new Color(36, 44, 68));
            genrePanel.setBounds((int) (gamePanel.getWidth() * .66f), 200, (int) (gamePanel.getWidth() * .3f), ((Game) product).getGenders().size() * 20 + 25);
            genrePanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
            JLabel genre = new JLabel("Genres: ");
            genre.setForeground(Color.WHITE);
            genre.setFont(new Font("Arial", Font.BOLD, 15));
            genre.setBounds(0, 0, genrePanel.getWidth(), 25);
            genre.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
            genrePanel.add(genre);

            strings = ((Game) product).getGenders();
            for (int i = 0; i < strings.size(); i++) {
                String s = strings.get(i);
                JLabel genreLabel = new JLabel(s);
                genreLabel.setForeground(Color.WHITE);
                genreLabel.setFont(new Font("Arial", Font.BOLD, 15));
                genreLabel.setBounds(2, ((i+1) * 20) + 2, genrePanel.getWidth(), 20);
                genrePanel.add(genreLabel);
            }
            gamePanel.add(genrePanel);

            JLabel developer = new JLabel("Developer: " + ((Game) product).getDeveloper());
            developer.setForeground(Color.WHITE);
            developer.setFont(new Font("Arial", Font.BOLD, 15));
            developer.setBounds((int) (gamePanel.getWidth() * .66f), 200 + genrePanel.getHeight() + 2, (int) (gamePanel.getWidth() * .3f), 25);
            developer.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
            gamePanel.add(developer);

            JLabel publisher = new JLabel("Publisher: " + ((Game) product).getEditor());
            publisher.setForeground(Color.WHITE);
            publisher.setFont(new Font("Arial", Font.BOLD, 15));
            publisher.setBounds((int) (gamePanel.getWidth() * .66f), 200 + genrePanel.getHeight() + 2 + developer.getHeight(), (int) (gamePanel.getWidth() * .3f),25);
            publisher.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
            gamePanel.add(publisher);

            JLabel releaseDate = new JLabel("Release Date: " + ((Game) product).getReleaseDate().toString());
            releaseDate.setForeground(Color.WHITE);
            releaseDate.setFont(new Font("Arial", Font.BOLD, 15));
            releaseDate.setBounds((int) (gamePanel.getWidth() * .66f), 200 + genrePanel.getHeight() + 2 + developer.getHeight() + publisher.getHeight(), (int) (gamePanel.getWidth() * .3f), 25);
            releaseDate.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
            gamePanel.add(releaseDate);

            JLabel multiplayer = new JLabel();
            if (((Game) product).getMultiplayer()) {
                multiplayer.setText("Multiplayer");
            } else {
                multiplayer.setText("Single Player");
            }
            multiplayer.setForeground(Color.WHITE);
            multiplayer.setFont(new Font("Arial", Font.BOLD, 15));
            multiplayer.setBounds((int) (gamePanel.getWidth() * .66f), 200 + genrePanel.getHeight() + 2 + developer.getHeight() + publisher.getHeight() + releaseDate.getHeight(), (int) (gamePanel.getWidth() * .3f), 25);
            multiplayer.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
            gamePanel.add(multiplayer);

            JLabel esrb = new JLabel("ESRB: " + ((Game) product).getESRB().toString());
            esrb.setForeground(Color.WHITE);
            esrb.setFont(new Font("Arial", Font.BOLD, 15));
            esrb.setBounds((int) (gamePanel.getWidth() * .66f), 200 + genrePanel.getHeight() + 2 + developer.getHeight() + publisher.getHeight() + releaseDate.getHeight() + multiplayer.getHeight(), (int) (gamePanel.getWidth() * .3f), 25);
            esrb.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
            gamePanel.add(esrb);

            JLabel size = new JLabel("Size: " + ((Game) product).getSizeGB() + " GB");
            size.setForeground(Color.WHITE);
            size.setFont(new Font("Arial", Font.BOLD, 15));
            size.setBounds((int) (gamePanel.getWidth() * .66f), 200 + genrePanel.getHeight() + 2 + developer.getHeight() + publisher.getHeight() + releaseDate.getHeight() + multiplayer.getHeight() + esrb.getHeight(), (int) (gamePanel.getWidth() * .3f), 25);
            size.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
            gamePanel.add(size);
        }

        JPanel priceBuyPanel = new JPanel();
        priceBuyPanel.setLayout(null);
        priceBuyPanel.setBackground(Color.BLACK);
        priceBuyPanel.setBounds(20, 300 , gamePanel.getWidth() / 2, 50);
        priceBuyPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        JLabel price = new JLabel("Price: $" + product.getPrice());
        price.setForeground(Color.WHITE);
        price.setFont(new Font("Arial", Font.BOLD, 15));
        price.setBounds(0, 0, (int) (priceBuyPanel.getWidth() * .75f), priceBuyPanel.getHeight());
        price.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel buy = new JLabel();
        if(user.searchLibrary(product.getId())) {
            buy.setText("Review");
        } else if (user.searchCart(product.getId())) {
            buy.setText("In Cart");
        } else {
            buy.setText("Add to cart");
        }
        buy.setBounds((int) (priceBuyPanel.getWidth() * .75f), 0, (int) (priceBuyPanel.getWidth() * .25f), priceBuyPanel.getHeight());
        buy.setOpaque(true);
        buy.setBackground(new Color(35, 159, 15));
        buy.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
        buy.setForeground(Color.WHITE);
        buy.setHorizontalAlignment(SwingConstants.CENTER);
        buy.setFont(new Font("Arial", Font.BOLD, 15));
        JFrame aux = this;
        buy.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(user.searchLibrary(product.getId())) {
                    ReviewFrame reviewFrame = new ReviewFrame(user, product, aux);
                } else if (user.searchCart(product.getId())) {
                    JOptionPane.showMessageDialog(null, "This item is already in your cart!");
                } else {
                    user.addCart(product.getId());
                    try {
                        UserGamesFile.addProductCart(user.getUuid(), product);
                        cart.setText("(" + Objects.requireNonNull(UserGamesFile.getCart(user.getUuid())).size() + ")");
                    } catch (AlreadyInListException ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Item added to cart!");
                    dispose();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                buy.setBackground(new Color(15, 80, 8));
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                buy.setBackground(new Color(35, 159, 15));
            }
        });

        priceBuyPanel.add(price);
        priceBuyPanel.add(buy);

        gamePanel.add(priceBuyPanel);

        JList<JPanel> reviewPanel = new JList<>();
        //reviewPanel.setLayout(null);
        reviewPanel.setOpaque(true);
        reviewPanel.setBackground(Color.BLACK);
        reviewPanel.setBounds(25, 400 , gamePanel.getWidth() / 2, -1);
        reviewPanel.setPreferredSize(new Dimension(gamePanel.getWidth() / 2 -25 , -1));

        JLabel reviewLabel = new JLabel("Reviews");
        reviewLabel.setForeground(Color.WHITE);
        reviewLabel.setFont(new Font("Arial", Font.BOLD, 15));
        reviewLabel.setBounds(0, 0, reviewPanel.getWidth(), 25);
        reviewLabel.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        reviewPanel.add(reviewLabel);

        ArrayList<Review> reviews = product.getReviews();
        for (int i = 0; i < reviews.size(); i++) {
            Review r = reviews.get(i);
            JPanel review = new JPanel();
            review.setLayout(null);
            review.setBackground(Color.BLACK);
            review.setBounds(0, 25 + i * 150 , reviewPanel.getWidth(), 150);
            review.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, false));

            JLabel userLabel = new JLabel(r.getUser());
            userLabel.setForeground(Color.WHITE);
            userLabel.setFont(new Font("Arial", Font.BOLD, 15));
            userLabel.setBounds(0, 0, review.getWidth(), (int) (review.getHeight() * .2f));

            StarsPanel stars = new StarsPanel(r.getRating());
            stars.setBounds(0, (int) (review.getHeight() * .15f), review.getWidth() / 2, (int) (review.getHeight() * .2f));

            JTextArea textLabel = new JTextArea(r.getText());
            textLabel.setLineWrap(true);
            textLabel.setBackground(new Color(0, 0, 0, 0));
            textLabel.setEditable(false);
            textLabel.setForeground(Color.WHITE);
            textLabel.setFont(new Font("Arial", Font.BOLD, 15));
            textLabel.setBounds(0, (int) (review.getHeight() * .4f), review.getWidth(), (int) (review.getHeight() * .6f));

            review.add(userLabel);
            review.add(stars);
            review.add(textLabel);

            reviewPanel.add(review);
        }

        JScrollPane sp = new JScrollPane(reviewPanel);
        //sp.setPreferredSize(new Dimension(gamePanel.getWidth() / 2, 450));
        sp.setBounds(25, 400 , gamePanel.getWidth() / 2, product.getReviews().size() * 150 + 50);

        gamePanel.add(sp);

        mainPanel.add(gamePanel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);

        add(scrollPane);
        setVisible(true);
    }
}
