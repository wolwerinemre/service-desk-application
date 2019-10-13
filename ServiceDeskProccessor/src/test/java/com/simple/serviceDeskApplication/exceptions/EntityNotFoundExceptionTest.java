package com.simple.serviceDeskApplication.exceptions;

import com.simple.serviceDeskApplication.util.exception.EntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EntityNotFoundExceptionTest{

    @Test(expected = EntityNotFoundException.class)
    public void constructor(){
        throw new EntityNotFoundException();
    }

}