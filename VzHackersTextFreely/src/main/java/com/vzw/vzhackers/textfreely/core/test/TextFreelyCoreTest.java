package com.vzw.vzhackers.textfreely.core.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.vzw.vzhackers.textfreely.core.KeyOpGraph;
import com.vzw.vzhackers.textfreely.core.TextFreelyCore;

@RunWith(MockitoJUnitRunner.class)  

public class TextFreelyCoreTest   {
	
	TextFreelyCore core;
	@Test
	public void testExtractKeyWordFound() {
		core = new TextFreelyCore();
		List<String> key = core.extractKeyWord("balance info needed");
		Assert.assertNotNull(key);
	}
	
	@Test
	public void testExtractKeyWordNotFound() {
		core = new TextFreelyCore();
		List<String> key = core.extractKeyWord("I want to complaint");
		Assert.assertNull(key);
	}
	
	@Test
	public void testFindOpt() {
		core = new TextFreelyCore();
		List<String> keyWordList = new ArrayList<String>();
		keyWordList.add("PAY");
		keyWordList.add("PROMO");
		
		KeyOpGraph root = new KeyOpGraph();
		root.setKeyId("root");
		List<KeyOpGraph> childGraph = new ArrayList<KeyOpGraph>();
		root.setChildGraph(childGraph);
		KeyOpGraph ch1 = new KeyOpGraph();
		ch1.setKeyId("BAL");
		ch1.setParentKey("root");
		ch1.setOperation("BAL");
		KeyOpGraph ch11 = new KeyOpGraph();
		ch11.setKeyId("DATA");
		ch11.setParentKey("BAL");
		ch11.setOperation("DAT_BAL");
		KeyOpGraph ch12 = new KeyOpGraph();
		ch12.setKeyId("AMT");
		ch12.setParentKey("BAL");
		ch12.setOperation("AMT_BAL");
		List<KeyOpGraph> childGraph1 = new ArrayList<KeyOpGraph>();
		childGraph1.add(ch12);
		childGraph1.add(ch11);
		ch1.setChildGraph(childGraph1);
		KeyOpGraph ch2 = new KeyOpGraph();
		ch2.setKeyId("PAY");
		ch2.setParentKey("root");
		ch2.setOperation("PYMT");
		KeyOpGraph ch3 = new KeyOpGraph();
		ch3.setKeyId("PROMO");
		ch3.setParentKey("root");
		ch3.setOperation("GET_PROMO");
		childGraph.add(ch1);
		childGraph.add(ch2);
		childGraph.add(ch3);
		
		String key = core.findOperation(keyWordList, root);
		System.out.println(key);
		Assert.assertNotNull(key);
	}

	private KeyOpGraph populateKeyOp() {
		// TODO Auto-generated method stub
		return null;
	}

	private Map<String, String> populateKeyMap() {
		
		Map<String, String> map = new HashMap<String,String>();
		map.put( "Balance","BAL");
		map.put("detail","INFO");
		map.put("information","INFO");
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
		
		
		
		return null;
	}

}
