package com.example.blapp.model

class PgmItem
{
    var pgm_id: Byte? = 0
    var command: Byte? = 0
    var pgm: Byte? = 0
    var name: String = ""

        //Getter
        get() = field
        //Setter
        set(value){
            field = value
        }
}