package nbq.springboot.thymeleaf.lesson3.beans;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class PaginationBean<T> {
    private List<T> contents;
    private Integer totalPages;
    private Integer totalItems;
    private Integer currentPage;
    private String sortField;
    private String sortDir;
    private String reverseSortDir;
    private String dirPath;

    public PaginationBean() {
    }

    public PaginationBean(Integer currentPage, String sortField, String sortDir) {
        this.currentPage = currentPage;
        this.sortField = sortField;
        this.sortDir = sortDir;
    }

    public PaginationBean(List<T> contents, Integer totalPages, Integer totalItems, Integer currentPage,
                          String sortField, String sortDir, String reverseSortDir, String dirPath) {
        this.contents = contents;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
        this.currentPage = currentPage;
        this.sortField = sortField;
        this.sortDir = sortDir;
        this.reverseSortDir = reverseSortDir;
        this.dirPath = dirPath;
    }

    public List<T> getContents() {
        return contents;
    }

    public void setContents(List<T> contents) {
        this.contents = contents;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortDir() {
        return sortDir;
    }

    public void setSortDir(String sortDir) {
        this.sortDir = sortDir;
    }

    public String getReverseSortDir() {
        return reverseSortDir;
    }

    public void setReverseSortDir(String reverseSortDir) {
        this.reverseSortDir = reverseSortDir;
    }

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }
}
