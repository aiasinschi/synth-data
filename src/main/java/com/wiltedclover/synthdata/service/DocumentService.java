package com.wiltedclover.synthdata.service;

import java.util.List;

import com.wiltedclover.synthdata.mapper.DocumentMapper;
import com.wiltedclover.synthdata.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

	@Autowired
	private DocumentMapper documentMapper;

	public List<Document> getAllDocuments() {
		return documentMapper.getAllDocuments();
	}

	public void addNewDocument(Document doc) {
		documentMapper.addDocument(doc);
	}

	public void addAllDocuments(List<Document> docs) {
		documentMapper.addAllDocuments(docs);
	}

	public void deleteDocument(Long docId) {
		documentMapper.deleteDocument(docId);
	}

	public DocumentMapper getDocumentMapper() {
		return documentMapper;
	}

	public void setDocumentMapper(DocumentMapper documentMapper) {
		this.documentMapper = documentMapper;
	}
}
