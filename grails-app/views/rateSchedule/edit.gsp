

<%@ page import="com.kn.RateSchedule" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'rateSchedule.label', default: 'RateSchedule')}" />
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
            <g:hasErrors bean="${rateScheduleInstance}">
            <div class="errors">
                <g:renderErrors bean="${rateScheduleInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${rateScheduleInstance?.id}" />
                <g:hiddenField name="version" value="${rateScheduleInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="rateSchedule.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rateScheduleInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" maxlength="50" value="${rateScheduleInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="rates"><g:message code="rateSchedule.rates.label" default="Rates" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rateScheduleInstance, field: 'rates', 'errors')}">
                                    <g:select name="rates" from="${com.kn.Rate.list()}" multiple="yes" optionKey="id" size="5" value="${rateScheduleInstance?.rates*.id}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="stepping"><g:message code="rateSchedule.stepping.label" default="Stepping" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rateScheduleInstance, field: 'stepping', 'errors')}">
                                    <g:checkBox name="stepping" value="${rateScheduleInstance?.stepping}" />
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
