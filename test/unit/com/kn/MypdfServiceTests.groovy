package com.kn

import grails.test.*

class MypdfServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
        def MypdfService mypdfService
        mypdfService.test("myurl")
    }
}
