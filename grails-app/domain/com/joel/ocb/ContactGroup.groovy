package com.joel.ocb

class ContactGroup {

    Integer id
    String name
    Member member

    Date dateCreated
    Date lastUpdate

    static belongsTo= Contact
    static hasMany = [ contact: Contact]
    static constraints = {
        name(blank:false, nullable: false)

    }
    static mapping = {
        version(false)
    }
}

