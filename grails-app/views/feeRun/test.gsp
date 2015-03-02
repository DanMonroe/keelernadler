
<%@ page import="com.kn.FeeRun" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'feeRun.label', default: 'FeeRun')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
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
            <td valign="top" class="name"><g:message code="feeRun.id.label" default="Id" /></td>

        <td valign="top" class="value">${fieldValue(bean: feeRunInstance, field: "id")}</td>

        </tr>

        <tr class="prop">
          <td valign="top" class="name"><g:message code="feeRun.groups.label" default="Groups" /></td>

        <td valign="top" style="text-align: left;" class="value">
          <ul>
            <g:each in="${feeRunInstance.feegroups}" var="g">
              <li><g:link controller="groupAccount" action="show" id="${g.id}">${g?.group.name}</g:link></li>
            </g:each>
          </ul>
        </td>

        </tr>

        <tr class="prop">
          <td valign="top" class="name"><g:message code="feeRun.groups.label" default="Accounts" /></td>

        <td valign="top" style="text-align: left;" class="value">
          <table border="0" cellpadding="0" cellspacing="0">
            <tbody>
            <g:each in="${feeRunInstance.feegroups}" var="fg">
              <tr class="prop">
                <td><g:link controller="groupAccount" action="show" id="${fg.id}">${fg?.group.name}</g:link></td>
              </tr>
              <tr>
                <th>Pershing&nbsp;format</th>
                <th>Account&nbsp;Name</th>
                <th>Market&nbsp;Value</th>
                <th>Fee&nbsp;Amount</th>
              </tr>
              <g:each in="${fg?.fees}" var="fee">
                <tr class="prop">
                  <td>${fee.accountID}</td>
                  <td><g:link controller="acct" action="show" id="${fee?.id}">${accountMap[fee.accountID].name}</g:link></td>
                <td style="text-align: right"><g:formatNumber number="${fee.marketValue}" type="currency" currencyCode="USD" /></td>
                <td style="text-align: right"><g:formatNumber number="${fee.feeValue}" type="currency" currencyCode="USD" /></td>
                </tr>
              </g:each>
              <tr class="prop">
                <td colspan="4"> </td>
              </tr>

            </g:each>



            </tbody>
          </table>

        </td>

        </tr>

        <!-- full spreadsheet -->
        <tr class="prop">
          <td valign="top" class="name"><g:message code="feeRun.groups.label" default="Accounts" /><br>
          <a href='<g:createLink action="download" id="${fieldValue(bean: feeRunInstance, field: 'id')}" />'>Download</a>
          </td>

        <td valign="top" style="text-align: left;" class="value">
          <table border="0" cellpadding="0" cellspacing="0">
            <tbody>
            <g:each in="${feeRunInstance.feegroups}" var="fg">
              <tr>
                <th>Pershing&nbsp;Format</th>
                <th>Account&nbsp;Name</th>
                <th>Account&nbsp;Type</th>
                <th>Billing&nbsp;Account</th>
                <th>Value&nbsp;Date</th>
                <th>Market&nbsp;Value</th>
                <th>Account&nbsp;Weight</th>
                <th>Fee&nbsp;Amount</th>
                <th>Advance/Arrears</th>
                <th>Rep&nbsp;#</th>
              </tr>
              <g:each in="${fg?.fees}" var="fee">
                <tr class="prop">
                  <td>${fee.accountID}</td>
                  <td><g:link controller="acct" action="show" id="${fee?.id}">${accountMap[fee.accountID].name}</g:link></td>
                <td>${accountMap[fee.accountID].accountType}</td>
                <td>${accountMap[fee.accountID].billingAccountID}</td>
                <td> </td>
                <td style="text-align: right"><g:formatNumber number="${fee.marketValue}" type="currency" currencyCode="USD" /></td>
                <td style="text-align: right"><g:formatNumber number="${fee.weight}" type="percent" minFractionDigits="2" /></td>
                <td style="text-align: right"><g:formatNumber number="${fee.feeValue}" type="currency" currencyCode="USD" /></td>
                <td> </td>
                <td> </td>
                </tr>
              </g:each>
              <tr class="prop">
                <td colspan="6" style="text-align: right"><g:formatNumber number="${fg.marketValue}" type="currency" currencyCode="USD" /></td>
              <td colspan="2" style="text-align: right"><g:formatNumber number="${fg.groupFee}" type="currency" currencyCode="USD" /></td>
              </tr>
              <tr class="prop">
                <td colspan="">&nbsp;</td>
              </tr>

            </g:each>



            </tbody>
          </table>

        </td>

        </tr>


        </tbody>
      </table>
    </div>
    <div class="buttons">
      <g:form>
        <g:hiddenField name="id" value="${feeRunInstance?.id}" />
        <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
        <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
      </g:form>
    </div>
  </div>
</body>
</html>
