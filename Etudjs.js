
  // reveal items whene we scroll down
  window.addEventListener('scroll', reveal);
  
  function reveal() {
    const reveals = document.querySelectorAll('.reveal');
    for (let i = 0; i < reveals.length; i++) {
        const windowHeight = window.innerHeight;
        const revealTop = reveals[i].getBoundingClientRect().top;
        const revealPoint = 150;
  
        if (revealTop < windowHeight - revealPoint) {
            reveals[i].classList.add('active');
        } else {
            reveals[i].classList.remove('active');
        }
    }
  }
  // reveal items whene refreching the page
  document.addEventListener("DOMContentLoaded", function () {
    const fadeInElements = document.querySelectorAll('.fade-in');
    fadeInElements.forEach((el, index) => {
        el.style.animationDelay = `${index * 0.3}s`; // Stagger delay for each item
    });
  });
    
  
          // Toggle between Sign In and Register forms
          document.getElementById("signUpButton").onclick = function() {
              document.getElementById("signIn").style.display = "none";
              document.getElementById("signup").style.display = "block";
          };
          document.getElementById("signInButton").onclick = function() {
              document.getElementById("signup").style.display = "none";
              document.getElementById("signIn").style.display = "block";
          };
  
  //for the password icon to show the password
      window.onload = function() {
          function togglePasswordVisibility(passwordField, icon) {
              if (passwordField.type === "password") {
                  passwordField.type = "text";
                  icon.src = "https://cdn-icons-png.flaticon.com/128/159/159604.png"; // Eye open
              } else {
                  passwordField.type = "password";
                  icon.src = "https://cdn-icons-png.flaticon.com/128/10812/10812267.png"; // Eye closed
              }
          }
  
          document.getElementById("eyeicon-password").onclick = function() {
              togglePasswordVisibility(document.getElementById("registerPassword"), this);
          };
          const matriculeIcon = document.getElementById("eyeicon-matricule");
          const matriculeField = document.getElementById("matricule");
          
          if (matriculeIcon && matriculeField) {
              matriculeIcon.onclick = function() {
                  togglePasswordVisibility(matriculeField, matriculeIcon);
              };
          }
          document.getElementById("eyeicon-login").onclick = function() {
              togglePasswordVisibility(document.getElementById("loginPassword"), this);
          };
          document.getElementById("eyeicon-code").onclick = function() {
              togglePasswordVisibility(document.getElementById("code"), this);
          };
      };
    

      
     