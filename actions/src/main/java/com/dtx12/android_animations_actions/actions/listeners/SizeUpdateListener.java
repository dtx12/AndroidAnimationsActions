/**
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2015 dtx12
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.dtx12.android_animations_actions.actions.listeners;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

public class SizeUpdateListener extends BaseUpdateListener {

    public SizeUpdateListener(View view) {
        super(view);
    }

    @Override
    void onAnimationUpdate(View view, ValueAnimator animation) {
        float x = (float) animation.getAnimatedValue("x");
        float y = (float) animation.getAnimatedValue("y");
        final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) x;
        layoutParams.height = (int) y;
        view.requestLayout();
    }
}
