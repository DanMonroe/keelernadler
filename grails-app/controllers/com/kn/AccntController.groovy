package com.kn

import jxl.*
import jxl.write.*
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile

class AccntController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def importaccounts = {
        println "importing3"
                // get our multipart
        MultipartHttpServletRequest mpr = (MultipartHttpServletRequest)request;
        CommonsMultipartFile file = (CommonsMultipartFile) mpr.getFile("accountfile");
        if(file != null) {

            log.error("****** Beginning Accounts Import Upload and Processing ******")

            Workbook workbook = Workbook.getWorkbook(file.inputStream)
            Sheet sheet = workbook.getSheet(0)
            def added = 0;
            def skipped = [];
            def accountExists = [];
            def STARTING_ROW = 1;
            def EXPECTED_COLUMNS = 8;
            def startNewGroup = true;

            // Schedule 1 should accomodate all accounts
            def rateSch = com.kn.RateSchedule.findByName("Schedule 1");

            if(sheet.columns != EXPECTED_COLUMNS) {
                flash.message = "Invalid number of columns in account import file.  Should be ${EXPECTED_COLUMNS}, found ${sheet.columns}."
            } else {
                println "\nNew Group:"
                def thisGroup = null;
                for (int x = STARTING_ROW; x < sheet.rows; x++ ) {
                    def thisAccountObj = null;

                    def accntID = new String(sheet.getCell(0,x).contents).trim();
                    def accntName = new String(sheet.getCell(1,x).contents).trim();
                    def accntType = new String(sheet.getCell(2,x).contents).trim();
                    def accntBillingID = new String(sheet.getCell(3,x).contents).trim();
                    def accntPayPlan = new String(sheet.getCell(4,x).contents).trim().toLowerCase();
                    def accntRepNum = new String(sheet.getCell(5,x).contents).trim();
                    def accntBillingCycle = new String(sheet.getCell(6,x).contents).trim();
                    def accntInvoiced = new String(sheet.getCell(7,x).contents).trim();

                    boolean bPayInAdvance = true;
                    int whichBillingSchedule = 0;
                    boolean bInvoiced = false;

//println "this row account: ${accntID}"
                    if(accntID.length()==0) {
                        startNewGroup = true;
                        println "\nNew Group:"
                        if(thisGroup != null) {
                            thisGroup.save(flush:true, failOnError: true)
println "    - thisGroup: ${thisGroup.name}"
                        }
                        thisGroup = null;
                    } else {
                        def testAccnt = com.kn.Accnt.findByAccountID(accntID);
                        if(testAccnt == null) {
//                            println "  account does not exist yet"
                            if(startNewGroup) {
                                startNewGroup = false
                                thisGroup = new com.kn.GroupAccount(name:accntName, rateSchedule: rateSch)
                            }
                            if("indv".equals(accntType.toLowerCase())) {
                                accntType = "Individual";
                            }
                            def type = com.kn.AccountType.findByTitleIlike("%" + accntType + "%");

                            bPayInAdvance = (accntPayPlan.indexOf("adv") >= 0);
                            
                            def whichRepresentative = com.kn.RepNum.findByCode(accntRepNum);

                            def billCycle = null;
                            if("1".equals(accntBillingCycle)) {
                                billCycle = com.kn.BillingSchedule.findByName("Schedule 1");
                            } else if("2".equals(accntBillingCycle)) {
                                billCycle = com.kn.BillingSchedule.findByName("Schedule 2");
                            } else if("3".equals(accntBillingCycle)) {
                                billCycle = com.kn.BillingSchedule.findByName("Schedule 3");
                            }

                            bInvoiced = accntInvoiced.length() > 0;

                            println "   account info: id [${accntID}] name [${accntName}] type [${type?.title}] billingID [${accntBillingID}] advance? [${bPayInAdvance}] rep [${whichRepresentative?.owner}] billing schedule [${billCycle?.name}] Invoiced [${bInvoiced}] "

                            thisAccountObj = new com.kn.Accnt(name:accntName,accountID:accntID,billingAccountID:accntBillingID,type:type, payPlan: bPayInAdvance?"Advance":"Arrears", billingSchedule:billCycle, repNum: whichRepresentative)//.save(flush:true, failOnError: true)
println "    - thisAccountObj: ${thisAccountObj.name}"
                            if(thisGroup != null) {
                                thisGroup.addToAccounts(thisAccountObj)
                            }

                        } else {
                            accountExists += accntID
                            log.error("row ${accntID} skipped during account import - Account already exists.");
                        }
                    }
                }
                if(thisGroup != null) {
                    thisGroup.save(flush:true, failOnError: true)
println "    - thisGroup: ${thisGroup.name}"
                }

               // generate our flash message
                flash.message = "${added} account(s) added.<br><br>"
                if (accountExists.size() > 0) {
                    flash.message += "<p>Accounts ${accountExists.join(', ')} were skipped because the account already exists.</p>"
                }
                if (skipped.size() > 0) {
                    flash.message += "<p>Rows ${skipped.join(', ')} were skipped because they were incomplete or malformed.</p>"
                }
            }

            workbook.close();

            log.error("****** Completed Accounts Import Upload and Processing ******")

            redirect(action: "list")
        } else {
            flash.message = "There was an error trying to upload the file. It could not be found on the request."
            log.error("uploaded account import file object was null")
        }

    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        [accntInstanceList: Accnt.list(params), accntInstanceTotal: Accnt.count()]
    }

    def create = {
        def accntInstance = new Accnt()
        accntInstance.properties = params
        return [accntInstance: accntInstance]
    }

    def save = {
        def accntInstance = new Accnt(params)

        // http://jira.codehaus.org/browse/GRAILS-3783
        // Grails GRAILS-2986 org.hibernate.PropertyValueException -- null or transient Backref on List collection
        def accountGroup = accntInstance.groupAccount
        accountGroup.addToAccounts(accntInstance)

        if (accntInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'accnt.label', default: 'Accnt'), accntInstance.id])}"
            redirect(action: "show", id: accntInstance.id)
        }
        else {
            render(view: "create", model: [accntInstance: accntInstance])
        }
    }

    def show = {
        def accntInstance = Accnt.get(params.id)
        if (!accntInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'accnt.label', default: 'Accnt'), params.id])}"
            redirect(action: "list")
        }
        else {
            [accntInstance: accntInstance]
        }
    }

    def edit = {
        def accntInstance = Accnt.get(params.id)
        if (!accntInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'accnt.label', default: 'Accnt'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [accntInstance: accntInstance]
        }
    }

    def update = {
        def accntInstance = Accnt.get(params.id)
        if (accntInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (accntInstance.version > version) {
                    
                    accntInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'accnt.label', default: 'Accnt')] as Object[], "Another user has updated this Accnt while you were editing")
                    render(view: "edit", model: [accntInstance: accntInstance])
                    return
                }
            }
            accntInstance.properties = params
            if (!accntInstance.hasErrors() && accntInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'accnt.label', default: 'Accnt'), accntInstance.id])}"
                redirect(action: "show", id: accntInstance.id)
            }
            else {
                render(view: "edit", model: [accntInstance: accntInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'accnt.label', default: 'Accnt'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def accntInstance = Accnt.get(params.id)
        if (accntInstance) {
            try {
                accntInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'accnt.label', default: 'Accnt'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'accnt.label', default: 'Accnt'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'accnt.label', default: 'Accnt'), params.id])}"
            redirect(action: "list")
        }
    }
}
