
   
<!DOCTYPE html>
<html lang="en" class="!scroll-smooth">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register & Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="styl.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="home.css" />
    <link rel="stylesheet" href="maincss.css"/>
    <link rel="icon" href="\php\img\3.png" />
</head>

<body  class="body-home text-white  p-5"> 
<style>
     .container {
    width: 90%; /* Default width to take most of the screen on small devices */
    max-width: 350px; /* Maximum width to prevent it from becoming too large */
    padding:1.5rem; 
    margin-top: 60px;
    border-radius:10px; 
    box-shadow:0 20px 35px rgba(0,0,1,0.9); 
    box-sizing: border-box; /* Includes padding in the total width/height */
}

.btn{ 
  font-size:1.1rem;  
  border-radius:5px; 
  outline:none; 
  border:none; 
  width:100%; 
  background:rgb(133, 162, 255);
  color:white; 
  cursor:pointer; 
  transition:0.9s; 
} 
.btn:hover{ 
  background:#07001f; 
   color:white; 
} 
.container img {
    display: block; /* Makes the image block-level */
    margin-left: auto;  /* Auto margin on the left */
    margin-right: auto; /* Auto margin on the right */
    width: 100px;  /* Adjust as necessary */
}

</style>
   <!-- Header -->
   <section class="pt-2 text-white text-center fade-in card border rounded-5 p-3 shadow box-area border-white bg-transparent sticky-top" id="first">
            <nav class="navbar navbar-expand-lg w-100">
                
                <!-- Centered logo, title, and links -->
                <div class="d-flex flex-column w-100 align-items-center">
                    <div class="d-flex align-items-center justify-content-center gap-3">
                        <img src="img/3.png" style="width: 60px; height: auto;" />
                        <h4 class="m-0">University of Boumerdes Exam Platform</h4>
                    </div>
                </div>
            </nav>
        </section>
    <div class="black-fill justify-content-center custom-bg p-5 ">
    <div class="container p-5 text-center mt-5  border rounded-5 p-3 bg-transparent shadow box-area" id="signup" style="display:none;">
    <img src="\php\img\3.png" alt="logo"  >
        <h1 class="form-title">Register</h1>
        <form method="post" action="Register.php">
            <div class="input-group">
                <i class="fas fa-user"></i>
                <input type="text" name="fName" id="fName" placeholder="First Name" required>
                <label for="fName">First Name</label>
            </div>
            <div class="input-group">
                <i class="fas fa-user"></i>
                <input type="text" name="lName" id="lName" placeholder="Last Name" required>
                <label for="lName">Last Name</label>
            </div>
            <div class="input-group">
                <i class="fas fa-envelope"></i>
                <input type="email" name="email" id="email" placeholder="Email" required>
                <label for="email">Email</label>
            </div>
            <div class="input-group">
                <i class="fas fa-lock"></i>
                <input type="password" name="password" id="registerPassword" placeholder="Password" required>
                <label for="registerPassword">Password</label>
                <img src="https://cdn-icons-png.flaticon.com/128/10812/10812267.png" alt="Toggle Password" id="eyeicon-password" style="width: 20px; cursor: pointer;">
            </div>
            <div class="input-group">
                <i class="fas fa-key"></i>
                <input type="password" name="matricule" id="matricule" placeholder="matricule" required>
                <label for="matricule">Matricule</label>
                <img src="https://cdn-icons-png.flaticon.com/128/10812/10812267.png" alt="Toggle Code" id="eyeicon-matricule" style="width: 20px; cursor: pointer;">
            </div>
            <input type="submit" class="btn" value="Sign Up" name="signUp">
        </form>
        
        <div class="links">
            <p>Already Have Account?</p>
            <button id="signInButton">Sign In</button>
        </div>
    </div>

    <div class="container p-5 border rounded-5  bg-transparent shadow box-area" id="signIn">
    <img src="\php\img\3.png" alt="logo" >
        <h1 class="form-title">Sign In</h1>
        <form method="post" action="Register.php">
            <div class="input-group">
                <i class="fas fa-envelope"></i>
                <input type="email" name="email" id="loginEmail" placeholder="Email" required>
                <label for="loginEmail">Email</label>
            </div>
            <div class="input-group">
                <i class="fas fa-lock"></i>
                <input  type="password" name="password" id="loginPassword" placeholder="Password" required>
                <label for="loginPassword">Password</label>
                <img src="https://cdn-icons-png.flaticon.com/128/10812/10812267.png" alt="Toggle Password" id="eyeicon-login" style="width: 20px; cursor: pointer;">
            </div>
            <p class="recover">
                <a href="#">Recover Password</a>
            </p>
            <input type="submit" class="btn" value="Sign In" name="signIn">
        </form>
        <div class="links">
            <p>Don't have an account yet?</p>
            <button id="signUpButton">Sign Up</button>
        </div>
    </div>
</div>
    <script defer src="Etudjs.js"></script> 
    
</body>
</html>
