package com.kn

class FeeRun {
    List feegroups
    static hasMany = [feegroups:com.kn.FeeGroup]
    Map orphanedAccounts = [:]
    Date dateCreated
    Date lastUpdated

    static constraints = {
        orphanedAccounts (nullable:true)
    }

    def compileRun() {
        this.feegroups.each() { feegrp ->
            feegrp.marketValue = 0.00
            log.info( "$feegrp.group.name marketValue = [$feegrp.marketValue]" )

            // sum all values for the group - make this a method in GroupAccount
            feegrp.fees.each() { fee ->
                feegrp.marketValue += fee.marketValue

                // also initialize fee.  should be somewhere else
//                fee.feeValue = 0.00
            }
            log.info( "$feegrp.group.name value after summation = [$feegrp.marketValue]" )

            // figure out total fee for the group
            feegrp.groupFee = 0.00
            log.info( "initial $feegrp.group.name groupFee = [$feegrp.groupFee]" )

            if(feegrp.group != null && feegrp.group.rateSchedule != null ) {
                def tempGroupAccountValue = feegrp.marketValue;

                feegrp.group.rateSchedule.rates.each() { thisRate ->
                    // if there is still something to process
                    if(tempGroupAccountValue > 0 ) {
                        if(feegrp.group.rateSchedule.stepping && tempGroupAccountValue > thisRate.blockAmount) {
                            // add the fee at the current rate for the total blockAmount
                            feegrp.groupFee += (thisRate.blockAmount * thisRate.rate);
                            tempGroupAccountValue -= thisRate.blockAmount;

                        } else {
                            feegrp.groupFee += (tempGroupAccountValue * thisRate.rate);
                            tempGroupAccountValue = 0;
                        }
                        feegrp.groupFee = feegrp.groupFee.setScale(2, BigDecimal.ROUND_HALF_DOWN);
                            log.info( "  in process: [rate: $thisRate.rate] [blockAmount: $thisRate.blockAmount] [$feegrp.group.name groupFee = $feegrp.groupFee]" )
                    }
                }
            } else {
                log.warn( "feegrp.group.rateSchedule is null" )
            }
            log.info( "final $feegrp.group.name groupFee = [$feegrp.groupFee]" )

            // reset fees
            feegrp.resetAllFees();
            
            // figure weight and fee
            feegrp.fees.each() { thisFee ->
                thisFee.determineWeightAndFee(feegrp.marketValue, feegrp.groupFee);
            }

            // display weight and fee
            feegrp.fees.each() { fee ->

                log.info( "  $fee.accountID - [ marketValue = $fee.marketValue ] [ weight = $fee.weight ] [ fee = $fee.feeValue ]" )
            }

            log.info( "-------------------------" )
        }
    }
}
