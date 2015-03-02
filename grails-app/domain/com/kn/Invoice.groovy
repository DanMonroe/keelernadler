package com.kn

class Invoice {
    String returnAddress
    String regardingText
    String feeDeductionSummary
    String makeChecksPayable
    String sendCareOf
    String footer
    
    static constraints = {
        returnAddress(nullable:true)
        regardingText(nullable:true)
        feeDeductionSummary(nullable:true)
        makeChecksPayable(nullable:true)
        sendCareOf(nullable:true)
        footer(nullable:true)
    }
}
