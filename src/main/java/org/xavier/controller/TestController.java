package org.xavier.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xavier.domain.po.User;
import org.xavier.domain.protobufObj.ProtoBuf;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 描述信息：<br/>
 *
 * @author ProtoBuf
 * @version 1.0
 * @date 2018.01.16
 * @since Jdk 1.8
 */
@RestController
public class TestController {
    @Autowired
    ObjectMapper mapper;

    private static final ProtoBuf.User user;

    static {
        ProtoBuf.Article article1 = ProtoBuf.Article.newBuilder()
                .setTitle("演员的自我修养")
                .setSellWell(true)
                .build();
        ProtoBuf.Article article2 = ProtoBuf.Article.newBuilder()
                .setTitle("演员的自我修养2")
                .build();
        user = ProtoBuf.User.newBuilder()
                .setUName("张三")
                .addArticles(article1)
                .addArticles(article2).build();
        System.out.println("初始化");
    }

    @GetMapping(value = "/user")
    public ResponseEntity<?> getUser() {
        FileOutputStream fos = null;
        System.out.println(user.toString());
        try {
            fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\user.protoout");
            fos.write(user.toByteArray());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MediaType mediaType = new MediaType("application", "x-protobuf", Charset.forName("UTF-8"));
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.OK);
        builder.contentType(mediaType);
        return builder.body(user.toString());
    }

    @PostMapping(value = "/user")
    public ResponseEntity<?> saveUser(HttpServletRequest rowData) throws IOException {
        String contentType = rowData.getHeader("Content-Type");
        MediaType mediaType;
        ResponseEntity.BodyBuilder builder;
        User result;
        switch (contentType.toLowerCase()) {
            case "application/json":
                result = mapper.readValue(rowData.getInputStream(), User.class);
                mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
                builder = ResponseEntity.status(HttpStatus.OK);
                builder.contentType(mediaType);
                return builder.body(result);
            case "application/x-protobuf":
                ProtoBuf.User currentUser = ProtoBuf.User.parseFrom(rowData.getInputStream());
                result = new User(currentUser);
                mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
                builder = ResponseEntity.status(HttpStatus.OK);
                builder.contentType(mediaType);
                return builder.body(result);
            default:
                mediaType = new MediaType("application", "x-protobuf", Charset.forName("UTF-8"));
                builder = ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
                builder.contentType(mediaType);
                return builder.body("UNSUPPORTED_MEDIA_TYPE".toString());
        }
    }

    @PostMapping(value = "", consumes = "application/x-protobuf")
    public ResponseEntity<?> te(@RequestBody ProtoBuf.User currentUser) {
        MediaType mediaType = new MediaType("application", "x-protobuf", Charset.forName("UTF-8"));
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.OK);
        builder.contentType(mediaType);
        return builder.body(currentUser.toString());
    }

}
