package com.boyanik.newsreader

import android.support.v7.app.AppCompatActivity
import android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import android.os.Bundle
import android.widget.FrameLayout

import ru.mail.park.articlelistlib.Article
import ru.mail.park.articlelistlib.ArticleListFragment
import ru.mail.park.articlelistlib.OnArticleClickListener

private const val ARTICLE_SELECTED_BACK_STACK_NAME = "article_selected"
private const val CURRENT_ARTICLE_STATE_KEY = "current_article"

class MainActivity : AppCompatActivity(), OnArticleClickListener {
    private var fragmentManager = supportFragmentManager

    private var articleListFragment: ArticleListFragment? = null
    private var detailsArticleLayout: FrameLayout? = null
    private var currentArticle: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentArticle = savedInstanceState?.getSerializable(CURRENT_ARTICLE_STATE_KEY) as Article?
        detailsArticleLayout = findViewById(R.id.article_details_container)

        val fragmentTransaction = fragmentManager.beginTransaction()
        articleListFragment = ArticleListFragment()
        articleListFragment!!.setOnArticleClickListener(this)
        fragmentTransaction.replace(R.id.main_container, articleListFragment)

        if (currentArticle != null) {
            val currentArticleFragment = ArticleFragment.newInstance(currentArticle as Article)
            if (detailsArticleLayout == null) {  // Portrait orientation
                fragmentTransaction.replace(R.id.main_container, currentArticleFragment)
                        .addToBackStack(ARTICLE_SELECTED_BACK_STACK_NAME)
            } else {  // Landscape orientation
                fragmentManager.popBackStack(ARTICLE_SELECTED_BACK_STACK_NAME, POP_BACK_STACK_INCLUSIVE)
                fragmentTransaction.replace(R.id.article_details_container, currentArticleFragment)
            }
        }

        fragmentTransaction.commit()
    }

    override fun onArticleClick(article: Article) {
        currentArticle = article
        val currentArticleFragment = ArticleFragment.newInstance(article)
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (detailsArticleLayout == null) {  // Portrait orientation
            fragmentTransaction.replace(R.id.main_container, currentArticleFragment)
                    .addToBackStack(ARTICLE_SELECTED_BACK_STACK_NAME)
        } else {  // Landscape orientation
            fragmentTransaction.replace(R.id.article_details_container, currentArticleFragment)
        }
        fragmentTransaction.commit()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putSerializable(CURRENT_ARTICLE_STATE_KEY, currentArticle)
    }
}
