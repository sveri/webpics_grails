class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		"/jsupload/$albumid/$qqfile"(controller: "Album", action: "jsfiles")

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
