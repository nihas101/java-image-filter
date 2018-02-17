package de.nihas101.pictureFilter.filters;


import de.nihas101.pictureFilter.filters.filters3x3.*;
import de.nihas101.pictureFilter.filters.filtersNxN.BlurFilter5X5;
import de.nihas101.pictureFilter.filters.filtersNxN.FindEdges;
import de.nihas101.pictureFilter.filters.filtersNxN.MosaicFilter;
import de.nihas101.pictureFilter.filters.filtersNxN.SNESMosaicFilter;
import de.nihas101.pictureFilter.filters.mirrorFilters.*;
import de.nihas101.pictureFilter.filters.pixelFilters.GrayScaleFilter;
import de.nihas101.pictureFilter.filters.pixelFilters.InvertFilter;
import de.nihas101.pictureFilter.filters.utils.FilterNotFoundError;

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
        else return filter;
    }

    private void addAllFilters(){
        Filter bwFilter = new GrayScaleFilter();
        Filter blurFilter3x3 = new BlurFilter3x3();
        Filter blurFilter5x5 = new BlurFilter5X5();
        Filter motionBlurFilterDiagonal3x3 = new MotionBlurFilterDiagonal3x3();

        Filter mhFilter = new MirrorHorizontal();
        Filter mvFilter = new MirrorVertical();

        Filter mvlFilter = new MirrorVerticalLeft();
        Filter mvrFilter = new MirrorVerticalRight();
        Filter mhbFilter = new MirrorHorizontalBottom();
        Filter mhtFilter = new MirrorHorizontalTop();

        Filter findEdges = new FindEdges();
        Filter sharpenFilter = new SharpenFilter();
        Filter emphasiseEdges = new EmphasiseEdges();
        Filter embossFilter = new EmbossFilter();
        Filter bumpMap = new BumpMap();
        Filter invertFilter = new InvertFilter();

        SNESMosaicFilter snesMosaicFilter = new SNESMosaicFilter();
        MosaicFilter mosaicFilter = new MosaicFilter();

        this.filterHashMap.put(bwFilter.getFilterName().toLowerCase(), bwFilter);
        this.filterHashMap.put(blurFilter3x3.getFilterName().toLowerCase(),  blurFilter3x3);
        this.filterHashMap.put(blurFilter5x5.getFilterName().toLowerCase(), blurFilter5x5);
        this.filterHashMap.put(motionBlurFilterDiagonal3x3.getFilterName().toLowerCase(), motionBlurFilterDiagonal3x3);

        this.filterHashMap.put(mhFilter.getFilterName().toLowerCase(), mhFilter);
        this.filterHashMap.put(mvFilter.getFilterName().toLowerCase(), mvFilter);

        this.filterHashMap.put(mvlFilter.getFilterName().toLowerCase(), mvlFilter);
        this.filterHashMap.put(mvrFilter.getFilterName().toLowerCase(), mvrFilter);
        this.filterHashMap.put(mhbFilter.getFilterName().toLowerCase(), mhbFilter);
        this.filterHashMap.put(mhtFilter.getFilterName().toLowerCase(), mhtFilter);

        this.filterHashMap.put(findEdges.getFilterName().toLowerCase(), findEdges);
        this.filterHashMap.put(sharpenFilter.getFilterName().toLowerCase(), sharpenFilter);
        this.filterHashMap.put(emphasiseEdges.getFilterName().toLowerCase(), emphasiseEdges);
        this.filterHashMap.put(embossFilter.getFilterName().toLowerCase(), embossFilter);
        this.filterHashMap.put(bumpMap.getFilterName().toLowerCase(), bumpMap);
        this.filterHashMap.put(invertFilter.getFilterName().toLowerCase(), invertFilter);

        this.filterHashMap.put(snesMosaicFilter.getFilterName().toLowerCase(), snesMosaicFilter);
        this.filterHashMap.put(mosaicFilter.getFilterName().toLowerCase(), mosaicFilter);
    }

    public Collection<Filter> getFilters(){ return this.filterHashMap.values(); }
}
