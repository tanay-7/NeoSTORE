package com.example.neostore.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.neostore.ui.mvp.productdetails.ProductDetailsData
import com.example.neostore.ui.mvvm.mycart.MyCartResponse

class DBHandlerProductDetails(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "DB_NeoSTORE.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "product_details"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE $TABLE_NAME " +
                    "(PRODUCT_ID INTEGER PRIMARY KEY ," +
                    "PRODUCT_NAME TEXT," +
                    "PRODUCT_PRICE INTEGER," +
                    "PRODUCT_CATEGORY TEXT," +
                    "PRODUCT_PRODUCER TEXT," +
                    "PRODUCT_DESCRIPTION," +
                    "PRODUCT_RATING TEXT," +
                    "PRODUCT_IMAGE TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(
            "DROP TABLE IF EXISTS $TABLE_NAME"
        )
        onCreate(db)
    }

    fun insertData(obj: ProductDetailsData): String? {
        var productCategory = when (obj.productCategoryId) {
            1 -> "Table"
            2 -> "Chair"
            3 -> "Sofa"
            4 -> "Cupboard"
            else -> ""
        }
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("PRODUCT_ID", obj.id)
        contentValues.put("PRODUCT_NAME", obj.name)
        contentValues.put("PRODUCT_PRICE", obj.cost)
        contentValues.put("PRODUCT_CATEGORY", productCategory)
        contentValues.put("PRODUCT_PRODUCER", obj.producer)
        contentValues.put("PRODUCT_DESCRIPTION", obj.description)
        contentValues.put("PRODUCT_RATING", obj.rating)
        contentValues.put("PRODUCT_IMAGE", obj.created)
        return try {
            val result = db.insertOrThrow(TABLE_NAME, null, contentValues)
            if (result == (-1).toLong()) {
                "Unsuccessful"
            } else {
                "Success"
            }
        } catch (e: android.database.sqlite.SQLiteConstraintException) {
            e.message
        }
    }

    fun retrieveData(): ArrayList<MyCartResponse> {
        val productList: ArrayList<MyCartResponse> = arrayListOf()
        val cursor: Cursor = readableDatabase.query(
            TABLE_NAME,
            arrayOf(
                "PRODUCT_ID",
                "PRODUCT_NAME",
                "PRODUCT_PRICE",
                "PRODUCT_CATEGORY",
                "PRODUCT_PRODUCER",
                "PRODUCT_DESCRIPTION",
                "PRODUCT_RATING",
                "PRODUCT_IMAGE"
            ),
            null,
            null,
            null,
            null,
            null
        )
        cursor.use { cursor ->
            if (cursor.count != 0) {
                cursor.moveToFirst()
                if (cursor.count > 0) {
                    do {
                        val productId: Int = cursor.getInt(cursor.getColumnIndex("PRODUCT_ID"))
                        val productName: String = cursor.getString(cursor.getColumnIndex("PRODUCT_NAME"))
                        val productPrice: Int = cursor.getInt(cursor.getColumnIndex("PRODUCT_PRICE"))
                        val productCategory: String = cursor.getString(cursor.getColumnIndex("PRODUCT_CATEGORY"))
                        val productProducer: String = cursor.getString(cursor.getColumnIndex("PRODUCT_PRODUCER"))
                        val productDescription: String = cursor.getString(cursor.getColumnIndex("PRODUCT_DESCRIPTION"))
                        val productRating: Number = cursor.getInt(cursor.getColumnIndex("PRODUCT_RATING"))
                        val productImage: String = cursor.getString(cursor.getColumnIndex("PRODUCT_IMAGE"))
                        var productObject = MyCartResponse(
                            productId,
                            productName,
                            productPrice,
                            productCategory,
                            productProducer,
                            productDescription,
                            productRating,
                            productImage
                        )
                        productList.add(productObject)
                    } while ((cursor.moveToNext()))
                }
            }
        }
        return productList
    }
}

/*productObject.id = productId
productObject.name = productName
productObject.cost = productPrice
productObject.producer = productProducer
productObject.description = productDescription
productObject.rating = Integer.valueOf(productRating)
productObject.created = productImage*/