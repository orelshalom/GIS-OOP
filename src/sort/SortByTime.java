package sort;

import java.util.ArrayList;
import java.util.Comparator;

import objects.SampleScan;


public class SortByTime implements Comparator<SampleScan> { 
	
	    public int compare(SampleScan a, SampleScan b) { 
	        return a.getTime().compareTo(b.getTime()); 
	    } 
	}

