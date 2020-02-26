package com.vedantu.controllers;

import com.vedantu.requests.UploadRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;

@Controller
public class UploadController {

    private final String uploadDirectoryPath = "/home/user/Desktop/uploads";

    @RequestMapping("/form")
    public String form(){
        return "form";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView upload(UploadRequest uploadRequest) throws IOException {
        /* uploadRequest is formed but data in it based on the users form attributes
           all these are taken care by spring
        Note:  @RequestBody only works if there is need to conversion from json to object. not for form data to object
        for form data, just use with @RequestBOdy annotation,
        But to convert json to UploadRequest object, we should use @RequestBody
        For single, form value or json value, @RequestParam works
         */
        /*Note: only username and file null, if there is no username or file name attribute in form. otherwise empty.*/
        if(uploadRequest.getFile().isEmpty() || uploadRequest.getUsername().equals("")){
            ModelAndView errorModel = new ModelAndView("error");
            errorModel.addObject("message", "no username or empty file. both are mandatory");
            return  errorModel;
        }
        /* writing to disk */

        File uploadDir = new File(uploadDirectoryPath);
        if(uploadDir.mkdir()){
            System.out.println("directory created successfully...");
        }else{
            System.out.println("directory already exists");
        }

        /* creating file */
        String fileName = uploadRequest.getFile().getOriginalFilename();
        String time = String.valueOf(System.currentTimeMillis());
        String filePath = uploadDirectoryPath + "/" + time + "_"+fileName;
        File file = new File(filePath); // this will create a file
        /*file.createNewFile(); // creates new file if not presents returns true; otherwise no create of file and returns false*/
        /* following will create stream to the filepath. if not exists, creates. or we can use FileWriter
           FileWriter takes string to write , FileOutputStreams take bytes
           both will create file if not exists. append or overwrite depends on the second parameter */
        FileOutputStream outputStream = new FileOutputStream(file, false); // true to append. false to overwrite
        outputStream.write(uploadRequest.getFile().getBytes());
        outputStream.close();

        /* return ModelAndView */
        ModelAndView successModel = new ModelAndView("success");
        successModel.addObject("username", uploadRequest.getUsername());
        successModel.addObject("message", "uploaded successfullyy");
        return successModel;
    }
}
