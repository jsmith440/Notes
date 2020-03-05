package edu.cnm.deepdive.notes.model.repository;

import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.notes.model.dao.NoteDao;
import edu.cnm.deepdive.notes.model.entity.Note;
import edu.cnm.deepdive.notes.service.NotesDatabase;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class NoteRepository {

  private final NoteDao dao;

  public NoteRepository() {
    NotesDatabase database = NotesDatabase.getInstance();
    dao = database.getNoteDao();
  }

  public Completable save(Note note) {
    if (note.getId() == 0) {
      return Completable.fromSingle(
          dao.insert(note)
              .subscribeOn(Schedulers.io())
      );
    } else {
      return Completable.fromSingle(
          dao.update(note)
              .subscribeOn(Schedulers.io())
      );
    }
  }

  public Completable remove(Note note) {
    return Completable.fromSingle(
        dao.delete(note)
            .subscribeOn(Schedulers.io())
    );
  }

  public LiveData<List<Note>> getAll() {
    return dao.select();
  }

  public Single<Note> get(long id) {
    return dao.select(id)
        .subscribeOn(Schedulers.io());
  }

}
