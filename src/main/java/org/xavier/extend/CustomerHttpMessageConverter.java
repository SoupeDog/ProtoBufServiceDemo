package org.xavier.extend;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.xavier.domain.protobufObj.ProtoBuf;

import java.io.IOException;

/**
 * 描述信息：<br/>
 *
 * @author Xavier
 * @version 1.0
 * @date 2018.01.17
 * @since Jdk 1.8
 */
public class CustomerHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    private static final String USERCLASSNAME = "org.xavier.domain.protobufObj.ProtoBuf$User";
    private static final MediaType APPLICATION_PROTOBUF = new MediaType("application", "x-protobuf");

    public CustomerHttpMessageConverter() {
        super(APPLICATION_PROTOBUF);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        switch (clazz.getName()) {
            case USERCLASSNAME:
                return true;
            default:
                return false;
        }
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        ProtoBuf.User user = ProtoBuf.User.parseFrom(inputMessage.getBody());
        return user;
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String temp = (String) o;
        outputMessage.getBody().write(temp.getBytes());
    }
}
