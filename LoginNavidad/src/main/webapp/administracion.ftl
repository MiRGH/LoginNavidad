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
             function cargarUser(id) {
                document.getElementById("id").value = id;
            }
            </script>
        </head>
    <body>
        <#if Session.usuarioNombre??>
        <#if Session.mensaje??> 
        <h3> ${Session.mensaje}</h3>
        </#if>
        <h1>Administrador ${Session.usuarioNombre}</h1>
        <table>
         <#list usuarios as user>
            <tr>
                <td>
                    <input type="button" value="seleccionar" onclick="cargarUser('${user.id}');"/>
                </td> 
                <td>${user.id}</td>
                <td>${user.nombre}</td>
                <td>${user.email}</td>
                <td>${user.permiso}</td>
                <td>${user.activado}</td>
                <td>${user.fechaActivacion}</td>
                </tr>
            </#list>
            </table>

            <form action="/administracionUsuarios?opcion=cambiarPermiso" name="formulario1" method="POST" >
            <input type="text" id="id" name="id" size="12"/>
            <input type="text" id="permisoNuevo" name="permisoNuevo" size="12"/>
            <input type="submit" value="Cambiar Permiso"/>
            </form>


            <form action="/administracionUsuarios?opcion=atras" name="formulario2" method="POST" >
            <input type="submit" value="Atras"/>
            </form>

		</#if>

        </body>
    </html>
