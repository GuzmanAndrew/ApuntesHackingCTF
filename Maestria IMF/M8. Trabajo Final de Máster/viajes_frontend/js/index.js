document.addEventListener('DOMContentLoaded', function () {

   document.getElementById('showRegisterForm').addEventListener('click', function () {
      document.getElementById('login-form').style.display = 'none';
      document.getElementById('register-form').style.display = 'block';
   });

   document.getElementById('showLoginForm').addEventListener('click', function () {
      document.getElementById('register-form').style.display = 'none';
      document.getElementById('login-form').style.display = 'block';
   });

   document.getElementById('loginForm').addEventListener('submit', function (e) {
      e.preventDefault();
      const username = document.getElementById('username').value;
      const password = document.getElementById('password').value;

      fetch('http://localhost:8081/authenticate', {
         method: 'POST',
         headers: {
            'Content-Type': 'application/json',
         },
         body: JSON.stringify({ username, password })
      })
         .then(response => {
            if (!response.ok) {
               throw new Error(`Error en la autenticación: ${response.status}`);
            }
            return response.json();
         })
         .then(data => {
            localStorage.setItem('jwt', data.jwt);
            window.location.href = 'home.html';
         })
         .catch((error) => {
            console.error('Error:', error);
            alert('Fallo en el login. Verifica tus credenciales e inténtalo de nuevo.');
         });
   });

   document.getElementById('registerForm').addEventListener('submit', function (e) {
      e.preventDefault();

      const usuario = document.getElementById('registerUsuario').value;
      const contrasena = document.getElementById('registerContrasena').value;
      const nombres = document.getElementById('registerNombres').value;
      const apellidos = document.getElementById('registerApellidos').value;
      const correo = document.getElementById('registerCorreo').value;
      const telefono = document.getElementById('registerTelefono').value;

      fetch('http://localhost:8082/usuarios/create', {
         method: 'POST',
         headers: {
            'Content-Type': 'application/json',
         },
         body: JSON.stringify({ usuario, contrasena, nombres, apellidos, correo, telefono })
      })
         .then(response => {
            if (!response.ok) {
               throw new Error('Error en la creación de la cuenta');
            }
            return response.json();
         })
         .then(data => {

            alert('Registro exitoso!');

            document.getElementById('registerForm').reset();
            document.getElementById('register-form').style.display = 'none';
            document.getElementById('login-form').style.display = 'block';
         })
         .catch((error) => {
            console.error('Error:', error);
            alert('Hubo un problema con el registro. Inténtalo de nuevo.');
         });
   });
});
