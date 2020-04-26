package com.gnetna.zimos.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@RestController
public class ApiController {
    @GetMapping("/images")
    ResponseEntity<List<Map<String,String>>> getImages(){
        File dir = new File("./images");
        File[] files = dir.listFiles();
        Objects.requireNonNull(files);

        List<Map<String, String>> result = new LinkedList<>();

        for (File file : files) {
            String name = file.getName();
            System.out.println();
            result.add(new HashMap<String, String>(2){{
                put("title", name.substring(0, name.lastIndexOf('.')));
                put("src", "image/" + name);
            }});
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/image/{name}", produces = "image/jpeg;charset:utf-8;")
    void getImage(HttpServletResponse response, @PathVariable String name) {
        try {
            FileImageInputStream stream = new FileImageInputStream(new File("./images/"+name));

            byte[] image = new byte[(int) stream.length()];

            stream.read(image);
            OutputStream os = response.getOutputStream();
            os.write(image);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
