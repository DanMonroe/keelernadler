package com.kn

class AccountTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [accountTypeInstanceList: AccountType.list(params), accountTypeInstanceTotal: AccountType.count()]
    }

    def create = {
        def accountTypeInstance = new AccountType()
        accountTypeInstance.properties = params
        return [accountTypeInstance: accountTypeInstance]
    }

    def save = {
        def accountTypeInstance = new AccountType(params)
        if (accountTypeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'accountType.label', default: 'AccountType'), accountTypeInstance.id])}"
            redirect(action: "show", id: accountTypeInstance.id)
        }
        else {
            render(view: "create", model: [accountTypeInstance: accountTypeInstance])
        }
    }

    def show = {
        def accountTypeInstance = AccountType.get(params.id)
        if (!accountTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'accountType.label', default: 'AccountType'), params.id])}"
            redirect(action: "list")
        }
        else {
            [accountTypeInstance: accountTypeInstance]
        }
    }

    def edit = {
        def accountTypeInstance = AccountType.get(params.id)
        if (!accountTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'accountType.label', default: 'AccountType'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [accountTypeInstance: accountTypeInstance]
        }
    }

    def update = {
        def accountTypeInstance = AccountType.get(params.id)
        if (accountTypeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (accountTypeInstance.version > version) {
                    
                    accountTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'accountType.label', default: 'AccountType')] as Object[], "Another user has updated this AccountType while you were editing")
                    render(view: "edit", model: [accountTypeInstance: accountTypeInstance])
                    return
                }
            }
            accountTypeInstance.properties = params
            if (!accountTypeInstance.hasErrors() && accountTypeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'accountType.label', default: 'AccountType'), accountTypeInstance.id])}"
                redirect(action: "show", id: accountTypeInstance.id)
            }
            else {
                render(view: "edit", model: [accountTypeInstance: accountTypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'accountType.label', default: 'AccountType'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def accountTypeInstance = AccountType.get(params.id)
        if (accountTypeInstance) {
            try {
                accountTypeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'accountType.label', default: 'AccountType'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'accountType.label', default: 'AccountType'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'accountType.label', default: 'AccountType'), params.id])}"
            redirect(action: "list")
        }
    }
}
