package com.unicommerce.utilities.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.unicommerce.utilities.model.tracelog.CreateTraceLog;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraceLogController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${trace.log.redirect.url}")
    private String traceLogRedirectUrl;

    @Value("${trace.log.base.path}")
    private String traceLogBasePath;

    @PostMapping("/services/logs/{tenantCode}/create")
    public String create(@PathVariable("tenantCode") String tenantCode,@RequestParam("logRoutingKey") String logRoutingKey) throws IOException{

        CreateTraceLog createTraceLog = new CreateTraceLog();
        File traceLodBaseDirectory = new File(traceLogBasePath);
        if(!traceLodBaseDirectory.exists()){
            traceLodBaseDirectory.mkdirs();
            traceLodBaseDirectory.setExecutable(true);
            traceLodBaseDirectory.setReadable(true);
            traceLodBaseDirectory.setWritable(true);
        }
        createTraceLog.setLogRedirectUrl(traceLogRedirectUrl.replace("{serverPort}",serverPort).replace("{logRoutingKey}",logRoutingKey));
        createTraceLog.setSuccessful(true);

        File file = new File(traceLodBaseDirectory + File.separator + logRoutingKey+".log");
        if(!file.exists()){
            file.createNewFile();
            file.setExecutable(true);
            file.setReadable(true);
            file.setWritable(true);
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter((file.getAbsoluteFile())))){
            bw.write(createTraceLog.toString());
            bw.write("\n");
        }catch (IOException io){
            createTraceLog.setErrors("Error while creating file : " + io.getMessage() );
            createTraceLog.setSuccessful(false);
        }

        return new Gson().toJson(createTraceLog).toString();
    }
    @GetMapping("/files/trace-logs/{traceLogFile}")
    public String traceLogFile(@PathVariable("traceLogFile") String traceLogFile) throws IOException{

        File file = new File(traceLogBasePath+File.separator+traceLogFile);
        String data = FileUtils.readFileToString(file, "UTF-8");

        return data;
    }


}
