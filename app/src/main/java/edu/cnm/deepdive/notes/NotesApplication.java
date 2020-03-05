package edu.cnm.deepdive.notes;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.notes.service.NotesDatabase;

public class NotesApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    NotesDatabase.setContext(this);
    Stetho.initializeWithDefaults(this);
  }

}
