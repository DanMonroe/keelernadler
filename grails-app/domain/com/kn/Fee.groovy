package com.kn

class Fee {

    String accountID
    Date valueDate
    BigDecimal marketValue
    BigDecimal feeValue
    BigDecimal weight

    Date dateCreated
    Date lastUpdated

    static belongsTo = [ feegroup:com.kn.FeeGroup ]

    static constraints = {
        accountID(unique:false, nullable:false)
        marketValue(nullable:true, scale:2)
        valueDate(nullable:true)
        feeValue(blank:true, nullable:true, scale:2)
        weight(blank:true, nullable:true, min:0.00, max:100.00)
        feegroup(nullable:true)
    }

    def determineWeightAndFee(BigDecimal feeGroupMarketValue, BigDecimal groupFee) {
        if(feeValue == null) {
            feeValue = 0.00;
        }
        if(weight == null) {
            weight = 0.00;
        }
        if(feeGroupMarketValue > 0) {
            weight = marketValue / feeGroupMarketValue;
        }
        BigDecimal thisFeeAmount = (groupFee * weight);

        def thisAccount = com.kn.Accnt.findByAccountID(accountID)
        if(thisAccount != null) {
            if(thisAccount.billingAccountID != null && (thisAccount.billingAccountID != thisAccount.accountID) ) {
                log.info("  Different billing account: $thisAccount.accountID $thisAccount.billingAccountID")
                def billedFee = com.kn.Fee.findByAccountID(thisAccount.billingAccountID)
                if(billedFee != null){
                    if(billedFee.feeValue == null) {
                        billedFee.feeValue = 0.00;
                    }
                    log.info("    billing fee prior: $billedFee.feeValue");
                    billedFee.feeValue += thisFeeAmount.setScale(2, BigDecimal.ROUND_HALF_DOWN);
                    billedFee.save(flush:true);
                    log.info("    billing fee after: $billedFee.feeValue");
                } else {
                    log.error( "Could not find billed fee by AccountID: $thisAccount.billingAccountID");
                }
            } else {
                log.info("  Same billing account: $thisAccount.accountID")
                log.info( "    billing fee prior: $feeValue");
                feeValue += thisFeeAmount.setScale(2, BigDecimal.ROUND_HALF_DOWN);
                log.info( "    billing fee after: $feeValue");
            }
        } else {
            log.error( "Could not find by AccountID: $accountID" );
        }



        
    }
}
