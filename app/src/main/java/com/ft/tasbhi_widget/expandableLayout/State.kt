package com.ft.tasbhi_widget.expandableLayout

interface State {
    companion object {
        const val COLLAPSED = 0
        const val COLLAPSING = 1
        const val EXPANDING = 2
        const val EXPANDED = 3
    }
}