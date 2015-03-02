package com.kn

class BillingScheduleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [billingScheduleInstanceList: BillingSchedule.list(params), billingScheduleInstanceTotal: BillingSchedule.count()]
    }

    def create = {
        def billingScheduleInstance = new BillingSchedule()
        billingScheduleInstance.properties = params
        // Added to save selected months.
        // needed to covnert string to int
        def List newMonths = []
        params.selectedMonths?.each() { thisNum ->
            newMonths << thisNum.toInteger().intValue()
        }
        billingScheduleInstance.selectedMonths = newMonths

        return [billingScheduleInstance: billingScheduleInstance]
    }

    def save = {
        def billingScheduleInstance = new BillingSchedule(params)
        // Added to save selected months.
        // needed to covnert string to int
        def List newMonths = []
        params.selectedMonths?.each() { thisNum ->
            newMonths << thisNum.toInteger().intValue()
        }
        billingScheduleInstance.selectedMonths = newMonths
        
        if (billingScheduleInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'billingSchedule.label', default: 'BillingSchedule'), billingScheduleInstance.id])}"
            redirect(action: "show", id: billingScheduleInstance.id)
        }
        else {
            render(view: "create", model: [billingScheduleInstance: billingScheduleInstance])
        }
    }

    def show = {
        def billingScheduleInstance = BillingSchedule.get(params.id)
        if (!billingScheduleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'billingSchedule.label', default: 'BillingSchedule'), params.id])}"
            redirect(action: "list")
        }
        else {
            [billingScheduleInstance: billingScheduleInstance]
        }
    }

    def edit = {
        def billingScheduleInstance = BillingSchedule.get(params.id)
        if (!billingScheduleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'billingSchedule.label', default: 'BillingSchedule'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [billingScheduleInstance: billingScheduleInstance]
        }
    }

    def update = {
        def billingScheduleInstance = BillingSchedule.get(params.id)
        if (billingScheduleInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (billingScheduleInstance.version > version) {
                    
                    billingScheduleInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'billingSchedule.label', default: 'BillingSchedule')] as Object[], "Another user has updated this BillingSchedule while you were editing")
                    render(view: "edit", model: [billingScheduleInstance: billingScheduleInstance])
                    return
                }
            }
            billingScheduleInstance.properties = params

            // Added to save selected months.
            // needed to covnert string to int
            def List newMonths = []
            params.selectedMonths?.each() { thisNum ->
                newMonths << thisNum.toInteger().intValue()
            }
            billingScheduleInstance.selectedMonths = newMonths
            if (!billingScheduleInstance.hasErrors() && billingScheduleInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'billingSchedule.label', default: 'BillingSchedule'), billingScheduleInstance.id])}"
                redirect(action: "show", id: billingScheduleInstance.id)
            }
            else {
                render(view: "edit", model: [billingScheduleInstance: billingScheduleInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'billingSchedule.label', default: 'BillingSchedule'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def billingScheduleInstance = BillingSchedule.get(params.id)
        if (billingScheduleInstance) {
            try {
                billingScheduleInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'billingSchedule.label', default: 'BillingSchedule'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'billingSchedule.label', default: 'BillingSchedule'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'billingSchedule.label', default: 'BillingSchedule'), params.id])}"
            redirect(action: "list")
        }
    }
}
