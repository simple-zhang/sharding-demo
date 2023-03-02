package com.yl.common.value;

/**
 * @author simple-zhang
 * @date 3/1/2023 3:19 PM
 */
public abstract class BasePage {

    private int perSize = 10;

    private int currentPage = 1;

    public int getPerSize() {
        return perSize;
    }

    public void setPerSize(int perSize) {
        this.perSize = perSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
