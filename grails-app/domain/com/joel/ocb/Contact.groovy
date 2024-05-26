package com.joel.ocb

class Contact {

    Integer id
    String name
    String image
    Member member

    Date dateCreated
    Date lastUpdate

    Set<ContactDetails> contactDetails
    Set<ContactGroup> contactGroup

    static hasMany = [ contactDetails: ContactDetails, contactGroup: ContactGroup]

    static constraints = {
        image(blank:true, nullable: true)

    }
    static mapping = {
        version(false)
        contactDetails(cascade: 'all-delete-orphan')
    }
}
