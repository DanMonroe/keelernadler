package com.kn

class MonthsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [monthsInstanceList: Months.list(params), monthsInstanceTotal: Months.count()]
    }

    def create = {
        def monthsInstance = new Months()
        monthsInstance.properties = params
        return [monthsInstance: monthsInstance]
    }

    def save = {
        def monthsInstance = new Months(params)
        if (monthsInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'months.label', default: 'Months'), monthsInstance.id])}"
            redirect(action: "show", id: monthsInstance.id)
        }
        else {
            render(view: "create", model: [monthsInstance: monthsInstance])
        }
    }

    def show = {
        def monthsInstance = Months.get(params.id)
        if (!monthsInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'months.label', default: 'Months'), params.id])}"
            redirect(action: "list")
        }
        else {
            [monthsInstance: monthsInstance]
        }
    }

    def edit = {
        def monthsInstance = Months.get(params.id)
        if (!monthsInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'months.label', default: 'Months'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [monthsInstance: monthsInstance]
        }
    }

    def update = {
        def monthsInstance = Months.get(params.id)
        if (monthsInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (monthsInstance.version > version) {
                    
                    monthsInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'months.label', default: 'Months')] as Object[], "Another user has updated this Months while you were editing")
                    render(view: "edit", model: [monthsInstance: monthsInstance])
                    return
                }
            }
            monthsInstance.properties = params
            if (!monthsInstance.hasErrors() && monthsInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'months.label', default: 'Months'), monthsInstance.id])}"
                redirect(action: "show", id: monthsInstance.id)
            }
            else {
                render(view: "edit", model: [monthsInstance: monthsInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'months.label', default: 'Months'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def monthsInstance = Months.get(params.id)
        if (monthsInstance) {
            try {
                monthsInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'months.label', default: 'Months'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'months.label', default: 'Months'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'months.label', default: 'Months'), params.id])}"
            redirect(action: "list")
        }
    }
}
