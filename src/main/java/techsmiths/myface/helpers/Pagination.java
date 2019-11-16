package techsmiths.myface.helpers;

public class Pagination {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final int pageNumber;
    private final int pageSize;
    private final int totalNumberOfItems;

    public Pagination(Integer pageNumber, Integer pageSize, int totalNumberOfItems) {
        this.pageNumber = pageNumber == null ? 1 : pageNumber;
        this.pageSize = pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
        this.totalNumberOfItems = totalNumberOfItems;
    }

    public int getLimit() {
        return pageSize;
    }

    public int getOffset() {
        return pageSize * (pageNumber - 1);
    }

    public boolean hasPreviousPage() {
        return pageNumber <= 1;
    }

    public boolean hasNextPage() {
        return totalNumberOfItems > pageNumber * pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
