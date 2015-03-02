// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = keelernadler // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// JQuery
grails.views.javascript.library="jquery"
jquery.version = "1.4.4"

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
    development {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
    test {
        grails.serverURL = "http://localhost:8080/${appName}"
    }

}

/**
* Directory configuration.
* Pickup the Tomcat/Catalina directory else use the target or current dir.
*/
def fs = File.separator // Local variable.
globalDirs.targetDir = new File("target${fs}").isDirectory() ? "target${fs}" : ''
globalDirs.catalinaBase = System.properties.getProperty('catalina.base')
globalDirs.logDirectory = globalDirs.catalinaBase ? "${globalDirs.catalinaBase}${fs}logs${fs}" : globalDirs.targetDir
//globalDirs.workDirectory = globalDirs.catalinaBase ? "${globalDirs.catalinaBase}${fs}work${fs}" : globalDirs.targetDir
//globalDirs.searchableIndexDirectory = "${globalDirs.workDirectory}SearchableIndex${fs}${appName}${fs}"

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    appenders {
//        console name:'stdout', layout: pattern(conversionPattern: '%d{dd-MM-yyyy HH:mm:ss,SSS} %5p %c{1} - %m%n')
////        file name: 'knFile', file: 'keelernadler.log', layout: pattern(conversionPattern: '%d{dd-MM-yyyy HH:mm:ss,SSS} %5p %c{1} - %m%n')
//        rollingFile name: 'knFile', file: 'keelernadler.log', layout: pattern(conversionPattern: '%d{dd-MM-yyyy HH:mm:ss,SSS} %5p %c{1} - %m%n')

        rollingFile name:"knFile",
                        file:"${globalDirs.logDirectory}${appName}.log".toString(),
                        maxFileSize:'900kB',
                        maxBackupIndex:10,
                        layout:pattern(conversionPattern: '%d{[EEE, dd-MMM-yyyy @ HH:mm:ss.SSS]} [%t] %-5p %c %x - %m%n')


    }

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'

    error 'grails.app' // Set the default log level for our app code.
    info 'grails.app.bootstrap'

    info  'com.kn'
//          'grails.app.domain',
//          'org.codehaus.groovy.grails.web.servlet'
    
//    debug 'com.kn',
//          'grails.app.domain'

//    root {
//        info 'knFile'
////        error 'knFile'
//        additivity = true
//    }

    // Move anything that should behave differently into this section.
    switch(environment) {
        case 'development':
            // Configure the root logger to output to stdout and knFile appenders.
            root {
                error 'stdout','knFile'
                additivity = true
            }
            //debug "org.hibernate.SQL"
            debug 'grails.app.service'
            debug 'grails.app.controller'
            debug 'org.hibernate.cfg.annotations.Version'
            break
        case 'test':
            // Configure the root logger to only output to knFile appender.
            root {
                error 'stdout','knFile'
                additivity = true
            }
            debug 'grails.app.service'
            debug 'grails.app.controller'
            break
        case 'production':
            // Configure the root logger to only output to knFile appender.
            root {
                error 'knFile'
                additivity = true
            }
            warn 'grails.app.service'
            warn 'grails.app.controller'
            error 'org.hibernate.cfg.annotations.Version'
            break
    }

}
