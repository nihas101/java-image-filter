package pictureFilter.filters;

import java.util.HashMap;

public class FilterFactory {

    private HashMap<String, Filter> filterHashMap;

    public FilterFactory(HashMap<String, Filter> filterHashMap){
        this.filterHashMap = filterHashMap;
        addAllFilters();
    }

    public Filter getFilter(String filterString){
        Filter filter = filterHashMap.getOrDefault(filterString.toLowerCase(), null);
        if(filter == null) throw new FilterNotFoundError(filterString);

        return filter;
    }

    private void addAllFilters(){
        Filter bwFilter = new Grayscale_Filter();
        Filter testFilter = new TestFilter();

        this.filterHashMap.put(bwFilter.getFilterName().toLowerCase(), bwFilter);
        this.filterHashMap.put(testFilter.getFilterName().toLowerCase(), testFilter);
    }
}
