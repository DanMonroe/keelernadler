<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Keeler and Nadler Help</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body help">
          
            <p>
              If you have any questions other than the items listed on this page, send an email to <a href="mailto:danmonroe67@gmail.com">danmonroe67@gmail.com</a>
            </p>
            <div class="list">



<h2>What basic configuration should be done prior to processing billing spreadsheets?</h2>
<p>
<ul>
<li>Ensure all possible Account Types have been created.</li>
<li>Import or manually add accounts that may appear in the uploaded NetEx spreadsheet.</li>
<li>All representatives and their codes added.</li>
</ul>
</p>
<h2>How do I create a new account?</h2>
<p>All accounts must belong to a Group Account.  First create a new Group Account.  After you have created the group account, edit the group account.  There will be a link on the edit page to 'Add Account'  When you click on the link, a pop-up window will appear allowing you to create a new account.</p>

<h2>How do you add multiple accounts at once?</h2>
<p>
You may upload a spreadsheet with the new accounts from the <g:link controller="accnt" action="list">Accounts</g:link> page. Click on the 'Import Accounts' link in the top menu. The spreadsheet must be in a pre-defined format such as the example below and follow these rules:
<ul>
<li>The first line is a header line and is skipped during the process. Each subsequent line contains the details for the accounts.</li>
<li>All accounts in a group are listed sequentially followed by an empty line to indicate the end of the group.  The first account in the group will become the name of the group.</li>
<li>For the Billing Cycle column, the number provided is the first month of the year billed. 1 is January, 2 is February, and 3 is March.</li>
<li>Anything entered in the Invoiced column will result in that account being flagged as being invoiced.</li>

</ul>
    
</p>

<p>
Note: All Account Types and Representative codes MUST be present in the system prior to importing.
</p>
<p>
<table class="helptable">
<tr>
<th>Account ID</th>
<th>Account Name</th>
<th>Account Type</th>
<th>Billing Account</th>
<th>Advance/Arrears</th>
<th>Rep # </th>
<th>Billing Cycle</th>
<th>Invoiced</th>
</tr>

<tr class="sample1"><td>5DK111111</td><td>John Doe Retirement Account1</td><td>TRUST</td><td>5DK111111</td><td>ADVANCE</td><td>PU5</td><td>1</td><td> </td></tr>
<tr><td>5DK111112</td><td>John Doe Retirement Account2</td><td>TRUST</td><td>5DK111112</td><td>ADVANCE</td><td>PU5</td><td>1</td><td> </td></tr>
<tr class="sample1"><td>5DK111113</td><td>John Doe Retirement Account3</td><td>TRUST</td><td>5DK111113</td><td>ADVANCE</td><td>PU5</td><td>1</td><td> </td></tr>
<tr><td>5DK111114</td><td>John Doe Retirement Account4</td><td>IRA RO</td><td>5DK111114</td><td>ADVANCE</td><td>PU5</td><td>1</td><td> </td></tr>
<tr class="sample1"><td> </td><td> </td><td> </td><td> </td><td> </td><td> </td><td> </td><td> </td></tr>
<tr><td>5DK111117</td><td>John Doe Retirement Account5</td><td>Individual</td><td>5DK111117</td><td>ADVANCE</td><td>PU5</td><td>3</td><td>y</td></tr>
<tr class="sample1"><td> </td><td> </td><td> </td><td> </td><td> </td><td> </td><td> </td><td> </td></tr>
<tr><td>5DK111119</td><td>John Doe Retirement Account6</td><td>IRA</td><td>5DK111119</td><td>ADVANCE</td><td>DR5</td><td>2</td><td> </td></tr>
<tr class="sample1"><td>5DK111120</td><td>John Doe Retirement Account7</td><td>Individual</td><td>5DK111120</td><td>ADVANCE</td><td>DR5</td><td>2</td><td> </td></tr>
<tr><td> </td><td> </td><td> </td><td> </td><td> </td><td> </td><td> </td><td> </td></tr>
<tr class="sample1"><td>6DK111111</td><td>John Doe Retirement Account8</td><td>TRUST</td><td>6DK111111</td><td>ADVANCE</td><td>PU5</td><td>1</td><td> </td></tr>
<tr><td>6DK111112</td><td>John Doe Retirement Account9</td><td>TRUST</td><td>6DK111112</td><td>ADVANCE</td><td>PU5</td><td>1</td><td> </td></tr>
<tr class="sample1"><td>6DK111113</td><td>John Doe Retirement Account10</td><td>TRUST</td><td>6DK111113</td><td>ADVANCE</td><td>PU5</td><td>1</td><td> </td></tr>
<tr><td>6DK111114</td><td>John Doe Retirement Account11</td><td>IRA RO</td><td>6DK111114</td><td>ADVANCE</td><td>PU5</td><td>1</td><td> </td></tr>
<tr class="sample1"><td> </td><td> </td><td> </td><td> </td><td> </td><td> </td><td> </td><td> </td></tr>
<tr><td>6DK111117</td><td>John Doe Retirement Account12</td><td>Individual</td><td>6DK111117</td><td>ADVANCE</td><td>PU5</td><td>3</td><td>y</td></tr>
<tr class="sample1"><td> </td><td> </td><td> </td><td> </td><td> </td><td> </td><td> </td><td> </td></tr>
<tr><td>6DK111119</td><td>John Doe Retirement Account13</td><td>IRA</td><td>6DK111119</td><td>ADVANCE</td><td>PU5</td><td>2</td><td>Sure</td></tr>
<tr class="sample1"><td>6DK111120</td><td>John Doe Retirement Account14</td><td>Individual</td><td>6DK111120</td><td>ADVANCE</td><td>PU5</td><td>2</td><td>Ok</td></tr>

</table>
</p>

<h2>What is the normal process for a billing cycle.</h2>
<p>
Each billing cycle should use the following process:
<ul>
<li>Upload the NetEx Spreadsheet. Click on the Browse button or click in the adjacent textbox.  Select the spreadsheet from your hard drive and then click the 'Upload' button.</li>
<li>After clicking upload, the system will process the file and calculate the fees.</li>
<li>When the system has completed the fee calculation, the results will be displayed on the page.</li>
<li>Click on the 'Download' button to download a spreadsheet with the calculated fees.</li>
<li>Any accounts from the NetEx upload that are not yet in the system will be listed as 'Orphans'.</li>
</ul>
</p>

<h2>What are Orphans?</h2>
<p>Orphans are accounts that were found in an uploaded NetEx file that have not yet been added to the database.  You will need to create the account in the system and re-upload teh corresponding NetEx spreadsheet.</p>

<h2>What are the 'Fees' and 'Fee Groups' under the 'Raw Fee Data' section in the left navigation area?</h2>
<p>These are the raw database records resulting from a previous NetEx upload.  They are present only as a means to edit specific information after an upload and are not used normally.</p>

<h2></h2>
<p></p>

              </div>
            </div>
        </div>

    </body>
</html>
