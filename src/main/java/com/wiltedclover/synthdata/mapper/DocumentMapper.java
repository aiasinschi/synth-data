package com.wiltedclover.synthdata.mapper;

import java.util.List;

import com.wiltedclover.synthdata.model.Document;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentMapper {

	List<Document> getAllDocuments();

	/*@Insert("INSERT INTO document" +
			"(id, name, \"shortName\", content, access, code, pages) VALUES " +
			"(nextval('seq_document'), #{name}, #{shortName}, #{content}, #{access}, #{code}, #{pages})")*/
	void addDocument(Document doc);

	void addAllDocuments(List<Document> docs);

	void deleteDocument(Long docId);
}
