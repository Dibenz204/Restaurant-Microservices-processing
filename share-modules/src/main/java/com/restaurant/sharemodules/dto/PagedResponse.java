package com.restaurant.sharemodules.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 * Standard paginated response wrapper used across all services.
 *
 * @param <T> The type of the items in the page.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagedResponse<T> {

    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private boolean first;

    // ==================== Factory Methods ====================

    public static <T> PagedResponse<T> of(List<T> content, int page, int size, long totalElements) {
        int totalPages = (size > 0) ? (int) Math.ceil((double) totalElements / size) : 0;

        return new PagedResponse<>(
                content,
                page,
                size,
                totalElements,
                totalPages,
                page >= totalPages - 1,
                page == 0
        );
    }

    // ==================== toString ====================

    @Override
    public String toString() {
        return "PagedResponse{" +
                "page=" + page +
                ", size=" + size +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", last=" + last +
                ", first=" + first +
                '}';
    }
}
