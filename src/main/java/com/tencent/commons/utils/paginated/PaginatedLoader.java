package com.tencent.commons.utils.paginated;

import java.util.List;

/**
 * @author bobzbfeng
 */

public interface PaginatedLoader<T> {

    List<T> loadList();

    long loadTotalSize();

}