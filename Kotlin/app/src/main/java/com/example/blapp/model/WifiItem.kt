package com.example.blapp.model

class WifiItem{
    var name: String? = ""
    var status: Int? = 0
    var level: Int? = 0

        //Getter
        get() = field
        //Setter
        set(value){
            field = value
        }
}