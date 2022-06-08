package com.daisymai99.onlstay.model

class Room {
    var id = 0
    var noiQuyPhong: String? = null
    var img: String? = null
    var timeNhanPhong: String? = null
    var timeTraPhong: String? = null
    var statusRoom: Boolean? = null
    var price :String ?=null
    var idOwner : String ?=null
    var idUser :String? =null

    constructor() {}
    constructor(
        id: Int,
        noiQuyPhong: String?,
        img: String?,
        timeNhanPhong: String?,
        timeTraPhong: String?,
        statusRoom: Boolean?,
        price :String, idOwner :String?,
        idUser :String?
    ) {
        this.id = id
        this.noiQuyPhong = noiQuyPhong
        this.img = img
        this.timeNhanPhong = timeNhanPhong
        this.timeTraPhong = timeTraPhong
        this.statusRoom = statusRoom
        this.price = price
        this.idOwner = idOwner
        this.idUser = idUser
    }

    constructor(
        id: Int,
        noiQuyPhong: String?,
        img: String?,
        timeNhanPhong: String?,
        statusRoom: Boolean?,
        price :String, idOwner :String?,
        idUser :String?
    ){
        this.id = id
        this.noiQuyPhong = noiQuyPhong
        this.img = img
        this.timeNhanPhong = timeNhanPhong
        this.statusRoom = statusRoom
        this.price = price
        this.idOwner = idOwner
        this.idUser = idUser
    }
}
