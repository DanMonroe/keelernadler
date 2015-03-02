package com.kn

class Months {
    String name

    static constraints = {
        name(inList:["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"])
    }

    String toString() {
        "${name}"
    }
}
