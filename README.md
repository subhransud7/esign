# esign

API implemented over external Digio API.

To start, simply run the command:java -jar esign-0.0.1-SNAPSHOT.jar --server.port=8080 in terminal.

GET API: 
 1. http://localhost:8081/document/download/{fileId} -> to download the pdf
 2. http://localhost:8081/document/{fileId} -> to get the metadata info of the file.
 
POST API:
 1. http://localhost:8081/document  
    - In Body as form-data, 
    For KEY:file  VALUE:Upload a pdf file
    
    For KEY:metada VALUE:metadata in JSON  CONTENT TYPE:application/json
    
    Example: {
              "signers": [{
                  "identifier": "8249085867",
                  "name": "Subhransu Dalai",
                  "reason": "ASsignment"
                }],
                "expire_in_days": 10,
              "file_name": "testing1.pdf"
              }
     
    
 Things need to be improved:
 1. Check whether uploaded document is pdf or not.
 2. Other validations of param
 3. Testing
 
 
