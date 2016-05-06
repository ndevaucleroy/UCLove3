package lsinf1225.uclove;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Guillaume on 29/04/16.
 */
public class ScoreManager {
    //Key Score Name
    public static final String TABLE_PERSON = "person";
    public static final String TABLE_SCORE = "score";
    public static final String SCORE_LOGINGET = "login";
    public static final String SCORE_LOGINGIVE = "picture";
    public static final String SCORE_QUOTATION = "quotation";
    //Score
    public static final String SCORE_TABLE_CREATE =
            "CREATE TABLE " + TABLE_SCORE + " (" +
                    SCORE_LOGINGIVE + " TEXT not null references " + TABLE_PERSON + ", " +
                    SCORE_LOGINGET + " TEXT not null references " + TABLE_PERSON + ", " +
                    SCORE_QUOTATION + " INTEGER not null, PRIMARY KEY (" +
                    SCORE_LOGINGIVE + ", " + SCORE_LOGINGET + "));";

    private DatabaseHandler maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;


    public ScoreManager(Context context) {
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

    public long addScore(Score score) {
        // Ajout d'un enregistrement dans la table
        ContentValues values = new ContentValues();
        values.put(SCORE_LOGINGIVE, score.getLoginGive());
        values.put(SCORE_LOGINGET, score.getLoginGet());
        values.put(SCORE_QUOTATION, score.getQuotation());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_SCORE,null,values);
    }

    public int modScore(Score score) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(SCORE_LOGINGIVE, score.getLoginGive());
        values.put(SCORE_LOGINGET, score.getLoginGet());
        values.put(SCORE_QUOTATION, score.getQuotation());

        String where = SCORE_LOGINGIVE+" = ? and "+SCORE_LOGINGET+" = ?" ;
        String[] whereArgs = {""+score.getLoginGive(), ""+score.getLoginGet()};

        return db.update(TABLE_SCORE, values, where, whereArgs);
    }

    public int supScore(Score score) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = SCORE_LOGINGIVE+" = ? and "+SCORE_LOGINGET+" = ?" ;
        String[] whereArgs = {""+score.getLoginGive(), ""+score.getLoginGet()};

        return db.delete(TABLE_SCORE, where, whereArgs);
    }

    public Score getScoreStr(String loginGive, String loginGet) {
        // Retourne le score dont le donneur est loginGive et le receveur est loginGet

        Score a=new Score();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_SCORE+" WHERE "+SCORE_LOGINGIVE+"='"+loginGive+"' and "+SCORE_LOGINGET+"='"+loginGet+"'", null);
        if (c.moveToFirst()) {
            a.setLoginGive(c.getString(c.getColumnIndex(SCORE_LOGINGIVE)));
            a.setLoginGet(c.getString(c.getColumnIndex(SCORE_LOGINGET)));
            a.setQuotation(c.getInt(c.getColumnIndex(SCORE_QUOTATION)));
            c.close();
        }

        return a;
    }

    public Cursor getScoresStr() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_SCORE, null);
    }

    public double getAverage(User usrGet) {
        double sum = 0;
        int i = 0;
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_SCORE+" WHERE "+SCORE_LOGINGET+ " = '"+usrGet.getLoginStr()+"'",null);
        if(c.moveToFirst()) {
            do {
                sum += c.getInt(c.getColumnIndex(SCORE_QUOTATION));
                i++;
            } while(c.moveToNext());
        }
        return (double)(sum/i);
    }
}