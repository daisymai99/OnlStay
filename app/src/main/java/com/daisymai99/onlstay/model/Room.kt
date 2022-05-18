package com.daisymai99.onlstay.model

class Room(var namehotel:String, var addd: String, var id :Int, var noiquy :String, var image :String ,var priceRoom: Int, var timeTake : String, var timeOut:String, var status :Int ){



    var nameHotel : String =""

    var adddress :String =""
    var idRoom : Int =0
    var noiQuyRoom : String  =""
    var img : String =""
    var price :Int =0
    var timeNhanRoom : String =""
    var timeTraRoom :String =" "
    var statusRoom :Int = 0

    constructor() : this("","",0,"","",0,"00:00","00:00",0) {
    }



}