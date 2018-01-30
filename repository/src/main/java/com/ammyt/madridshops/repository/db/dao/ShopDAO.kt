package com.ammyt.madridshops.repository.db.dao

import com.ammyt.madridshops.repository.db.DBConstants
import com.ammyt.madridshops.repository.db.DBHelper
import com.ammyt.madridshops.repository.model.ShopEntity
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class ShopDAO(val dbHelper: DBHelper): DAOPersistable<ShopEntity> {

    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase


    override fun query(id: Long): ShopEntity {
        val cursor = queryCursor(id)

        // DOS Cursores extra: 'BEFORE_FIRST' y 'AFTER_LAST'. El cursor comienza en 'BEFORE_FIRST'.
        // Con moveToNext() se mueve al siguiente y lee. Si llega a 'AFTER_LAST' para de leer.
        cursor.moveToFirst()

        // Podríamos hacer una extensión de Cursor para poder hacer esto: cursor.readString
        return entityFromCursor(cursor)!!
    }

    override fun query(): List<ShopEntity> {
        val queryResult = ArrayList<ShopEntity>()

        // SELECT * FROM TABLE_SHOP
        val cursor = dbReadOnlyConnection.query(
                DBConstants.TABLE_SHOP,
                DBConstants.ALL_COLUMNS,
                null,
                null,
                "",
                "",
                DBConstants.KEY_SHOP_DATABASE_ID)

        while (cursor.moveToNext()) {
            val shopEntity = entityFromCursor(cursor)
            queryResult.add(shopEntity!!)
        }

        return queryResult
    }

    fun entityFromCursor(cursor: Cursor): ShopEntity? {
        if (cursor.isAfterLast || cursor.isBeforeFirst) return null

        return ShopEntity(
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_SHOP_ID_JSON)),
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_SHOP_DATABASE_ID)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_DESCRIPTION)),
                cursor.getFloat(cursor.getColumnIndex(DBConstants.KEY_SHOP_LATITUDE)),
                cursor.getFloat(cursor.getColumnIndex(DBConstants.KEY_SHOP_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_OPENING_HOURS)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_ADDRESS))
        )
    }

    override fun queryCursor(id: Long): Cursor {
        val cursor = dbReadOnlyConnection.query(
                DBConstants.TABLE_SHOP,
                DBConstants.ALL_COLUMNS,
                DBConstants.KEY_SHOP_DATABASE_ID + " = ?",
                arrayOf(id.toString()),
                "",
                "",
                DBConstants.KEY_SHOP_DATABASE_ID)

        return cursor
    }

    override fun insert(element: ShopEntity): Long {
        var id: Long = -1

        // Deberíamos verificar si los datos de entrada son correctos, qué resultado (id)
        // nos devuelve la operación, etc
        id = dbReadWriteConnection.insert(DBConstants.TABLE_SHOP, null, contentValues(element))

        return id
    }

    fun contentValues(shopEntity: ShopEntity): ContentValues {
        val content = ContentValues()

        // El id de la database lo genera solo si no se lo indicamos
        content.put(DBConstants.KEY_SHOP_ID_JSON, shopEntity.id)
        content.put(DBConstants.KEY_SHOP_NAME, shopEntity.name)

        // Optionals
        content.put(DBConstants.KEY_SHOP_DESCRIPTION, shopEntity.description)
        content.put(DBConstants.KEY_SHOP_LATITUDE, shopEntity.latitude)
        content.put(DBConstants.KEY_SHOP_LONGITUDE, shopEntity.longitude)
        content.put(DBConstants.KEY_SHOP_IMAGE_URL, shopEntity.image)
        content.put(DBConstants.KEY_SHOP_LOGO_IMAGE_URL, shopEntity.logo)
        content.put(DBConstants.KEY_SHOP_ADDRESS, shopEntity.address)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS, shopEntity.openingHours)

        return content
    }

    override fun update(id: Long, element: ShopEntity): Long {
        // Devuelve el número de registros actualizados
        return dbReadWriteConnection.update(
                DBConstants.TABLE_SHOP,
                contentValues(element),
                DBConstants.KEY_SHOP_DATABASE_ID + " = ? ",
                arrayOf(id.toString())
        ).toLong()
    }

    override fun delete(element: ShopEntity): Long {
        if (element.databaseId < 1) return 0

        return delete(element.databaseId)
    }

    override fun delete(id: Long): Long {
        // Con '?' se evita la inyección de código, pues el ? se resuelve mirando el array
        // Devuelve el número de registros borrados
        return dbReadWriteConnection.delete(
                DBConstants.TABLE_SHOP,
                DBConstants.KEY_SHOP_DATABASE_ID + " = ?",
                arrayOf(id.toString())
                ).toLong()
    }

    override fun deleteAll(): Boolean {
        return dbReadWriteConnection.delete(
                DBConstants.TABLE_SHOP,
                null,
                null
        ).toLong() > 0
    }

}