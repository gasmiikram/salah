<?php
include 'Connect.php';

class Register {
    private $conn;

    public function __construct($dbConnection) {
        $this->conn = $dbConnection;
    }

    // Register a new user
    public function register($matricule, $firstName, $lastName, $email, $password) {
        try {
            // Check if email already exists
            if ($this->checkIfEmailExists($email)) {
                return "Email Address Already Exists!";
            }
            // Check if matricule already exists
            if ($this->checkIfMatriculeExists($matricule)) {
                return "Invalid Matricule";
            }

            // Encrypt password
            $passwordHash = md5($password);

            // Prepare the SQL statement to insert a new user
            $insertQuery = $this->conn->prepare("INSERT INTO users (Matricule, firstName, lastName, email, password) VALUES (?, ?, ?, ?, ?)");
            $insertQuery->bind_param("sssss", $matricule, $firstName, $lastName, $email, $passwordHash);

            if ($insertQuery->execute()) {
                // Store matricule in session for later use
                session_start(); // Ensure session is started
                $_SESSION['matricule'] = $matricule; // Save matricule in session

                header("Location: index.php");
                exit();
            } else {
                throw new Exception("Error during user registration: " . $this->conn->error);
            }
        } catch (Exception $e) {
            return "An error occurred: " . $e->getMessage();
        }
    }

    // Login a user
    public function login($email, $password) {
        $passwordHash = md5($password);

        $sql = $this->conn->prepare("SELECT * FROM users WHERE email = ? AND password = ?");
        $sql->bind_param("ss", $email, $passwordHash);
        $sql->execute();
        $result = $sql->get_result();

        if ($result->num_rows > 0) {
            session_start();
            $row = $result->fetch_assoc();
            $_SESSION['email'] = $row['email'];
            $_SESSION['matricule'] = $row['Matricule']; // Save matricule in session

            header("Location: Main.php");
            exit();
        } else {
            return "Not Found, Incorrect Email or Password";
        }
    }

    // Check if email already exists in the database
    private function checkIfEmailExists($email) {
        $checkEmail = $this->conn->prepare("SELECT * FROM users WHERE email = ?");
        $checkEmail->bind_param("s", $email);
        $checkEmail->execute();
        $result = $checkEmail->get_result();
        return $result->num_rows > 0;
    }

    // Check if matricule already exists in the database
    private function checkIfMatriculeExists($matricule) {
        $checkMat = $this->conn->prepare("SELECT * FROM users WHERE Matricule = ?");
        $checkMat->bind_param("s", $matricule);
        $checkMat->execute();
        $result = $checkMat->get_result();
        return $result->num_rows > 0;
    }
}

// Usage Example
$db = new Connect();
$conn = $db->getConnection();
$user = new Register($conn);

if (isset($_POST['signUp'])) {
    $response = $user->register($_POST['matricule'], $_POST['fName'], $_POST['lName'], $_POST['email'], $_POST['password']);
    if ($response) {
        echo $response;
    }
}

if (isset($_POST['signIn'])) {
    $response = $user->login($_POST['email'], $_POST['password']);
    if ($response) {
        echo $response;
    }
}
?>
