package com.kn

class RateScheduleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [rateScheduleInstanceList: RateSchedule.list(params), rateScheduleInstanceTotal: RateSchedule.count()]
    }

    def create = {
        def rateScheduleInstance = new RateSchedule()
        rateScheduleInstance.properties = params
        return [rateScheduleInstance: rateScheduleInstance]
    }

    def save = {
        def rateScheduleInstance = new RateSchedule(params)
        if (rateScheduleInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'rateSchedule.label', default: 'RateSchedule'), rateScheduleInstance.id])}"
            redirect(action: "show", id: rateScheduleInstance.id)
        }
        else {
            render(view: "create", model: [rateScheduleInstance: rateScheduleInstance])
        }
    }

    def show = {
        def rateScheduleInstance = RateSchedule.get(params.id)
        if (!rateScheduleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rateSchedule.label', default: 'RateSchedule'), params.id])}"
            redirect(action: "list")
        }
        else {
            [rateScheduleInstance: rateScheduleInstance]
        }
    }

    def edit = {
        def rateScheduleInstance = RateSchedule.get(params.id)
        if (!rateScheduleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rateSchedule.label', default: 'RateSchedule'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [rateScheduleInstance: rateScheduleInstance]
        }
    }

    def update = {
        def rateScheduleInstance = RateSchedule.get(params.id)
        if (rateScheduleInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (rateScheduleInstance.version > version) {
                    
                    rateScheduleInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'rateSchedule.label', default: 'RateSchedule')] as Object[], "Another user has updated this RateSchedule while you were editing")
                    render(view: "edit", model: [rateScheduleInstance: rateScheduleInstance])
                    return
                }
            }
            rateScheduleInstance.properties = params
            if (!rateScheduleInstance.hasErrors() && rateScheduleInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'rateSchedule.label', default: 'RateSchedule'), rateScheduleInstance.id])}"
                redirect(action: "show", id: rateScheduleInstance.id)
            }
            else {
                render(view: "edit", model: [rateScheduleInstance: rateScheduleInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rateSchedule.label', default: 'RateSchedule'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def rateScheduleInstance = RateSchedule.get(params.id)
        if (rateScheduleInstance) {
            try {
                rateScheduleInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'rateSchedule.label', default: 'RateSchedule'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'rateSchedule.label', default: 'RateSchedule'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rateSchedule.label', default: 'RateSchedule'), params.id])}"
            redirect(action: "list")
        }
    }
}
