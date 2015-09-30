package com.vzw.vzhackers.textfreely.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.vzw.vzhackers.textfreely.dto.ProcessTextResponse;

public class TextFreelyCore {

	public static Map<String, String> keyWordMap;
	public static KeyOpGraph root;

	static {
		keyWordMap = cacheKeyWordMap();
		root = generateKeyOpGraph();
	}

	public ProcessTextResponse process(String inputText, String mtn) {

		ProcessTextResponse response = new ProcessTextResponse();

		String operation = null;
		String template = null;
		String responseMsg = null;
		List<String> extractedData = null;
		List<String> extractedKeys = null;
		String statusCode = "SUCCESS";

		// Extracting valid keywords from input text
		extractedKeys = extractKeyWord(inputText);

		// Extracting customer input if any
		extractedData = extractDataIfAny(inputText);

		// Finding operation for given set of key
		operation = findOperation(extractedKeys, root);
		if (operation != null) {
			// Getting operation response template
			template = getTemplate(operation);

			responseMsg = operate(operation, extractedData, mtn, template);

		} else {
			statusCode = "FAILURE";
			responseMsg = "Sorry unable to process your request";
		}

		response.setOperation(operation);
		response.setResponseMessage(responseMsg);
		response.setStatusCode(statusCode);

		return response;

	}

	public static KeyOpGraph generateKeyOpGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Map<String, String> cacheKeyWordMap() {

		Map<String, String> map = new HashMap<String, String>();
		map.put("Balance", "BAL");
		map.put("detail", "INFO");
		map.put("information", "INFO");
		map.put("info", "INFO");
		map.put("offer", "PROMO");
		map.put("promotion", "PROMO");
		map.put("promo", "PROMO");
		map.put("promocode", "PROMO");
		map.put("data", "DATA");
		map.put("pay", "PAY");
		map.put("bill", "BILL");
		map.put("invoice", "BILL");
		map.put("billing", "BILL");
		return map;
	}

	private String operate(String operation, List<String> inputData, String template, String template2) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> extractKeyWord(String inputText) {

		List<String> keyWords = new ArrayList<String>();
		Iterator<Entry<String, String>> itr = null;
		inputText = removePunctuation(inputText.toUpperCase());
		String[] wordArr = inputText.split(" ");
		ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(wordArr));

		itr = keyWordMap.entrySet().iterator();

		while (itr.hasNext()) {
			Entry<String, String> element = itr.next();
			if (wordList.contains(element.getKey().toUpperCase())) {
				keyWords.add(element.getValue());
			}
		}

		if (keyWords.isEmpty()) {
			keyWords = extractApxMatchingKeyWords(inputText);
		}
		return keyWords;
	}

	private String removePunctuation(String inputText) {
		// TODO Auto-generated method stub
		return inputText;
	}

	private List<String> extractApxMatchingKeyWords(String inputText) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<String> extractDataIfAny(String inputText) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getTemplate(String operation) {
		// TODO Auto-generated method stub
		return null;
	}

	public String findOperation(List<String> keyWordList, KeyOpGraph root) {
		List<KeyOpGraph> childNode = null;
		String operation = null;
		List<String> keyWordsLeft = new ArrayList<String>();
		keyWordsLeft.addAll(keyWordList);

		childNode = null;
		childNode = root.getChildGraph();

		if (childNode != null && (!childNode.isEmpty())) {
			System.out.println(root.getKeyId() + "-" + root.getChildGraph().size());
			for (KeyOpGraph child : childNode) {
				if (keyWordList.contains(child.getKeyId())) {
					keyWordsLeft.remove(child.getKeyId());
					System.out.println("calling on -" + child.getKeyId());
					operation = findOperation(keyWordsLeft, child);
					break;
				}
			}
			if (operation == null) {
				operation = root.getOperation();
			}
		} else {
			System.out.println(root.getKeyId() + "-0");
			operation = root.getOperation();
		}
		return operation;
	}

}
