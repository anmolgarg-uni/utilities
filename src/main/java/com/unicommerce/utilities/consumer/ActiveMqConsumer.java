package com.unicommerce.utilities.consumer;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;
import com.unicommerce.utilities.model.tracelog.TraceLog;
import com.unicommerce.utilities.model.tracelog.TraceLogQueueMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ActiveMqConsumer {

    @Value("${trace.log.base.path}")
    private String traceLogBasePath;

    @JmsListener( destination = "${activemq.topic-logging-queue}")
    public void traceLogQueue(String message) {
        try {
            Gson gson = new Gson();
            TraceLog traceLog = gson.fromJson(message,TraceLog.class);
            TraceLogQueueMessage traceLogQueueMessage = gson.fromJson(traceLog.getObjectJson(), TraceLogQueueMessage.class);
            File file = new File(traceLogBasePath + File.separator + traceLog.getLogRoutingKey()+".log");
            createFileIfNotExist(file);

            FileUtils.write(file,"<br>"+ traceLogQueueMessage.toString()  , "UTF-8",true);
        }catch(Exception e){
            System.out.println("Exception");
        }
    }

    private void createFileIfNotExist(File file) throws IOException {
        if(!file.exists()){
            file.createNewFile();
            file.setExecutable(true);
            file.setReadable(true);
            file.setWritable(true);
        }
    }

}