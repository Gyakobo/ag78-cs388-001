package com.codepath.articlesearch

class ArticleApplication: Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}

