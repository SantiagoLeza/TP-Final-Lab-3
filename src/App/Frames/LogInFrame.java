package App.Frames;

import App.Accounts.Account;
import App.Exceptions.BadInputException;
import App.Exceptions.NewUserException;
import App.Steam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class LogInFrame extends JFrame
{
    private JPasswordField passwordField;
    private JTextField mailField;
    private Account user;
    private Steam steam;

    public LogInFrame()
    {
        super("Login");
        steam = new Steam();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if(screenSize.getHeight() <= 480)
        {
            setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        }
        else
        {
            setSize((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
        }
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setOpaque(true);
        mainPanel.setBackground(new Color(36, 44, 68));

        JLabel mailTitle = new JLabel("Enter your email adress");
        mailTitle.setForeground(Color.WHITE);
        mailTitle.setBounds( Math.round( getWidth() / 3f) , Math.round (getHeight() / 3f ) , Math.round( getWidth() / 3f) , 25 );

        mailField = new JTextField("");
        mailField.setBounds( Math.round( getWidth() / 3f) , Math.round (getHeight() / 3f ) + 30 , Math.round( getWidth() / 3f) , 25 );
        mailField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    logIn();
                }
            }
        });

        JLabel passwordTitle = new JLabel("Enter your password");
        passwordTitle.setForeground(Color.WHITE);
        passwordTitle.setBounds( Math.round( getWidth() /3f) , Math.round (getHeight() / 3f + 90 ) , Math.round( getWidth() / 3f) , 25 );

        passwordField = new JPasswordField("");
        passwordField.setBounds( Math.round( getWidth() /3f) , Math.round (getHeight() / 3f + 120 ) , Math.round( getWidth() / 3f) , 25 );
        passwordField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    logIn();
                }
            }
        });

        JButton loginButton = new JButton("Login");
        loginButton.setBounds( Math.round( getWidth() /3f) + 100 , Math.round (getHeight() / 3f + 180 ) , Math.round( getWidth() / 3f) - 200 , 30 );
        loginButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                logIn();
            }
        });

        JLabel signUp = new JLabel("Not registered? SignUp");
        signUp.setForeground(Color.WHITE);
        signUp.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                signUp();
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                signUp.setForeground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                signUp.setForeground(Color.WHITE);
            }
        });
        signUp.setBounds( Math.round( getWidth() /3f) , Math.round (getHeight() / 3f + 230 ) , Math.round( getWidth() / 3f) , 30 );
        signUp.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel.add(mailTitle);
        mainPanel.add(mailField);
        mainPanel.add(passwordTitle);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(signUp);

        add(mainPanel);

        setVisible(true);
    }

    public void signUp()
    {
        JFrame signUpFrame = new JFrame("SignUp");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signUpFrame.setSize((int) screenSize.getWidth() / 3, (int) screenSize.getHeight() - 250);
        signUpFrame.setLocationRelativeTo(null);

        JPanel componentsPanel = new JPanel();
        componentsPanel.setLayout(null);
        componentsPanel.setOpaque(true);
        componentsPanel.setBackground(new Color(36, 44, 68));
        componentsPanel.setPreferredSize(new Dimension(signUpFrame.getWidth() - 100, 1200));

        JLabel image = new JLabel(){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                LinearGradientPaint lgp = new LinearGradientPaint(
                        new Point(0, 0),
                        new Point(0, getHeight()),
                        new float[]{0f, .8f},
                        new Color[]{Color.BLACK, new Color(36, 44, 68)});
                g2d.setPaint(lgp);
                g2d.fill(new Rectangle(0, 0, getWidth(), getHeight()));
                g2d.dispose();
                getUI().paint(g, this);
            }
        };
        image.setOpaque(false);
        image.setBackground(new Color(36, 44, 68));
        image.setIcon(new ImageIcon("src/App/Images/steamLogo.png"));
        image.setBounds(0, 0, signUpFrame.getWidth(), Math.round(signUpFrame.getHeight() / 3f) + 45);
        image.setBackground(Color.BLACK);

        JLabel mailTitle = new JLabel("Enter your email adress");
        mailTitle.setForeground(Color.WHITE);
        mailTitle.setBounds( Math.round( signUpFrame.getWidth() / 3f) , Math.round (signUpFrame.getHeight() / 3f + 50 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JTextField mailField = new JTextField("");
        mailField.setBounds( Math.round( signUpFrame.getWidth() / 3f) , Math.round (signUpFrame.getHeight() / 3f + 80 ), Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JLabel passwordTitle = new JLabel("Enter your password");
        passwordTitle.setForeground(Color.WHITE);
        passwordTitle.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 110 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JPasswordField passwordField = new JPasswordField("");
        passwordField.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 140 ), Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JLabel passwordConfirmTitle = new JLabel("Confirm your password");
        passwordConfirmTitle.setForeground(Color.WHITE);
        passwordConfirmTitle.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 170 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JPasswordField passwordConfirmField = new JPasswordField("");
        passwordConfirmField.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 200 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JLabel nameTitle = new JLabel("Enter your name");
        nameTitle.setForeground(Color.WHITE);
        nameTitle.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 230 ) , Math.round( signUpFrame.getWidth() / 3f ) , 25 );

        JTextField nameField = new JTextField("");
        nameField.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 260 ) , Math.round( signUpFrame.getWidth() / 3f ) , 25 );

        JLabel surnameTitle = new JLabel("Enter your surname");
        surnameTitle.setForeground(Color.WHITE);
        surnameTitle.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 290 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JTextField surnameField = new JTextField("");
        surnameField.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 320 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JLabel idTitle = new JLabel("Enter your id");
        idTitle.setForeground(Color.WHITE);
        idTitle.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 350 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JTextField idField = new JTextField("");
        idField.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 380 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JLabel phoneTitle = new JLabel("Enter your phone number");
        phoneTitle.setForeground(Color.WHITE);
        phoneTitle.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 410 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JTextField phoneField = new JTextField("");
        phoneField.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 440 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JLabel countryTitle = new JLabel("Enter your country");
        countryTitle.setForeground(Color.WHITE);
        countryTitle.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 470 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JTextField countryField = new JTextField("");
        countryField.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 500 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JLabel stateTitle = new JLabel("Enter your state");
        stateTitle.setForeground(Color.WHITE);
        stateTitle.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 530 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JTextField stateField = new JTextField("");
        stateField.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 560 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JLabel cityTitle = new JLabel("Enter your city");
        cityTitle.setForeground(Color.WHITE);
        cityTitle.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 590 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JTextField cityField = new JTextField("");
        cityField.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 620 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JLabel streetTitle = new JLabel("Enter your street");
        streetTitle.setForeground(Color.WHITE);
        streetTitle.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 650 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JTextField streetField = new JTextField("");
        streetField.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 680 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );

        JLabel birthdayTitle = new JLabel("Enter your birthday");
        birthdayTitle.setForeground(Color.WHITE);
        birthdayTitle.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 710 ) , Math.round( signUpFrame.getWidth() / 3f) , 25 );
        birthdayTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel birthDatePanel = new JPanel();
        birthDatePanel.setOpaque(false);
        birthDatePanel.setBounds( Math.round( signUpFrame.getWidth() /3f) , Math.round (signUpFrame.getHeight() / 3f + 740 ) , Math.round( signUpFrame.getWidth() / 3f) , 30 );

        JComboBox<Integer> dayBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            dayBox.addItem(i);
        }
        JComboBox<Integer> monthBox = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            monthBox.addItem(i);
        }
        JComboBox<Integer> yearBox = new JComboBox<>();
        for (int i = 1900; i <= 2020; i++) {
            yearBox.addItem(i);
        }
        birthDatePanel.add(dayBox);
        birthDatePanel.add(monthBox);
        birthDatePanel.add(yearBox);

        JButton signUpButton = new JButton("SignUp");
        signUpButton.setBounds( Math.round( signUpFrame.getWidth() /3f) + 50 , Math.round (signUpFrame.getHeight() / 3f + 780 ) , Math.round( signUpFrame.getWidth() / 3f) - 100 , 30 );
        signUpButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {

                if(passwordField.getText().equals(passwordConfirmField.getText()))
                {
                    try {
                        user = steam.createUser(
                                mailField.getText(),
                                passwordField.getText(),
                                nameField.getText(),
                                surnameField.getText(),
                                idField.getText(),
                                phoneField.getText(),
                                countryField.getText(),
                                stateField.getText(),
                                cityField.getText(),
                                streetField.getText(),
                                Integer.parseInt(Objects.requireNonNull(yearBox.getSelectedItem()).toString()),
                                Integer.parseInt(Objects.requireNonNull(monthBox.getSelectedItem()).toString()),
                                Integer.parseInt(Objects.requireNonNull(dayBox.getSelectedItem()).toString())
                        );
                        signUpFrame.dispose();
                        dispose();
                    } catch (NewUserException | BadInputException ex) {
                        JOptionPane.showMessageDialog(signUpFrame, ex.getMessage());
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(signUpFrame, "Passwords don't match");
                }
            }
        });

        componentsPanel.add(image);
        componentsPanel.add(mailTitle);
        componentsPanel.add(mailField);
        componentsPanel.add(passwordTitle);
        componentsPanel.add(passwordField);
        componentsPanel.add(passwordConfirmTitle);
        componentsPanel.add(passwordConfirmField);
        componentsPanel.add(nameTitle);
        componentsPanel.add(nameField);
        componentsPanel.add(surnameTitle);
        componentsPanel.add(surnameField);
        componentsPanel.add(idTitle);
        componentsPanel.add(idField);
        componentsPanel.add(phoneTitle);
        componentsPanel.add(phoneField);
        componentsPanel.add(countryTitle);
        componentsPanel.add(countryField);
        componentsPanel.add(stateTitle);
        componentsPanel.add(stateField);
        componentsPanel.add(cityTitle);
        componentsPanel.add(cityField);
        componentsPanel.add(streetTitle);
        componentsPanel.add(streetField);
        componentsPanel.add(birthdayTitle);
        componentsPanel.add(birthDatePanel);
        componentsPanel.add(signUpButton);

        JScrollPane scrollPane = new JScrollPane(componentsPanel);

        signUpFrame.add(scrollPane);

        signUpFrame.setVisible(true);
    }

    public void logIn()
    {
        String mail = mailField.getText();
        String password = passwordField.getText();

        if(mail.isEmpty() || password.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please enter your email and password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try{
            user = steam.logIn(mail, password);
            dispose();
        } catch (BadInputException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Something went wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Account getUser()
    {
        return user;
    }
}
