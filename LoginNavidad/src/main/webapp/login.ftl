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
            function boton(num) {
                var opcion = null;
                switch (num) {
                    case 1:
                        opcion = "users?opcion=login";
                        document.forms.formulario1.action = opcion;
                        document.forms.formulario1.submit();
                        break;
                    case 2:
                        opcion = "users?opcion=registrar";
                        document.forms.formulario2.action = opcion;
                        document.forms.formulario2.submit();
                        break;
            }
        </script>
        </head>
    <body>
         <#if Session.mensaje??> 
        <h3> ${Session.mensaje}</h3>
        </#if>
        <h1>Login</h1>
        <form action="/login" name="formulario1" method="POST" >
            <input type="text" id="nombre1" name="nombre" size="12" />
            <input type="text" id="pass1" name="password" size="12"/>
            <input type="button" value="Logearse" onclick="boton(1);"/>
        </form>
        <form action="/login" name="formulario2" method="POST" >
            <input type="text" id="nombre2" name="nombre" size="12" />
            <input type="text" id="pass2" name="password" size="12"/>
            <input type="text" id="email" name="email" size="12" />
            <input type="button" value="Registrarse" onclick="boton(2);"/>
        </form>

		
 </body>
    </html>
