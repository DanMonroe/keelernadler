
<%@ page import="com.kn.Invoice" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'invoice.label', default: 'Invoice')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="invoice.returnAddress.label" default="Return Address" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: invoiceInstance, field: "returnAddress")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="invoice.feeDeductionSummary.label" default="Fee Deduction Summary" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: invoiceInstance, field: "feeDeductionSummary")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="invoice.footer.label" default="Footer" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: invoiceInstance, field: "footer")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="invoice.makeChecksPayable.label" default="Make Checks Payable" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: invoiceInstance, field: "makeChecksPayable")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="invoice.regardingText.label" default="Regarding Text" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: invoiceInstance, field: "regardingText")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="invoice.sendCareOf.label" default="Send Care Of" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: invoiceInstance, field: "sendCareOf")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${invoiceInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
