
<%@ page import="com.kn.Fee" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fee.label', default: 'Fee')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'fee.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="accountID" title="${message(code: 'fee.accountID.label', default: 'Account ID')}" />
                        
                            <g:sortableColumn property="marketValue" title="${message(code: 'fee.marketValue.label', default: 'Market Value')}" />
                        
                            <g:sortableColumn property="valueDate" title="${message(code: 'fee.valueDate.label', default: 'Value Date')}" />
                        
                            <g:sortableColumn property="feeValue" title="${message(code: 'fee.feeValue.label', default: 'Fee Value')}" />
                        
                            <g:sortableColumn property="weight" title="${message(code: 'fee.weight.label', default: 'Weight')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${feeInstanceList}" status="i" var="feeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${feeInstance.id}">${fieldValue(bean: feeInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: feeInstance, field: "accountID")}</td>
                        
                            <td>${fieldValue(bean: feeInstance, field: "marketValue")}</td>
                        
                            <td><g:formatDate date="${feeInstance.valueDate}" /></td>
                        
                            <td>${fieldValue(bean: feeInstance, field: "feeValue")}</td>
                        
                            <td>${fieldValue(bean: feeInstance, field: "weight")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${feeInstanceTotal}" />
            </div>
        </div>
    </div>
    </body>
</html>
