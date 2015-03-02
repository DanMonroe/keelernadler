package com.kn

class RateSchedule {
    List rates;
    static hasMany = [ rates:com.kn.Rate ]
    String name
    boolean stepping = false; // has multiple rate levels
    

    static constraints = {
        name(blank:false, maxSize:50)
        rates(nullable:true)
        }

    String toString() {
        "${this.name}"
    }
}
