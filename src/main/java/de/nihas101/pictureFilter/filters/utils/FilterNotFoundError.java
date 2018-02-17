package de.nihas101.pictureFilter.filters.utils;

public class FilterNotFoundError extends Error {
    public FilterNotFoundError(String filter) { super("The requested filter: " + filter + " does not exist"); }
}
