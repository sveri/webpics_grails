<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="pix.navigation.albums" /></title>
		
		<script type="text/javascript">
			var GALLERIA_CLASSIC_THEME_LINK = "${resource(dir: 'js/album/galleria/themes/classic/', file: 'galleria.classic.min.js')}";
		</script>
		
	    <r:require modules="album_view"/>
	</head>
	<body>
		<tmpl:albumLeftNav album="${album}" /> 

		<div id="galleria">
			<g:each in="${photos}" var="photo">
				<g:link action="getFile" params="[photoid: photo.id, size: '']">
					<img data-big="${createLink(action: 'getFile', params: [photoid: photo.id, size: 'big'] )}"
						src="${createLink(action: 'getFile', params: [photoid: photo.id, size: 'thumbs'] )}" />
				</g:link>
			</g:each>
	    </div>
	    
	    <div id="galleria-button-row" class="row" style="width: 900px;">
	    	<div class="span4">
		    	<button id="rotate-left" class="btn btn-primary" 
		    		title="${message(code: 'pix.album.album.button.rotate_left.title')}">
	    			<g:message code="pix.album.album.button.rotate_right.text" /></button>
	    	</div>
	    	<div class="span4">
			    <button id="fullscreen" class="btn btn-primary">
			    	<g:message code="pix.album.album.button.fullscreen" /></button>
	    	</div>
	    	<div class="span4">
		    	<button id="rotate-right" class="btn btn-primary" 
		    		title="${message(code: 'pix.album.album.button.rotate_right.title').encodeAsHTML()}">
		    		<g:message code="pix.album.album.button.rotate_right.text" /></button>
	    	</div>
	    </div>
	</body>
</html>