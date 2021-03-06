package com.kproduce.mvvm.ui.adapter;

import androidx.annotation.LayoutRes;
import androidx.databinding.ViewDataBinding;

import com.kproduce.mvvm.viewmodel.BaseAdapterViewModel;

import java.util.List;

/**
 * 单个布局使用DataBinding和ViewModel的Adapter
 * <p>
 * （注：此处的ViewModel仅是将逻辑挪移到ViewModel中，没有生命周期作用。
 * 因为每个ViewModel只会在Owner中保存一份，并且数据的恢复应该由整个页面的ViewModel负责）
 *
 * @author by KG on 2022/05/19
 */
public class BindingViewModelAdapter<T, DB extends ViewDataBinding> extends BaseAdapter<T, DB> {

    private final Class<? extends BaseAdapterViewModel<T>> mVmClass;

    public BindingViewModelAdapter(@LayoutRes int layoutId, int variableId, Class<? extends BaseAdapterViewModel<T>> clazz) {
        this(layoutId, variableId, clazz, null);
    }

    public BindingViewModelAdapter(@LayoutRes int layoutId, int variableId, Class<? extends BaseAdapterViewModel<T>> clazz, List<T> datas) {
        super(layoutId, variableId, datas);
        this.mVmClass = clazz;
    }

    @Override
    public void onBind(int position, DB dataBinding, T data) {
        BaseAdapterViewModel<T> instance = getViewModelInstance(position, data);
        if (instance != null) {
            dataBinding.setVariable(mVariableId, instance);
        }
    }

    private BaseAdapterViewModel<T> getViewModelInstance(int position, T data) {
        BaseAdapterViewModel<T> instance = null;
        if (mVmClass != null) {
            try {
                instance = mVmClass.getDeclaredConstructor(int.class, data.getClass()).newInstance(position, data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
