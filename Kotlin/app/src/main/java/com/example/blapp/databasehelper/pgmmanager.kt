package com.example.blapp.databasehelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.blapp.model.PgmItem
import com.example.blapp.model.StepItem
import android.widget.Toast



class pgmmanager(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null , DATABASE_VER) {
    companion object{
        const val DATABASE_NAME = "BIRDLIGHT.db"
        const val DATABASE_VER = 1

        const val PROGRAM_TABLE = "TABLE_PROGRAM"
        const val PROGRAM_ID = "PROGRAM_ID"
        const val PROGRAM_COMMAND = "PROGRAM_COMMAND"
        const val PROGRAM_NAME = "PROGRAM_NAME"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(("CREATE TABLE $PROGRAM_TABLE ($PROGRAM_ID INTEGER PRIMARY KEY AUTOINCREMENT , $PROGRAM_COMMAND BYTE, $PROGRAM_NAME TEXT)"))
   }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + PROGRAM_TABLE)
    }

    fun eyy(){
        val db: SQLiteDatabase = this.writableDatabase
        val arrTblNames = ArrayList<String>()
        val c = db!!.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null)

        if (c.moveToFirst()) {
            while (!c.isAfterLast) {
                arrTblNames.add(c.getString(c.getColumnIndex("name")))
                c.moveToNext()
            }
        }
    }
//    val allpgm:List<PgmItem>
//
//        get() {
//         val lstpgm = ArrayList<PgmItem>()
//         val selectQuery = "SELECT * FROM $PROGRAM_TABLE"
//         val db: SQLiteDatabase = this.writableDatabase
//         val cursor: Cursor = db.rawQuery(selectQuery, null)
//            if(cursor.moveToFirst()){
//                do{
//                    val pgm = PgmItem()
//                    pgm.pgm_id = cursor.getInt(cursor.getColumnIndex(PROGRAM_ID.toString())).toByte()
//                    pgm.command = cursor.getInt(cursor.getColumnIndex(PROGRAM_COMMAND.toString())).toByte()
//                    pgm.name = cursor.getString(cursor.getColumnIndex(PROGRAM_NAME.toString()))
//
//                    lstpgm.add(pgm)
//                }while(cursor.moveToNext())
//
//            }
//            db.close()
//            return lstpgm
//        }
//
//    fun addPgm(pgm:PgmItem){
//        val db:SQLiteDatabase = this.writableDatabase
//        val values = ContentValues()
//        values.put(PROGRAM_COMMAND,pgm.command)
//        values.put(PROGRAM_NAME, pgm.name)
//        db.insert(PROGRAM_TABLE, null , values)
//        db.close()
//
//    }
//
//    fun updatePgm(pgm:PgmItem): Int{
//        val db:SQLiteDatabase = this.writableDatabase
//        val values = ContentValues()
//        values.put(PROGRAM_ID, pgm.pgm_id)
//        values.put(PROGRAM_COMMAND,pgm.command)
//        values.put(PROGRAM_NAME, pgm.name)
//
//       return db.update(PROGRAM_TABLE, values , "$PROGRAM_ID = ?" , arrayOf(pgm.pgm_id.toString()))
//        db.close()
//    }
//
//    fun deletePgm(pgm: PgmItem){
//        val db:SQLiteDatabase = this.writableDatabase
//
//        db.delete(PROGRAM_TABLE, "$PROGRAM_ID = ?", arrayOf(pgm.pgm_id.toString()))
//        db.close()
//    }
//
//    fun deleteAllPgm(){
//        val db:SQLiteDatabase = this.writableDatabase
//
//        db.execSQL("DELETE from $PROGRAM_TABLE")
//    }

}