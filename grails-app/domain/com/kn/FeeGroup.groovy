package com.kn

class FeeGroup {
    List fees
    static hasMany = [ fees:com.kn.Fee ]

    static belongsTo = [ feeRun:com.kn.FeeRun ]

    GroupAccount group
    BigDecimal marketValue = 0.00
    BigDecimal groupFee = 0.00

    static constraints = {
        fees(nullable:true)
        group(nullable:true)
        marketValue(scale:2)
        groupFee(scale:2)
        feeRun(nullable:true)
    }

    def resetAllFees() {
        this.fees.each() {
            it.feeValue = 0.00;
            it.weight = 0.00;
        }
    }
}
