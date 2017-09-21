package helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Implementacao de SQLiteOpenHelper
 * 
 * Classe utilitaria para abrir, criar, e atualizar o banco de dados
 * 
 * @author Fabio Oliveira
 */
class SQLiteHelper extends SQLiteOpenHelper {

	private static final String CATEGORIA = "BABYLOG_DATABASE";

	private String[] scriptSQLCreate;
	private String scriptSQLDelete;

	/**
	 * Cria uma instancia de SQLiteHelper
	 * 
	 * @param context
	 * @param nomeBanco nome do banco de dados
	 * @param versaoBanco versao do banco de dados (se for diferente eh para atualizar)
	 * @param scriptSQLCreate SQL com o create table..
	 * @param scriptSQLDelete SQL com o drop table...
	 */
	SQLiteHelper(Context context, String nomeBanco, int versaoBanco, String[] scriptSQLCreate, String scriptSQLDelete) {
		super(context, nomeBanco, null, versaoBanco);
		this.scriptSQLCreate = scriptSQLCreate;
		this.scriptSQLDelete = scriptSQLDelete;

	}

	@Override
	// Criar novo banco...
	public void onCreate(SQLiteDatabase db) {
		int qtdeScripts = scriptSQLCreate.length;
		db.execSQL(scriptSQLDelete);
		// Executa cada sql passado como parametro
		for (int i = 0; i < qtdeScripts; i++) {
			String sql = scriptSQLCreate[i];
			//Log.i(CATEGORIA, sql);
			db.execSQL(sql);
		}
	}

	@Override
	// Mudou a vers�o...
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Log.w(CATEGORIA, "Atualizando da versao " + versaoAntiga + " para " + novaVersao + ". Todos os registros serao deletados.");
		//Log.i(CATEGORIA, scriptSQLDelete);
		// Deleta as tabelas...
		db.execSQL(scriptSQLDelete);
		// Cria novamente...
		onCreate(db);
		if(newVersion > oldVersion){

	     }
	}
}