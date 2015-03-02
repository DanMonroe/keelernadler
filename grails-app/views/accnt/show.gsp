
<%@ page import="com.kn.Accnt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'accnt.label', default: 'Accnt')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><a class="help" href="${createLink(uri: '/help')}"><g:message code="default.help.label"/></a></span>
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
                            <td valign="top" class="name"><g:message code="accnt.accountID.label" default="Account ID" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: accntInstance, field: "accountID")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="accnt.billingAccountID.label" default="Billing Account ID" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: accntInstance, field: "billingAccountID")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="accnt.name.label" default="Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: accntInstance, field: "name")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="accnt.type.label" default="Type" /></td>
                            
                            <td valign="top" class="value"><g:link controller="accountType" action="show" id="${accntInstance?.type?.id}">${accntInstance?.type?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="accnt.payPlan.label" default="Pay Plan" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: accntInstance, field: "payPlan")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="accnt.repNum.label" default="Rep Num" /></td>
                            
                            <td valign="top" class="value"><g:link controller="repNum" action="show" id="${accntInstance?.repNum?.id}">${accntInstance?.repNum?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="accnt.billingSchedule.label" default="Billing Schedule" /></td>
                            
                            <td valign="top" class="value"><g:link controller="billingSchedule" action="show" id="${accntInstance?.billingSchedule?.id}">${accntInstance?.billingSchedule?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="accnt.groupAccount.label" default="Group Account" /></td>
                            
                            <td valign="top" class="value"><g:link controller="groupAccount" action="show" id="${accntInstance?.groupAccount?.id}">${accntInstance?.groupAccount?.encodeAsHTML()}</g:link></td>
                            
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">Address 1</td>
                            <td valign="top" class="value">${fieldValue(bean: accntInstance, field: "address1")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Address 2</td>
                            <td valign="top" class="value">${fieldValue(bean: accntInstance, field: "address2")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">City</td>
                            <td valign="top" class="value">${fieldValue(bean: accntInstance, field: "city")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">State</td>
                            <td valign="top" class="value">${fieldValue(bean: accntInstance, field: "state")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Zip</td>
                            <td valign="top" class="value">${fieldValue(bean: accntInstance, field: "zip")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Phone 1</td>
                            <td valign="top" class="value">${fieldValue(bean: accntInstance, field: "phone1")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Phone 2</td>
                            <td valign="top" class="value">${fieldValue(bean: accntInstance, field: "phone2")}</td>
                        </tr>

                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${accntInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
