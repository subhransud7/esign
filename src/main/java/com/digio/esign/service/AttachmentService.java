package com.digio.esign.service;

import com.digio.esign.entity.Document;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AttachmentService {

    private final RestTemplate restTemplate;

    @Autowired
    public AttachmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


//    public Boolean validateAttachment(MultipartFile file) {
//}

    public Document setFileData(MultipartFile file, Document document) throws IOException {
        document.setFile_data(Base64.encodeBase64String(file.getBytes()));
        return document;
    }


    private HttpHeaders getHeaders(){
        String credential = "AIZ67DUSNZ8TGWJV4DZ7DI3T5Z2LN2W2:ASN9AVKHU6HF41KTU71G3KNXLG1ET7BC";
        String encodedCredential = new String(Base64.encodeBase64(credential.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization","Basic" + encodedCredential);
        return headers;
    }

    private MultiValueMap<String, String> updateBody(MultiValueMap<String, String> body,Document document){
        body.add("signers",document.getSigners().toString());
        body.add("file_data",document.getFile_data());
        if(document.getNotify_signers()!=null) body.add("notify_signers",document.getNotify_signers().toString());
        if(document.getFile_name()!=null) body.add("file_name",document.getFile_name());
        if(document.getSend_sign_link()!=null) body.add("send_sign_link",document.getSend_sign_link().toString());
        if(document.getDisplay_on_page()!=null) body.add("display_on_page",document.getDisplay_on_page());
        body.add("expire_in_days",Integer.toString(document.getExpire_in_days()));
        if(document.getCallback()!=null) body.add("callback",document.getCallback());
        if(document.getComment()!=null) body.add("comment",document.getComment());
        if(document.getSign_coordinates()!=null) body.add("sign_coordinates",document.getSign_coordinates().toString());
        if(document.getSequential()!=null) body.add("sequential",document.getSequential().toString());
        if(document.getWill_self_sign()!=null) body.add("will_self_sign",document.getWill_self_sign().toString());
        if(document.getSignatory()!=null) body.add("signatory",document.getSignatory());

        return body;
    }
    public Resource sendDetails(Document document){
        HttpHeaders headers =getHeaders();
        String url ="https://ext.digio.in:444/v2/client/document/uploadpdf";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<Document> request = new HttpEntity<Document>(document, headers);
        Resource resource = restTemplate.postForObject(url, request, Resource.class);
        return resource;
    }
    public ResponseEntity<Resource> getDetails(String fileId){
        HttpHeaders headers =getHeaders();
        String url ="https://ext.digio.in:444/v2/client/document/"+ fileId;
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Resource> resourceResponseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Resource.class);
        return resourceResponseEntity;
    }

    public ResponseEntity<Resource> downlaodFile(String fileId) {
        HttpHeaders headers =getHeaders();
        String url ="https://ext.digio.in:444/v2/client/document/download?document_id="+ fileId;
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Resource> resourceResponseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Resource.class);
        return resourceResponseEntity;
    }
}
