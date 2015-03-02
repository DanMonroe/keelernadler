
<%@ page import="com.kn.GroupAccount" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'groupAccount.label', default: 'GroupAccount')}" />
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
                        
                            <g:sortableColumn property="name" title="${message(code: 'groupAccount.name.label', default: 'Name')}" />
                        
                            <th><g:message code="groupAccount.rateSchedule.label" default="Rate Schedule" /></th>
                        
                            <g:sortableColumn property="invoiced" title="${message(code: 'groupAccount.invoiced.label', default: 'Invoiced')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${groupAccountInstanceList}" status="i" var="groupAccountInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${groupAccountInstance.id}">${fieldValue(bean: groupAccountInstance, field: "name")}</g:link></td>
                        
                            <td>${fieldValue(bean: groupAccountInstance, field: "rateSchedule")}</td>
                        
                            <td><g:formatBoolean boolean="${groupAccountInstance.invoiced}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${groupAccountInstanceTotal}" />
            </div>
        </div>
    </div>
    </body>
</html>
