package com.simple.serviceDeskApplication.exceptions;

import com.simple.serviceDeskApplication.util.exception.BadRequestException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BadRequestExceptionTest{


    @Test
    public void test_constructor(){
        //when
        BadRequestException userRegularException = new BadRequestException("Message 1");
        //than
        Assertions.assertThat(userRegularException.getMessage()).isEqualTo("Message 1");
    }

}