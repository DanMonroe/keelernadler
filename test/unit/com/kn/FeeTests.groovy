package com.kn

import grails.test.*

class FeeTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
        //        def fee = new Fee(name:"Nadler");
        //        assertEquals "Nadler", fee.name;

        def grp = new GroupAccount(name:'group1');
        assertEquals 'group1', grp.name;

//        assertNotNull grp
//        grp.save(flush:true)
        
//        def testAccount = new Accnt(name:'Test Account');
//        assertEquals 'Test Account', testAccount.name;
//        assertNotNull testAccount


//        grp.addToAccounts(name:"test account 2")
        //        grp.addToAccounts(new Accnt(name:'foo'))
        //        grp.save()
    }


    // for integration tests... must extend GroovyTestCase
//    private Object save(Object object) {
//        validateAndPrintErrors(object)
//        Object result = object.save(flush:true)
//        assertNotNull("Object not created: " + object, result)
//        return result
//    }
//
//    private void validateAndPrintErrors(Object object) {
//        if (!object.validate()) {
//            object.errors.allErrors.each {error ->
//                println error
//            }
//            fail("failed to save object ${object}")
//        }
//    }
}
