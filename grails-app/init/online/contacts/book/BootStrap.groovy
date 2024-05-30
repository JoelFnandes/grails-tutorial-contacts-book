package online.contacts.book

import com.joel.ocb.AppInitializationService


class BootStrap {

    def init = { servletContext ->
        AppInitializationService.initialize()
    }

    def destroy = {
    }
}
