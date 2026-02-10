package com.testProject.service;

import com.testProject.dto.ResponseDTO;
import com.testProject.entity.CompanyMasters;
import com.testProject.entity.EmployeeMaster;
import com.testProject.repository.CompanyMastersRepository;
import com.testProject.repository.EmployeeMasterRepository;
import com.testProject.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileUploadService {

    private static final Logger log= LogManager.getLogger(FileUploadService.class);

    @Value("${file.upload.dir}")
    private String fileUploadDir;

    @Value("${file.download.dir}")
    private String downloadDir;

    @Autowired
    private EmployeeMasterRepository employeeMasterRepository;

    @Autowired
    private CompanyMastersRepository companyMastersRepository;

    public ResponseDTO uploadFile(MultipartFile file,String emailId)
    {
        log.info("==> uploadFile Method");
        ResponseDTO responseDTO = new ResponseDTO();
        Map<String,Object> respMessages=new HashMap<>();
        try {

            if(emailId==null)
            {
                log.warn(Constants.Messages.emptyEmailId);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.emptyEmailId);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            if(file==null)
            {
                log.warn(Constants.Messages.NULL_OR_EMPTY_FILE);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.NULL_OR_EMPTY_FILE);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            EmployeeMaster employeeMaster=employeeMasterRepository.findByEmailId(emailId);

            if(employeeMaster==null)
            {
                log.warn(Constants.Messages.EMPLOYEE_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.EMPLOYEE_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            CompanyMasters companyMasters=companyMastersRepository.findById((long)employeeMaster.getCompanyId());

            if(companyMasters==null)
            {
                log.warn(Constants.Messages.COMPANY_NOT_FOUND);
                responseDTO.setMessage(Constants.Messages.badRequest);
                responseDTO.setStatusCode(Constants.StatusCode.badRequest);
                respMessages.put("msg",Constants.Messages.COMPANY_NOT_FOUND);
                responseDTO.setResponseObj(respMessages);
                return responseDTO;
            }

            String directory=fileUploadDir+"/"+companyMasters.getCompanyName()+"_"+companyMasters.getId()+"/"+ LocalDate.now();

            log.info("dir: {}",directory);

            File filePath=new File(directory);

            if(!filePath.exists())
            {
                filePath.mkdirs();
            }

            String fileName=System.currentTimeMillis()+"_"+file.getOriginalFilename();

            log.info("fileName: {}",fileName);
            Path directoryPath= Paths.get(filePath+File.separator+fileName);

            Files.write(directoryPath,file.getBytes());

            String downloadPath=downloadDir+"/"+companyMasters.getCompanyName()+"_"+companyMasters.getId()+"/"+ LocalDate.now()+"/"+fileName;

            log.info("download Path: {}",downloadPath);

            responseDTO.setMessage(Constants.Messages.success);
            responseDTO.setStatusCode(Constants.StatusCode.success);
            respMessages.put("data",downloadPath);
            responseDTO.setResponseObj(respMessages);


        }
        catch (Exception e) {
            log.error(Constants.Messages.error);
            e.printStackTrace();
            responseDTO.setMessage(Constants.Messages.error);
            responseDTO.setStatusCode(Constants.StatusCode.error);
        }
        return responseDTO;
    }
}
