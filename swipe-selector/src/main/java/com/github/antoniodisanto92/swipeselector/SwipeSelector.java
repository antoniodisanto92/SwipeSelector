package com.github.antoniodisanto92.swipeselector;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;


/*
 * SwipeSelector library for Android
 * Copyright (c) 2016 Iiro Krankka (http://github.com/roughike).
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
public class SwipeSelector extends FrameLayout {
    private static final int DEFAULT_INDICATOR_SIZE = 6;
    private static final int DEFAULT_INDICATOR_MARGIN = 8;
    private static final String STATE_SELECTOR = "STATE_SELECTOR";

    private SwipeAdapter mAdapter;

    public SwipeSelector(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public SwipeSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public SwipeSelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SwipeSelector(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.SwipeSelector, defStyleAttr, defStyleRes);

        int indicatorSize;
        int indicatorMargin;
        int indicatorInActiveColor;
        int indicatorActiveColor;
        int leftButtonResource;
        int rightButtonResource;

        String customFontPath;
        int titleTextAppearance;
        int descriptionTextAppearance;
        int descriptionGravity;

        try {
            indicatorSize = (int) ta.getDimension(R.styleable.SwipeSelector_swipe_indicatorSize,
                    PixelUtils.dpToPixel(context, DEFAULT_INDICATOR_SIZE));
            indicatorMargin = (int) ta.getDimension(R.styleable.SwipeSelector_swipe_indicatorMargin,
                    PixelUtils.dpToPixel(context, DEFAULT_INDICATOR_MARGIN));
            indicatorInActiveColor = ta.getColor(R.styleable.SwipeSelector_swipe_indicatorInActiveColor,
                    ContextCompat.getColor(context, R.color.swipeselector_color_indicator_inactive));
            indicatorActiveColor = ta.getColor(R.styleable.SwipeSelector_swipe_indicatorActiveColor,
                    ContextCompat.getColor(context, R.color.swipeselector_color_indicator_active));

            leftButtonResource = ta.getResourceId(R.styleable.SwipeSelector_swipe_leftButtonResource,
                    R.drawable.ic_action_navigation_chevron_left);
            rightButtonResource = ta.getResourceId(R.styleable.SwipeSelector_swipe_rightButtonResource,
                    R.drawable.ic_action_navigation_chevron_right);

            customFontPath = ta.getString(R.styleable.SwipeSelector_swipe_customFontPath);
            titleTextAppearance = ta.getResourceId(R.styleable.SwipeSelector_swipe_titleTextAppearance,
                    -1);
            descriptionTextAppearance = ta.getResourceId(R.styleable.SwipeSelector_swipe_descriptionTextAppearance,
                    -1);
            descriptionGravity = ta.getInteger(R.styleable.SwipeSelector_swipe_descriptionGravity,
                    -1);
        } finally {
            ta.recycle();
        }

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.swipeselector_layout, this);

        ViewPager pager = (ViewPager) findViewById(R.id.swipeselector_layout_swipePager);
        ViewGroup indicatorContainer = (ViewGroup) findViewById(R.id.swipeselector_layout_circleContainer);
        ImageView leftButton = (ImageView) findViewById(R.id.swipeselector_layout_leftButton);
        ImageView rightButton = (ImageView) findViewById(R.id.swipeselector_layout_rightButton);

        mAdapter = new SwipeAdapter.Builder()
                .viewPager(pager)
                .indicatorContainer(indicatorContainer)
                .indicatorSize(indicatorSize)
                .indicatorMargin(indicatorMargin)
                .inActiveIndicatorColor(indicatorInActiveColor)
                .activeIndicatorColor(indicatorActiveColor)
                .leftButtonResource(leftButtonResource)
                .rightButtonResource(rightButtonResource)
                .leftButton(leftButton)
                .rightButton(rightButton)
                .customFontPath(customFontPath)
                .titleTextAppearance(titleTextAppearance)
                .descriptionTextAppearance(descriptionTextAppearance)
                .descriptionGravity(descriptionGravity)
                .build();
        pager.setAdapter(mAdapter);
    }

    /**
     * Set a listener to be fired every time a different item is chosen.
     * @param listener the listener that gets fired on item selection
     */
    public void setOnItemSelectedListener(OnSwipeItemSelectedListener listener) {
        mAdapter.setOnItemSelectedListener(listener);
    }

    /**
     * A method for giving this SwipeSelector something to show.
     *
     * @param swipeItems an array of {@link SwipeItem} to show
     * inside this view.
     */
    public void setItems(SwipeItem... swipeItems) {
        mAdapter.setItems(swipeItems);
    }

    /**
     * @return the selected slides' SwipeItem.
     */
    public SwipeItem getSelectedItem() {
        if (mAdapter.getCount() == 0) {
            throw new UnsupportedOperationException("The SwipeSelector " +
                    "doesn't have any items! Use the setItems() method " +
                    "for setting the items before calling getSelectedItem().");
        }

        return mAdapter.getSelectedItem();
    }

    /**
     * Select an item at the specified position and animate the change.
     *
     * @param position the position to select.
     */
    public void selectItemAt(int position) {
        selectItemAt(position, true);
    }

    /**
     * Select an item at the specified position.
     *
     * @param position the position to select.
     * @param animate should the change be animated or not.
     */
    public void selectItemAt(int position, boolean animate) {
        mAdapter.selectItemAt(position, animate);
    }

    /**
     * Select an item that has the specified value. The value was given
     * to the {@link SwipeItem} when constructing it.
     *
     * For example, an item constructed like this:
     *
     * {@code
     * new SwipeItem(1, "Example title", "Example description");
     * }
     *
     * would have the value "1" (without the quote marks). Then you would
     * select that item by using something like this:
     *
     * {@code
     * swipeSelector.selectItemWithValue(1);
     * }
     *
     * @param value the value of the item to select.
     */
    public void selectItemWithValue(Object value) {
        selectItemWithValue(value, true);
    }

    /**
     * Select an item that has the specified value. The value was given
     * to the {@link SwipeItem} when constructing it.
     *
     * For example, an item constructed like this:
     *
     * {@code
     * new SwipeItem(1, "Example title", "Example description");
     * }
     *
     * would have the value "1" (without the quote marks). Then you would
     * select that item by using something like this:
     *
     * {@code
     * swipeSelector.selectItemWithValue(1, true);
     * }
     *
     * @param value the value of the item to select.
     * @param animate should the change be animated or not.
     */
    public void selectItemWithValue(Object value, boolean animate) {
        mAdapter.selectItemWithValue(value, animate);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = mAdapter.onSaveInstanceState();
        bundle.putParcelable(STATE_SELECTOR, super.onSaveInstanceState());
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {//Shouldn't be needed, just in case
            Bundle bundle = (Bundle) state;
            mAdapter.onRestoreInstanceState(bundle);
            state = bundle.getParcelable(STATE_SELECTOR);
        }
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }
}
