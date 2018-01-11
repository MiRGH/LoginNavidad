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
    <body><table>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
            </tr>
            <#if content??>
            <#list asignaturas as asignatura>
            <tr>
                <td>
                    <input type="button" value="ver">
                </td>
                <td>${asignatura.id}</td>
                <td>${asignatura.nombre}</td>
                <td>${asignatura.id_curso}</td>
                </tr>
            </#list>
           <#else>
            <div> No hay asignaturas para este profe</div>
            </#if>
            </table>
        </body>
    </html>
