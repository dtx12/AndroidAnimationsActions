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

package com.dtx12.android_animations_actions.library;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Interpolator;

public final class Actions {

    private static void setPropertiesForAnimator(@NonNull Animator animator, float duration, @Nullable Interpolator interpolator) {
        animator.setDuration((long) (duration * 1000));
        if (interpolator != null) {
            animator.setInterpolator(interpolator);
        }
    }

    @NonNull
    public static Animator forever(@NonNull Animator animator) {
        return repeat(Integer.MAX_VALUE, animator);
    }

    @NonNull
    public static Animator repeat(final int count, @NonNull Animator animator) {
        if (count <= 1)
            return animator;
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(animator);
        final boolean forever = count == Integer.MAX_VALUE;
        set.addListener(new SimpleAnimatorListener() {
            private int currentIndex = 1;

            @Override
            public void onAnimationEnd(Animator animation) {
                if (currentIndex < count || forever) {
                    if (!forever) {
                        currentIndex++;
                    }
                    animation.start();
                }
            }
        });
        return set;
    }

    @NonNull
    public static Animator color(int from, int to) {
        return color(from, to, 0);
    }

    @NonNull
    public static Animator color(int from, int to, float duration) {
        return color(from, to, duration, null);
    }

    @NonNull
    public static Animator color(int from, int to, float duration, @Nullable Interpolator interpolator) {
        TypedAction action = new TypedAction();
        setPropertiesForAnimator(action, duration, interpolator);
        action.setType(ActionType.COLOR);
        action.setIntTargets(from, to);
        return action;
    }

    @NonNull
    public static Animator run(@NonNull final Runnable runnable) {
        ValueAnimator animator = new ValueAnimator();
        animator.setIntValues(1);
        animator.addListener(new SimpleAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                runnable.run();
            }
        });
        return animator;
    }

    @NonNull
    public static Animator rotateBy(float rotation) {
        return rotateBy(rotation, 0);
    }

    @NonNull
    public static Animator rotateBy(float rotation, float duration) {
        return rotateBy(rotation, duration, null);
    }

    @NonNull
    public static Animator rotateBy(float rotation, float duration, @Nullable Interpolator interpolator) {
        return rotate(rotation, duration, interpolator, ActionType.ROTATE_BY);
    }

    @NonNull
    public static Animator rotateTo(float rotation) {
        return rotateTo(rotation, 0);
    }

    @NonNull
    public static Animator rotateTo(float rotation, float duration) {
        return rotateTo(rotation, duration, null);
    }

    @NonNull
    public static Animator rotateTo(float rotation, float duration, @Nullable Interpolator interpolator) {
        return rotate(rotation, duration, interpolator, ActionType.ROTATE_TO);
    }

    @NonNull
    private static Animator rotate(float rotation, float duration, @Nullable Interpolator interpolator, ActionType type) {
        TypedAction action = new TypedAction();
        setPropertiesForAnimator(action, duration, interpolator);
        action.setType(type);
        action.setFloatTargets(rotation);
        return action;
    }

    @NonNull
    public static Animator moveBy(float x, float y) {
        return moveBy(x, y, 0);
    }

    @NonNull
    public static Animator moveBy(float x, float y, float duration) {
        return moveBy(x, y, duration, null);
    }

    @NonNull
    public static Animator moveBy(float x, float y, float duration, @Nullable Interpolator interpolator) {
        return move(x, y, duration, interpolator, ActionType.MOVE_BY);
    }

    @NonNull
    public static Animator moveTo(float x, float y) {
        return moveTo(x, y, 0);
    }

    @NonNull
    public static Animator moveTo(float x, float y, float duration) {
        return moveTo(x, y, duration, null);
    }

    @NonNull
    public static Animator moveTo(float x, float y, float duration, @Nullable Interpolator interpolator) {
        return move(x, y, duration, interpolator, ActionType.MOVE_TO);
    }

    @NonNull
    private static Animator move(float x, float y, float duration, @Nullable Interpolator interpolator, @NonNull ActionType type) {
        TypedAction action = new TypedAction();
        setPropertiesForAnimator(action, duration, interpolator);
        action.setFloatTargets(x, y);
        action.setType(type);
        return action;
    }

    @NonNull
    public static Animator scaleTo(float scaleX, float scaleY) {
        return scaleTo(scaleX, scaleY, 0);
    }

    @NonNull
    public static Animator scaleTo(float scaleX, float scaleY, float duration) {
        return scaleTo(scaleX, scaleY, duration, null);
    }

    @NonNull
    public static Animator scaleTo(float scaleX, float scaleY, float duration, @Nullable Interpolator interpolator) {
        return scale(scaleX, scaleY, duration, interpolator, ActionType.SCALE_TO);
    }

    @NonNull
    public static Animator scaleBy(float scaleX, float scaleY) {
        return scaleBy(scaleX, scaleY, 0);
    }

    public static Animator scaleBy(float scaleX, float scaleY, float duration) {
        return scaleBy(scaleX, scaleY, duration, null);
    }

    @NonNull
    public static Animator scaleBy(float scaleX, float scaleY, float duration, @Nullable Interpolator interpolator) {
        return scale(scaleX, scaleY, duration, interpolator, ActionType.SCALE_BY);
    }

    @NonNull
    private static Animator scale(float scaleX, float scaleY, float duration, @Nullable Interpolator interpolator, @NonNull ActionType type) {
        TypedAction action = new TypedAction();
        setPropertiesForAnimator(action, duration, interpolator);
        action.setFloatTargets(scaleX, scaleY);
        action.setType(type);
        return action;
    }

    @NonNull
    public static Animator fadeOut() {
        return alpha(0);
    }

    @NonNull
    public static Animator fadeOut(float duration) {
        return alpha(0, duration);
    }

    @NonNull
    public static Animator fadeOut(float duration, @Nullable Interpolator interpolator) {
        return alpha(0, duration, interpolator);
    }

    @NonNull
    public static Animator fadeIn() {
        return alpha(1);
    }

    @NonNull
    public static Animator fadeIn(float duration) {
        return alpha(1, duration);
    }

    @NonNull
    public static Animator fadeIn(float duration, @Nullable Interpolator interpolator) {
        return alpha(1, duration, interpolator);
    }

    @NonNull
    public static Animator alpha(float alpha) {
        return alpha(alpha, 0);
    }

    @NonNull
    public static Animator alpha(float alpha, float duration) {
        return alpha(alpha, duration, null);
    }

    @NonNull
    public static Animator alpha(float alpha, float duration, @Nullable Interpolator interpolator) {
        TypedAction action = new TypedAction();
        setPropertiesForAnimator(action, duration, interpolator);
        action.setFloatTargets(alpha);
        action.setType(ActionType.ALPHA);
        return action;
    }

    @NonNull
    public static Animator delay(float duration) {
        ValueAnimator animator = new ValueAnimator();
        setPropertiesForAnimator(animator, duration, null);
        animator.setIntValues(1);
        return animator;
    }

    @NonNull
    public static Animator parallel(@NonNull Animator... animators) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animators);
        return set;
    }

    @NonNull
    public static Animator sequence(@NonNull Animator... animators) {
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(animators);
        return set;
    }

    public static void play(@NonNull final Animator animator, @NonNull final View view) {
        prepareActions(animator, view);
        animator.start();
    }

    private static void prepareActions(@NonNull Animator animator, @NonNull View view) {
        if (animator instanceof AnimatorSet) {
            AnimatorSet set = ((AnimatorSet) animator);
            for (Animator child : set.getChildAnimations()) {
                prepareActions(child, view);
            }
        }
        else {
            animator.setTarget(view);
        }
    }
}
