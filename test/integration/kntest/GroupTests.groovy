package keelernadler

import grails.test.*

class GroupTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
        def accnt1 = new com.kn.Accnt(name:"a1")
        def accnt2 = new com.kn.Accnt(name:"a2")
//        def g = new com.kn.GroupAccount(name:"group1")
//            .addToAccounts(accnt1)
//            .addToAccounts(accnt2)
//            .save(flush:true)

        def g = new com.kn.GroupAccount(name:"group1")
            .save(flush:true)

        g.addToAccounts(accnt1)
            .addToAccounts(accnt2)
            .save(flush:true)

    }
}
