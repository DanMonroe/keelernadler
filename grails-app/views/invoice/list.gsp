
<%@ page import="com.kn.Invoice" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'invoice.label', default: 'Invoice')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'invoice.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="returnAddress" title="${message(code: 'invoice.returnAddress.label', default: 'Return Address')}" />
                        
                            <g:sortableColumn property="feeDeductionSummary" title="${message(code: 'invoice.feeDeductionSummary.label', default: 'Fee Deduction Summary')}" />
                        
                            <g:sortableColumn property="footer" title="${message(code: 'invoice.footer.label', default: 'Footer')}" />
                        
                            <g:sortableColumn property="makeChecksPayable" title="${message(code: 'invoice.makeChecksPayable.label', default: 'Make Checks Payable')}" />
                        
                            <g:sortableColumn property="regardingText" title="${message(code: 'invoice.regardingText.label', default: 'Regarding Text')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${invoiceInstanceList}" status="i" var="invoiceInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${invoiceInstance.id}">${fieldValue(bean: invoiceInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: invoiceInstance, field: "returnAddress")}</td>
                        
                            <td>${fieldValue(bean: invoiceInstance, field: "feeDeductionSummary")}</td>
                        
                            <td>${fieldValue(bean: invoiceInstance, field: "footer")}</td>
                        
                            <td>${fieldValue(bean: invoiceInstance, field: "makeChecksPayable")}</td>
                        
                            <td>${fieldValue(bean: invoiceInstance, field: "regardingText")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${invoiceInstanceTotal}" />
            </div>
        </div>
    </div>
    </body>
</html>
