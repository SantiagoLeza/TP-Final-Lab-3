package App.Frames;

import App.Accounts.User;
import App.FilesHandler.UserGamesFile;
import App.Products.Product;
import App.Steam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class CartFrame extends JFrame
{
    public CartFrame(User user, JLabel cartLabel, JLabel walletLabel)
    {
        super("Cart");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, (int) (screenSize.height * .8f));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.black);

        JPanel cartProductsPanel = new JPanel(new GridLayout(0, 1));
        cartProductsPanel.setOpaque(true);
        cartProductsPanel.setBounds(10, 10, getWidth() - 40, getHeight() - 100);
        cartProductsPanel.setBackground(new Color(36, 44, 68));

        Steam steam = new Steam();
        ArrayList<Integer> cart = user.getCart();
        for (int j = 0; j < cart.size(); j++) {
            Integer i = cart.get(j);
            Product p = steam.getProductById(i);

            JPanel productPanel = new JPanel(new GridLayout(1, 2));
            productPanel.setOpaque(false);

            JLabel nameLabel = new JLabel(p.getName());
            nameLabel.setBackground(new Color(36, 44, 68));
            nameLabel.setForeground(Color.white);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 20));

            JLabel priceLabel = new JLabel("$" + p.getPrice());
            priceLabel.setForeground(Color.white);
            priceLabel.setFont(new Font("Arial", Font.BOLD, 20));

            productPanel.add(nameLabel);
            productPanel.add(priceLabel);

            cartProductsPanel.add(productPanel);
        }

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(getWidth() / 2 - 100, getHeight() - 100, 200, 50);
        checkoutButton.setBackground(Color.black);
        checkoutButton.setForeground(Color.white);
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 20));
        checkoutButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                try
                {
                    float total = 0;
                    for (Integer i : user.getCart())
                    {
                        Product p = steam.getProductById(i);
                        total += p.getPrice();
                    }
                    if(user.getWallet() < total)
                    {
                        JOptionPane.showMessageDialog(null, "You don't have enough money in your wallet to buy this products", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        for(int i : user.getCart())
                        {
                            Product p = steam.getProductById(i);
                            user.finalizePurchase(p);
                            cartLabel.setText("(" + user.getCart().size() + ")");
                            walletLabel.setText("$" + user.getWallet());
                        }

                        dispose();
                    }
                }
                catch (ConcurrentModificationException ignored)
                {
                }
            }
        });

        mainPanel.add(cartProductsPanel);
        mainPanel.add(checkoutButton);

        add(mainPanel);
        setVisible(true);
    }
}
