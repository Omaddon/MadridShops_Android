package ammyt.com.repository.db.dao

import ammyt.com.repository.db.DBConstants
import ammyt.com.repository.db.DBHelper
import ammyt.com.repository.model.ShopEntity
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class ShopDAO(val dbHelper: DBHelper): DAOPersistable<ShopEntity> {

    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase


    override fun query(id: Long): ShopEntity {
        val cursor = queryCursor(id)

        // DOS Cursores extra: 'BEFORE_FIRST' y 'AFTER_LAST'. El cursor comienza en 'BEFORE_FIRST'.
        // Con moveToNext() se mueve al siguiente y lee. Si llega a 'AFTER_LAST' para.
        cursor.moveToFirst()

        // Podríamos hacer una extensión de Cursor para poder hacer esto: cursor.readString
        return ShopEntity(
                1,
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_SHOP_ID)),
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

    override fun query(): List<ShopEntity> {
        // TODO queryList
        return ArrayList()
    }

    override fun queryCursor(id: Long): Cursor {
        val cursor = dbReadOnlyConnection.query(
                DBConstants.TABLE_SHOP,
                DBConstants.ALL_COLUMNS,
                DBConstants.KEY_SHOP_ID + " = ?",
                arrayOf(id.toString()),
                "",
                "",
                DBConstants.KEY_SHOP_ID)

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

        content.put(DBConstants.KEY_SHOP_ID, shopEntity.id)
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
        // TODO update
        return 1
    }

    override fun delete(element: ShopEntity): Long {
        return delete(element.id)
    }

    override fun delete(id: Long): Long {

        // Con '?' se evita la inyección de código, pues el ? se resuelve mirando el array
        return dbReadWriteConnection.delete(
                DBConstants.TABLE_SHOP,
                DBConstants.KEY_SHOP_ID + " = ?",
                arrayOf(id.toString())
                ).toLong()
    }

    override fun deleteAll(): Boolean {
        // TODO deleteAll
        return true
    }


}