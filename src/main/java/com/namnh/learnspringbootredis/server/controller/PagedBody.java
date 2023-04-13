package com.namnh.learnspringbootredis.server.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

import java.util.List;

public class PagedBody<T> extends ResponseBody {

    private PagedBody(Page<T> page) {
        super(System.currentTimeMillis(), "SUCCESS", Payload.of(page.getContent(), page.getSize(), page.getNumber(), page.getTotalPages()));
    }

    public static <T> PagedBody<T> of(Page<T> page) {
        return new PagedBody<>(page);
    }

    private record Payload<T>(List<T> content, PagedBody.Payload.Pagination pagination) {
        private static <T> Payload<T> of(List<T> content, int pageSize, int pageNumber, int totalPages) {
            return new Payload<>(content, new Pagination(pageSize, pageNumber, totalPages));
        }

        private record Pagination(
                @JsonProperty(index = 0) int pageSize,
                @JsonProperty(index = 1) int pageNumber,
                @JsonProperty(index = 2) int totalPages,
                @JsonProperty(index = 3) @JsonInclude(JsonInclude.Include.NON_EMPTY) String previousPage,
                @JsonProperty(index = 4) @JsonInclude(JsonInclude.Include.NON_EMPTY) String nextPage
        ) {
            private Pagination(int pageSize, int pageNumber, int totalPages) {
                this(pageSize, pageNumber, totalPages, null, null);
            }

            private Pagination(int pageSize, int pageNumber, int totalPages, String previousPage) {
                this(pageSize, pageNumber, totalPages, previousPage, null);
            }

            private Pagination(int pageSize, int pageNumber, int totalPages, String previousPage, String nextPage) {
                this.pageSize = pageSize;
                this.pageNumber = pageNumber;
                this.totalPages = totalPages;
                this.previousPage = previousPage;
                this.nextPage = nextPage;
            }
        }
    }
}
