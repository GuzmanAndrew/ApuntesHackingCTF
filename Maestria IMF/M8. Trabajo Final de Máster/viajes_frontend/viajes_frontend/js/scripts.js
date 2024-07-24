document.addEventListener('DOMContentLoaded', function () {
   const getReservasBtn = document.getElementById('getReservasBtn');

   getReservasBtn.addEventListener('click', function () {
      fetch('http://localhost:8002/reservas/all')
          .then(response => response.json())
          .then(data => {
             console.log('Datos de reservas recibidos:', data);
             const reservasTableBody = document.getElementById('reservasTableBody');
             reservasTableBody.innerHTML = '';
             data.forEach(reserva => {
                const row = document.createElement('tr');
                row.innerHTML = `
                        <td>${reserva.idreserva}</td>
                        <td>${reserva.dni}</td>
                        <td>${reserva.hotel}</td>
                        <td>${reserva.nombre}</td>
                        <td>${reserva.vuelo}</td>
                    `;
                reservasTableBody.appendChild(row);
             });
          })
          .catch(error => console.error('Error al obtener reservas:', error));
   });
});
