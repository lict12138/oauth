package com.tencent.commons.utils.paginated;

import java.util.List;

/**
 * @author bobzbfeng
 */

public interface Paginated<T> {

    List<T> getList();

    int getPageNumber();

    int getPerPageSize();

    long getTotalSize();

    String getSortName();

    PaginatedSort getSort();

    long getTotalPages();
}