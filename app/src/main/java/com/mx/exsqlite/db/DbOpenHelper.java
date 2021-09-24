package com.mx.exsqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbOpenHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 25;
    private static final String DB_NAME = "SQLite.db";
    public static SQLiteDatabase mdb;

    public DbOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbTable.Member.QUERY_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbTable.Member.QUERY_DROP);
    }

    /**
     * SQL
     */
    // Member
    public Cursor selectAllMember() {
        mdb = this.getReadableDatabase();
        String sql = "select * from member";
        Cursor member_list = mdb.rawQuery(sql, null);

        return member_list;
    }

    public boolean insertMember(String member_id, String member_nickname, String member_birth) {
        mdb = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put(DbTable.Member.COLUMN_ID, member_id);
        values.put(DbTable.Member.COLUMN_NICKNAME, member_nickname);
        values.put(DbTable.Member.COLUMN_BIRTH, member_birth);

        long result = mdb.insert(DbTable.Member.TABLENAME, null, values);

        if(result == -1)
            return false; // error
        else
            return true; // success
    }

    public boolean updateMember(String member_id, String member_nickname, String member_birth) {
        mdb = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put(DbTable.Member.COLUMN_ID, member_id);
        values.put(DbTable.Member.COLUMN_NICKNAME, member_nickname);
        values.put(DbTable.Member.COLUMN_BIRTH, member_birth);

        int result = mdb.update(DbTable.Member.TABLENAME, values, DbTable.Member.COLUMN_ID + "=?", new String[] {member_id});

        if( result == 0)
            return false; // error
        else
            return true; // success
    }

    public boolean deleteMember(String member_id) {
        mdb = this.getWritableDatabase();
        int result = mdb.delete(DbTable.Member.TABLENAME, DbTable.Member.COLUMN_ID + "=?", new String[] {member_id} );

        if( result == 0)
            return false; // error
        else
            return true; // success
    }

}
