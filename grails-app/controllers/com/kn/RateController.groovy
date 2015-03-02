package com.kn

class RateController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
//        Rate.list().each() {
//            println it.rate
//        }

        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [rateInstanceList: Rate.list(params), rateInstanceTotal: Rate.count()]
    }

    def create = {
        def rateInstance = new Rate()
        rateInstance.properties = params
        return [rateInstance: rateInstance]
    }

    def save = {
        def rateInstance = new Rate(params)
        if (rateInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'rate.label', default: 'Rate'), rateInstance.id])}"
            redirect(action: "show", id: rateInstance.id)
        }
        else {
            render(view: "create", model: [rateInstance: rateInstance])
        }
    }

    def show = {
        def rateInstance = Rate.get(params.id)
        if (!rateInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rate.label', default: 'Rate'), params.id])}"
            redirect(action: "list")
        }
        else {
            [rateInstance: rateInstance]
        }
    }

    def edit = {
        def rateInstance = Rate.get(params.id)
        if (!rateInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rate.label', default: 'Rate'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [rateInstance: rateInstance]
        }
    }

    def update = {
        def rateInstance = Rate.get(params.id)
        if (rateInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (rateInstance.version > version) {
                    
                    rateInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'rate.label', default: 'Rate')] as Object[], "Another user has updated this Rate while you were editing")
                    render(view: "edit", model: [rateInstance: rateInstance])
                    return
                }
            }
            rateInstance.properties = params
            if (!rateInstance.hasErrors() && rateInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'rate.label', default: 'Rate'), rateInstance.id])}"
                redirect(action: "show", id: rateInstance.id)
            }
            else {
                render(view: "edit", model: [rateInstance: rateInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rate.label', default: 'Rate'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def rateInstance = Rate.get(params.id)
        if (rateInstance) {
            try {
                rateInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'rate.label', default: 'Rate'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'rate.label', default: 'Rate'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rate.label', default: 'Rate'), params.id])}"
            redirect(action: "list")
        }
    }
}
