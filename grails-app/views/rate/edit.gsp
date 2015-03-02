

<%@ page import="com.kn.Rate" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'rate.label', default: 'Rate')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><a class="help" href="${createLink(uri: '/help')}"><g:message code="default.help.label"/></a></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${rateInstance}">
            <div class="errors">
                <g:renderErrors bean="${rateInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${rateInstance?.id}" />
                <g:hiddenField name="version" value="${rateInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="rate"><g:message code="rate.rate.label" default="Rate" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rateInstance, field: 'rate', 'errors')}">
                                    <g:textField name="rate" value="${rateInstance.rate}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="flatRate"><g:message code="rate.flatRate.label" default="Flat Rate" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rateInstance, field: 'flatRate', 'errors')}">
                                    <g:textField name="flatRate" value="${fieldValue(bean: rateInstance, field: 'flatRate')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="blockAmount"><g:message code="rate.blockAmount.label" default="Block Amount" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rateInstance, field: 'blockAmount', 'errors')}">
                                    <g:textField name="blockAmount" value="${fieldValue(bean: rateInstance, field: 'blockAmount')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="displayRate"><g:message code="rate.displayRate.label" default="Display Rate" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rateInstance, field: 'displayRate', 'errors')}">
                                    <g:textField name="displayRate" value="${rateInstance?.displayRate}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="description"><g:message code="rate.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rateInstance, field: 'description', 'errors')}">
                                    <g:textField name="description" value="${rateInstance?.description}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
