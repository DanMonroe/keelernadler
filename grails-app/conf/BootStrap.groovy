class BootStrap {

    def init = { servletContext ->

        def testForInvoiceData = com.kn.Invoice.get(1);
        if(testForInvoiceData == null) {
            def invoiceData = new com.kn.Invoice(
                returnAddress:"5100 Bradenton Ave. Suite B<br>Dublin, OH 43017<br>614-791-4123<br>Fax 614-889-5250<br>rick.nadler@keelernadler.com",
                regardingText:"Portfolio Servicing Fees",
                feeDeductionSummary:"<h2>Fee Deduction Summary</h2> The above fees can be paid by writing a check made payable to:<br> Cambridge Investment Research, Inc.",
                makeChecksPayable:"Make all checks payable to<br> Cambridge Investment Research, Inc.",
                sendCareOf:"And send care of:<br> Keeler & Nadler Financial Planning and Wealth Management<br> 5100 Bradenton Ave. Suite B<br> Dublin, OH 43017",
                footer:"Thank you for your business!"
            )
            .save(flush:true, failOnError: true)
        }
        else {println "${testForInvoiceData.footer}\n\n"}
        def testForRep1 = com.kn.RepNum.findByCode("PU5");
        if(testForRep1 == null) {
            println "\n\nCreating initial representatives, billing schedules, account types, and billing rates.\n\n"
            def rep1 = new com.kn.RepNum(code:"PU5", owner:"William K. Root").save(flush:true, failOnError: true)
            def rep2 = new com.kn.RepNum(code:"DR5", owner:"Andrew Kieler").save(flush:true, failOnError: true)
            def rep3 = new com.kn.RepNum(code:"P4U", owner:"Andrew Kieler").save(flush:true, failOnError: true)
            def rep4 = new com.kn.RepNum(code:"PRX", owner:"Richard Nadler").save(flush:true, failOnError: true)

            def billsch1 = new com.kn.BillingSchedule(name:"Schedule 1", selectedMonths:[0,3,6,9]).save(flush:true, failOnError: true)
            def billsch2 = new com.kn.BillingSchedule(name:"Schedule 2", selectedMonths:[1,4,7,10]).save(flush:true, failOnError: true)
            def billsch3 = new com.kn.BillingSchedule(name:"Schedule 3", selectedMonths:[2,5,8,11]).save(flush:true, failOnError: true)

            def type1 = new com.kn.AccountType(title:"Trust").save(flush:true, failOnError: true)
            def type2 = new com.kn.AccountType(title:"IRA").save(flush:true, failOnError: true)
            def type3 = new com.kn.AccountType(title:"IRA RO").save(flush:true, failOnError: true)
            def type4 = new com.kn.AccountType(title:"Individual").save(flush:true, failOnError: true)
            def type5 = new com.kn.AccountType(title:"Joint").save(flush:true, failOnError: true)
            def type6 = new com.kn.AccountType(title:"403B").save(flush:true, failOnError: true)
            def typeSep = new com.kn.AccountType(title:"SEP").save(flush:true, failOnError: true)
            def typeIRAInh = new com.kn.AccountType(title:"IRA Inherited").save(flush:true, failOnError: true)
            def type403B_01 = new com.kn.AccountType(title:"403B 01-3132177").save(flush:true, failOnError: true)


//            def rate1 = new com.kn.Rate(rate:0.002520548, blockAmount:250000).save(flush:true, failOnError: true)
//            def rate2 = new com.kn.Rate(rate:0.001890411, blockAmount:250000).save(flush:true, failOnError: true)
//            def rate3 = new com.kn.Rate(rate:0.001260274, blockAmount:50000000).save(flush:true, failOnError: true)
//            def rate4 = new com.kn.Rate(rate:0.001479452, blockAmount:50000000).save(flush:true, failOnError: true)
            def rate1 = new com.kn.Rate(rate:0.002520548, blockAmount:250000, displayRate: "1.00%", description: "(up to \$250,000)").save(flush:true, failOnError: true)
            def rate2 = new com.kn.Rate(rate:0.001890411, blockAmount:250000, displayRate: ".75%", description: "(\$250,000 - \$500,000)").save(flush:true, failOnError: true)
            def rate3 = new com.kn.Rate(rate:0.001260274, blockAmount:50000000, displayRate: ".50%", description: "(\$500,001 and up)").save(flush:true, failOnError: true)
            def rate4 = new com.kn.Rate(rate:0.001479452, blockAmount:50000000, displayRate: ".60%", description: "(up to \$50,000,000)").save(flush:true, failOnError: true)

            def rateSchd1 = new com.kn.RateSchedule(name:"Schedule 1", stepping: true)
            .addToRates(rate1)
            .addToRates(rate2)
            .addToRates(rate3)
            .save(flush:true, failOnError: true)

            def rateSchd2 = new com.kn.RateSchedule(name:"Schedule 2", stepping: false)
                .addToRates(rate1)
                .save(flush:true, failOnError: true)

            def rateSchd3 = new com.kn.RateSchedule(name:"Schedule 3", stepping: false)
                .addToRates(rate4)
                .save(flush:true, failOnError: true)

        }

        if(false) {
        def rep1 = new com.kn.RepNum(code:"PU5", owner:"William K. Root").save(flush:true, failOnError: true)
        def rep2 = new com.kn.RepNum(code:"DR5", owner:"Andrew Kieler").save(flush:true, failOnError: true)
        def rep3 = new com.kn.RepNum(code:"P4U", owner:"Andrew Kieler").save(flush:true, failOnError: true)
        def rep4 = new com.kn.RepNum(code:"PRX", owner:"Richard Nadler").save(flush:true, failOnError: true)

//        def m1 = new com.kn.Months(name:"Jan").save(flush:true, failOnError: true)
//        def m2 = new com.kn.Months(name:"Feb").save(flush:true, failOnError: true)
//        def m3 = new com.kn.Months(name:"Mar").save(flush:true, failOnError: true)
//        def m4 = new com.kn.Months(name:"Apr").save(flush:true, failOnError: true)
//        def m5 = new com.kn.Months(name:"May").save(flush:true, failOnError: true)
//        def m6 = new com.kn.Months(name:"Jun").save(flush:true, failOnError: true)
//        def m7 = new com.kn.Months(name:"Jul").save(flush:true, failOnError: true)
//        def m8 = new com.kn.Months(name:"Aug").save(flush:true, failOnError: true)
//        def m9 = new com.kn.Months(name:"Sep").save(flush:true, failOnError: true)
//        def m10 = new com.kn.Months(name:"Oct").save(flush:true, failOnError: true)
//        def m11 = new com.kn.Months(name:"Nov").save(flush:true, failOnError: true)
//        def m12 = new com.kn.Months(name:"Dec").save(flush:true, failOnError: true)
//
        def billsch1 = new com.kn.BillingSchedule(name:"Schedule 1", selectedMonths:[0,3,6,9])
//            .addToMonths(m1)
//            .addToMonths(m4)
//            .addToMonths(m7)
//            .addToMonths(m10)
            .save(flush:true, failOnError: true)

        def billsch2 = new com.kn.BillingSchedule(name:"Schedule 2", selectedMonths:[1,4,7,10])
//            .addToMonths(m2)
//            .addToMonths(m5)
//            .addToMonths(m8)
//            .addToMonths(m11)
            .save(flush:true, failOnError: true)

        def billsch3 = new com.kn.BillingSchedule(name:"Schedule 3", selectedMonths:[2,5,8,11])
//            .addToMonths(m3)
//            .addToMonths(m6)
//            .addToMonths(m9)
//            .addToMonths(m12)
            .save(flush:true, failOnError: true)


        def type1 = new com.kn.AccountType(title:"Trust").save(flush:true, failOnError: true)
        def type2 = new com.kn.AccountType(title:"IRA").save(flush:true, failOnError: true)
        def type3 = new com.kn.AccountType(title:"IRA RO").save(flush:true, failOnError: true)
        def type4 = new com.kn.AccountType(title:"Individual").save(flush:true, failOnError: true)
        def type5 = new com.kn.AccountType(title:"Joint").save(flush:true, failOnError: true)
        def type6 = new com.kn.AccountType(title:"403B").save(flush:true, failOnError: true)
        def typeSep = new com.kn.AccountType(title:"SEP").save(flush:true, failOnError: true)
        def typeIRAInh = new com.kn.AccountType(title:"IRA Inherited").save(flush:true, failOnError: true)
        def type403B_01 = new com.kn.AccountType(title:"403B 01-3132177").save(flush:true, failOnError: true)


        def rate1 = new com.kn.Rate(rate:0.002520548, blockAmount:250000, displayRate: "1.00%", description: "(up to \$250,000)").save(flush:true, failOnError: true)
        def rate2 = new com.kn.Rate(rate:0.001890411, blockAmount:250000, displayRate: ".75%", description: "(\$250,000 - \$500,000)").save(flush:true, failOnError: true)
        def rate3 = new com.kn.Rate(rate:0.001260274, blockAmount:50000000, displayRate: ".50%", description: "(\$500,001 and up)").save(flush:true, failOnError: true)
        def rate4 = new com.kn.Rate(rate:0.001479452, blockAmount:50000000, displayRate: ".60%", description: "(up to \$50,000,000)").save(flush:true, failOnError: true)

        def rateSchd1 = new com.kn.RateSchedule(name:"Schedule 1", stepping: true)
        .addToRates(rate1)
        .addToRates(rate2)
        .addToRates(rate3)
        .save(flush:true, failOnError: true)

        def rateSchd2 = new com.kn.RateSchedule(name:"Schedule 2", stepping: false)
            .addToRates(rate1)
            .save(flush:true, failOnError: true)

        def rateSchd3 = new com.kn.RateSchedule(name:"Schedule 3", stepping: false)
            .addToRates(rate4)
            .save(flush:true, failOnError: true)

    def accnt1 = new com.kn.Accnt(name:"a1",accountID:"5DK111111",billingAccountID:"5DK111111",type:type1, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt2 = new com.kn.Accnt(name:"a2",accountID:"5DK111112",billingAccountID:"5DK111112",type:type1, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt3 = new com.kn.Accnt(name:"a3",accountID:"5DK111113",billingAccountID:"5DK111113",type:type1, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt4 = new com.kn.Accnt(name:"a4",accountID:"5DK111114",billingAccountID:"5DK111114",type:type3, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)

println "accnt1.billsch1.selectedMonths = ${accnt1.billingSchedule.selectedMonths}"
    def g1 = new com.kn.GroupAccount(name:"group1", rateSchedule: rateSchd1)
            .addToAccounts(accnt1)
            .addToAccounts(accnt2)
            .addToAccounts(accnt3)
            .addToAccounts(accnt4)
            .save(flush:true, failOnError: true)

//    def accnt21 = new com.kn.Accnt(name:"a21",accountID:"5DK111128",billingAccountID:"5DK111128",type:type5, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
//    def accnt22 = new com.kn.Accnt(name:"a22",accountID:"5DK111129",billingAccountID:"5DK111129",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
//    def accnt23 = new com.kn.Accnt(name:"a23",accountID:"5DK111130",billingAccountID:"5DK111130",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
//    def accnt24 = new com.kn.Accnt(name:"a24",accountID:"5DK111131",billingAccountID:"5DK111131",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
//
//    def g2 = new com.kn.GroupAccount(name:"group2", rateSchedule: rateSchd2)
//            .addToAccounts(accnt21)
//            .addToAccounts(accnt22)
//            .addToAccounts(accnt23)
//            .addToAccounts(accnt24)
//            .save(flush:true, failOnError: true)

    // group 3
    // uses rateSched2
    def accnt31 = new com.kn.Accnt(name:"a31",accountID:"5DK111117",billingAccountID:"5DK111117",type:type4, payPlan: "Advance", billingSchedule:billsch2, repNum: rep2)

    def g3 = new com.kn.GroupAccount(name:"group3", rateSchedule: rateSchd2)
            .addToAccounts(accnt31)
            .save(flush:true, failOnError: true)

    // group 4
    def accnt41 = new com.kn.Accnt(name:"a41",accountID:"5DK111119",billingAccountID:"5DK111119",type:type2, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt42 = new com.kn.Accnt(name:"a42",accountID:"5DK111120",billingAccountID:"5DK111120",type:type4, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g4 = new com.kn.GroupAccount(name:"group4", rateSchedule: rateSchd1)
            .addToAccounts(accnt41)
            .addToAccounts(accnt42)
            .save(flush:true, failOnError: true)

    // group 5
    def accnt51 = new com.kn.Accnt(name:"a51",accountID:"5DK111123",billingAccountID:"5DK111124",type:type4, payPlan: "Advance", billingSchedule:billsch3, repNum: rep1)
    def accnt52 = new com.kn.Accnt(name:"a52",accountID:"5DK111124",billingAccountID:"5DK111124",type:type2, payPlan: "Advance", billingSchedule:billsch3, repNum: rep1)
    def accnt53 = new com.kn.Accnt(name:"a53",accountID:"5DK111125",billingAccountID:"5DK111124",type:type2, payPlan: "Advance", billingSchedule:billsch3, repNum: rep1)
    def g5 = new com.kn.GroupAccount(name:"group5", rateSchedule: rateSchd1)
            .addToAccounts(accnt51)
            .addToAccounts(accnt52)
            .addToAccounts(accnt53)
            .save(flush:true, failOnError: true)

    def accnt61 = new com.kn.Accnt(name:"a61",accountID:"5DK111128",billingAccountID:"5DK111128",type:type5, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt62 = new com.kn.Accnt(name:"a62",accountID:"5DK111129",billingAccountID:"5DK111129",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt63 = new com.kn.Accnt(name:"a63",accountID:"5DK111130",billingAccountID:"5DK111130",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt64 = new com.kn.Accnt(name:"a64",accountID:"5DK111131",billingAccountID:"5DK111131",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g6 = new com.kn.GroupAccount(name:"group6", rateSchedule: rateSchd1)
            .addToAccounts(accnt61)
            .addToAccounts(accnt62)
            .addToAccounts(accnt63)
            .addToAccounts(accnt64)
            .save(flush:true, failOnError: true)
        
    def accnt71 = new com.kn.Accnt(name:"a71",accountID:"5DK111134",billingAccountID:"5DK111135",type:type2, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt72 = new com.kn.Accnt(name:"a72",accountID:"5DK111135",billingAccountID:"5DK111135",type:type2, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g7 = new com.kn.GroupAccount(name:"group7", rateSchedule: rateSchd1)
            .addToAccounts(accnt71)
            .addToAccounts(accnt72)
            .save(flush:true, failOnError: true)
        
    def accnt81 = new com.kn.Accnt(name:"a81",accountID:"5DK111138",billingAccountID:"5DK111138",type:type5, payPlan: "Advance", billingSchedule:billsch2, repNum: rep1)
    def accnt82 = new com.kn.Accnt(name:"a82",accountID:"5DK111139",billingAccountID:"5DK111138",type:type3, payPlan: "Advance", billingSchedule:billsch2, repNum: rep1)
    def g8 = new com.kn.GroupAccount(name:"group8", rateSchedule: rateSchd1)
            .addToAccounts(accnt81)
            .addToAccounts(accnt82)
            .save(flush:true, failOnError: true)
        
    def accnt91 = new com.kn.Accnt(name:"a91",accountID:"5DK111142",billingAccountID:"5DK111142",type:type3, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt92 = new com.kn.Accnt(name:"a92",accountID:"5DK111143",billingAccountID:"5DK111143",type:type2, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt93 = new com.kn.Accnt(name:"a93",accountID:"5DK111144",billingAccountID:"5DK111144",type:typeIRAInh, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g9 = new com.kn.GroupAccount(name:"group9", rateSchedule: rateSchd1)
            .addToAccounts(accnt91)
            .addToAccounts(accnt92)
            .addToAccounts(accnt93)
            .save(flush:true, failOnError: true)
        
    def accnt101 = new com.kn.Accnt(name:"a101",accountID:"5DK111147",billingAccountID:"5DK111147",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt102 = new com.kn.Accnt(name:"a102",accountID:"5DK111148",billingAccountID:"5DK111148",type:type3, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g10 = new com.kn.GroupAccount(name:"group10", rateSchedule: rateSchd1)
            .addToAccounts(accnt101)
            .addToAccounts(accnt102)
            .save(flush:true, failOnError: true)
        
    def accnt111 = new com.kn.Accnt(name:"a111",accountID:"5DK111151",billingAccountID:"5DK111151",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt112 = new com.kn.Accnt(name:"a112",accountID:"5DK111152",billingAccountID:"5DK111152",type:type3, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt113 = new com.kn.Accnt(name:"a113",accountID:"5DK111153",billingAccountID:"5DK111153",type:type3, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g11 = new com.kn.GroupAccount(name:"group11", rateSchedule: rateSchd1)
            .addToAccounts(accnt111)
            .addToAccounts(accnt112)
            .addToAccounts(accnt113)
            .save(flush:true, failOnError: true)

    def accnt121 = new com.kn.Accnt(name:"a121",accountID:"5DK111156",billingAccountID:"5DK111157",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt122 = new com.kn.Accnt(name:"a122",accountID:"5DK111157",billingAccountID:"5DK111157",type:type3, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g12 = new com.kn.GroupAccount(name:"group12", rateSchedule: rateSchd1)
            .addToAccounts(accnt121)
            .addToAccounts(accnt122)
            .save(flush:true, failOnError: true)
        
    def accnt131 = new com.kn.Accnt(name:"a131",accountID:"5DK111160",billingAccountID:"5DK111160",type:type3, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g13 = new com.kn.GroupAccount(name:"group13", rateSchedule: rateSchd1)
            .addToAccounts(accnt131)
            .save(flush:true, failOnError: true)
        
    def accnt141 = new com.kn.Accnt(name:"a141",accountID:"5DK111163",billingAccountID:"5DK111163",type:type3, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g14 = new com.kn.GroupAccount(name:"group14", rateSchedule: rateSchd1)
            .addToAccounts(accnt141)
            .save(flush:true, failOnError: true)
        
    def accnt151 = new com.kn.Accnt(name:"a151",accountID:"5DK111166",billingAccountID:"5DK111166",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt152 = new com.kn.Accnt(name:"a152",accountID:"5DK111167",billingAccountID:"5DK111167",type:type3, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g15 = new com.kn.GroupAccount(name:"group15", rateSchedule: rateSchd1)
            .addToAccounts(accnt151)
            .addToAccounts(accnt152)
            .save(flush:true, failOnError: true)
        
    def accnt161 = new com.kn.Accnt(name:"a161",accountID:"5DK111170",billingAccountID:"5DK111170",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt162 = new com.kn.Accnt(name:"a162",accountID:"5DK111171",billingAccountID:"5DK111171",type:type3, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt163 = new com.kn.Accnt(name:"a163",accountID:"5DK111172",billingAccountID:"5DK111172",type:type3, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt164 = new com.kn.Accnt(name:"a164",accountID:"5DK111173",billingAccountID:"5DK111173",type:type3, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt165 = new com.kn.Accnt(name:"a165",accountID:"5DK111174",billingAccountID:"5DK111174",type:type3, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g16 = new com.kn.GroupAccount(name:"group16", rateSchedule: rateSchd1)
            .addToAccounts(accnt161)
            .addToAccounts(accnt162)
            .addToAccounts(accnt163)
            .addToAccounts(accnt164)
            .addToAccounts(accnt165)
            .save(flush:true, failOnError: true)
        
    def accnt171 = new com.kn.Accnt(name:"a171",accountID:"5DK111177",billingAccountID:"5DK111177",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt172 = new com.kn.Accnt(name:"a172",accountID:"5DK111178",billingAccountID:"5DK111178",type:type3, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt173 = new com.kn.Accnt(name:"a173",accountID:"5DK111179",billingAccountID:"5DK111179",type:type3, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g17 = new com.kn.GroupAccount(name:"group17", rateSchedule: rateSchd1)
            .addToAccounts(accnt171)
            .addToAccounts(accnt172)
            .addToAccounts(accnt173)
            .save(flush:true, failOnError: true)
        
    def accnt181 = new com.kn.Accnt(name:"a181",accountID:"5DK111182",billingAccountID:"5DK111182",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g18 = new com.kn.GroupAccount(name:"group18", rateSchedule: rateSchd1)
            .addToAccounts(accnt181)
            .save(flush:true, failOnError: true)
        
    // group 6 - has different billingaccount for accnt64
    def accnt191 = new com.kn.Accnt(name:"a191",accountID:"5DK111185",billingAccountID:"5DK111185",type:type4, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt192 = new com.kn.Accnt(name:"a192",accountID:"5DK111186",billingAccountID:"5DK111186",type:type2, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt193 = new com.kn.Accnt(name:"a193",accountID:"5DK111187",billingAccountID:"5DK111187",type:type2, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt194 = new com.kn.Accnt(name:"a194",accountID:"5DK111188",billingAccountID:"5DK111187",type:type2, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g19 = new com.kn.GroupAccount(name:"group19", rateSchedule: rateSchd1)
            .addToAccounts(accnt191)
            .addToAccounts(accnt192)
            .addToAccounts(accnt193)
            .addToAccounts(accnt194)
            .save(flush:true, failOnError: true)

    def accnt201 = new com.kn.Accnt(name:"a201",accountID:"5DK111191",billingAccountID:"5DK111191",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt202 = new com.kn.Accnt(name:"a202",accountID:"5DK111192",billingAccountID:"5DK111192",type:type3, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g20 = new com.kn.GroupAccount(name:"group20", rateSchedule: rateSchd1)
            .addToAccounts(accnt201)
            .addToAccounts(accnt202)
            .save(flush:true, failOnError: true)
        
    def accnt211 = new com.kn.Accnt(name:"a211",accountID:"5DK111195",billingAccountID:"5DK111195",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt212 = new com.kn.Accnt(name:"a212",accountID:"5DK111196",billingAccountID:"5DK111196",type:type3, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g21 = new com.kn.GroupAccount(name:"group21", rateSchedule: rateSchd1)
            .addToAccounts(accnt211)
            .addToAccounts(accnt212)
            .save(flush:true, failOnError: true)
        
    def accnt221 = new com.kn.Accnt(name:"a221",accountID:"5DK111199",billingAccountID:"5DK111199",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g22 = new com.kn.GroupAccount(name:"group22", rateSchedule: rateSchd1)
            .addToAccounts(accnt221)
            .save(flush:true, failOnError: true)
        
    def accnt231 = new com.kn.Accnt(name:"a231",accountID:"5DK111201",billingAccountID:"5DK111201",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g23 = new com.kn.GroupAccount(name:"group23", rateSchedule: rateSchd1)
            .addToAccounts(accnt231)
            .save(flush:true, failOnError: true)
        
    def accnt241 = new com.kn.Accnt(name:"a241",accountID:"5DK111203",billingAccountID:"5DK111203",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt242 = new com.kn.Accnt(name:"a242",accountID:"5DK111204",billingAccountID:"5DK111204",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g24 = new com.kn.GroupAccount(name:"group24", rateSchedule: rateSchd1)
            .addToAccounts(accnt241)
            .addToAccounts(accnt242)
            .save(flush:true, failOnError: true)
        
    def accnt251 = new com.kn.Accnt(name:"a251",accountID:"5DK111207",billingAccountID:"5DK111212",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt252 = new com.kn.Accnt(name:"a252",accountID:"5DK111208",billingAccountID:"5DK111212",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt253 = new com.kn.Accnt(name:"a253",accountID:"5DK111209",billingAccountID:"5DK111212",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt254 = new com.kn.Accnt(name:"a254",accountID:"5DK111210",billingAccountID:"5DK111212",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt255 = new com.kn.Accnt(name:"a255",accountID:"5DK111211",billingAccountID:"5DK111212",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def accnt256 = new com.kn.Accnt(name:"a256",accountID:"5DK111212",billingAccountID:"5DK111212",type:type6, payPlan: "Advance", billingSchedule:billsch1, repNum: rep1)
    def g25 = new com.kn.GroupAccount(name:"group25", rateSchedule: rateSchd3)
            .addToAccounts(accnt251)
            .addToAccounts(accnt252)
            .addToAccounts(accnt253)
            .addToAccounts(accnt254)
            .addToAccounts(accnt255)
            .addToAccounts(accnt256)
            .save(flush:true, failOnError: true)
        }
    }
    def destroy = {
    }
}
