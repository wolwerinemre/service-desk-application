package com.simple.serviceDeskApplication.exceptions;

import com.simple.serviceDeskApplication.util.exception.PersistencyException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PersistencyExceptionTest {

    @Test
    public void test_constructor(){
        //given
        String message = "message 1";
        //when
        PersistencyException processingException = new PersistencyException(message);
        //then
        Assertions.assertThat(processingException.getMessage()).isEqualTo("message 1");

    }
}