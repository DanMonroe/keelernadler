
<%@ page import="com.kn.Rate" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'rate.label', default: 'Rate')}" />
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
                        
                            <g:sortableColumn property="rate" title="${message(code: 'rate.rate.label', default: 'Rate')}" />
                        
                            <g:sortableColumn property="displayRate" title="${message(code: 'rate.displayRate.label', default: 'Display Rate')}" />
                        
                            <g:sortableColumn property="description" title="${message(code: 'rate.description.label', default: 'Description')}" />
                        
                            <g:sortableColumn property="flatRate" title="${message(code: 'rate.flatRate.label', default: 'Flat Rate')}" />

                            <g:sortableColumn property="blockAmount" title="${message(code: 'rate.blockAmount.label', default: 'Block Amount')}" />

                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${rateInstanceList}" status="i" var="rateInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${rateInstance.id}">${rateInstance.rate}</g:link></td>
                        
                            <td>${fieldValue(bean: rateInstance, field: "displayRate")}</td>
                        
                            <td>${fieldValue(bean: rateInstance, field: "description")}</td>
                        
                            <td>${fieldValue(bean: rateInstance, field: "flatRate")}</td>

                            <td>${fieldValue(bean: rateInstance, field: "blockAmount")}</td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${rateInstanceTotal}" />
            </div>
        </div>
    </div>
    </body>
</html>
