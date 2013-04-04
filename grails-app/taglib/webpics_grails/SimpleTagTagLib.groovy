package webpics_grails

import org.apache.shiro.SecurityUtils

class SimpleTagTagLib {

    def roleService

//    static namespace = "pix"

    /**
     * @attr controller returns 'active' if the controller equals the current open controller
     * <br>
     * used for the navigation bar to indicate an active link
     */
    def activeContLink = { attrs, body ->
        out << body() << (attrs.controller == pageScope.controllerName ? "active" : "")
    }

    /**
     * @body the body to be returned in case we are on action album and controller alumb
     */
    def canDownloadSingleImage = { attrs, body ->
        if (pageScope.controllerName == 'album' && pageScope.actionName == 'album')
            out << body()
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
        out << body() << ((attrs.controller == pageScope.controllerName
                && attrs.action == pageScope.actionName) ? "active" : "")
    }

    /**
     * no tests exist for this
     */
    def permissionSelect = { attrs ->
        def allPerms = []
        if (!SecurityUtils.subject.hasRole("Administrator")) {
            allPerms = UserService.getLoggedinUsersPermissions()
        }
        else {
            allPerms = (roleService.getAllPermissions())
        }
        if (allPerms) {
            out << g.select(name: attrs.name, from: allPerms, multiple: true, size: (attrs.size ?: 10), value: (attrs.value ?: []), noSelection: ['': ''], 'class': "many-to-many")
        } else {
            out << "<i>-</i>"
        }
    }

    /**
     * Returns true if the user or his role has one of the given permissions.
     * @attr in the list with the permissions. Must have the form: in="['cont:act:right','cont:act:right2']"
     */
    def hasPermissions = { attrs, body ->
        def isAllowed = false
        for (attr in attrs["in"]) {
            if (SecurityUtils.subject.isPermitted(attr)) {
                isAllowed = true
                break
            }
        }
        if (isAllowed)
            out << body()
    }
}
