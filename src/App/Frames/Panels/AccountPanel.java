package App.Frames.Panels;

import App.Accounts.Account;
import App.Accounts.User;
import App.AppDate;
import App.FilesHandler.UsersFile;
import App.Steam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccountPanel extends JPanel
{
    public AccountPanel(User user, Steam steam, JTabbedPane jtp, JLabel nameLabel, JLabel walletLabel)
    {
        super();
        setLayout(new GridLayout(1,1));
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, (int) size.getWidth(), (int) size.getHeight());

        Panel mainPanel = new Panel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(null);

        JLabel passwordTitle = new JLabel("Enter your password");
        passwordTitle.setForeground(Color.WHITE);
        passwordTitle.setBounds( 50 ,  110  , Math.round( getWidth() / 3f) , 25 );

        JPasswordField passwordField = new JPasswordField(user.getPassword());
        passwordField.setEchoChar('*');
        passwordField.setBounds( 50 ,  140 , Math.round( getWidth() / 3f) , 25 );

        JButton showPass1 = new JButton("Show");
        showPass1.setBounds( 55 + Math.round( getWidth() / 3f) ,  140 , 100 , 25 );
        showPass1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {

                passwordField.setEchoChar((char) 0);
                showPass1.setText("");
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                passwordField.setEchoChar('*');
                showPass1.setText("Show");
            }
        });

        JLabel passwordConfirmTitle = new JLabel("Confirm your password");
        passwordConfirmTitle.setForeground(Color.WHITE);
        passwordConfirmTitle.setBounds(50 ,  170  , Math.round( getWidth() / 3f) , 25 );

        JPasswordField passwordConfirmField = new JPasswordField(user.getPassword());
        passwordConfirmField.setBounds( 50 , 200  , Math.round( getWidth() / 3f) , 25 );

        JButton showPass2 = new JButton("Show");
        passwordField.setEchoChar('*');
        showPass2.setBounds( 55 + Math.round( getWidth() / 3f) ,  200 , 100 , 25 );
        showPass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {

                passwordConfirmField.setEchoChar((char) 0);
                showPass2.setText("");
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                passwordConfirmField.setEchoChar('*');
                showPass2.setText("Show");
            }
        });

        JLabel nameTitle = new JLabel("Enter your name");
        nameTitle.setForeground(Color.WHITE);
        nameTitle.setBounds( 50, 230 , Math.round( getWidth() / 3f ) , 25 );

        JTextField nameField = new JTextField(user.getName());
        nameField.setBounds( 50 , 260 , Math.round( getWidth() / 3f ) , 25 );

        JLabel surnameTitle = new JLabel("Enter your surname");
        surnameTitle.setForeground(Color.WHITE);
        surnameTitle.setBounds( 50 ,  290  , Math.round( getWidth() / 3f) , 25 );

        JTextField surnameField = new JTextField(user.getSurname());
        surnameField.setBounds( 50 , 320  , Math.round( getWidth() / 3f) , 25 );

        JLabel idTitle = new JLabel("Enter your id");
        idTitle.setForeground(Color.WHITE);
        idTitle.setBounds( 50 , 350  , Math.round( getWidth() / 3f) , 25 );

        JTextField idField = new JTextField(user.getID());
        idField.setBounds( 50 ,  380  , Math.round( getWidth() / 3f) , 25 );

        JLabel phoneTitle = new JLabel("Enter your phone number");
        phoneTitle.setForeground(Color.WHITE);
        phoneTitle.setBounds( 50 ,  410  , Math.round( getWidth() / 3f) , 25 );

        JTextField phoneField = new JTextField(user.getPhone());
        phoneField.setBounds( 50 , 440  , Math.round( getWidth() / 3f) , 25 );

        JLabel countryTitle = new JLabel("Enter your country");
        countryTitle.setForeground(Color.WHITE);
        countryTitle.setBounds( 50 , 470  , Math.round( getWidth() / 3f) , 25 );

        JTextField countryField = new JTextField(user.getAdress().getCountry());
        countryField.setBounds( 50 , 500  , Math.round( getWidth() / 3f) , 25 );

        JLabel stateTitle = new JLabel("Enter your state");
        stateTitle.setForeground(Color.WHITE);
        stateTitle.setBounds( 50 , 530  , Math.round( getWidth() / 3f) , 25 );

        JTextField stateField = new JTextField(user.getAdress().getState());
        stateField.setBounds( 50 ,  560  , Math.round( getWidth() / 3f) , 25 );

        JLabel cityTitle = new JLabel("Enter your city");
        cityTitle.setForeground(Color.WHITE);
        cityTitle.setBounds( 50 , 590  , Math.round( getWidth() / 3f) , 25 );

        JTextField cityField = new JTextField(user.getAdress().getCity());
        cityField.setBounds( 50 , 620  , Math.round( getWidth() / 3f) , 25 );

        JLabel streetTitle = new JLabel("Enter your street");
        streetTitle.setForeground(Color.WHITE);
        streetTitle.setBounds( 50 , 650  , Math.round( getWidth() / 3f) , 25 );

        JTextField streetField = new JTextField(user.getAdress().getStreet());
        streetField.setBounds( 50 , 680  , Math.round( getWidth() / 3f) , 25 );

        JLabel birthdayTitle = new JLabel("Enter your birthday");
        birthdayTitle.setForeground(Color.WHITE);
        birthdayTitle.setBounds( 50 , 710  , Math.round( getWidth() / 3f) , 25 );
        birthdayTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel birthDatePanel = new JPanel();
        birthDatePanel.setOpaque(false);
        birthDatePanel.setBounds( 50 , 740  , Math.round( getWidth() / 3f) , 30 );

        JComboBox<Integer> dayBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            dayBox.addItem(i);
        }
        dayBox.setSelectedItem(user.getBirthDate().getDay());
        JComboBox<Integer> monthBox = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            monthBox.addItem(i);
        }
        monthBox.setSelectedItem(user.getBirthDate().getMonth());
        JComboBox<Integer> yearBox = new JComboBox<>();
        for (int i = 1900; i <= 2020; i++) {
            yearBox.addItem(i);
        }
        yearBox.setSelectedItem(user.getBirthDate().getYear());
        birthDatePanel.add(dayBox);
        birthDatePanel.add(monthBox);
        birthDatePanel.add(yearBox);

        JButton signUpButton = new JButton("Update");
        signUpButton.setBounds(100, 780 , 200 , 30 );
        signUpButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(passwordField.getText().equals(passwordConfirmField.getText()))
                {
                    user.setPassword(passwordField.getText());
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Passwords do not match");
                }
                user.setName(nameField.getText());
                user.setSurname(surnameField.getText());
                user.setID(idField.getText());
                user.setPhone(phoneField.getText());
                user.getAdress().setCountry(countryField.getText());
                user.getAdress().setState(stateField.getText());
                user.getAdress().setCity(cityField.getText());
                user.getAdress().setStreet(streetField.getText());
                user.setBirthDate(new AppDate(
                        Integer.parseInt(dayBox.getSelectedItem().toString()),
                        Integer.parseInt(monthBox.getSelectedItem().toString()),
                        Integer.parseInt(yearBox.getSelectedItem().toString())
                ));

                steam.modifyAccountUser(user);
                JOptionPane.showMessageDialog(null, "Account modified successfully");
                jtp.setSelectedIndex(0);

                nameLabel.setText(user.getName());
            }
        });

        JButton addFoundButton = new JButton("Add founds");
        addFoundButton.setBounds(400, 780 , 200 , 30 );
        addFoundButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                JFrame addFoundFrame = new JFrame("Add founds");
                addFoundFrame.setSize(500, 500);
                addFoundFrame.setLocationRelativeTo(null);
                addFoundFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addFoundFrame.setVisible(true);
                addFoundFrame.setResizable(false);

                JPanel addFoundPanel = new JPanel();
                addFoundPanel.setLayout(null);
                addFoundPanel.setOpaque(true);
                addFoundPanel.setBackground(new Color(36, 47, 65));
                addFoundPanel.setBounds(0, 0, 500, 500);
                addFoundFrame.add(addFoundPanel);

                JLabel addFoundLabel = new JLabel("Enter the amount of founds");
                addFoundLabel.setForeground(Color.WHITE);
                addFoundLabel.setBounds(50, 50, 500, 50);
                addFoundPanel.add(addFoundLabel);

                JTextField addFoundField = new JTextField();
                addFoundField.setBounds(50, 100, 200, 50);
                addFoundPanel.add(addFoundField);

                JButton addFoundButton = new JButton("Add");
                addFoundButton.setBounds(50, 150, 200, 50);
                addFoundButton.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        try {
                            user.addFounds(Integer.parseInt(addFoundField.getText()));
                            steam.modifyAccountUser(user);
                            JOptionPane.showMessageDialog(null, "Founds added successfully");
                            walletLabel.setText("$" + String.valueOf(user.getWallet()));
                        }
                        catch (NumberFormatException ex)
                        {
                            JOptionPane.showMessageDialog(null, "Please enter a number");
                        }
                        addFoundFrame.dispose();
                    }
                });
                addFoundPanel.add(addFoundButton);
            }
        });

        JPanel container = new JPanel();
        container.setBackground(new Color(36, 44, 68));
        container.setOpaque(true);
        container.setBounds(getWidth() / 4, 0, getWidth()/2, getHeight());
        container.setLayout(null);

        container.add(passwordTitle);
        container.add(passwordField);
        container.add(showPass1);
        container.add(passwordConfirmTitle);
        container.add(passwordConfirmField);
        container.add(showPass2);
        container.add(nameTitle);
        container.add(nameField);
        container.add(surnameTitle);
        container.add(surnameField);
        container.add(idTitle);
        container.add(idField);
        container.add(phoneTitle);
        container.add(phoneField);
        container.add(countryTitle);
        container.add(countryField);
        container.add(stateTitle);
        container.add(stateField);
        container.add(cityTitle);
        container.add(cityField);
        container.add(streetTitle);
        container.add(streetField);
        container.add(birthdayTitle);
        container.add(birthDatePanel);
        container.add(signUpButton);
        container.add(addFoundButton);

        mainPanel.add(container);

        add(mainPanel);

    }
}
