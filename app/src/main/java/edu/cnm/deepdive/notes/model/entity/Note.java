package edu.cnm.deepdive.notes.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity
public class Note {

  @ColumnInfo(name = "note_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @NonNull
  @ColumnInfo(index = true)
  private Date created = new Date();

  @NonNull
  @ColumnInfo(index = true, collate = ColumnInfo.NOCASE)
  private String subject;

  @NonNull
  private String text;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @NonNull
  public String getSubject() {
    return subject;
  }

  public void setSubject(@NonNull String subject) {
    this.subject = subject;
  }

  @NonNull
  public String getText() {
    return text;
  }

  public void setText(@NonNull String text) {
    this.text = text;
  }

  @NonNull
  @Override
  public String toString() {
    return String.format("[%1$s] %2$s", created, subject);
  }

}
