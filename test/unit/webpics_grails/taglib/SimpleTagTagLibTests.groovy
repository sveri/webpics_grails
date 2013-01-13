package webpics_grails.taglib



import grails.test.mixin.*
import org.junit.*

import webpics_grails.SimpleTagTagLib

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(SimpleTagTagLib)
class SimpleTagTagLibTests {

    @Test
    @Ignore
    void testActiveController() {
	pageScope.controllerName = "testC"
	assert applyTemplate('<g:activeContLink controller="testC" />') == 'active'
	assert applyTemplate('<g:activeContLink controller="tesC1" />') == ''
    }

    @Test
    @Ignore
    void testActiveControllerAndAction() {
	pageScope.controllerName = "testC"
	pageScope.actionName = "testAction"
	assert applyTemplate('<g:activeContActLink controller="testC" action="testAction" />') == 'active'
	assert applyTemplate('<g:activeContActLink controller="testC" />') == ''
	assert applyTemplate('<g:activeContActLink controller="testC" action="af" />') == ''
	assert applyTemplate('<g:activeContActLink action="testAction" />') == ''
    }
}
