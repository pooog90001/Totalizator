package by.epam.totalizator.controller;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize = 1024 * 1024 * 15) // 15MB
@WebServlet(name = "UploadController", urlPatterns = {"/uploadController"}) //TODO эти значения надо в констаннты?
public class UploadController extends AbstractController {

}
