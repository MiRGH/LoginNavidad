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
        </head>

    <body>
         <#if Session.usuarioNombre??>
        <h1>Bienvenido ${Session.usuarioNombre}</h1>
        <form action="users?opcion=modificar" name="formulario8" method="POST" >
            <input type="text" id="pass" name="password" size="12" />
            <input type="submit" value="cambiar contraseña" />
        </form>
         <#if Session.mensaje??> 
        <h3> ${Session.mensaje}</h3>
        </#if>
         <form action="/users?opcion=unLogin" name="formulario1" method="POST" >
            <input type="submit" value="unlogin"/>
            </form>
		<#else>
        <div>No hay usuario</div>
		</#if>
        <#if Session.permiso.equals("4")>
        <h3> Permiso de nivel ${Session.permiso}</h3>
        <#if Session.permiso.equals("1") || Session.permiso.equals("0")>
        <form action="/administracionUsuarios?opcion=todos" name="formulario2" method="POST" >
            <input type="submit" value="Administradores"/>
            </form>
        <form action="/profesores" name="formulario2" method="POST" >
            <input type="submit" value="Profesores" />
            </form>
          <form action="/alumnos" name="formulario3" method="POST" >
            <input type="button" value="Alumnos"/>
            </form>
        </#if>
        <#if Session.permiso.equals("2")>
        <form action="/notas" name="formulario4" method="POST" >
            <input type="submit" value="notas" />
        </form>
        <form action="/tareas" name="formulario5" method="POST" >
            <input type="submit" value="tareas" />
        </form>
        </#if>
        <#if Session.permiso.equals("3")>
        <form action="/notas" name="formulario6" method="POST" >
            <input type="button" value="notas"/>
        </form>
        <form action="/tareas" name="formulario7" method="POST" >
            <input type="submit" value="tareas" />
        </form>
        </#if>
		<#else>
        <div>Aun no se le han asignado los permisos</div>
		</#if>
        </body>
    </html>
