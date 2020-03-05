package edu.cnm.deepdive.notes.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.notes.model.entity.Note;
import io.reactivex.Single;
import java.util.List;

@Dao
public interface NoteDao {

  @Insert
  Single<Long> insert(Note note);

  @Insert
  Single<List<Long>> insert(Note... notes);

  @Update
  Single<Integer> update(Note notes);

  @Delete
  Single<Integer> delete(Note... notes);

  @Query("SELECT * FROM Note ORDER BY created DESC")
  LiveData<List<Note>> select();

  @Query("SELECT * FROM Note WHERE note_id =:id")
  Single<Note> select(long id);


}
