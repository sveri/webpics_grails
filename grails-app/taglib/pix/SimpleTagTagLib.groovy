package pix

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
}
