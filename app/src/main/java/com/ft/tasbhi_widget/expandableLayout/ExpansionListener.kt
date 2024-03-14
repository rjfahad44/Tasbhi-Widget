package com.ft.tasbhi_widget.expandableLayout

import android.animation.Animator

class ExpansionListener(
    private val expandableLayout: ExpandableLayout,
    private val targetExpansion: Int
) : Animator.AnimatorListener {
    private var state = 0
    private var canceled = false
    override fun onAnimationStart(animation: Animator) {
        state = if (targetExpansion == 0) State.COLLAPSING else State.EXPANDING
        expandableLayout.state = state
    }

    override fun onAnimationEnd(animation: Animator) {
        if (!canceled) {
            state = if (targetExpansion == 0) State.COLLAPSED else State.EXPANDED
            expandableLayout.state = state
            expandableLayout.setExpansion(targetExpansion.toFloat())
        }
    }

    override fun onAnimationCancel(animation: Animator) {
        canceled = true
    }

    override fun onAnimationRepeat(animation: Animator) {
        // nothing to do yet
    }
}
