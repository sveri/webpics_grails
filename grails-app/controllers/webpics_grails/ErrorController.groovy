package webpics_grails

import grails.util.Environment

class ErrorController {

    def index() {
        try {
            Exception exception = request.exception
            println "Here's what happened: ${exception}"

            switch (Environment.current) {
                case Environment.DEVELOPMENT:
                    render(view: 'development')
                    break
                case Environment.PRODUCTION:
                    render(view: 'production')
                    break
            }
        } catch (Exception e) {
            render(view: 'unhandleable')
        }
    }
}
