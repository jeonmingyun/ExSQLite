package com.mx.exsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mx.exsqlite.db.DbOpenHelper;
import com.mx.exsqlite.db.DbTable;
import com.mx.exsqlite.vo.MemberVo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DbOpenHelper mDbHelper;
    private ArrayList<MemberVo> memberList;
    private EditText id, nickname, birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new DbOpenHelper(this);
        id = findViewById(R.id.id);
        nickname = findViewById(R.id.nickname);
        birth = findViewById(R.id.birth);

        findViewById(R.id.sel_btn).setOnClickListener(this);
        findViewById(R.id.del_btn).setOnClickListener(this);
        findViewById(R.id.ins_btn).setOnClickListener(this);
        findViewById(R.id.upd_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ins_btn:
                insertMember(id.getText().toString(), nickname.getText().toString(), birth.getText().toString());
                break;
            case R.id.del_btn:
                deleteMember(id.getText().toString());
                break;
            case R.id.upd_btn:
                updateMember(id.getText().toString(), nickname.getText().toString(), birth.getText().toString());
                break;
            case R.id.sel_btn:
                selectAllMember();
                break;
        }

    }

    /*select*/
    private ArrayList<MemberVo> selectAllMember() {
        Cursor cursor = mDbHelper.selectAllMember();
        MemberVo vo;

        memberList = new ArrayList<>();
        while (cursor.moveToNext()) {
            vo = new MemberVo();
            vo.setMember_id(cursor.getString(cursor.getColumnIndex(DbTable.Member.COLUMN_ID)));
            vo.setMember_nickname(cursor.getString(cursor.getColumnIndex(DbTable.Member.COLUMN_NICKNAME)));
            vo.setMember_birth(cursor.getString(cursor.getColumnIndex(DbTable.Member.COLUMN_BIRTH)));
            memberList.add(vo);
        }

        String toastStr = memberList.get(0).getMember_id() + " / " + memberList.get(0).getMember_nickname() + " / " + memberList.get(0).getMember_birth();
        Toast.makeText(this, toastStr, Toast.LENGTH_SHORT).show();
        return memberList;
    }

    /*insert*/
    private void insertMember(String member_id, String member_nickname, String member_birth) {
        boolean result = mDbHelper.insertMember(member_id, member_nickname, member_birth);
        String toastStr;

        if (result) {
            toastStr = "success : " + member_id + " / " + member_nickname + " / " + member_birth;
        } else {
            toastStr = "fail";
        }
        Toast.makeText(this, toastStr, Toast.LENGTH_SHORT).show();
    }

    /*delete*/
    private void deleteMember(String member_id) {
        boolean result = mDbHelper.deleteMember(member_id);
        String toastStr;

        if (result) {
            toastStr = "success : " + member_id;
        } else {
            toastStr = "fail : can't find member_id";
        }
        Toast.makeText(this, toastStr, Toast.LENGTH_SHORT).show();
    }

    /*update*/
    private void updateMember(String member_id, String member_nickname, String member_birth) {
        boolean result = mDbHelper.updateMember(member_id, member_nickname, member_birth);
        String toastStr;

        if (result) {
            toastStr = "success : " + member_id + " / " + member_nickname + " / " + member_birth;
        } else {
            toastStr = "fail : can't find member_id";
        }
        Toast.makeText(this, toastStr, Toast.LENGTH_SHORT).show();
    }

}
