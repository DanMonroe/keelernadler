package com.kn

class Rate {
    BigDecimal rate = 0.000000000
    BigDecimal flatRate = 0.00
    BigDecimal blockAmount = 0.00
    String displayRate
    String description

    static constraints = {
        rate(blank:true, scale:9)
        flatRate(blank:true,scale:2)
        blockAmount(blank:true,scale:2)
        displayRate(blank:true)
        description(blank:true)
    }

    String toString() {
        "${rate}"
    }
}
