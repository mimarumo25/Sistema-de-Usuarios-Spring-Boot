// Call the dataTables jQuery plugin
$(document).ready(function () {
  cargarUsuario();

  $("#usuarios").DataTable();
  cargarUser();
});
function cargarUser(){
  document.getElementById('nombreUsuario').outerHTML =localStorage.email;
}
async function cargarUsuario() {
  const request = await fetch("api/usuarios", {
    method: "GET",
    headers: getHeaders(),
  });
  const usuarios = await request.json();

  let listadoHTML = "";
  for (let usuario of usuarios) {
    let usuarioHTML =
      "<tr><td>" +
      usuario.id +
      "</td><td>" +
      usuario.nombre +
      " " +
      usuario.apellido +
      "</td><td>" +
      usuario.email +
      "</td><td>" +
      usuario.telefono +
      '</td><td><a href="#" onClick="eliminarUsuario(' +
      usuario.id +
      ')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a></td></tr>';
    listadoHTML += usuarioHTML;
  }

  document.querySelector("#usuarios tbody").outerHTML = listadoHTML;
  
}
async function eliminarUsuario(id) {
  if (!confirm("Desea eliminar este Usuario?")) {
    return;
  }
  const request = await fetch("api/usuario/" + id, {
    method: "DELETE",
    headers: getHeaders(),
  });
  location.reload();
}

function getHeaders() {
  return {
    Accept: "application/json",
    "Content-Type": "application/json",
    'Authorization': localStorage.token,
  };
}
