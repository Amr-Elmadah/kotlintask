package com.mytaxi.task

interface BaseView<T> {
    fun setPresenter(presenter: T)
}
