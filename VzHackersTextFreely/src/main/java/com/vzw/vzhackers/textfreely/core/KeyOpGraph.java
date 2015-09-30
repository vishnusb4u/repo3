package com.vzw.vzhackers.textfreely.core;

import java.util.List;

public class KeyOpGraph {

	private String keyId;
	private String parentKey;
	private String operation;
	
	private List<KeyOpGraph> childGraph;

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public List<KeyOpGraph> getChildGraph() {
		return childGraph;
	}

	public void setChildGraph(List<KeyOpGraph> childGraph) {
		this.childGraph = childGraph;
	}
	
}
