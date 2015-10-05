package com.fia.mia.expandlistviewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fia.mia.expandlistviewdemo.R;
import com.fia.mia.expandlistviewdemo.data.MyDemoData;
import com.fia.mia.expandlistviewdemo.viewer.ExpandAnimation;

import java.util.ArrayList;
import java.util.HashMap;

public class MyDemoAdapter extends BaseAdapter {

	Context mContext;
	private final static String LOGTAG = MyDemoAdapter.class
			.getSimpleName();


	private Context context;
	private LayoutInflater li;
	private HashMap<String, Boolean> isExpandList;

	// 清單的資料，常用一個可變動的陣列或是集合來儲存，在此以ArrayList<E>為例
	private ArrayList<MyDemoData> array;


	public MyDemoAdapter(Context context, ArrayList<MyDemoData> array) {
		this.context = context;
		this.array = array;
		this.li = LayoutInflater.from(context);
		isExpandList = new HashMap<String, Boolean>();
	}

	@Override
	public int getCount() {
		return array.size();
	}

	@Override
	public Object getItem(int position) {
		return array.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = new ViewHolder();
		if(convertView == null){
			convertView = li.inflate(R.layout.expand_list_item, parent, false);
			viewHolder.frontView = (LinearLayout) convertView.findViewById(R.id.push_frontView);
			viewHolder.titleTV = (TextView) convertView.findViewById(R.id.push_list_title_tv);
			viewHolder.timeTV = (TextView) convertView.findViewById(R.id.push_list_time_tv);
			viewHolder.contentImg = (ImageView) convertView.findViewById(R.id.push_list_expend_content);
			viewHolder.contentTV = (TextView) convertView.findViewById(R.id.push_list_content);
			viewHolder.contentli = (LinearLayout) convertView.findViewById(R.id.push_list_content_li);
			viewHolder.ivDelete = (ImageView) convertView.findViewById(R.id.ivDelete);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		MyDemoData data = (MyDemoData)getItem(pos);
		viewHolder.titleTV.setText(data.getTitle());
		viewHolder.contentTV.setText(data.getContent());
		final String dataId = data.getIndexId();


		// 初始狀態
		if (isExpandList.containsKey(dataId) && isExpandList.get(dataId)) {
			viewHolder.contentli.setVisibility(View.VISIBLE);
			viewHolder.contentli.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
			viewHolder.contentli.requestLayout();
			viewHolder.contentImg.setBackgroundResource(R.drawable.arrow_listup);
		} else {
			viewHolder.contentli.setVisibility(View.GONE);
			viewHolder.contentImg.setBackgroundResource(R.drawable.arrow_listdn);
		}


		final View animationView1 = viewHolder.contentli;
		final View animationImg = viewHolder.contentImg;
		viewHolder.frontView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				ExpandAnimation expandAni = new ExpandAnimation(animationView1, animationImg, 600, true);
				expandAni.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationStart(Animation arg0) {
						//showReadState(viewHolder);
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
					}

					@Override
					public void onAnimationEnd(Animation animation) {
						if (isExpandList.containsKey(dataId) && isExpandList.get(dataId)) {
							isExpandList.put(dataId, false);
						} else {
							isExpandList.put(dataId, true);
						}
					}
				});
				animationView1.startAnimation(expandAni);
			}
		});

		return convertView;
	}

	static class ViewHolder {
		LinearLayout frontView;
		TextView titleTV;
		TextView timeTV;
		TextView contentTV;
		ImageView contentImg;
		LinearLayout contentli;
		ImageView ivDelete;
	}
}

