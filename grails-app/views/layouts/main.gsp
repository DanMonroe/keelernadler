<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'kn.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="jquery" plugin="jquery" />
        <jqui:resources theme="ui-lightness" />
        <g:javascript library="kn" />
    </head>
    <body>
      <script type="text/javascript">
        var contextPath = '<%=request.getContextPath()%>';
      </script>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
      <div id="bodyWrapper">
        <div id="grailsLogo"><img src="${resource(dir:'images',file:'top_wide.jpg')}" alt="Keeler and Nadler" border="0" /></div>
        <g:layoutBody />
      </div>
      <div id="jqDialog" title="">
        <p id="jqMsg"></p>
      </div>
    </body>
</html>