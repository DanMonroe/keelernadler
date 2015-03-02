package com.kn

class GroupAccountController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [groupAccountInstanceList: GroupAccount.list(params), groupAccountInstanceTotal: GroupAccount.count()]
    }

    def create = {
        def groupAccountInstance = new GroupAccount()
        groupAccountInstance.properties = params
        return [groupAccountInstance: groupAccountInstance]
    }

    def save = {
        def groupAccountInstance = new GroupAccount(params)
        if (groupAccountInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'groupAccount.label', default: 'GroupAccount'), groupAccountInstance.id])}"
            redirect(action: "show", id: groupAccountInstance.id)
        }
        else {
            render(view: "create", model: [groupAccountInstance: groupAccountInstance])
        }
    }

    def show = {
        def groupAccountInstance = GroupAccount.get(params.id)
        if (!groupAccountInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'groupAccount.label', default: 'GroupAccount'), params.id])}"
            redirect(action: "list")
        }
        else {
            [groupAccountInstance: groupAccountInstance]
        }
    }

    def edit = {
        def groupAccountInstance = GroupAccount.get(params.id)
        if (!groupAccountInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'groupAccount.label', default: 'GroupAccount'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [groupAccountInstance: groupAccountInstance]
        }
    }

    def update = {
        def groupAccountInstance = GroupAccount.get(params.id)
        if (groupAccountInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (groupAccountInstance.version > version) {
                    
                    groupAccountInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'groupAccount.label', default: 'GroupAccount')] as Object[], "Another user has updated this GroupAccount while you were editing")
                    render(view: "edit", model: [groupAccountInstance: groupAccountInstance])
                    return
                }
            }
            groupAccountInstance.properties = params
            if (!groupAccountInstance.hasErrors() && groupAccountInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'groupAccount.label', default: 'GroupAccount'), groupAccountInstance.id])}"
                redirect(action: "show", id: groupAccountInstance.id)
            }
            else {
                render(view: "edit", model: [groupAccountInstance: groupAccountInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'groupAccount.label', default: 'GroupAccount'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def groupAccountInstance = GroupAccount.get(params.id)
        if (groupAccountInstance) {
            try {
                groupAccountInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'groupAccount.label', default: 'GroupAccount'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'groupAccount.label', default: 'GroupAccount'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'groupAccount.label', default: 'GroupAccount'), params.id])}"
            redirect(action: "list")
        }
    }
}
