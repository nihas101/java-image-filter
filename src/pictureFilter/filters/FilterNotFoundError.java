package pictureFilter;

class FilterNotFoundError extends Error {
    FilterNotFoundError(String filter) { super("The requested filter: " + filter + "does not exist"); }
}
