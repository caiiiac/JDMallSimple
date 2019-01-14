package com.simple.android.jdmallsimple.listener;

public interface IShopcarDeleteLister {

    /**
     * @param shopcarId   被删除的购物车表 id
     */
    public void onItemDelete(long shopcarId);
}
