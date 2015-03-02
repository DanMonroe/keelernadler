

<%@ page import="com.kn.Accnt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'accnt.label', default: 'Accnt')}" />
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
            <g:hasErrors bean="${accntInstance}">
            <div class="errors">
                <g:renderErrors bean="${accntInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="accountID"><g:message code="accnt.accountID.label" default="Account ID" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accntInstance, field: 'accountID', 'errors')}">
                                    <g:textField name="accountID" value="${accntInstance?.accountID}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="billingAccountID"><g:message code="accnt.billingAccountID.label" default="Billing Account ID" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accntInstance, field: 'billingAccountID', 'errors')}">
                                    <g:textField name="billingAccountID" value="${accntInstance?.billingAccountID}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="accnt.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accntInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" maxlength="50" value="${accntInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="type"><g:message code="accnt.type.label" default="Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accntInstance, field: 'type', 'errors')}">
                                    <g:select name="type.id" from="${com.kn.AccountType.list()}" optionKey="id" value="${accntInstance?.type?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="payPlan"><g:message code="accnt.payPlan.label" default="Pay Plan" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accntInstance, field: 'payPlan', 'errors')}">
                                    <g:select name="payPlan" from="${accntInstance.constraints.payPlan.inList}" value="${accntInstance?.payPlan}" valueMessagePrefix="accnt.payPlan"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="repNum"><g:message code="accnt.repNum.label" default="Rep Num" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accntInstance, field: 'repNum', 'errors')}">
                                    <g:select name="repNum.id" from="${com.kn.RepNum.list()}" optionKey="id" value="${accntInstance?.repNum?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="billingSchedule"><g:message code="accnt.billingSchedule.label" default="Billing Schedule" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accntInstance, field: 'billingSchedule', 'errors')}">
                                    <g:select name="billingSchedule.id" from="${com.kn.BillingSchedule.list()}" optionKey="id" value="${accntInstance?.billingSchedule?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="groupAccount"><g:message code="accnt.groupAccount.label" default="Group Account" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accntInstance, field: 'groupAccount', 'errors')}">
                                    <g:select name="groupAccount.id" from="${com.kn.GroupAccount.list()}" optionKey="id" value="${accntInstance?.groupAccount?.id}"  />
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
