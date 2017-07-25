package pictureFilter.filters;

import pictureFilter.filters.filters3x3.BlurFilter3x3;
import pictureFilter.filters.filters3x3.EmphEdges;
import pictureFilter.filters.filters3x3.MotionBlurFilterDiag3x3;
import pictureFilter.filters.filters3x3.SharpenFilter;
import pictureFilter.filters.filtersNxN.BlurFilter5X5;
import pictureFilter.filters.filtersNxN.FindEdges;
import pictureFilter.filters.mirrorFilters.*;
import pictureFilter.filters.pixelFilters.Grayscale_Filter;

import java.util.Collection;
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
        Filter blurFilter3x3 = new BlurFilter3x3();
        Filter blurFilter5x5 = new BlurFilter5X5();
        Filter motionBlurFilterDiag3x3 = new MotionBlurFilterDiag3x3();
        //Filter motionBlurFilterDiag5x5 = new MotionBlurFilterDiag5x5();

        Filter mhFilter = new MirrorHor();
        Filter mvFilter = new MirrorVert();

        Filter mvlFilter = new MirrorVertLeft();
        Filter mvrFilter = new MirrorVertRight();
        Filter mhbFilter = new MirrorHorBottom();
        Filter mhtFilter = new MirrorHorTop();

        Filter findEdges = new FindEdges();
        Filter sharpenFilter = new SharpenFilter();
        Filter emphEdges = new EmphEdges();

        this.filterHashMap.put(bwFilter.getFilterName().toLowerCase(), bwFilter);
        this.filterHashMap.put(blurFilter3x3.getFilterName().toLowerCase(),  blurFilter3x3);
        this.filterHashMap.put(blurFilter5x5.getFilterName().toLowerCase(), blurFilter5x5);
        this.filterHashMap.put(motionBlurFilterDiag3x3.getFilterName().toLowerCase(), motionBlurFilterDiag3x3);

        this.filterHashMap.put(mhFilter.getFilterName().toLowerCase(), mhFilter);
        this.filterHashMap.put(mvFilter.getFilterName().toLowerCase(), mvFilter);

        this.filterHashMap.put(mvlFilter.getFilterName().toLowerCase(), mvlFilter);
        this.filterHashMap.put(mvrFilter.getFilterName().toLowerCase(), mvrFilter);
        this.filterHashMap.put(mhbFilter.getFilterName().toLowerCase(), mhbFilter);
        this.filterHashMap.put(mhtFilter.getFilterName().toLowerCase(), mhtFilter);

        this.filterHashMap.put(findEdges.getFilterName().toLowerCase(), findEdges);
        this.filterHashMap.put(sharpenFilter.getFilterName().toLowerCase(), sharpenFilter);
        this.filterHashMap.put(emphEdges.getFilterName().toLowerCase(), emphEdges);
    }

    public Collection<Filter> getFilters(){ return this.filterHashMap.values(); }
}
