/**
 * The MIT License (MIT)
 *
 *
 * Copyright (c) 2015 dtx12
 *
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.dtx12.android_animations_actions.actions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.Interpolator

object Actions {

    private fun setPropertiesForAnimator(animator: Animator, duration: Float, interpolator: Interpolator?) {
        check(duration >= 0) { "Duration cannot be negative" }
        animator.duration = (duration * 1000).toLong()
        if (interpolator != null) {
            animator.interpolator = interpolator
        }
    }

    /**
     * Repeats action forever
     *
     * @param animator action to repeat
     */
    fun forever(duration: Float, animator: Animator): Animator {
        return repeat(-1, duration, animator)
    }

    fun forever(animator: Animator): Animator {
        return repeat(-1, animator)
    }

    /**
     * Repeats action specified count of times
     *
     * @param count    how many times action should be repeated
     * @param animator action to repeat
     */
    fun repeat(count: Int, duration: Float, animator: Animator): Animator {
        return if (count <= 1 && count != -1) animator else RepeatAction(count, animator).setDuration((duration * 1000f).toLong())
    }

    fun repeat(count: Int, animator: Animator): Animator {
        return if (count <= 1 && count != -1) animator else RepeatAction(count, animator)
    }
    /**
     * Animates transition between specified colors
     *
     * @param from         start color in ARGB
     * @param to           end color in ARGB
     * @param duration     animation duration in seconds
     * @param interpolator interpolator which will be used for this animation. See also [Interpolations]
     */
    /**
     * Animates transition between specified colors
     *
     * @param from     start color in ARGB
     * @param to       end color in ARGB
     * @param duration animation duration in seconds
     */
    /**
     * Makes instant transition from start color to end
     *
     * @param from start color in ARGB
     * @param to   end color in ARGB
     */
    @JvmOverloads
    fun color(from: Int, to: Int, duration: Float = 0f, interpolator: Interpolator? = null): Animator {
        val action = TypedAction()
        setPropertiesForAnimator(action, duration, interpolator)
        action.setType(ActionType.COLOR)
        action.setIntTargets(from, to)
        return action
    }

    /**
     * Runs specified runnable
     *
     * @param runnable runnable to run
     */
    fun run(runnable: Runnable): Animator {
        val animator = ValueAnimator()
        animator.setIntValues(1)
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                runnable.run()
            }
        })
        return animator
    }
    /**
     * Animates view rotating by specified degree
     *
     * @param rotation     rotation in degrees
     * @param duration     animation duration
     * @param interpolator interpolator which will be used for this animation. See also [Interpolations]
     */
    /**
     * Animates view rotating by specified degree
     *
     * @param rotation rotation in degrees
     * @param duration animation duration
     */
    /**
     * Rotates view by specified degree instantly
     *
     * @param rotation rotation in degrees
     */
    @JvmOverloads
    fun rotateBy(rotation: Float, duration: Float = 0f, interpolator: Interpolator? = null): Animator {
        return rotate(rotation, duration, interpolator, ActionType.ROTATE_BY)
    }
    /**
     * Animates view rotating to specified degree
     *
     * @param rotation     rotation rotation in degrees
     * @param duration     duration animation duration
     * @param interpolator interpolator which will be used for this animation. See also [Interpolations]
     */
    /**
     * Animates view rotating to specified degree
     *
     * @param rotation rotation in degrees
     * @param duration animation duration
     */
    /**
     * Rotates view to specified degree instantly
     *
     * @param rotation rotation in degrees
     */
    @JvmOverloads
    fun rotateTo(rotation: Float, duration: Float = 0f, interpolator: Interpolator? = null): Animator {
        return rotate(rotation, duration, interpolator, ActionType.ROTATE_TO)
    }

    private fun rotate(rotation: Float, duration: Float, interpolator: Interpolator?, type: ActionType): Animator {
        val action = TypedAction()
        setPropertiesForAnimator(action, duration, interpolator)
        action.setType(type)
        action.setFloatTargets(rotation)
        return action
    }
    /**
     * Moves view with animation by specified x, y
     *
     * @param x            x
     * @param y            y
     * @param duration     animation duration
     * @param interpolator interpolator which will be used for this animation. See also [Interpolations]
     */
    /**
     * Moves view with animation by specified x, y
     *
     * @param x        x
     * @param y        y
     * @param duration animation duration
     */
    /**
     * Moves view by specified x, y instantly
     *
     * @param x x
     * @param y y
     */
    @JvmOverloads
    fun moveBy(x: Float, y: Float, duration: Float = 0f, interpolator: Interpolator? = null): Animator {
        return move(x, y, duration, interpolator, ActionType.MOVE_BY)
    }

    fun sizeBy(x: Float, y: Float, duration: Float): Animator {
        return sizeBy(x, y, duration, null)
    }

    fun sizeBy(x: Float, y: Float, duration: Float, interpolator: Interpolator?): Animator {
        return size(x, y, duration, interpolator, ActionType.SIZE_BY)
    }

    @JvmOverloads
    fun sizeTo(x: Float, y: Float, duration: Float, interpolator: Interpolator? = null): Animator {
        return size(x, y, duration, interpolator, ActionType.SIZE_TO)
    }

    private fun size(x: Float, y: Float, duration: Float, interpolator: Interpolator?, type: ActionType): Animator {
        val action = TypedAction()
        setPropertiesForAnimator(action, duration, interpolator)
        action.setFloatTargets(x, y)
        action.setType(type)
        return action
    }
    /**
     * Moves view with animation to specified x, y
     *
     * @param x            x
     * @param y            y
     * @param duration     animation duration
     * @param interpolator interpolator which will be used for this animation. See also [Interpolations]
     */
    /**
     * Moves view with animation to specified x, y
     *
     * @param x        x
     * @param y        y
     * @param duration animation duration
     */
    /**
     * Moves view to specified x, y instantly
     *
     * @param x x
     * @param y y
     */
    @JvmOverloads
    fun moveTo(x: Float, y: Float, duration: Float = 0f, interpolator: Interpolator? = null): Animator {
        return move(x, y, duration, interpolator, ActionType.MOVE_TO)
    }

    private fun move(x: Float, y: Float, duration: Float, interpolator: Interpolator?, type: ActionType): Animator {
        val action = TypedAction()
        setPropertiesForAnimator(action, duration, interpolator)
        action.setFloatTargets(x, y)
        action.setType(type)
        return action
    }
    /**
     * Scales view to specified scaleX, scaleY with animation
     *
     * @param scaleX       scaleX
     * @param scaleY       scaleY
     * @param duration     animation duration
     * @param interpolator interpolator which will be used for this animation. See also [Interpolations]
     */
    /**
     * Scales view to specified scaleX, scaleY with animation
     *
     * @param scaleX   scaleX
     * @param scaleY   scaleY
     * @param duration animation duration
     */
    /**
     * Scales view to specified scaleX, scaleY instantly
     *
     * @param scaleX scaleX
     * @param scaleY scaleY
     */
    @JvmOverloads
    fun scaleTo(scaleX: Float, scaleY: Float, duration: Float = 0f, interpolator: Interpolator? = null): Animator {
        return scale(scaleX, scaleY, duration, interpolator, ActionType.SCALE_TO)
    }

    /**
     * Scales view by specified scaleX, scaleY instantly
     *
     * @param scaleX scaleX
     * @param scaleY scaleY
     */
    fun scaleBy(scaleX: Float, scaleY: Float): Animator {
        return scaleBy(scaleX, scaleY, 0f)
    }

    /**
     * Scales view by specified scaleX, scaleY with animation
     *
     * @param scaleX   scaleX
     * @param scaleY   scaleY
     * @param duration animation duration
     */
    fun scaleBy(scaleX: Float, scaleY: Float, duration: Float): Animator {
        return scaleBy(scaleX, scaleY, duration, null)
    }

    /**
     * Scales view by specified scaleX, scaleY with animation
     *
     * @param scaleX       scaleX
     * @param scaleY       scaleY
     * @param duration     animation duration
     * @param interpolator interpolator which will be used for this animation. See also [Interpolations]
     */
    fun scaleBy(scaleX: Float, scaleY: Float, duration: Float, interpolator: Interpolator?): Animator {
        return scale(scaleX, scaleY, duration, interpolator, ActionType.SCALE_BY)
    }

    private fun scale(scaleX: Float, scaleY: Float, duration: Float, interpolator: Interpolator?, type: ActionType): Animator {
        val action = TypedAction()
        setPropertiesForAnimator(action, duration, interpolator)
        action.setFloatTargets(scaleX, scaleY)
        action.setType(type)
        return action
    }

    /**
     * Fades view alpha to 0 instantly
     */
    fun fadeOut(): Animator {
        return alpha(0f)
    }

    /**
     * Fades view alpha to 0 with animation
     *
     * @param duration animation duration
     */
    fun fadeOut(duration: Float): Animator {
        return alpha(0f, duration)
    }

    /**
     * Fades view alpha to 0 with animation
     *
     * @param duration     animation duration
     * @param interpolator interpolator which will be used for this animation. See also [Interpolations]
     */
    fun fadeOut(duration: Float, interpolator: Interpolator?): Animator {
        return alpha(0f, duration, interpolator)
    }

    /**
     * Fades view alpha to 1 instantly
     */
    fun fadeIn(): Animator {
        return alpha(1f)
    }

    /**
     * Fades view alpha to 1 with animation
     *
     * @param duration animation duration
     */
    fun fadeIn(duration: Float): Animator {
        return alpha(1f, duration)
    }

    /**
     * Fades view alpha to 1 with animation
     *
     * @param duration     animation duration
     * @param interpolator interpolator which will be used for this animation. See also [Interpolations]
     */
    fun fadeIn(duration: Float, interpolator: Interpolator?): Animator {
        return alpha(1f, duration, interpolator)
    }
    /**
     * Animates transition between current view alpha to target alpha
     *
     * @param alpha        alpha to set
     * @param duration     animation duration
     * @param interpolator interpolator which will be used for this animation. See also [Interpolations]
     */
    /**
     * Animates transition between current view alpha to target alpha
     *
     * @param alpha    alpha to set
     * @param duration animation duration
     */
    /**
     * Sets view alpha to specified value instantly
     *
     * @param alpha alpha to set
     */
    @JvmOverloads
    fun alpha(alpha: Float, duration: Float = 0f, interpolator: Interpolator? = null): Animator {
        val action = TypedAction()
        setPropertiesForAnimator(action, duration, interpolator)
        action.setFloatTargets(alpha)
        action.setType(ActionType.ALPHA)
        return action
    }

    /**
     * Inserts pause to sequence with specified duration
     *
     * @param duration duration in seconds
     */
    fun delay(duration: Float): Animator {
        val animator = ValueAnimator()
        setPropertiesForAnimator(animator, duration, null)
        animator.setIntValues(1)
        return animator
    }

    /**
     * Runs actions in parallel
     *
     * @param animators actions to run
     */
    fun parallel(vararg animators: Animator): Animator {
        val set = AnimatorSet()
        set.playTogether(*animators)
        return set
    }

    /**
     * Runs actions sequence
     *
     * @param animators actions to run
     */
    fun sequence(vararg animators: Animator): Animator {
        val set = AnimatorSet()
        set.playSequentially(*animators)
        return set
    }

    /**
     * Plays actions on view
     *
     * @param animator action to play
     * @param view     view where action should be played
     */
    fun play(animator: Animator, view: View) {
        prepareActions(animator, view)
        animator.start()
    }

    private fun prepareActions(animator: Animator, view: View) {
        if (animator is AnimatorSet) {
            for (child in animator.childAnimations) {
                prepareActions(child, view)
            }
        } else {
            animator.setTarget(view)
        }
    }
}