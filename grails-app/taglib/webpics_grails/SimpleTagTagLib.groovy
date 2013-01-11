package webpics_grails

class SimpleTagTagLib {

    /**
     * @attr controller returns 'active' if the controller equals the current open controller
     * <br>
     * used for the navigation bar to indicate an active link
     */
    def activeContLink = { attrs, body ->
	out << body() << (attrs.controller == pageScope.controllerName ? "active" : "")
    }

    /**
     * @attr controller
     * @attr action
     * returns 'active' if the controller equals the current open controller and the action
     * the current active action
     * <br>
     * used for the navigation bar to indicate an active link
     */
    def activeContActLink = { attrs, body ->
	out << body() << ( (attrs.controller == pageScope.controllerName
		&& attrs.action == pageScope.actionName) ? "active" : "")
    }

    /**
     *
     */
    def permissionSelect = {
	attrs->
	def allPerms = ((attrs.value ?: []) + grailsApplication.controllerClasses.findAll{
	    it.propertyName!="authController" && it.propertyName!="dbdocController" && it.propertyName!="errorController"
	}.collect{ controller->
	    def base = controller.propertyName - 'Controller';
	    controller.getURIs().collect{
		def action = it.split('\\/')
		action = (action.size() == 2 ? "*" : action[2] )
		"${base}:${action}"
	    }
	} + "*:*").flatten().collect{
	    it.toString()
	}.unique().sort()
	if (allPerms) {
	    out << g.select(name:attrs.name, from:allPerms, multiple:true, size:(attrs.size ?: 10), value:(attrs.value ?: []), noSelection:['':''], 'class':"many-to-many")
	} else {
	    out << "<i>-</i>"
	}
    }
}
