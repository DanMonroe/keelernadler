

<%@ page import="com.kn.FeeGroup" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'feeGroup.label', default: 'FeeGroup')}" />
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
            <g:hasErrors bean="${feeGroupInstance}">
            <div class="errors">
                <g:renderErrors bean="${feeGroupInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${feeGroupInstance?.id}" />
                <g:hiddenField name="version" value="${feeGroupInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fees"><g:message code="feeGroup.fees.label" default="Fees" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feeGroupInstance, field: 'fees', 'errors')}">
                                    
<ul>
<g:each in="${feeGroupInstance?.fees?}" var="f">
    <li><g:link controller="fee" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="fee" action="create" params="['feeGroup.id': feeGroupInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'fee.label', default: 'Fee')])}</g:link>

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="group"><g:message code="feeGroup.group.label" default="Group" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feeGroupInstance, field: 'group', 'errors')}">
                                    <g:select name="group.id" from="${com.kn.GroupAccount.list()}" optionKey="id" value="${feeGroupInstance?.group?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="marketValue"><g:message code="feeGroup.marketValue.label" default="Market Value" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feeGroupInstance, field: 'marketValue', 'errors')}">
                                    <g:textField name="marketValue" value="${fieldValue(bean: feeGroupInstance, field: 'marketValue')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="groupFee"><g:message code="feeGroup.groupFee.label" default="Group Fee" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feeGroupInstance, field: 'groupFee', 'errors')}">
                                    <g:textField name="groupFee" value="${fieldValue(bean: feeGroupInstance, field: 'groupFee')}" />
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
