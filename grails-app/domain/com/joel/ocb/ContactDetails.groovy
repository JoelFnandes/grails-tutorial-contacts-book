package com.joel.ocb

class ContactDetails {

    Integer id
    String mobile
    String phone
    String email
    String website
    String address
    String type
    Contact contact

    Date dateCreated
    Date lastUpdated


    static constraints = {
        mobile(nullable: true, blank: true)
        phone(nullable: true, blank: true)
        email(nullable: true, blank: true)
        website(nullable: true, blank: true)
        address(nullable: true, blank: true)
    }

    static mapping = {
        version(false)
    }

    def beforeUpdate() {
        if (!this?.mobile && !this?.phone && !this?.email && !this?.website && !this?.address){
            return false
        }

    }

    def beforeInsert() {
        if (!this?.mobile && !this?.phone && !this?.email && !this?.website && !this?.address){
            return false
        }

    }

}
