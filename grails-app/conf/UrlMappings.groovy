class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

//		"/"(view:"/index")
		"/admin"(view:"/admin")
		"/help"(view:"/help")
                "/"(controller:"feeRun", action:"list")
		"500"(view:'/error')
	}
}
