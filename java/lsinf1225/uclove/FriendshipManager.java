package lsinf1225.uclove;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Created by cariamole on 29.04.16.
 */
public class FriendshipManager {
    public static final String TABLE_FRIENDSHIP = "friendship";
    public static final String TABLE_PERSON = "person";

    public static final String FRIENDSHIP_LOGIN1 = "login1";
    public static final String FRIENDSHIP_LOGIN2 = "login2";
    public static final String FRIENDSHIP_CHAT = "chat";

    public static final String FRIENDSHIP_TABLE_CREATE =
            "CREATE TABLE " + TABLE_FRIENDSHIP + " (" +
                    FRIENDSHIP_LOGIN1 + " TEXT not null references " + TABLE_PERSON + ", " +
                    FRIENDSHIP_LOGIN2 + " TEXT not null references " + TABLE_PERSON + ", " +
                    FRIENDSHIP_CHAT + " TEXT, PRIMARY KEY (" +
                    FRIENDSHIP_LOGIN1 + ", " + FRIENDSHIP_LOGIN2 + "));";

    private DatabaseHandler maBaseSQLite;
    private SQLiteDatabase db;

    public FriendshipManager(Context context) {
        maBaseSQLite = DatabaseHandler.getInstance(context);
    }

    public void open() {
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public long addFriendship(Friendship friendship){
        ContentValues values = new ContentValues();

        values.put(FRIENDSHIP_LOGIN1, friendship.getLogin1());
        values.put(FRIENDSHIP_LOGIN2, friendship.getLogin2());
        values.put(FRIENDSHIP_CHAT, friendship.getChat());
        return db.insert(TABLE_FRIENDSHIP, null, values);
    }

    public int modFriendship(Friendship friendship){
        ContentValues values=new ContentValues();
        values.put(FRIENDSHIP_LOGIN1, friendship.getLogin1());
        values.put(FRIENDSHIP_LOGIN2, friendship.getLogin2());
        values.put(FRIENDSHIP_CHAT, friendship.getChat());

        String where = FRIENDSHIP_LOGIN1+" = ? and "+FRIENDSHIP_LOGIN2+" = ? ";
        String[] whereArgs={""+friendship.getLogin1(),""+friendship.getLogin2()};

        return db.update(TABLE_FRIENDSHIP, values, where, whereArgs);
    }

    public int supFriendship(Friendship friendship){
        String where = FRIENDSHIP_LOGIN1+" = ? and "+FRIENDSHIP_LOGIN2+" = ? ";
        String[] whereArgs={""+friendship.getLogin1(),""+friendship.getLogin2()};

        return db.delete(TABLE_FRIENDSHIP, where, whereArgs);
    }

    public String getChatHistory(User user, User otherUser) {
        Cursor c = db.rawQuery("SELECT chat FROM "+TABLE_FRIENDSHIP+" WHERE (("
                +FRIENDSHIP_LOGIN1+" = '"+user.getLoginStr()+"' AND "+ FRIENDSHIP_LOGIN2+" = '"+otherUser.getLoginStr()+"') OR ("
                +FRIENDSHIP_LOGIN2+" = '"+user.getLoginStr()+"' AND "+ FRIENDSHIP_LOGIN1+" = '"+otherUser.getLoginStr()+"')) AND "
                +FRIENDSHIP_CHAT+" != 'null'",null);
        if(c.moveToFirst()) {
            return c.getString(c.getColumnIndex(FRIENDSHIP_CHAT));
        }
        return null;
    }

    public Friendship getFriendshipStr(String login1, String login2){
        Friendship f=new Friendship();

        Cursor c= db.rawQuery("SELECT * FROM "+TABLE_FRIENDSHIP+" WHERE "+FRIENDSHIP_LOGIN1+" = '"+login1+"' AND "+FRIENDSHIP_LOGIN2+" = '"+login2+"'",null);
        if(c.moveToFirst()){
            f.setLogin1(c.getString(c.getColumnIndex(FRIENDSHIP_LOGIN1)));
            f.setLogin2(c.getString(c.getColumnIndex(FRIENDSHIP_LOGIN2)));
            f.setChat(c.getString(c.getColumnIndex(FRIENDSHIP_CHAT)));
            c.close();
        }
        return f;
    }
    public ArrayList<String> getFriendsStr(String login1){
        ArrayList<String> list= new ArrayList();

        Cursor c= db.rawQuery("SELECT * FROM "+TABLE_FRIENDSHIP+" WHERE "+FRIENDSHIP_LOGIN1+" = '"+login1+"' and "+FRIENDSHIP_CHAT+" is not null", null);
        if(c.moveToFirst()){
            do {
                list.add(c.getString(c.getColumnIndex(FRIENDSHIP_LOGIN2)));
            } while(c.moveToNext());
        }
        c.close();
        Cursor d = db.rawQuery("SELECT * FROM "+TABLE_FRIENDSHIP+" WHERE "+FRIENDSHIP_LOGIN2+" = '"+login1+"' and "+FRIENDSHIP_CHAT+" is not null", null);
        if(d.moveToFirst()){
            do {
                list.add(d.getString(d.getColumnIndex(FRIENDSHIP_LOGIN1)));
            } while(d.moveToNext());
        }
        d.close();
        return list;
    }
    public ArrayList<String> getRecFriendsRequestStr(String login2){
        ArrayList<String> list= new ArrayList();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_FRIENDSHIP+" WHERE "+FRIENDSHIP_LOGIN2+" = '"+login2+"' and "+FRIENDSHIP_CHAT+" is null ", null);
        if(c.moveToFirst()) {
            do {
                list.add(c.getString(c.getColumnIndex(FRIENDSHIP_LOGIN1)));
            } while(c.moveToNext());
        }
        c.close();
        return list;
    }
    public ArrayList<String> getSentFriendsRequestStr(String login1){
        ArrayList<String> list= new ArrayList();

        Cursor c= db.rawQuery("SELECT * FROM "+TABLE_FRIENDSHIP+" WHERE "+FRIENDSHIP_LOGIN1+" = '"+login1+"' and "+FRIENDSHIP_CHAT+" is null ", null);
        if(c.moveToFirst()) {
            do {
                list.add(c.getString(c.getColumnIndex(FRIENDSHIP_LOGIN1)));
            } while(c.moveToNext());
        }
        c.close();
        return list;
    }
}