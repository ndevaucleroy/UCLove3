package lsinf1225.uclove;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 *
 * Created by Guillaume on 29/04/16.
 */
public class RendezVousManager {


    //Key RendezVous Name
    public static final String TABLE_PERSON = "person";
    public static final String TABLE_RENDEZVOUS = "rendezvous";
    public static final String TABLE_AVAILABILITY = "availability";
    public static final String RENDEZVOUS_LOGIN1 = "login1";
    public static final String RENDEZVOUS_LOGIN2 = "login2";
    public static final String RENDEZVOUS_MEETING = "meeting";
    //Rendezvous
    public static final String RENDEZVOUS_TABLE_CREATE =
            "CREATE TABLE " + TABLE_RENDEZVOUS + " (" +
                    RENDEZVOUS_LOGIN1 + " TEXT not null references " + TABLE_PERSON + " , " +
                    RENDEZVOUS_LOGIN2 + " TEXT not null references " + TABLE_PERSON + " , " +
                    RENDEZVOUS_MEETING + " TEXT not null, PRIMARY KEY (" +
                    RENDEZVOUS_LOGIN1 + " , " + RENDEZVOUS_LOGIN2 + "), FOREIGN KEY(" +
                    RENDEZVOUS_LOGIN1 + " , " + RENDEZVOUS_MEETING + ") references " + TABLE_AVAILABILITY + " , FOREIGN KEY ( " +
                    RENDEZVOUS_LOGIN2 + " , " + RENDEZVOUS_MEETING + " ) references " + TABLE_AVAILABILITY + " ) ; ";
    private DatabaseHandler maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    public RendezVousManager(Context context) {
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

    public long addRendezVous(RendezVous rdv) {
        // Ajout d'un enregistrement dans la table
        ContentValues values = new ContentValues();
        values.put(RENDEZVOUS_LOGIN1, rdv.getLogin1());
        values.put(RENDEZVOUS_LOGIN2, rdv.getLogin2());
        values.put(RENDEZVOUS_MEETING, rdv.getMeeting());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_RENDEZVOUS, null, values);
    }

    public int modRendezVous(RendezVous rdv) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(RENDEZVOUS_LOGIN1, rdv.getLogin1());
        values.put(RENDEZVOUS_LOGIN2, rdv.getLogin2());
        values.put(RENDEZVOUS_MEETING, rdv.getMeeting());

        String where = RENDEZVOUS_LOGIN1+" = ? and "+RENDEZVOUS_LOGIN2+" = ?" ;
        String[] whereArgs = {""+rdv.getLogin1(), ""+rdv.getLogin2()};

        return db.update(TABLE_RENDEZVOUS, values, where, whereArgs);
    }

    public int supRendezVous(RendezVous rdv) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = RENDEZVOUS_LOGIN1+" = ? and "+RENDEZVOUS_LOGIN2+" = ?" ;
        String[] whereArgs = {""+rdv.getLogin1(), ""+rdv.getLogin2()};

        return db.delete(TABLE_RENDEZVOUS, where, whereArgs);
    }

    public RendezVous getRendezVousStr(String login1, String login2) {
        // Retourne les rendez vous entre login1 et login2

        RendezVous rdv=new RendezVous();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_RENDEZVOUS+" WHERE "+RENDEZVOUS_LOGIN1+"='"+login1+"' and "+RENDEZVOUS_LOGIN2+"='"+login2+"'", null);
        if (c.moveToFirst()) {
            rdv.setLogin1(c.getString(c.getColumnIndex(RENDEZVOUS_LOGIN1)));
            rdv.setLogin2(c.getString(c.getColumnIndex(RENDEZVOUS_LOGIN2)));
            rdv.setMeeting(c.getString(c.getColumnIndex(RENDEZVOUS_MEETING)));
            c.close();
        }

        return rdv;
    }

    public Cursor getAllRendezVousStr() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_RENDEZVOUS, null);
    }
}