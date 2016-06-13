package com.tw.restsample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

//embedded tomcat run : clean install tomcat7:run


//localhost:9090/rest/book
@Path("/book/1.0")
public class RestfulTest {
public List<String> bookList =  new ArrayList<String>(Arrays.asList("HelloJava","HelloBootStrap","HelloGit"));


//http://localhost:9090/rest/book/1.0
@POST
@Consumes("text/plain")
public void addBook(String bookname){
	bookList.add(bookname);
	System.out.println(this.bookList);
}

//http://localhost:9090/rest/book/1.0
@GET
@Produces("text/plain")
public String getAllbooks(){
	System.out.println(this.bookList);
	return this.bookList.toString();
}

//http://localhost:9090/reset/book/1.0/HelloJava
@GET
@Path("/{book}")
public String getBookByName(@PathParam("book") String bookname){
	String result="";
	if(bookList.contains(bookname)){
		result = bookname;
	}
	else{
		result = "no search result";
	}
	System.out.println(result);
	return result;
}
//http://localhost:9090/rest/book/1.0/HelloOpenstack?targetBook=HelloJava
@PUT
@Path("/{book}")
public void updateBook(@PathParam("book") String book,
		@QueryParam("targetBook") String targetBook){
	System.out.println("before: "+bookList.toString());
	int bookIdx = bookList.indexOf(targetBook);
	bookList.remove(bookIdx);
	bookList.add(bookIdx, book);
	System.out.println("after: "+bookList.toString());
}

//http://localhost:9090/rest/book/1.0/HelloJava
@DELETE
@Path("/{book}")
public void deleBook(@PathParam("book") String book){
	System.out.println("before: "+bookList.toString());
	bookList.remove(book);
	System.out.println("after: "+bookList.toString());
}

}
