package com.kn

import net.sf.ehcache.*
import net.sf.ehcache.store.*
import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.xhtmlrenderer.pdf.ITextRenderer

/**
 * from https://github.com/aeischeid/grails-pdfplugin/raw/master/grails-app/services/pdf/PdfService.groovy
 */

import org.springframework.beans.factory.InitializingBean

class MypdfService implements InitializingBean {

  boolean transactional = false

  void afterPropertiesSet() {
      println "InitializingBean after prop set"
  }

    String test(url) {
        return "done"
    }

/*  A Simple fetcher to turn a specific URL into a PDF.  */

  byte[] buildPdf(url) {
    println("1-1")
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    println("1-2")
    ITextRenderer renderer = new ITextRenderer();
    println("1-3")
    try {
        println("1-4")
      renderer.setDocument(url);
      renderer.layout();
      renderer.createPDF(baos);
      byte[] b = baos.toByteArray();
      return b
    }
    catch (Throwable e) {
      log.error e
    }
  }

/*
  A Simple fetcher to turn a well formated XHTML string into a PDF
  The baseUri is included to allow for relative URL's in the XHTML string
*/

  byte[] buildPdfFromString(content, baseUri) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ITextRenderer renderer = new ITextRenderer();
    try {
      renderer.setDocumentFromString(content, baseUri);
      renderer.layout();
      renderer.createPDF(baos);
      byte[] b = baos.toByteArray();
      return b
    }
    catch (Throwable e) {
      log.error e
    }
  }

}
