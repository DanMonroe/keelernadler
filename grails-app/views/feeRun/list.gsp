
<%@ page import="com.kn.FeeRun" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'feeRun.label', default: 'FeeRun')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
      <script>
        $(document).ready(function() {
          $('input[type=file]').each(function(){

            var uploadbuttonlabeltext = $(this).attr('title'); //get title attribut for languagesettings
            if(uploadbuttonlabeltext == ''){
              var uploadbuttonlabeltext = 'Browse';
            }

            var uploadbutton = '<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" value="'+uploadbuttonlabeltext+'" />';
            var realUploadBtn = '<input id="uploadBtn" type="submit" value="Upload" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-hover"/>';
          //  var uploadbutton = '<button>' + uploadbuttonlabeltext + '</button>';
             $(this).wrap('<div class="fileinputs"></div>');
              $(this).addClass('file').css('opacity', 0); //set to invisible
              $(this).parent().append($('<div class="fakefile" />').append($('<input type="text" class="ui-widget fakefileinput" />').attr('id',$(this).attr('id')+'__fake')).append(uploadbutton).append(realUploadBtn));

              $(this).bind('change', function() {
                $('#'+$(this).attr('id')+'__fake').val($(this).val());;
              });
              $(this).bind('mouseout', function() {
                $('#'+$(this).attr('id')+'__fake').val($(this).val());;
              });
            });

          $('#uploadBtn').bind('click', function() {
          var uploadName = $("#imgfile").val();
          if (uploadName == null || uploadName == '') {
            showMessage("No File", "Please browse for a NetEx spreadsheet to upload.");
            return false;
          } else {
              showMessage("Processing", "<p>Uploading and processing NetEx file.<br /><br />Please wait...</p>", 140, true);
          }
          });
        });
      </script>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><a class="help" href="${createLink(uri: '/help')}"><g:message code="default.help.label"/></a></span>
        </div>
        <div class="body">


          <g:render template="/layouts/leftnav" />






          <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
          <div id="fileUploadWrap">
          <h1>Upload a new NetEx spreadsheet</h1>

              <g:form action="upload" method="post" enctype="multipart/form-data">
                <input name="imgfile" id="imgfile" type="file" size="67" title="Browse..."/>
              </g:form>
          </div>
            <h1>Previously Uploaded</h1>
            
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="dateCreated" title="Uploaded Date" />
                            <th />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${feeRunInstanceList}" status="i" var="feeRunInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${feeRunInstance.id}"><g:formatDate date='${feeRunInstance.dateCreated}' type='datetime' style='LONG' timeStyle='SHORT'/></g:link></td>
                    <td>
                      <g:link action="show" id="${feeRunInstance.id}"><button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover"><span class="ui-button-text">View</span></button></g:link>
                      <g:link action="download" id="${feeRunInstance.id}"><button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover"><span class="ui-button-text">Download</span></button></g:link>
                      <g:pdfLink  url="/feeRun/invoice" pdfController="feeRun" pdfAction="invoice" pdfId="${feeRunInstance.id}"><button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover"><span class="ui-button-text">Invoices</span></button</g:pdfLink>
                      <g:link action="invoice" id="${feeRunInstance.id}"><button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover"><span class="ui-button-text">Test</span></button></g:link>
                    </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${feeRunInstanceTotal}" />
            </div>
        </div>
        </div>
    </body>
</html>
