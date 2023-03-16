package com.nauhalf.newsapp.screen.webview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val title = savedStateHandle.getStateFlow("title", "")
    val url = savedStateHandle.getStateFlow("url", "")

}
