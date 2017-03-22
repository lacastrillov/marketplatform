/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lacv.marketplatform.services.impl;


import com.lacv.marketplatform.daos.WebFileJpa;
import com.lacv.marketplatform.entities.WebFile;
import com.lacv.marketplatform.mappers.WebFileMapper;
import com.lacv.marketplatform.services.WebFileService;
import com.dot.gcpbasedot.dao.GenericDao;
import com.dot.gcpbasedot.dao.Parameters;
import com.dot.gcpbasedot.service.EntityServiceImpl1;
import com.dot.gcpbasedot.util.FileService;
import com.dot.gcpbasedot.util.Util;
import com.google.api.services.storage.model.StorageObject;
import com.lacv.marketplatform.constants.WebConstants;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nalvarez
 */
@Service("webFileService")
public class WebFileServiceImpl extends EntityServiceImpl1<WebFile> implements WebFileService {
    
    @Autowired
    public WebFileJpa webFileJpa;
    
    @Autowired
    public WebFileMapper webFileMapper;
    
    @Override
    public GenericDao getGenericDao(){
        return webFileJpa;
    }
    
    
    @Override
    @Transactional(readOnly= true)
    public WebFile findByPath(String path){
        WebFile webFile, parentFile=null;
        String[] folders= path.split("/");
        
        if(folders.length>0){
            Parameters p1= new Parameters();
            p1.whereIsNull("webFile");
            p1.whereEqual("name", folders[0]);
            try {
                parentFile= super.findUniqueByParameters(p1);
            } catch (Exception ex) {}
            webFile= parentFile;
                
            for(int i=1; i<folders.length; i++){
                if(!folders[i].equals("")){
                    Parameters p2= new Parameters();
                    p2.whereEqual("webFile", parentFile);
                    p2.whereEqual("name", folders[i]);
                    try {
                        parentFile= super.findUniqueByParameters(p2);
                    } catch (Exception ex) {
                        parentFile= null;
                    }
                    if(parentFile!=null){
                        webFile= parentFile;
                    }
                }
            }
            return webFile;
        }
        return null;
    }
    
    @Override
    @Transactional(value = TRANSACTION_MANAGER, propagation = Propagation.REQUIRED)
    public WebFile createByFileData(WebFile webParentFile, int slice, String fileName, String fileType, int fileSize, InputStream is) throws IOException {
        WebFile webFile= new WebFile();
        webFile.setWebFile(webParentFile);
        String path= webFile.getPath();
        String location= WebConstants.LOCAL_DIR + WebConstants.ROOT_FOLDER + path;

        if(slice==0){
            webFile.setName(fileName);
            webFile.setType(fileType);
            webFile.setSize(fileSize);
            webFile.setIcon(Util.getSimpleContentType(fileType));
            webFile.setCreationDate(new Date());
            webFile.setModificationDate(new Date());

            create(webFile);

            FileService.deleteFile(location + fileName);
        }

        FileService.addPartToFile(fileName, location, fileSize, is);

        return webFile;
    }

    @Override
    @Transactional(value = TRANSACTION_MANAGER, propagation = Propagation.REQUIRED)
    public WebFile createByStorageObject(StorageObject object, WebFile parent, String location) {
        WebFile webFile= new WebFile();
        
        webFile.setName(object.getName());
        webFile.setSize(object.getSize().intValue());
        webFile.setType(object.getContentType());
        webFile.setIcon(Util.getSimpleContentType(object.getContentType()));
        webFile.setCreationDate(new Date());
        webFile.setModificationDate(new Date());
        
        super.create(webFile);
        return webFile;
    }

    @Override
    @Transactional(value = TRANSACTION_MANAGER, propagation = Propagation.REQUIRED)
    public WebFile createFolder(WebFile parentFile, String folderName) {
        WebFile webFile= new WebFile();
        webFile.setName(folderName);
        webFile.setCreationDate(new Date());
        webFile.setType("folder");
        webFile.setIcon("folder");
        webFile.setModificationDate(new Date());
        webFile.setSize(1);
        webFile.setWebFile(parentFile);
        super.create(webFile);
        
        String path= webFile.getPath();
        String location= WebConstants.LOCAL_DIR + WebConstants.ROOT_FOLDER + path;
        FileService.createFolder(location + webFile.getName());
        
        return webFile;
    }

    @Override
    @Transactional(value = TRANSACTION_MANAGER, propagation = Propagation.REQUIRED)
    public WebFile createEmptyFile(WebFile parentFile, String fileName) {
        String extension= FilenameUtils.getExtension(fileName);
        WebFile webFile= new WebFile();
        webFile.setName(fileName);
        webFile.setCreationDate(new Date());
        webFile.setType(extension);
        webFile.setIcon(Util.getSimpleContentType(extension));
        webFile.setModificationDate(new Date());
        webFile.setSize(1);
        webFile.setWebFile(parentFile);
        super.create(webFile);
        
        String path= webFile.getPath();
        String location= WebConstants.LOCAL_DIR + WebConstants.ROOT_FOLDER + path;
        FileService.createFile(location + webFile.getName());
        
        return webFile;
    }
    
    
}
