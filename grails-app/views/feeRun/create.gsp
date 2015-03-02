

<%@ page import="com.kn.FeeRun" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'feeRun.label', default: 'FeeRun')}" />
        <title>NetEx Upload</title>
    </head>
    <body>
      <script>
        $(document).ready(function()
        {

            $('#uploadBtn').bind('click', function() {
                        var uploadName = $("#file").val();
          if (uploadName == null || uploadName == '') {
            showMessage("No File", "Please browse for a NetEx spreadsheet to upload.");
            return false;
          } else {
                  $( "#dialog-modal" ).dialog({
                          height: 140,
                          modal: true
                  });
          }
        });



        })
        </script>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list">Previous Uploads</g:link></span>
            <span class="menuButton"><a class="help" href="${createLink(uri: '/help')}"><g:message code="default.help.label"/></a></span>
        </div>
        <div class="body">
            <h1>Upload New NetEx File</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${feeRunInstance}">
            <div class="errors">
                <g:renderErrors bean="${feeRunInstance}" as="list" />
            </div>
            </g:hasErrors>
                <div class="dialog">

                              <div id="excelimport" class="dialog">
              
              <g:form action="upload" method="post" enctype="multipart/form-data">
                
                <input type="file" name="file" id="file" style="width:400px;"/>
              </g:form>
            </div>


                </div>

        </div>
        <div id="dialog-modal" title="Processing" style="display:none;">
          <p>Uploading and processing NetEx file.<br /><br />Please wait...</p>
        </div>
    </body>
</html>
