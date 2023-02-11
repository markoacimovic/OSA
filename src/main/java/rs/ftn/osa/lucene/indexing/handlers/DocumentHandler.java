package rs.ftn.osa.lucene.indexing.handlers;

import rs.ftn.osa.model.entity.Artikal;

import java.io.File;

public abstract class DocumentHandler {
	/**
	 * Od prosledjene datoteke se konstruise Lucene Document
	 * 
	 * @param file
	 *            datoteka u kojoj se nalaze informacije
	 * @return Lucene Document
	 */
	public abstract Artikal getIndexUnit(File file);
	public abstract String getText(File file);

}
