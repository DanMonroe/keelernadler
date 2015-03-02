

<%@ page import="com.kn.GroupAccount" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'groupAccount.label', default: 'GroupAccount')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
      <script src="/keelernadler/js/ajaxaccount.js" type="text/javascript"></script>
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
            <g:hasErrors bean="${groupAccountInstance}">
            <div class="errors">
                <g:renderErrors bean="${groupAccountInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${groupAccountInstance?.id}" />
                <g:hiddenField name="version" value="${groupAccountInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="groupAccount.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: groupAccountInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" maxlength="50" value="${groupAccountInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="accounts"><g:message code="groupAccount.accounts.label" default="Accounts" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: groupAccountInstance, field: 'accounts', 'errors')}">
                                    
<ul id="account_list">
<g:each in="${groupAccountInstance?.accounts?}" var="a">
    <li><g:link controller="accnt" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
                                  <a href="#" id='add_account'>${message(code: 'default.add.label', args: [message(code: 'accnt.label', default: 'Accnt')])}</a>

                          <!--  <g:link controller="accnt" action="create" params="['groupAccount.id': groupAccountInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'accnt.label', default: 'Accnt')])}</g:link>
-->
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="rateSchedule"><g:message code="groupAccount.rateSchedule.label" default="Rate Schedule" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: groupAccountInstance, field: 'rateSchedule', 'errors')}">
                                    <g:select name="rateSchedule.id" from="${com.kn.RateSchedule.list()}" optionKey="id" value="${groupAccountInstance?.rateSchedule?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="invoiced"><g:message code="groupAccount.invoiced.label" default="Invoiced" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: groupAccountInstance, field: 'invoiced', 'errors')}">
                                    <g:checkBox name="invoiced" value="${groupAccountInstance?.invoiced}" />
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

      <div id="newaccountform" title="Create new Account">
<g:form action="save" >
  <g:hiddenField name="groupAccount.id" value="${groupAccountInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="accountID"><g:message code="accnt.accountID.label" default="Account ID" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accntInstance, field: 'accountID', 'errors')}">
                                    <g:textField name="accountID" value="${fieldValue(bean: accntInstance, field: 'accountID')}" />
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


                        </tbody>
                    </table>
                </div>

            </g:form>
      </div>
    </body>
</html>
