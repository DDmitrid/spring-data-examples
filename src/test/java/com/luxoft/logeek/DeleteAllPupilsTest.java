package com.luxoft.logeek;

import com.luxoft.logeek.entity.Pupil;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertTrue;

@SuppressWarnings("unused")
public class DeleteAllPupilsTest extends TestBase {
    
    /**
     * What queries will be executed?
     */
    @Test
    public void deleteAll() throws Exception {
        repository.deleteAll();
        assertTrue(repository.findAll().isEmpty());
    }
}
