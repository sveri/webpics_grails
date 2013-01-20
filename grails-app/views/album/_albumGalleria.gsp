<div id="galleria">
</div>
   
<div id="galleria-button-row" class="row" style="width: 900px;">
   	<div class="span4">
    	<button id="rotate-left" class="btn" 
    		title="${message(code: 'pix.album.album.button.rotate_left.title')}">
    		<img alt="${message(code: 'pix.album.album.button.rotate_left.text')}" 
    			src="${resource(dir: 'images', file: 'arrow-turn-270-left-icon.png')}">
   		</button>
   	</div>
   	<div class="span4">
	    <button id="fullscreen" class="btn">
	    	<g:message code="pix.album.album.button.fullscreen" /></button>
   	</div>
   	<div class="span4">
    	<button id="rotate-right" class="btn" 
    		title="${message(code: 'pix.album.album.button.rotate_right.title')}">
    		<img alt=""${message(code: 'pix.album.album.button.rotate_right.text')}" 
    		src="${resource(dir: 'images', file: 'arrow-turn-270-icon.png')}">
   		</button>
   	</div>
</div>