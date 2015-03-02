package com.kn

class GroupAccount {
    List accounts
    static hasMany = [ accounts:com.kn.Accnt ]
    String name
//    BigDecimal groupAccountValue = 0.00
//    BigDecimal groupFee = 0.00
    def com.kn.RateSchedule rateSchedule;
    boolean invoiced = false;

    static constraints = {
        name(unique: true, blank:false, maxSize:50)
        accounts(nullable:true)
        rateSchedule(nullable:true)
        invoiced()
//        groupAccountValue(scale:2)
//        groupFee(scale:2)
        }

    String toString() {
        "${this.name}"
    }
}
