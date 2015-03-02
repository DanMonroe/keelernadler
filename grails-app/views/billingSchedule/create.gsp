

<%@ page import="com.kn.BillingSchedule" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'billingSchedule.label', default: 'BillingSchedule')}" />
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
            <g:hasErrors bean="${billingScheduleInstance}">
            <div class="errors">
                <g:renderErrors bean="${billingScheduleInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="billingSchedule.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: billingScheduleInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${billingScheduleInstance?.name}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="billingSchedule.selectedMonths.label" default="Billing Months" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: billingScheduleInstance, field: 'selectedMonths', 'errors')}">
                                    <kn:monthsCheckBoxList name="selectedMonths" height="270px"/> 
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
