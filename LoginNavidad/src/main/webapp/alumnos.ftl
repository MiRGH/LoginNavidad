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
        <table>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
            </tr>
            <#if content??>
            <#list alumnos as alumno>
            <tr>
                <td>
                    <input type="button" value="ver">
                </td>
                <td>${alumno.id}</td>
                <td>${alumno.nombre}</td>
                </tr>
            </#list>
           <#else>
            <div> No hay alumnos</div>
            </#if>
            </table>
            
        </body>
    </html>
