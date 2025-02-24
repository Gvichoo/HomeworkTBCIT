package com.example.homeworktbc.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.model.PostResponse
import com.example.homeworktbc.data.model.StoryResponse
import com.example.homeworktbc.data.resource.Resource
import com.example.homeworktbc.di.repository.PostRepository
import com.example.homeworktbc.di.repository.StoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storyRepository: StoryRepository,
    private val postRepository: PostRepository
) : ViewModel() {

    private val _story = MutableStateFlow<Resource<List<StoryResponse>>>(Resource.Loading())
    val story: StateFlow<Resource<List<StoryResponse>>> = _story

    private val _post = MutableStateFlow<Resource<List<PostResponse>>>(Resource.Loading())
    val post: StateFlow<Resource<List<PostResponse>>> = _post

    fun getStories() {
        viewModelScope.launch {
            storyRepository.getStories().collect { resource ->
                _story.value = resource }

        }
    }

    fun getPosts(){
        viewModelScope.launch {
            postRepository.getPosts().collect { resource ->
                _post.value = resource }

        }
    }
}

