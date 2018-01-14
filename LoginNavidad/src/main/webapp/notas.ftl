<#ftl strip_whitespace = true>

<#setting boolean_format=computer>
<#import "/libs/mylib.ftl" as my>

<#assign charset="UTF-8">
<#assign title="Example">
<#assign content>
This is content
</#assign>
<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <meta charset="${charset}">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script>
            function cargarAlumno(id, nombre) {
                document.getElementById("nombreAl").value = nombre;
                document.getElementById("idalumno").value = id;
            }
            function cargarAsignatura(id, nombre) {
                document.getElementById("idasignatura").value = id;
                document.getElementById("nombreAs").value = nombre;
            }

            function boton(num) {
                var opcion = null;
                switch (num) {
                    case 1:
                        opcion = "notas?opcion=insert";
                        break;
                    case 2:
                        opcion = "notas?opcion=select";
                        break;
                }
                document.forms.formulario1.action = opcion;
                document.forms.formulario1.submit();
            }
            </script>
        </head>
    <body>
    <#if Session.usuarioNombre??>
        <h1>${Session.usuarioNombre}</h1>
        <form action="/users?opcion=unLogin" name="formulario1" method="POST" >
            <input type="submit" value="unlogin"/>
        </form>
        <span>Alumno: </span>
        <select id="alumno" onchange="cargarAlumno(this.value, this.options[this.selectedIndex].innerHTML)">
            <option disabled selected>alumno</option>
                <#list alumnos as alumno>
            <option value="${alumno.id}" name="${alumno.nombre}">${alumno.nombre}</option>
                </#list>
            </select>
        <span>Asignatura: </span>
        <select id="asignatura" onchange="cargarAsignatura(this.value, this.options[this.selectedIndex].innerHTML)">
            <option disabled selected>asignatura</option>
              <#list asignaturas as asignatura>
            <option value="${asignatura.id}">${asignatura.nombre}</option>
              </#list>
            </select>

        <form action="notas" name="formulario1" method="POST" >
            <table>
                <tr>
                    <td>
                        ALUMNO
                        <br>
                        <input type="hidden" id="idalumno" name="idAlumno"  value="${idAlumno}">
                        <input type="text" id="nombreAl" name="nombreAlumno"  value="${nombreAlumno}">
                        </td>
                    <td>
                        ASIGNATURA
                        <br>
                        <input type="hidden" id="idasignatura" name="idAsignatura"  value="${idAsignatura}">
                        <input type="text" id="nombreAs" name="nombreAsignatura"  value="${nombreAsignatura}">
                        </td>
                    <td>


                        </td>
                    </tr>
                <tr>
                    <td>
                        <br>
                        NOTA <input type="text" value="${nota.nota}" id="nota" name="nota" size="1">
                        </td>
                    <td>
                        <br>
                        <#if Session.permiso==2>
                        <input type="button" value="insertar" onclick="boton(1);"/>
                        </#if>
                        <input type="button" value="select" onclick="boton(2);"/>
                        </td>
                    </tr>
                </table>
            </form>

		<#else>
        <div>Ha ocurrido un error</div>
		</#if>

        </body>
    </html>
