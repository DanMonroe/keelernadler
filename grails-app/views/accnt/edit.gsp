

<%@ page import="com.kn.Accnt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'accnt.label', default: 'Accnt')}" />
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
            <g:hasErrors bean="${accntInstance}">
            <div class="errors">
                <g:renderErrors bean="${accntInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${accntInstance?.id}" />
                <g:hiddenField name="version" value="${accntInstance?.version}" />
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
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="address1">Address 1</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accntInstance, field: 'address1', 'errors')}">
                                    <g:textField name="address1" maxlength="50" value="${accntInstance?.address1}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="address2">Address 2</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accntInstance, field: 'address2', 'errors')}">
                                    <g:textField name="address2" maxlength="50" value="${accntInstance?.address2}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="city">City</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accntInstance, field: 'city', 'errors')}">
                                    <g:textField name="city" maxlength="50" value="${accntInstance?.city}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="state">State</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accntInstance, field: 'state', 'errors')}">
                                    <g:textField name="state" maxlength="50" value="${accntInstance?.state}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="zip">Zip</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accntInstance, field: 'zip', 'errors')}">
                                    <g:textField name="zip" maxlength="50" value="${accntInstance?.zip}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="phone1">Phone 1</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accntInstance, field: 'phone1', 'errors')}">
                                    <g:textField name="phone1" maxlength="50" value="${accntInstance?.phone1}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="phone2">Phone 2</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accntInstance, field: 'phone2', 'errors')}">
                                    <g:textField name="phone2" maxlength="50" value="${accntInstance?.phone2}" />
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
