package com.novacapital.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UsuarioDao _usuarioDao;

  private volatile ProyectoDao _proyectoDao;

  private volatile InversionDao _inversionDao;

  private volatile RetoDao _retoDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `usuarios` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nombre` TEXT, `saldoAurus` REAL NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `proyectos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nombre` TEXT, `descripcion` TEXT, `inversionObjetivo` REAL NOT NULL, `inversionActual` REAL NOT NULL, `estado` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `inversiones` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `idProyecto` INTEGER NOT NULL, `cantidad` REAL NOT NULL, `fecha` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `retos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `titulo` TEXT, `recompensa` REAL NOT NULL, `completado` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '6a6234e76df4920201b0eea5b89af3c4')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `usuarios`");
        db.execSQL("DROP TABLE IF EXISTS `proyectos`");
        db.execSQL("DROP TABLE IF EXISTS `inversiones`");
        db.execSQL("DROP TABLE IF EXISTS `retos`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUsuarios = new HashMap<String, TableInfo.Column>(3);
        _columnsUsuarios.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("nombre", new TableInfo.Column("nombre", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("saldoAurus", new TableInfo.Column("saldoAurus", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsuarios = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsuarios = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsuarios = new TableInfo("usuarios", _columnsUsuarios, _foreignKeysUsuarios, _indicesUsuarios);
        final TableInfo _existingUsuarios = TableInfo.read(db, "usuarios");
        if (!_infoUsuarios.equals(_existingUsuarios)) {
          return new RoomOpenHelper.ValidationResult(false, "usuarios(com.novacapital.models.Usuario).\n"
                  + " Expected:\n" + _infoUsuarios + "\n"
                  + " Found:\n" + _existingUsuarios);
        }
        final HashMap<String, TableInfo.Column> _columnsProyectos = new HashMap<String, TableInfo.Column>(6);
        _columnsProyectos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProyectos.put("nombre", new TableInfo.Column("nombre", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProyectos.put("descripcion", new TableInfo.Column("descripcion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProyectos.put("inversionObjetivo", new TableInfo.Column("inversionObjetivo", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProyectos.put("inversionActual", new TableInfo.Column("inversionActual", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProyectos.put("estado", new TableInfo.Column("estado", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProyectos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProyectos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProyectos = new TableInfo("proyectos", _columnsProyectos, _foreignKeysProyectos, _indicesProyectos);
        final TableInfo _existingProyectos = TableInfo.read(db, "proyectos");
        if (!_infoProyectos.equals(_existingProyectos)) {
          return new RoomOpenHelper.ValidationResult(false, "proyectos(com.novacapital.models.Proyecto).\n"
                  + " Expected:\n" + _infoProyectos + "\n"
                  + " Found:\n" + _existingProyectos);
        }
        final HashMap<String, TableInfo.Column> _columnsInversiones = new HashMap<String, TableInfo.Column>(4);
        _columnsInversiones.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInversiones.put("idProyecto", new TableInfo.Column("idProyecto", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInversiones.put("cantidad", new TableInfo.Column("cantidad", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInversiones.put("fecha", new TableInfo.Column("fecha", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInversiones = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInversiones = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoInversiones = new TableInfo("inversiones", _columnsInversiones, _foreignKeysInversiones, _indicesInversiones);
        final TableInfo _existingInversiones = TableInfo.read(db, "inversiones");
        if (!_infoInversiones.equals(_existingInversiones)) {
          return new RoomOpenHelper.ValidationResult(false, "inversiones(com.novacapital.models.Inversion).\n"
                  + " Expected:\n" + _infoInversiones + "\n"
                  + " Found:\n" + _existingInversiones);
        }
        final HashMap<String, TableInfo.Column> _columnsRetos = new HashMap<String, TableInfo.Column>(4);
        _columnsRetos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRetos.put("titulo", new TableInfo.Column("titulo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRetos.put("recompensa", new TableInfo.Column("recompensa", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRetos.put("completado", new TableInfo.Column("completado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRetos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRetos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRetos = new TableInfo("retos", _columnsRetos, _foreignKeysRetos, _indicesRetos);
        final TableInfo _existingRetos = TableInfo.read(db, "retos");
        if (!_infoRetos.equals(_existingRetos)) {
          return new RoomOpenHelper.ValidationResult(false, "retos(com.novacapital.models.Reto).\n"
                  + " Expected:\n" + _infoRetos + "\n"
                  + " Found:\n" + _existingRetos);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "6a6234e76df4920201b0eea5b89af3c4", "a71299e5a2037064a0de17bb91236750");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "usuarios","proyectos","inversiones","retos");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `usuarios`");
      _db.execSQL("DELETE FROM `proyectos`");
      _db.execSQL("DELETE FROM `inversiones`");
      _db.execSQL("DELETE FROM `retos`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UsuarioDao.class, UsuarioDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ProyectoDao.class, ProyectoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(InversionDao.class, InversionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RetoDao.class, RetoDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UsuarioDao usuarioDao() {
    if (_usuarioDao != null) {
      return _usuarioDao;
    } else {
      synchronized(this) {
        if(_usuarioDao == null) {
          _usuarioDao = new UsuarioDao_Impl(this);
        }
        return _usuarioDao;
      }
    }
  }

  @Override
  public ProyectoDao proyectoDao() {
    if (_proyectoDao != null) {
      return _proyectoDao;
    } else {
      synchronized(this) {
        if(_proyectoDao == null) {
          _proyectoDao = new ProyectoDao_Impl(this);
        }
        return _proyectoDao;
      }
    }
  }

  @Override
  public InversionDao inversionDao() {
    if (_inversionDao != null) {
      return _inversionDao;
    } else {
      synchronized(this) {
        if(_inversionDao == null) {
          _inversionDao = new InversionDao_Impl(this);
        }
        return _inversionDao;
      }
    }
  }

  @Override
  public RetoDao retoDao() {
    if (_retoDao != null) {
      return _retoDao;
    } else {
      synchronized(this) {
        if(_retoDao == null) {
          _retoDao = new RetoDao_Impl(this);
        }
        return _retoDao;
      }
    }
  }
}
