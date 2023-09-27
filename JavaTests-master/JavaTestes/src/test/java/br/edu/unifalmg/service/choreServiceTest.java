package br.edu.unifalmg.service;

import br.edu.unifalmg.exception.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class choreServiceTest {

    @Test
    void addChoreWhenTheDescriptionIsInvalidThrowAnException() {
        ChoreService service = new ChoreService();
        assertAll(
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore(null, null)),
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore("", null)),
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore(null, LocalDate.now().plusDays(1))),
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore("", LocalDate.now().plusDays(1))),
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore(null, LocalDate.now().minusDays(1))),
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore("", LocalDate.now().minusDays(1)))
        );
    }


    @Test
    @DisplayName("#addChore > When the deadline is invalid > Throw an exception")
    void addChoreWhenTheDeadLineIsInvalidThrowAnException() {
        //LocalDate.of(2023,2,31);
        ChoreService service = new ChoreService();
        assertAll(
                () ->assertThrows(InvalidDeadLineException.class,
                        ()-> service.addChore("Description", null)),
                () ->assertThrows(InvalidDeadLineException.class,
                        ()-> service.addChore("Description", LocalDate.now().minusDays(1)))
        );

    }

    @Test
    @DisplayName("#addChore > When adding a chore > When the chore already exists > Throw an exception")
    void addChoreWhenAddingAChoreWhenTheChoreAlreadyExistsThrowAnException(){
        ChoreService service = new ChoreService();
        service.addChore("Description", LocalDate.now());
        assertThrows(DuplicatedChoreException.class,
                ()-> service.addChore("Description", LocalDate.now()));
    }


    @Test
    @DisplayName("#delteChore > When the list is empty > Throw an exception")
    void deleteChoreWhenTheListIsEmptyThrowAnException(){
        ChoreService service = new ChoreService();
        assertThrows(EmptChoreListException.class, () -> {
            service.deleteChore("Qualquer coisa", LocalDate.now());
        });
    }

    @Test
    @DisplayName("#delteChore > When the list is not empty > When the chore does not exist")
    void deleteChoreWhenTheListIsNotEmptyThrowAnException(){
        ChoreService service = new ChoreService();
        service.addChore("Description", LocalDate.now());
        assertThrows(ChoreNotFoundException.class, () -> {
           service.deleteChore("Chore to be deleted", LocalDate.now().plusDays());
        });
    }


    @Test
    @DisplayName("#delteChore > When the list is not empty > When the chore exist")
    void deleteChoreWhenTheListIsNotEmptyWhenTheChoreExistDeleteTheChore(){
        ChoreService service = new ChoreService();
        service.addChore("Chore #01", LocalDate.now().plusDays(1));
        assertEquals(1, service.getChore().size());

        service.deleteChore("Chore #01", LocalDate.now().plusDays(1));
        assertEquals(0, service.getChore().size());

    }









}
















































