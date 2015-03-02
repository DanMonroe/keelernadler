package com.kn

class AccountType {
    String title

    static constraints = {
        title(unique:true, nullable:false, maxSize:15)
    }

    String toString() {
        title
    }
}
