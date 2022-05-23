package com.daisymai99.onlstay.model

import android.provider.ContactsContract

class User     {

    constructor(id :String, name : String , email: String , password : String){
        this.uid = id
        this.email = email
        this.password = password
        this.name = name
    }

    constructor()

   var name =""
    var email =""
    var password =""
    var uid =""

}