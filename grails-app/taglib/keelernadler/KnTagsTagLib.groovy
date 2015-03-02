package keelernadler

import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib

class KnTagsTagLib {
    static namespace = 'kn'

  def myTestLink = { attrs, body ->
    //String template = attrs['template']
    String pdfController = attrs['pdfController']
    String pdfAction = attrs['pdfAction']
    String pdfId = attrs['pdfId']
    //String pdfParams = attrs['pdfParams']
    String url = attrs['url']
    String filename = attrs['filename']
    String link
    // class attribute is for CSS styling
    String c = attrs['class'] ?: 'pdf'
    if(url){
      link = new ApplicationTagLib().createLink(url: [controller:'feeRun', action:'mypdfLink',
             params: [url: attrs.url] ] )
    }
    if(pdfController){
      link = new ApplicationTagLib().createLink(url: [controller:'feeRun', action:'invoice',
             params: [pdfController:"${pdfController}"] ] )
      if(pdfAction) link += "&pdfAction=${pdfAction}"
    }
    if(pdfId) link+= "&pdfId=${pdfId}"
    if(filename){ link += "&filename=${filename}" }
    out << """
      <a href="${link}" class="${c}" title="mypdf">
    """

        out << body()
        out << "</a>"
  }

  def mypdfLink = { attrs, body ->
    //String template = attrs['template']
    String pdfController = attrs['pdfController']
    String pdfAction = attrs['pdfAction']
    String pdfId = attrs['pdfId']
    //String pdfParams = attrs['pdfParams']
    String url = attrs['url']
    String filename = attrs['filename']
    String link
    // class attribute is for CSS styling
    String c = attrs['class'] ?: 'pdf'
    if(url){
      link = new ApplicationTagLib().createLink(url: [controller:'pdf', action:'pdfLink',
             params: [url: attrs.url] ] )
    }
    //if(template){
      //link = new ApplicationTagLib().createLink(url: [controller:'pdf', action:'pdfLink',
             //params: [template: "${template}"] ] )
    //}
    if(pdfController){
      link = new ApplicationTagLib().createLink(url: [controller:'pdf', action:'pdfLink',
             params: [pdfController:"${pdfController}"] ] )
      if(pdfAction) link += "&pdfAction=${pdfAction}"
      if(pdfId) link+= "&pdfId=${pdfId}"
    }
    if(filename){ link += "&filename=${filename}" }
    out << """
      <a href="${link}" class="${c}" title="mypdf">
    """
    // setup icon/button inside of link if icon is set to 'true'
    if (attrs.icon) {
      out << "<img src='"
      out << createLinkTo(dir:'images', file:'pdf_button.png')
      out << "' alt='PDF Version' border='0'/>"
    }
    out << body()
    out << "</a>"
  }





    def months = [
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

    def knTest = { attrs, body ->
        out << body()
    }

    def monthsListDisplay = {attrs, body ->
        def value = attrs.value
        def style, html
        
        style = "style='"
        if(attrs.height)
        style += "height:${attrs.height};"
        if(attrs.width)
        style += "width:${attrs.width};"

        if (style.length() == "style='".length())
        style = ""
        else
        style += "'" // closing single quote

        html = "<ul class='MonthList' " + style + ">"

        out << html

        value.each() { val ->
            out << "<li>${months[val]}</li>"
        }

        out << "</ul>"
    }

    def showMonths = {attrs ->
        def value = attrs.value
        def totalMonths = value.size()-1;
        value.eachWithIndex() { val, i ->
            out << "${months[val]}"
            if(i < totalMonths) {
                out << ", "
            }
        }
    }

    def monthsCheckBoxList = {attrs, body ->
        def value = attrs.value
        def cname = attrs.name
        def isChecked, style, html

        style = "style='"
        if(attrs.height)
        style += "height:${attrs.height};"
        if(attrs.width)
        style += "width:${attrs.width};"

        if (style.length() == "style='".length())
        style = ""
        else
        style += "'" // closing single quote

        html = "<ul class='CheckBoxList' " + style + ">"

        out << html

        months.eachWithIndex() { m, i ->
            //            isChecked = false;
            isChecked = value?.contains(i)

            out << "<li>" << checkBox(name:cname, value:"${i}", checked: isChecked) << "${m}" << "</li>"
        }

        out << "</ul>"
    }

    //  Checkbox list that can be used as a more user-friendly alternative to
    // a multiselect list box
    def checkBoxList = {attrs, body ->
        def from = attrs.from
        def value = attrs.value
        def cname = attrs.name
        def isChecked, ht, wd, style, html

        //  sets the style to override height and/or width if either of them
        //  is specified, else the default from the CSS is taken
        style = "style='"
        if(attrs.height)
        style += "height:${attrs.height};"
        if(attrs.width)
        style += "width:${attrs.width};"

        if (style.length() == "style='".length())
        style = ""
        else
        style += "'" // closing single quote

        html = "<ul class='CheckBoxList' " + style + ">"

        out << html

        from.each { obj ->

            // if we wanted to select the checkbox using a click anywhere on the label (also hover effect)
            // but grails does not recognize index suffix in the name as an array:
            //      cname = "${attrs.name}[${idx++}]"
            //      and put this inside the li: <label for='$cname'>...</label>

            isChecked = (value?.contains(obj."${attrs.optionKey}"))? true: false

            out << "<li>" <<
            checkBox(name:cname, value:obj."${attrs.optionKey}", checked: isChecked) <<
                    "${obj}" << "</li>"
        }

        out << "</ul>"

    }

}
