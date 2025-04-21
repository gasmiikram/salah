package ihmproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.sql.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import org.json.JSONObject;




public class VehicleBookingSystem extends JFrame {

    private JPanel cards; // A container for holding the different pages
    private CardLayout cardLayout;
    private JPanel dashboardPanel;
    private String tableHtml;
    private String role;
    private JFrame manageVehiclesFrame; // Reference to the existing frame
    private JTable vehicleTable; // Reference to the JTable
    private JFrame manageuserFrame; // Reference to the existing frame
    private JTable userTable;
    
    public VehicleBookingSystem() {
        setTitle("Vehicle Booking System");
        setSize(1000, 700); // Increased size for better layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        // Initialize the CardLayout and container for pages
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // Create the Home Page, About Page, and Login Page
        JPanel homePage = createHomePage();
        JPanel aboutPage = createAboutPage();
        JPanel loginPage = createLoginPage();

        // Add pages to the card container
        cards.add(homePage, "Home");
        cards.add(aboutPage, "About");
        cards.add(loginPage, "Login");

        // Add the card container to the frame
        add(cards, BorderLayout.CENTER);

        // Set the default page (home page)
        cardLayout.show(cards, "Home");
        
    }

    // Method to create the Home Page
    private JPanel createHomePage() {
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new BorderLayout());
        homePanel.setBackground(Color.BLACK); // Set background to black

        // Create the navigation bar
        JPanel navbarPanel = new JPanel();
        navbarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
        navbarPanel.setBackground(new Color(128, 0, 32)); // Burgundy red

        // Home, About, and Login buttons
        JButton homeButton = new JButton("Home");
        JButton aboutButton = new JButton("About");
        JButton loginButton = new JButton("Login Panel");

        // Style buttons
        styleButton(homeButton);
        styleButton(aboutButton);
        styleButton(loginButton);

        // Add buttons to the navbar
        navbarPanel.add(homeButton);
        navbarPanel.add(aboutButton);
        navbarPanel.add(loginButton);

        // Add navbar to the home panel
        homePanel.add(navbarPanel, BorderLayout.NORTH);

        // Action listeners to switch pages
        homeButton.addActionListener(e -> cardLayout.show(cards, "Home"));
        aboutButton.addActionListener(e -> cardLayout.show(cards, "About"));
        loginButton.addActionListener(e -> cardLayout.show(cards, "Login"));

        // Header section
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(Color.BLACK); // Set background to black

        // Title label
        JLabel welcomeLabel = new JLabel("Welcome to Vehicle Booking System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(new Color(128, 0, 32)); // Burgundy red

        // Image to be displayed alongside the title
        ImageIcon logo = new ImageIcon("src/ihmproject/renbg.jpg"); // Change to your image path
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Adding image and title to header
        headerPanel.add(welcomeLabel, BorderLayout.NORTH); // Title below image
        headerPanel.add(logoLabel, BorderLayout.CENTER); // Image at the top

        // Add header panel to home panel
        homePanel.add(headerPanel, BorderLayout.CENTER);

        return homePanel;
    }

    private JPanel createAboutPage() {
        JPanel aboutPanel = new JPanel();
        aboutPanel.setLayout(new BorderLayout());
        aboutPanel.setBackground(Color.BLACK); // Set background to black

        // Create the navigation bar
        JPanel navbarPanel = new JPanel();
        navbarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
        navbarPanel.setBackground(new Color(128, 0, 32)); // Burgundy red

        // Home, About, and Login buttons
        JButton homeButton = new JButton("Home");
        JButton aboutButton = new JButton("About");
        JButton loginButton = new JButton("Login Panel");

        // Style buttons
        styleButton(homeButton);
        styleButton(aboutButton);
        styleButton(loginButton);

        // Add buttons to the navbar
        navbarPanel.add(homeButton);
        navbarPanel.add(aboutButton);
        navbarPanel.add(loginButton);

        // Add navbar to the about panel
        aboutPanel.add(navbarPanel, BorderLayout.NORTH);

        // Action listeners to switch pages
        homeButton.addActionListener(e -> cardLayout.show(cards, "Home"));
        aboutButton.addActionListener(e -> cardLayout.show(cards, "About"));
        loginButton.addActionListener(e -> cardLayout.show(cards, "Login"));

        // Header section
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(Color.BLACK); // Set background to black

        // Title label
        JLabel welcomeLabel = new JLabel("About Vehicle Booking System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(new Color(128, 0, 32)); // Burgundy red

        // Add title label to header
        headerPanel.add(welcomeLabel, BorderLayout.CENTER);

        // About description text using JTextPane for text formatting
        JTextPane aboutTextPane = new JTextPane();
        aboutTextPane.setText(
                "Vehicle Booking System is a system built to deal with problems faced in transportation.\n" +
                "Major problems include:\n" +
                "- Task allocation, tracking of vehicles, assigning routes, payment, and bookings.\n" +
                "- Overworking of employees and ensuring security of goods, users, and drivers.\n" +
                "- Maintenance of vehicles in terms of finance and time consumption.\n\n" +
                "Our system solves these issues using advanced GPS tracking, task assignment, \n" +
                "and digital financial reporting. We maintain comprehensive customer and vehicle records to \n" +
                "streamline the booking process."
        );

        // Set the text to be centered
        aboutTextPane.setEditable(false); // Make it non-editable
        aboutTextPane.setFont(new Font("Arial", Font.BOLD, 20));
        aboutTextPane.setBackground(Color.BLACK);
        aboutTextPane.setForeground(new Color(128, 200, 200)); // Light cyan text color

        // Center the text in the JTextPane
        StyledDocument doc = aboutTextPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        // Create a JScrollPane to make the JTextPane scrollable
        JScrollPane scrollPane = new JScrollPane(aboutTextPane);
        scrollPane.setPreferredSize(new Dimension(800, 400)); // Adjust size

        // Create a center panel to hold the text pane and center it
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(scrollPane, gbc);

        // Add the centerPanel to the center of the aboutPanel
        aboutPanel.add(centerPanel, BorderLayout.CENTER);

        return aboutPanel;
    }


    // Method to style buttons
    private void styleButton(JButton button) {
        button.setForeground(new Color(128, 0, 32)); // Burgundy red
        button.setBackground(Color.BLACK); // Black background
        button.setFocusPainted(false);
    }

    private JPanel createLoginPage() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BorderLayout());
        loginPanel.setBackground(Color.BLACK);

        // Create the navigation bar
        JPanel navbarPanel = new JPanel();
        navbarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
        navbarPanel.setBackground(new Color(128, 0, 32)); // Burgundy red

        // Home, About, and Login buttons
        JButton homeButton = new JButton("Home");
        JButton aboutButton = new JButton("About");
        JButton loginButton = new JButton("Login Panel");

        // Style buttons
        styleButton(homeButton);
        styleButton(aboutButton);
        styleButton(loginButton);

        // Add buttons to the navbar
        navbarPanel.add(homeButton);
        navbarPanel.add(aboutButton);
        navbarPanel.add(loginButton);

        // Add navbar to the login panel
        loginPanel.add(navbarPanel, BorderLayout.NORTH);

        // Action listeners to switch pages
        homeButton.addActionListener(e -> cardLayout.show(cards, "Home"));
        aboutButton.addActionListener(e -> cardLayout.show(cards, "About"));
        loginButton.addActionListener(e -> cardLayout.show(cards, "Login"));

        // Login form components
        JLabel loginLabel = new JLabel("Please Select Your Role", SwingConstants.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 24));
        loginLabel.setForeground(new Color(128, 0, 32)); // Burgundy red

        // Role selection buttons
        JButton adminButton = new JButton("Admin");
        JButton agencyManagerButton = new JButton("Agency Manager");
  

        // Style buttons
        styleButton(adminButton);
        styleButton(agencyManagerButton);
 

        // Create a JPanel for the center section (using GridBagLayout)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(Color.BLACK);

        // GridBagConstraints for centering components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(loginLabel, gbc);

        gbc.gridy++;
        centerPanel.add(adminButton, gbc);
        gbc.gridy++;
        centerPanel.add(agencyManagerButton, gbc);

        // Add the center panel to the center of the login panel
        loginPanel.add(centerPanel, BorderLayout.CENTER);

        // Action listeners for role selection
        adminButton.addActionListener(e -> createRoleLoginPage("Admin"));
        agencyManagerButton.addActionListener(e -> createRoleLoginPage("Agency Manager"));
       

        return loginPanel;
    }

    private void createRoleLoginPage(String role) {
        JPanel roleLoginPanel = new JPanel();
        roleLoginPanel.setLayout(new GridBagLayout());
        roleLoginPanel.setBackground(Color.BLACK); // Set background to black

        // Title label
        JLabel titleLabel = new JLabel(role + " Login/Signup", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(128, 0, 32)); // Burgundy red

        // Fields for Email and Password
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        JTextField emailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        JPasswordField passwordField = new JPasswordField(20);

        // Buttons
       
        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back to Home");

  
        styleButton(loginButton);
        styleButton(backButton);

        // Layout for the form
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        roleLoginPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        roleLoginPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        roleLoginPanel.add(emailField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        roleLoginPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        roleLoginPanel.add(passwordField, gbc);

        // Add buttons
        gbc.gridy++;

        gbc.gridx = 1;
        roleLoginPanel.add(loginButton, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        roleLoginPanel.add(backButton, gbc);

        // Action listeners for buttons
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            
            if (role.equals("Admin")) {
                // Admin login logic
                adminLogin(email, password);
            }  else if (role.equals("Agency Manager")) {
                // Agency Managerlogin logic
            	agencyManagerLogin(email, password);
            }else {
                // User login logic
                JOptionPane.showMessageDialog(this, role + " Login successful!");
            }
        });


        backButton.addActionListener(e -> cardLayout.show(cards, "Home"));

        // Add roleLoginPanel to the cards container
        cards.add(roleLoginPanel, role + " Login");
        cardLayout.show(cards, role + " Login");
    }
    private boolean validateLogin(String email, String password, String table) {
        try {
            String dbUrl = "jdbc:mysql://localhost:3306/vehiclebookings";
            String dbUser = "root";
            String dbPassword = "";
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String query = "SELECT a_email, a_pwd FROM " + table + " WHERE a_email = ? AND a_pwd = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database.");
            return false;
        }
    }

    private void adminLogin(String email, String password) {
        if (validateLogin(email, password, "tms_admin")) {
            JOptionPane.showMessageDialog(this, "Admin Login successful!");
            // Navigate to admin dashboard
            // Navigate to the Admin Dashboard
            JPanel adminDashboard = createDashboardPage("Admin");
            cards.add(adminDashboard, "Admin Dashboard");
            cardLayout.show(cards, "Admin Dashboard");
        } else {
            JOptionPane.showMessageDialog(this, "Access Denied. Please check your credentials.");
        }
    }

    private void agencyManagerLogin(String email, String password) {
        if (validateLogin(email, password, "tms_agency_manager")) {
            JOptionPane.showMessageDialog(this, "Agency Manager Login successful!");
            // Navigate to agency manager dashboard
            JPanel AgencyManagerDashboard = createDashboardPage("Agency Manager");
            cards.add(AgencyManagerDashboard, "Agency Manager Dashboard");
            cardLayout.show(cards, "Agency Manager Dashboard");
        } else {
            JOptionPane.showMessageDialog(this, "Access Denied. Please check your credentials.");
        }
    }

    

    private void createClientRegistrationPage() {
        JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(new GridBagLayout());
        registrationPanel.setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("Registration", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(128, 0, 32));

        // Fields for client registration
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField(20);
        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField(20);
        JLabel contactLabel = new JLabel("Contact:");
        JTextField contactField = new JTextField(20);
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(20);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JButton createAccountButton = new JButton("Create Account");
        JButton backToLoginButton = new JButton("Back to Login");

        // Style buttons
        styleButton(createAccountButton);
        styleButton(backToLoginButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        registrationPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        registrationPanel.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        registrationPanel.add(firstNameField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        registrationPanel.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        registrationPanel.add(lastNameField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        registrationPanel.add(contactLabel, gbc);
        gbc.gridx = 1;
        registrationPanel.add(contactField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        registrationPanel.add(addressLabel, gbc);
        gbc.gridx = 1;
        registrationPanel.add(addressField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        registrationPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        registrationPanel.add(emailField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        registrationPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        registrationPanel.add(passwordField, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        registrationPanel.add(createAccountButton, gbc);
        gbc.gridx = 0;
        registrationPanel.add(backToLoginButton, gbc);

        // Action listeners for buttons
        createAccountButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Account Created Successfully!");
            cardLayout.show(cards, "Login");
        });

        backToLoginButton.addActionListener(e -> cardLayout.show(cards, "Login"));

        cards.add(registrationPanel, "Client Registration");
        cardLayout.show(cards, "Client Registration");
    }

    private JPanel tablePanel;  // Declare the tablePanel globally

    private JPanel createDashboardPage(String role) {
        // Initialize the dashboardPanel
        dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new BorderLayout());
        dashboardPanel.setBackground(Color.BLACK);  // Set background to black

        // Create a container to stack title and search panel vertically
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));  // Stack vertically
        topPanel.setBackground(Color.BLACK);  // Set background to black for consistency

        // Create the title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align the title to the left
        titlePanel.setBackground(Color.BLACK); // Set background to black for consistency

        // Title for the dashboard
        JLabel dashboardLabel = new JLabel(role + " Dashboard", SwingConstants.LEFT);
        dashboardLabel.setFont(new Font("Arial", Font.BOLD, 30));
        dashboardLabel.setForeground(Color.WHITE); // Set title text color to white
        titlePanel.add(dashboardLabel); // Add title label to the title panel

        // Add title panel to the top panel (WEST region)
        topPanel.add(titlePanel, BorderLayout.WEST);

        // Create the search panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Align the search bar to the right
        searchPanel.setBackground(Color.BLACK); // Set background to black for consistency

        // Search label
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setForeground(Color.WHITE); // Set search label color to white
        searchPanel.add(searchLabel);

        // Search text field
        JTextField searchField = new JTextField(20);
        searchField.setBackground(Color.WHITE); // Set search field background to white
        searchField.setForeground(Color.BLACK); // Set text color to black for visibility
        searchPanel.add(searchField);

        // Add search panel to the top panel (EAST region)
        topPanel.add(searchPanel, BorderLayout.EAST);

        // Add top panel to the dashboard panel (NORTH region)
        dashboardPanel.add(topPanel, BorderLayout.NORTH);

        // Create the table panel
        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        // Center the table in the middle of the page using FlowLayout
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Center the content
        centerPanel.add(tablePanel);  // Add the tablePanel to the center panel

        // Create the HTML table as a string (initial data load)
        String htmlTable = getHTMLTableData("");  // Initially no filter

        // Display the HTML table inside a JTextPane
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(htmlTable);
        textPane.setEditable(false); // Make it non-editable
        textPane.setBackground(Color.BLACK);  // Set the background of the text pane to black
        textPane.setForeground(Color.WHITE);  // Set the text color to white

        // Wrap the JTextPane inside a JScrollPane
        JScrollPane scrollPane = new JScrollPane(textPane);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Add the table panel to the dashboard
        dashboardPanel.add(tablePanel, BorderLayout.CENTER);

        // Add DocumentListener to the search field to filter table data
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTable(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTable(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTable(searchField.getText());
            }
        });

     // Create the "Back to Login" button
        JButton backToLoginButton = new JButton("Back to Login");

        // Add action listener to navigate back to the login page
        backToLoginButton.addActionListener(e -> cardLayout.show(cards, "Login"));

        // Create a panel for the button and add it to the bottom of the dashboard
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Center the button
        buttonPanel.setBackground(Color.BLACK);  // Set background to black for consistency
        buttonPanel.add(backToLoginButton);  // Add the button to the panel

        // Add the button panel to the dashboard (SOUTH region)
        dashboardPanel.add(buttonPanel, BorderLayout.SOUTH);


        // Add the sidebar panel to the dashboard
        JPanel sidebarPanel = createSidebarPanel(role);

        // Create a container for the sidebar and main content
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(sidebarPanel);
        splitPane.setRightComponent(createMainDashboard(role));
        splitPane.setDividerSize(1); // Minimal divider size
        splitPane.setDividerLocation(200); // Set initial sidebar width

        // Add the split pane to the dashboard panel
        dashboardPanel.add(splitPane, BorderLayout.WEST);
        
        // Add the dashboard page to the 'cards' container
        cards.add(dashboardPanel, "Dashboard");

        // Show the dashboard page
        cardLayout.show(cards, "Dashboard");
        
        return dashboardPanel;
    }

    private void displayVehicleTable() {
        StringBuilder tableHtml = new StringBuilder(); // Initialize StringBuilder
        tableHtml.append("<html>");
        tableHtml.append("<head>"); // Add the <head> section for styles
        tableHtml.append("<style>") // Add some CSS styling to the table
                 .append("table {")
                 .append("  width: 100%;")
                 .append("  border-collapse: collapse;")
                 .append("  font-family: Arial, sans-serif;")
                 .append("  margin: 20px 0;")
                 .append("  background-color: #f4f4f4;")
                 .append("}")
                 .append("th, td {")
                 .append("  padding: 10px;")
                 .append("  text-align: left;")
                 .append("  border: 1px solid #ddd;")
                 .append("}")
                 .append("th {")
                 .append("  background-color: #800020;") // Burgundy red
                 .append("  color: white;")
                 .append("}")
                 .append("tr:nth-child(even) {")
                 .append("  background-color: #f2f2f2;")
                 .append("}")
                 .append("tr:hover {")
                 .append("  background-color: #ddd;")
                 .append("}")
                 .append("</style>");
        tableHtml.append("</head>");
        
        tableHtml.append("<body>");
        tableHtml.append("<table class='table table-bordered table-striped table-hover' id='dataTable' cellspacing='0'>");
        tableHtml.append("<thead><tr>");
        tableHtml.append("<th>rowCount</th>");
        tableHtml.append("<th>Name</th>");
        tableHtml.append("<th>passengerNo</th>");
        tableHtml.append("<th>category</th>");
        tableHtml.append("<th>Reg No</th>");
        tableHtml.append("<th>status</th>");
       
        // Vehicle panel setup
        JPanel vehiclePanel = new JPanel(new BorderLayout());
        vehiclePanel.setBackground(Color.BLACK);  // Set background to black

        // Create a container to stack title and search panel vertically
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.BLACK);  // Set background to black for consistency

        // Create the title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align the title to the left
        titlePanel.setBackground(Color.BLACK); // Set background to black for consistency

        // Title for the dashboard
        JLabel dashboardLabel = new JLabel( "Admin Dashboard", SwingConstants.LEFT);
        dashboardLabel.setFont(new Font("Arial", Font.BOLD, 30));
        dashboardLabel.setForeground(Color.WHITE); // Set title text color to white
        titlePanel.add(dashboardLabel); // Add title label to the title panel

        // Add title panel to the top panel
        topPanel.add(titlePanel, BorderLayout.NORTH); // Use NORTH for title

        // Add the topPanel to the main dashboard container (ensure this is done)
        dashboardPanel.removeAll(); // Clear any previous content
        dashboardPanel.add(topPanel, BorderLayout.NORTH); // Add title panel to the top of the layout
        dashboardPanel.add(vehiclePanel, BorderLayout.CENTER); // Add vehicle panel below the title

        dashboardPanel.revalidate();
        dashboardPanel.repaint();

        String[] columnNames = {"Name", "Registration No", "Category", "Passenger No",  "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclebookings", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tms_vehicle");

            int rowCount = 1; // For row number
            while (rs.next()) {
                String name = rs.getString("v_name");
                String regNo = rs.getString("v_reg_no");
                String category = rs.getString("v_category");
                String passengerNo = rs.getString("v_pass_no");
                String status = rs.getString("v_status");
                
                // Add each result as a row in the table
                tableHtml.append("<tr>")
                         .append("<td>").append(rowCount).append("</td>")
                         .append("<td>").append(name).append("</td>")
                         .append("<td>").append(passengerNo).append("</td>") // Assuming phone is passenger number
                         .append("<td>").append(category).append("</td>")
                         .append("<td>").append(regNo).append("</td>")
                         .append("<td>").append(status).append("</td>")
                         .append("</tr>");
                rowCount++;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableHtml.append("</tbody></table>");
        tableHtml.append("</body></html>");

        // Set the generated HTML as the content of the vehicle panel (e.g., using a JLabel or JEditorPane)
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(tableHtml.toString());
        textPane.setEditable(false); // Make it non-editable
        textPane.setBackground(Color.BLACK);  // Set the background of the text pane to black
        textPane.setForeground(Color.WHITE);  // Set the text color to white

        // Wrap the JTextPane inside a JScrollPane
        JScrollPane scrollPane = new JScrollPane(textPane);
        
        // Create the table panel and add the scrollPane to it
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
     // Add a title for the vehiclePanel
        JLabel vehiclePanelTitle = new JLabel("Vehicle / view", SwingConstants.CENTER);
        vehiclePanelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        vehiclePanelTitle.setForeground(Color.WHITE); // Set title text color to white

        // Add the title to the top of the vehiclePanel
        vehiclePanel.add(vehiclePanelTitle, BorderLayout.NORTH);
        // Center the table panel inside the vehicle panel
        vehiclePanel.add(tablePanel, BorderLayout.CENTER);

        // Add the sidebar panel to the dashboard
        JPanel sidebarPanel = createSidebarPanel(role);

        // Create a container for the sidebar and main content
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(sidebarPanel);
        splitPane.setRightComponent(createMainDashboard(role));
        splitPane.setDividerSize(1); // Minimal divider size
        splitPane.setDividerLocation(200); // Set initial sidebar width

        // Add the split pane to the dashboard panel
        dashboardPanel.add(splitPane, BorderLayout.WEST);
        // Create the "Back to Login" button
        JButton backToLoginButton = new JButton("Back to Login");

        // Add action listener to navigate back to the login page
        backToLoginButton.addActionListener(e -> cardLayout.show(cards, "Login"));

        // Create a panel for the button and add it to the bottom of the dashboard
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Center the button
        buttonPanel.setBackground(Color.BLACK);  // Set background to black for consistency
        buttonPanel.add(backToLoginButton);  // Add the button to the panel

        // Add the button panel to the dashboard (SOUTH region)
        dashboardPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    private void displayuserTable() {
        StringBuilder tableHtml = new StringBuilder(); // Initialize StringBuilder
        tableHtml.append("<html>");
        tableHtml.append("<head>"); // Add the <head> section for styles
        tableHtml.append("<style>") // Add some CSS styling to the table
                 .append("table {")
                 .append("  width: 100%;")
                 .append("  border-collapse: collapse;")
                 .append("  font-family: Arial, sans-serif;")
                 .append("  margin: 20px 0;")
                 .append("  background-color: #f4f4f4;")
                 .append("}")
                 .append("th, td {")
                 .append("  padding: 10px;")
                 .append("  text-align: left;")
                 .append("  border: 1px solid #ddd;")
                 .append("}")
                 .append("th {")
                 .append("  background-color: #800020;") // Burgundy red
                 .append("  color: white;")
                 .append("}")
                 .append("tr:nth-child(even) {")
                 .append("  background-color: #f2f2f2;")
                 .append("}")
                 .append("tr:hover {")
                 .append("  background-color: #ddd;")
                 .append("}")
                 .append("</style>");
        tableHtml.append("</head>");
        
        tableHtml.append("<body>");
        tableHtml.append("<table class='table table-bordered table-striped table-hover' id='dataTable' cellspacing='0'>");
        tableHtml.append("<thead><tr>");
        tableHtml.append("<th>Name</th>");
        tableHtml.append("<th>Contact</th>");
        tableHtml.append("<th>Address</th>");
        tableHtml.append("<th>Email</th>");

        // Vehicle panel setup
        JPanel vehiclePanel = new JPanel(new BorderLayout());
        vehiclePanel.setBackground(Color.BLACK);  // Set background to black

        // Create a container to stack title and search panel vertically
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.BLACK);  // Set background to black for consistency

        // Create the title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align the title to the left
        titlePanel.setBackground(Color.BLACK); // Set background to black for consistency

        // Title for the dashboard
        JLabel dashboardLabel = new JLabel( "Admin Dashboard", SwingConstants.LEFT);
        dashboardLabel.setFont(new Font("Arial", Font.BOLD, 30));
        dashboardLabel.setForeground(Color.WHITE); // Set title text color to white
        titlePanel.add(dashboardLabel); // Add title label to the title panel

        // Add title panel to the top panel
        topPanel.add(titlePanel, BorderLayout.NORTH); // Use NORTH for title

        // Add the topPanel to the main dashboard container (ensure this is done)
        dashboardPanel.removeAll(); // Clear any previous content
        dashboardPanel.add(topPanel, BorderLayout.NORTH); // Add title panel to the top of the layout
        dashboardPanel.add(vehiclePanel, BorderLayout.CENTER); // Add vehicle panel below the title

        dashboardPanel.revalidate();
        dashboardPanel.repaint();

        String[] columnNames = {"Name", "Contact", "Address", "Email"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclebookings", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tms_user");

            int rowCount = 1; // For row number
            while (rs.next()) {
                String name = rs.getString("u_fname");
                String Contact = rs.getString("u_phone");
                String Address = rs.getString("u_addr");
                String Email = rs.getString("u_email");

                // Add each result as a row in the table
                tableHtml.append("<tr>")
                         .append("<td>").append(name).append("</td>")
                         .append("<td>").append(Contact).append("</td>") // Assuming phone is passenger number
                         .append("<td>").append(Address).append("</td>")
                         .append("<td>").append(Email).append("</td>")
                         .append("</tr>");
                rowCount++;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableHtml.append("</tbody></table>");
        tableHtml.append("</body></html>");

        // Set the generated HTML as the content of the vehicle panel (e.g., using a JLabel or JEditorPane)
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(tableHtml.toString());
        textPane.setEditable(false); // Make it non-editable
        textPane.setBackground(Color.BLACK);  // Set the background of the text pane to black
        textPane.setForeground(Color.WHITE);  // Set the text color to white

        // Wrap the JTextPane inside a JScrollPane
        JScrollPane scrollPane = new JScrollPane(textPane);
        
        // Create the table panel and add the scrollPane to it
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
     // Add a title for the vehiclePanel
        JLabel vehiclePanelTitle = new JLabel("user / view", SwingConstants.CENTER);
        vehiclePanelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        vehiclePanelTitle.setForeground(Color.WHITE); // Set title text color to white

        // Add the title to the top of the vehiclePanel
        vehiclePanel.add(vehiclePanelTitle, BorderLayout.NORTH);
        // Center the table panel inside the vehicle panel
        vehiclePanel.add(tablePanel, BorderLayout.CENTER);

        // Add the sidebar panel to the dashboard
        JPanel sidebarPanel = createSidebarPanel(role);

        // Create a container for the sidebar and main content
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(sidebarPanel);
        splitPane.setRightComponent(createMainDashboard(role));
        splitPane.setDividerSize(1); // Minimal divider size
        splitPane.setDividerLocation(200); // Set initial sidebar width

        // Add the split pane to the dashboard panel
        dashboardPanel.add(splitPane, BorderLayout.WEST);
        // Create the "Back to Login" button
        JButton backToLoginButton = new JButton("Back to Login");

        // Add action listener to navigate back to the login page
        backToLoginButton.addActionListener(e -> cardLayout.show(cards, "Login"));

        // Create a panel for the button and add it to the bottom of the dashboard
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Center the button
        buttonPanel.setBackground(Color.BLACK);  // Set background to black for consistency
        buttonPanel.add(backToLoginButton);  // Add the button to the panel

        // Add the button panel to the dashboard (SOUTH region)
        dashboardPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    private void displaymanagerTable() {
        StringBuilder tableHtml = new StringBuilder(); // Initialize StringBuilder
        tableHtml.append("<html>");
        tableHtml.append("<head>"); // Add the <head> section for styles
        tableHtml.append("<style>") // Add some CSS styling to the table
                 .append("table {")
                 .append("  width: 100%;")
                 .append("  border-collapse: collapse;")
                 .append("  font-family: Arial, sans-serif;")
                 .append("  margin: 20px 0;")
                 .append("  background-color: #f4f4f4;")
                 .append("}")
                 .append("th, td {")
                 .append("  padding: 10px;")
                 .append("  text-align: left;")
                 .append("  border: 1px solid #ddd;")
                 .append("}")
                 .append("th {")
                 .append("  background-color: #800020;") // Burgundy red
                 .append("  color: white;")
                 .append("}")
                 .append("tr:nth-child(even) {")
                 .append("  background-color: #f2f2f2;")
                 .append("}")
                 .append("tr:hover {")
                 .append("  background-color: #ddd;")
                 .append("}")
                 .append("</style>");
        tableHtml.append("</head>");
        
        tableHtml.append("<body>");
        tableHtml.append("<table class='table table-bordered table-striped table-hover' id='dataTable' cellspacing='0'>");
        tableHtml.append("<thead><tr>");
        tableHtml.append("<th>#</th>");
        tableHtml.append("<th>Name</th>");
        tableHtml.append("<th>Email</th>");
        tableHtml.append("<th>Password</th>");
        tableHtml.append("<th>Contact</th>");
        tableHtml.append("<th>Address</th>");
       
      	
        // Vehicle panel setup
        JPanel vehiclePanel = new JPanel(new BorderLayout());
        vehiclePanel.setBackground(Color.BLACK);  // Set background to black

        // Create a container to stack title and search panel vertically
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.BLACK);  // Set background to black for consistency

        // Create the title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align the title to the left
        titlePanel.setBackground(Color.BLACK); // Set background to black for consistency

        // Title for the dashboard
        JLabel dashboardLabel = new JLabel( "Admin Dashboard", SwingConstants.LEFT);
        dashboardLabel.setFont(new Font("Arial", Font.BOLD, 30));
        dashboardLabel.setForeground(Color.WHITE); // Set title text color to white
        titlePanel.add(dashboardLabel); // Add title label to the title panel

        // Add title panel to the top panel
        topPanel.add(titlePanel, BorderLayout.NORTH); // Use NORTH for title

        // Add the topPanel to the main dashboard container (ensure this is done)
        dashboardPanel.removeAll(); // Clear any previous content
        dashboardPanel.add(topPanel, BorderLayout.NORTH); // Add title panel to the top of the layout
        dashboardPanel.add(vehiclePanel, BorderLayout.CENTER); // Add vehicle panel below the title

        dashboardPanel.revalidate();
        dashboardPanel.repaint();

        String[] columnNames = {"#","Name", "Email", "Password","Contact", "Address"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclebookings", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tms_agency_manager");

            int rowCount = 1; // For row number
            while (rs.next()) { 
            	String id = rs.getString("a_id");
                String name = rs.getString("a_fname");
                String Email = rs.getString("a_email");
                String Password = rs.getString("a_pwd");
                String Contact = rs.getString("a_phone");
                String Address = rs.getString("a_addr");
               

                // Add each result as a row in the table
                tableHtml.append("<tr>")
                         .append("<td>").append(id).append("</td>")
                         .append("<td>").append(name).append("</td>")
                         .append("<td>").append(Email).append("</td>")
                         .append("<td>").append(Password).append("</td>")
                         .append("<td>").append(Contact).append("</td>") // Assuming phone is passenger number
                         .append("<td>").append(Address).append("</td>")
                         .append("</tr>");
                rowCount++;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableHtml.append("</tbody></table>");
        tableHtml.append("</body></html>");

        // Set the generated HTML as the content of the vehicle panel (e.g., using a JLabel or JEditorPane)
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(tableHtml.toString());
        textPane.setEditable(false); // Make it non-editable
        textPane.setBackground(Color.BLACK);  // Set the background of the text pane to black
        textPane.setForeground(Color.WHITE);  // Set the text color to white

        // Wrap the JTextPane inside a JScrollPane
        JScrollPane scrollPane = new JScrollPane(textPane);
        
        // Create the table panel and add the scrollPane to it
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
     // Add a title for the vehiclePanel
        JLabel vehiclePanelTitle = new JLabel("Agency manager / view", SwingConstants.CENTER);
        vehiclePanelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        vehiclePanelTitle.setForeground(Color.WHITE); // Set title text color to white

        // Add the title to the top of the vehiclePanel
        vehiclePanel.add(vehiclePanelTitle, BorderLayout.NORTH);
        // Center the table panel inside the vehicle panel
        vehiclePanel.add(tablePanel, BorderLayout.CENTER);

        // Add the sidebar panel to the dashboard
        JPanel sidebarPanel = createSidebarPanel(role);

        // Create a container for the sidebar and main content
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(sidebarPanel);
        splitPane.setRightComponent(createMainDashboard(role));
        splitPane.setDividerSize(1); // Minimal divider size
        splitPane.setDividerLocation(200); // Set initial sidebar width

        // Add the split pane to the dashboard panel
        dashboardPanel.add(splitPane, BorderLayout.WEST);
        // Create the "Back to Login" button
        JButton backToLoginButton = new JButton("Back to Login");

        // Add action listener to navigate back to the login page
        backToLoginButton.addActionListener(e -> cardLayout.show(cards, "Login"));

        // Create a panel for the button and add it to the bottom of the dashboard
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Center the button
        buttonPanel.setBackground(Color.BLACK);  // Set background to black for consistency
        buttonPanel.add(backToLoginButton);  // Add the button to the panel

        // Add the button panel to the dashboard (SOUTH region)
        dashboardPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    private void displaybookingTable() {
        StringBuilder tableHtml = new StringBuilder(); // Initialize StringBuilder
        tableHtml.append("<html>");
        tableHtml.append("<head>"); // Add the <head> section for styles
        tableHtml.append("<style>") // Add some CSS styling to the table
                 .append("table {")
                 .append("  width: 100%;")
                 .append("  border-collapse: collapse;")
                 .append("  font-family: Arial, sans-serif;")
                 .append("  margin: 20px 0;")
                 .append("  background-color: #f4f4f4;")
                 .append("}")
                 .append("th, td {")
                 .append("  padding: 10px;")
                 .append("  text-align: left;")
                 .append("  border: 1px solid #ddd;")
                 .append("}")
                 .append("th {")
                 .append("  background-color: #800020;") // Burgundy red
                 .append("  color: white;")
                 .append("}")
                 .append("tr:nth-child(even) {")
                 .append("  background-color: #f2f2f2;")
                 .append("}")
                 .append("tr:hover {")
                 .append("  background-color: #ddd;")
                 .append("}")
                 .append("</style>");
        tableHtml.append("</head>");
        
        tableHtml.append("<body>");
        tableHtml.append("<table class='table table-bordered table-striped table-hover' id='dataTable' cellspacing='0'>");
        tableHtml.append("<thead><tr>");
        tableHtml.append("<th>Name</th>");
        tableHtml.append("<th>Contact</th>");
        tableHtml.append("<th>Address</th>");
        tableHtml.append("<th>Email</th>");

        // Vehicle panel setup
        JPanel vehiclePanel = new JPanel(new BorderLayout());
        vehiclePanel.setBackground(Color.BLACK);  // Set background to black

        // Create a container to stack title and search panel vertically
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.BLACK);  // Set background to black for consistency

        // Create the title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align the title to the left
        titlePanel.setBackground(Color.BLACK); // Set background to black for consistency

        // Title for the dashboard
        JLabel dashboardLabel = new JLabel( "Admin Dashboard", SwingConstants.LEFT);
        dashboardLabel.setFont(new Font("Arial", Font.BOLD, 30));
        dashboardLabel.setForeground(Color.WHITE); // Set title text color to white
        titlePanel.add(dashboardLabel); // Add title label to the title panel

        // Add title panel to the top panel
        topPanel.add(titlePanel, BorderLayout.NORTH); // Use NORTH for title

        // Add the topPanel to the main dashboard container (ensure this is done)
        dashboardPanel.removeAll(); // Clear any previous content
        dashboardPanel.add(topPanel, BorderLayout.NORTH); // Add title panel to the top of the layout
        dashboardPanel.add(vehiclePanel, BorderLayout.CENTER); // Add vehicle panel below the title

        dashboardPanel.revalidate();
        dashboardPanel.repaint();

        String[] columnNames = {"Name", "Contact", "Address", "Email"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        tableHtml.append("<th>Action</th>"); // Add a column for the button

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclebookings", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tms_user where u_car_book_status = 'Approved' || u_car_book_status = 'Pending'");

            while (rs.next()) {
                int userId = rs.getInt("u_id");
                String name = rs.getString("u_fname");
                String contact = rs.getString("u_phone");
                String address = rs.getString("u_addr");
                String email = rs.getString("u_email");
                String status = rs.getString("u_car_book_status");

                tableHtml.append("<tr>")
                         .append("<td>").append(name).append("</td>")
                         .append("<td>").append(contact).append("</td>")
                         .append("<td>").append(address).append("</td>")
                         .append("<td>").append(email).append("</td>")
                         .append("<td>").append(status).append("</td>")
                         .append("<td>")
                         .append("<form action='/updateStatus' method='post'>") // Form submission to server
                         .append("<input type='hidden' name='userId' value='").append(userId).append("'>") // Hidden field for userId
                         .append("<input type='hidden' name='status' value='Approved'>") // Hidden field for status
                         .append("<input type='submit' value='Book Vehicle'>") // Submit button
                         .append("</form>")
                         .append("</td>")
                         .append("</tr>");
            }
            while (rs.next()) {
                int userId = rs.getInt("u_id");
                String name = rs.getString("u_fname");
                String contact = rs.getString("u_phone");
                String address = rs.getString("u_addr");
                String email = rs.getString("u_email");
                String status = rs.getString("u_car_book_status");

                // Create the row in your JPanel (Swing)
                JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                rowPanel.add(new JLabel(name));
                rowPanel.add(new JLabel(contact));
                rowPanel.add(new JLabel(address));
                rowPanel.add(new JLabel(email));
                rowPanel.add(new JLabel(status));

                // Create the "Book Vehicle" button in your JPanel for vehicle panel
                JButton bookVehicleButton = new JButton("Book Vehicle");
                bookVehicleButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Open the BookingForm when the button is clicked
                    	 BookingForm(); 
                    }
                });

                // Add the button to your rowPanel
                rowPanel.add(bookVehicleButton);

                // Add the rowPanel to your main panel (assuming you have a parent panel to hold these rows)
                vehiclePanel.add(rowPanel); // Assuming 'mainPanel' is where you want to add the rows
            }



            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
  
      


        tableHtml.append("</tbody></table>");
        tableHtml.append("</body></html>");

        // Set the generated HTML as the content of the vehicle panel (e.g., using a JLabel or JEditorPane)
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(tableHtml.toString());
        textPane.setEditable(false); // Make it non-editable
        textPane.setBackground(Color.BLACK);  // Set the background of the text pane to black
        textPane.setForeground(Color.WHITE);  // Set the text color to white

        // Wrap the JTextPane inside a JScrollPane
        JScrollPane scrollPane = new JScrollPane(textPane);
        
        // Create the table panel and add the scrollPane to it
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
     // Add a title for the vehiclePanel
        JLabel vehiclePanelTitle = new JLabel("Booking / Add", SwingConstants.CENTER);
        vehiclePanelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        vehiclePanelTitle.setForeground(Color.WHITE); // Set title text color to white

        // Add the title to the top of the vehiclePanel
        vehiclePanel.add(vehiclePanelTitle, BorderLayout.NORTH);
        // Center the table panel inside the vehicle panel
        vehiclePanel.add(tablePanel, BorderLayout.CENTER);

        // Add the sidebar panel to the dashboard
        JPanel sidebarPanel = createSidebarPanel(role);

        // Create a container for the sidebar and main content
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(sidebarPanel);
        splitPane.setRightComponent(createMainDashboard(role));
        splitPane.setDividerSize(1); // Minimal divider size
        splitPane.setDividerLocation(200); // Set initial sidebar width

        // Add the split pane to the dashboard panel
        dashboardPanel.add(splitPane, BorderLayout.WEST);
        // Create the "Back to Login" button
        JButton backToLoginButton = new JButton("Back to Login");

        // Add action listener to navigate back to the login page
        backToLoginButton.addActionListener(e -> cardLayout.show(cards, "Login"));

        // Create a panel for the button and add it to the bottom of the dashboard
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Center the button
        buttonPanel.setBackground(Color.BLACK);  // Set background to black for consistency
        buttonPanel.add(backToLoginButton);  // Add the button to the panel

        // Add the button panel to the dashboard (SOUTH region)
        dashboardPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    public void  BookingForm (){
    	   
        // Set up the frame
        setTitle("Book Vehicle");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(11, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add form fields
        mainPanel.add(new JLabel("First Name:"));
        JTextField firstNameField = new JTextField();
        mainPanel.add(firstNameField);

        mainPanel.add(new JLabel("Last Name:"));
        JTextField lastNameField = new JTextField();
        mainPanel.add(lastNameField);

        mainPanel.add(new JLabel("User:"));
        JTextField userField = new JTextField();
        mainPanel.add(userField);

        mainPanel.add(new JLabel("Contact:"));
        JTextField contactField = new JTextField("070678909");
        mainPanel.add(contactField);

        mainPanel.add(new JLabel("Address:"));
        JTextField addressField = new JTextField();
        mainPanel.add(addressField);

        mainPanel.add(new JLabel("Email Address:"));
        JTextField emailField = new JTextField();
        mainPanel.add(emailField);

        mainPanel.add(new JLabel("Vehicle Category:"));
        JTextField categoryField = new JTextField();
        mainPanel.add(categoryField);

        mainPanel.add(new JLabel("Vehicle Registration Number:"));
        JTextField regNumberField = new JTextField();
        mainPanel.add(regNumberField);

        mainPanel.add(new JLabel("Booking Date:"));
        JTextField bookingDateField = new JTextField("mm/dd/yyyy");
        mainPanel.add(bookingDateField);

        mainPanel.add(new JLabel("Booking Status:"));
        JTextField bookingStatusField = new JTextField("Pending");
        mainPanel.add(bookingStatusField);

        // Add a submit button
        JButton submitButton = new JButton("Submit");
        mainPanel.add(submitButton);

        // Add a cancel button
        JButton cancelButton = new JButton("Cancel");
        mainPanel.add(cancelButton);

        // Add action listeners
        submitButton.addActionListener(e -> {
            // Handle form submission and update the database
            JOptionPane.showMessageDialog(this, "Booking Submitted!");
            // You can add database update logic here
            dispose(); // Close the form
        });

        cancelButton.addActionListener(e -> dispose()); // Close the form on cancel

        // Add the main panel to the frame
        add(mainPanel);

        // Make the frame visible
        setVisible(true);
    
}

    public void bookVehicle(int userId) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclebookings", "root", "");
            String query = "UPDATE tms_user SET status = 'Pending' WHERE u_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Vehicle booked successfully for user ID: " + userId);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displaybookingviewTable() {
        StringBuilder tableHtml = new StringBuilder(); // Initialize StringBuilder
        tableHtml.append("<html>");
        tableHtml.append("<head>"); // Add the <head> section for styles
        tableHtml.append("<style>") // Add some CSS styling to the table
                 .append("table {")
                 .append("  width: 100%;")
                 .append("  border-collapse: collapse;")
                 .append("  font-family: Arial, sans-serif;")
                 .append("  margin: 20px 0;")
                 .append("  background-color: #f4f4f4;")
                 .append("}")
                 .append("th, td {")
                 .append("  padding: 10px;")
                 .append("  text-align: left;")
                 .append("  border: 1px solid #ddd;")
                 .append("}")
                 .append("th {")
                 .append("  background-color: #800020;") // Burgundy red
                 .append("  color: white;")
                 .append("}")
                 .append("tr:nth-child(even) {")
                 .append("  background-color: #f2f2f2;")
                 .append("}")
                 .append("tr:hover {")
                 .append("  background-color: #ddd;")
                 .append("}")
                 .append("</style>");
        tableHtml.append("</head>");
        
        tableHtml.append("<body>");
        tableHtml.append("<table class='table table-bordered table-striped table-hover' id='dataTable' cellspacing='0'>");
        tableHtml.append("<thead><tr>");
        tableHtml.append("<th>#</th>");
        tableHtml.append("<th>Name</th>");
        tableHtml.append("<th>Contact</th>");
        tableHtml.append("<th>Vehicle Type</th>");
        tableHtml.append("<th>Vehicle Reg No</th>");
        tableHtml.append("<th>Booking date</th>");
        tableHtml.append("<th>Status</th>");
               // Vehicle panel setup
        JPanel vehiclePanel = new JPanel(new BorderLayout());
        vehiclePanel.setBackground(Color.BLACK);  // Set background to black

        // Create a container to stack title and search panel vertically
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.BLACK);  // Set background to black for consistency

        // Create the title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align the title to the left
        titlePanel.setBackground(Color.BLACK); // Set background to black for consistency

        // Title for the dashboard
        JLabel dashboardLabel = new JLabel( "Admin Dashboard", SwingConstants.LEFT);
        dashboardLabel.setFont(new Font("Arial", Font.BOLD, 30));
        dashboardLabel.setForeground(Color.WHITE); // Set title text color to white
        titlePanel.add(dashboardLabel); // Add title label to the title panel

        // Add title panel to the top panel
        topPanel.add(titlePanel, BorderLayout.NORTH); // Use NORTH for title

        // Add the topPanel to the main dashboard container (ensure this is done)
        dashboardPanel.removeAll(); // Clear any previous content
        dashboardPanel.add(topPanel, BorderLayout.NORTH); // Add title panel to the top of the layout
        dashboardPanel.add(vehiclePanel, BorderLayout.CENTER); // Add vehicle panel below the title

        dashboardPanel.revalidate();
        dashboardPanel.repaint();

        String[] columnNames = {"#","Name","Contact","Vehicle Type", "Vehicle Reg No", "Booking date", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclebookings", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tms_user where u_car_book_status = 'Approved' || u_car_book_status = 'Pending'");
        
        		
            int rowCount = 1; // For row number
            while (rs.next()) {
            	 String id = rs.getString("u_id");
                String name = rs.getString("u_fname");
                String Contact = rs.getString("u_phone");
                String VehicleType = rs.getString("u_car_type");
                String VehicleRegNo = rs.getString("u_car_regno");
                String Bookingdate = rs.getString("u_car_bookdate");
                String Status = rs.getString("u_car_book_status");
             

                // Add each result as a row in the table
                tableHtml.append("<tr>")
                         .append("<td>").append(id).append("</td>")
                         .append("<td>").append(name).append("</td>")
                         .append("<td>").append(Contact).append("</td>") // Assuming phone is passenger number
                         .append("<td>").append(VehicleType).append("</td>")
                         .append("<td>").append(VehicleRegNo).append("</td>")
                         .append("<td>").append(Bookingdate).append("</td>")
                         .append("<td>").append(Status).append("</td>")
                         .append("</tr>");
                rowCount++;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableHtml.append("</tbody></table>");
        tableHtml.append("</body></html>");

        // Set the generated HTML as the content of the vehicle panel (e.g., using a JLabel or JEditorPane)
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(tableHtml.toString());
        textPane.setEditable(false); // Make it non-editable
        textPane.setBackground(Color.BLACK);  // Set the background of the text pane to black
        textPane.setForeground(Color.WHITE);  // Set the text color to white

        // Wrap the JTextPane inside a JScrollPane
        JScrollPane scrollPane = new JScrollPane(textPane);
        
        // Create the table panel and add the scrollPane to it
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
     // Add a title for the vehiclePanel
        JLabel vehiclePanelTitle = new JLabel("Booking / view", SwingConstants.CENTER);
        vehiclePanelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        vehiclePanelTitle.setForeground(Color.WHITE); // Set title text color to white

        // Add the title to the top of the vehiclePanel
        vehiclePanel.add(vehiclePanelTitle, BorderLayout.NORTH);
        // Center the table panel inside the vehicle panel
        vehiclePanel.add(tablePanel, BorderLayout.CENTER);

        // Add the sidebar panel to the dashboard
        JPanel sidebarPanel = createSidebarPanel(role);

        // Create a container for the sidebar and main content
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(sidebarPanel);
        splitPane.setRightComponent(createMainDashboard(role));
        splitPane.setDividerSize(1); // Minimal divider size
        splitPane.setDividerLocation(200); // Set initial sidebar width

        // Add the split pane to the dashboard panel
        dashboardPanel.add(splitPane, BorderLayout.WEST);
        // Create the "Back to Login" button
        JButton backToLoginButton = new JButton("Back to Login");

        // Add action listener to navigate back to the login page
        backToLoginButton.addActionListener(e -> cardLayout.show(cards, "Login"));

        // Create a panel for the button and add it to the bottom of the dashboard
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Center the button
        buttonPanel.setBackground(Color.BLACK);  // Set background to black for consistency
        buttonPanel.add(backToLoginButton);  // Add the button to the panel

        // Add the button panel to the dashboard (SOUTH region)
        dashboardPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

private void displaybookingmanageTable() {
    StringBuilder tableHtml = new StringBuilder(); // Initialize StringBuilder
    tableHtml.append("<html>");
    tableHtml.append("<head>"); // Add the <head> section for styles
    tableHtml.append("<style>") // Add some CSS styling to the table
             .append("table {")
             .append("  width: 100%;")
             .append("  border-collapse: collapse;")
             .append("  font-family: Arial, sans-serif;")
             .append("  margin: 20px 0;")
             .append("  background-color: #f4f4f4;")
             .append("}")
             .append("th, td {")
             .append("  padding: 10px;")
             .append("  text-align: left;")
             .append("  border: 1px solid #ddd;")
             .append("}")
             .append("th {")
             .append("  background-color: #800020;") // Burgundy red
             .append("  color: white;")
             .append("}")
             .append("tr:nth-child(even) {")
             .append("  background-color: #f2f2f2;")
             .append("}")
             .append("tr:hover {")
             .append("  background-color: #ddd;")
             .append("}")
             .append("</style>");
    tableHtml.append("</head>");
    
    tableHtml.append("<body>");
    tableHtml.append("<table class='table table-bordered table-striped table-hover' id='dataTable' cellspacing='0'>");
    tableHtml.append("<thead><tr>");
    tableHtml.append("<th>#</th>");
    tableHtml.append("<th>Name</th>");
    tableHtml.append("<th>Contact</th>");
    tableHtml.append("<th>Vehicle Type</th>");
    tableHtml.append("<th>Vehicle Reg No</th>");
    tableHtml.append("<th>Booking date</th>");
    tableHtml.append("<th>Status</th>");
           // Vehicle panel setup
    JPanel vehiclePanel = new JPanel(new BorderLayout());
    vehiclePanel.setBackground(Color.BLACK);  // Set background to black

    // Create a container to stack title and search panel vertically
    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.setBackground(Color.BLACK);  // Set background to black for consistency

    // Create the title panel
    JPanel titlePanel = new JPanel();
    titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align the title to the left
    titlePanel.setBackground(Color.BLACK); // Set background to black for consistency

    // Title for the dashboard
    JLabel dashboardLabel = new JLabel( "Admin Dashboard", SwingConstants.LEFT);
    dashboardLabel.setFont(new Font("Arial", Font.BOLD, 30));
    dashboardLabel.setForeground(Color.WHITE); // Set title text color to white
    titlePanel.add(dashboardLabel); // Add title label to the title panel

    // Add title panel to the top panel
    topPanel.add(titlePanel, BorderLayout.NORTH); // Use NORTH for title

    // Add the topPanel to the main dashboard container (ensure this is done)
    dashboardPanel.removeAll(); // Clear any previous content
    dashboardPanel.add(topPanel, BorderLayout.NORTH); // Add title panel to the top of the layout
    dashboardPanel.add(vehiclePanel, BorderLayout.CENTER); // Add vehicle panel below the title

    dashboardPanel.revalidate();
    dashboardPanel.repaint();

    String[] columnNames = {"#","Name","Contact","Vehicle Type", "Vehicle Reg No", "Booking date", "Status"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclebookings", "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tms_user where u_car_book_status = 'Approved' || u_car_book_status = 'Pending'");
    
    		
        int rowCount = 1; // For row number
        while (rs.next()) {
        	 String id = rs.getString("u_id");
            String name = rs.getString("u_fname");
            String Contact = rs.getString("u_phone");
            String VehicleType = rs.getString("u_car_type");
            String VehicleRegNo = rs.getString("u_car_regno");
            String Bookingdate = rs.getString("u_car_bookdate");
            String Status = rs.getString("u_car_book_status");
         

            // Add each result as a row in the table
            tableHtml.append("<tr>")
                     .append("<td>").append(id).append("</td>")
                     .append("<td>").append(name).append("</td>")
                     .append("<td>").append(Contact).append("</td>") // Assuming phone is passenger number
                     .append("<td>").append(VehicleType).append("</td>")
                     .append("<td>").append(VehicleRegNo).append("</td>")
                     .append("<td>").append(Bookingdate).append("</td>")
                     .append("<td>").append(Status).append("</td>")
                     .append("</tr>");
            rowCount++;
        }

        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    tableHtml.append("</tbody></table>");
    tableHtml.append("</body></html>");

    // Set the generated HTML as the content of the vehicle panel (e.g., using a JLabel or JEditorPane)
    JTextPane textPane = new JTextPane();
    textPane.setContentType("text/html");
    textPane.setText(tableHtml.toString());
    textPane.setEditable(false); // Make it non-editable
    textPane.setBackground(Color.BLACK);  // Set the background of the text pane to black
    textPane.setForeground(Color.WHITE);  // Set the text color to white

    // Wrap the JTextPane inside a JScrollPane
    JScrollPane scrollPane = new JScrollPane(textPane);
    
    // Create the table panel and add the scrollPane to it
    JPanel tablePanel = new JPanel(new BorderLayout());
    tablePanel.add(scrollPane, BorderLayout.CENTER);
 // Add a title for the vehiclePanel
    JLabel vehiclePanelTitle = new JLabel("Booking / view", SwingConstants.CENTER);
    vehiclePanelTitle.setFont(new Font("Arial", Font.BOLD, 20));
    vehiclePanelTitle.setForeground(Color.WHITE); // Set title text color to white

    // Add the title to the top of the vehiclePanel
    vehiclePanel.add(vehiclePanelTitle, BorderLayout.NORTH);
    // Center the table panel inside the vehicle panel
    vehiclePanel.add(tablePanel, BorderLayout.CENTER);

    // Add the sidebar panel to the dashboard
    JPanel sidebarPanel = createSidebarPanel(role);

    // Create a container for the sidebar and main content
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    splitPane.setLeftComponent(sidebarPanel);
    splitPane.setRightComponent(createMainDashboard(role));
    splitPane.setDividerSize(1); // Minimal divider size
    splitPane.setDividerLocation(200); // Set initial sidebar width

    // Add the split pane to the dashboard panel
    dashboardPanel.add(splitPane, BorderLayout.WEST);
    // Create the "Back to Login" button
    JButton backToLoginButton = new JButton("Back to Login");

    // Add action listener to navigate back to the login page
    backToLoginButton.addActionListener(e -> cardLayout.show(cards, "Login"));

    // Create a panel for the button and add it to the bottom of the dashboard
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Center the button
    buttonPanel.setBackground(Color.BLACK);  // Set background to black for consistency
    buttonPanel.add(backToLoginButton);  // Add the button to the panel

    // Add the button panel to the dashboard (SOUTH region)
    dashboardPanel.add(buttonPanel, BorderLayout.SOUTH);
}

    private JPanel createSidebarPanel(String role) {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS)); // Vertical layout
        sidebarPanel.setBackground(new Color(33, 37, 41)); // Sidebar background color
if(role == "Agency Manager") {        // Add sidebar buttons
    addSidebarButton(sidebarPanel, "Dashboard", "fas fa-tachometer-alt", "agency-dashboard.php");
    addSidebarDropdown(sidebarPanel, "Vehicles", new String[]{
            "Add:agency-add-vehicle.php",
            "View:agency-view-vehicle.php",
            "Manage:agency-manage-vehicle.php"
    });
    addSidebarDropdown(sidebarPanel, "Bookings", new String[]{
            "Add:agency-add-booking.php",
            "View:agency-view-booking.php",
            "Manage:agency-manage-booking.php"
    });
    addSidebarDropdown(sidebarPanel, "Feedbacks", new String[]{
            "View:agency-view-feedback.php",
            "Manage:agency-publish-feedback.php"
    });
    addSidebarButton(sidebarPanel, "Password Resets", "fas fa-key", "admin-pwd-resets.php");
    addSidebarButton(sidebarPanel, "System Logs", "fas fa-history", "admin-view-syslogs.php");

    return sidebarPanel;}
else {
        // Add sidebar buttons
        addSidebarButton(sidebarPanel, "Dashboard", "fas fa-tachometer-alt", "admin-dashboard.php");
        addSidebarDropdown(sidebarPanel, "Agency Manager", new String[]{
                "Add:admin-add-agency-manager.php",
                "View:admin-view-agency-manager.php",
                "Manage:admin-manage-agency-manager.php"
        });
        addSidebarDropdown(sidebarPanel, "Users", new String[]{
                "Add:admin-add-user.php",
                "View:admin-view-user.php",
                "Manage:admin-manage-user.php"
        });

        addSidebarDropdown(sidebarPanel, "Vehicles", new String[]{
                "Add:admin-add-vehicle.php",
                "View:admin-view-vehicle.php",
                "Manage:admin-manage-vehicle.php"
        });
        addSidebarDropdown(sidebarPanel, "Bookings", new String[]{
                "Add:admin-add-booking.php",
                "View:admin-view-booking.php",
                "Manage:admin-manage-booking.php"
        });
        addSidebarDropdown(sidebarPanel, "Feedbacks", new String[]{
                "View:admin-view-feedback.php",
                "Manage:admin-publish-feedback.php"
        });
        addSidebarButton(sidebarPanel, "Password Resets", "fas fa-key", "admin-pwd-resets.php");
        addSidebarButton(sidebarPanel, "System Logs", "fas fa-history", "admin-view-syslogs.php");

        return sidebarPanel;}
    }

    private void addSidebarButton(JPanel sidebarPanel, String text, String icon, String actionCommand) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to the left
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // Set uniform button height
        button.setBackground(new Color(33, 37, 41));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setActionCommand(actionCommand); // Assign action command for later use
        
        // Action listener for Dashboard and Login
        button.addActionListener(e -> {
            if ("admin-dashboard.php".equals(e.getActionCommand())) {
                // Navigate to the dashboard page
                createDashboardPage("Admin"); // Pass the role or other needed info
                cardLayout.show(cards, "Dashboard"); // Switch to Dashboard card
            }  else if ("agency-dashboard.php".equals(e.getActionCommand())) {
                // Navigate to the login page
            	createDashboardPage("Agency Manager"); 
                cardLayout.show(cards, "Dashboard"); // Switch to Login card
            } else if ("admin-login.php".equals(e.getActionCommand())) {
                // Navigate to the login page
                cardLayout.show(cards, "Login"); // Switch to Login card
            } else {
                System.out.println("Navigate to: " + e.getActionCommand());
            }
        });

        sidebarPanel.add(button);
    }


    private void addSidebarDropdown(JPanel sidebarPanel, String title, String[] options) {
        JButton dropdownButton = new JButton(title);
        dropdownButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        dropdownButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        dropdownButton.setBackground(new Color(33, 37, 41));
        dropdownButton.setForeground(Color.WHITE);
        dropdownButton.setFocusPainted(false);
        dropdownButton.setBorderPainted(false);

        JPanel dropdownPanel = new JPanel();
        dropdownPanel.setLayout(new BoxLayout(dropdownPanel, BoxLayout.Y_AXIS));
        dropdownPanel.setBackground(new Color(33, 37, 41));

        for (String option : options) {
            String[] parts = option.split(":");
            String text = parts[0];
            String actionCommand = parts[1];

            JButton itemButton = new JButton(text);
            itemButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            itemButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            itemButton.setBackground(new Color(44, 47, 51));
            itemButton.setForeground(Color.WHITE);
            itemButton.setFocusPainted(false);
            itemButton.setBorderPainted(false);
            itemButton.setActionCommand(actionCommand);

            itemButton.addActionListener(e -> {
            
                if (actionCommand.equals("admin-manage-vehicle.php")) {
                    // Open the Manage Vehicles panel in the current window
                    openManageVehiclesPage();
                } else if (actionCommand.equals("admin-add-vehicle.php")) {
                    openAddVehicleForm();
                } else if (actionCommand.equals("admin-view-vehicle.php")) {
                    displayVehicleTable();
                }  else if (actionCommand.equals("admin-manage-vehicle.php")) {
                    // Open the Manage Vehicles panel in the current window
                    openManageVehiclesPage();
                } else if (actionCommand.equals("admin-add-user.php")) {
                    openAdduserForm();
                }   else if (actionCommand.equals("admin-view-user.php")) {
                    displayuserTable();
                }     else if (actionCommand.equals("admin-add-agency-manager.php")) {
                    openAddmanagerForm();
                }   else if (actionCommand.equals("admin-view-agency-manager.php")) {
                    displaymanagerTable();
                }else if (actionCommand.equals("admin-add-booking.php")) {
                    displaybookingTable();
                }   else if (actionCommand.equals("admin-view-booking.php")) {
                	displaybookingviewTable();
                } else if (actionCommand.equals("admin-manage-booking.php")) {
                	displaybookingmanageTable();
                }else if (actionCommand.equals("agency-add-vehicle.php")) {
                    openAddVehicleForm();
                } else if (actionCommand.equals("agency-view-vehicle.php")) {
                    displayVehicleTable();
                }  else if (actionCommand.equals("agency-manage-vehicle.php")) {
                    // Open the Manage Vehicles panel in the current window
                    openManageVehiclesPage();
                } else if (actionCommand.equals("agency-add-booking.php")) {
                    displaybookingTable();
                }   else if (actionCommand.equals("agency-view-booking.php")) {
                	displaybookingviewTable();
                } else if (actionCommand.equals("agency-manage-booking.php")) {
                	displaybookingmanageTable();
                }else if (actionCommand.equals("agency-manage-vehicle.php")) {
                    // Open the Manage Vehicles panel in the current window
                    openManageVehiclesPage();
                }
                 else {
                    System.out.println("Navigate to: " + actionCommand);
                }
            });

            dropdownPanel.add(itemButton);
        }

        dropdownButton.addActionListener(e -> dropdownPanel.setVisible(!dropdownPanel.isVisible()));
        dropdownPanel.setVisible(false);

        sidebarPanel.add(dropdownButton);
        sidebarPanel.add(dropdownPanel);
    }
  

    private JPanel createManageVehiclesPanel() {
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // Main container panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        // VEHICLE PANEL SETUP
        JPanel vehiclePanel = new JPanel(new BorderLayout());
        vehiclePanel.setBackground(Color.BLACK);

        // TOP PANEL (Title + Search Bar)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.BLACK);

        // Title Panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(Color.BLACK);
        JLabel titleLabel = new JLabel("Admin Dashboard ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // Add title and search panels to the top panel
        topPanel.add(titlePanel);

        // Add top panel to the vehicle panel
        vehiclePanel.add(topPanel, BorderLayout.NORTH);

        // TABLE SECTION
        String[] columnNames = {"#", "Name", "Registration Number", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        try {
            // Fetch data from database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclebookings", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tms_vehicle");

            int rowCount = 1; // For row number
            while (rs.next()) {
                String name = rs.getString("v_name");
                String regNo = rs.getString("v_reg_no");
                String status = rs.getString("v_status");

                // Add each result as a row in the table
                model.addRow(new Object[]{rowCount, name, regNo, status});
                rowCount++;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create JTable and set the model
        JTable vehicleTable = new JTable(model);
        vehicleTable.setFillsViewportHeight(true);

        // Set custom table header background and text color
        vehicleTable.getTableHeader().setBackground(new Color(128, 0, 32)); // Burgundy red
        vehicleTable.getTableHeader().setForeground(Color.BLACK);
        vehicleTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Make the table bigger
        vehicleTable.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase font size
        vehicleTable.setRowHeight(40); // Increase row height

        // Set up the table scroll pane
        JScrollPane tableScrollPane = new JScrollPane(vehicleTable);
        vehiclePanel.add(tableScrollPane, BorderLayout.CENTER);
        vehicleTable.setBackground(Color.BLACK);
        vehicleTable.setForeground(Color.WHITE);
        // BUTTON PANEL (Update, Delete, Back to Login)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.BLACK);

        // Update Button
        JButton updateButton = new JButton("Update Selected");
        updateButton.addActionListener(e -> {
            int selectedRow = vehicleTable.getSelectedRow();
            if (selectedRow != -1) {
                int vehicleId = (int) vehicleTable.getValueAt(selectedRow, 0);
                String name = (String) vehicleTable.getValueAt(selectedRow, 1);
                String regNo = (String) vehicleTable.getValueAt(selectedRow, 2);
                String status = (String) vehicleTable.getValueAt(selectedRow, 3);
                openUpdateVehicleForm(vehicleId, name, regNo, status);
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Please select a row to update.");
            }
        });

        // Delete Button
        JButton deleteButton = new JButton("Delete Selected");
        deleteButton.addActionListener(e -> {
            int selectedRow = vehicleTable.getSelectedRow();
            if (selectedRow != -1) {
                int vehicleId = (int) vehicleTable.getValueAt(selectedRow, 0);
                deleteVehicle(vehicleId);
                refreshVehicleTable(); // Refresh the table after deletion
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Please select a row to delete.");
            }
        });

     // Back to Login Button
        JButton backToLoginButton = new JButton("Back to Login");
        backToLoginButton.addActionListener(e -> {
            // Pass the role to the createRoleLoginPage method
            createRoleLoginPage("Admin"); // Replace "Admin" with the appropriate role if necessary
        });

        // Add buttons to button panel
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backToLoginButton);

        vehiclePanel.add(buttonPanel, BorderLayout.SOUTH);

        // SIDEBAR
        JPanel sidebarPanel = createSidebarPanel(role);

        // SPLIT PANEL (Sidebar + Vehicle Content)
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setRightComponent(vehiclePanel);
        splitPane.setDividerSize(1);
        splitPane.setDividerLocation(200);
     // Add a title for the vehiclePanel
        JLabel vehiclePanelTitle = new JLabel("Vehicle / Manage", SwingConstants.CENTER);
        vehiclePanelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        vehiclePanelTitle.setForeground(Color.WHITE); // Set title text color to white

        // Add the title to the top of the vehiclePanel
        mainPanel.add(vehiclePanelTitle, BorderLayout.NORTH);
        // Add the split pane to the main panel
        mainPanel.add(splitPane, BorderLayout.CENTER);
        vehiclePanel.add(sidebarPanel, BorderLayout.WEST);

        // Add to cards for switching
        cards.add(mainPanel, "ManageVehicles");
        cardLayout.show(cards, "ManageVehicles");

        return mainPanel;
    }
    private void openUpdateVehicleForm(int vehicleId, String name, String regNo, String status) {
        JFrame updateVehicleFrame = new JFrame("Update Vehicle");
        updateVehicleFrame.setSize(500, 400);  // Set the size of the window
        updateVehicleFrame.setLocationRelativeTo(null);  // Center the window

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        // Add form fields pre-filled with the selected row's data
        JTextField vehicleNameField = new JTextField(name, 20);
        JTextField vehicleRegNoField = new JTextField(regNo, 20);
        JTextField vehicleStatusField = new JTextField(status, 20);

        formPanel.add(new JLabel("Vehicle Name:"));
        formPanel.add(vehicleNameField);
        formPanel.add(new JLabel("Vehicle Registration No:"));
        formPanel.add(vehicleRegNoField);
        formPanel.add(new JLabel("Status:"));
        formPanel.add(vehicleStatusField);

        // Submit button
        JButton submitButton = new JButton("Update Vehicle");
        submitButton.addActionListener(e -> {
            // Call the updateVehicle method with the modified details
            try {
                boolean success = updateVehicle(vehicleId, 
                                                vehicleNameField.getText(), 
                                                vehicleRegNoField.getText(),  
                                                vehicleStatusField.getText());
                if (success) {
                    JOptionPane.showMessageDialog(updateVehicleFrame, "Vehicle updated successfully!");
                    updateVehicleFrame.dispose();  // Close the window after success
                    refreshVehicleTable();  // Refresh the table to show updated data
                } else {
                    JOptionPane.showMessageDialog(updateVehicleFrame, "Failed to update vehicle.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(updateVehicleFrame, "Error updating vehicle.");
            }
        });

        formPanel.add(submitButton);
        updateVehicleFrame.add(formPanel);

        updateVehicleFrame.setVisible(true);
    }


    private boolean updateVehicle(int vehicleId, String name, String regNo,  String status) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclebookings", "root", "")) {
            String sql = "UPDATE tms_vehicle SET v_name = ?, v_reg_no = ?, v_status = ? WHERE v_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, regNo);
                stmt.setString(3, status);
                stmt.setInt(4, vehicleId);

                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;  // Return true if the update was successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private void openManageVehiclesPage() {
        // Clear the existing content
        JPanel existingContentPanel = (JPanel) getContentPane();
        existingContentPanel.removeAll(); // Remove all previous components

        // Create and add the panel for managing vehicles
        JPanel manageVehiclesPanel = createManageVehiclesPanel();
        existingContentPanel.add(manageVehiclesPanel, BorderLayout.CENTER);

        // Refresh the panel
        existingContentPanel.revalidate();
        existingContentPanel.repaint();
    }

    private void refreshVehicleTable() {
        Object[][] updatedData = fetchVehicleDataFromDatabase();
        DefaultTableModel model = new DefaultTableModel(updatedData, new String[]{"#", "Name", "Registration Number", "Status"});
        vehicleTable.setModel(model); // Update the table's model with new data
    }


    // Fetch vehicle data from the database
    private Object[][] fetchVehicleDataFromDatabase() {
        Object[][] data = new Object[0][4]; // Default empty data array
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclebookings", "root", "");
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Enable scrolling
             ResultSet rs = stmt.executeQuery("SELECT * FROM tms_vehicle")) {
        	 System.out.println("Database connection successful.");
             // Get row count for the data array
             rs.last();
             int rowCount = rs.getRow();
             rs.beforeFirst(); // Reset the cursor position

             System.out.println("Row count: " + rowCount); // Debugging line

             if (rowCount > 0) {
                 data = new Object[rowCount][4]; // Assuming 4 columns: ID, Name, Registration, Status
                 int cnt = 0;
                 while (rs.next()) {
                     data[cnt][0] = rs.getInt("v_id");
                     data[cnt][1] = rs.getString("v_name");
                     data[cnt][2] = rs.getString("v_reg_no");
                     data[cnt][3] = rs.getString("v_status");
                     cnt++;
                 }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connecting to database.");
        
        }
        return data;
    }

    // Delete a vehicle from the database
    private void deleteVehicle(int vehicleId) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclebookings", "root", "")) {
            String sql = "DELETE FROM tms_vehicle WHERE v_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, vehicleId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Vehicle Deleted");
                } else {
                    System.out.println("Vehicle not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public class VehicleService {

        // Modify this method to accept form data as parameters
        public static boolean addVehicle(String v_name, String v_reg_no, String v_category, String v_pass_no, String v_status ) {
            // Database connection and insertion
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclebookings", "root", "")) {
                String query = "INSERT INTO tms_vehicle (v_name, v_pass_no, v_reg_no,  v_category, v_status) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, v_name);
                    stmt.setString(2, v_pass_no);
                    stmt.setString(3, v_reg_no);
                    stmt.setString(4, v_category);
                    stmt.setString(5, v_status);

                    int result = stmt.executeUpdate();
                    if (result > 0) {
                        System.out.println("Vehicle Added Successfully.");
                        return true; // Vehicle successfully added
                    } else {
                        System.out.println("Please Try Again Later.");
                        return false; // Failed to add vehicle
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Database Error.");
                return false; // Database error occurred
            }
        }
    }

  
    private void openAddVehicleForm() {
        // Create a new frame or window for adding a vehicle
        JFrame addVehicleFrame = new JFrame("Add Vehicle");
        addVehicleFrame.setSize(500, 400);  // Set the size of the window
        addVehicleFrame.setLocationRelativeTo(null);  // Center the window

        // Create a panel with form fields for adding a vehicle
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        // Add form fields
        JTextField vehicleNameField = new JTextField(20);
        JTextField vehicleRegNoField = new JTextField(20);
        JTextField vehicleCategoryField = new JTextField(20);
        JTextField vehiclePassNoField = new JTextField(20);
        JTextField vehicleStatusField = new JTextField(20);
        

        formPanel.add(new JLabel("Vehicle Name:"));
        formPanel.add(vehicleNameField);
        formPanel.add(new JLabel("Vehicle Registration No:"));
        formPanel.add(vehicleRegNoField);
        formPanel.add(new JLabel("Vehicle Category:"));
        formPanel.add(vehicleCategoryField);
        formPanel.add(new JLabel("Passenger Number:"));
        formPanel.add(vehiclePassNoField);
        formPanel.add(new JLabel("Status:"));
        formPanel.add(vehicleStatusField);
       

        // Submit button
        JButton submitButton = new JButton("Add Vehicle");
        submitButton.addActionListener(e -> {
            // Call the addVehicle method from the VehicleService class
            try {
                boolean success = VehicleService.addVehicle(vehicleNameField.getText(), vehicleRegNoField.getText(), 
                                                            vehicleCategoryField.getText(), vehiclePassNoField.getText(), 
                                                            vehicleStatusField.getText());
                if (success) {
                    JOptionPane.showMessageDialog(addVehicleFrame, "Vehicle added successfully!");
                    addVehicleFrame.dispose();  // Close the window after success
                } else {
                    JOptionPane.showMessageDialog(addVehicleFrame, "Failed to add vehicle. Try again later.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(addVehicleFrame, "Error adding vehicle.");
            }
        });

        formPanel.add(submitButton);
        addVehicleFrame.add(formPanel);

        addVehicleFrame.setVisible(true);
    }

    private void openAdduserForm() {
        // Create a new frame or window for adding a vehicle
        JFrame adduserFrame = new JFrame("Add User");
        adduserFrame.setSize(500, 400);  // Set the size of the window
        adduserFrame.setLocationRelativeTo(null);  // Center the window

        // Create a panel with form fields for adding a vehicle
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        // Add form fields
        JTextField useru_fnameField = new JTextField(20);
        JTextField useru_lnameField = new JTextField(20);
        JTextField useru_phoneField = new JTextField(20);
        JTextField useru_addrField = new JTextField(20);
        JTextField useru_emailField = new JTextField(20);
        JTextField useru_pwdField = new JTextField(20);
       
        
        
       
       
        
        formPanel.add(new JLabel(" First Name:"));
        formPanel.add(useru_fnameField);
        formPanel.add(new JLabel("Last Name:"));
        formPanel.add(useru_lnameField);
        formPanel.add(new JLabel("Contact:"));
        formPanel.add(useru_phoneField);
        formPanel.add(new JLabel(" Address:"));
        formPanel.add(useru_addrField);
        formPanel.add(new JLabel(" Email address:"));
        formPanel.add(useru_emailField);
        formPanel.add(new JLabel(" Password:"));
        formPanel.add(useru_pwdField);
     				
        // Submit button
        JButton submitButton = new JButton("Add Vehicle");
        submitButton.addActionListener(e -> {
            // Call the addVehicle method from the VehicleService class
            try {
                boolean success = userService.adduser(useru_fnameField.getText(), useru_lnameField.getText(), 
                                                            useru_phoneField.getText(), useru_addrField.getText(), 
                                                            useru_emailField.getText(), useru_pwdField.getText());
                if (success) {
                    JOptionPane.showMessageDialog(adduserFrame, "Vehicle added successfully!");
                    adduserFrame.dispose();  // Close the window after success
                } else {
                    JOptionPane.showMessageDialog(adduserFrame, "Failed to add vehicle. Try again later.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(adduserFrame, "Error adding vehicle.");
            }
        });

        formPanel.add(submitButton);
        adduserFrame.add(formPanel);

        adduserFrame.setVisible(true);
    }
    public class userService {
        // Modify this method to accept form data as parameters
        public static boolean adduser(String u_fname, String u_lname, String u_phone, String u_email, String u_pwd, String u_addr) {
            // Database connection and insertion
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclebookings", "root", "")) {
                String query = "INSERT INTO tms_user (u_fname, u_lname,	u_phone	,u_addr	, u_email,u_pwd	) VALUES (?, ?, ?, ?, ?,?)";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, u_fname);
                    stmt.setString(2, u_lname);
                    stmt.setString(3, u_email);
                    stmt.setString(4, u_phone);
                    stmt.setString(5, u_pwd);
                    stmt.setString(6, u_addr);

                    int result = stmt.executeUpdate();
                    if (result > 0) {
                        System.out.println("Vehicle Added Successfully.");
                        return true; // Vehicle successfully added
                    } else {
                        System.out.println("Please Try Again Later.");
                        return false; // Failed to add vehicle
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Database Error.");
                return false; // Database error occurred
            }
        }
    }

    private void openAddmanagerForm() {
        // Create a new frame or window for adding a vehicle
        JFrame addmanagerFrame = new JFrame("Add Manager");
        addmanagerFrame.setSize(500, 400);  // Set the size of the window
        addmanagerFrame.setLocationRelativeTo(null);  // Center the window

        // Create a panel with form fields for adding a vehicle
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        // Add form fields
        JTextField managera_fnameField = new JTextField(20);
        JTextField managera_lnameField = new JTextField(20);
        JTextField managera_phoneField = new JTextField(20);
        JTextField managera_addrField = new JTextField(20);
        JTextField managera_emailField = new JTextField(20);
        JTextField managera_pwdField = new JTextField(20);

        formPanel.add(new JLabel(" First Name:"));
        formPanel.add(managera_fnameField);
        formPanel.add(new JLabel("Last Name:"));
        formPanel.add(managera_lnameField);
        formPanel.add(new JLabel("Contact:"));
        formPanel.add(managera_phoneField);
        formPanel.add(new JLabel(" Address:"));
        formPanel.add(managera_addrField);
        formPanel.add(new JLabel(" Email address:"));
        formPanel.add(managera_emailField);
        formPanel.add(new JLabel(" Password:"));
        formPanel.add(managera_pwdField);
     				
        // Submit button
        JButton submitButton = new JButton("Add Vehicle");
        submitButton.addActionListener(e -> {
            // Call the addVehicle method from the VehicleService class
            try {
                boolean success = managerService.addmanager(managera_fnameField.getText(), managera_lnameField.getText(), 
                                                            managera_phoneField.getText(), managera_addrField.getText(), 
                                                            managera_emailField.getText(), managera_pwdField.getText());
                if (success) {
                    JOptionPane.showMessageDialog(addmanagerFrame, "Vehicle added successfully!");
                    addmanagerFrame.dispose();  // Close the window after success
                } else {
                    JOptionPane.showMessageDialog(addmanagerFrame, "Failed to add vehicle. Try again later.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(addmanagerFrame, "Error adding vehicle.");
            }
        });

        formPanel.add(submitButton);
        addmanagerFrame.add(formPanel);

        addmanagerFrame.setVisible(true);
    }

    public class managerService {
        // Modify this method to accept form data as parameters
        public static boolean addmanager(String a_fname, String a_lname, String a_phone, String a_email, String a_pwd, String a_addr) {
            // Database connection and insertion
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclebookings", "root", "")) {
                String query = "INSERT INTO tms_agency_manager (a_fname, a_lname,	a_phone	,a_addr	, a_email,a_pwd	) VALUES (?, ?, ?, ?, ?,?)";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, a_fname);
                    stmt.setString(2, a_lname);
                    stmt.setString(3, a_email);
                    stmt.setString(4, a_phone);
                    stmt.setString(5, a_pwd);
                    stmt.setString(6, a_addr);

                    int result = stmt.executeUpdate();
                    if (result > 0) {
                        System.out.println("Vehicle Added Successfully.");
                        return true; // Vehicle successfully added
                    } else {
                        System.out.println("Please Try Again Later.");
                        return false; // Failed to add vehicle
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Database Error.");
                return false; // Database error occurred
            }
        }
    }


    private JPanel createMainDashboard(String role) {
        // Your existing dashboard creation logic
        return new JPanel(); // Placeholder for the main content
    }

    private void updateTable(String query) {
        // Rebuild the table based on the search query
        String htmlTable = getHTMLTableData(query);
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(htmlTable);
        textPane.setEditable(false); // Make it non-editable
        textPane.setBackground(Color.BLACK);  // Set the background of the text pane to black
        textPane.setForeground(Color.WHITE);  // Set the text color to white

        // Use the stored reference to tablePanel
        JScrollPane scrollPane = new JScrollPane(textPane);
        tablePanel.removeAll();  // Remove the previous table
        tablePanel.add(scrollPane, BorderLayout.CENTER);  // Add the new table
        tablePanel.revalidate();  // Revalidate the panel
        tablePanel.repaint();  // Repaint the panel
    }


    private String getHTMLTableData(String query) {
        StringBuilder tableHtml = new StringBuilder();

        // Add some CSS styling to the table
        tableHtml.append("<style>")
                 .append("table {")
                 .append("  width: 100%;")
                 .append("  border-collapse: collapse;")
                 .append("  font-family: Arial, sans-serif;")
                 .append("  margin: 20px 0;")
                 .append("  background-color: #f4f4f4;")
                 .append("}")
                 .append("th, td {")
                 .append("  padding: 10px;")
                 .append("  text-align: left;")
                 .append("  border: 1px solid #ddd;")
                 .append("}")
                 .append("th {")
                 .append("  background-color: #800020;") // Burgundy red
                 .append("  color: white;")
                 .append("}")
                 .append("tr:nth-child(even) {")
                 .append("  background-color: #f2f2f2;")
                 .append("}")
                 .append("tr:hover {")
                 .append("  background-color: #ddd;")
                 .append("}")
                 .append("</style>");

        // Create table header
        tableHtml.append("<table class='table table-bordered table-striped table-hover' id='dataTable' cellspacing='0'>")
                 .append("<thead><tr>")
                 .append("<th>#</th>")
                 .append("<th>Name</th>")
                 .append("<th>Phone</th>")
                 .append("<th>Vehicle Type</th>")
                 .append("<th>Reg No</th>")
                 .append("<th>Booking date</th>")
                 .append("<th>Status</th>")
                 .append("</tr></thead><tbody>");

        try {
            // Database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclebookings", "root", "");

            // Query to fetch bookings, apply filter if any
            String queryString = "SELECT * FROM tms_user WHERE u_car_book_status IN ('Approved', 'Pending')";
            if (query != null && !query.trim().isEmpty()) {
                queryString += " AND (u_fname LIKE ? OR u_lname LIKE ? OR u_phone LIKE ? OR u_car_type LIKE ? OR u_car_regno LIKE ? OR u_car_book_status LIKE ?OR u_car_bookdate LIKE ?)";
            }

            PreparedStatement stmt = conn.prepareStatement(queryString);

            // Set parameters for the search query
            if (query != null && !query.trim().isEmpty()) {
                String searchQuery = "%" + query + "%";
                stmt.setString(1, searchQuery);
                stmt.setString(2, searchQuery);
                stmt.setString(3, searchQuery);
                stmt.setString(4, searchQuery);
                stmt.setString(5, searchQuery);
                stmt.setString(6, searchQuery);
                stmt.setString(7, searchQuery);
            }

            ResultSet rs = stmt.executeQuery();

            int cnt = 1;
            while (rs.next()) {
                String status = rs.getString("u_car_book_status").equals("Pending") ? 
                                "<span class='badge badge-warning'>" + rs.getString("u_car_book_status") + "</span>" :
                                "<span class='badge badge-success'>" + rs.getString("u_car_book_status") + "</span>";

                tableHtml.append("<tr>")
                         .append("<td>").append(cnt).append("</td>")
                         .append("<td>").append(rs.getString("u_fname")).append(" ").append(rs.getString("u_lname")).append("</td>")
                         .append("<td>").append(rs.getString("u_phone")).append("</td>")
                         .append("<td>").append(rs.getString("u_car_type")).append("</td>")
                         .append("<td>").append(rs.getString("u_car_regno")).append("</td>")
                         .append("<td>").append(rs.getString("u_car_bookdate")).append("</td>")
                         .append("<td>").append(status).append("</td>")
                         .append("</tr>");
                cnt++;
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Close table
        tableHtml.append("</tbody></table>");

        return tableHtml.toString();
    }
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VehicleBookingSystem frame = new VehicleBookingSystem();
            frame.setVisible(true);
        });
    }
}
