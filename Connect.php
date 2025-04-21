<?php

class Connect {
    private $host = "localhost";
    private $user = "root";
    private $pass = "";
    private $db = "school_db";
    private $conn;

    // Constructor to automatically establish a database connection
    public function __construct() {
        $this->connect();
    }

    // Connects to the database
    private function connect() {
        $this->conn = new mysqli($this->host, $this->user, $this->pass, $this->db);

        // Check for connection error
        if ($this->conn->connect_error) {
            die("Failed to connect to DB: " . $this->conn->connect_error);
        }
    }

    // Getter for the database connection
    public function getConnection() {
        return $this->conn;
    }

    // Destructor to close the connection when the object is destroyed
    public function __destruct() {
        if ($this->conn) {
            $this->conn->close();
        }
    }
}

// Usage
$db = new Connect();
$conn = $db->getConnection();
?>

