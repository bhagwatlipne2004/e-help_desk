package com.superx.Controller.AI;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.superx.view.AI.Note;
import com.superx.view.AI.Notesdao;




public class NotesController {
    private Notesdao c2w_ai_notesDao = new Notesdao();
    
    public void addNote(Note c2w_ai_note) {
        try {
            c2w_ai_notesDao.addData("notes", 
                String.valueOf(System.currentTimeMillis()), c2w_ai_note);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    
    public List<Note> getAllNotesForUser(String c2w_ai_userName) {
        try {
            return c2w_ai_notesDao.getDataList("notes", c2w_ai_userName);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
