// Call the dataTables jQuery plugin
$(document).ready(function() {
   //ready
   });
   

    async function RegistrarUsuario(){
        let datos = {};
        datos.nombre = document.getElementById('inputNombre').value;
        datos.apellido = document.getElementById('inputApellidos').value;
        datos.email = document.getElementById('inputEmail').value;
        datos.telefono = document.getElementById('inputTelefono').value;
        datos.password = document.getElementById('inputPassword').value;
        
        let repitirPassword = document.getElementById('repeatPassword').value;
        if (repitirPassword != datos.password){
            alert("Las contraseñas no son iguales");
            return;
        }
        
        const request = await fetch('api/usuarios', {
           method: 'POST',
           headers: {
             'Accept': 'application/json',
             'Content-Type': 'application/json'
           },
           body: JSON.stringify(datos)
         });
         alert("La cuenta fue creada correctamente !!!");
         window.location.href='login.html';
    }
