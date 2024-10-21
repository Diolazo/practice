package com.example.practice.data.files

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import org.mindrot.jbcrypt.BCrypt

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val CREATE_TABLE_USER = "CREATE TABLE $TABLE_USER (" +
            "$COL_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$COL_USER_NAME TEXT," +
            "$COL_USER_EMAIL TEXT UNIQUE," +
            "$COL_USER_NUMBER TEXT," +
            "$COL_USER_PASSWORD TEXT," +
            "$COL_USER_CONFIRM TEXT)"

    private val CREATE_TABLE_PRODUCT = "CREATE TABLE $TABLE_PRODUCT (" +
            "$COL_PRODUCT_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$COL_PRODUCT_TITLE TEXT," +
            "$COL_PRODUCT_PRICE TEXT," +
            "$COL_PRODUCT_CATEGORY TEXT," +
            "$COL_PRODUCT_IMAGE_URI TEXT)"

    private val CREATE_TABLE_CART = "CREATE TABLE $TABLE_CART (" +
            "$COL_CART_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$COL_CART_PRODUCT_ID INTEGER, " +
            "$COL_CART_PRODUCT_NAME TEXT, " +
            "$COL_CART_PRODUCT_IMAGE_URI TEXT, " +
            "$COL_CART_IS_SELECTED INTEGER DEFAULT 0)"

    private val DROP_TABLE_USER = "DROP TABLE IF EXISTS $TABLE_USER"
    private val DROP_TABLE_PRODUCT = "DROP TABLE IF EXISTS $TABLE_PRODUCT"
    private val DROP_TABLE_CART = "DROP TABLE IF EXISTS $TABLE_CART"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_USER)
        db.execSQL(CREATE_TABLE_PRODUCT)
        db.execSQL(CREATE_TABLE_CART)
        insertDefaultAdmin(db)
        Log.d("DatabaseHelper", "Tables created: $TABLE_USER, $TABLE_PRODUCT")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE_USER)
        db.execSQL(DROP_TABLE_PRODUCT)
        db.execSQL(DROP_TABLE_CART)
        onCreate(db)
        Log.d("DatabaseHelper", "Database upgraded from version $oldVersion to $newVersion")
    }

    fun addProduct(product: Product): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COL_PRODUCT_TITLE, product.title)
            put(COL_PRODUCT_PRICE, product.price)
            put(COL_PRODUCT_CATEGORY, product.category)
            put(COL_PRODUCT_IMAGE_URI, product.imageUri)
        }

        val id = db.insert(TABLE_PRODUCT, null, values)
        Log.d("DatabaseHelper", "Product added: ID = $id, Title = ${product.title}")
        return id
    }

    fun deleteProduct(productId: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_PRODUCT, "$COL_PRODUCT_ID = ?", arrayOf(productId.toString()))
    }

    fun getAllProducts(): List<Product> {
        val productList = mutableListOf<Product>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_PRODUCT", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_PRODUCT_ID))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(COL_PRODUCT_TITLE))
                val price = cursor.getString(cursor.getColumnIndexOrThrow(COL_PRODUCT_PRICE))
                val category = cursor.getString(cursor.getColumnIndexOrThrow(COL_PRODUCT_CATEGORY))
                val imageUri = cursor.getString(cursor.getColumnIndexOrThrow(COL_PRODUCT_IMAGE_URI))

                val product = Product(id, title, price, category, imageUri)
                productList.add(product)
            } while (cursor.moveToNext())
        }
        cursor.close()

        Log.d("Database", "Fetched products: $productList")
        return productList
    }

    private fun insertDefaultAdmin(db: SQLiteDatabase) {
        val cursor = db.query(TABLE_USER, null, "$COL_USER_EMAIL = ?", arrayOf("admin4@gmail.com"), null, null, null)

        if (cursor?.count == 0) {
            val values = ContentValues()
            values.put(COL_USER_EMAIL, "admin4@gmail.com")
            val defaultPassword = "admin123"
            values.put(COL_USER_PASSWORD, BCrypt.hashpw(defaultPassword, BCrypt.gensalt()))
            values.put(COL_USER_CONFIRM, defaultPassword)

            db.insert(TABLE_USER, null, values)
        }
        cursor.close()
    }

    fun registerUser(user: Users): Long {
        val db = this.writableDatabase
        if (checkUserExists(user.email)) {
            throw Exception("User already exists")
        }

        val values = ContentValues()
        values.put(COL_USER_NAME, user.name)
        values.put(COL_USER_EMAIL, user.email)
        values.put(COL_USER_PASSWORD, user.password)
        values.put(COL_USER_CONFIRM, user.confirmPassword)

        return try {
            db.insert(TABLE_USER, null, values)
        } catch (e: Exception) {
            e.printStackTrace()
            -1
        } finally {
            db.close()
        }
    }

    fun deleteUser(userId: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_USER, "$COL_USER_ID = ?", arrayOf(userId.toString()))
    }

    fun getUserNameByEmail(email: String): String? {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_USER, arrayOf(COL_USER_NAME),
            "$COL_USER_EMAIL = ?", arrayOf(email), null, null, null
        )

        return if (cursor.moveToFirst()) {
            val userName = cursor.getString(cursor.getColumnIndexOrThrow(COL_USER_NAME))
            cursor.close()
            userName
        } else {
            cursor.close()
            null
        }
    }


    fun getAllUsers(): List<Users> {
        val userList = mutableListOf<Users>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USER", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_USER_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COL_USER_NAME)) ?: "Unknown"
                val email = cursor.getString(cursor.getColumnIndexOrThrow(COL_USER_EMAIL)) ?: "No Email"

                val user = Users(id, name, email, "", "") // Adjust as needed
                userList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        Log.d("Database", "Fetched users: $userList")
        return userList
    }


    fun checkUserExists(email: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USER WHERE $COL_USER_EMAIL = ?"
        val cursor = db.rawQuery(query, arrayOf(email))

        return try {
            cursor.moveToFirst()
        } finally {
            cursor.close()
        }
    }

    fun loginUser(email: String, password: String): Boolean {
        val columns = arrayOf(COL_USER_ID)
        val db = this.readableDatabase
        val selection = "$COL_USER_EMAIL = ?"
        val cursor = db.query(
            TABLE_USER,
            columns,
            selection,
            arrayOf(email),
            null,
            null,
            null,
        )

        val cursorCount = cursor.count
        cursor.close()
        return cursorCount > 0
    }

    fun addCartItem(cartItem: Cart): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COL_CART_PRODUCT_ID, cartItem.productId)
            put(COL_CART_PRODUCT_NAME, cartItem.productName)
            put(COL_CART_PRODUCT_IMAGE_URI, cartItem.productImageUri)
        }

        val id = db.insert(TABLE_CART, null, values)
        Log.d("DatabaseHelper", "Cart item added: ID = $id, Product Name = ${cartItem.productName}")
        return id
    }

    fun getCartItems(): List<Cart> {
        val cartList = mutableListOf<Cart>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_CART", null)

        if (cursor.moveToFirst()) {
            do {
                val productId = cursor.getInt(cursor.getColumnIndexOrThrow(COL_CART_PRODUCT_ID))
                val productName = cursor.getString(cursor.getColumnIndexOrThrow(COL_CART_PRODUCT_NAME))
                val productImageUri = cursor.getString(cursor.getColumnIndexOrThrow(COL_CART_PRODUCT_IMAGE_URI))

                val cartItem = Cart(productId, productName, productImageUri)
                cartList.add(cartItem)
            } while (cursor.moveToNext())
        }
        cursor.close()

        Log.d("Database", "Fetched cart items: $cartList")
        return cartList
    }

    fun deleteCartItem(productId: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_CART, "$COL_CART_PRODUCT_ID = ?", arrayOf(productId.toString()))
    }


    companion object {
        private const val DATABASE_VERSION = 3
        private const val DATABASE_NAME = "user.db"
        private const val TABLE_USER = "tbl_user"
        private const val COL_USER_ID = "user_id"
        private const val COL_USER_NAME = "user_name"
        private const val COL_USER_NUMBER = "user_number"
        private const val COL_USER_EMAIL = "user_email"
        private const val COL_USER_PASSWORD = "user_password"
        private const val COL_USER_CONFIRM = "user_confirm"

        private const val TABLE_PRODUCT = "tbl_product"
        private const val COL_PRODUCT_ID = "product_id"
        private const val COL_PRODUCT_TITLE = "product_title"
        private const val COL_PRODUCT_PRICE = "product_price"
        private const val COL_PRODUCT_CATEGORY = "product_category"
        private const val COL_PRODUCT_IMAGE_URI = "product_image_uri"

        private const val TABLE_CART = "tbl_cart"
        private const val COL_CART_ID = "cart_id"
        private const val COL_CART_PRODUCT_ID = "cart_product_id"
        private const val COL_CART_PRODUCT_NAME = "cart_product_name"
        private const val COL_CART_PRODUCT_IMAGE_URI = "cart_product_image_uri"
        private const val COL_CART_IS_SELECTED = "cart_is_selected"
    }
}
