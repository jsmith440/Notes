package edu.cnm.deepdive.notes.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import edu.cnm.deepdive.notes.service.NotesDatabase.Converters;
import edu.cnm.deepdive.notes.model.dao.NoteDao;
import edu.cnm.deepdive.notes.model.entity.Note;
import java.util.Date;

@Database(
    entities = {Note.class},
    version = 1
)
@TypeConverters(Converters.class)
public abstract class NotesDatabase extends RoomDatabase {

  private static final String DB_NAME = "notes_db";

  private static Application context;

  public static void setContext(Application context) {
    NotesDatabase.context = context;
  }

  public abstract NoteDao getNoteDao();

  public static NotesDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private static class InstanceHolder {

    private static final NotesDatabase INSTANCE =
        Room.databaseBuilder(context, NotesDatabase.class, DB_NAME)
            .build();

  }

  public static class Converters {

    @TypeConverter
    public static Long fromDate(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    @TypeConverter
    public static Date fromLong(Long value) {
      return (value != null) ? new Date(value) : null;
    }

  }

}
