package com.btrapp.jklarfreader.objects;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 
 * @author btrapp
 */

/*
 * Example:
 * 
 * Record WaferRecord "FirstWaferId" {
 * Field DieOrigin 2 {0, 0}
 * Field OrientationInstructions 1 {""}
 * }
 * 
 * Would map to:
 * 
 * name: "WaferRecord"
 * id: "FirstWaferId"
 * fields: {
 * "DieOrigin" : ["0","0"],
 * "OrientationInstructions" : [""]
 * }
 * 
 */
public final class KlarfRecord {
	public KlarfRecord(String name, String id) {
		this.name = name;
		this.id = id;
	}

	private String name; //ex: FileRecord, LotRecord, WaferRecord...
	private String id = ""; //1.8  (Some Records don't have an ID.  Use a blank string for those)
	private LinkedHashMap<String, List<String>> fields = new LinkedHashMap<>();
	private List<KlarfList> lists = new ArrayList<>();
	private List<KlarfRecord> records = new ArrayList<>();

	/**
	 * A case-insensitive search to match a record by name.
	 * 
	 * @param name
	 * @return
	 */
	public Stream<KlarfRecord> findRecordsByName(String name) {
		return records.stream().filter(r -> name.equalsIgnoreCase(r.getName()));
	}

	/**
	 * A <b>case-insensitive</b> search to find a list by name. Multiple records could
	 * match, so return a stream.
	 * 
	 * @param string
	 * @return
	 */
	public Stream<KlarfList> findListByName(String name) {
		return lists.stream().filter(r -> name.equalsIgnoreCase(r.getName()));
	}

	/**
	 * A <b>case-insensitive</b> search to match a record by name and an ID
	 * 
	 * @param name
	 * @return
	 */
	public Optional<KlarfRecord> findRecordByNameAndId(String name, String id) {
		return records.stream()
				.filter(r -> name.equalsIgnoreCase(r.getName()))
				.filter(r -> id.equalsIgnoreCase(r.getId()))
				.findFirst();
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public LinkedHashMap<String, List<String>> getFields() {
		return fields;
	}

	public List<KlarfList> getLists() {
		return lists;
	}

	public List<KlarfRecord> getRecords() {
		return records;
	}

}