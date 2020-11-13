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

internal class RepeatAction(repeatCount: Int, private val animator: Animator) : ValueAnimator() {
    override fun start() {
        super.start()
        animator.start()
    }

    private fun countDuration(animator: Animator): Long {
        var duration: Long = 0
        // Log.v(TAG, "[countDuration] " + animator.getClass().getSimpleName());
        if (animator is AnimatorSet) {
            for (child in animator.childAnimations) {
                duration += countDuration(child)
            }
        } else if (animator is RepeatAction) {
            duration += countDuration(animator.animator)
        } else {
            duration += animator.duration
        }
        return Math.max(duration, 0)
    }

    override fun setTarget(target: Any?) {
        animator.setTarget(target)
        setTargetInternal(target, animator)
    }

    private fun setTargetInternal(target: Any?, animator: Animator) {
        if (animator is AnimatorSet) {
            for (child in animator.childAnimations) {
                setTargetInternal(target, child)
            }
        } else {
            animator.setTarget(target)
        }
    }

    init {
        val duration = countDuration(animator)
        setDuration(duration)
        setFloatValues(0f, 1f)
        setRepeatCount(Math.max(-1, repeatCount - 1))
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationRepeat(animation: Animator) {
                animator.start()
            }
        })
    }
}