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

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.view.View
import com.dtx12.android_animations_actions.actions.listeners.*
import kotlin.properties.Delegates

internal class TypedAction : ValueAnimator() {

    private var type: ActionType? = null

    private var floatTargets: FloatArray? = null

    private var intTargets: IntArray? = null

    private var view: View by Delegates.notNull()

    fun setFloatTargets(vararg floatTargets: Float) {
        this.floatTargets = floatTargets
    }

    fun setIntTargets(vararg intTargets: Int) {
        this.intTargets = intTargets
    }

    fun setType(type: ActionType?) {
        this.type = type
    }

    override fun setTarget(target: Any?) {
        if (target is View) {
            view = target
        }
    }

    override fun start() {
        updateValues()
        super.start()
    }

    private fun updateValues() {
        checkNotNull(type) { "type is not specified!" }
        checkNotNull(view) { "target is not specified!" }
        when (type) {
            ActionType.ALPHA -> prepareAlphaAnimation()
            ActionType.SCALE_TO -> prepareScaleAnimation(false)
            ActionType.SCALE_BY -> prepareScaleAnimation(true)
            ActionType.SIZE_TO -> prepareSizeAnimation(false)
            ActionType.SIZE_BY -> prepareSizeAnimation(true)
            ActionType.ROTATE_TO -> prepareRotateAnimation(false)
            ActionType.ROTATE_BY -> prepareRotateAnimation(true)
            ActionType.COLOR -> prepareColorAnimation()
            ActionType.MOVE_TO -> prepareMoveAnimation(false)
            ActionType.MOVE_BY -> prepareMoveAnimation(true)
        }
    }

    private fun prepareSizeAnimation(sizeBy: Boolean) {
        val layoutParams = view.layoutParams
        val sizeXOffset = if (sizeBy) layoutParams.width.toFloat() else 0.toFloat()
        val sizeYOffset = if (sizeBy) layoutParams.height.toFloat() else 0.toFloat()
        val x = PropertyValuesHolder.ofFloat("x", layoutParams.width.toFloat(), sizeXOffset + (floatTargets?.getOrNull(0) ?: 0f))
        val y = PropertyValuesHolder.ofFloat("y", layoutParams.height.toFloat(), sizeYOffset + (floatTargets?.getOrNull(1) ?: 0f))
        setValues(x, y)
        addUpdateListener(SizeUpdateListener(view))
    }

    private fun prepareColorAnimation() {
        setIntValues(*intTargets!!)
        setEvaluator(ArgbEvaluator.getInstance())
        addUpdateListener(ColorUpdateListener(view))
    }

    private fun prepareRotateAnimation(rotateBy: Boolean) {
        val rotationOffset: Float = if (rotateBy) view.rotation else 0f
        setFloatValues(view.rotation, rotationOffset + (floatTargets?.getOrNull(0) ?: 0f))
        addUpdateListener(RotationUpdateListener(view))
    }

    private fun prepareMoveAnimation(moveBy: Boolean) {
        val moveXOffset: Float = if (moveBy) view.x else 0f
        val moveYOffset: Float = if (moveBy) view.y else 0f
        val x = PropertyValuesHolder.ofFloat("x", view.x, moveXOffset + (floatTargets?.getOrNull(0) ?: 0f))
        val y = PropertyValuesHolder.ofFloat("y", view.y, moveYOffset + (floatTargets?.getOrNull(1) ?: 0f))
        setValues(x, y)
        addUpdateListener(MoveUpdateListener(view))
    }

    private fun prepareScaleAnimation(scaleBy: Boolean) {
        val scaleXOffset: Float = if (scaleBy) view.scaleX else 0f
        val scaleYOffset: Float = if (scaleBy) view.scaleY else 0f
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", view.scaleX, scaleXOffset + (floatTargets?.getOrNull(0) ?: 0f))
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", view.scaleY, scaleYOffset + (floatTargets?.getOrNull(1) ?: 0f))
        setValues(scaleX, scaleY)
        addUpdateListener(ScaleUpdateListener(view))
    }

    private fun prepareAlphaAnimation() {
        setFloatValues(view.alpha, (floatTargets?.getOrNull(0) ?: 0f))
        addUpdateListener(AlphaUpdateListener(view))
    }
}