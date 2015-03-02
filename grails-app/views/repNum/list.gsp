
<%@ page import="com.kn.RepNum" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'repNum.label', default: 'RepNum')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><a class="help" href="${createLink(uri: '/help')}"><g:message code="default.help.label"/></a></span>
        </div>
        <div class="body">
          <g:render template="/layouts/leftnav" />
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="code" title="${message(code: 'repNum.code.label', default: 'Code')}" />
                        
                            <g:sortableColumn property="owner" title="${message(code: 'repNum.owner.label', default: 'Owner')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${repNumInstanceList}" status="i" var="repNumInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${repNumInstance.id}">${fieldValue(bean: repNumInstance, field: "code")}</g:link></td>
                        
                            <td>${fieldValue(bean: repNumInstance, field: "owner")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${repNumInstanceTotal}" />
            </div>
        </div>
    </div>
    </body>
</html>
