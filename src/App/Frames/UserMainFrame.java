package App.Frames;

import App.Accounts.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserMainFrame extends JFrame
{
    public UserMainFrame(User user)
    {
        super("MainFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());

        setBackground(Color.BLACK);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBounds(0, 0, this.getWidth(), this.getHeight());

        JPanel topBar = new JPanel();
        topBar.setBackground(new Color(36, 44, 68));
        topBar.setLayout(new GridLayout(1,4));
        topBar.setBounds(0, 0, this.getWidth(), 75);

        JPanel topBarLeft = new JPanel();
        topBarLeft.setLayout(new GridLayout(1,4));
        topBarLeft.setBackground(new Color(0, 0, 0, 0));
        
        JLabel store = new JLabel("STORE");
        store.setForeground(Color.WHITE);
        store.setFont(new Font("Arial", Font.BOLD, 15));
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
        });
        topBarLeft.add(store);

        JLabel library = new JLabel("LIBRARY");
        library.setForeground(Color.WHITE);
        library.setFont(new Font("Arial", Font.BOLD, 15));
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
        });
        topBarLeft.add(library);
        
        JLabel account = new JLabel("ACCOUNT");
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
        });
        topBarLeft.add(account);

        JPanel topBarRight = new JPanel();
        topBarRight.setLayout(new GridLayout(1,1));
        topBarRight.setBackground(new Color(0, 0, 0, 0));

        

        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(new Color(0, 0, 0, 0));

        topBar.add(topBarLeft);
        topBar.add(emptyPanel);
        topBar.add(emptyPanel);
        topBar.add(topBarRight);

        mainPanel.add(topBar);

        add(mainPanel);

        setVisible(true);
    }
}
