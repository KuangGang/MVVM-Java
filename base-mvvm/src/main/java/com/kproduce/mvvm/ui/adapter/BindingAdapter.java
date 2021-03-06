package com.kproduce.mvvm.ui.adapter;

import androidx.databinding.ViewDataBinding;

import java.util.List;

/**
 * 单个布局使用DataBinding的Adapter
 *
 * @author by KG on 2022/05/19
 */
public class BindingAdapter<T, DB extends ViewDataBinding> extends BaseAdapter<T, DB> {

    public BindingAdapter(int layoutId, int variableId) {
        this(layoutId, variableId, null);
    }

    public BindingAdapter(int layoutId, int variableId, List<T> datas) {
        super(layoutId, variableId, datas);
    }

    @Override
    public void onBind(int position, DB dataBinding, T data) {
        dataBinding.setVariable(mVariableId, data);
    }
}
