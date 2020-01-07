package sort;

import java.util.Comparator;

import objects.Wifi;


public class SortBySignal implements Comparator<Wifi> { 
	
	@Override
    public int compare(Wifi a, Wifi b) { 
        return Math.abs(a.getSignal()) - Math.abs(b.getSignal()); 
    }

}

