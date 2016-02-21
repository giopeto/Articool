package com.articool.files;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.articool.items.domain.Item;
import com.articool.items.service.ItemServiceImpl;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.googlecode.objectify.Ref;


public class Upload extends HttpServlet {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    private ItemServiceImpl itemsService = new ItemServiceImpl();
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("myFile");
        
        String fileId = blobKeys.get(0).getKeyString();
        
        long itemId =  Long.valueOf(req.getParameter("itemId")).longValue();
        System.out.println("Item id 2: " + itemId);
        Item item = itemsService.getOneItemById(itemId);
        
        Map<String, String>  fileids = item.getFileIds();
        Long fileNumber = (long) fileids.size();
        fileNumber++;
        fileids.put(fileId, fileNumber.toString());
        
       ofy().save().entity(item).now();
        
        
        if (blobKeys == null || blobKeys.isEmpty()) {
            res.sendRedirect("/");
        } else {
            res.sendRedirect("/serve?blob-key=" + blobKeys.get(0).getKeyString());
            //res.sendRedirect("/items_add_edit/");
        }
    }
}