
<%@ page import="com.kn.Upload" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'upload.label', default: 'Upload')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><a class="help" href="${createLink(uri: '/help')}"><g:message code="default.help.label"/></a></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>

                            <g:sortableColumn property="id" title="${message(code: 'upload.id.label', default: 'Id')}" />

                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${uploadInstanceList}" status="i" var="uploadInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                            <td><g:link action="show" id="${uploadInstance.id}">${fieldValue(bean: uploadInstance, field: "id")}</g:link></td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${uploadInstanceTotal}" />
            </div>

            <div id="excelimport" class="dialog">
              <h2>Excel Upload</h2>
              <g:form action="upload" method="post" enctype="multipart/form-data">
                <label for="file">File:</label>
                <input type="file" name="file" id="file"/>
                <input class="save" type="submit" value="Upload"/>
              </g:form>
            </div>
        </div>
    </body>
</html>
