package com.kn;

import java.io.File;
import java.util.Date;
import jxl.*;

import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile
class UploadController {

    def scaffold = Upload

    //def index = { }

        def upload = {
        // get our multipart
        MultipartHttpServletRequest mpr = (MultipartHttpServletRequest)request;
        CommonsMultipartFile file = (CommonsMultipartFile) mpr.getFile("file");

        // create our workbook
        Workbook workbook = Workbook.getWorkbook(file.inputStream)
        Sheet sheet = workbook.getSheet(0)

        // TODO  check that the sheet has two columns

        flash.message = "${sheet.rows} rows. ${sheet.columns} columns.<br>"
        def added = 0;
        def skipped = [];

        def feeRun = new com.kn.FeeRun();

        def STARTING_ROW = 2;

        List feegroups = []

        for (int x = STARTING_ROW; x < sheet.rows; x++ ) {
            def accountID = sheet.getCell(0,x).contents;
            def netWorth = sheet.getCell(1,x).contents;


            if(accountID != null && netWorth != null) {


                def newFee = new com.kn.Fee(accountID: accountID, marketValue: netWorth); //.save(flush:true, failOnError: true)



                def thisAccount = com.kn.Accnt.findByAccountID(accountID);
                if( thisAccount != null ) {
                    def thisAccountsGroup = thisAccount.groupAccount;
                    def thisFeeGroup = com.kn.FeeGroup.findByGroup(thisAccountsGroup)
                    if(thisFeeGroup == null) {
                        println "Couldn't find group - creating a new one.";
                        thisFeeGroup = new com.kn.FeeGroup(group:thisAccountsGroup)
                    }
                    thisFeeGroup.addToFees(newFee).save(flush:true, failOnError: true);

                    if( ! feegroups.contains(thisFeeGroup)) {
                        feegroups << thisFeeGroup;
                    }


                    flash.message += "[${accountID}  ${netWorth} ${thisAccount.name} ${thisAccountsGroup.name}]<br>"

                    added++;
                }

            } else {
                skipped += (x + 1)
            }

        }

        // test groups
        feegroups.each() {
            feeRun.addToFeegroups(it);

            flash.message += "[[${it.group.name}]]<br>"
            it.fees.eachWithIndex() { f, i  ->
                flash.message += "  ${i}: ${f.accountID} ${f.feeValue}<br>"
            }
        }
        feeRun.save(flush:true, failOnError: true)

        feeRun.compileRun()

//        for (int x = 1; x < sheet.rows; x++ ) {
//            def feeName = sheet.getCell(1,x).contents;
//            def accountType = sheet.getCell(2,x).contents;
//            def billingAccountID = sheet.getCell(3,x).contents;
//            def accountValue = sheet.getCell(5,x).contents;
//            if(feeName == null || feeName=="") {
//                // do nothing
//            } else if ( true) {
////true
//
//
//
//            try {
//
//            def convertedValue = new BigDecimal( accountValue )
//
//            def thisFee = new Fee(name:feeName,
//                billingAccountID:billingAccountID,
//                accountValue:convertedValue
//            );
//println thisFee.name + " - " + thisFee.billingAccountID + " value: " + accountValue
//
//            saveClosure(thisFee)
//
//                } catch(e) {
//                    println e
//                }
//
////            if(!thisFee.save(flush:true)) {
////                throw new Exception("Save failed")
////            }
//
////                new Fee(
////                        "name":feeName,
////                        "accountType":accountType,
////                        "billingAccountID":billingAccountID,
////                        "accountValue":accountValue,
////                        ).save()
//
//                flash.message += feeName;
//                flash.message += " ";
//
//                added++;
//            } else {
//                skipped += (x + 1)
//            }
//        }

/////////////////////////////////////////////////////

//        for (int r = 3; r < sheet.rows; r++) {
//            // get our fields
//            def top = sheet.getCell(0, r).contents
//
//            def bottom = sheet.getCell(1, r).contents
//            if (bottom == "") bottom = null
//
//            def number = sheet.getCell(2, r).contents
//            if (number == "") number = 1
//
//            def spacing = sheet.getCell(3, r).contents
//            if (spacing == "") spacing = 0.0
//
//            def type = sheet.getCell(4, r).contents
//            def notes = sheet.getCell(5, r).contents
//
//            // check that we got a top and a type
//            if (top == null || top == "") {
//                // do nothing
////            }
////            else if ((new SampleRequest(
////    "investigator":user,
////    "hole":hole,
////    "sampleGroup":group,
////    "top":top,
////    "bottom":bottom,
////    "sampleType":type,
////    "samplesRequested":number,
////    "sampleSpacing":spacing,
////    "notes":notes)).save()) {
////                added++
//            } else {
//                skipped += (r + 1)
//            }
//        }

//        for(int r = 0; r < sheet.rows; r++ ) {
//            for(int c = 0; c < sheet.columns; c++) {
//                flash.message += sheet.getCell(c,r).contents
//                flash.message += " "
//            }
//            flash.message += "<br>"
//        }



        workbook.close()

        // generate our flash message
        flash.message += "<p>${added} fee entries(s) added.</p>"
//        flash.message = "${added} fee entries(s) added."
        if (skipped.size() > 0) {
            flash.message += "  Rows ${skipped.join(', ')} were skipped because they were incomplete or malformed"
        }



        redirect(controller:"fee", action: "list")
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
}
