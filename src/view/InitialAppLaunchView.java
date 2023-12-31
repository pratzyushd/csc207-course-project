package view;

import interface_adapter.initial_app_launch.InitialAppLaunchController;
import interface_adapter.initial_app_launch.InitialAppLaunchViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class InitialAppLaunchView extends JPanel implements PropertyChangeListener {
    public final String viewName = "initial app view";
    private final InitialAppLaunchController initialAppLaunchController;
    private final InitialAppLaunchViewModel initialAppLaunchViewModel;
    private JButton loadExistingUserData;
    private JButton createNewUser;
    private JLabel usernameLabel;

    /**
     * Create a new view for the initial app launch.
     * @param initialAppLaunchController the controller that executes the actions for the front page.
     * @param initialAppLaunchViewModel representation of the information required for the view.
     */
    public InitialAppLaunchView(InitialAppLaunchController initialAppLaunchController, InitialAppLaunchViewModel initialAppLaunchViewModel) {
        this.initialAppLaunchController = initialAppLaunchController;
        this.initialAppLaunchViewModel = initialAppLaunchViewModel;
        this.initialAppLaunchViewModel.addPropertyChangeListener(this);

        setLayout(null);
        setPreferredSize(new Dimension(1400, 800));

        loadExistingUserData = new JButton("Load Existing User Data");
        createNewUser = new JButton("Create New User");
        usernameLabel = new JLabel("Choose A Username: ");
        JTextField usernameTextField = new JTextField(10);

        loadExistingUserData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean load = true;
                JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
                int returnValue = fileChooser.showOpenDialog(null);
                File selectedFile = fileChooser.getSelectedFile();
                if (selectedFile == null) {
                    return;
                }
                String filePath = selectedFile.getAbsolutePath();
                initialAppLaunchController.execute(load, filePath, "");
            }
        });

        createNewUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
                int returnValue = fileChooser.showSaveDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    if (selectedFile == null) {
                        return;
                    }
                    String filePath = selectedFile.getAbsolutePath();

                    // Now 'filePath' contains the selected file's path as a String
                    System.out.println("Selected File: " + filePath);

                    // Use 'filePath' as needed in your application logic

                    // For your specific case, you can pass 'filePath' to your controller
                    Boolean load = false;

                    String username = usernameTextField.getText();

                    initialAppLaunchController.execute(load, filePath, username);
                } else {
                    // User canceled the file saving
                    System.out.println("File saving canceled");
                }
            }
        });

        loadExistingUserData.setBounds(180, 560, 450, 35);
        add(loadExistingUserData);
        createNewUser.setBounds(180, 520, 450, 35);
        add(createNewUser);
        usernameLabel.setBounds(190, 470, 450, 35);
        add(usernameLabel);
        usernameTextField.setBounds(330, 470, 300, 35);
        add(usernameTextField);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Load the background image
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("assets/myrecipemate_launch.png"));
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //todo implement? Don't change state, only tell view model? or view manager? to switch views?
    }
}

