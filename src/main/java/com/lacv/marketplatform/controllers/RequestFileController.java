package com.lacv.marketplatform.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/requestcontent")
public class RequestFileController {
    
    /*private final String PATH_LOCAL = "/home/lacastrillov/Documentos/upload/";
    
    private FileServiceImpl fileServiceImpl;
    
    protected static final Logger LOGGER = Logger.getLogger(RestController.class);
    
    private final Long maxFileSizeToUpload=1024L;
    
    @PostConstruct
    public void init(){
        fileServiceImpl= new FileServiceImpl();
    }
    
    
    @RequestMapping(value = "/ajax/uploadFile.htm", method = RequestMethod.POST)
    public @ResponseBody String uploadFile(@RequestParam(value = "archivo", required = true) CommonsMultipartFile archivo) {
        
        fileServiceImpl.saveFile(archivo, archivo.getOriginalFilename(), PATH_LOCAL);

        return archivo.getOriginalFilename();
    }
 
    @RequestMapping(value = "/ajax/upload/file/{idEntity}.htm")
    @ResponseBody
    public String upload(HttpServletRequest request, @PathVariable String idEntity) {
        //50MB
        long maxFileSize= maxFileSizeToUpload * 1024 * 1024;

        try {
            ServletFileUpload upload = new ServletFileUpload();
            upload.setSizeMax(maxFileSize);
            
            FileItemIterator iterator;

            iterator = upload.getItemIterator(request);
            while (iterator.hasNext()) {
                FileItemStream fileIS = iterator.next();
                if (fileIS.getName()!=null && !fileIS.getName().equals("")){
                    InputStream initialStream= fileIS.openStream();
                    
                    byte[] buffer = new byte[initialStream.available()];
                    initialStream.read(buffer);
                    
                    File targetFile = new File(PATH_LOCAL+fileIS.getName());
                    OutputStream outStream = new FileOutputStream(targetFile);
                    outStream.write(buffer);
                }
            }
            return "write...";
        } catch (Exception e) {
            LOGGER.error("upload ", e);
            return Util.getOperationCallback(null, "Error en actualizaci&oacute;n" + e.getMessage(), false, null);
        }
    }*/

}
