package com.example.neostore.ui.homescreen

import android.content.Context
import android.os.Parcelable
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.neostore.R
import java.util.*

class SlidingImageAdapter(private val context: Context, private val imageModelArraylist: ArrayList<ImageModel>) :
    PagerAdapter() {
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return imageModelArraylist.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.custom_layout, container, false)
        val imageView = imageLayout.findViewById(R.id.slider_image_view) as ImageView
        imageView.setImageResource(imageModelArraylist[position].image_drawable)
        container.addView(imageLayout, 0)
        return imageLayout
        //return super.instantiateItem(container, position)
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {
        //super.restoreState(state, loader)
    }

    override fun saveState(): Parcelable? {
        return null
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //super.destroyItem(container, position, `object`)
        //container.removeView("object" as View)
    }

}
