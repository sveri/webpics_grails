<script type="text/javascript">
    var GALLERIA_CLASSIC_THEME_LINK = "${resource(dir: 'js/album/galleria/themes/classic/', file: 'galleria.classic.min.js')}";
    var SAVE_ROTATION_STATE = "${createLink(action: 'rotateImage')}";

    var dataGalleria = [
        <g:each in="${photos}" var="photo">
            {
                thumb: "${createLink(action: 'getFile', params: [photoid: photo.id, size: 'thumbs'] )}",
                image: "${createLink(action: 'getFile', params: [photoid: photo.id] )}",
                big: "${createLink(action: 'getFile', params: [photoid: photo.id, size: 'big'] )}",
                rotVal: '${photo.rotVal}',
                photoId: '${photo.id}'
            },
        </g:each>
    ];
</script>