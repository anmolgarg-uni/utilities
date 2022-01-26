package com.unicommerce.utilities.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CreateManifest {

    @PostMapping("/downloadManifestFromAjio")
    public ResponseEntity<Resource> uploadFile(@RequestParam("file") MultipartFile file ,
            @RequestHeader("authKey") String authKey,
            @RequestHeader("apiKey") String apiKey) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));

        List<CreateManifestParam> createManifestParams = new ArrayList<>();
        br.lines().skip(1).forEach( line -> {
                    String[] param = line.split(",");
                    CreateManifestParam createManifestParam = new CreateManifestParam();
                    createManifestParam.manifestCode = param[0];
                    createManifestParam.masterAWB = param[1];
                    createManifestParam.orderId = param[2];
                    createManifestParam.invoiceNo = param[3];
                    createManifestParams.add(createManifestParam);
        });

        HttpHeaders headers = new HttpHeaders();
        headers.set("apiKey",apiKey);

        Map<String, List<CreateManifestParam>>  manifestCodeToOrders = createManifestParams.stream().collect(Collectors.groupingBy(CreateManifestParam::getManifestCode));
        String url = "https://api-seller.services.ajio.com/unicom/orders/dispatch";
        manifestCodeToOrders.entrySet()
                .stream()
                .forEach(entry -> {

                    List<DispatchItem> dispatchItems = entry.getValue().stream().map(createManifestParam -> {
                        DispatchItem dispatchItem = new DispatchItem();
                        dispatchItem.masterAWB = createManifestParam.getMasterAWB();
                        dispatchItem.invoiceNo = createManifestParam.getInvoiceNo();
                        dispatchItem.orderId = createManifestParam.getOrderId();
                        return dispatchItem;
                    }).collect(Collectors.toList());


                    AjioRequest ajioRequest = new AjioRequest();
                    ajioRequest.setAuth(authKey);
                    ajioRequest.setDispatchItems(dispatchItems);

                    HttpEntity<AjioRequest> request = new HttpEntity<>(ajioRequest,headers);
                    int retryCount = 3;
                    String status = null ;
                    AjioResponse ajioResponse = null;

                    while(retryCount-- > 0){

                        RestTemplate restTemplate = new RestTemplate();
                        ResponseEntity<AjioResponse> response = restTemplate.exchange(url, HttpMethod.POST,request,AjioResponse.class);
                        ajioResponse = response.getBody();

                        if("SUCCESS".equals(ajioResponse.getStatus())){
                            status = "SUCESS";
                            break;
                        }
                    }
                    if("SUCCESS".equals(status)){
                        //get manifest fiile
                        HttpEntity<Void> req = new HttpEntity(headers);
                        RestTemplate restTemplate = new RestTemplate();
                        ResponseEntity<InputStream> response = restTemplate.exchange(ajioResponse.manifestUrl,HttpMethod.GET,req,InputStream.class,new Object());

                        InputStream inputStream = response.getBody();
                        BufferedReader readFile = new BufferedReader(new InputStreamReader(inputStream));
                    }

                });


        String downloadedFileDirectory = "downloadedFileDirectory";
        File downlaodedFilesZip = new File(downloadedFileDirectory);



        InputStreamResource resource = new InputStreamResource(new FileInputStream(downlaodedFilesZip));

        HttpHeaders headers1 = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+downlaodedFilesZip.getName());
        return ResponseEntity.ok()
                .headers(headers1)
                .contentLength(downlaodedFilesZip.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


}
class CreateManifestParam {
    String manifestCode;
    String masterAWB;
    String orderId;
    String invoiceNo;

    public String getManifestCode() {
        return manifestCode;
    }

    public void setManifestCode(String manifestCode) {
        this.manifestCode = manifestCode;
    }

    public String getMasterAWB() {
        return masterAWB;
    }

    public void setMasterAWB(String masterAWB) {
        this.masterAWB = masterAWB;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
}

 class DispatchItem{
    public String masterAWB;
    public String orderId;
    public String invoiceNo;
}

 class AjioRequest{
    public String auth;
    public List<DispatchItem> dispatchItems;

     public String getAuth() {
         return auth;
     }

     public void setAuth(String auth) {
         this.auth = auth;
     }

     public List<DispatchItem> getDispatchItems() {
         return dispatchItems;
     }

     public void setDispatchItems(List<DispatchItem> dispatchItems) {
         this.dispatchItems = dispatchItems;
     }
 }
class AjioResponse{
    public String status;
    public String manifestUrl;
    public ArrayList<Object> failedDispatchItems;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getManifestUrl() {
        return manifestUrl;
    }

    public void setManifestUrl(String manifestUrl) {
        this.manifestUrl = manifestUrl;
    }

    public ArrayList<Object> getFailedDispatchItems() {
        return failedDispatchItems;
    }

    public void setFailedDispatchItems(ArrayList<Object> failedDispatchItems) {
        this.failedDispatchItems = failedDispatchItems;
    }
}
