package com.boyanik.newsreader

import android.support.v7.app.AppCompatActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.os.Bundle
import ru.mail.park.articlelistlib.Article

import ru.mail.park.articlelistlib.ArticleListFragment
import ru.mail.park.articlelistlib.OnArticleClickListener

class MainActivity : AppCompatActivity(), OnArticleClickListener {
    private val fragmentManager: FragmentManager = supportFragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
    private var articleListFragment = ArticleListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        articleListFragment.setOnArticleClickListener(this)
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_container, articleListFragment)
        fragmentTransaction.commit()
    }

    override fun onArticleClick(article: Article) {
        fragmentTransaction = fragmentManager.beginTransaction()
        val articleFragment = ArticleFragment.newInstance(article)
        fragmentTransaction.replace(R.id.main_container, articleFragment)
                .addToBackStack("new_clicked")
        fragmentTransaction.commit()
    }
}
