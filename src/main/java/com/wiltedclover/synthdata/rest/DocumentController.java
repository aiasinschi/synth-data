package com.wiltedclover.synthdata.rest;

import java.util.*;

import com.wiltedclover.synthdata.model.Document;
import com.wiltedclover.synthdata.service.DocumentService;
import com.wiltedclover.synthdata.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	@GetMapping("/documents")
	public List<Document> getAllDocuments() {
		return documentService.getAllDocuments();
	}

	@PostMapping("/document/add")
	public Document addDocument(@RequestBody Document document) {
		documentService.addNewDocument(document);
		return document;
	}

	@PostMapping("/document/delete")
	public void deleteDocument(@RequestBody Long docId) {
		documentService.deleteDocument(docId);
	}

	@GetMapping("/document/generate")
	public int generateDocuments(@RequestParam("count") int count) {
		List<Document> docs = new ArrayList<>();
		long timestamp = new Date().getTime();
		for (int i = 0; i < count; i++) {
			Document d = new Document();
			d.setName("Generated_" + i + "_" + timestamp);
			d.setShortName("Generated_" + i);
			d.setAccess("A");
			d.setCode("CODE" + (timestamp % 365 * 24 * 60 * 60));
			d.setContent(AppUtils.getRandomLoremIpsum());
			d.setPages(Math.round(Math.random() * 10));
			docs.add(d);
		}
		try {
			long t0 = new Date().getTime();
			documentService.addAllDocuments(docs);
			System.out.println("Spent " + (new Date().getTime() - t0) + " ms adding " + count + " records");
			return docs.size();
		} catch (Exception ex) {
			System.err.println(ex.toString());
			ex.printStackTrace();
			return -1;
		}
	}

	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}
}
