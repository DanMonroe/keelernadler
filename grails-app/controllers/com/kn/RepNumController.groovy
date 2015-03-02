package com.kn

class RepNumController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [repNumInstanceList: RepNum.list(params), repNumInstanceTotal: RepNum.count()]
    }

    def create = {
        def repNumInstance = new RepNum()
        repNumInstance.properties = params
        return [repNumInstance: repNumInstance]
    }

    def save = {
        def repNumInstance = new RepNum(params)
        if (repNumInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'repNum.label', default: 'RepNum'), repNumInstance.id])}"
            redirect(action: "show", id: repNumInstance.id)
        }
        else {
            render(view: "create", model: [repNumInstance: repNumInstance])
        }
    }

    def show = {
        def repNumInstance = RepNum.get(params.id)
        if (!repNumInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'repNum.label', default: 'RepNum'), params.id])}"
            redirect(action: "list")
        }
        else {
            [repNumInstance: repNumInstance]
        }
    }

    def edit = {
        def repNumInstance = RepNum.get(params.id)
        if (!repNumInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'repNum.label', default: 'RepNum'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [repNumInstance: repNumInstance]
        }
    }

    def update = {
        def repNumInstance = RepNum.get(params.id)
        if (repNumInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (repNumInstance.version > version) {
                    
                    repNumInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'repNum.label', default: 'RepNum')] as Object[], "Another user has updated this RepNum while you were editing")
                    render(view: "edit", model: [repNumInstance: repNumInstance])
                    return
                }
            }
            repNumInstance.properties = params
            if (!repNumInstance.hasErrors() && repNumInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'repNum.label', default: 'RepNum'), repNumInstance.id])}"
                redirect(action: "show", id: repNumInstance.id)
            }
            else {
                render(view: "edit", model: [repNumInstance: repNumInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'repNum.label', default: 'RepNum'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def repNumInstance = RepNum.get(params.id)
        if (repNumInstance) {
            try {
                repNumInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'repNum.label', default: 'RepNum'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'repNum.label', default: 'RepNum'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'repNum.label', default: 'RepNum'), params.id])}"
            redirect(action: "list")
        }
    }
}
