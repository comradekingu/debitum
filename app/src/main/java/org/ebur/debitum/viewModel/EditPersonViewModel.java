package org.ebur.debitum.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.ebur.debitum.database.Person;
import org.ebur.debitum.database.PersonRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class EditPersonViewModel extends AndroidViewModel {

    private final PersonRepository repository;
    private final LiveData<List<Person>> persons;
    private boolean newPerson;

    public EditPersonViewModel(Application application) {
        super(application);
        repository = new PersonRepository(application);
        persons = repository.getAllPersons();
    }

    public LiveData<List<Person>> getPersons() { return persons; }

    public boolean isNewPerson() { return newPerson; }
    public void setNewPerson(boolean newPerson) { this.newPerson = newPerson; }

    public void addPerson(String name) {repository.insert(new Person(name));}
    public boolean personExists(String name) throws ExecutionException, InterruptedException { return repository.exists(name); }
}