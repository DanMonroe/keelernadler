package com.kn

class BillingSchedule {
    String name
    List selectedMonths

    static hasMany = [ selectedMonths:int ]

    public static months = [
            'January',
            'February',
            'March',
            'April',
            'May',
            'June',
            'July',
            'August',
            'September',
            'October',
            'November',
            'December'
    ]

    static constraints = {
        name(unique:true, nullable:false)
        selectedMonths()
    }

    String toString() {
        def monthlist = " ("

        selectedMonths.eachWithIndex() { val, i ->
            monthlist += "${months[val]}"
            if(i < (selectedMonths.size()-1)) {
                monthlist += ", "
            }
        }
        return "${name}${monthlist})"
    }
}
