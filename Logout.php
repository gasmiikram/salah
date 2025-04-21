<?php

class Logout {
    public function __construct() {
        session_start(); // Start the session
    }

    public function destroySession() {
        session_destroy(); // Destroy the session
    }

    public function redirectTo($location) {
        header("Location: " . $location);
        exit(); // Ensure the script stops after redirection
    }
}

// Usage
$sessionManager = new Logout();
$sessionManager->destroySession();
$sessionManager->redirectTo("Index.php");

?>
