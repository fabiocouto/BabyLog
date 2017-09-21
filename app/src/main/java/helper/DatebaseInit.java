package helper;

import android.content.Context;

import dao.LocalStorageDAO;

public class DatebaseInit extends LocalStorageDAO {

	private static final String SCRIPT_DATABASE_DELETE = " DROP TABLE IF EXISTS baby ;" +
			" DROP TABLE IF EXISTS activity ;" +
            " DROP TABLE IF EXISTS syncstatus ;" +
            " DROP TABLE IF EXISTS session ;" ;

	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
	"CREATE TABLE IF NOT EXISTS syncstatus ( _id integer primary key autoincrement, last_sync_date text not null, last_sync_hour text not null, baby_log_group integer null, generic_info text null)",
    "CREATE TABLE IF NOT EXISTS session ( _id integer primary key autoincrement, name text null, email text null, password text null)",
    "CREATE TABLE IF NOT EXISTS activity ( _id integer primary key autoincrement, idactivity integer null, title text null, description text null, username text null, category text null, platform text null, local_date text null, local_hour text null)",
	"CREATE TABLE IF NOT EXISTS baby ( _id integer primary key autoincrement, idbaby integer null, name text not null, birth_date text null)"};

	private static final String DATABASE_NAME = "babylog_db";
	// Controle de versao
	private static final int DATABASE_VERSION = 1;
	private SQLiteHelper dbHelper;

	// Cria o banco de dados com um script SQL
	public DatebaseInit(Context ctx) {

		dbHelper = new SQLiteHelper(ctx, DatebaseInit.DATABASE_NAME,
				DatebaseInit.DATABASE_VERSION,
				DatebaseInit.SCRIPT_DATABASE_CREATE,
				DatebaseInit.SCRIPT_DATABASE_DELETE);
		//db = dbHelper.getWritableDatabase();
	}
//
//@Override
//public void closeDataBase(){
//super.closeDataBase();
//if (dbHelper != null) {
//dbHelper.close();
//}
//}
}
