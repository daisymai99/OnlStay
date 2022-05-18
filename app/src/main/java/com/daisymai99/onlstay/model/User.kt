package com.daisymai99.onlstay.model

import android.provider.ContactsContract

class User     {

    constructor( name : String , email: ContactsContract.CommonDataKinds.Email ,  itemLike : ArrayList<Hotel>, itemBook : ArrayList<Hotel>){

    }

    constructor()

    var u_name: String = ""
        private set

    fun user_name(n: String) {
        u_name = n             // we set the name here
    }


}