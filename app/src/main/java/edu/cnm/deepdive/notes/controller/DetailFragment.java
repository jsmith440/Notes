package edu.cnm.deepdive.notes.controller;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.notes.R;
import edu.cnm.deepdive.notes.model.entity.Note;
import edu.cnm.deepdive.notes.viewmodel.MainViewModel;

public class DetailFragment extends DialogFragment {

  private static final String ID_KEY = "id";

  private long id;
  private View view;
  private EditText subject;
  private EditText text;
  private Note note;
  private MainViewModel viewModel;

  public static DetailFragment newInstance(long id) {
    DetailFragment fragment = new DetailFragment();
    Bundle args = new Bundle();
    args.putLong(ID_KEY, id);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      id = getArguments().getLong(ID_KEY, 0);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
//    return inflater.inflate(R.layout.fragment_detail, container, false);
    return view;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_detail, null, false);
    subject = view.findViewById(R.id.subject);
    text = view.findViewById(R.id.text);
    return new AlertDialog.Builder(getContext())
        .setTitle("Note Details")
        .setView(view)
        .setPositiveButton("OK", (dlg, which) -> {
          note.setSubject(subject.getText().toString());
          note.setText(text.getText().toString());
          viewModel.save(note);
        })
        .setNegativeButton("Cancel", (dlg, which) -> { /* Do nothing */})
        .create();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    viewModel.getNote().observe(getViewLifecycleOwner(), (note) -> {
      this.note = note;
      subject.setText(note.getSubject());
      text.setText(note.getText());
    });
    if (id != 0) {
      viewModel.setNoteId(id);
    } else {
      note = new Note();
    }
  }

}
