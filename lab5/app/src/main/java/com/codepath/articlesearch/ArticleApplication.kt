package com.codepath.articlesearch

import android.app.Application

class ArticleApplication: Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}

