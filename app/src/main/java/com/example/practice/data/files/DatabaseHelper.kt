package com.example.practice.data.files

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.mindrot.jbcrypt.BCrypt

class DatabaseHelper(context:Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val CREATE_TABLE_USER = "CREATE TABLE $TABLE_USER (" +
            "$COL_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$COL_USER_NAME TEXT," +
            "$COL_USER_EMAIL TEXT UNIQUE," +  // Added UNIQUE constraint
            "$COL_USER_PASSWORD TEXT," +
            "$COL_USER_CONFIRM TEXT)"

    private val DROP_TABLE_USER = "DROP TABLE IF EXISTS $TABLE_USER"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USER)
        insertDefaultAdmin(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE_USER)
        onCreate(db)
    }

    private fun insertDefaultAdmin(db: SQLiteDatabase?){
        var cursor = db?.query(TABLE_USER, null,"$COL_USER_EMAIL = ?", arrayOf("admin@gmail.com"), null, null, null)

        if (cursor?.count == 0){
            val values = ContentValues()
            values.put(COL_USER_NAME, "Admin")
            values.put(COL_USER_EMAIL, "admin@gmail.com")
            val defaultPassword = "admin123"
            values.put(COL_USER_PASSWORD, BCrypt.hashpw(defaultPassword, BCrypt.gensalt()))
            values.put(COL_USER_CONFIRM, defaultPassword)

            db?.insert(TABLE_USER, null, values)
        }
        cursor?.close()
    }

    fun registerUser(user : Users) {
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(COL_USER_NAME, user.name)
        value.put(COL_USER_EMAIL, user.email)
        value.put(COL_USER_PASSWORD, user.password)
        value.put(COL_USER_CONFIRM, user.confirmPassword)

        try {
            db.insert(TABLE_USER, null, value)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.close()
        }
    }

    fun loginUser(email: String, password: String): Boolean {
        val columns = arrayOf(COL_USER_ID)
        val db = this.readableDatabase
        val selection = "$COL_USER_EMAIL = ? AND $COL_USER_PASSWORD = ?"
        val selectionArgs = arrayOf(email, password)

        val cursor = db.query(
            TABLE_USER,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null,
        )

        val cursorCount = cursor.count
        cursor.close()
        db.close()

        return cursorCount > 0
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "user.db"
        private const val TABLE_USER = "tbl_user"
        private const val COL_USER_ID = "user_id"
        private const val COL_USER_NAME = "user_name"
        private const val COL_USER_EMAIL = "user_email"
        private const val COL_USER_PASSWORD = "user_password"
        private const val COL_USER_CONFIRM = "user_confirm"
    }
}
