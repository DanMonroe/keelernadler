package com.kn

class Accnt {

    String name

    static belongsTo = [ groupAccount:com.kn.GroupAccount ]

    String accountID
    String billingAccountID
    AccountType type
    String payPlan
    RepNum repNum
    BillingSchedule billingSchedule
    String address1
    String address2
    String city
    String state
    String zip
    String phone1
    String phone2

    static constraints = {
        accountID(unique:true)
        billingAccountID(unique:false)
        name(blank:false, maxSize:50)
        type(nullable:false)
        payPlan(inList:["Advance", "Arrears"])
        repNum(nullable:true)
        billingSchedule(nullable:true)
        address1(nullable:true)
        address2(nullable:true)
        city(nullable:true)
        state(nullable:true)
        zip(nullable:true)
        phone1(nullable:true)
        phone2(nullable:true)
   }

    String toString() {
        "${this.name}"
    }

    def String buildAddress() {
        String addyStr = ""
        if(this.address1) {
            addyStr += "${this.address1}<br>"
        }
        if(this.address2) {
            addyStr += "${this.address2}<br>"
        }
        addyStr += "${this.city ?: ""},${this.state?: ""} ${this.zip?: ""}"

        return addyStr
    }
}
