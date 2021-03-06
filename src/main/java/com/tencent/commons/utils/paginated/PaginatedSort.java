package com.tencent.commons.utils.paginated;

/**
 * @author bobzbfeng
 */
public enum PaginatedSort {
    ASC("asc"),
    DESC("desc");

    private String label;

    PaginatedSort(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}