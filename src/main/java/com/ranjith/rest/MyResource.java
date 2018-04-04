package com.ranjith.rest;



import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.glassfish.jersey.server.spi.ResponseErrorMapper;
import org.json.JSONObject;

import com.ranjith.objects.Person;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

 
   
	/**
	 * Provides a JSON response. This method can be customized to give the desired result
	 * 
	 */
	
    @GET
    @Path("/ranjith/check")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
    	System.out.println("Changes are reflecting");
    	Map<String, Object> responseMap = new HashMap<String,Object>();
    	responseMap.put("status", "success");	
    	responseMap.put("key", "value");
    	JSONObject json = new JSONObject(responseMap);
    	
    	return Response.ok(json.toString(), MediaType.APPLICATION_JSON).build();
    	
    }
    
    
    /**
     * Example method which provides plain text response
     * @return
     */
    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
    	 System.out.println("hello check");
        return new Date().toString();
       
    }
    
  
    /**
     * Example of using path params and query params
     * @param nation
     * @param team
     * @param firstName
     * @param lastName
     * @return
     */
    @GET
    @Path("/{nation}/{team}/all")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPlayer(@PathParam("nation") String nation,
    		@PathParam("team") String team,
    		@QueryParam("name")String firstName,@QueryParam("lastName")String lastName) {
    	
    	Reader in;
    	System.out.println(team);
    	System.out.println(nation);
    	Person p;
    	String result="";
		try {
			in = new FileReader("/Users/r.vadakkumthanni/Documents/workspace/restmavenexample/src/main/resources/POST/sample.csv");
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
    	
    	for(CSVRecord record : records){
    		String firstNameInfile = String.valueOf(record.get("FirstName"));
    		System.out.println("firstname is " +firstNameInfile);
    		if(firstName.equalsIgnoreCase(firstNameInfile)){
    			boolean isFav = Boolean.valueOf(record.get("Favourite"));
    			p= new Person(String.valueOf(record.get("FirstName")),String.valueOf(record.get("LastName")),String.valueOf(record.get("Position")),String.valueOf(record.get("Team")),isFav);
    			result = p.toString();
    	}
    	
    	}
    	} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
        
    	System.out.println("name in request url is "+ firstName);
    	
    	 return result;
    }
    /**
     * Example of a POST request
     * @return
     */
    @POST
    @Path("add")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addUser(String body) {
    	//Using @Consumes annotation extracts the request body
    	
    	//System.out.println("body is "+body);
    	JSONObject jsonData = new JSONObject(body);
    	System.out.println(jsonData.get("key1"));
    	
    	CSVFormat csvformat = CSVFormat.DEFAULT.withRecordSeparator("\n");
        Person messi = new Person("Lionel", "Messi", "Forward","Argentina",true);
        final Object [] FILE_HEADER = {"FirstName","LastName","Position","Team","Favourite"};
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        CSVParser csvParser;
        CSVPrinter printer = null;
        
        
        try {
        	 fileReader = new FileReader("/Users/r.vadakkumthanni/Documents/workspace/restmavenexample/src/main/resources/POST/sample.csv");
			 fileWriter = new FileWriter("/Users/r.vadakkumthanni/Documents/workspace/restmavenexample/src/main/resources/POST/sample.csv");
			
			 csvParser = new CSVParser(fileReader, csvformat);
			Long numberOfecords = csvParser.getRecordNumber();
			System.out.println(numberOfecords);
			
			printer = new CSVPrinter(fileWriter, csvformat);
			if(numberOfecords == 0 ){
			printer.printRecord(FILE_HEADER);
			}
			printer.printRecord(messi);
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			try {
				fileWriter.flush();
			
			fileWriter.close();
			fileReader.close();
			printer.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			 
			

		}
    	
    	
    	
        return "Yo yo ... created the user";
    
        
        
    }
}
