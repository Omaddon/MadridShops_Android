package ammyt.com.repository.db.dao

import android.database.Cursor

// DAO = Data Access Object
interface DAOReadOperations<T> {
    fun query(id: Int): T
    fun query(): List<T>
    fun queryCursor(): Cursor
}

interface DAOWriteOperations<T> {
    fun insert()
    fun update()
    fun delete()
    fun deleteAll()
}

interface DAOPersistable<T>: DAOReadOperations<T>, DAOWriteOperations<T>