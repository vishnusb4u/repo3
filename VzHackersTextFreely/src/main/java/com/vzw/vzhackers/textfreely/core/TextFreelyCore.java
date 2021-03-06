package com.vzw.vzhackers.textfreely.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Ignore;

import com.vzw.vzhackers.textfreely.db.BillingInfo;
import com.vzw.vzhackers.textfreely.db.KeyOpGraphModular;
import com.vzw.vzhackers.textfreely.db.KeyWordMapModular;
import com.vzw.vzhackers.textfreely.db.OfferDetailsInfo;
import com.vzw.vzhackers.textfreely.db.OperationModular;
import com.vzw.vzhackers.textfreely.db.PlanDetailsInfo;
import com.vzw.vzhackers.textfreely.dto.ProcessTextResponse;

public class TextFreelyCore {

	public static Map<String, String> keyWordMap;
	public static KeyOpGraph root;

	static {
		keyWordMap = KeyWordMapModular.getKeyWordMap();
		root = KeyOpGraphModular.getKeyOpGraph();
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

		if(extractedKeys!=null){
		// Finding operation for given set of key
		operation = findOperation(extractedKeys, root,0);
		System.out.println("Operation ->"+operation);
			// Getting operation response template
			template = OperationModular.getTemplate(operation);

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

	private String operate(String operation, List<String> inputData, String mtn, String template) {
		String response = null;
		if(operation.equalsIgnoreCase("BAL")){
			response = BillingInfo.runQuery(mtn, template);
		} else if(operation.equalsIgnoreCase("PROMO")) {
			response = OfferDetailsInfo.runQuery(mtn, template);
		} else if(operation.equalsIgnoreCase("PLANDET")) {
			response = PlanDetailsInfo.runQuery(mtn, template);
		} else {
			response = operation;
		}
		return response;
	}

	public List<String> extractKeyWord(String inputText) {

		List<String> keyWords = new ArrayList<String>();
		Iterator<Entry<String, String>> itr = null;
		inputText = removePunctuation(inputText.toUpperCase());
		String[] wordArr = inputText.split(" ");
		ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(wordArr));
		//System.out.println(wordList);
		itr = keyWordMap.entrySet().iterator();

		while (itr.hasNext()) {
			Entry<String, String> element = itr.next();
//			System.out.println("--"+element.getKey().toUpperCase());
			if (wordList.contains(element.getKey().toUpperCase())) {
				keyWords.add(element.getValue());
//				System.out.println("*"+element.getValue());
			}
		}

		if (keyWords.isEmpty()) {
			keyWords = extractApxMatchingKeyWords(inputText);
		} else {
			if(keyWords.contains("PHONE")||keyWords.contains("ADDRESS")){
				keyWords.add("MYINFO");
			}
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

	public String findOperation(List<String> keyWordList, KeyOpGraph root, int i) {
		List<KeyOpGraph> childNode = null;
		String operation = null;
		List<String> keyWordsLeft = new ArrayList<String>();
		keyWordsLeft.addAll(keyWordList);

		childNode = null;
		childNode = root.getChildGraph();
		System.out.println(root.getKeyId());
		

		if (childNode != null && (!childNode.isEmpty())) {
			//System.out.println(root.getKeyId() + "-" + root.getChildGraph().size());
			for (KeyOpGraph child : childNode) {
				if (keyWordList.contains(child.getKeyId())) {
					keyWordsLeft.remove(child.getKeyId());
					//System.out.println("calling on -" + child.getKeyId());
					for(int x = 0;x<i;x++)
						System.out.print("\t");
					System.out.print("|____");
					operation = findOperation(keyWordsLeft, child,i+1);
				}
			}
			if (operation == null) {
				operation = root.getOperation();
			}
		} else {
			//System.out.println(root.getKeyId() + "-0");
			operation = root.getOperation();
		}
		return operation;
	}

}
