package com.ammyt.madridshops.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.ammyt.madridshops.repository.db.DBConstants
import com.ammyt.madridshops.repository.db.DBHelper
import com.ammyt.madridshops.repository.model.ActivityEntity

internal class ActivityDAO(val dbHelper: DBHelper): DAOPersistable<ActivityEntity> {

    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase


    override fun query(id: Long): ActivityEntity {
        val cursor = queryCursor(id)

        cursor.moveToFirst()

        return entityFromCursor(cursor)!!
    }

    override fun query(): List<ActivityEntity> {
        val queryResult = ArrayList<ActivityEntity>()

        val cursor = dbReadOnlyConnection.query(
                DBConstants.TABLE_ACTIVITY,
                DBConstants.ALL_COLUMNS_ACTIVITY,
                null,
                null,
                "",
                "",
                DBConstants.KEY_ACTIVITY_NAME + " ASC")

        while (cursor.moveToNext()) {
            val activityEntity = entityFromCursor(cursor)
            queryResult.add(activityEntity!!)
        }

        return queryResult
    }

    private fun entityFromCursor(cursor: Cursor): ActivityEntity? {
        if (cursor.isAfterLast || cursor.isBeforeFirst) return null

        return ActivityEntity(
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_ID_JSON)),
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_DATABASE_ID)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_DESCRIPTION_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_DESCRIPTION_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_LATITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_OPENING_HOURS_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_OPENING_HOURS_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_ADDRESS)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_TELEPHONE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_URL))
        )
    }

    override fun queryCursor(id: Long): Cursor {
        val cursor = dbReadOnlyConnection.query(
                DBConstants.TABLE_ACTIVITY,
                DBConstants.ALL_COLUMNS_ACTIVITY,
                DBConstants.KEY_ACTIVITY_DATABASE_ID + " = ?",
                arrayOf(id.toString()),
                "",
                "",
                DBConstants.KEY_ACTIVITY_NAME + " ASC")

        return cursor
    }

    override fun insert(element: ActivityEntity): Long {
        var id: Long = -1

        id = dbReadWriteConnection.insert(DBConstants.TABLE_ACTIVITY, null, contentValues(element))
        close()

        return id
    }

    private fun contentValues(activityEntity: ActivityEntity): ContentValues {
        val content = ContentValues()

        // El id de la database lo genera solo si no se lo indicamos
        content.put(DBConstants.KEY_ACTIVITY_ID_JSON, activityEntity.id)
        content.put(DBConstants.KEY_ACTIVITY_NAME, activityEntity.name)

        // Optionals
        content.put(DBConstants.KEY_ACTIVITY_DESCRIPTION_EN, activityEntity.description_en)
        content.put(DBConstants.KEY_ACTIVITY_DESCRIPTION_ES, activityEntity.description_es)
        content.put(DBConstants.KEY_ACTIVITY_LATITUDE, activityEntity.latitude)
        content.put(DBConstants.KEY_ACTIVITY_LONGITUDE, activityEntity.longitude)
        content.put(DBConstants.KEY_ACTIVITY_IMAGE_URL, activityEntity.img)
        content.put(DBConstants.KEY_ACTIVITY_LOGO_IMAGE_URL, activityEntity.logo)
        content.put(DBConstants.KEY_ACTIVITY_ADDRESS, activityEntity.address)
        content.put(DBConstants.KEY_ACTIVITY_OPENING_HOURS_EN, activityEntity.openingHours_en)
        content.put(DBConstants.KEY_ACTIVITY_OPENING_HOURS_ES, activityEntity.openingHours_es)
        content.put(DBConstants.KEY_ACTIVITY_TELEPHONE, activityEntity.telephone)
        content.put(DBConstants.KEY_ACTIVITY_URL, activityEntity.url)

        return content
    }

    override fun update(id: Long, element: ActivityEntity): Long {
        return dbReadWriteConnection.update(
                DBConstants.TABLE_ACTIVITY,
                contentValues(element),
                DBConstants.KEY_ACTIVITY_DATABASE_ID + " = ? ",
                arrayOf(id.toString())
        ).toLong()
    }

    override fun delete(element: ActivityEntity): Long {
        if (element.databaseId < 1) return 0

        return delete(element.databaseId)
    }

    override fun delete(id: Long): Long {
        val elementsDeleted = dbReadWriteConnection.delete(
                DBConstants.TABLE_ACTIVITY,
                DBConstants.KEY_ACTIVITY_DATABASE_ID + " = ?",
                arrayOf(id.toString())
        ).toLong()

        close()

        return elementsDeleted
    }

    override fun deleteAll(): Boolean {

        val result = dbReadWriteConnection.delete(
                DBConstants.TABLE_ACTIVITY,
                null,
                null
        ).toLong() >= 0 // >= 0 para comprobar el caso en el que la db esté vacía

        close()

        return result
    }

    private fun close() {
        dbReadOnlyConnection.close()
        dbReadWriteConnection.close()
    }

}