package com.example.yows.models

class FoodDetail {
    var name: String? = null
    var detail : String? = null
    var discountPrice : String? = null
    var price : String? = null
    var image : String? = null
    constructor() {}
    constructor(
        name: String?,
        detail: String?,
        discountPrice: String?,
        price: String?,
        image: String?
    ) {
        this.name = name
        this.detail = detail
        this.discountPrice = discountPrice
        this.price = price
        this.image = image
    }


}