package techsmiths.myface.models.apiModels;

import org.springframework.web.util.UriBuilder;

public abstract class Filter {
    protected Integer page = 1;
    protected Integer pageSize = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return (page - 1) * pageSize;
    }

    public abstract void appendQueryParams(UriBuilder builder);
}
