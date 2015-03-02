package com.kn

class FeeGroupController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [feeGroupInstanceList: FeeGroup.list(params), feeGroupInstanceTotal: FeeGroup.count()]
    }

    def create = {
        def feeGroupInstance = new FeeGroup()
        feeGroupInstance.properties = params
        return [feeGroupInstance: feeGroupInstance]
    }

    def save = {
        def feeGroupInstance = new FeeGroup(params)
        if (feeGroupInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'feeGroup.label', default: 'FeeGroup'), feeGroupInstance.id])}"
            redirect(action: "show", id: feeGroupInstance.id)
        }
        else {
            render(view: "create", model: [feeGroupInstance: feeGroupInstance])
        }
    }

    def show = {
        def feeGroupInstance = FeeGroup.get(params.id)
        if (!feeGroupInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feeGroup.label', default: 'FeeGroup'), params.id])}"
            redirect(action: "list")
        }
        else {
            [feeGroupInstance: feeGroupInstance]
        }
    }

    def edit = {
        def feeGroupInstance = FeeGroup.get(params.id)
        if (!feeGroupInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feeGroup.label', default: 'FeeGroup'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [feeGroupInstance: feeGroupInstance]
        }
    }

    def update = {
        def feeGroupInstance = FeeGroup.get(params.id)
        if (feeGroupInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (feeGroupInstance.version > version) {
                    
                    feeGroupInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'feeGroup.label', default: 'FeeGroup')] as Object[], "Another user has updated this FeeGroup while you were editing")
                    render(view: "edit", model: [feeGroupInstance: feeGroupInstance])
                    return
                }
            }
            feeGroupInstance.properties = params
            if (!feeGroupInstance.hasErrors() && feeGroupInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'feeGroup.label', default: 'FeeGroup'), feeGroupInstance.id])}"
                redirect(action: "show", id: feeGroupInstance.id)
            }
            else {
                render(view: "edit", model: [feeGroupInstance: feeGroupInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feeGroup.label', default: 'FeeGroup'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def feeGroupInstance = FeeGroup.get(params.id)
        if (feeGroupInstance) {
            try {
                feeGroupInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'feeGroup.label', default: 'FeeGroup'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'feeGroup.label', default: 'FeeGroup'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feeGroup.label', default: 'FeeGroup'), params.id])}"
            redirect(action: "list")
        }
    }
}
