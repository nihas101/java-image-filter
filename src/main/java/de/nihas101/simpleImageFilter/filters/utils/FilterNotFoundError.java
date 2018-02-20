package de.nihas101.simpleImageFilter.filters.utils;

public class FilterNotFoundError extends Error {
    public FilterNotFoundError(String filter) {
        super("The requested filter: " + filter + " does not exist");
    }
}
