package pictureFilter;

import java.util.HashMap;

class FilterFactory {

    private HashMap<String, Filter> filterHashMap;

    public FilterFactory(HashMap<String, Filter> filterHashMap){
        this.filterHashMap = filterHashMap;
        addAllFilters();
    }

    Filter getFilter(String filter){
        return filterHashMap.getOrDefault(filter.toLowerCase(), null);
    }

    public void addAllFilters(){
        Filter bwFilter = new Grayscale_Filter();
        Filter testFilter = new TestFilter();

        this.filterHashMap.put(bwFilter.getFilterName().toLowerCase(), bwFilter);
        this.filterHashMap.put(testFilter.getFilterName().toLowerCase(), testFilter);
    }
}
