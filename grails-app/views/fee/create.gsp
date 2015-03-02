

<%@ page import="com.kn.Fee" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fee.label', default: 'Fee')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><a class="help" href="${createLink(uri: '/help')}"><g:message code="default.help.label"/></a></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${feeInstance}">
            <div class="errors">
                <g:renderErrors bean="${feeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="accountID"><g:message code="fee.accountID.label" default="Account ID" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feeInstance, field: 'accountID', 'errors')}">
                                    <g:textField name="accountID" value="${feeInstance?.accountID}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="marketValue"><g:message code="fee.marketValue.label" default="Market Value" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feeInstance, field: 'marketValue', 'errors')}">
                                    <g:textField name="marketValue" value="${fieldValue(bean: feeInstance, field: 'marketValue')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="valueDate"><g:message code="fee.valueDate.label" default="Value Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feeInstance, field: 'valueDate', 'errors')}">
                                    <g:datePicker name="valueDate" precision="day" value="${feeInstance?.valueDate}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="feeValue"><g:message code="fee.feeValue.label" default="Fee Value" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feeInstance, field: 'feeValue', 'errors')}">
                                    <g:textField name="feeValue" value="${fieldValue(bean: feeInstance, field: 'feeValue')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="weight"><g:message code="fee.weight.label" default="Weight" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feeInstance, field: 'weight', 'errors')}">
                                    <g:textField name="weight" value="${fieldValue(bean: feeInstance, field: 'weight')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="feegroup"><g:message code="fee.feegroup.label" default="Feegroup" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feeInstance, field: 'feegroup', 'errors')}">
                                    <g:select name="feegroup.id" from="${com.kn.FeeGroup.list()}" optionKey="id" value="${feeInstance?.feegroup?.id}"  />
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
