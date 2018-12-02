package com.ivonkhalif.ragnarok.footballlive.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class SQLiteMatchHelper (ctx : Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1){
    companion object {
        private var instance: SQLiteMatchHelper? = null

        fun getInstance(ctx: Context) : SQLiteMatchHelper{
            if (instance == null) {
                instance = SQLiteMatchHelper(ctx.applicationContext)
            }
            return instance as SQLiteMatchHelper
        }
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(FavoriteMatch.TABLE_FAVORITE, true,
            FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatch.ID_MATCH to TEXT + UNIQUE,
            FavoriteMatch.ID_HOME_TEAM to TEXT,
            FavoriteMatch.ID_AWAY_TEAM to TEXT,
            FavoriteMatch.NAME_HOME_TEAM to TEXT,
            FavoriteMatch.NAME_AWAY_TEAM to TEXT,
            FavoriteMatch.DATE_MATCH to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteMatch.TABLE_FAVORITE, true)
    }
}

val Context.database: SQLiteMatchHelper
get() = SQLiteMatchHelper.getInstance(applicationContext)