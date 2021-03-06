/**
 * Generated by the Shiro plugin. This filters class protects all URLs
 * via access control by convention.
 */
class ShiroSecurityFilters {

    def filters = {
	all(uri: "/**") {
	    before = {
		// Ignore direct views (e.g. the default main index page).
		if (!controllerName) return true

		//		if(filter.request.xhr){
		//		    println "xhr"
		//		}

		// Access control by convention.
		accessControl()
	    }
	}
    }
}
