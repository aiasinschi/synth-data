package com.wiltedclover.synthdata.rest;

import java.util.*;

import com.wiltedclover.synthdata.model.AccessType;
import com.wiltedclover.synthdata.model.Document;
import com.wiltedclover.synthdata.service.DocumentService;
import com.wiltedclover.synthdata.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@RestController
@RequestMapping("/rest")
public class DocumentController {

	private static Log LOG = LogFactory.getLog(DocumentController.class);

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
		for (int i = 0; i < count; i++) {
			docs.add(buildMockDocument());
		}
		try {
			long t0 = new Date().getTime();
			documentService.addAllDocuments(docs);
			LOG.debug("Spent " + (new Date().getTime() - t0) + " ms adding " + count + " records");
			return docs.size();
		} catch (Exception ex) {
			LOG.error(ex.toString());
			ex.printStackTrace();
			return -1;
		}
	}

	private Document buildMockDocument() {
		Document d = new Document();

		String title = AppUtils.getRandomTitle();
		d.setName(title);
		d.setShortName(getShortName(title));
		d.setAccess(AppUtils.random(2) % 2 == 0 ? AccessType.PRIVATE.getCode() : AccessType.PUBLIC.getCode());
		d.setCode(getCode(title));
		d.setContent(AppUtils.getRandomLoremIpsum());
		d.setPages(Math.round(Math.random() * 100) + 1);
		return d;
	}

	private String getShortName(String title) {
		String[] tokens = title.split(" ");
		return tokens.length == 1 ? title : String.format("%s %s", tokens[0], tokens[1]);
	}

	private String getCode(String title) {
		long timestamp = new Date().getTime();
		String[] tokens = title.split(" ");
		String first = tokens[0].substring(0, Math.min(tokens.length > 1 ? 3 : 6, tokens[0].length()));
		String second = tokens.length > 1 ? tokens[1].substring(0, Math.min(3, tokens[1].length())) : "";
		return String.format("%s%s%d", first.toUpperCase(),second.toUpperCase(), timestamp % 365 * 24 * 60 * 60);
	}

	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}
}
