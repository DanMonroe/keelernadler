package com.kn

import jxl.*
import jxl.write.*

import java.io.File;
import java.util.Date;

import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile

import org.xhtmlrenderer.pdf.ITextRenderer
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.*

class FeeRunController {

    //    static allowedMethods = [save: "POST", update: "POST", delete: "POST", upload: "POST"]

    def com.kn.MypdfService myPdfService

def mypdfLink = {
        def feeRunInstance = FeeRun.get(params.pdfId)
        if (!feeRunInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feeRun.label', default: 'FeeRun'), params.id])}"
            redirect(action: "list")
        }
        else {
            println ("invoice 22")
            def accountMap = [:]
            def foundAccount
            feeRunInstance.feegroups.each() { fg ->
                fg.fees.each() { fee ->
                    foundAccount = com.kn.Accnt.findByAccountID(fee.accountID);
                    //println "foundAccount = ${foundAccount.name}"
                    accountMap[fee.accountID] = foundAccount
                }
            }
            [feeRunInstance: feeRunInstance, accountMap: accountMap]
            println ("invoice 23")
    try{
      byte[] b
      def baseUri = request.scheme + "://" + request.serverName + ":" + request.serverPort + grailsAttributes.getApplicationUri(request)
      // def baseUri = g.createLink(uri:"/", absolute:"true").toString()
      // TODO: get this working...
      //if(params.template){
        //println "Template: $params.template"
        //def content = g.render(template:params.template, model:[pdf:params])
        //b = pdfService.buildPdfFromString(content.readAsString(), baseUri)
      //}
      if(params.pdfController){
println "GSP - FeeRunController: $params.pdfController , Action: $params.pdfAction, Id: $params.pdfId"
        def content = g.include(controller:params.pdfController, action:params.pdfAction, id:params.pdfId)
        b = myPdfService.buildPdfFromString(content.readAsString(), baseUri)
      }
      else{
        def url = baseUri + params.url
println "GSP - url: $url"
if(myPdfService == null) {
    println "myPdfService is null"
}
else {
    println "not null"
}
        b = myPdfService.buildPdf(url)
      }
      response.setContentType("application/pdf")
      response.setHeader("Content-disposition", "attachment; filename=" + (params.filename ?: "document.pdf"))
println "44"
println "${b}"
println "55"
println "${b?.length}"
                response.setContentLength(b.length)
      response.getOutputStream().write(b)
    }
    catch (Throwable e) {
      println "there was a problem with PDF generation ${e}"
      //if(params.template) render(template:params.template)
      if(params.pdfController) redirect(controller:params.pdfController, action:params.pdfAction, params:params)
      else redirect(uri:params.url + '?' + request.getQueryString())
    }
        }
  }


    def invoicetest = {
        println ("invoicetest 11")
        StringBuffer buf = new StringBuffer();
        buf.append("<html>");
        String css = getServletContext().getRealPath("/print.css");
        // put in some style
        buf.append("<head><link rel='stylesheet' type='text/css' href='"+css+"' media='print'/></head>");

        buf.append("<body>");
        buf.append("<p><img src='images/top.jpg'/></p>");
        buf.append("<h2>Test</h2>");
        buf.append("</body>");
        buf.append("</html>");

        println ("invoicetest 12")
        // parse our markup into an xml Document
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new StringBufferInputStream(buf.toString()));
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(doc, null);
            renderer.layout();
            OutputStream os = response.getOutputStream();
            renderer.createPDF(os);
            os.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        println ("invoicetest 13")
    }
    
    def invoice = {

        def feeRunInstance = FeeRun.get(params.id)
        if (!feeRunInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feeRun.label', default: 'FeeRun'), params.id])}"
            redirect(action: "list")
        }
        else {
            println ("invoicetest 1")
            def accountMap = [:]
            def foundAccount
            def toAddr
            feeRunInstance.feegroups.each() { fg ->
                fg.fees.each() { fee ->
                    foundAccount = com.kn.Accnt.findByAccountID(fee.accountID);
                    //println "foundAccount = ${foundAccount.name}"
                    accountMap[fee.accountID] = foundAccount
                    if( ! toAddr ) {
                        toAddr = foundAccount.buildAddress()
                    }
                }
            }
            println feeRunInstance
            println accountMap
            println ("invoicetest 22")
            
            def invoiceData = com.kn.Invoice.get(1);

            def billingPeriodDescription = "${invoiceData.regardingText}<br>12/1/2010 Through 2/28/2011"

            [feeRunInstance: feeRunInstance, accountMap: accountMap,
            invoiceDate : "February 1, 2011",
            toAddress : toAddr,
            billingPeriodDescription : billingPeriodDescription,
            invoiceData : invoiceData

            ]
        }
    }











    static def writeExcel(out, map, objects) {
        // create our workbook and sheet
        def workbook = Workbook.createWorkbook(out)
        def sheet = workbook.createSheet("Requests", 0)

        // walk through our map and write out the headers
        def c = 0
        map.each() { k, v ->
            // write out our header
            sheet.addCell(new Label(c, 0, v.toString()))

            // write out the value for each object
            def r = 1
            objects.each() { o ->
                if (o[k] != null) {
                    if (o[k] instanceof java.lang.Number) {
                        sheet.addCell(new Number(c, r, o[k]))
                    } else {
                        sheet.addCell(new Label(c, r, o[k].toString()))
                    }
                }
                r++
            }
            c++
        }

        // close
        workbook.write()
        workbook.close()
    }

    def show = {
        def feeRunInstance = FeeRun.get(params.id)
        if (!feeRunInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feeRun.label', default: 'FeeRun'), params.id])}"
            redirect(action: "list")
        }
        else {
            def accountMap = [:]
            def foundAccount
            feeRunInstance.feegroups.each() { fg ->
                fg.fees.each() { fee ->
                    foundAccount = com.kn.Accnt.findByAccountID(fee.accountID);
                    //println "foundAccount = ${foundAccount.name}"
                    accountMap[fee.accountID] = foundAccount
                }
            }
            [feeRunInstance: feeRunInstance, accountMap: accountMap]
        }
    }

    def download = {
        def feeRunInstance = FeeRun.get(params.id)
        if (!feeRunInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feeRun.label', default: 'FeeRun'), params.id])}"
            redirect(action: "list")
        }
        else {
            // set our header and content type
            response.setHeader("Content-disposition", "attachment; filename='NetEx-processed-${feeRunInstance.dateCreated}.xls'")
            response.contentType = "application/vnd.ms-excel"


            // create our workbook and sheet
            def workbook = Workbook.createWorkbook(response.outputStream)
            def sheet = workbook.createSheet("Fee Summary", 0)

            // Create a cell format for Arial 10 point font
            WritableFont arial10Boldfont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat arial10Boldformat = new WritableCellFormat (arial10Boldfont);

            NumberFormat currencyFormatter = new NumberFormat(NumberFormat.CURRENCY_DOLLAR);
            WritableCellFormat cellFormatCurrency = new WritableCellFormat(currencyFormatter);

            WritableCellFormat accountingFloatFormat = new WritableCellFormat (NumberFormats.ACCOUNTING_FLOAT);


            NumberFormat percentFormatter = new NumberFormat("###.##");
            WritableCellFormat cellFormatPercent = new WritableCellFormat(percentFormatter);
            WritableCellFormat floatFormat = new WritableCellFormat (NumberFormats.FLOAT);

            DateFormat customDateFormat = new DateFormat ("MM/dd/yyyy");
            WritableCellFormat dateFormat = new WritableCellFormat (customDateFormat);

            //currencyOut = currencyFormatter.format(currency);

            def header = [:]
            header.accountID = "Pershing Format"
            header.name = "Account Name"
            header.accountValue = "Market Value"
            header.fee = "Fee Amount"
            header.payPlan = "Advance/Arrears"
            header.repNum = "Rep #"

            // walk through our map and write out the headers
            header.eachWithIndex() { k, v, index ->
                // write out our header
                sheet.addCell(new Label(index, 0, v.toString(), arial10Boldformat))
                sheet.setColumnView(index,16)
            }
            sheet.setColumnView(1,25)

            def row = 1
            def cell = 0
            def thisAccount
            feeRunInstance.feegroups.eachWithIndex() { fg, index ->
                if(fg != null && fg.fees != null){
                    fg.fees.eachWithIndex() { fee, actIndex ->
                        thisAccount = com.kn.Accnt.findByAccountID(fee.accountID)
                        cell = 0
                        sheet.addCell(new Label(cell++, row, fee.accountID))
                        sheet.addCell(new Label(cell++, row, thisAccount.name))
                        sheet.addCell(new Number(cell++, row, fee.marketValue, accountingFloatFormat))
                        sheet.addCell(new Number(cell++, row, fee.feeValue,accountingFloatFormat))
                        sheet.addCell(new Label(cell++, row, thisAccount.payPlan))
                        sheet.addCell(new Label(cell++, row, thisAccount.repNum.code))
                        row++
                    }
                }
            }

    
            // Details on new sheet
            sheet = workbook.createSheet("Fee Details", 1)
            def sheetCycle1 = workbook.createSheet("Billing Cycle 1", 2)
            def sheetCycle2 = workbook.createSheet("Billing Cycle 2", 3)
            def sheetCycle3 = workbook.createSheet("Billing Cycle 3", 4)


            // define our header map
            header = [:]
            header.accountID = "Pershing Format"
            header.name = "Account Name"
            header.type = "Account Type"
            header.billingAccountID = "Billing Account"
            header.dateCreated = "Value Date"
            header.accountValue = "Market Value"
            header.weight = "Account Weight"
            header.fee = "Fee Amount"
            header.payPlan = "Advance/Arrears"
            header.repNum = "Rep #"

            //            writeExcel(response.outputStream, header, feeRunInstance.groups.accounts)

            // walk through our map and write out the headers
            header.eachWithIndex() { k, v, index ->
                // write out our headers
                sheet.addCell(new Label(index, 0, v.toString(), arial10Boldformat))
                sheet.setColumnView(index,16)
                sheetCycle1.addCell(new Label(index, 0, v.toString(), arial10Boldformat))
                sheetCycle1.setColumnView(index,16)
                sheetCycle2.addCell(new Label(index, 0, v.toString(), arial10Boldformat))
                sheetCycle2.setColumnView(index,16)
                sheetCycle3.addCell(new Label(index, 0, v.toString(), arial10Boldformat))
                sheetCycle3.setColumnView(index,16)
            }
            sheet.setColumnView(1,25); // sets column width for account name
            sheetCycle1.setColumnView(1,25); // sets column width for account name
            sheetCycle2.setColumnView(1,25); // sets column width for account name
            sheetCycle3.setColumnView(1,25); // sets column width for account name

            def rows = [ 1,1,1,1 ]
//            row = 1
            cell = 0
            def whichBilling = -1;
            feeRunInstance.feegroups.eachWithIndex() { fg, index ->
                whichBilling = -1;
                if(fg != null && fg.fees != null){
                    fg.fees.eachWithIndex() { fee, actIndex ->
                        thisAccount = com.kn.Accnt.findByAccountID(fee.accountID)
                        cell = 0
                        sheet.addCell(new Label(cell++, rows[0], fee.accountID))
                        sheet.addCell(new Label(cell++, rows[0], thisAccount.name))
                        sheet.addCell(new Label(cell++, rows[0], thisAccount.type.title))
                        sheet.addCell(new Label(cell++, rows[0], thisAccount.billingAccountID))
                        sheet.addCell(new DateTime(cell++, rows[0], fee.dateCreated, dateFormat))
                        sheet.addCell(new Number(cell++, rows[0], fee.marketValue,accountingFloatFormat))
                        sheet.addCell(new Number(cell++, rows[0], (fee.weight*100),floatFormat))
                        sheet.addCell(new Number(cell++, rows[0], fee.feeValue,accountingFloatFormat))
                        sheet.addCell(new Label(cell++, rows[0], thisAccount.payPlan))
                        sheet.addCell(new Label(cell++, rows[0], thisAccount.repNum.code))
                        rows[0]++

                        def tempSheet = null;
                        

                        if( thisAccount.billingSchedule?.selectedMonths?.contains(0)) {
                            whichBilling = 1;
                        } else if( thisAccount.billingSchedule?.selectedMonths?.contains(1)) {
                            whichBilling = 2;
                        } else if( thisAccount.billingSchedule?.selectedMonths?.contains(2)) {
                            whichBilling = 3;
                        }
                        switch (whichBilling) {
                            case 1:
                                tempSheet = sheetCycle1;
                                break;
                            case 2:
                                tempSheet = sheetCycle2;
                                break;
                            case 3:
                                tempSheet = sheetCycle3;
                                break;
                            default:
                                break;
                        }

                        if(tempSheet != null) {
                            cell = 0
                            tempSheet.addCell(new Label(cell++, rows[whichBilling], fee.accountID))
                            tempSheet.addCell(new Label(cell++, rows[whichBilling], thisAccount.name))
                            tempSheet.addCell(new Label(cell++, rows[whichBilling], thisAccount.type.title))
                            tempSheet.addCell(new Label(cell++, rows[whichBilling], thisAccount.billingAccountID))
                            tempSheet.addCell(new DateTime(cell++, rows[whichBilling], fee.dateCreated, dateFormat))
                            tempSheet.addCell(new Number(cell++, rows[whichBilling], fee.marketValue,accountingFloatFormat))
                            tempSheet.addCell(new Number(cell++, rows[whichBilling], (fee.weight*100),floatFormat))
                            tempSheet.addCell(new Number(cell++, rows[whichBilling], fee.feeValue,accountingFloatFormat))
                            tempSheet.addCell(new Label(cell++, rows[whichBilling], thisAccount.payPlan))
                            tempSheet.addCell(new Label(cell++, rows[whichBilling], thisAccount.repNum.code))

                            rows[whichBilling]++
                        }

                        
                    }
                }
                (0..1).each { rowIndex ->
                    // should do this with some sort of array of sheets but
                    // time is tight so brute forcing.
                    switch(rowIndex) {
                        case 0:
                            sheet.addCell(new Number(5, rows[rowIndex], fg.marketValue,accountingFloatFormat))
                            sheet.addCell(new Number(7, rows[rowIndex], fg.groupFee,accountingFloatFormat))
                            rows[rowIndex] += 2
                            break;
                        default:
                            switch (whichBilling) {
                                case 1:
                                    sheetCycle1.addCell(new Number(5, rows[whichBilling], fg.marketValue,accountingFloatFormat))
                                    sheetCycle1.addCell(new Number(7, rows[whichBilling], fg.groupFee,accountingFloatFormat))
                                    rows[whichBilling] += 2
                                    break;
                                case 2:
                                    sheetCycle2.addCell(new Number(5, rows[whichBilling], fg.marketValue,accountingFloatFormat))
                                    sheetCycle2.addCell(new Number(7, rows[whichBilling], fg.groupFee,accountingFloatFormat))
                                    rows[whichBilling] += 2
                                    break;
                                case 3:
                                    sheetCycle3.addCell(new Number(5, rows[whichBilling], fg.marketValue,accountingFloatFormat))
                                    sheetCycle3.addCell(new Number(7, rows[whichBilling], fg.groupFee,accountingFloatFormat))
                                    rows[whichBilling] += 2
                                    break;
                                default:
                                    break;
                            }
                            break;
                    }
                }

            }

            // close
            workbook.write()
            workbook.close()


        }
        flash.message = "Exported Excel file: NetEx-processed-${feeRunInstance.dateCreated}.xls"
        redirect( action:list)
    }

    def upload = {
        // get our multipart
        MultipartHttpServletRequest mpr = (MultipartHttpServletRequest)request;
        CommonsMultipartFile file = (CommonsMultipartFile) mpr.getFile("imgfile");
        if(file != null) {

            log.error("****** Beginning File Upload and Processing ******")

            // create our workbook
            Workbook workbook = Workbook.getWorkbook(file.inputStream)
            Sheet sheet = workbook.getSheet(0)
            // TODO  check that the sheet has two columns

            //        flash.message = "${sheet.rows} rows. ${sheet.columns} columns.<br>"
            def added = 0;
            def skipped = [];
            def accountNotFound = [];

            def feeRunInstance = new com.kn.FeeRun();
            def STARTING_ROW = 2;

            List feegroups = []
            Date valueDate = new Date();

            for (int x = STARTING_ROW; x < sheet.rows; x++ ) {
                def accountID = sheet.getCell(0,x).contents;
                def netWorth = sheet.getCell(1,x).contents;


                if(accountID != null && netWorth != null) {
//log.error("Account: ${accountID} and netWorth: ${netWorth}")

                    if(accountID.size() == 0 || netWorth.size() == 0 ) {
                        log.error("row ${(x + 1)} skipped during upload because accountID or netWorth was empty.");
                    } else {
                        def tempNetWorth = new BigDecimal(netWorth)
//log.error("1 tempNetWorth: ${tempNetWorth}")
//                        def newFee = new com.kn.Fee(accountID: accountID, marketValue: tempNetWorth, valueDate: valueDate).save(flush:true, failOnError: true)
//log.error("1.5 newFee: ${newFee}")
                        def thisAccount = com.kn.Accnt.findByAccountID(accountID);
//log.error("2")
                        if( thisAccount != null && thisAccount.groupAccount != null) {
//log.error("3")
                            def thisAccountsGroup = thisAccount.groupAccount;

                            def thisFeeGroup = feegroups.find{ it.group.name == thisAccountsGroup.name}
                            if(thisFeeGroup == null) {
//            log.error("4")

                                thisFeeGroup = new com.kn.FeeGroup(group:thisAccountsGroup)
                            }
//log.error("4.5")
                        def newFee = new com.kn.Fee(accountID: accountID, marketValue: tempNetWorth, valueDate: valueDate)//.save(flush:true, failOnError: true)
//log.error("4.75 newFee: ${newFee}")
                            thisFeeGroup.addToFees(newFee)//.save(flush:true, failOnError: true);
//log.error("5")

                            if( ! feegroups.contains(thisFeeGroup)) {
                                feegroups << thisFeeGroup;
                            }

                            added++;
                        } else {
                            accountNotFound += accountID
                            log.error("[${accountID}] or group could not be found during upload.");

                            if(feeRunInstance.orphanedAccounts == null){
                                feeRunInstance.orphanedAccounts = [:];
                            }
                            feeRunInstance.orphanedAccounts.put(accountID, netWorth);
                            
                            log.error("[${feeRunInstance.orphanedAccounts}]");
                        }
                    }

                } else {
                    skipped += (x + 1)
                    log.error("row ${(x + 1)} skipped during upload.");
                }

            }

            workbook.close()

            // test groups
            feegroups.each() {
                feeRunInstance.addToFeegroups(it);
            }
            if( feeRunInstance.save(flush:true, failOnError: true) ) {


                feeRunInstance.compileRun()

                // generate our flash message
                flash.message = "${added} fee entries(s) added.<br><br>"
                if (accountNotFound.size() > 0) {
                    flash.message += "<p>Accounts ${accountNotFound.join(', ')} were skipped because the account or account's group could not be found.</p>"
                }
                if (skipped.size() > 0) {
                    flash.message += "<p>Rows ${skipped.join(', ')} were skipped because they were incomplete or malformed.</p>"
                }

                log.error("****** Completed File Upload and Processing ******")

                redirect(action: "show", id: feeRunInstance.id)
            }
        } else {
            flash.message = "There was an error trying to upload the file. It could not be found on the request."
            log.error("uploaded file object was null")
        }


        //redirect(action: "show", id: userInstance.id)

        //        if(feeRunInstance != null && feeRunInstance.id != null) {
        //            redirect(action: "show", id: feeRunInstance.id)
        //            return [feeRunInstance: feeRunInstance]
        //        } else {
        //            redirect(action: "list", params: params)
        //        }
        
    }

    Closure saveClosure = { domainObj ->
        if(domainObj.save())
        println "Domain Object $domainObj Saved"
        else
        {
            println "Errors Found During Save of $domainObj!"
            println domainObj.errors.allErrors.each {
                println it.defaultMessage
            }
        }
    }

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [feeRunInstanceList: FeeRun.list(params), feeRunInstanceTotal: FeeRun.count()]
    }

    def create = {
        def feeRunInstance = new FeeRun()
        feeRunInstance.properties = params
        return [feeRunInstance: feeRunInstance]
    }

    def save = {
        def feeRunInstance = new FeeRun(params)
        if (feeRunInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'feeRun.label', default: 'FeeRun'), feeRunInstance.id])}"
            redirect(action: "show", id: feeRunInstance.id)
        }
        else {
            render(view: "create", model: [feeRunInstance: feeRunInstance])
        }
    }
    /**
    def show = {
    def feeRunInstance = FeeRun.get(params.id)
    if (!feeRunInstance) {
    flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feeRun.label', default: 'FeeRun'), params.id])}"
    redirect(action: "list")
    }
    else {
    [feeRunInstance: feeRunInstance]
    }
    }
     **/
    def edit = {
        def feeRunInstance = FeeRun.get(params.id)
        if (!feeRunInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feeRun.label', default: 'FeeRun'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [feeRunInstance: feeRunInstance]
        }
    }

    def update = {
        def feeRunInstance = FeeRun.get(params.id)
        if (feeRunInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (feeRunInstance.version > version) {
                    
                    feeRunInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'feeRun.label', default: 'FeeRun')] as Object[], "Another user has updated this FeeRun while you were editing")
                    render(view: "edit", model: [feeRunInstance: feeRunInstance])
                    return
                }
            }
            feeRunInstance.properties = params
            if (!feeRunInstance.hasErrors() && feeRunInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'feeRun.label', default: 'FeeRun'), feeRunInstance.id])}"
                redirect(action: "show", id: feeRunInstance.id)
            }
            else {
                render(view: "edit", model: [feeRunInstance: feeRunInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feeRun.label', default: 'FeeRun'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def feeRunInstance = FeeRun.get(params.id)
        if (feeRunInstance) {
            try {
                feeRunInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'feeRun.label', default: 'FeeRun'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'feeRun.label', default: 'FeeRun'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feeRun.label', default: 'FeeRun'), params.id])}"
            redirect(action: "list")
        }
    }

}
