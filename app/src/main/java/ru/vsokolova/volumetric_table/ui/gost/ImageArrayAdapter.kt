package ru.vsokolova.volumetric_table.ui.gost

import android.app.Activity
import android.content.res.TypedArray
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ImageView.ScaleType


class ImageArrayAdapter(private val context: Activity, private val imageIdList: TypedArray) :
    BaseAdapter() {

    override fun getItem(position: Int): Any {
        return imageIdList.getResourceId(position, 0)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(context)
            imageView.scaleType = ScaleType.CENTER_INSIDE
            imageView.adjustViewBounds = true
            imageView.layoutParams = ViewGroup.LayoutParams(-1, -1)
        } else {
            imageView = convertView as ImageView
        }
        imageView.setImageResource(imageIdList.getResourceId(position, 0))
        return imageView
    }

    override fun getCount(): Int {
        return imageIdList.length()
    }
}