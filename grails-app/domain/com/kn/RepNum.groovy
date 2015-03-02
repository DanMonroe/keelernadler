package com.kn

class RepNum {
    String code
    String owner

    static constraints = {
        code(unique:true, nullable:false, maxSize:15)
        owner(nullable:false)
    }

    String toString() {
        "${code} (${owner})"
    }
}
