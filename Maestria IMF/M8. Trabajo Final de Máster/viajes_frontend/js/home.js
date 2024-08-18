document.addEventListener('DOMContentLoaded', function () {
   const getReservasBtn = document.getElementById('getReservasBtn');

   getReservasBtn.addEventListener('click', function () {

      const token = localStorage.getItem('jwt');

      if (!token) {
         alert('Debes iniciar sesión para obtener las reservas.');
         return;
      }

      const payload = JSON.parse(atob(token.split('.')[1]));
      const username = payload.username;

      const url = `http://localhost:8084/reservas/all/by-user/${username}`;

      fetch(url, {
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
            const reservasTableBody = document.getElementById('reservasTableBody');
            reservasTableBody.innerHTML = '';
            data.forEach(reserva => {
               const row = document.createElement('tr');
               row.innerHTML = `
                     <td>${reserva.hotel}</td>
                     <td>${reserva.vuelo}</td>
                  `;
               reservasTableBody.appendChild(row);
            });
         })
         .catch(error => console.error('Error al obtener reservas:', error));
   });
});
