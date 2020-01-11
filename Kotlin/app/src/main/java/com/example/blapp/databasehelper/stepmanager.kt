package com.example.blapp.databasehelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.fragment.app.FragmentActivity
import com.example.blapp.ProgramFragment
import com.example.blapp.model.StepItem

class stepmanager(context: FragmentActivity?):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object{
        const val DATABASE_NAME = "BIRDLIGHT.db"
        const val DATABASE_VER = 1

        const val STEP_TABLE = "TABLE_STEP"
        const val STEP_ID = "STEP_ID"
        const val STEP_COMMAND = "STEP_COMMAND"
        const val STEP_STEP = "STEP_STEP"
        const val STEP_PAN = "STEP_PAN"
        const val STEP_TILT = "STEP_TILT"
        const val STEP_BLINK = "STEP_BLINK"
        const val STEP_TIME = "STEP_TIME"
        const val STEP_PGM = "STEP_PGM"


        const val SEQUENCE_TABLE = "TABLE_SEQUENCE"
        const val SEQUENCE_ID = "SEQUENCE_ID"
        const val SEQUENCE_COMMAND = "SEQUENCE_COMMAND"
        const val SEQUENCE_SQN = "SEQUENCE_SQN"
        const val SEQUENCE_SHOUR = "SEQUENCE_SHOUR"
        const val SEQUENCE_SMINUTE = "SEQUENCE_SMINUTE"
        const val SEQUENCE_EHOUR = "SEQUENCE_EHOUR"
        const val SEQUENCE_EMINUTE = "SEQUENCE_EMINUTE"
        const val SEQUENCE_NAME = "SEQUENCE-NAME"

        const val SEQ_PROGRAM_TABLE = "TABLE_SEQ_PROGRAM"
        const val SEQ_PROGRAM_ID = "SEQ_PROGRAM_ID"
        const val SEQ_PROGRAM_COMMAND = "SEQ_PROGRAM_COMMAND"
        const val SEQ_PROGRAM_SQN = "SEQ_PROGRAM_SQN"
        const val SEQ_PROGRAM_ORDER = "SEQ_PROGRAM_ORDER"
        const val SEQ_PROGRAM_PGM = "SEQ_PROGRAM_PGM"
        const val SEQ_PROGRAM_DURATION = "SEQ_PROGRAM_DURATION"

        const val SETUP_TABLE = "TABLE_SETUP"
        const val SETUP_ID = "SETUP_ID"
        const val SETUP_COMMAND = "SETUP_COMMAND"
        const val SETUP_STP = "SETUP_STP"
        const val SETUP_SMONTH = "SETUP_SMONTH"
        const val SETUP_SDAY = "SETUP_SDAY"
        const val SETUP_EMONTH = "SETUP_EMONTH"
        const val SETUP_EDAY = "SETUP_EDAY"
        const val SETUP_NAME = "SETUP_NAME"

        const val SETUP_SEQ_TABLE = "TABLE_SETUP_SEQ"
        const val SETUP_SEQ_ID = "SETUP_SEQ_ID"
        const val SETUP_SEQ_COMMAND = "SETUP_SEQ_COMMAND"
        const val SETUP_SEQ_STP = "SETUP_SEQ_STP"
        const val SETUP_SEQ_SQN = "SETUP_SEQ_SQN"
        const val SETUP_SEQ_DAY = "SETUP_SEQ_DAY"


    }
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(("CREATE TABLE $STEP_TABLE ($STEP_ID INTEGER PRIMARY KEY AUTOINCREMENT, $STEP_COMMAND BYTE, $STEP_STEP BYTE, $STEP_PAN BYTE,$STEP_TILT BYTE , $STEP_BLINK BYTE , $STEP_TIME BYTE , $STEP_PGM BYTE)"))

        db!!.execSQL(("CREATE TABLE $SEQUENCE_TABLE ($SEQUENCE_ID INTEGER PRIMARY KEY AUTOINCREMENT, $SEQUENCE_COMMAND BYTE , $SEQUENCE_SQN BYTE , $SEQUENCE_SHOUR BYTE,$SEQUENCE_SMINUTE BYTE, $SEQUENCE_EHOUR BYTE , $SEQUENCE_EMINUTE BYTE, $SEQUENCE_NAME TEXT)"))
        db!!.execSQL(("CREATE TABLE $SEQ_PROGRAM_TABLE ($SEQ_PROGRAM_ID PRIMARY KEY AUTOINCREMENT , $SEQ_PROGRAM_COMMAND BYTE , $SEQ_PROGRAM_SQN BYTE , $SEQ_PROGRAM_ORDER BYTE , $SEQ_PROGRAM_PGM BYTE , $SEQ_PROGRAM_DURATION BYTE)"))
        db!!.execSQL(("CREATE TABLE $SETUP_TABLE ($SETUP_ID INTEGER PRIMARY KEY AUTOINCREMENT , $SETUP_COMMAND BYTE, SETUP_STP BYTE , $SETUP_SMONTH BYTE , $SETUP_SDAY BYTE , $SETUP_EMONTH BYTE, $SETUP_EDAY BYTE , $SETUP_NAME TEXT)"))
        db!!.execSQL(("CREATE TABLE $SETUP_SEQ_TABLE ($SETUP_SEQ_ID INTEGER PRIMARY KEY AUTOINCREMENT , $SETUP_SEQ_COMMAND BYTE , $SETUP_SEQ_STP BYTE , $SETUP_SEQ_SQN BYTE , $SETUP_SEQ_DAY BYTE)"))


    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + STEP_TABLE)

        db.execSQL("DROP TABLE IF EXISTS " + SEQUENCE_TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + SEQ_PROGRAM_TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + SETUP_TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + SETUP_SEQ_TABLE)
        onCreate(db)
    }


    val allStep:List<StepItem>

        get(){
            val lstStep = ArrayList<StepItem>()
            val selectQuery = "SELECT * FROM $STEP_TABLE"
            val db:SQLiteDatabase = this.writableDatabase
            val cursor: Cursor = db.rawQuery(selectQuery, null)
            if(cursor.moveToFirst()){
                do{
                    val step = StepItem()
                    step.step_id = cursor.getInt(cursor.getColumnIndex(STEP_ID.toString())).toByte()
                    step.pgm = cursor.getInt(cursor.getColumnIndex(STEP_PGM.toString())).toByte()
                    step.step = cursor.getInt(cursor.getColumnIndex(STEP_STEP.toString())).toByte()
                    step.pan = cursor.getInt(cursor.getColumnIndex(STEP_PAN.toString())).toByte()
                    step.tilt = cursor.getInt(cursor.getColumnIndex(STEP_TILT.toString())).toByte()
                    step.blink = cursor.getInt(cursor.getColumnIndex(STEP_BLINK.toString())).toByte()
                    step.time = cursor.getInt(cursor.getColumnIndex(STEP_TIME.toString())).toByte()

                    lstStep.add(step)

                }while(cursor.moveToNext())

            }
            db.close()
            return lstStep
        }


    fun addStep(step:StepItem){
        val db:SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(STEP_PGM,step.pgm)
        values.put(STEP_STEP, step.step)
        values.put(STEP_PAN, step.pan)
        values.put(STEP_TILT, step.tilt)
        values.put(STEP_BLINK, step.blink)
        values.put(STEP_TIME, step.time)

        db.insert(STEP_TABLE , null , values)
        db.close()
    }


    fun updateStep(step: StepItem): Int {
        val db:SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(STEP_PGM,step.pgm)
        values.put(STEP_STEP, step.step)
        values.put(STEP_PAN, step.pan)
        values.put(STEP_TILT, step.tilt)
        values.put(STEP_BLINK, step.blink)
        values.put(STEP_TIME, step.time)

        return db.update(STEP_TABLE , values , "$STEP_ID =" , arrayOf(step.step_id.toString()))
        db.close()
    }

    fun deleteStep(step: StepItem){
        val db:SQLiteDatabase = this.writableDatabase

        db.delete(STEP_TABLE , "$STEP_ID = ?" , arrayOf(step.step_id.toString()))
        db.close()
    }
}