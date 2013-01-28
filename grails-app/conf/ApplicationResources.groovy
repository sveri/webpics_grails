modules = {
    application { resource url: 'js/main.js' }

    album_view {
        resource url: '/js/album/galleria/galleria-1.2.9.min.js'
        resource url: '/js/album/album.js'
        resource url: '/js/album/jQueryRotateCompressed.2.2.js'
        resource url: '/js/album/galleria/themes/classic/galleria.classic.css'
        resource url: '/css/album/album.css'
    }

    album_upload {
        resource url: '/js/album/uploader.js'
        resource url: '/js/album/fileuploader.min.js'
        resource url: '/css/album/fileuploader.css'
    }
}