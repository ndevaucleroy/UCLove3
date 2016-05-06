package lsinf1225.uclove;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by Guillaume on 29/04/16.
 */
public class AlbumManager {
    //Key Album Name
    public static final String TABLE_PERSON = "person";
    public static final String TABLE_ALBUM = "album";
    public static final String ALBUM_LOGIN = "login";
    public static final String ALBUM_PICTURE = "picture";
    //Album
    public static final String ALBUM_TABLE_CREATE =
            "CREATE TABLE " + TABLE_ALBUM + " (" +
                    ALBUM_LOGIN + " TEXT not null references " + TABLE_PERSON + ", " +
                    ALBUM_PICTURE + " TEXT not null, PRIMARY KEY(" +
                    ALBUM_LOGIN + ", " + ALBUM_PICTURE + "));";

    private DatabaseHandler maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;


    public AlbumManager(Context context) {
        maBaseSQLite = DatabaseHandler.getInstance(context);
    }

    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addAlbum(Album album) {
        // Ajout d'un enregistrement dans la table
        ContentValues values = new ContentValues();
        values.put(ALBUM_LOGIN, album.getLogin());
        values.put(ALBUM_PICTURE, album.getPicture());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_ALBUM, null, values);
    }

    public int modAlbum(Album album) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(ALBUM_LOGIN, album.getLogin());
        values.put(ALBUM_PICTURE, album.getPicture());

        String where = ALBUM_LOGIN+" = ? and "+ALBUM_PICTURE+" = ?" ;
        String[] whereArgs = {""+album.getLogin(), ""+album.getPicture()};

        return db.update(TABLE_ALBUM, values, where, whereArgs);
    }

    public int supAlbum(Album album) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = ALBUM_LOGIN+" = ? and "+ALBUM_PICTURE+" = ?" ;
        String[] whereArgs = {""+album.getLogin(), ""+album.getPicture()};

        return db.delete(TABLE_ALBUM, where, whereArgs);
    }

    public Album getAlbumStr(String login, String picture) {
        // Retourne le score dont le donneur est loginGive et le receveur est loginGet

        Album a=new Album("","");

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_ALBUM+" WHERE "+ALBUM_LOGIN+"='"+login+"' and "+ALBUM_PICTURE+"='"+picture+"'", null);
        if (c.moveToFirst()) {
            a.setLogin(c.getString(c.getColumnIndex(ALBUM_LOGIN)));
            a.setPicture(c.getString(c.getColumnIndex(ALBUM_PICTURE)));
            c.close();
        }

        return a;
    }

    public Cursor getLoginsAlbum(String login) {
        // Retourne un curseur sur tous les albums de login
        return db.rawQuery("SELECT * FROM "+TABLE_ALBUM+" WHERE "+ALBUM_LOGIN+"='"+login+"'", null);
    }

    public Cursor getAlbumsStr() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_ALBUM, null);
    }
}