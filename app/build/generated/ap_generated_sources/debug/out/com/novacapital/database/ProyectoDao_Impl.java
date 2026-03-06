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
import com.novacapital.models.Proyecto;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ProyectoDao_Impl implements ProyectoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Proyecto> __insertionAdapterOfProyecto;

  private final EntityDeletionOrUpdateAdapter<Proyecto> __updateAdapterOfProyecto;

  public ProyectoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProyecto = new EntityInsertionAdapter<Proyecto>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `proyectos` (`id`,`nombre`,`descripcion`,`inversionObjetivo`,`inversionActual`,`estado`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Proyecto entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNombre() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNombre());
        }
        if (entity.getDescripcion() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescripcion());
        }
        statement.bindDouble(4, entity.getInversionObjetivo());
        statement.bindDouble(5, entity.getInversionActual());
        if (entity.getEstado() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getEstado());
        }
      }
    };
    this.__updateAdapterOfProyecto = new EntityDeletionOrUpdateAdapter<Proyecto>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `proyectos` SET `id` = ?,`nombre` = ?,`descripcion` = ?,`inversionObjetivo` = ?,`inversionActual` = ?,`estado` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Proyecto entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNombre() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNombre());
        }
        if (entity.getDescripcion() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescripcion());
        }
        statement.bindDouble(4, entity.getInversionObjetivo());
        statement.bindDouble(5, entity.getInversionActual());
        if (entity.getEstado() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getEstado());
        }
        statement.bindLong(7, entity.getId());
      }
    };
  }

  @Override
  public void insertar(final Proyecto proyecto) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfProyecto.insert(proyecto);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void actualizar(final Proyecto proyecto) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfProyecto.handle(proyecto);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Proyecto> obtenerTodos() {
    final String _sql = "SELECT * FROM proyectos ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
      final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
      final int _cursorIndexOfInversionObjetivo = CursorUtil.getColumnIndexOrThrow(_cursor, "inversionObjetivo");
      final int _cursorIndexOfInversionActual = CursorUtil.getColumnIndexOrThrow(_cursor, "inversionActual");
      final int _cursorIndexOfEstado = CursorUtil.getColumnIndexOrThrow(_cursor, "estado");
      final List<Proyecto> _result = new ArrayList<Proyecto>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Proyecto _item;
        final String _tmpNombre;
        if (_cursor.isNull(_cursorIndexOfNombre)) {
          _tmpNombre = null;
        } else {
          _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
        }
        final String _tmpDescripcion;
        if (_cursor.isNull(_cursorIndexOfDescripcion)) {
          _tmpDescripcion = null;
        } else {
          _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
        }
        final double _tmpInversionObjetivo;
        _tmpInversionObjetivo = _cursor.getDouble(_cursorIndexOfInversionObjetivo);
        _item = new Proyecto(_tmpNombre,_tmpDescripcion,_tmpInversionObjetivo);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final double _tmpInversionActual;
        _tmpInversionActual = _cursor.getDouble(_cursorIndexOfInversionActual);
        _item.setInversionActual(_tmpInversionActual);
        final String _tmpEstado;
        if (_cursor.isNull(_cursorIndexOfEstado)) {
          _tmpEstado = null;
        } else {
          _tmpEstado = _cursor.getString(_cursorIndexOfEstado);
        }
        _item.setEstado(_tmpEstado);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Proyecto obtenerPorId(final int id) {
    final String _sql = "SELECT * FROM proyectos WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
      final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
      final int _cursorIndexOfInversionObjetivo = CursorUtil.getColumnIndexOrThrow(_cursor, "inversionObjetivo");
      final int _cursorIndexOfInversionActual = CursorUtil.getColumnIndexOrThrow(_cursor, "inversionActual");
      final int _cursorIndexOfEstado = CursorUtil.getColumnIndexOrThrow(_cursor, "estado");
      final Proyecto _result;
      if (_cursor.moveToFirst()) {
        final String _tmpNombre;
        if (_cursor.isNull(_cursorIndexOfNombre)) {
          _tmpNombre = null;
        } else {
          _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
        }
        final String _tmpDescripcion;
        if (_cursor.isNull(_cursorIndexOfDescripcion)) {
          _tmpDescripcion = null;
        } else {
          _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
        }
        final double _tmpInversionObjetivo;
        _tmpInversionObjetivo = _cursor.getDouble(_cursorIndexOfInversionObjetivo);
        _result = new Proyecto(_tmpNombre,_tmpDescripcion,_tmpInversionObjetivo);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final double _tmpInversionActual;
        _tmpInversionActual = _cursor.getDouble(_cursorIndexOfInversionActual);
        _result.setInversionActual(_tmpInversionActual);
        final String _tmpEstado;
        if (_cursor.isNull(_cursorIndexOfEstado)) {
          _tmpEstado = null;
        } else {
          _tmpEstado = _cursor.getString(_cursorIndexOfEstado);
        }
        _result.setEstado(_tmpEstado);
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
  public int contarProyectos() {
    final String _sql = "SELECT COUNT(*) FROM proyectos";
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
