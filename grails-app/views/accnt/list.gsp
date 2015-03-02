
<%@ page import="com.kn.Accnt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'accnt.label', default: 'Accnt')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
      <script>
        $(document).ready(function() {
              $( "#jqDialogImport" ).dialog({
			autoOpen: false,
			height: 'auto',
			width: 'auto',
			modal: true,
                        resizable: false,
			buttons: {
//				"Import": function() {
//                                  //$(this).trigger('submit');
//                                  alert('1');
//                                  document.importForm.submit();
//				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
//				allFields.val( "" ).removeClass( "ui-state-error" );
			}
		});

//                $("#jqDialogImport").bind( "submit", function() {
//                  //allFields.removeClass('ui-state-error');
//                  alert('4.5');
//                  document.importForm.submit();
//                  alert('5');
//                  $(this).dialog('close');
//                  });

              $( "#importAccounts" )
			.click(function() {
                          $( "#jqDialogImport" ).dialog( "open" );
			});
        });
      </script>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><a id="importAccounts" class="import" href="" onClick="return false;"><g:message code="default.import.label" default="Import Accounts" /></a></span>
            <span class="menuButton"><a class="help" href="${createLink(uri: '/help')}"><g:message code="default.help.label"/></a></span>
        </div>
        <div class="body">
          <g:render template="/layouts/leftnav" />
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="name" title="${message(code: 'accnt.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="accountID" title="${message(code: 'accnt.accountID.label', default: 'Account ID')}" />
                        
                            <g:sortableColumn property="billingAccountID" title="${message(code: 'accnt.billingAccountID.label', default: 'Billing Account ID')}" />
                        
                        
                            <th><g:message code="accnt.type.label" default="Type" /></th>
                        
                            <g:sortableColumn property="payPlan" title="${message(code: 'accnt.payPlan.label', default: 'Pay Plan')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${accntInstanceList}" status="i" var="accntInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${accntInstance.id}">${fieldValue(bean: accntInstance, field: "name")}</g:link></td>
                        
                            <td>${fieldValue(bean: accntInstance, field: "accountID")}</td>
                        
                            <td>${fieldValue(bean: accntInstance, field: "billingAccountID")}</td>
                        
                        
                            <td>${fieldValue(bean: accntInstance, field: "type")}</td>
                        
                            <td>${fieldValue(bean: accntInstance, field: "payPlan")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${accntInstanceTotal}" />
            </div>
        </div>
    </div>

    <div id="jqDialogImport" title="Import Accounts" style="display:block;">
          <form id="importForm" action="importaccounts" method="post" enctype="multipart/form-data">
          <br />
          <p>Please locate and upload a spreadsheet with the account information.</p>
          <br />
          <p>
            <input name="accountfile" id="accountfile" type="file" size="57" title="Browse..."/>
            <input type="submit" value="Import" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
          </p>
          </form>
    </div>

    </body>
</html>
