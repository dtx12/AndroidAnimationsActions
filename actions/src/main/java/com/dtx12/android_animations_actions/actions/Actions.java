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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Interpolator;

public final class Actions {

    private static void setPropertiesForAnimator(@NonNull Animator animator, float duration, @Nullable Interpolator interpolator) {
        if (duration < 0) {
            throw new IllegalStateException("Duration cannot be negative");
        }
        animator.setDuration((long) (duration * 1000));
        if (interpolator != null) {
            animator.setInterpolator(interpolator);
        }
    }

    /**
     * Repeats action forever
     *
     * @param animator action to repeat
     */
    @NonNull
    public static Animator forever(@NonNull Animator animator) {
        return repeat(-1, animator);
    }

    /**
     * Repeats action specified count of times
     *
     * @param count    how many times action should be repeated
     * @param animator action to repeat
     */
    @NonNull
    public static Animator repeat(final int count, @NonNull Animator animator) {
        if (count <= 1 && count != -1)
            return animator;

        return new RepeatAction(count, animator);
    }

    /**
     * Makes instant transition from start color to end
     *
     * @param from start color in ARGB
     * @param to   end color in ARGB
     */
    @NonNull
    public static Animator color(int from, int to) {
        return color(from, to, 0);
    }

    /**
     * Animates transition between specified colors
     *
     * @param from     start color in ARGB
     * @param to       end color in ARGB
     * @param duration animation duration in seconds
     */
    @NonNull
    public static Animator color(int from, int to, float duration) {
        return color(from, to, duration, null);
    }

    /**
     * Animates transition between specified colors
     *
     * @param from         start color in ARGB
     * @param to           end color in ARGB
     * @param duration     animation duration in seconds
     * @param interpolator interpolator which will be used for this animation. See also {@link Interpolations}
     */
    @NonNull
    public static Animator color(int from, int to, float duration, @Nullable Interpolator interpolator) {
        TypedAction action = new TypedAction();
        setPropertiesForAnimator(action, duration, interpolator);
        action.setType(ActionType.COLOR);
        action.setIntTargets(from, to);
        return action;
    }

    /**
     * Runs specified runnable
     *
     * @param runnable runnable to run
     */
    @NonNull
    public static Animator run(@NonNull final Runnable runnable) {
        ValueAnimator animator = new ValueAnimator();
        animator.setIntValues(1);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                runnable.run();
            }
        });
        return animator;
    }

    /**
     * Rotates view by specified degree instantly
     *
     * @param rotation rotation in degrees
     */
    @NonNull
    public static Animator rotateBy(float rotation) {
        return rotateBy(rotation, 0);
    }

    /**
     * Animates view rotating by specified degree
     *
     * @param rotation rotation in degrees
     * @param duration animation duration
     */
    @NonNull
    public static Animator rotateBy(float rotation, float duration) {
        return rotateBy(rotation, duration, null);
    }

    /**
     * Animates view rotating by specified degree
     *
     * @param rotation     rotation in degrees
     * @param duration     animation duration
     * @param interpolator interpolator which will be used for this animation. See also {@link Interpolations}
     */
    @NonNull
    public static Animator rotateBy(float rotation, float duration, @Nullable Interpolator interpolator) {
        return rotate(rotation, duration, interpolator, ActionType.ROTATE_BY);
    }

    /**
     * Rotates view to specified degree instantly
     *
     * @param rotation rotation in degrees
     */
    @NonNull
    public static Animator rotateTo(float rotation) {
        return rotateTo(rotation, 0);
    }

    /**
     * Animates view rotating to specified degree
     *
     * @param rotation rotation in degrees
     * @param duration animation duration
     */
    @NonNull
    public static Animator rotateTo(float rotation, float duration) {
        return rotateTo(rotation, duration, null);
    }

    /**
     * Animates view rotating to specified degree
     *
     * @param rotation     rotation rotation in degrees
     * @param duration     duration animation duration
     * @param interpolator interpolator which will be used for this animation. See also {@link Interpolations}
     */
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

    /**
     * Moves view by specified x, y instantly
     *
     * @param x x
     * @param y y
     */
    @NonNull
    public static Animator moveBy(float x, float y) {
        return moveBy(x, y, 0);
    }

    /**
     * Moves view with animation by specified x, y
     *
     * @param x        x
     * @param y        y
     * @param duration animation duration
     */
    @NonNull
    public static Animator moveBy(float x, float y, float duration) {
        return moveBy(x, y, duration, null);
    }

    /**
     * Moves view with animation by specified x, y
     *
     * @param x            x
     * @param y            y
     * @param duration     animation duration
     * @param interpolator interpolator which will be used for this animation. See also {@link Interpolations}
     */
    @NonNull
    public static Animator moveBy(float x, float y, float duration, @Nullable Interpolator interpolator) {
        return move(x, y, duration, interpolator, ActionType.MOVE_BY);
    }

    /**
     * Moves view to specified x, y instantly
     *
     * @param x x
     * @param y y
     */
    @NonNull
    public static Animator moveTo(float x, float y) {
        return moveTo(x, y, 0);
    }

    /**
     * Moves view with animation to specified x, y
     *
     * @param x        x
     * @param y        y
     * @param duration animation duration
     */
    @NonNull
    public static Animator moveTo(float x, float y, float duration) {
        return moveTo(x, y, duration, null);
    }

    /**
     * Moves view with animation to specified x, y
     *
     * @param x            x
     * @param y            y
     * @param duration     animation duration
     * @param interpolator interpolator which will be used for this animation. See also {@link Interpolations}
     */
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

    /**
     * Scales view to specified scaleX, scaleY instantly
     *
     * @param scaleX scaleX
     * @param scaleY scaleY
     */
    @NonNull
    public static Animator scaleTo(float scaleX, float scaleY) {
        return scaleTo(scaleX, scaleY, 0);
    }

    /**
     * Scales view to specified scaleX, scaleY with animation
     *
     * @param scaleX   scaleX
     * @param scaleY   scaleY
     * @param duration animation duration
     */
    @NonNull
    public static Animator scaleTo(float scaleX, float scaleY, float duration) {
        return scaleTo(scaleX, scaleY, duration, null);
    }

    /**
     * Scales view to specified scaleX, scaleY with animation
     *
     * @param scaleX       scaleX
     * @param scaleY       scaleY
     * @param duration     animation duration
     * @param interpolator interpolator which will be used for this animation. See also {@link Interpolations}
     */
    @NonNull
    public static Animator scaleTo(float scaleX, float scaleY, float duration, @Nullable Interpolator interpolator) {
        return scale(scaleX, scaleY, duration, interpolator, ActionType.SCALE_TO);
    }

    /**
     * Scales view by specified scaleX, scaleY instantly
     *
     * @param scaleX scaleX
     * @param scaleY scaleY
     */
    @NonNull
    public static Animator scaleBy(float scaleX, float scaleY) {
        return scaleBy(scaleX, scaleY, 0);
    }

    /**
     * Scales view by specified scaleX, scaleY with animation
     *
     * @param scaleX   scaleX
     * @param scaleY   scaleY
     * @param duration animation duration
     */
    public static Animator scaleBy(float scaleX, float scaleY, float duration) {
        return scaleBy(scaleX, scaleY, duration, null);
    }

    /**
     * Scales view by specified scaleX, scaleY with animation
     *
     * @param scaleX       scaleX
     * @param scaleY       scaleY
     * @param duration     animation duration
     * @param interpolator interpolator which will be used for this animation. See also {@link Interpolations}
     */
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

    /**
     * Fades view alpha to 0 instantly
     */
    @NonNull
    public static Animator fadeOut() {
        return alpha(0);
    }

    /**
     * Fades view alpha to 0 with animation
     *
     * @param duration animation duration
     */
    @NonNull
    public static Animator fadeOut(float duration) {
        return alpha(0, duration);
    }

    /**
     * Fades view alpha to 0 with animation
     *
     * @param duration     animation duration
     * @param interpolator interpolator which will be used for this animation. See also {@link Interpolations}
     */
    @NonNull
    public static Animator fadeOut(float duration, @Nullable Interpolator interpolator) {
        return alpha(0, duration, interpolator);
    }

    /**
     * Fades view alpha to 1 instantly
     */
    @NonNull
    public static Animator fadeIn() {
        return alpha(1);
    }

    /**
     * Fades view alpha to 1 with animation
     *
     * @param duration animation duration
     */
    @NonNull
    public static Animator fadeIn(float duration) {
        return alpha(1, duration);
    }

    /**
     * Fades view alpha to 1 with animation
     *
     * @param duration     animation duration
     * @param interpolator interpolator which will be used for this animation. See also {@link Interpolations}
     */
    @NonNull
    public static Animator fadeIn(float duration, @Nullable Interpolator interpolator) {
        return alpha(1, duration, interpolator);
    }

    /**
     * Sets view alpha to specified value instantly
     *
     * @param alpha alpha to set
     */
    @NonNull
    public static Animator alpha(float alpha) {
        return alpha(alpha, 0);
    }

    /**
     * Animates transition between current view alpha to target alpha
     *
     * @param alpha    alpha to set
     * @param duration animation duration
     */
    @NonNull
    public static Animator alpha(float alpha, float duration) {
        return alpha(alpha, duration, null);
    }

    /**
     * Animates transition between current view alpha to target alpha
     *
     * @param alpha        alpha to set
     * @param duration     animation duration
     * @param interpolator interpolator which will be used for this animation. See also {@link Interpolations}
     */
    @NonNull
    public static Animator alpha(float alpha, float duration, @Nullable Interpolator interpolator) {
        TypedAction action = new TypedAction();
        setPropertiesForAnimator(action, duration, interpolator);
        action.setFloatTargets(alpha);
        action.setType(ActionType.ALPHA);
        return action;
    }

    /**
     * Inserts pause to sequence with specified duration
     *
     * @param duration duration in seconds
     */
    @NonNull
    public static Animator delay(float duration) {
        ValueAnimator animator = new ValueAnimator();
        setPropertiesForAnimator(animator, duration, null);
        animator.setIntValues(1);
        return animator;
    }

    /**
     * Runs actions in parallel
     *
     * @param animators actions to run
     */
    @NonNull
    public static Animator parallel(@NonNull Animator... animators) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animators);
        return set;
    }

    /**
     * Runs actions sequence
     *
     * @param animators actions to run
     */
    @NonNull
    public static Animator sequence(@NonNull Animator... animators) {
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(animators);
        return set;
    }

    /**
     * Plays actions on view
     *
     * @param animator action to play
     * @param view     view where action should be played
     */
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
        } else {
            animator.setTarget(view);
        }
    }
}
