package com.novacapital.database;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.novacapital.models.Inversion;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class InversionDao_Impl implements InversionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Inversion> __insertionAdapterOfInversion;

  public InversionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfInversion = new EntityInsertionAdapter<Inversion>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `inversiones` (`id`,`idProyecto`,`cantidad`,`fecha`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Inversion entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getIdProyecto());
        statement.bindDouble(3, entity.getCantidad());
        if (entity.getFecha() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getFecha());
        }
      }
    };
  }

  @Override
  public void insertar(final Inversion inversion) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfInversion.insert(inversion);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Inversion> obtenerTodas() {
    final String _sql = "SELECT * FROM inversiones ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfIdProyecto = CursorUtil.getColumnIndexOrThrow(_cursor, "idProyecto");
      final int _cursorIndexOfCantidad = CursorUtil.getColumnIndexOrThrow(_cursor, "cantidad");
      final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
      final List<Inversion> _result = new ArrayList<Inversion>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Inversion _item;
        final int _tmpIdProyecto;
        _tmpIdProyecto = _cursor.getInt(_cursorIndexOfIdProyecto);
        final double _tmpCantidad;
        _tmpCantidad = _cursor.getDouble(_cursorIndexOfCantidad);
        final String _tmpFecha;
        if (_cursor.isNull(_cursorIndexOfFecha)) {
          _tmpFecha = null;
        } else {
          _tmpFecha = _cursor.getString(_cursorIndexOfFecha);
        }
        _item = new Inversion(_tmpIdProyecto,_tmpCantidad,_tmpFecha);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Inversion> obtenerPorProyecto(final int idProyecto) {
    final String _sql = "SELECT * FROM inversiones WHERE idProyecto = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, idProyecto);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfIdProyecto = CursorUtil.getColumnIndexOrThrow(_cursor, "idProyecto");
      final int _cursorIndexOfCantidad = CursorUtil.getColumnIndexOrThrow(_cursor, "cantidad");
      final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
      final List<Inversion> _result = new ArrayList<Inversion>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Inversion _item;
        final int _tmpIdProyecto;
        _tmpIdProyecto = _cursor.getInt(_cursorIndexOfIdProyecto);
        final double _tmpCantidad;
        _tmpCantidad = _cursor.getDouble(_cursorIndexOfCantidad);
        final String _tmpFecha;
        if (_cursor.isNull(_cursorIndexOfFecha)) {
          _tmpFecha = null;
        } else {
          _tmpFecha = _cursor.getString(_cursorIndexOfFecha);
        }
        _item = new Inversion(_tmpIdProyecto,_tmpCantidad,_tmpFecha);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int contarInversiones() {
    final String _sql = "SELECT COUNT(*) FROM inversiones";
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

  @Override
  public int contarProyectosInvertidos() {
    final String _sql = "SELECT COUNT(DISTINCT idProyecto) FROM inversiones";
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
