<%@ page import="com.kn.FeeRun" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="${resource(dir:'css',file:'print.css')}" />
    <g:set var="entityName" value="${message(code: 'feeRun.label', default: 'FeeRun')}" />
    <title>Invoice</title>
  </head>
  <body>

  <g:each in="${feeRunInstance.feegroups}" var="fg">

  <table width="100%" border="1" cellpadding="0" cellspacing="0">
    <tr>
      <td>
        <div class="invoiceHeader">INVOICE</div>
        <div class="invDate">${invoiceDate}</div>
      </td>
      <td align="right"><img src="../../images/cambridge.jpg"></td>
    </tr>
  </table>
    <table width="100%" border="1" cellpadding="0" cellspacing="0">
      <tr>
      <td>${invoiceData.returnAddress}</td>
      <td>TO</td>
      <td>${toAddress}</td>
      <td>RE</td>
      <td>${billingPeriodDescription}</td>
      </tr>
    </table>
    <h1>Assets Under Management</h1>
  <table width="100%" border="1" cellpadding="0" cellspacing="0">
            <tbody>
              <tr>
                <th>Custodian</th>
                <th>Account</th>
                <th>Account&nbsp;Owner</th>
                <th>Account&nbsp;Type</th>
                <th>Billing&nbsp;Account</th>
                <th>Value&nbsp;Date</th>
                <th>Asset&nbsp;Value</th>
              </tr>
              <g:each in="${fg?.fees}" var="fee">
                <tr class="prop">
                  <td>Pershing</td>
                  <td>${fee.accountID}</td>
                  <td><g:link controller="accnt" action="show" id="${fee?.id}">${accountMap[fee.accountID].name}</g:link></td>
                <td>${accountMap[fee.accountID].type?.title}</td>
                <td>${accountMap[fee.accountID].billingAccountID}</td>
                <td><g:formatDate date="${feeRunInstance?.dateCreated}" type="date" /></td>
                <td style="text-align: right"><g:formatNumber number="${fee.marketValue}" type="currency" currencyCode="USD" /></td>
                </tr>
              </g:each>
              <tr class="prop">
                <td colspan="7" style="text-align: right"><g:formatNumber number="${fg.marketValue}" type="currency" currencyCode="USD" /></td>
              </tr>


            </tbody>
          </table>
    <h1>Portfolio Service Fee Calculation</h1>
  <table width="100%" border="1" cellpadding="0" cellspacing="0">
            <tbody>
              <tr>
                <th>&nbsp;</th>
                <th>Fee&nbsp;Rate</th>
                <th>Fee</th>
              </tr>
              <tr class="prop">
                <td colspan="2"><h3>Calculated Quarterly Fee</h3></td>
                <td style="text-align: right"><g:formatNumber number="${fg.groupFee}" type="currency" currencyCode="USD" /></td>
              </tr>
              <tr class="prop">
                <td colspan="2">Days in period</td>
                <td style="text-align: right">${daysInPeriod}</td>
              </tr>


            </tbody>
  </table>
    <div class="feeDeductionSummary">${invoiceData.feeDeductionSummary}</div>
    <div class="makeChecks">${invoiceData.makeChecksPayable}</div>
    <div class="careOf">${invoiceData.sendCareOf}</div>
    <div class="thanks">${invoiceData.footer}</div>
    <div style="page-break-after:always"></div>
    </g:each>

  </body>
</html>
