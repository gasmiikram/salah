<?php
// Start the session to access session variables
include("Connect.php"); // Ensure this file sets up your database connection

class Teste {
    public $conn;

    public function __construct($host, $user, $password, $database) {
        $this->connect($host, $user, $password, $database);
    }

    private function connect($host, $user, $password, $database) {
        $this->conn = new mysqli($host, $user, $password, $database);

        // Check connection
        if ($this->conn->connect_error) {
            die("Connection error: " . $this->conn->connect_error);
        }
    }


}




?>
