package App.Frames;

import App.Accounts.User;
import App.Exceptions.FileErrorException;
import App.FilesHandler.GamesFile;
import App.FilesHandler.UserGamesFile;
import App.Frames.Panels.AccountPanel;
import App.Frames.Panels.LibraryPanel;
import App.Frames.Panels.StarsPanel;
import App.Products.Product;
import App.Steam;
import org.json.JSONException;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Objects;

public class UserMainFrame extends JFrame
{
    private boolean close = true;

    public UserMainFrame(User user, Steam steam)
    {
        super("Steam");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        setLocationRelativeTo(null);

        setBackground(Color.BLACK);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBounds(0, 0, this.getWidth(), this.getHeight());

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel topBar = new JPanel();
        topBar.setBackground(new Color(36, 44, 68));
        topBar.setLayout(new GridLayout(1,4));
        topBar.setBounds(0, 0, this.getWidth(), 75);
        topBar.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));

        JPanel topBarLeft = new JPanel();
        topBarLeft.setOpaque(true);
        topBarLeft.setLayout(new GridLayout(1,4));
        topBarLeft.setBackground(new Color(0, 0, 0, 0));
        
        JLabel store = new JLabel("STORE");
        store.setOpaque(true);
        store.setForeground(Color.WHITE);
        store.setFont(new Font("Arial", Font.BOLD, 15));
        store.setBackground(new Color(36, 44, 68));
        store.setHorizontalAlignment(SwingConstants.CENTER);
        store.setVerticalAlignment(SwingConstants.CENTER);
        store.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                store.setForeground(Color.gray);
                topBarLeft.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                store.setForeground(Color.WHITE);
                topBarLeft.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e)
            {
                tabbedPane.setSelectedIndex(0);
            }
        });
        topBarLeft.add(store);

        JPanel topBarRight = new JPanel();
        topBarRight.setLayout(new GridLayout(1,4));
        topBarRight.setBackground(new Color(0, 0, 0, 0));

        JLabel cart = new JLabel();
        cart.setOpaque(true);
        cart.setBackground(new Color(36, 44, 68));
        ImageIcon cartIcon = new ImageIcon("src/App/Images/cart.png");
        cartIcon.setImage(cartIcon.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
        cart.setIcon(cartIcon);
        cart.setText("(" + Objects.requireNonNull(UserGamesFile.getCart(user.getUuid())).size() + ")");
        cart.setHorizontalAlignment(SwingConstants.RIGHT);
        cart.setVerticalAlignment(SwingConstants.BOTTOM);
        cart.setForeground(Color.WHITE);
        cart.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel account = new JLabel("ACCOUNT");
        account.setOpaque(true);
        account.setBackground(new Color(36, 44, 68));
        account.setForeground(Color.WHITE);
        account.setFont(new Font("Arial", Font.BOLD, 15));
        account.setHorizontalAlignment(SwingConstants.CENTER);
        account.setVerticalAlignment(SwingConstants.CENTER);
        account.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                account.setForeground(Color.gray);
                topBarLeft.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                account.setForeground(Color.WHITE);
                topBarLeft.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e)
            {
                tabbedPane.setSelectedIndex(1);
            }
        });
        topBarLeft.add(account);

        JLabel library = new JLabel("LIBRARY");
        library.setOpaque(true);
        library.setForeground(Color.WHITE);
        library.setFont(new Font("Arial", Font.BOLD, 15));
        library.setBackground(new Color(36, 44, 68));
        library.setHorizontalAlignment(SwingConstants.CENTER);
        library.setVerticalAlignment(SwingConstants.CENTER);
        library.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                library.setForeground(Color.gray);
                topBarLeft.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                library.setForeground(Color.WHITE);
                topBarLeft.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e)
            {
                tabbedPane.removeTabAt(2);
                tabbedPane.addTab("LIBRARY", new LibraryPanel(user, steam, cart));
                tabbedPane.setSelectedIndex(2);
            }
        });
        topBarLeft.add(library);

        JLabel userName = new JLabel(user.getName());
        userName.setOpaque(true);
        userName.setBackground(new Color(36, 44, 68));
        userName.setForeground(Color.WHITE);
        userName.setFont(new Font("Arial", Font.BOLD, 15));
        userName.setHorizontalAlignment(SwingConstants.CENTER);
        userName.setVerticalAlignment(SwingConstants.CENTER);

        JLabel userBalance = new JLabel("$" + user.getWallet());
        userBalance.setOpaque(true);
        userBalance.setBackground(new Color(36, 44, 68));
        userBalance.setForeground(Color.WHITE);
        userBalance.setFont(new Font("Arial", Font.BOLD, 15));
        userBalance.setHorizontalAlignment(SwingConstants.CENTER);
        userBalance.setVerticalAlignment(SwingConstants.CENTER);

        cart.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                cart.setForeground(Color.gray);
                topBarRight.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                cart.setForeground(Color.WHITE);
                topBarRight.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e)
            {
                new CartFrame(user, cart, userBalance);
            }
        });
        
        JLabel userLogOut = new JLabel();
        userLogOut.setOpaque(true);
        userLogOut.setBackground(new Color(36, 44, 68));
        ImageIcon logOutIcon = new ImageIcon("src/App/Images/logOut.png");
        logOutIcon.setImage(logOutIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        userLogOut.setIcon(logOutIcon);
        userLogOut.setHorizontalAlignment(SwingConstants.CENTER);
        userLogOut.setVerticalAlignment(SwingConstants.CENTER);
        userLogOut.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                close = false;
                dispose();
            }
        });

        topBarRight.add(cart);
        topBarRight.add(userBalance);
        topBarRight.add(userName);
        topBarRight.add(userLogOut);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(new Color(0, 0, 0, 0));

        topBar.add(topBarLeft);
        topBar.add(emptyPanel);
        topBar.add(emptyPanel);
        topBar.add(topBarRight);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setOpaque(true);
        centerPanel.setBackground(Color.BLACK);
        centerPanel.setBounds(0, 75, this.getWidth(), this.getHeight() - 75);

        JPanel gamesPanel = new JPanel();
        gamesPanel.setLayout(null);
        gamesPanel.setBackground(new Color(36, 44, 68));
        gamesPanel.setBounds((centerPanel.getWidth() - ( (int) (centerPanel.getWidth() / 1.5f) )) / 2, 0, (int) (centerPanel.getWidth() / 1.5f), centerPanel.getHeight());

        try {
            int i = 0;
            for (Map.Entry<Integer, Product> entry : GamesFile.fileToTree2("./src/App/FilesHandler/products.json").getTreemap().entrySet())
            {
                i++;

                Product product = entry.getValue();

                JPanel productPanel = new JPanel();
                productPanel.setLayout(new GridLayout(1, 0));
                productPanel.setBackground(new Color(36, 44, 68));
                productPanel.setBounds((int) (gamesPanel.getWidth() * 0.05f), (100 + 5) * i, (int) (gamesPanel.getWidth() * 0.9f), 100);
                productPanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));

                JLabel productName = new JLabel(product.getName());
                productName.setForeground(Color.WHITE);
                productName.setFont(new Font("Arial", Font.BOLD, 15));
                productName.setHorizontalAlignment(SwingConstants.CENTER);
                productName.setVerticalAlignment(SwingConstants.CENTER);

                JLabel productPrice = new JLabel("$" + product.getPrice());
                productPrice.setForeground(Color.WHITE);
                productPrice.setFont(new Font("Arial", Font.BOLD, 15));
                productPrice.setHorizontalAlignment(SwingConstants.CENTER);
                productPrice.setVerticalAlignment(SwingConstants.CENTER);

                productPanel.add(productName);
                productPanel.add(new JLabel(""));
                productPanel.add(new StarsPanel(product.getRating()));
                productPanel.add(new JLabel(""));
                productPanel.add(productPrice);

                productPanel.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                    if (e.getButton() == MouseEvent.BUTTON1)
                    {
                        ProductFrame productFrame = new ProductFrame(user, product, cart);
                    }
                    }
                });

                gamesPanel.add(productPanel);
            }

        } catch (FileErrorException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        centerPanel.add(gamesPanel, BorderLayout.CENTER);

        tabbedPane.addTab("Store", centerPanel);
        tabbedPane.setBounds(0, 50, this.getWidth(), this.getHeight() - 50);

        JPanel accountPanel = new JPanel();

        tabbedPane.addTab("Account", new AccountPanel(user, steam, tabbedPane, userName, userBalance));

        tabbedPane.addTab("Library", new LibraryPanel(user, steam, cart));

        mainPanel.add(topBar);
        mainPanel.add(tabbedPane);

        add(mainPanel);

        setVisible(true);
    }

    public boolean isClose()
    {
        return close;
    }
}
