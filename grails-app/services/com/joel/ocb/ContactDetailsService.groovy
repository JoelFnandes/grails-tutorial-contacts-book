package com.joel.ocb


class ContactDetailsService {

    private def getContactDetailsParamsParse(Contact contact, def params, Integer id = null) {
        def map = [
                id     : null,
                mobile : null,
                phone  : null,
                email  : null,
                website: null,
                address: null,
                type   : null,
                contact: contact,
        ]

        // Verificar e definir o ID
        if (id == null && params.detailsId) {
            map.id = params.detailsId
        } else if (id != null) {
            try {
                if (params["detailsId"]?.getAt(id)) {
                    map.id = params["detailsId"][id]
                }
            } catch (Exception e) {
                // Optionally log the exception here
            }
        }

        // Preencher outros campos somente se o ID estiver presente

        def keys = ['mobile', 'phone', 'email', 'website', 'address', 'type']

        keys.each { key ->
            if (id == null && params[key]) {
                map[key] = params[key]?: ""
            } else if (id != null) {
                try {
                    if (params[key]?.getAt(id)) {
                        map[key] = params[key]?.getAt(id)?:""
                    }
                } catch (Exception e) {
                    // Optionally log the exception here
                }
            }
        }


        return map
    }

    private def saveOrUpdate(def map) {
        if (map && map.id) {
            ContactDetails contactDetails
            contactDetails = getById(map.id) ?: new ContactDetails()
            contactDetails.properties = map
            contactDetails.save(flush: true)
        } else {
            if (map?.mobile || map?.phone || map?.email || map?.website || map?.address) {
                ContactDetails contactDetails
                contactDetails = new ContactDetails(map)
                contactDetails.save(flush: true)
            }
        }
    }


    def createOrUpdateDetails(Contact contact, def params) {

        if (params.type instanceof String) {
            if (!(params.mobile == "" && params.phone == "" && params.email == "" && params.website == "" && params.address == "")) {
                saveOrUpdate(getContactDetailsParamsParse(contact, params))
            }

        } else if (params.type && params.type.getClass().isArray()) {
            Integer index = 0
            params.type.each {
                saveOrUpdate(getContactDetailsParamsParse(contact, params, index))
                index++
            }
        }
    }


    def getById(Serializable id) {
        return ContactDetails.get(id)
    }

    def deleteContactDetails(Serializable id) {
        ContactDetails contactDetails = getById(id)
        if (contactDetails) {
            contactDetails.delete(flush: true)
            return AppUtil.infoMessage("Deleted")
        }
        return AppUtil.infoMessage("Unable to Delete", false)
    }

    def getContactDetailsListByContact(Contact contact) {
        if (contact) {
            return ContactDetails.createCriteria().list {
                eq("contact", contact)
            }
        }
        return []
    }

}
