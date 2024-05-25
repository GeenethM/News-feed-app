package nibm.hd231.madassignment

import android.content.ClipDescription
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (val context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){
    companion object {
        const val DB_NAME: String = "news"
        const val DB_VERSION: Int = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var query: String = "CREATE TABLE news(category TEXT, headline TEXT, description TEXT)"
        db?.execSQL(query)
        query = "INSERT INTO news values('Sports', 'PMCO 2024 finals', 'The winners of this years PUBG mobile championship is NRC')," +
                "('Sports', 'FIFA worldcup 2022', 'Messi to continue his football carrier')," +
                "('Sports', 'Esports', 'Can esports relly be considered as sports'), " +
                "('Technology', 'Future of humanity', 'AI the job killer')," +
                "('Technology', 'Tesla cybertruck', 'Is Teslas echo friendly bullet proof vehicle worth it')," +
                "('Technology', 'Apples cyberpuncky invention', 'Apples vision pro stunts people in New York subway')," +
                "('Entertainment', 'Grand history of movie making', 'Downfall of Hollywood')," +
                "('Entertainment', 'House of the Dragon', 'Season 2 of the well anticipated series')," +
                "('Entertainment', 'ULTRA miami', 'Join and have fun with worlds biggiest music festivals')"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion == 1) {
            //Changes to be performed to upgrade the db version to new version
            //Change will include for DB version 2, DB version 3
        }else if (oldVersion == 2) {
            //Changes to be performed to upgrade the db version from version 2
            //Change will include for DB version 3
        }
        var query = "DROP TABLE news"
        db?.execSQL(query)
        onCreate(db)
    }

    public fun insertNews(category: String, headline: String, description: String): Boolean {
        //Make the db as writable
        val db: SQLiteDatabase= this.writableDatabase
        val content: ContentValues = ContentValues()
        content.put("category", category)
        content.put("headline", headline)
        content.put("description", description)
        db.insert("news", null, content)
        return true
    }

    public fun deleteNews(headline: String): Boolean {
        //Make the db as writable
        val db: SQLiteDatabase= this.writableDatabase
        db.delete("news", "headline = ?", arrayOf(headline))
        return true
    }
    public fun getCategory (): Cursor {
        //Make the db as readable
        val db: SQLiteDatabase= this.readableDatabase
        return db.rawQuery("SELECT DISTINCT category FROM news", null)
    }

    public fun getTitles (cat: String): Cursor {
        //Make the db as readable
        val db: SQLiteDatabase= this.readableDatabase
        return db.rawQuery("SELECT headline FROM news WHERE category=?", arrayOf(cat))
    }

    public fun getDesc (head: String): Cursor {
        //Make the db as readable
        val db: SQLiteDatabase= this.readableDatabase
        return db.rawQuery("SELECT description FROM news WHERE headline=?", arrayOf(head))
    }

//    fun getDesc(head: String): List<Desc> {
//        val newsList = mutableListOf<Desc>()
//        val db = readableDatabase
//        val query = "SELECT description FROM news WHERE headline=?"
//        val cursor: Cursor = db.rawQuery(query, arrayOf(head))
//
//        while (cursor.moveToNext()) {
//            val description = cursor.getString(cursor.getColumnIndex("description"))
//
//            val newsItem = Desc(description)
//            newsList.add(newsItem)
//        }
//
//        cursor.close()
//        return newsList
//    }


}