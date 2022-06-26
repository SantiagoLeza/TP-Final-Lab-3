package App.Frames.Panels;

import App.Accounts.User;
import App.Frames.ProductFrame;
import App.Products.Product;
import App.Steam;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LibraryPanel extends JPanel
{
    public LibraryPanel(User user, Steam steam, JLabel cart)
    {
        super();
        setLayout(new GridLayout(0, 1));

        setBackground(new Color(36, 44, 68));

        for(Integer i : user.getLibrary())
        {
            Product product = steam.getProductById(i);

            JPanel productPanel = new JPanel();
            productPanel.setLayout(new GridLayout(1, 0));
            productPanel.setBackground(new Color(36, 44, 68));
            if(!product.isAviable()){productPanel.setBackground(new Color(36, 44, 68, 126));}
            productPanel.setPreferredSize(new Dimension((int) (getWidth() * 0.9f), 100));
            productPanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));

            JLabel productName = new JLabel(product.getName());
            productName.setForeground(Color.WHITE);
            productName.setFont(new Font("Arial", Font.BOLD, 15));
            productName.setHorizontalAlignment(SwingConstants.CENTER);
            productName.setVerticalAlignment(SwingConstants.CENTER);

            JLabel productPrice = new JLabel("$" + product.getPrice());
            if(!product.isAviable()){productPrice.setText("Product not aviable");}
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
                    if (product.isAviable() && e.getButton() == MouseEvent.BUTTON1)
                    {
                        ProductFrame productFrame = new ProductFrame(user, product, cart);
                    }
                }
            });

            JScrollPane scrollPane = new JScrollPane(productPanel);

            add(scrollPane);
        }
    }
}
