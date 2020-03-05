package edu.cnm.deepdive.notes.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.notes.model.entity.Note;
import edu.cnm.deepdive.notes.model.repository.NoteRepository;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

  private NoteRepository repository;
  private MutableLiveData<Note> note;
  private MutableLiveData<Throwable> throwable;
  // TODO Declare and use a CompositeDisposable

  public MainViewModel(@NonNull Application application) {
    super(application);
    repository = new NoteRepository();
    note = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
  }

  public LiveData<List<Note>> getAll() {
    throwable.setValue(null);
    return repository.getAll();
  }

  public LiveData<Note> getNote() {
    return note;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void setNoteId(long id) {
    throwable.setValue(null);
    repository.get(id)
        .subscribe(
            note::postValue,
            throwable::postValue
        );
  }

  public void save(Note note) {
    throwable.setValue(null);
    repository.save(note)
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }

  public void remove(Note note) {
    throwable.setValue(null);
    repository.remove(note)
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }

}
