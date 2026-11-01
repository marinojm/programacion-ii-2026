const API_BASE = '/api';

document.addEventListener('DOMContentLoaded', () => {
    cargarSalones();
    cargarReservas();
});

function switchTab(tab) {
    document.querySelectorAll('.tab-btn').forEach(btn => btn.classList.remove('active'));
    document.querySelectorAll('.tab-content').forEach(content => content.classList.remove('active'));

    if (tab === 'salones') {
        document.querySelectorAll('.tab-btn')[0].classList.add('active');
        document.getElementById('salonesView').classList.add('active');
        cargarSalones();
    } else {
        document.querySelectorAll('.tab-btn')[1].classList.add('active');
        document.getElementById('reservasView').classList.add('active');
        cargarReservas();
    }
}

function showAlert(message, isError = false) {
    const alertBox = document.getElementById('alertBox');
    alertBox.textContent = message;
    alertBox.className = `alert ${isError ? 'error' : 'success'}`;
    alertBox.classList.remove('hidden');

    setTimeout(() => {
        alertBox.classList.add('hidden');
    }, 5000);
}

// ==========================================
// 1. GESTIÓN DE SALONES
// ==========================================
async function cargarSalones() {
    try {
        const response = await fetch(`${API_BASE}/salones`);
        if (!response.ok) throw new Error('Error al obtener salones');

        const salones = await response.json();
        const tbody = document.getElementById('salonesTableBody');
        tbody.innerHTML = '';

        document.getElementById('totalSalones').textContent = salones.length;

        const selectSalon = document.getElementById('reservaSalonSelect');
        selectSalon.innerHTML = '<option value="">-- Seleccione un salón --</option>';

        salones.forEach(salon => {
            if (salon.activo) {
                selectSalon.innerHTML += `<option value="${salon.id}">${salon.nombre} (Cap: ${salon.capacidad})</option>`;
            }

            const tarifa = salon.tarifaPorHora !== undefined ? salon.tarifaPorHora : salon.precioPorHora;

            tbody.innerHTML += `
                <tr>
                    <td>${salon.id}</td>
                    <td><strong>${salon.nombre}</strong></td>
                    <td>${salon.ubicacion}</td>
                    <td>${salon.capacidad} pers.</td>
                    <td>Q${(tarifa || 0).toFixed(2)}</td>
                    <td>
                        <span class="status-badge ${salon.activo ? 'status-activo' : 'status-inactivo'}">
                            ${salon.activo ? 'ACTIVO' : 'INACTIVO'}
                        </span>
                    </td>
                    <td>
                        <button class="btn btn-secondary" onclick="editarSalon(${salon.id}, '${salon.nombre}', '${salon.ubicacion}', ${salon.capacidad}, ${tarifa})">Editar</button>
                        ${salon.activo ? `<button class="btn btn-danger" onclick="desactivarSalon(${salon.id}, '${salon.nombre}', '${salon.ubicacion}', ${salon.capacidad}, ${tarifa})">Desactivar</button>` : ''}
                    </td>
                </tr>
            `;
        });
    } catch (err) {
        showAlert(err.message, true);
    }
}
async function guardarSalon(event) {
    event.preventDefault();
    const id = document.getElementById('salonId').value;
    const body = {
        nombre: document.getElementById('salonNombre').value,
        ubicacion: document.getElementById('salonUbicacion').value,
        capacidad: parseInt(document.getElementById('salonCapacidad').value),
        tarifaPorHora: parseFloat(document.getElementById('salonPrecio').value)
    };

    const method = id ? 'PUT' : 'POST';
    const url = id ? `${API_BASE}/salones/${id}` : `${API_BASE}/salones`;

    try {
        const response = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body)
        });

        const data = await response.json();
        if (!response.ok) {
            const mensajeError = data.errores ? Object.values(data.errores).join(', ') : data.mensaje;
            throw new Error(mensajeError || 'Error procesando solicitud');
        }

        showAlert(`Salón ${id ? 'actualizado' : 'creado'} con éxito`);
        closeSalonModal();
        cargarSalones();
    } catch (err) {
        showAlert(err.message, true);
    }
}

async function desactivarSalon(id, nombre, ubicacion, capacidad, tarifa) {
    if (!confirm('¿Desea desactivar este salón?')) return;
    try {
        // Construimos el DTO completo exigido por el backend, cambiando activo a false
        const body = {
            nombre: nombre,
            ubicacion: ubicacion,
            capacidad: parseInt(capacidad),
            tarifaPorHora: parseFloat(tarifa),
            activo: false
        };

        const response = await fetch(`${API_BASE}/salones/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body)
        });

        if (!response.ok) {
            const errorData = await response.json().catch(() => ({}));
            const mensajeError = errorData.errores ? Object.values(errorData.errores).join(', ') : (errorData.mensaje || 'No se pudo desactivar el salón');
            throw new Error(mensajeError);
        }

        showAlert('Salón desactivado correctamente');
        cargarSalones();
    } catch (err) {
        showAlert(err.message, true);
    }
}// ==========================================
// 2. GESTIÓN DE RESERVAS
// ==========================================
async function cargarReservas() {
    try {
        const response = await fetch(`${API_BASE}/reservas`);
        if (!response.ok) throw new Error('Error al obtener reservas');

        const reservas = await response.json();
        renderReservasTable(reservas);
        document.getElementById('totalReservas').textContent = reservas.length;
    } catch (err) {
        showAlert(err.message, true);
    }
}

function renderReservasTable(reservas) {
    const tbody = document.getElementById('reservasTableBody');
    tbody.innerHTML = '';

    reservas.forEach(res => {
        // Determinamos el nombre del salón de forma inteligente adaptándonos al JSON
        let salonNombre = 'Salón Principal';
        if (res.salon && res.salon.nombre) {
            salonNombre = res.salon.nombre;
        } else if (res.salonId) {
            salonNombre = `Salón ID: ${res.salonId}`;
        } else if (res.nombreSalon) {
            salonNombre = res.nombreSalon;
        }

        const fecha = res.fechaReserva || res.fechaEvento || 'N/A';
        const horas = res.horasReservadas ? `${res.horasReservadas} hrs` : 'N/A';
        const invitados = res.cantidadInvitados ? `${res.cantidadInvitados} pers.` : 'N/A';
        const estado = res.estado || 'CONFIRMADA';

        tbody.innerHTML += `
            <tr>
                <td>${res.id}</td>
                <td><strong>${res.clienteNombre}</strong></td>
                <td>${salonNombre}</td>
                <td>${fecha}</td>
                <td>${horas}</td>
                <td>${invitados}</td>
                <td>
                    <span class="status-badge status-${estado.toLowerCase()}">
                        ${estado}
                    </span>
                </td>
                <td>
                    ${estado !== 'CANCELADA' ? `<button class="btn btn-danger" onclick="cancelarReserva(${res.id})">Cancelar</button>` : '-'}
                </td>
            </tr>
        `;
    });
}
async function filtrarReservas() {
    const estado = document.getElementById('filtroEstado').value;
    if (!estado) {
        cargarReservas();
        return;
    }

    try {
        const response = await fetch(`${API_BASE}/reservas`);
        if (!response.ok) throw new Error('Error al filtrar reservas');
        const reservas = await response.json();
        const filtradas = reservas.filter(r => (r.estado || 'CONFIRMADA') === estado);
        renderReservasTable(filtradas);
    } catch (err) {
        showAlert(err.message, true);
    }
}

async function guardarReserva(event) {
    event.preventDefault();
    const body = {
        clienteNombre: document.getElementById('reservaCliente').value,
        salonId: parseInt(document.getElementById('reservaSalonSelect').value),
        fechaReserva: document.getElementById('reservaFecha').value,
        horasReservadas: parseInt(document.getElementById('reservaHoraInicio').value) || 4, // Ajustado a horas
        cantidadInvitados: parseInt(document.getElementById('reservaHoraFin').value) || 30 // Campo adaptado
    };

    try {
        const response = await fetch(`${API_BASE}/reservas`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body)
        });

        const data = await response.json();
        if (!response.ok) {
            const mensajeError = data.errores ? Object.values(data.errores).join(', ') : data.mensaje;
            throw new Error(mensajeError || 'Conflicto al crear reserva');
        }

        showAlert('Reserva registrada con éxito');
        closeReservaModal();
        cargarReservas();
    } catch (err) {
        showAlert(err.message, true);
    }
}

async function cancelarReserva(id) {
    if (!confirm('¿Desea cancelar esta reserva?')) return;
    try {
        // En lugar de un PUT a /cancelar que quizás no esté mapeado, eliminamos o actualizamos según tu backend
        const response = await fetch(`${API_BASE}/reservas/${id}`, { method: 'DELETE' });
        if (!response.ok) throw new Error('No se pudo eliminar/cancelar la reserva');
        showAlert('Reserva eliminada/cancelada correctamente');
        cargarReservas();
    } catch (err) {
        showAlert(err.message, true);
    }
}

// MODALES HELPERS
function openSalonModal() {
    document.getElementById('salonForm').reset();
    document.getElementById('salonId').value = '';
    document.getElementById('salonModalTitle').textContent = 'Registrar Salón';
    document.getElementById('salonModal').classList.remove('hidden');
}
function closeSalonModal() { document.getElementById('salonModal').classList.add('hidden'); }

function openReservaModal() {
    document.getElementById('reservaForm').reset();
    document.getElementById('reservaModalTitle').textContent = 'Crear Nueva Reserva';
    document.getElementById('reservaModal').classList.remove('hidden');
}
function closeReservaModal() { document.getElementById('reservaModal').classList.add('hidden'); }

function editarSalon(id, nombre, ubicacion, capacidad, precio) {
    document.getElementById('salonId').value = id;
    document.getElementById('salonNombre').value = nombre;
    document.getElementById('salonUbicacion').value = ubicacion;
    document.getElementById('salonCapacidad').value = capacidad;
    document.getElementById('salonPrecio').value = precio;
    document.getElementById('salonModalTitle').textContent = 'Editar Salón';
    document.getElementById('salonModal').classList.remove('hidden');
}