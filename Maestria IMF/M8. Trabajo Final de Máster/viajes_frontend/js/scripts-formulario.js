document.addEventListener('DOMContentLoaded', function () {
   const nextBtn = document.getElementById('nextBtn');
   const step1 = document.getElementById('step1');
   const reservaFormContainer = document.getElementById('reservaFormContainer');

   function populateHoteles() {

      const token = localStorage.getItem('jwt');

      if (!token) {
         alert('Debes iniciar sesión para obtener las reservas.');
         return;
      }

      fetch('http://localhost:8083/hoteles/all', {
         method: 'GET',
         headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
         }
      })
         .then(response => {
            if (!response.ok) {
               throw new Error('Error en la respuesta al obtener reservas');
            }
            return response.json();
         })
         .then(data => {
            const hotelSelect = document.getElementById('hotel');
            hotelSelect.innerHTML = '<option value="">Selecciona una opción</option>';
            data.forEach(hotel => {
               const option = document.createElement('option');
               option.value = hotel.idHotel;
               /*option.textContent = `Hotel: ${hotel.nombre}, Categoría: ${hotel.categoria}`;*/
               option.textContent = `Hotel: ${hotel.nombre}`;
               hotelSelect.appendChild(option);
            });
         })
         .catch(error => console.error('Error al obtener hoteles:', error));
   }

   function populateVuelos(personas) {

      const token = localStorage.getItem('jwt');

      if (!token) {
         alert('Debes iniciar sesión para obtener las reservas.');
         return;
      }

      fetch(`http://localhost:8085/vuelos/all/${personas}`, {
         method: 'GET',
         headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
         }
      })
         .then(response => {
            if (!response.ok) {
               throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
         })
         .then(data => {
            const vueloSelect = document.getElementById('vuelo');
            vueloSelect.innerHTML = '<option value="">Selecciona una opción</option>';
            if (data.length === 0) {
               console.warn('No se encontraron vuelos para el número de pasajeros especificado.');
               vueloSelect.innerHTML += '<option value="">No hay vuelos disponibles</option>';
            } else {
               data.forEach(vuelo => {
                  const option = document.createElement('option');
                  option.value = vuelo.idvuelo;
                  option.textContent = `Vuelo: ${vuelo.company}`;
                  vueloSelect.appendChild(option);
               });
            }
         })
         .catch(error => console.error('Error al obtener vuelos:', error));
   }

   populateHoteles();

   nextBtn.addEventListener('click', function () {
      const personasInput = document.getElementById('personas');
      const pasajeros = personasInput.value;
      if (pasajeros) {
         step1.style.display = 'none';
         reservaFormContainer.style.display = 'block';
         populateVuelos(pasajeros);
      } else {
         Swal.fire({
            title: 'Error',
            text: 'Por favor, ingrese el número de personas.',
            icon: 'error',
            confirmButtonText: 'OK'
         });
      }
   });

   const reservaForm = document.getElementById('reservaForm');
   reservaForm.addEventListener('submit', function (event) {
      event.preventDefault();
      const hotel = document.getElementById('hotel').value;
      const vuelo = document.getElementById('vuelo').value;
      const personas = document.getElementById('personas').value;

      const token = localStorage.getItem('jwt');

      if (!token) {
         console.error('No se encontró el token en localStorage');
         alert('Debes iniciar sesión para obtener las reservas.');
         return;
      }

      const payload = JSON.parse(atob(token.split('.')[1]));
      const username = payload.username;

      const requestBody = {
         hotel: hotel,
         vuelo: vuelo,
         usuario: {
            usuario: username
         }
      };

      fetch(`http://localhost:8084/reservas/save/${personas}`, {
         method: 'POST',
         headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
         },
         body: JSON.stringify(requestBody)
      })
         .then(response => {
            if (response.ok) {
               Swal.fire({
                  title: 'Reserva Exitosa',
                  text: 'Su reserva se ha generado con éxito.',
                  icon: 'success',
                  confirmButtonText: 'OK'
               }).then(() => {
                  window.location.href = 'home.html';
               });
            } else {
               Swal.fire({
                  title: 'Error',
                  text: 'Error al generar la reserva.',
                  icon: 'error',
                  confirmButtonText: 'OK'
               });
            }
         })
         .catch(error => {
            console.error('Error al generar reserva:', error);
            Swal.fire({
               title: 'Error',
               text: 'Ocurrió un error al procesar su solicitud.',
               icon: 'error',
               confirmButtonText: 'OK'
            });
         });
   });
});
