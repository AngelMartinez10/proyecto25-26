package com.novacapital.database;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.novacapital.models.Reto;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RetoDao_Impl implements RetoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Reto> __insertionAdapterOfReto;

  private final EntityDeletionOrUpdateAdapter<Reto> __updateAdapterOfReto;

  public RetoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReto = new EntityInsertionAdapter<Reto>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `retos` (`id`,`titulo`,`recompensa`,`completado`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Reto entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitulo() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitulo());
        }
        statement.bindDouble(3, entity.getRecompensa());
        final int _tmp = entity.isCompletado() ? 1 : 0;
        statement.bindLong(4, _tmp);
      }
    };
    this.__updateAdapterOfReto = new EntityDeletionOrUpdateAdapter<Reto>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `retos` SET `id` = ?,`titulo` = ?,`recompensa` = ?,`completado` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Reto entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitulo() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitulo());
        }
        statement.bindDouble(3, entity.getRecompensa());
        final int _tmp = entity.isCompletado() ? 1 : 0;
        statement.bindLong(4, _tmp);
        statement.bindLong(5, entity.getId());
      }
    };
  }

  @Override
  public void insertar(final Reto reto) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfReto.insert(reto);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void actualizar(final Reto reto) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfReto.handle(reto);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Reto> obtenerTodos() {
    final String _sql = "SELECT * FROM retos";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
      final int _cursorIndexOfRecompensa = CursorUtil.getColumnIndexOrThrow(_cursor, "recompensa");
      final int _cursorIndexOfCompletado = CursorUtil.getColumnIndexOrThrow(_cursor, "completado");
      final List<Reto> _result = new ArrayList<Reto>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Reto _item;
        final String _tmpTitulo;
        if (_cursor.isNull(_cursorIndexOfTitulo)) {
          _tmpTitulo = null;
        } else {
          _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
        }
        final double _tmpRecompensa;
        _tmpRecompensa = _cursor.getDouble(_cursorIndexOfRecompensa);
        _item = new Reto(_tmpTitulo,_tmpRecompensa);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final boolean _tmpCompletado;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfCompletado);
        _tmpCompletado = _tmp != 0;
        _item.setCompletado(_tmpCompletado);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Reto obtenerPorId(final int id) {
    final String _sql = "SELECT * FROM retos WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
      final int _cursorIndexOfRecompensa = CursorUtil.getColumnIndexOrThrow(_cursor, "recompensa");
      final int _cursorIndexOfCompletado = CursorUtil.getColumnIndexOrThrow(_cursor, "completado");
      final Reto _result;
      if (_cursor.moveToFirst()) {
        final String _tmpTitulo;
        if (_cursor.isNull(_cursorIndexOfTitulo)) {
          _tmpTitulo = null;
        } else {
          _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
        }
        final double _tmpRecompensa;
        _tmpRecompensa = _cursor.getDouble(_cursorIndexOfRecompensa);
        _result = new Reto(_tmpTitulo,_tmpRecompensa);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final boolean _tmpCompletado;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfCompletado);
        _tmpCompletado = _tmp != 0;
        _result.setCompletado(_tmpCompletado);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int contarRetos() {
    final String _sql = "SELECT COUNT(*) FROM retos";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
