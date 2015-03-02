

<%@ page import="com.kn.Invoice" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'invoice.label', default: 'Invoice')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${invoiceInstance}">
            <div class="errors">
                <g:renderErrors bean="${invoiceInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="returnAddress"><g:message code="invoice.returnAddress.label" default="Return Address" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: invoiceInstance, field: 'returnAddress', 'errors')}">
                                    <g:textArea  name="returnAddress" value="${invoiceInstance?.returnAddress}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="feeDeductionSummary"><g:message code="invoice.feeDeductionSummary.label" default="Fee Deduction Summary" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: invoiceInstance, field: 'feeDeductionSummary', 'errors')}">
                                    <g:textArea name="feeDeductionSummary" value="${invoiceInstance?.feeDeductionSummary}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="footer"><g:message code="invoice.footer.label" default="Footer" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: invoiceInstance, field: 'footer', 'errors')}">
                                    <g:textArea name="footer" value="${invoiceInstance?.footer}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="makeChecksPayable"><g:message code="invoice.makeChecksPayable.label" default="Make Checks Payable" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: invoiceInstance, field: 'makeChecksPayable', 'errors')}">
                                    <g:textArea name="makeChecksPayable" value="${invoiceInstance?.makeChecksPayable}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="regardingText"><g:message code="invoice.regardingText.label" default="Regarding Text" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: invoiceInstance, field: 'regardingText', 'errors')}">
                                    <g:textArea name="regardingText" value="${invoiceInstance?.regardingText}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="sendCareOf"><g:message code="invoice.sendCareOf.label" default="Send Care Of" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: invoiceInstance, field: 'sendCareOf', 'errors')}">
                                    <g:textArea name="sendCareOf" value="${invoiceInstance?.sendCareOf}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
