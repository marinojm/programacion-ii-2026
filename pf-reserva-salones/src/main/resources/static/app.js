const API_BASE = '/api'; // URL base para los controladores Spring Boot

document.addEventListener('DOMContentLoaded', () => {
    cargarSalones();
    cargarReservas();
});

// NAVEGACIÓN ENTRE PESTAÑAS
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

// MOSTRAR ALERTAS (Manejo de Respuestas Exitosas y Excepciones de la API)
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

        // Poblar select de reservas en modal
        const selectSalon = document.getElementById('reservaSalonSelect');
        selectSalon.innerHTML = '<option value="">-- Seleccione un salón --</option>';

        salones.forEach(salon => {
            if (salon.activo) {
                selectSalon.innerHTML += `<option value="${salon.id}">${salon.nombre} (Cap: ${salon.capacidad})</option>`;
            }

            tbody.innerHTML += `
                <tr>
                    <td>${salon.id}</td>
                    <td><strong>${salon.nombre}</strong></td>
                    <td>${salon.ubicacion}</td>
                    <td>${salon.capacidad} pers.</td>
                    <td>Q${(salon.precioPorHora || 0).toFixed(2)}</td>
                    <td>
                        <span class="status-badge ${salon.activo ? 'status-activo' : 'status-inactivo'}">
                            ${salon.activo ? 'ACTIVO' : 'INACTIVO'}
                        </span>
                    </td>
                    <td>
                        <button class="btn btn-secondary" onclick="editarSalon(${salon.id}, '${salon.nombre}', '${salon.ubicacion}', ${salon.capacidad}, ${salon.precioPorHora})">Editar</button>
                        ${salon.activo ? `<button class="btn btn-danger" onclick="desactivarSalon(${salon.id})">Desactivar</button>` : ''}
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
        precioPorHora: parseFloat(document.getElementById('salonPrecio').value)
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
            // Manejo global de excepciones provenientes del backend (400, 409, 404)
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

async function desactivarSalon(id) {
    if (!confirm('¿Desea desactivar este salón?')) return;
    try {
        const response = await fetch(`${API_BASE}/salones/${id}`, { method: 'DELETE' });
        if (!response.ok) throw new Error('No se pudo desactivar el salón');
        showAlert('Salón desactivado correctamente');
        cargarSalones();
    } catch (err) {
        showAlert(err.message, true);
    }
}

// ==========================================
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
        tbody.innerHTML += `
            <tr>
                <td>${res.id}</td>
                <td><strong>${res.clienteNombre}</strong></td>
                <td>${res.salonNombre || 'N/A'}</td>
                <td>${res.fechaEvento}</td>
                <td>${res.horaInicio}</td>
                <td>${res.horaFin}</td>
                <td>
                    <span class="status-badge status-${res.estado ? res.estado.toLowerCase() : 'confirmada'}">
                        ${res.estado || 'CONFIRMADA'}
                    </span>
                </td>
                <td>
                    ${res.estado !== 'CANCELADA' ? `<button class="btn btn-danger" onclick="cancelarReserva(${res.id})">Cancelar</button>` : '-'}
                </td>
            </tr>
        `;
    });
}

// FILTRO ESPECÍFICO (Requisito Obligatorio del Alcance)
async function filtrarReservas() {
    const estado = document.getElementById('filtroEstado').value;
    if (!estado) {
        cargarReservas();
        return;
    }

    try {
        const response = await fetch(`${API_BASE}/reservas/buscar?estado=${estado}`);
        if (!response.ok) throw new Error('Error al aplicar el filtro');
        const reservasFiltradas = await response.json();
        renderReservasTable(reservasFiltradas);
    } catch (err) {
        showAlert(err.message, true);
    }
}

async function guardarReserva(event) {
    event.preventDefault();
    const body = {
        clienteNombre: document.getElementById('reservaCliente').value,
        salonId: parseInt(document.getElementById('reservaSalonSelect').value),
        fechaEvento: document.getElementById('reservaFecha').value,
        horaInicio: document.getElementById('reservaHoraInicio').value,
        horaFin: document.getElementById('reservaHoraFin').value
    };

    try {
        const response = await fetch(`${API_BASE}/reservas`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body)
        });

        const data = await response.json();

        if (!response.ok) {
            // Captura los errores HTTP 409 (Conflicto de fecha) o HTTP 400 del GlobalExceptionHandler
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
        const response = await fetch(`${API_BASE}/reservas/${id}/cancelar`, { method: 'PUT' });
        if (!response.ok) throw new Error('No se pudo cancelar la reserva');
        showAlert('Reserva cancelada correctamente');
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