package com.yoslabs.t24haber.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private boolean loading = true;
    private int currentPage = 1;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView.Adapter mRecyclerAdapter;

    public EndlessScrollListener(LinearLayoutManager linearLayoutManager, RecyclerView.Adapter recyclerAdapter) {
        mRecyclerAdapter = recyclerAdapter;
        mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if(mLinearLayoutManager.findLastCompletelyVisibleItemPosition() == mRecyclerAdapter.getItemCount() - 1 && loading) {
            onLoadMore(currentPage);
        }
    }

    public void increaseCurrentPage() { currentPage++; }

    public void setLoading(boolean loading) { this.loading = loading; }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() { return currentPage; }

    public abstract void onLoadMore(int currentPage);

}
