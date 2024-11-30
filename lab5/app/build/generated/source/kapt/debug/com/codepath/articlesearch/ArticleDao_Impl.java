package com.codepath.articlesearch;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ArticleDao_Impl implements ArticleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ArticleEntity> __insertionAdapterOfArticleEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ArticleDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfArticleEntity = new EntityInsertionAdapter<ArticleEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `article_table` (`id`,`headline`,`articleAbstract`,`byline`,`mediaImageUrl`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ArticleEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getHeadline() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getHeadline());
        }
        if (value.getArticleAbstract() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getArticleAbstract());
        }
        if (value.getByline() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getByline());
        }
        if (value.getMediaImageUrl() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getMediaImageUrl());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM article_table";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(final List<ArticleEntity> articles) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfArticleEntity.insert(articles);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public Flow<List<ArticleEntity>> getAll() {
    final String _sql = "SELECT * FROM article_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"article_table"}, new Callable<List<ArticleEntity>>() {
      @Override
      public List<ArticleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "headline");
          final int _cursorIndexOfArticleAbstract = CursorUtil.getColumnIndexOrThrow(_cursor, "articleAbstract");
          final int _cursorIndexOfByline = CursorUtil.getColumnIndexOrThrow(_cursor, "byline");
          final int _cursorIndexOfMediaImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "mediaImageUrl");
          final List<ArticleEntity> _result = new ArrayList<ArticleEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ArticleEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpHeadline;
            if (_cursor.isNull(_cursorIndexOfHeadline)) {
              _tmpHeadline = null;
            } else {
              _tmpHeadline = _cursor.getString(_cursorIndexOfHeadline);
            }
            final String _tmpArticleAbstract;
            if (_cursor.isNull(_cursorIndexOfArticleAbstract)) {
              _tmpArticleAbstract = null;
            } else {
              _tmpArticleAbstract = _cursor.getString(_cursorIndexOfArticleAbstract);
            }
            final String _tmpByline;
            if (_cursor.isNull(_cursorIndexOfByline)) {
              _tmpByline = null;
            } else {
              _tmpByline = _cursor.getString(_cursorIndexOfByline);
            }
            final String _tmpMediaImageUrl;
            if (_cursor.isNull(_cursorIndexOfMediaImageUrl)) {
              _tmpMediaImageUrl = null;
            } else {
              _tmpMediaImageUrl = _cursor.getString(_cursorIndexOfMediaImageUrl);
            }
            _item = new ArticleEntity(_tmpId,_tmpHeadline,_tmpArticleAbstract,_tmpByline,_tmpMediaImageUrl);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
