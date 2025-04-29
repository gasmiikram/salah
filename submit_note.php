
<?php
// Start session
session_start();

// Check if matricule (student identifier) exists in session
if (!isset($_SESSION['matricule'])) {
    header('Content-Type: application/json');
    echo json_encode(['success' => false, 'message' => "You must be logged in."]);
    exit;
}

$matricule = $_SESSION['matricule'];

// DB connection setup
try {
    // Establish PDO connection
    $pdo = new PDO("mysql:host=localhost;dbname=school_db", "root", "");
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Check if the form was submitted
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        // Retrieve form data (exam name and grade)
        $exam_name = $_POST['exam_name'] ?? '';
        $grade = $_POST['grade'] ?? 0;
        
        // Validate and sanitize grade input (must be between 0 and 100)
        $grade = filter_var($grade, FILTER_VALIDATE_FLOAT, [
            'options' => [
                'default' => 0,
                'min_range' => 0,
                'max_range' => 100
            ]
        ]);
        
        // If grade is invalid, return an error
        if ($grade === false) {
            header('Content-Type: application/json');
            echo json_encode(['success' => false, 'message' => 'Invalid grade input.']);
            exit;
        }

        // Check if the student has already submitted the exam
        $stmt = $pdo->prepare("SELECT 1 FROM notes WHERE Matricule = ? AND exam_name = ?");
        $stmt->execute([$matricule, $exam_name]);
        
        if ($stmt->fetchColumn()) {
            header('Content-Type: application/json');
            echo json_encode(['success' => false, 'message' => "You have already taken this exam."]);
            exit;
        }

        // Insert the new grade for the exam
        $stmt = $pdo->prepare("INSERT INTO notes (matricule, exam_name, grade, submitted_at) 
                             VALUES (?, ?, ?, NOW())");
        $success = $stmt->execute([$matricule, $exam_name, $grade]);

        // Return the success or failure response
        header('Content-Type: application/json');
        echo json_encode([
            'success' => $success,
            'message' => $success ? "Exam submitted successfully!" : "Failed to save exam results",
            'grade' => $grade
        ]);
    }
} catch (PDOException $e) {
    // Handle database connection errors
    header('Content-Type: application/json');
    echo json_encode([
        'success' => false,
        'message' => "Database error: " . $e->getMessage()
    ]);
}
?>
