package sort;

import java.util.Comparator;

import objects.SampleScanCombo;


public class SortByTime implements Comparator<SampleScanCombo> { 
	
	    public int compare(SampleScanCombo a, SampleScanCombo b) { 
	        return a.getTime().compareTo(b.getTime()); 
	    } 
	}

