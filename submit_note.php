
<?php
/*
session_start();

if (!isset($_SESSION['matricule'])) {
    echo json_encode(['success' => false, 'message' => "You must be logged in."]);
    exit;
}

$matricule = $_SESSION['matricule'];

// DB connection
try {
    $pdo = new PDO("mysql:host=localhost;dbname=school_db", "root", "");
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        // Get and validate inputs
        $exam_name = $_POST['exam_name'] ?? null;
        $grade = $_POST['grade'] ?? 0; // Default to 0 if not provided

        // Ensure grade is numeric and between 0-100
        $grade = is_numeric($grade) ? max(0, min(100, floatval($grade))) : 0;
        
        // Check if the student already has a grade for this exam
        $stmt = $pdo->prepare("SELECT * FROM notes WHERE matricule = ? AND exam_name = ?");
        $stmt->execute([$matricule, $exam_name]);
        
        if ($stmt->rowCount() > 0) {
            echo json_encode(['success' => false, 'message' => "You have already taken this exam."]);
            exit;
        }

        // Insert the grade into notes table (even if 0)
        $stmt = $pdo->prepare("INSERT INTO notes (matricule, exam_name, grade, submitted_at, submission_type) 
                             VALUES (?, ?, ?, NOW(), ?)");
        $stmt->execute([
            $matricule, 
            $exam_name, 
            $grade,
            ($_POST['auto_submit'] ?? false) ? 'auto' : 'manual'
        ]);

        // Return success response
        echo json_encode([
            'success' => true,
            'message' => "Exam results saved successfully!",
            'grade' => $grade
        ]);
    }
} catch (PDOException $e) {
    echo json_encode([
        'success' => false,
        'message' => "Database error: " . $e->getMessage()
    ]);
} catch (Exception $e) {
    echo json_encode([
        'success' => false,
        'message' => $e->getMessage()
    ]);
}
    */
?>



<?php
session_start();

if (!isset($_SESSION['matricule'])) {
    header('Content-Type: application/json');
    echo json_encode(['success' => false, 'message' => "You must be logged in."]);
    exit;
}

$matricule = $_SESSION['matricule'];

// DB connection
try {
    $pdo = new PDO("mysql:host=localhost;dbname=school_db", "root", "");
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $exam_name = $_POST['exam_name'] ?? '';
        $grade = $_POST['grade'] ?? 0;
        
        // Validate and sanitize inputs
        $grade = filter_var($grade, FILTER_VALIDATE_FLOAT, [
            'options' => [
                'default' => 0,
                'min_range' => 0,
                'max_range' => 100
            ]
        ]);

        // Check for existing submission
        $stmt = $pdo->prepare("SELECT 1 FROM notes WHERE matricule = ? AND exam_name = ?");
        $stmt->execute([$matricule, $exam_name]);
        
        if ($stmt->fetchColumn()) {
            header('Content-Type: application/json');
            echo json_encode(['success' => false, 'message' => "You have already taken this exam."]);
            exit;
        }

        // Insert new grade
        $stmt = $pdo->prepare("INSERT INTO notes (matricule, exam_name, grade, submitted_at) 
                             VALUES (?, ?, ?, NOW())");
        $success = $stmt->execute([$matricule, $exam_name, $grade]);

        header('Content-Type: application/json');
        echo json_encode([
            'success' => $success,
            'message' => $success ? "Exam submitted successfully!" : "Failed to save exam results",
            'grade' => $grade
        ]);
    }
} catch (PDOException $e) {
    header('Content-Type: application/json');
    echo json_encode([
        'success' => false,
        'message' => "Database error: " . $e->getMessage()
    ]);
}
?>