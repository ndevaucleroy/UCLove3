package lsinf1225.uclove;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.SharedPreferences;
import android.database.Cursor;

public class DatabaseHandler extends SQLiteOpenHelper {

    //SQLite Name, Version, instance
    private static final String DATABASE_NAME = "db.sqlite";
    private static final int DATABASE_VERSION = 8;
    private static DatabaseHandler sInstance;

    //Drop Table
    public static final String PERSON_TABLE_DROP = "DROP TABLE IF EXISTS " + UserManager.TABLE_PERSON + ";";
    public static final String SCORE_TABLE_DROP = "DROP TABLE IF EXISTS " + ScoreManager.TABLE_SCORE + ";";
    public static final String ALBUM_TABLE_DROP = "DROP TABLE IF EXISTS " + AlbumManager.TABLE_ALBUM + ";";
    public static final String FRIENDSHIP_TABLE_DROP = "DROP TABLE IF EXISTS " + FriendshipManager.TABLE_FRIENDSHIP + ";";
    public static final String RENDEZVOUS_TABLE_DROP = "DROP TABLE IF EXISTS " + RendezVousManager.TABLE_RENDEZVOUS + ";";
    public static final String AVAILABILITY_TABLE_DROP = "DROP TABLE IF EXISTS " + AvailabilityManager.TABLE_AVAILABILITY + ";";

    public static synchronized DatabaseHandler getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHandler(context); }
        return sInstance;
    }

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserManager.PERSON_TABLE_CREATE);
        db.execSQL(ScoreManager.SCORE_TABLE_CREATE);
        db.execSQL(AlbumManager.ALBUM_TABLE_CREATE);
        db.execSQL(FriendshipManager.FRIENDSHIP_TABLE_CREATE);
        db.execSQL(RendezVousManager.RENDEZVOUS_TABLE_CREATE);
        db.execSQL(AvailabilityManager.AVAILABILITY_TABLE_CREATE);

        db.execSQL("INSERT INTO person VALUES(\"Maasym\",\"Charleroi\",\"Gabeline\",\"Hadagonde\",\"1915-06-20\",\"Français\",\"other\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",48656900)");
        db.execSQL("INSERT INTO person VALUES(\"Mabsuthat\",\"Charleroi\",\"Gabrienne\",\"Haddee\",\"1951-10-22\",\"Français\",\"other\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"B\",30011947)");
        db.execSQL("INSERT INTO person VALUES(\"Maia\",\"Liege\",\"Gacienne\",\"Haddie\",\"1917-09-06\",\"English\",\"blond\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"B\",186285744)");
        db.execSQL("INSERT INTO person VALUES(\"Majya\",\"Liege\",\"Gadaline\",\"Hadeltrude\",\"1936-03-15\",\"Français\",\"black\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",67898410)");
        db.execSQL("INSERT INTO person VALUES(\"Manubrij\",\"Anvers\",\"Gadelie\",\"Hailemine\",\"1961-08-24\",\"Français\",\"other\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",186814916)");
        db.execSQL("INSERT INTO person VALUES(\"Marchab\",\"Bruxelles\",\"Gadelienne\",\"Hailemise\",\"1967-04-05\",\"Français\",\"blond\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"M\",51505075)");
        db.execSQL("INSERT INTO person VALUES(\"Marfak\",\"Liege\",\"Gaëlle\",\"Haissa\",\"1994-05-25\",\"English\",\"blond\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"F\",120739713)");
        db.execSQL("INSERT INTO person VALUES(\"Marfic\",\"Bruxelles\",\"Gaëtina\",\"Halaide\",\"1953-01-02\",\"Français\",\"other\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",171867)");
        db.execSQL("INSERT INTO person VALUES(\"Marfik\",\"Anvers\",\"Galamine\",\"Halbondie\",\"1952-09-06\",\"English\",\"other\",\"brown\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",253085730)");
        db.execSQL("INSERT INTO person VALUES(\"Marj\",\"Anvers\",\"Galarmine\",\"Haldina\",\"1920-07-20\",\"English\",\"blond\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",67505597)");
        db.execSQL("INSERT INTO person VALUES(\"Markab\",\"Mons\",\"Galianne\",\"Haliande\",\"1998-10-13\",\"English\",\"other\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"M\",252505503)");
        db.execSQL("INSERT INTO person VALUES(\"Markeb\",\"Mons\",\"Galice\",\"Haliesca\",\"1915-04-25\",\"English\",\"black\",\"brown\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"B\",176614666)");
        db.execSQL("INSERT INTO person VALUES(\"Marrha\",\"Mons\",\"Galienne\",\"Halima\",\"1909-08-04\",\"Français\",\"black\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"B\",220562044)");
        db.execSQL("INSERT INTO person VALUES(\"Marsik\",\"Liege\",\"Galina\",\"Halperia\",\"1946-09-25\",\"Français\",\"black\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",177506416)");
        db.execSQL("INSERT INTO person VALUES(\"Masym\",\"Gand\",\"Gallienne\",\"Halvina\",\"1965-01-19\",\"English\",\"blond\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"B\",142379881)");
        db.execSQL("INSERT INTO person VALUES(\"Matar\",\"Bruxelles\",\"Gamelienne\",\"Halvine\",\"1928-06-17\",\"English\",\"red\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"M\",35289871)");
        db.execSQL("INSERT INTO person VALUES(\"Meboula\",\"Louvain-La-Neuve\",\"Gameline\",\"Hameline\",\"1929-01-10\",\"Français\",\"other\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"M\",198627856)");
        db.execSQL("INSERT INTO person VALUES(\"Mebsuta\",\"Mons\",\"Gamelire\",\"Hamida\",\"1900-05-12\",\"English\",\"blond\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"B\",259268411)");
        db.execSQL("INSERT INTO person VALUES(\"Media\",\"Liege\",\"Gamelise\",\"Hamilda\",\"1945-01-19\",\"Français\",\"blond\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",104466436)");
        db.execSQL("INSERT INTO person VALUES(\"Megrets\",\"Mons\",\"Ganaisse\",\"Hamildee\",\"1919-08-22\",\"Français\",\"blond\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"B\",162110593)");
        db.execSQL("INSERT INTO person VALUES(\"Megrez\",\"Louvain-La-Neuve\",\"Gardelia\",\"Handrianne\",\"1921-05-21\",\"Français\",\"black\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"B\",113765612)");
        db.execSQL("INSERT INTO person VALUES(\"Meissa\",\"Gand\",\"Gardeva\",\"Handrienne\",\"1974-01-05\",\"Français\",\"other\",\"brown\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"F\",84524509)");
        db.execSQL("INSERT INTO person VALUES(\"Mejssa\",\"Liege\",\"Gardina\",\"Hantippe\",\"1945-06-13\",\"English\",\"black\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"F\",17290574)");
        db.execSQL("INSERT INTO person VALUES(\"Mekbuda\",\"Liege\",\"Gargonie\",\"Haregonde\",\"1946-02-20\",\"English\",\"other\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"F\",10495012)");
        db.execSQL("INSERT INTO person VALUES(\"Melucta\",\"Liege\",\"Gargottine\",\"Harianne\",\"1972-04-24\",\"Français\",\"black\",\"brown\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"M\",113498336)");
        db.execSQL("INSERT INTO person VALUES(\"Men\",\"Namur\",\"Garnoise\",\"Harielle\",\"1980-01-17\",\"Français\",\"red\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"M\",45971094)");
        db.execSQL("INSERT INTO person VALUES(\"Menchi\",\"Namur\",\"Garsende\",\"Harienne\",\"1975-11-22\",\"English\",\"red\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",161883439)");
        db.execSQL("INSERT INTO person VALUES(\"Menka\",\"Gand\",\"Gasparde\",\"Hariette\",\"1900-02-25\",\"English\",\"black\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",162346776)");
        db.execSQL("INSERT INTO person VALUES(\"Menkab\",\"Liege\",\"Gasparina\",\"Harilda\",\"1969-03-10\",\"English\",\"black\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"F\",164159862)");
        db.execSQL("INSERT INTO person VALUES(\"Menkalina\",\"Liege\",\"Gasparine\",\"Harildee\",\"1982-01-11\",\"English\",\"black\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"M\",50089481)");
        db.execSQL("INSERT INTO person VALUES(\"Menkalinan\",\"Charleroi\",\"Gasperina\",\"Harlina\",\"1919-05-05\",\"Français\",\"brown\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",37787554)");
        db.execSQL("INSERT INTO person VALUES(\"Menkar\",\"Anvers\",\"Gassienne\",\"Harline\",\"1970-11-25\",\"English\",\"other\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"B\",15163046)");
        db.execSQL("INSERT INTO person VALUES(\"Menkent\",\"Bruxelles\",\"Gastelle\",\"Harlinia\",\"1998-04-24\",\"English\",\"black\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",10632749)");
        db.execSQL("INSERT INTO person VALUES(\"Menkhib\",\"Gand\",\"Gastonia\",\"Harlinie\",\"1938-08-01\",\"English\",\"red\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"B\",33383967)");
        db.execSQL("INSERT INTO person VALUES(\"Menkib\",\"Anvers\",\"Gastonne\",\"Harmaide\",\"1966-07-11\",\"Français\",\"red\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"F\",206995203)");
        db.execSQL("INSERT INTO person VALUES(\"Mentar\",\"Charleroi\",\"Gatienne\",\"Harmaisse\",\"1932-06-03\",\"Français\",\"brown\",\"brown\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",224973344)");
        db.execSQL("INSERT INTO person VALUES(\"Merach\",\"Charleroi\",\"Gauberte\",\"Harmance\",\"1968-04-09\",\"English\",\"black\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"M\",29749163)");
        db.execSQL("INSERT INTO person VALUES(\"Merak\",\"Charleroi\",\"Gauchère\",\"Harmedia\",\"1982-11-19\",\"English\",\"brown\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"M\",18432509)");
        db.execSQL("INSERT INTO person VALUES(\"Merakh\",\"Bruxelles\",\"Gauchette\",\"Harmelia\",\"1936-10-12\",\"English\",\"black\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"B\",121929192)");
        db.execSQL("INSERT INTO person VALUES(\"Meres\",\"Louvain-La-Neuve\",\"Gaudelia\",\"Harmelie\",\"1950-07-24\",\"Français\",\"other\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"B\",216967638)");
        db.execSQL("INSERT INTO person VALUES(\"Merets\",\"Bruxelles\",\"Gaudelie\",\"Harmelina\",\"1980-05-22\",\"Français\",\"red\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"B\",184809649)");
        db.execSQL("INSERT INTO person VALUES(\"Merez\",\"Charleroi\",\"Gaudelina\",\"Harmeline\",\"1915-11-09\",\"English\",\"other\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"M\",54750980)");
        db.execSQL("INSERT INTO person VALUES(\"Merga\",\"Namur\",\"Gaudeline\",\"Harmelinia\",\"1988-01-15\",\"Français\",\"brown\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",72191027)");
        db.execSQL("INSERT INTO person VALUES(\"Merkab\",\"Anvers\",\"Gaudelinia\",\"Harmelinie\",\"1982-07-02\",\"Français\",\"other\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"F\",623416)");
        db.execSQL("INSERT INTO person VALUES(\"Meropa\",\"Charleroi\",\"Gaudelise\",\"Harmelisa\",\"1926-11-07\",\"Français\",\"black\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"M\",186652072)");
        db.execSQL("INSERT INTO person VALUES(\"Merope\",\"Namur\",\"Gaudia\",\"Harmelise\",\"1958-09-19\",\"Français\",\"brown\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"B\",150565284)");
        db.execSQL("INSERT INTO person VALUES(\"Mesarthim\",\"Mons\",\"Gaudianna\",\"Harmelle\",\"1900-01-11\",\"Français\",\"blond\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"B\",51980759)");
        db.execSQL("INSERT INTO person VALUES(\"Mesartkhim\",\"Bruxelles\",\"Gaudianne\",\"Harmenie\",\"1941-02-13\",\"Français\",\"brown\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"B\",55663425)");
        db.execSQL("INSERT INTO person VALUES(\"Mesartim\",\"Charleroi\",\"Gaudienne\",\"Harmenise\",\"1947-10-02\",\"English\",\"brown\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",52291244)");
        db.execSQL("INSERT INTO person VALUES(\"Metallah\",\"Bruxelles\",\"Gaudiosa\",\"Harmicia\",\"1995-01-04\",\"Français\",\"brown\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",211551094)");
        db.execSQL("INSERT INTO person VALUES(\"Metallakh\",\"Gand\",\"Gaudrière\",\"Harmina\",\"1977-05-07\",\"English\",\"other\",\"brown\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",117330280)");
        db.execSQL("INSERT INTO person VALUES(\"Mezartim\",\"Charleroi\",\"Gauthilda\",\"Harmonia\",\"1918-07-12\",\"Français\",\"brown\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",14800117)");
        db.execSQL("INSERT INTO person VALUES(\"Miaplacidus\",\"Louvain-La-Neuve\",\"Gauthilde\",\"Harmonie\",\"1960-11-03\",\"Français\",\"blond\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"B\",76282260)");
        db.execSQL("INSERT INTO person VALUES(\"Miaplatsidus\",\"Bruxelles\",\"Gauvine\",\"Harmoza\",\"1968-11-12\",\"English\",\"brown\",\"brown\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",129905006)");
        db.execSQL("INSERT INTO person VALUES(\"Mifrid\",\"Louvain-La-Neuve\",\"Gaviria\",\"Harolda\",\"1915-07-07\",\"English\",\"brown\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",34508682)");
        db.execSQL("INSERT INTO person VALUES(\"Mimosa\",\"Namur\",\"Gazalie\",\"Haroldine\",\"1971-01-15\",\"English\",\"red\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",168904153)");
        db.execSQL("INSERT INTO person VALUES(\"Mimoza\",\"Charleroi\",\"Gazela\",\"Harriette\",\"1908-02-06\",\"English\",\"black\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"B\",83045609)");
        db.execSQL("INSERT INTO person VALUES(\"Minchir\",\"Gand\",\"Gazelia\",\"Hartemise\",\"1987-07-05\",\"English\",\"black\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",147500039)");
        db.execSQL("INSERT INTO person VALUES(\"Minelaua\",\"Namur\",\"Gazelie\",\"Hattie\",\"1941-04-13\",\"English\",\"other\",\"brown\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"B\",105078025)");
        db.execSQL("INSERT INTO person VALUES(\"Minkar\",\"Anvers\",\"Gazella\",\"Hauviette\",\"1938-04-11\",\"English\",\"black\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"F\",126691761)");
        db.execSQL("INSERT INTO person VALUES(\"Mintaka\",\"Namur\",\"Gazielea\",\"Hayda\",\"1980-02-09\",\"English\",\"brown\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"F\",110139463)");
        db.execSQL("INSERT INTO person VALUES(\"Mintika\",\"Mons\",\"Gaziella\",\"Hazilda\",\"1985-10-01\",\"Français\",\"black\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",168355396)");
        db.execSQL("INSERT INTO person VALUES(\"Mira\",\"Bruxelles\",\"Gazielle\",\"Hazildee\",\"1930-11-12\",\"English\",\"other\",\"brown\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",120834984)");
        db.execSQL("INSERT INTO person VALUES(\"Miram\",\"Gand\",\"Gazilda\",\"Hebee\",\"1926-09-14\",\"English\",\"other\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",19976424)");
        db.execSQL("INSERT INTO person VALUES(\"Mirac\",\"Charleroi\",\"Gazildee\",\"Hebernia\",\"1956-07-08\",\"English\",\"black\",\"brown\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"B\",68183022)");
        db.execSQL("INSERT INTO person VALUES(\"Mirach\",\"Anvers\",\"Gedalia\",\"Hecterine\",\"1979-01-16\",\"Français\",\"blond\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"F\",160874809)");
        db.execSQL("INSERT INTO person VALUES(\"Mirak\",\"Gand\",\"Gedalie\",\"Hectorette\",\"1969-11-09\",\"Français\",\"black\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"F\",209534433)");
        db.execSQL("INSERT INTO person VALUES(\"Mirakh\",\"Charleroi\",\"Gedegonde\",\"Hectoria\",\"1952-06-04\",\"Français\",\"black\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"F\",39831918)");
        db.execSQL("INSERT INTO person VALUES(\"Mirfak\",\"Bruxelles\",\"Gedelia\",\"Hectorienne\",\"1992-09-15\",\"English\",\"brown\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",163625753)");
        db.execSQL("INSERT INTO person VALUES(\"Mirphak\",\"Mons\",\"Gedeline\",\"Hectoriette\",\"1934-01-13\",\"Français\",\"other\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"B\",196417464)");
        db.execSQL("INSERT INTO person VALUES(\"Mirtsam\",\"Anvers\",\"Gedelise\",\"Hectorina\",\"1922-01-24\",\"Français\",\"other\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"M\",237280596)");
        db.execSQL("INSERT INTO person VALUES(\"Mirza\",\"Charleroi\",\"Gedeonne\",\"Hectorine\",\"1987-10-15\",\"English\",\"black\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",218695095)");
        db.execSQL("INSERT INTO person VALUES(\"Mirzam\",\"Louvain-La-Neuve\",\"Gedina\",\"Hectorise\",\"1904-07-14\",\"English\",\"red\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"B\",222480625)");
        db.execSQL("INSERT INTO person VALUES(\"Mismar\",\"Bruxelles\",\"Gedinia\",\"Hectovie\",\"1939-05-11\",\"English\",\"other\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"M\",97954084)");
        db.execSQL("INSERT INTO person VALUES(\"Misam\",\"Mons\",\"Geffrine\",\"Hedelianne\",\"1989-07-06\",\"Français\",\"red\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",240201923)");
        db.execSQL("INSERT INTO person VALUES(\"Mitsar\",\"Charleroi\",\"Gelenora\",\"Hedelienne\",\"1912-03-02\",\"English\",\"other\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",46616533)");
        db.execSQL("INSERT INTO person VALUES(\"Mizar\",\"Gand\",\"Gelinda\",\"Hedemora\",\"1908-06-17\",\"English\",\"red\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",21636389)");
        db.execSQL("INSERT INTO person VALUES(\"Mizat\",\"Gand\",\"Geline\",\"Hedia\",\"1921-05-24\",\"English\",\"blond\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",9424732)");
        db.execSQL("INSERT INTO person VALUES(\"Monkar\",\"Mons\",\"Gelisee\",\"Hedine\",\"1903-02-03\",\"English\",\"other\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",257713086)");
        db.execSQL("INSERT INTO person VALUES(\"Motallakh\",\"Charleroi\",\"Gelisette\",\"Hedivilda\",\"1961-10-01\",\"Français\",\"brown\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"B\",236088510)");
        db.execSQL("INSERT INTO person VALUES(\"Mothallah\",\"Charleroi\",\"Gelmira\",\"Hedola\",\"1923-06-22\",\"Français\",\"blond\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"F\",40125984)");
        db.execSQL("INSERT INTO person VALUES(\"Mufrid\",\"Namur\",\"Gelna\",\"Hedonine\",\"1955-09-04\",\"Français\",\"brown\",\"blue\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",70079174)");
        db.execSQL("INSERT INTO person VALUES(\"Mufride\",\"Bruxelles\",\"Gelsemina\",\"Hedonise\",\"1983-09-21\",\"Français\",\"blond\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"M\",37102057)");
        db.execSQL("INSERT INTO person VALUES(\"Muhlifain\",\"Anvers\",\"Gema\",\"Hedroige\",\"1973-09-11\",\"English\",\"other\",\"brown\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"F\",196358304)");
        db.execSQL("INSERT INTO person VALUES(\"Muliphein\",\"Liege\",\"Gemeda\",\"Hedwidge\",\"1935-08-25\",\"English\",\"red\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"M\",80677855)");
        db.execSQL("INSERT INTO person VALUES(\"Muliphen\",\"Charleroi\",\"Gemelina\",\"Hedwilda\",\"1975-08-15\",\"English\",\"blond\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"F\",\"M\",131960453)");
        db.execSQL("INSERT INTO person VALUES(\"Muphrid\",\"Namur\",\"Gemeline\",\"Hedwildee\",\"1962-08-08\",\"Français\",\"red\",\"black\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",69604319)");
        db.execSQL("INSERT INTO person VALUES(\"Muphride\",\"Anvers\",\"Gemelinia\",\"Helanda\",\"1964-10-07\",\"Français\",\"other\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"B\",246021688)");
        db.execSQL("INSERT INTO person VALUES(\"Murfach\",\"Bruxelles\",\"Gemelinie\",\"Helanise\",\"1929-08-22\",\"Français\",\"brown\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",231927590)");
        db.execSQL("INSERT INTO person VALUES(\"Murzim\",\"Namur\",\"Gemelise\",\"Helaria\",\"1952-01-05\",\"English\",\"red\",\"green\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",188840946)");
        db.execSQL("INSERT INTO person VALUES(\"Muscida\",\"Namur\",\"Gemila\",\"Helarie\",\"1953-07-12\",\"Français\",\"brown\",\"brown\",\"password\",\"Euh...flemme... prout d'ecrire une description\",\"M\",\"F\",238937317)");


        db.execSQL("INSERT INTO friendship VALUES(\"Maia\",\"Markab\",\"null\")");
        db.execSQL("INSERT INTO friendship VALUES(\"Maia\",\"Masym\",\"null\")");
        db.execSQL("INSERT INTO friendship VALUES(\"Maia\",\"Meboula\",\"null\")");
        db.execSQL("INSERT INTO friendship VALUES(\"Maia\",\"Media\",\"BlablbalalalezklnCanailleo,fzlkne\")");
        db.execSQL("INSERT INTO friendship VALUES(\"Maia\",\"Men\",\"BlablbalalalezklnCanailleo,fzlkne\")");
        db.execSQL("INSERT INTO friendship VALUES(\"Maia\",\"Menka\",\"BlablbalalalezklnCanailleo,fzlkne\")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PERSON_TABLE_DROP);
        db.execSQL(SCORE_TABLE_DROP);
        db.execSQL(ALBUM_TABLE_DROP);
        db.execSQL(FRIENDSHIP_TABLE_DROP);
        db.execSQL(RENDEZVOUS_TABLE_DROP);
        db.execSQL(AVAILABILITY_TABLE_DROP);
        onCreate(db);
    }

}
