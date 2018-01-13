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
         <#if Session.mensaje??> 
        <h3> ${Session.mensaje}</h3>
        </#if>
         <form action="/users?opcion=unLogin" name="formulario1" method="POST" >
            <input type="submit" value="unlogin"/>
            </form>
		<#else>
        <div>No hay usuario</div>
		</#if>
        <#if Session.permiso!=4>
        <h3> Permiso de nivel ${Session.permiso}</h3>
        <#if Session.permiso ==1 || Session.permiso ==0>
        <form action="/administracionUsuarios?opcion=todos" name="formulario2" method="POST" >
            <input type="submit" value="Administradores"/>
            </form>
        <form action="/profesores" name="formulario3" method="POST" >
            <input type="submit" value="Profesores" />
            </form>
          <form action="/alumnos" name="formulario4" method="POST" >
            <input type="button" value="Alumnos"/>
            </form>
        </#if>
        <#if Session.permiso ==2>
        <form action="/profesores" name="formulario3" method="POST" >
            <input type="submit" value="Profesores" />
            </form>
        </#if>
        <#if Session.permiso ==3>
        <form action="/alumnos" name="formulario4" method="POST" >
            <input type="button" value="Alumnos"/>
            </form>
        </#if>
		<#else>
        <div>Aun no se le han asignado los permisos</div>
		</#if>
        </body>
    </html>
