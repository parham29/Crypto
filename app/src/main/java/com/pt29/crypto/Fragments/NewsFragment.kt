package com.pt29.crypto.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pt29.crypto.Activities.MainActivity
import com.pt29.crypto.Adapters.NewsListAdapter
import com.pt29.crypto.Models.NewsModel
import com.pt29.crypto.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_news.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class NewsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        view.news_recyclerview.layoutManager = LinearLayoutManager(context)
        val newsAdapter = NewsListAdapter(activity as MainActivity)


        (activity as MainActivity).navigation.selectedItemId = R.id.navigation_news

        val newsList: MutableList<NewsModel> = arrayListOf()
        newsList.add(NewsModel("پیش فروش عمومی ارز دیجیتال تلگرام لغو می شود", "پیش فروش عمومی ارز دیجیتال تلگرام لغو میشود" +
                "به گزارش روزنامه وال\u200Cاستریت ژورنال و به نقل از منابع آگاه، تلگرام قصد دارد برنامه پیش فروش عمومی (ICO) ارز دیجیتال خود را لغو کند. دلیل این تصمیم، جمع آوری سرمایه کافی در دو دوره پیش فروش خصوصی اعلام شده است.\n" +
                "\n" +
                "یک منبع ناشناس به وال\u200Cاستریت ژورنال گزارش داد که تلگرام پس از جمع آوری ۱٫۷ میلیارد دلار از ۱۶۵ سرمایه گذار بزرگ در طور دو دوره پیش فروش خصوصی، قصد لغو پیش فروش عمومی را دارد.\n" +
                "\n" +
                "به گفته این منبع از نظر تلگرام، پول حاصل از دو پیش فروش خصوصی، برای توسعه بلاک چین این شبکه کافی است. در پیش فروش خصوصی فقط افرادی با سرمایه خالص بیش از ۱ میلیون دلار امکان سرمایه گذاری و خرید توکن\u200Cهای گرام را داشتند. همچنین ساکنین کشورهای تحت تحریم\u200Cهای سازمان ملل، ایالات متحده و اتحادیه اروپا، از جمله ایران حق شرکت در پیش فروش را نداشتند.", "https://cdn.arzdigital.com/uploads/2018/05/telegram-crypto.jpg"))
        newsList.add(NewsModel("کتاب مصوری که مفهوم بلاک چین را به کودکان آموزش می\u200Cدهد!", "هر شب حدود ساعت هفت، برای فرزندانم کتاب داستان می خوانم، تا آن ها را برای خواب آماده کنم.\n" +
                "\n" +
                "در حال حاضر کتاب مورد علاقه آن ها، Dave’s Cave است، یک کتاب پر از داستان های دایناسوری.\n" +
                "\n" +
                "خوشبختانه با وجود کتاب جدید (A Place In The Blockchain)، دیگر نیازی نیست هر شب Dave’s Cave را برایشان بخوانم!\n" +
                "\n" +
                "بالاخره یک کتاب کودک در مورد بلاک چین نوشته شد.معرفی می کنم؛ این شما و این هم بلاکی!", "https://cdn.arzdigital.com/uploads/2018/05/picture-book-explains-blockchain-to-children-380x260.png"))
        newsList.add(NewsModel("«بیت کوین» نهمین مقاله\u200C پربازدید ویکی\u200Cپدیا است!", "در لیستی که از ۵۰ مقاله¬ ی پربازدید ویکی پدیا در سال ۲۰۱۷ منتشر شده است، بیتکوین موفق به کسب رتبه¬ی نهم شده است\n" +
                "مقاله ی بیت کوین ویکی پدیا در کل بیش از ۱۵ میلیون بازدید داشته و با فاصله ی کمی (کمتر از ۱ میلیون اختلاف) نسبت به مقاله ی ایالات متحده آمریکا، در رده ی نهم قرار گرفته است. این لیست نشان میدهد که بیتکوین یک میلیون بازدید بیشتر از فیلم زن شگفت انگیز (واندر وومن) و ۲.۶ میلیون بازدید بیشتر از دواین داگلاس جانسون (ملقب به دِ راک (بازیگر و قهرمان کشتی کج آمریکایی و ۲ میلیون بازدید بیشتر از ایلان ماسک داشته است.", "https://cdn.arzdigital.com/uploads/2018/04/bitcoin-wikipedia-ninth-most-read-article1-380x260.png"))
        newsList.add(NewsModel("سیف : هیچ بانک مرکزی در هیچ جای دنیا اقدام اجرایی برای فعالیت ارزهای دیجیتالی انجام نداده است", "رییس کل بانک مرکزی گفت: به دلیل نبود کارت های اعتباری بین المللی و حجم مسافران به خارج از کشور که نیاز به اسکناس ارز دارند، اگر در تامین آن مشکلی به وجود بیاید بلافاصله اثر شدید بر بازار میگذارد.", "https://cdn.arzdigital.com/uploads/2018/04/saif-no-central-bank-executing-digital-currency-activity-145x100.png"))
        newsAdapter.newsList = newsList
        view.news_recyclerview.adapter = newsAdapter


        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                NewsFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
