//for the side bar movement
function show() {
    document.getElementById('side-bar').classList.toggle('active');
    document.getElementById('main1').classList.toggle('active');
    const sidebar = document.getElementById('person');
    sidebar.classList.toggle('hidden');  // Toggle the 'hidden' class
  }
  //for the top section of the main and the side bar
  const activePage = window.location.pathname;
  document.querySelectorAll('nav a').forEach(link => {
    if (link.href.includes(`${activePage}`)) { 
        link.classList.add('active');
    }
  });
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
  
  
         
         
       // remove the row of the documments in the administration   
  function removeRow(matricule, documentName) {
      const row = document.querySelector(`tr[data-matricule='${matricule}'][data-document-name='${documentName}']`);
      if (row) {
          row.remove(); // Remove the specific row that matches both matricule and document name
      }
  }
  
  // to send email if the documment eccepted or not
  function sendEmail(matricule, action, documentName) {
      fetch('send_email_ad.php', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json',
          },
          body: JSON.stringify({ matricule: matricule, action: action, documentName: documentName }),
      })
      .then(response => response.json())
      .then(data => {
          console.log(data.message); // Optionally log the response from the server
      })
      .catch(error => {
          console.error('Error:', error);
      });
  }
  // to remove the row after we accepted it and update the database  
  function acceptDocument(matricule, documentName) {
    if (confirm("Are you sure you want to accept the document for Matricule: " + matricule + "?")) {
        sendEmail(matricule, 'accept', documentName);  // Update the database
        removeRow(matricule, documentName);  // Remove the specific row from the table
        alert('Document for Matricule ' + matricule + ' has been accepted.');
    }
}
// to remove the row after we reffused it and update the database
function refuseDocument(matricule, documentName) {
    if (confirm("Are you sure you want to refuse the document for Matricule: " + matricule + "?")) {
        sendEmail(matricule, 'refuse', documentName);  // Update the database
        removeRow(matricule, documentName);  // Remove the specific row from the table
        alert('Document for Matricule ' + matricule + ' has been refused.');
    }
}
  
  
  // to serche in the administration tabel
  function searchFunction() {
      var input = document.getElementById("search").value.toLowerCase();
      var rows = document.getElementById("table-body").getElementsByTagName("tr");
  
      for (var i = 0; i < rows.length; i++) {
          var cells = rows[i].getElementsByTagName("td");
          var showRow = false;
  
          // Loop through the columns and check if any cell matches the search term
          for (var j = 0; j < cells.length; j++) {
              var cellText = cells[j].textContent || cells[j].innerText;
              if (cellText.toLowerCase().indexOf(input) > -1) {
                  showRow = true;
                  break;
              }
          }
  
          if (showRow) {
              rows[i].style.display = "";
          } else {
              rows[i].style.display = "none";
          }
      }
  }



// to confirm that you want to remove the documment 
function confirmRemoval(documentId, buttonElement) {
    // Show confirmation dialog
    if (confirm("Are you sure you want to remove this document? This action cannot be undone.")) {
        // If user confirms, call removeDocument
        removeDocument(documentId, buttonElement);
    }
}
// to remove the document in the utudient side 
function removeDocument(documentId, buttonElement) {
    // Send AJAX request to remove the document from the database
    fetch('remove_document.php', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id: documentId })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            // Remove the card element from the page
            const card = buttonElement.closest('.col-sm-6');
            if (card) card.remove();
        } else {
            alert("Failed to remove document from the database.");
        }
    })
    .catch(error => console.error('Error:', error));
}



  
     
    
          
          // Gestion de la soumission du formulaire
          document.getElementById('noteForm').addEventListener('submit', function(event) {
              event.preventDefault();
  
              // Récupération des éléments du formulaire
              const moduleName = document.getElementById('moduleName').value;
              const teacherName = document.getElementById('teacherName').value;
              const wrongNote = document.getElementById('wrongNote').value;
              const correctNote = document.getElementById('correctNote').value;
  
              // Validation de base
              if (wrongNote < 0 || wrongNote > 20 || correctNote < 0 || correctNote > 20) {
                  alert('Veuillez entrer des notes entre 0 et 20.');
                  return;
              }
  
              // Affichage des données (à titre d'exemple)
              alert(`
                  Module : ${moduleName}
                  Professeur : ${teacherName}
                  Note Erronée : ${wrongNote}
                  Note Correcte : ${correctNote}
              `);
  
              // Optionnellement, envoyer les données à un serveur (par exemple, en utilisant AJAX ou une soumission de formulaire)
              // Exemple : fetch('/submit', { method: 'POST', body: formData });
          });
  
          // Validation de formulaire Bootstrap
          (function () {
              'use strict'
              var forms = document.querySelectorAll('.needs-validation')
              Array.prototype.slice.call(forms)
                  .forEach(function (form) {
                      form.addEventListener('submit', function (event) {
                          if (!form.checkValidity()) {
                              event.preventDefault()
                              event.stopPropagation()
                          }
                          form.classList.add('was-validated')
                      }, false)
                  })
          })()
       
             // to confirm that you want to remove the documment 
function confirmRemov(documentId, buttonElement) {
    // Show confirmation dialog
    if (confirm("Are you sure you want to remove this recour? This action cannot be undone.")) {
        // If user confirms, call removeDocument
        deleteRow(documentId, buttonElement);
    }
}
    function deleteRow(button, recourId) {
    // Remove the row from the page
    const row = button.closest('.col-md-4');
    row.remove();

    // Make an Ajax call to delete the row from the database
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "remove_recour.php", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Optionally, handle any response from the server
            console.log(xhr.responseText);
        }
    };

    // Send the recour ID to delete from the database
    xhr.send("recourId=" + recourId);
}

          
    // Function to remove the row based on the ID
function removeRowById(id) {
    const row = document.querySelector(`tr[data-id='${id}']`);
    if (row) {
        row.remove(); // Remove the specific row
    }
}

// Function to send an email when the document is accepted or refused
function sendEmailForAction(id, action) {
    fetch('update_recour_status.php', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ id: id, action: action }),
    })
    .then(response => response.json())
    .then(data => {
        console.log(data.message); // Log the server response (email sent confirmation)
    })
    .catch(error => {
        console.error('Error:', error); // Log any errors during the request
    });
}

function acceptDocumentById(id) {
    console.log("Accepting document with ID: ", id); // Debugging
    if (confirm("Are you sure you want to accept the document with ID: " + id + "?")) {
        sendEmailForAction(id, 'accept');
        removeRowById(id);
        alert('Document with ID ' + id + ' has been accepted.');
    }
}

function refuseDocumentById(id) {
    console.log("Refusing document with ID: ", id); // Debugging
    if (confirm("Are you sure you want to refuse the document with ID: " + id + "?")) {
        sendEmailForAction(id, 'refuse');
        removeRowById(id);
        alert('Document with ID ' + id + ' has been refused.');
    }
}
      


      
     