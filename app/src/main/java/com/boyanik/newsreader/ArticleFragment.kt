package com.boyanik.newsreader

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.mail.park.articlelistlib.Article
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [ArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleFragment : Fragment() {
    private var date: AppCompatTextView? = null
    private var content: AppCompatTextView? = null
    private var title: AppCompatTextView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v: View = inflater!!.inflate(R.layout.article_fragment, container, false)
        title = v.findViewById(R.id.title)
        date = v.findViewById(R.id.date)
        content = v.findViewById(R.id.content)
        setupUI(v)
        return v
    }

    private fun setupUI(v: View) {
        if (arguments != null) {
            title?.text = arguments.getString(TITLE_PARAM)
            date?.text = arguments.getString(DATE_PARAM)
            content?.text = arguments.getString(CONTENT_PARAM)
        }
    }

    companion object {
        private val TITLE_PARAM = "title"
        private val DATE_PARAM = "date"
        private val CONTENT_PARAM = "content"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided article.
         *
         * @param article Clicked article object.
         * @return A new instance of fragment ArticleFragment.
         */
        fun newInstance(article: Article): ArticleFragment {
            val fragment = ArticleFragment()
            val bundle = Bundle()
            bundle.putCharSequence(TITLE_PARAM, article.title)
            bundle.putCharSequence(DATE_PARAM, SimpleDateFormat.getDateTimeInstance().format(article.date))
            bundle.putCharSequence(CONTENT_PARAM, article.content)
            fragment.arguments = bundle
            return fragment
        }
    }
}// Required empty public constructor
