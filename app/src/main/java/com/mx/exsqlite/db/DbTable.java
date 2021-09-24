package com.mx.exsqlite.db;

import android.provider.BaseColumns;

public final class DbTable {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DbTable() {}

    /*회원*/
    public static final class Member implements BaseColumns {
        public static final String TABLENAME= "member";
        public static final String COLUMN_ID = "member_id";
        public static final String COLUMN_NICKNAME = "member_nickname";
        public static final String COLUMN_BIRTH = "member_birth";
        public static final String QUERY_CREATE = "create table IF NOT EXISTS "+ TABLENAME + "("
                + COLUMN_ID + " text primary key,"
                + COLUMN_NICKNAME + " text not null,"
                + COLUMN_BIRTH + " text);";
        public static final String QUERY_DROP = "drop table if exists "+TABLENAME;
    }
}
