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


package com.dtx12.android_animations_actions.actions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;

class RepeatAction extends ValueAnimator {
    private final Animator animator;

    RepeatAction(int repeatCount, Animator animator) {
        this.animator = animator;
        long duration = countDuration(animator);
        setDuration(duration);
        setFloatValues(0, 1);
        setRepeatCount(Math.max(-1, repeatCount - 1));
        addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                RepeatAction.this.animator.start();
            }
        });
    }

    @Override
    public void start() {
        super.start();
        animator.start();
    }

    private long countDuration(Animator animator) {
        long duration = 0;
        if (animator instanceof AnimatorSet) {
            for (Animator child : ((AnimatorSet) animator).getChildAnimations()) {
                duration += countDuration(child);
            }
        } else if (animator instanceof RepeatAction) {
            duration += countDuration(((RepeatAction) animator).animator);
        } else {
            duration += animator.getDuration();
        }
        return Math.max(duration, 0);
    }

    @Override
    public void setTarget(Object target) {
        if (animator instanceof AnimatorSet) {
            AnimatorSet set = ((AnimatorSet) animator);
            for (Animator child : set.getChildAnimations()) {
                child.setTarget(target);
            }
        } else {
            animator.setTarget(target);
        }
    }
}
